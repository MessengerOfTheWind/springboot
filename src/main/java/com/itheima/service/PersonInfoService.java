package com.itheima.service;

import com.itheima.domain.LayuiPage;
import com.itheima.domain.Personal;
import com.itheima.domain.User;
import com.itheima.domainList.PersonalList;
import com.itheima.domainList.WordList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PersonInfoService {
    /**
     *
     * 添加个人信息
     * @param personInfo
     * @return
     */
    public int save(Personal personInfo);

    /**
     *
     * 更新个人信息
     * @param personal
     * @return
     */
    public int update(Personal personal);

    /**
     *
     *
     * 根据uid去删除个人信息
     * @param id
     * @return
     */
    public int delete(Integer id);

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

    /**
     * 分页模糊查询
     * @param page
     * @param limit
     * @param personalVo
     * @return
     */
    public LayuiPage selectLayui(int page, int limit, String personalVo);

    /**
     * 根据id批量删除
     * @param personalList
     * @return
     */
    public boolean deleteIds(PersonalList personalList);



}
