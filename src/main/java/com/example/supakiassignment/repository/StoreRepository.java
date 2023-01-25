package com.example.supakiassignment.repository;

import com.example.supakiassignment.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Item, String> {
    public List<Item> findByListingId(@Param("listing_id") String listingId);
    public Item save(Item item);
}
