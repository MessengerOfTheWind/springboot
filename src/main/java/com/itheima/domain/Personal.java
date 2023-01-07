package com.itheima.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
public class Personal {

//
//
    private Integer uid;
    @TableField("id_safety")
    private String idsafety;
    @TableField("bind_mailbox")
    private String bindmailbox;
    @TableField("bind_phone")
    private String bindphone;
    @TableField("self_intro")
    private String selfintro;
    @TableField("u_name")
    private String uname;
    @TableField("self_description1")
    private String selfdescription1;
    @TableField("self_description2")
    private String selfdescription2;
    @TableField("self_description3")
    private String selfdescription3;
    @TableField("self_description4")
    private String selfdescription4;


}
