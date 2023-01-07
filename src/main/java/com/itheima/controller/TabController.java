package com.itheima.controller;


import com.itheima.domain.*;
import com.itheima.domainList.TabList;
import com.itheima.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tab")
public class TabController {

    @Autowired
    private TabService tabService;

    @GetMapping("/list")
    public List<Tab> selectAll(){
        return tabService.selectAll();
    }

    @PostMapping("/s")
    public Result save(@RequestBody Tab tab) {
        System.out.println(1);
        int flag = tabService.save(tab);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        System.out.println(tab);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @GetMapping("/d/{id}")
    public Result deleteById(@PathVariable String id) {
//        System.out.println("youqingqiu ");
        int flag = tabService.deleteById(id);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PostMapping("/b")
    public Result update(@RequestBody Tab tab) {
        System.out.println("有请求");
        int flag = tabService.update(tab);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public LayuiPage listNews(@RequestParam("page")Integer currentPage, @RequestParam("limit")Integer limit, @RequestParam(name = "QueryBlog",required = false) String  tabVo) {
        //分页显示
//        WordsVo wordsVo1= JSON.parseObject(wordsVo,WordsVo.class);
//        System.out.println("laile");
        return tabService.selectLayui(currentPage,limit,tabVo);
    }

    @PostMapping ("/large")
    public Result deleteIds(@RequestBody TabList tabList){
        System.out.println("shanchu");
        boolean result=tabService.deleteIds(tabList);
        Integer code = result != false ? Code.GET_OK : Code.GET_ERR;
        String msg = result != false ? "ok" : "数据查询失败，请重试！";
        return new Result(code,  result,msg);
    }


}
