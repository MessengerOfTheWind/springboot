package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Tab {
//    @TableId(type = IdType.AUTO)
    private String id;
    private String name;
    private String description;
    private String url;
    private String pid;
    @TableField(exist = false)
    private List<Tab> children = new ArrayList<>();
}
