package com.example;

import com.example.aspect.Performance;
import com.example.board.Board;
import com.example.board.BoardRepository;
import com.example.board.BoardService;
import com.example.user.User;
import com.example.user.UserRepository;
import com.example.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
public class AoPwithSpringBootApplication implements CommandLineRunner {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public Performance performance() {
        return new Performance();
    }

    public static void main(String[] args) {
        SpringApplication.run(AoPwithSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i=1;i<=100;i++) {
            boardRepository.save(new Board(i+"번째 게시글의 제목", i+"번째 게시글의 내용"));
            userRepository.save(new User(i+"@email.com", i+"번째 사용자"));
        }
    }
}
