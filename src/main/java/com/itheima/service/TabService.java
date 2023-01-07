package com.itheima.service;


import com.itheima.domain.*;
import com.itheima.domainList.TabList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TabService {
    /**
     *
     * 查询出所有的tab栏信息
     * @return
     */
    public List<Tab> selectAll();

    /**
     * 添加tab信息
     * @param tab
     * @return
     */
    public int save(Tab tab);

    /**
     * 根据内容获取id进行修改
     * @param tab
     * @return
     */
    public int update(Tab tab);

    /**
     * 根据id进行删除
     * @param id
     * @return
     */
    public int deleteById(String id);

    /**
     * 分页模糊查询
     * @param page
     * @param limit
     * @param wordsVo
     * @return
     */
    public LayuiPage selectLayui(int page, int limit, String wordsVo);


    public boolean deleteIds(TabList tabList);
}
