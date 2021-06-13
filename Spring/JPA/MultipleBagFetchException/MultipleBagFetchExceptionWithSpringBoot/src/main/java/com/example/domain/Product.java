package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private long price;

    @ManyToOne
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_STORE"))
    private Store store;

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public void updateStore(Store store) {
        this.store = store;
    }
}
