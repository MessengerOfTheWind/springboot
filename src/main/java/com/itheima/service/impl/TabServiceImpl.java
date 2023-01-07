package com.itheima.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.TabDao;
import com.itheima.domain.*;
import com.itheima.domainList.TabList;
import com.itheima.domainvo.TabVo;
import com.itheima.service.TabService;
import com.itheima.util.TabNodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabServiceImpl implements TabService {

    @Autowired
    private TabDao tabDao;
    @Override
    public List<Tab> selectAll() {
        QueryWrapper qw=new QueryWrapper<>();
        List<Tab> list= tabDao.selectList(qw);
        TabNodeUtil tabNodeUtil=new TabNodeUtil();
        return tabNodeUtil.buildTree(list);
    }

    @Override
    public int save(Tab tab) {
        System.out.println("执行了");
        return  tabDao.insert(tab);
    }

    @Override
    public int update(Tab tab) {
        String id=tab.getId();
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq("id",id);
        return tabDao.update(tab,qw);
    }

    @Override
    public int deleteById(String id) {
        return tabDao.deleteById(id);
    }

    @Override
    public LayuiPage selectLayui(int page, int limit, String tabVo) {
        IPage<Tab> result=new Page<>(page, limit);
        TabVo tabVo1= JSON.parseObject(tabVo, TabVo.class);
        System.out.println(tabVo1);
        LambdaQueryWrapper<Tab> lqw=new LambdaQueryWrapper<Tab>();
        if (tabVo1!=null){
            lqw.eq(null!=tabVo1.getTid(),Tab::getId,tabVo1.getTid());
            lqw.like(null!=tabVo1.getTname(),Tab::getName,tabVo1.getTname());
            lqw.like(null!=tabVo1.getTdescription(),Tab::getDescription,tabVo1.getTdescription());
            lqw.like(null!=tabVo1.getTurl(),Tab::getUrl,tabVo1.getTurl());
            lqw.like(null!=tabVo1.getTpid(), Tab::getPid,tabVo1.getTpid());

        }

        IPage<Tab> tabIPage=tabDao.selectPage(result,lqw);
        return new LayuiPage(tabIPage.getTotal(),tabIPage.getRecords());
    }

    @Override
    public boolean deleteIds(TabList tabList) {
        List<String> ids = tabList.getIds();
        if (!ids .isEmpty()&&ids.size()==0) {
            return false;
        }else{
            QueryWrapper<Tab> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            tabDao.delete(wrapper);
            return true;
        }
    }
}
