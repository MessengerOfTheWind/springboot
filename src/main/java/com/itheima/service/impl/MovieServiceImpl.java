package com.itheima.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.MovieDao;
import com.itheima.dao.WordsDao;
import com.itheima.domain.*;
import com.itheima.domainList.MovieList;
import com.itheima.domainvo.MovieVo;
import com.itheima.domainvo.TabVo;
import com.itheima.service.MovieService;
import com.itheima.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;


    @Override
    public int save(Movie movie) {
        return  movieDao.insert(movie);
    }




    @Override
    public int update(Movie movie) {
        String movieName=movie.getMoviename();
        int uid=movie.getUid();
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq("moviename",movieName);
        qw.eq("uid",uid);
        return movieDao.update(movie,qw);
    }

    @Override
    public int updateByid(Movie movie) {

        return movieDao.updateById(movie);
    }


    @Override
    public int deleteById(String id) {
        return movieDao.deleteById(id);
    }



    @Override
    public Movie getById(String id) {
        return null;
    }

    @Override
    public Movie getByuname(String movieName) {
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq("moviename",movieName);
        return  movieDao.selectOne(qw);
    }

    @Override
    public List<Personal> getAll() {
        return null;
    }

    @Override
    public MovieResult selectPage(int page,MovieCondition movieCondition) {
        IPage<Movie> result=new Page<>(page,6);
        LambdaQueryWrapper<Movie> lqw=new LambdaQueryWrapper<Movie>();
        lqw.eq(0!=movieCondition.getUid(),Movie::getUid,movieCondition.getUid());
        lqw.eq(null!=movieCondition.getMoviename(),Movie::getMoviename,movieCondition.getMoviename());
//        lqw.like(null!=condition.getYear(),Words::getYear,condition.getYear());
//        lqw.like(null!=condition.getContent(),Words::getContent,condition.getContent());
        IPage<Movie> movieIPage=movieDao.selectPage(result,lqw);

        return new MovieResult(movieIPage.getCurrent(),movieIPage.getSize(),movieIPage.getPages(),movieIPage.getTotal(),movieIPage.getRecords());
    }

    @Override
    public Movie selectByCondition(MovieCondition movieCondition) {

        LambdaQueryWrapper<Movie> lqw=new LambdaQueryWrapper<Movie>();
        lqw.eq(0!=movieCondition.getUid(),Movie::getUid,movieCondition.getUid());
        lqw.eq(null!=movieCondition.getMoviename(),Movie::getMoviename,movieCondition.getMoviename());
        return movieDao.selectOne(lqw);
    }

    @Override
    public int deleteByCondition(MovieCondition movieCondition){
        LambdaQueryWrapper<Movie> lqw=new LambdaQueryWrapper<Movie>();
        lqw.eq(0!=movieCondition.getUid(),Movie::getUid,movieCondition.getUid());
        lqw.eq(null!=movieCondition.getMoviename(),Movie::getMoviename,movieCondition.getMoviename());
        return movieDao.delete(lqw);
    }

    @Override
    public LayuiPage selectLayui(int page, int limit, String movieVo) {
        IPage<Movie> result=new Page<>(page, limit);
        MovieVo movieVo1= JSON.parseObject(movieVo, MovieVo.class);
        System.out.println(movieVo1);
        LambdaQueryWrapper<Movie> lqw=new LambdaQueryWrapper<Movie>();
        if (movieVo1!=null){
            lqw.eq(null!=movieVo1.getMid(),Movie::getId,movieVo1.getMid());
            lqw.like(null!=movieVo1.getMuid(),Movie::getUid,movieVo1.getMuid());
            lqw.like(null!=movieVo1.getMseetime(),Movie::getSeetime,movieVo1.getMseetime());
            lqw.like(null!=movieVo1.getMmoviename(),Movie::getMoviename,movieVo1.getMmoviename());
            lqw.like(null!=movieVo1.getMdescription(), Movie::getDescription1,movieVo1.getMdescription());

        }

        IPage<Movie> tabIPage=movieDao.selectPage(result,lqw);
        return new LayuiPage(tabIPage.getTotal(),tabIPage.getRecords());
    }

    @Override
    public boolean deleteIds(MovieList movieList) {
        List<String> ids = movieList.getIds();
        if (!ids .isEmpty()&&ids.size()==0) {
            return false;
        }else{
            QueryWrapper<Movie> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            movieDao.delete(wrapper);
            return true;
        }
    }
}
