package com.example;

import com.example.service.TestService1;
import com.example.service.TestService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadPoolTaskExecutorWithSpringBootApplication implements CommandLineRunner {

    @Autowired
    private TestService1 testService1;

    @Autowired
    private TestService2 testService2;

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolTaskExecutorWithSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testService1.executeThreads();
        System.out.println("Completed to invoke testSerivce1");
        Thread.sleep(10000);

        System.out.println("Executing threads with @Async annotation!");
        for(int i=0;i<10;i++) {
            testService2.executeThreads();
        }
        System.out.println("Completed to invoke testSerivce2");

    }
}
