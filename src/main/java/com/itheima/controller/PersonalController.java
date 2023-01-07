package com.itheima.controller;


import com.itheima.domain.LayuiPage;
import com.itheima.domain.Personal;
import com.itheima.domain.User;
import com.itheima.domainList.PersonalList;
import com.itheima.domainList.WordList;
import com.itheima.service.PersonInfoService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personalInfo")
public class PersonalController {
    @Autowired
    private PersonInfoService personInfoService;

    @PostMapping
    public Result save(@RequestBody Personal personal) {
        //System.out.println(httpSession.getAttribute("18379883767"));
        System.out.println(personal);
        //System.out.println("有请i去");
        int flag = personInfoService.save(personal);
        boolean flag1=false;
        if (flag!=0){
            flag1=true;
        }else {
            flag1=false;
        }
      //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @GetMapping("/d/{id}")
    public Result deleteById(@PathVariable int id) {
//        System.out.println("youqingqiu ");
        int flag = personInfoService.delete(id);
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
    public Result update(@RequestBody Personal personal){
        int flag = personInfoService.update(personal);
        boolean flag1=false;
        if (flag!=0){
            flag1=true;
        }else {
            flag1=false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }


    @GetMapping("/.{name}")
    public Result getByUsername(@PathVariable String name) {

        System.out.println("有请求出现");
        System.out.println(name);
        Personal personal = personInfoService.getByuname(name);
        System.out.println(personal);
        Integer code = personal != null ? Code.GET_OK : Code.GET_ERR;
        String msg = personal != null ? "" : "数据查询失败，请重试！";
        return new Result(code,personal,msg);
    }


    @GetMapping("/{id}")
    public Result getByUsername(@PathVariable int id) {

        System.out.println("有请求出现");
        System.out.println(id);
        Personal personal = personInfoService.getById(id);
        System.out.println(personal);
        Integer code = personal != null ? Code.GET_OK : Code.GET_ERR;
        String msg = personal != null ? "" : "数据查询失败，请重试！";
        return new Result(code,personal,msg);
    }


    /**
     * 分页模糊查询
     * @param currentPage
     * @param limit
     * @param personVo
     * @return
     */
    @GetMapping("/findAll")
    @ResponseBody
    public LayuiPage listNews(@RequestParam("page")Integer currentPage, @RequestParam("limit")Integer limit, @RequestParam(name = "QueryBlog",required = false) String  personVo) {
        //分页显示
//        WordsVo wordsVo1= JSON.parseObject(wordsVo,WordsVo.class);
//        System.out.println("laile");
        return personInfoService.selectLayui(currentPage,limit,personVo);
    }

    /**
     * 根据id批量删除
     * @param personalList
     * @return
     */
    @PostMapping ("/large")
    public Result deleteIds(@RequestBody PersonalList personalList){
        System.out.println("shanchu");
        boolean result=personInfoService.deleteIds(personalList);
        Integer code = result != false ? Code.GET_OK : Code.GET_ERR;
        String msg = result != false ? "ok" : "数据查询失败，请重试！";
        return new Result(code,  result,msg);
    }



}
