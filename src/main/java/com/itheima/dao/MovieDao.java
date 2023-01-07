package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Movie;
import org.apache.ibatis.annotations.*;


@Mapper
public interface MovieDao extends BaseMapper<Movie> {
}
