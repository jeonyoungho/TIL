package com.example.performance;

import java.util.List;

public abstract class SuperPerformance<T> {

    private long before() {
        return System.currentTimeMillis();
    }

    private void after(long start) {
        long end = System.currentTimeMillis();
        System.out.println("수행 시간: " + (end - start));
    }

    public List<T> getDataAll() {
        long start = before();
        List<T> users = findAll();
        after(start);

        return users;
    }

    public abstract List<T> findAll();
}
