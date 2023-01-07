package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Words;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordsDao extends BaseMapper<Words> {

}
