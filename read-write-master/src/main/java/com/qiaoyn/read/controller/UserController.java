package com.qiaoyn.read.controller;

import com.qiaoyn.read.pojo.UserPoJo;
import com.qiaoyn.read.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-28 23:00
 **/
@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<UserPoJo> getAll(){
        return userService.getList();
    }

    @GetMapping("/selectUserById/{id}")
    public UserPoJo getById(@PathVariable Integer id){
        return userService.selectById(id);
    }


    @PostMapping("/add")
    public void addUser(@RequestBody UserPoJo userPoJo){
        userService.addUserPoJo(userPoJo);
    }

}
