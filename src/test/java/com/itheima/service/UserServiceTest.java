package com.itheima.service;

import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetById(){

        User user1=userService.getById(1);
        System.out.println(user1);
    }

    @Test
    public void testGetByName(){
        User user2=userService.getByUsername("5720201510");
        System.out.println(user2);
    }

    @Test
    public void testGetAll(){
        List<User> all = userService.getAll();
        System.out.println(all);
    }

    @Test
    public void testUpdate(){
        User user=userService.getById(1);
//        System.out.println(user);
        user.setEpithet("ceshi");
        userService.update(user);
    }





}
