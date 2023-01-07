package com.itheima.controller;


import com.itheima.domain.*;
import com.itheima.domainList.WordList;
import com.itheima.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/words")
public class WordsController {
    @Autowired
    private WordsService wordsService;

    /**
     * 进行分页查找
     *
     * @param wordsCondition
     * @param page
     * @return
     */
    @PostMapping("/{page}")
    public Result selectWordsPage(@RequestBody WordsCondition wordsCondition, @PathVariable int page) {
        System.out.println("有请求");
        PageResult pageResult = wordsService.selectPage(page, wordsCondition);
        Integer code = pageResult != null ? Code.GET_OK : Code.GET_ERR;
        String msg = pageResult != null ? "" : "数据查询失败，请重试！";
        return new Result(code, pageResult, msg);
    }

    @RequestMapping("/layui")
    @ResponseBody
    public LayuiPage wordsList(int page, int limit) {
        System.out.println("有请求");
        return wordsService.selectLayuiPage(page, limit);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public LayuiPage listNews(@RequestParam("page")Integer currentPage, @RequestParam("limit")Integer limit, @RequestParam(name = "QueryBlog",required = false) String  wordsVo) {
        //分页显示
//        WordsVo wordsVo1= JSON.parseObject(wordsVo,WordsVo.class);
        System.out.println("laile");
        return wordsService.selectLayui(currentPage,limit,wordsVo);
    }


    /**
     * 进行更新
     *
     * @param words
     * @return
     */
    @PostMapping("/b")
    public Result update(@RequestBody Words words) {
        System.out.println("有请求");
        int flag = wordsService.update(words);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    /**
     * 进行根据id删除
     *
     * @param id
     * @return
     */
    @GetMapping("/d/{id}")
    public Result deleteById(@PathVariable int id) {
        System.out.println("youqingqiu ");
        int flag = wordsService.deleteById(id);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PostMapping("/s")
    public Result save(@RequestBody Words words) {
        System.out.println(1);
        int flag = wordsService.save(words);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        System.out.println(words);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PostMapping ("/large")
    public Result deleteIds(@RequestBody WordList wordList){
        System.out.println("shanchu");
        boolean result=wordsService.deleteIds(wordList);
        Integer code = result != false ? Code.GET_OK : Code.GET_ERR;
        String msg = result != false ? "ok" : "数据查询失败，请重试！";
        return new Result(code,  result,msg);
    }

}
