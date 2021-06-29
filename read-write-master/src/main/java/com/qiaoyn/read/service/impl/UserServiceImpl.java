package com.qiaoyn.read.service.impl;

import com.qiaoyn.read.mapper.UserMapper;
import com.qiaoyn.read.pojo.UserPoJo;
import com.qiaoyn.read.service.UserService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-28 23:05
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserPoJo> getList() {

        // 强制路由主库
        HintManager.getInstance().setMasterRouteOnly();
        return userMapper.selectList(null);
    }

    public UserPoJo selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public void addUserPoJo(UserPoJo userPoJo) {
        userMapper.insert(userPoJo);
    }
}
