package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = "SELECT distinct s FROM Store s " +
            "JOIN FETCH s.products " +
            "JOIN FETCH s.employees")
    List<Store> findAllByFetchJoin();
}
