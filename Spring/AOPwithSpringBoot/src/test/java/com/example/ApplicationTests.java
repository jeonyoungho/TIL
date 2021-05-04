package com.example;

import com.example.board.BoardService;
import com.example.history.History;
import com.example.history.HistoryRepository;
import com.example.user.User;
import com.example.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    public void getBoards() throws Exception {
        assertThat(boardService.getBoards().size(), is(equalTo(100)));
//        System.out.println("Board total count: " + boardService.getBoards().size());
    }

    @Test
    public void getUsers() throws Exception {
        assertThat(userService.getUsers().size(), is(equalTo(100)));
//        System.out.println("User total count: " + userService.getUsers().size());
    }

    @Test
    public void updateUsers() throws Exception {
        List<User> users = userService.getUsers();
        for(int i=0;i<5;i++){
            User user = users.get(i);
            user.setEmail("update@gmail.com");
            userService.update(user);
        }

        List<History> histories = historyRepository.findAll();
        assertThat(histories.size(), is(equalTo(5)));
        assertThat(histories.get(0).getUserIdx(), is(equalTo(1L)));
        assertThat(histories.get(1).getUserIdx(), is(equalTo(2L)));
    }
}
