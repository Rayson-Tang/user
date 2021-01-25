package com.Rayson.test;

import com.Rayson.domain.User;
import com.Rayson.service.UserService;
import com.Rayson.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class UserTest {
    @Test
    public void test(){
        UserService userService = new UserServiceImpl();
        List<User> all = userService.findAll();
        System.out.println(all);
    }

    @Test
    public void test2(){
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setId(1);
        user.setName("111");
        userService.update(user);
        User userById = userService.findUserById("1");
        System.out.println(userById);
    }
}
