package com.itheima.service;

import com.itheima.controller.Result;
import com.itheima.domain.*;

import com.itheima.domainList.MovieList;
import com.itheima.domainList.TabList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MovieService {
    /**
     *
     * 添加电影信息
     * @param movie
     * @return
     */
    public int save(Movie movie);

    //public int saveall(List<Movie> listMovie);

    /**
     *
     * 更新电影信息
     * @param movie
     * @return
     */
    public int update(Movie movie);

    public int updateByid(Movie movie);

    /**
     *
     *
     * 根据id删除电影信息
     * @param id
     * @return
     */
    public int deleteById(String id);

    /**
     * 按id查询电影信息
     * @param id
     * @return
     */
    public Movie getById(String id);
    // public Book getById(Integer id);


    /**
     *
     * 按用户名查询
     * @param uname
     * @return
     */
    public Movie getByuname(String uname);


    /**
     * 查询全部
     * @return
     */

    public List<Personal> getAll();

    public MovieResult selectPage(int page,MovieCondition movieCondition);

    public Movie selectByCondition(MovieCondition movieCondition);

    public int deleteByCondition(MovieCondition movieCondition);

    /**
     * 分页模糊查询
     * @param page
     * @param limit
     * @param movieVo
     * @return
     */
    public LayuiPage selectLayui(int page, int limit, String movieVo);

    /**
     * 批量删除
     * @param movieList
     * @return
     */
    public boolean deleteIds(MovieList movieList);
}
