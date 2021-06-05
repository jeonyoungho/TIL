package com.example.domain2;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {

    @Builder
    public Product(String name, int price, String description, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    @Id
    @GeneratedValue
    @Column(name="product_id")
    private Long id;

    private String name;
    private int price;
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
