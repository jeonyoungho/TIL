package com.example.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        Category category1 = Category.builder()
                .name("computer")
                .build();
        Product product1 = Product.builder()
                .name("Desktop")
                .price(150)
                .description("Powerful PC")
                .category(category1)
                .build();
        Product product2 = Product.builder()
                .name("Notebook")
                .price(200)
                .description("Awesome notebook")
                .category(category1)
                .build();

        category1.getProducts().add(product1);
        category1.getProducts().add(product2);

        Category category2 = Category.builder()
                .name("Car")
                .build();

        Product product3 = Product.builder()
                .name("Sonata")
                .price(2000)
                .description("Popular car")
                .category(category2)
                .build();

        category2.getProducts().add(product3);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }

    @AfterEach
    void cleanAll() {
        categoryRepository.deleteAll();
    }

    @Test
    public void testSelectCategories() {
        List<Category> categories = categoryRepository.findAll();

        for(Category category:categories) {
            Set<Product> products = category.getProducts();
            for(Product product:products) {
                logger.info("[{}]product name: {}", category.getName(), product.getName());
            }

        }
    }
}