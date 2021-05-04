package com.example.aspect;

import com.example.history.History;
import com.example.history.HistoryRepository;
import com.example.user.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserHistory {

    @Autowired
    private HistoryRepository historyRepository;

    @Pointcut("execution(* com.example.user.UserServiceImpl.update(*)) && args(user)")
    public void updateUser(User user){}

    @AfterReturning("updateUser(user)")
    public void saveHistory(User user) {
        System.out.println("[AOP] Update " + user.getIdx() + " user...");
        historyRepository.save(new History(user.getIdx()));
    }
}
