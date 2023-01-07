package com.itheima.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UserDao;
import com.itheima.domain.*;
import com.itheima.domainList.PersonalList;
import com.itheima.domainList.UserList;
import com.itheima.domainvo.UserVo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public int save(User user){
        return userDao.insert(user) ;
    }

    public int delete(Integer id){
        int Id=id.intValue();
        return userDao.deleteById(Id);
    }

    public User getById(Integer id) {
        return userDao.selectById(id);
    }

    public User getByUsername(String username){
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq("username",username);
        return userDao.selectOne(qw);
    }

    public List<User> getAll(){
        QueryWrapper qw=new QueryWrapper();
        return userDao.selectList(qw);
    }

    public int update(User user){
        int id=user.getId();
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq("id",id);
        return userDao.update(user,qw);
    }

    @Override
    public LayuiPage selectLayui(int page, int limit, String userVo) {
        IPage<User> result=new Page<>(page, limit);
//        Tab tab=new Tab();
//        System.out.println(tab.getId());
//        System.out.println();
        UserVo userVo1= JSON.parseObject(userVo, UserVo.class);
        System.out.println(userVo1);
        LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<User>();
        if (userVo1!=null){
            lqw.eq(null!=userVo1.getUid(),User::getId,userVo1.getUid());
            lqw.like(null!=userVo1.getUname(),User::getUsername,userVo1.getUname());
            lqw.like(null!=userVo1.getUpassword(),User::getPassword,userVo1.getUpassword());
            lqw.like(null!=userVo1.getUphonenumber(),User::getPhonenumber,userVo1.getUphonenumber());
            lqw.like(null!=userVo1.getUidcard(), User::getIdcard,userVo1.getUidcard());
            lqw.like(null!=userVo1.getUemail(), User::getEmail,userVo1.getUemail());
            lqw.like(null!=userVo1.getUsex(), User::getSex,userVo1.getUsex());
            lqw.like(null!=userVo1.getUepithet(), User::getEpithet,userVo1.getUepithet());
        }

        IPage<User> userIPage=userDao.selectPage(result,lqw);
        return new LayuiPage(userIPage.getTotal(),userIPage.getRecords());
    }

    @Override
    public boolean deleteIds(UserList userList) {

        List<Long> ids = userList.getIds();
        if (!ids.isEmpty() && ids.size() == 0) {
            return false;
        } else {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.in("id", ids);
            userDao.delete(wrapper);
            return true;
        }

    }
}
