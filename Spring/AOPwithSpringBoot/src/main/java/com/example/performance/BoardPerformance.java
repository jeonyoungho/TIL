package com.example.performance;

import com.example.board.Board;

import java.util.List;

public abstract class BoardPerformance {

    private long before() {
        return System.currentTimeMillis();
    }

    private void after(long start) {
        long end = System.currentTimeMillis();
        System.out.println("수행 시간 : " + (end - start));
    }

    public List<Board> getBoards() {
        long start = before();
        List<Board> boards = findAll();
        after(start);

        return boards;
    }

    public abstract List<Board> findAll();
}
