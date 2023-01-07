package com.itheima.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.SignatureDao;
import com.itheima.domain.*;
import com.itheima.domainList.SignatureList;
import com.itheima.domainvo.SignatureVo;
import com.itheima.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignatureServiceImpl implements SignatureService {

    @Autowired
    private SignatureDao signatureDao;


    @Override
    public int save(Signature signature) {
        return  signatureDao.insert(signature);
    }

    @Override
    public int update(Signature signature) {
        int id=signature.getId();
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq("id",id);
        return signatureDao.update(signature,qw);
    }

    @Override
    public int deleteById(Integer id) {
        return signatureDao.deleteById(id);
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
    public PageResult2 selectPage(int page, SignatureCondition signatureCondition) {
            IPage<Signature> result=new Page<>(page,14);
            LambdaQueryWrapper<Signature> lqw=new LambdaQueryWrapper<Signature>();
            lqw.eq(0!=signatureCondition.getUid(),Signature::getUid,signatureCondition.getUid());
            lqw.like(null!=signatureCondition.getYear(),Signature::getYear,signatureCondition.getYear());
            lqw.like(null!=signatureCondition.getContent(),Signature::getContent,signatureCondition.getContent());
            IPage<Signature> signatureIPage=signatureDao.selectPage(result,lqw);
            return new PageResult2(signatureIPage.getCurrent(),signatureIPage.getSize(),signatureIPage.getPages(),signatureIPage.getTotal(),signatureIPage.getRecords());
    }

    @Override
    public LayuiPage selectLayui(int page,int limit,String signatureVo) {
//        System.out.println(signatureVo);
        IPage<Signature> result=new Page<>(page, limit);
        SignatureVo signatureVo1= JSON.parseObject(signatureVo,SignatureVo.class);
        System.out.println(signatureVo1);
        LambdaQueryWrapper<Signature> lqw=new LambdaQueryWrapper<Signature>();
        if (signatureVo1!=null){
            lqw.eq(null!=signatureVo1.getUserid(),Signature::getUid,signatureVo1.getUserid());
            lqw.like(null!=signatureVo1.getStartDate(), Signature::getYear,signatureVo1.getStartDate());
            lqw.like(null!=signatureVo1.getCon(),Signature::getContent,signatureVo1.getCon());
        }

        IPage<Signature> signatureIPage=signatureDao.selectPage(result,lqw);
        return new LayuiPage(signatureIPage.getTotal(),signatureIPage.getRecords());
    }

    @Override
    public boolean deleteIds(SignatureList signatureList) {
        List<Long> ids = signatureList.getIds();
        if (!ids .isEmpty()&&ids.size()==0) {
            return false;
        }else{
            QueryWrapper<Signature> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            signatureDao.delete(wrapper);
            return true;
        }
    }




}
