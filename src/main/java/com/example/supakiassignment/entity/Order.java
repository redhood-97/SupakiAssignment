package com.example.supakiassignment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NonNull
    @Column(name = "user_id")
    private String userId;

    @NonNull
    @Column(name = "item_id")
    private String itemId;

    @NonNull
    @Column(name = "cost")
    private Float cost;

    @NonNull
    @Column(name = "name")
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "ordered_at")
    private LocalDate orderedAt;

}
