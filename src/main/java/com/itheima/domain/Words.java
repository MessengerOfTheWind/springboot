package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Words {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private String content;
    private String year;
}
