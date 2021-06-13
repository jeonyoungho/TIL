package com.example.domain;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;


//@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_batch_fetch_size=1000") // 옵션 적용
@SpringBootTest( properties = {"spring.jpa.properties.hibernate.default_batch_fetch_size=1000"} )
class StoreServiceTest {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreService storeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        storeRepository.deleteAll();
    }

//    @Transactional(readOnly = true)
    @Test
    public void NO_Repository_의_BatchSize () throws Exception {
        Store store1 = new Store("서점1", "서울시 강남구");
        store1.addProduct(new Product("책1_1", 10000L));
        store1.addProduct(new Product("책1_2", 20000L));
        store1.addEmployee(new Employee("직원1_1", LocalDate.now()));
        store1.addEmployee(new Employee("직원1_2", LocalDate.now()));
        storeRepository.save(store1);

        Store store2 = new Store("서점2", "서울시 강남구");
        store2.addProduct(new Product("책2_1", 10000L));
        store2.addProduct(new Product("책2_2", 20000L));
        store2.addEmployee(new Employee("직원2_1", LocalDate.now()));
        store2.addEmployee(new Employee("직원2_2", LocalDate.now()));
        storeRepository.save(store2);

        long size = storeService.find();
        assertThat(size, is(60000L));
    }
}