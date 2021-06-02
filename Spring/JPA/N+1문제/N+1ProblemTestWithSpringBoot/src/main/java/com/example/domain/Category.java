package com.example.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Category {

    @Builder
    public Category(String name, Set<Product> products) {
        this.name = name;

        if(products == null) {
            this.products = new HashSet<Product>();
        } else {
            this.products = products;
        }
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<Product>();
}
