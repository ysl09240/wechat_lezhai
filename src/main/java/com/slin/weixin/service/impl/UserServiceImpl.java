package com.slin.weixin.service.impl;

import com.slin.weixin.dao.UserMapper;
import com.slin.weixin.pojo.User;
import com.slin.weixin.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SongLin.Yang
 * @data 2016-04-29 15:08
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(int id) {
        return userMapper.getUser(id);
    }
}
