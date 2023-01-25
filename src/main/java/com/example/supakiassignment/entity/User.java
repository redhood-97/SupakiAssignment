package com.example.supakiassignment.entity;


import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "email_address")
    private String emailAddress;

    @NonNull
    @Column(name = "active")
    private Boolean active;

}