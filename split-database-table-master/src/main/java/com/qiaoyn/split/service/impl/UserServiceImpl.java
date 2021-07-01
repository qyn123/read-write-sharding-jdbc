package com.qiaoyn.split.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiaoyn.split.dao.UserMapper;
import com.qiaoyn.split.pojo.User;
import com.qiaoyn.split.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-06-29 23:30
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public List<User> getUserList() {
        return baseMapper.selectList(Wrappers.<User>lambdaQuery());
    }

    /**
     * 根据主键查询用户id
     *
     * @param id
     */
    @Override
    public User selectUserById(Integer id) {
        return baseMapper.selectById(id);
    }

}
