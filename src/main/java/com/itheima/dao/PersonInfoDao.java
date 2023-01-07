package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Personal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonInfoDao extends BaseMapper<Personal> {

}
