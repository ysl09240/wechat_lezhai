package com.slin.weixin.dao.impl;

import com.slin.weixin.dao.IUserDao;
import com.slin.weixin.pojo.User;
import org.springframework.stereotype.Repository;
/**
 * @author SongLin.Yang
 * @data 2016-04-29 16:05
 */
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

    @Override
    public User getUser(int id) {
        return null;
    }
}
