package com.itheima.domainvo;


import com.itheima.domain.Personal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class PersonalVo extends Personal {
    private Integer uuid;
    private String uid_safety;
    private String ubind_mailbox;
    private String ubind_phone;
    private String uself_intro;
    private String uu_name;
    private String uself_description;
}
