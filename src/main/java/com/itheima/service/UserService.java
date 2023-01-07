package com.itheima.service;
import com.itheima.domain.LayuiPage;
import com.itheima.domain.User;
import com.itheima.domainList.PersonalList;
import com.itheima.domainList.UserList;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Transactional
public interface UserService {


    /**
     * 保存
     *
     * @param user
     * @return
     */
    public int save(User user);


    /**
     * 按id删除
     *
     * @param id
     * @return
     */
    public int delete(Integer id);


    /**
     * 按id查询
     * @param id
     * @return
     */
    public User getById(Integer id);
   // public Book getById(Integer id);


    /**
     *
     * 按用户名查询
     * @param username
     * @return
     */
    public User getByUsername(String username);

    /**
     * 查询全部
     * @return
     */
    //public List<Book> getAll();

    public List<User> getAll();

    public int update(User user);


    /**
     * 分页模糊查询出用户信息
     * @param page
     * @param limit
     * @param userVo
     * @return
     */
    public LayuiPage selectLayui(int page, int limit, String userVo);

    /**
     * 根据id批量删除
     * @param userList
     * @return
     */
    public boolean deleteIds(UserList userList);

}
