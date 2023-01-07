package com.itheima.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;;
import lombok.Data;

@Data

public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String phonenumber;
    private String idcard;
    private String email;
    private String sex;
    private String headportrait;
    private String epithet;
}
