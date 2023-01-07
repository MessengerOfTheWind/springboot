package com.itheima.domainvo;


import com.itheima.domain.Tab;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = false)
@Data
public class TabVo extends Tab {

    private Integer tid;
    private String tname;
    private String tdescription;
    private String turl;
    private String tpid;
}
