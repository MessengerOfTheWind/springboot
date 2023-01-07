package com.itheima.service;

import com.itheima.domain.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MovirTest {


    @Autowired
    private MovieService movieService;


    @Test
    public void TestSave(){
        Movie movie=new Movie();
//        movie.setId(1);
        movie.setUid(1);
//        movie.setMoviename("test");
        movie.setDescription1("test");
        movie.setDescription2("test");
        movie.setDescription3("test");
        movie.setDescription4("test");
        movieService.save(movie);
    }

//    @Test
//    public void TestSelectPage(){
//        movieService.selectPage(1);
//    }


}
