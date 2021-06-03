package com.example.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        List<Category> list = categoryRepository.findAll();
        System.out.println(list.size() +" <-");
        for(Category category:list) {
            System.out.println(category.getName());
        }

        System.out.println("hello");
        return list;
    }
}
