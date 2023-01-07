package com.itheima.controller;


import com.iceolive.util.StringUtil;
import com.itheima.domain.LayuiPage;
import com.itheima.domain.User;
import com.itheima.domainList.PersonalList;
import com.itheima.domainList.UserList;
import com.itheima.service.UserService;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        //System.out.println(httpSession.getAttribute("18379883767"));
        //System.out.println("有请i去");
        int flag = userService.save(user);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        System.out.println(user);

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
//        System.out.println("youqingqiu ");
        int flag = userService.delete(id);
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
     * 修改个人头像
     *
     * @return
     */
    @PostMapping("/photo/{id}")
    public Result changePhoto(@RequestParam MultipartFile photo, @PathVariable int id) {
//        Map<String,String> data = new HashMap<>();
        //获取MultipartFile文件的后缀
        System.out.println("有请求");
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
        if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("png")) {
            OutputStream os = null;
            InputStream inputStream = null;
            String fileName = (id + "") + System.currentTimeMillis() + ".jpg";
            String userUrl = "http://localhost:92/upload/" + fileName;
            try {
                inputStream = photo.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                byte[] bs = new byte[1024];
                int len;
                // 输出的文件流保存到本地文件
                File tempFile = new File("D:\\my file\\data_img");
                if (!tempFile.exists()) {
                    tempFile.mkdirs();
                }
                os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
                // 开始读取
                while ((len = inputStream.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                os.flush();
                User user = userService.getById(id);
                user.setHeadportrait(userUrl);
                userService.update(user);
                System.out.println(1122222);
                System.out.println(user);
                Integer code = user != null ? Code.GET_OK : Code.GET_ERR;
                String msg = user != null ? "" : "数据查询失败，请重试！";
                // 完毕，关闭所有链接
                try {
                    os.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new Result(code, user, msg);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Integer code = Code.GET_ERR;
            String msg = "输入格式错误";
            return new Result(code, null, msg);
        }
    }


    @ResponseBody
    @RequestMapping("/reg")
    public Result save(MultipartFile file, User user) {
        System.out.println("有请求");
        try {
            String path = "D:\\my file\\data_img";
            String pathPhoto = "http://localhost:92/upload";
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();//获取接受到的图片名称
                String newFileName = UUID.randomUUID().toString().substring(0, 5) + "." + FilenameUtils.getExtension(name);
                File fi = new File(path, newFileName);       //将path路径与图片名称联系在一起
                if (!fi.getParentFile().exists()) {    //判断是否存在path路径下的文件夹
                    fi.getParentFile().mkdirs();       //不存在创建path路径下的文件夹
                }
                file.transferTo(fi);                        //上传图片
                user.setHeadportrait(pathPhoto + "/" + newFileName);   //为保存图片路径
            }
            if (!StringUtil.isEmpty(user.getUsername()) && !StringUtil.isEmpty(user.getPassword())) {
                if (StringUtil.isEmpty(user.getId()))
                    userService.save(user);
                else {
                    System.out.println(user);
                    userService.update(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0, e.getMessage());
        }
        return new Result();
    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {

        User user = userService.getById(id);
        Integer code = user != null ? Code.GET_OK : Code.GET_ERR;
        String msg = user != null ? "" : "数据查询失败，请重试！";
        return new Result(code, user, msg);
    }

    @GetMapping("/.{username}")
    public Result getByUsername(@PathVariable String username) {

        System.out.println("有请求出现");
        System.out.println(username);
        User user = userService.getByUsername(username);
        System.out.println(user);
        Integer code = user != null ? Code.GET_OK : Code.GET_ERR;
        String msg = user != null ? "" : "数据查询失败，请重试！";
        return new Result(code, user, msg);
    }

    @GetMapping
    public Result getAll() {
        List<User> userList = userService.getAll();
        Integer code = userList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = userList != null ? "" : "数据查询失败，请重试！";
        return new Result(code, userList, msg);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        System.out.println("有请求");
        int flag = userService.update(user);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    /**
     * 分页模糊查询
     *
     * @param currentPage
     * @param limit
     * @param userVo
     * @return
     */
    @GetMapping("/findAll")
    @ResponseBody
    public LayuiPage listNews(@RequestParam("page") Integer currentPage, @RequestParam("limit") Integer
            limit, @RequestParam(name = "QueryBlog", required = false) String userVo) {
        //分页显示
//        WordsVo wordsVo1= JSON.parseObject(wordsVo,WordsVo.class);
//        System.out.println("laile");
        return userService.selectLayui(currentPage, limit, userVo);
    }

    /**
     * 根据id批量删除
     *
     * @param userList
     * @return
     */
    @PostMapping("/large")
    public Result deleteIds(@RequestBody UserList userList) {
//        System.out.println("shanchu");
        boolean result = userService.deleteIds(userList);
        Integer code = result != false ? Code.GET_OK : Code.GET_ERR;
        String msg = result != false ? "ok" : "数据查询失败，请重试！";
        return new Result(code, result, msg);
    }

}
