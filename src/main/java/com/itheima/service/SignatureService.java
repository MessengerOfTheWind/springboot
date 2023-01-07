package com.itheima.service;

import com.itheima.domain.*;
import com.itheima.domainList.SignatureList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SignatureService {
    /**
     *
     * 添加个人信息
     * @param signature
     * @return
     */
    public int save(Signature signature);

    /**
     *
     * 更新个人信息
     * @param signature
     * @return
     */
    public int update(Signature signature);

    /**
     *
     *
     * 根据uid去删除个人信息
     * @param id
     * @return
     */
    public int deleteById(Integer id);

    /**
     * 按id查询
     * @param id
     * @return
     */
    public Personal getById(Integer id);
    // public Book getById(Integer id);


    /**
     *
     * 按用户名查询
     * @param uname
     * @return
     */
    public Personal getByuname(String uname);


    /**
     * 查询全部
     * @return
     */

    public List<Personal> getAll();

    public PageResult2 selectPage(int page, SignatureCondition signatureCondition);

    public LayuiPage selectLayui(int page,int limit,String wordsVo);

//    public

    /**
     *
     * 实现批量删除，提供list的id集
     * @param signatureList
     * @return
     */
    public boolean deleteIds(SignatureList signatureList);


}
