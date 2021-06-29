package com.qiaoyn.read.service;

import com.qiaoyn.read.pojo.UserPoJo;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-28 23:04
 **/
public interface UserService {


    List<UserPoJo> getList();

    UserPoJo selectById(Integer id);


    void addUserPoJo(UserPoJo userPoJo);

}
