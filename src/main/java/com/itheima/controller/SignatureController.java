package com.itheima.controller;



import com.itheima.domain.*;
import com.itheima.domainList.SignatureList;
import com.itheima.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signature")
public class SignatureController {
    @Autowired
    private SignatureService signatureService;

    /**
     * 进行分页查找
     * @param signatureCondition
     * @param page
     * @return
     */
    @PostMapping("/{page}")
    public Result selectWordsPage(@RequestBody SignatureCondition signatureCondition, @PathVariable int page){
        System.out.println("有请求");
        PageResult2 pageResult= signatureService.selectPage(page,signatureCondition);
        Integer code = pageResult != null ? Code.GET_OK : Code.GET_ERR;
        String msg = pageResult != null ? "" : "数据查询失败，请重试！";
        return new Result(code,pageResult,msg);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public LayuiPage listNews(@RequestParam("page")Integer currentPage, @RequestParam("limit")Integer limit, @RequestParam(name = "QueryBlog",required = false) String  wordsVo) {
        //分页显示
//        WordsVo wordsVo1= JSON.parseObject(wordsVo,WordsVo.class);
        System.out.println("laile");
        return signatureService.selectLayui(currentPage,limit,wordsVo);
    }

    /**
     * 进行更新
     * @param signature
     * @return
     */
    @PostMapping("/b")
    public Result update(@RequestBody Signature signature){
        int flag = signatureService.update(signature);
        boolean flag1=false;
        if (flag!=0){
            flag1=true;
        }else {
            flag1=false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    /**
     * 进行根据id删除
     * @param id
     * @return
     */
    @GetMapping("/d/{id}")
    public Result deleteById(@PathVariable int id){
        System.out.println("youqingqiu ");
        int flag= signatureService.deleteById(id);
        boolean flag1=false;
        if (flag!=0){
            flag1=true;
        }else {
            flag1=false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PostMapping("/s")
    public Result save(@RequestBody Signature signature){
        System.out.println(1);
        int flag = signatureService.save(signature);
        boolean flag1=false;
        if (flag!=0){
            flag1=true;
        }else {
            flag1=false;
        }
        System.out.println(signature);
        return new Result(flag1 ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PostMapping ("/large")
    public Result deleteIds(@RequestBody SignatureList signatureList){
        System.out.println("shanchu");
        boolean result=signatureService.deleteIds(signatureList);
        Integer code = result != false ? Code.GET_OK : Code.GET_ERR;
        String msg = result != false ? "ok" : "数据查询失败，请重试！";
        return new Result(code,  result,msg);
    }



}
