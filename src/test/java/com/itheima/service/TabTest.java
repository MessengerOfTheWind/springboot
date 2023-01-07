package com.itheima.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TabTest {
    @Autowired
    private TabService tabService;

    @Test
    public void selectListTest(){
        System.out.println(tabService.selectAll());
//        tabService
    }

}
