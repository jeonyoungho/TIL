package com.example;

import com.example.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class OrmTestWithSpringApplication implements CommandLineRunner {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreService storeService;

    public static void main(String[] args) {
        SpringApplication.run(OrmTestWithSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
