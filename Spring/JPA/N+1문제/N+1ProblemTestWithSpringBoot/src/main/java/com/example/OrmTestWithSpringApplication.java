package com.example;

import com.example.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OrmTestWithSpringApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AcademyRepository academyRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrmTestWithSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        // Initialize Category and Product
//        Category category1 = Category.builder()
//                .name("Computer")
//                .build();
//        Product product1 = Product.builder()
//                .name("Desktop")
//                .price(150)
//                .description("Powerful PC")
//                .category(category1)
//                .build();
//        Product product2 = Product.builder()
//                .name("Notebook")
//                .price(200)
//                .description("Awesome notebook")
//                .category(category1)
//                .build();
//
//        category1.getProducts().add(product1);
//        category1.getProducts().add(product2);
//
//        Category category2 = Category.builder()
//                .name("Car")
//                .build();
//
//        Product product3 = Product.builder()
//                .name("Sonata")
//                .price(2000)
//                .description("Popular car")
//                .category(category2)
//                .build();
//
//        category2.getProducts().add(product3);
//
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//
////        productRepository.save(product1);
////        productRepository.save(product2);
////        productRepository.save(product3);
//
//        // Initialize Academy and Subject
//        List<Academy> academies = new ArrayList<>();
//
//        for(int i=0;i<10;i++) {
//            Academy academy = Academy.builder()
//                    .name("강남스쿨" + i)
//                    .build();
//
//            academy.addSubject(Subject.builder().name("자바웹개발" + i).build());
//            academies.add(academy);
//        }
//
//        academyRepository.saveAll(academies);
    }
}
