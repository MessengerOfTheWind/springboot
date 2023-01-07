package com.itheima.service;

import com.itheima.domain.Personal;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class PersonInfoTest {

    @Autowired
    private PersonInfoService personInfoService;


    @Test
    public void testByuname(){
        Personal p = personInfoService.getByuname("5720201510");
        System.out.println(p);
    }


    @Test
    public void testUpdate(){
        Personal p = personInfoService.getById(3);
        //p.setBind_mailbox("2062332152@qq.com");
        personInfoService.update(p);
        System.out.println(p);
    }


    @Test
    public void testSave(){
        Personal p = new Personal();
        p.setUid(4);
        p.setIdsafety("test");
        p.setBindmailbox("test");
        p.setBindphone("test");
        p.setUname("test");
        p.setSelfdescription1("test");
        p.setSelfdescription2("test");
        p.setSelfdescription3("test");
        p.setSelfdescription4("test");
        personInfoService.save(p);
    }
}
