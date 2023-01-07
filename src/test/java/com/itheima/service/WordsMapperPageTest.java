package com.itheima.service;
import com.itheima.domain.PageResult;

import com.itheima.domain.WordsCondition;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;





@SpringBootTest
public class WordsMapperPageTest {

    @Autowired
    private WordsService wordsService;


    @Test
    public void selectPage(){
        WordsCondition wordsCondition=new WordsCondition(1,"","2020/%/%");
        PageResult pageResult=wordsService.selectPage(1,wordsCondition);
    }
}

