package com.qiaoyn.split.controller;

import com.qiaoyn.split.pojo.User;
import com.qiaoyn.split.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-29 23:33
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select")
    public List<User> select() {
        return userService.getUserList();
    }

    @GetMapping("/insert")
    public Boolean insert(User user) {
        return userService.save(user);
    }

    @GetMapping("/selectUserById/{id}")
    public User selectUserById(@PathVariable Integer id){
        return userService.selectUserById(id);
    }

}
