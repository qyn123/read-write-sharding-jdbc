package com.qiaoyn.split.service;

import com.qiaoyn.split.pojo.User;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-29 23:29
 **/
public interface UserService {

    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    boolean save(User entity);

    /**
     * 查询全部用户信息
     * @return
     */
    List<User> getUserList();


    /**
     * 根据主键查询用户id
     */
    User selectUserById(Integer id);


}
