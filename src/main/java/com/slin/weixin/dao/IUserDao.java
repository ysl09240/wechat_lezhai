package com.slin.weixin.dao;

import com.slin.weixin.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author SongLin.Yang
 * @data 2016-04-29 15:07
 */
@Repository("UserMapper")
public interface IUserDao  {

    public User getUser(int id);
}
