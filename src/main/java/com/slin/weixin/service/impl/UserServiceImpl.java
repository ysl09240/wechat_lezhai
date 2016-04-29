package com.slin.weixin.service.impl;

import com.slin.weixin.dao.IUserDao;
import com.slin.weixin.pojo.User;
import com.slin.weixin.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author SongLin.Yang
 * @data 2016-04-29 15:08
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;
    @Override
    public User getUser(int id) {
        return null;
    }
}
