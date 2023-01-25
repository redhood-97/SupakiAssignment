package com.example.supakiassignment.ingestor;

import com.example.supakiassignment.config.IngestorConfigProperties;
import com.example.supakiassignment.dto.IngestorDataDto;
import com.example.supakiassignment.entity.Item;
import com.example.supakiassignment.enums.ItemStatus;
import com.example.supakiassignment.repository.StoreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ItemDataIngestor {

    private final IngestorConfigProperties ingestorConfigProperties;

    private final RestTemplate restTemplate;

    private final StoreRepository storeRepository;

    public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }

    @PostConstruct
    public void ingest() {
        List<Item> remainingBatches = new ArrayList<>();
        try {
            log.info("Beginning the ingestion of data...");
            List<IngestorDataDto> responseObjects = exchangeAsList(ingestorConfigProperties.getUrl(),
                    new ParameterizedTypeReference<>() {
                    });
            /**
             *  Currently, insertion happens in batches of 10 which improves the performance
             */
            final int[] batchSizeTracker = {0};
            List<Item> batchOfItems = new ArrayList<>();
            List<String> idBatch = new ArrayList<>();
            responseObjects.forEach(item -> {
                List<Item> existingItems = storeRepository.findByListingId(item.getId());
                log.info("The size of list returned for id = {} is {}", item.getId(), existingItems.size());
                if (existingItems.size() == 0 && !idBatch.contains(item.getId())) {
                    batchSizeTracker[0] = batchSizeTracker[0] +1;

                    Item it = Item.builder()
                            .id(item.getId())
                            .listingId(item.getId())
                            .name(item.getName())
                            .price(item.getPrice().floatValue())
                            .status(ItemStatus.AVAILABLE)
                            .build();

                    batchOfItems.add(it);
                    remainingBatches.add(it);
                    idBatch.add(item.getId());

                    if(batchSizeTracker[0] % 10 == 0) {
                        log.info("Batch insertion done ", batchSizeTracker[0], " ",responseObjects.size());
                        storeRepository.saveAll(batchOfItems);
                        batchOfItems.clear();
                        remainingBatches.clear();
                        idBatch.add(item.getId());
                    }
                } else {
                    log.info("This item is already present in the store");
                }
            });
        } catch (Exception e) {
            log.error("Encountered some error while loading the database", e);
        }

        // edge-case -> saving all the remaining batches in the last loop of lambda
        log.info("Storing the remaining batches in the last loop");
        storeRepository.saveAll(remainingBatches);

    }
}
