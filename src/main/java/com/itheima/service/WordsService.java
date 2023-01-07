package com.itheima.service;

import com.itheima.domain.*;
import com.itheima.domainList.WordList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface WordsService  {
    /**
     * 添加个人信息
     *
     * @param words
     * @return
     */
    public int save(Words words);

    /**
     *
     * 更新个人信息
     * @param words
     * @return
     */
    public int update(Words words);

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

    public PageResult selectPage(int page, WordsCondition condition);

    /**
     *
     * 实现words的分页模糊查询
     * @param page
     * @param limit
     * @return
     */
    public LayuiPage selectLayuiPage(int page,int limit);

    public LayuiPage selectLayui(int page,int limit,String wordsVo);

//    public

    /**
     *
     * 实现批量删除，提供list的id集
     * @param wordList
     * @return
     */
    public boolean deleteIds(WordList wordList);
}
