package com.example.supakiassignment.repository;

import com.example.supakiassignment.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Item, String> {
    @Query(value = "select * from public.store where listing_id = :listingId", nativeQuery = true)
    public List<Item> findByListingId(String listingId);
    public Item save(Item item);
}
