package com.example.domain2;

import com.example.domain2.Category;
import com.example.domain2.CategoryRepository;
import com.example.domain2.CategoryService;
import com.example.domain2.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@SpringBootTest
class CategoryServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        Category category1 = Category.builder()
                .name("Computer")
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

    @AfterAll
    void cleanAll() {
        categoryRepository.deleteAll();
    }

    @Transactional
    @Test
    public void testSelectCategories() {
        List<Category> categories = categoryService.findAll();
        System.out.println("=================================");
        for(Category category:categories) {
            Set<Product> products = category.getProducts();
            for(Product product:products) {
                logger.info("[{}]product name: {}", category.getName(), product.getName());
            }

        }
    }
}