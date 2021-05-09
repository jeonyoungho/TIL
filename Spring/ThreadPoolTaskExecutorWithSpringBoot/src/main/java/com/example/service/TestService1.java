package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class TestService1 {

    @Autowired
    @Qualifier("executor")
    private ThreadPoolTaskExecutor executor;

    public void executeThreads() {
        System.out.println("Executing threads with Autowired!");

        for(int i=0;i<10;i++) {
            executor.execute(new Job());
        }

    }

    class Job implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println("[TestService1]" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
