package com.example.supakiassignment.repository;

import com.example.supakiassignment.entity.Item;
import com.example.supakiassignment.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Item, String> {
    @Query(value = "select * from public.store where listing_id = :listingId", nativeQuery = true)
    public List<Item> findByListingId(String listingId);
    public Item save(Item item);

    public Page<Item> findAllByStatusNot(String status, PageRequest pageRequest);
}
