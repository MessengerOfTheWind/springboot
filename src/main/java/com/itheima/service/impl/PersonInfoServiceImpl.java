package com.itheima.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.PersonInfoDao;
import com.itheima.domain.*;
import com.itheima.domainList.PersonalList;
import com.itheima.domainList.WordList;
import com.itheima.domainvo.PersonalVo;
import com.itheima.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoDao personInfoDao;

    public int save(Personal personInfo) {
        return personInfoDao.insert(personInfo);
    }

    public int update(Personal personal) {
        //
        int id = personal.getUid();
        QueryWrapper qw = new QueryWrapper<>();
        qw.eq("uid", id);
        return personInfoDao.update(personal, qw);
    }

    public int delete(Integer id) {
        int Id = id.intValue();
        QueryWrapper qw = new QueryWrapper<>();
        qw.eq("uid", id);
        return personInfoDao.delete(qw);
    }

    public Personal getById(Integer id) {
        QueryWrapper qw = new QueryWrapper<>();
        qw.eq("uid", id);
        int Id = id.intValue();
        return personInfoDao.selectOne(qw);
    }

    public Personal getByuname(String uname) {
        QueryWrapper qw = new QueryWrapper<>();
        qw.eq("u_name", uname);
        return personInfoDao.selectOne(qw);
    }


    public List<Personal> getAll() {
        QueryWrapper qw = new QueryWrapper();
        return personInfoDao.selectList(qw);
    }

    @Override
    public LayuiPage selectLayui(int page, int limit, String personalVo) {
        IPage<Personal> result = new Page<>(page, limit);
        PersonalVo personalVo1 = JSON.parseObject(personalVo, PersonalVo.class);
        System.out.println(personalVo1);
        LambdaQueryWrapper<Personal> lqw = new LambdaQueryWrapper<Personal>();
        if (personalVo1 != null) {
            lqw.eq(null != personalVo1.getUuid(), Personal::getUid, personalVo1.getUuid());
            lqw.like(null != personalVo1.getUid_safety(), Personal::getIdsafety, personalVo1.getUid_safety());
            lqw.like(null != personalVo1.getUbind_mailbox(), Personal::getBindmailbox, personalVo1.getUbind_mailbox());
            lqw.like(null != personalVo1.getUbind_phone(), Personal::getBindphone, personalVo1.getUbind_phone());
            lqw.like(null != personalVo1.getUu_name(), Personal::getUname, personalVo1.getUu_name());
            lqw.like(null != personalVo1.getUself_description(), Personal::getSelfdescription1, personalVo1.getUself_description());
            lqw.like(null != personalVo1.getUself_intro(), Personal::getSelfintro, personalVo1.getUself_intro());
        }

        IPage<Personal> personalIPage = personInfoDao.selectPage(result, lqw);
        return new LayuiPage(personalIPage.getTotal(), personalIPage.getRecords());
    }

    @Override
    public boolean deleteIds(PersonalList personalList) {

        List<Long> ids = personalList.getIds();
        if (!ids.isEmpty() && ids.size() == 0) {
            return false;
        } else {
            QueryWrapper<Personal> wrapper = new QueryWrapper<>();
            wrapper.in("uid", ids);
            personInfoDao.delete(wrapper);
            return true;
        }

    }
}

