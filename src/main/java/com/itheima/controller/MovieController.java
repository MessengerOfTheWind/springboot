package com.itheima.controller;


import com.iceolive.util.StringUtil;
import com.itheima.domain.*;
import com.itheima.domainList.MovieList;
import com.itheima.domainList.PersonalList;
import com.itheima.service.MovieService;
import com.itheima.service.WordsService;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    /**
     * 进行分页查找
     *
     * @param
     * @param page
     * @return
     */
    @PostMapping("/{page}")
    public Result selectWordsPage(@PathVariable int page, @RequestBody MovieCondition movieCondition) {
        System.out.println("有请求");
        MovieResult movieResult = movieService.selectPage(page, movieCondition);
        Integer code = movieResult != null ? Code.GET_OK : Code.GET_ERR;
        String msg = movieResult != null ? "" : "数据查询失败，请重试！";
        return new Result(code, movieResult, msg);
    }


    @PostMapping("/photo/{id}")
    public List<String> changePhoto(@RequestParam MultipartFile[] photos, @PathVariable int id) {
//        Map<String,String> data = new HashMap<>();
        //获取MultipartFile文件的后缀
        List<String> list = new ArrayList<>();
        System.out.println("有请求");
        for (MultipartFile photo : photos) {
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
//                User user = userService.getById(id);
//                user.setHeadportrait(userUrl);
//                userService.update(user);
//                System.out.println(1122222);
//                System.out.println(user);
//                Integer code = user != null ? Code.GET_OK : Code.GET_ERR;
//                String msg = user != null ? "" : "数据查询失败，请重试！";
                    // 完毕，关闭所有链接
                    try {
                        os.close();
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    list.add(userUrl);


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Integer code = Code.GET_ERR;
                String msg = "输入格式错误";
                list.add(msg);
            }
        }
        return list;

    }

    /**
     * 接收文件
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload")
    public Result save_picture(MultipartFile file) throws IOException {
        System.out.println("有请求");

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
            System.out.println((pathPhoto + "/" + newFileName));

            return new Result(pathPhoto + "/" + newFileName);   //为保存图片路径
        }
        return null;
    }


    /**
     * 进行更新
     *
     * @param movie
     * @return
     */
    @PostMapping("/b")
    public Result update(@RequestBody Movie movie) {
        System.out.println("有请求");
        int flag = movieService.update(movie);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PostMapping("/bb")
    public Result updateById(@RequestBody Movie movie) {
        System.out.println(movie);
        System.out.println("有请求");
        int flag = movieService.updateByid(movie);
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
    public Result deleteById(@PathVariable String id) {
        System.out.println("youqingqiu ");
        int flag = movieService.deleteById(id);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        //  System.out.println(personal);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PostMapping("/d/condition")
    public Result deleteById(@RequestBody MovieCondition movieCondition) {
        int flag = movieService.deleteByCondition(movieCondition);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }


    @GetMapping("/{movieName}")
    public Result getByMovieName(@PathVariable String movieName) {
        Movie movie = movieService.getByuname(movieName);
        System.out.println(movie);
        Integer code = movie != null ? Code.GET_OK : Code.GET_ERR;
        String msg = movie != null ? "" : "数据查询失败，请重试！";
        return new Result(code, movie, msg);
    }

    @PostMapping("/s")
    public Result save(@RequestBody Movie movie) {
//        System.out.println(1);
        int flag = movieService.save(movie);
        System.out.println(movie);
        boolean flag1 = false;
        if (flag != 0) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        System.out.println(movie);
        return new Result(flag1 ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PostMapping("/c")
    public Result selectByCondition(@RequestBody MovieCondition movieCondition) {
        Movie movie = movieService.selectByCondition(movieCondition);
        //System.out.println(movie);
        Integer code = movie != null ? Code.GET_OK : Code.GET_ERR;
        String msg = movie != null ? "" : "数据查询失败，请重试！";
        return new Result(code, movie, msg);

    }


    /**
     * 分页模糊查询
     *
     * @param currentPage
     * @param limit
     * @param movieVo
     * @return
     */
    @GetMapping("/findAll")
    @ResponseBody
    public LayuiPage listNews(@RequestParam("page") Integer currentPage, @RequestParam("limit") Integer limit, @RequestParam(name = "QueryBlog", required = false) String movieVo) {
        //分页显示
//        WordsVo wordsVo1= JSON.parseObject(wordsVo,WordsVo.class);
//        System.out.println("laile");
        return movieService.selectLayui(currentPage, limit, movieVo);
    }

    /**
     * 根据id批量删除
     *
     * @param movieList
     * @return
     */
    @PostMapping("/large")
    public Result deleteIds(@RequestBody MovieList movieList) {
        System.out.println("shanchu");
        boolean result = movieService.deleteIds(movieList);
        Integer code = result != false ? Code.GET_OK : Code.GET_ERR;
        String msg = result != false ? "ok" : "数据查询失败，请重试！";
        return new Result(code, result, msg);
    }


}
