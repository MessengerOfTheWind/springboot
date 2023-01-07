package com.itheima.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.WordsDao;
import com.itheima.domain.*;
import com.itheima.domainList.WordList;
import com.itheima.domainvo.WordsVo;
import com.itheima.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsServiceImpl implements WordsService {

    @Autowired
    private WordsDao wordsDao;


    @Override
    public int save(Words words) {
        return  wordsDao.insert(words);
    }

    @Override
    public int update(Words words) {
        int id=words.getId();
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq("id",id);
        return wordsDao.update(words,qw);
    }

    @Override
    public int deleteById(Integer id) {
        return wordsDao.deleteById(id);
    }

    @Override
    public Personal getById(Integer id) {
        return null;
    }

    @Override
    public Personal getByuname(String uname) {
        return null;
    }

    @Override
    public List<Personal> getAll() {
        return null;
    }

    @Override
    public PageResult selectPage(int page, WordsCondition condition) {
        IPage<Words> result=new Page<>(page,14);
        LambdaQueryWrapper<Words> lqw=new LambdaQueryWrapper<Words>();
        lqw.eq(0!=condition.getUid(),Words::getUid,condition.getUid());
        lqw.like(null!=condition.getYear(),Words::getYear,condition.getYear());
        lqw.like(null!=condition.getContent(),Words::getContent,condition.getContent());


        IPage<Words> wordsIPage=wordsDao.selectPage(result,lqw);
//        wordsDao.deleteBatchIds()
        return new PageResult(wordsIPage.getCurrent(),wordsIPage.getSize(),wordsIPage.getPages(),wordsIPage.getTotal(),wordsIPage.getRecords());
    }

    @Override
    public LayuiPage selectLayuiPage(int page, int limit) {
        IPage<Words> result=new Page<>(page,limit);
        LambdaQueryWrapper<Words> lqw=new LambdaQueryWrapper<Words>();
        //lqw.eq(0!=condition.getUid(),Words::getUid,condition.getUid());
        //lqw.like(null!=condition.getYear(),Words::getYear,condition.getYear());
        //lqw.like(null!=condition.getContent(),Words::getContent,condition.getContent());

        IPage<Words> wordsIPage=wordsDao.selectPage(result,lqw);
        return new LayuiPage(wordsIPage.getTotal(),wordsIPage.getRecords());
    }

    @Override
    public LayuiPage selectLayui(int page,int limit,String wordsVo) {
//        System.out.println(wordsVo);
        IPage<Words> result=new Page<>(page, limit);
        WordsVo wordsVo1= JSON.parseObject(wordsVo,WordsVo.class);
        System.out.println(wordsVo1);
        LambdaQueryWrapper<Words> lqw=new LambdaQueryWrapper<Words>();
        if (wordsVo1!=null){
            lqw.eq(null!=wordsVo1.getUserid(),Words::getUid,wordsVo1.getUserid());
            lqw.like(null!=wordsVo1.getStartDate(), Words::getYear,wordsVo1.getStartDate());
            lqw.like(null!=wordsVo1.getCon(),Words::getContent,wordsVo1.getCon());
        }

        IPage<Words> wordsIPage=wordsDao.selectPage(result,lqw);
        return new LayuiPage(wordsIPage.getTotal(),wordsIPage.getRecords());
    }

    @Override
    public boolean deleteIds(WordList wordList) {
        List<Long> ids = wordList.getIds();
        if (!ids .isEmpty()&&ids.size()==0) {
            return false;
        }else{
            QueryWrapper<Words> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            wordsDao.delete(wrapper);
            return true;
        }
    }


}
