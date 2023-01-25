package com.example.supakiassignment.entity;

import com.example.supakiassignment.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "store")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "listing_id")
    private String listingId;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "price")
    private Float price;

    @NonNull
    @Column(name = "status")
    private String status;

}