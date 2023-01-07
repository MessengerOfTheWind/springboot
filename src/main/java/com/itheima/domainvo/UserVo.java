package com.itheima.domainvo;


import com.itheima.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = false)
@Data
public class UserVo extends User {
    private Integer uid;
    private String uname;
    private String upassword;
    private Integer uphonenumber;
    private String uidcard;
    private String uemail;
    private String usex;
    private String uheadportrait;
    private String uepithet;
}
