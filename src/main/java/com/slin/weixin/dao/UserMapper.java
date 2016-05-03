package com.slin.weixin.dao;

import com.slin.weixin.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author SongLin.Yang
 * @data 2016-05-03 14:04
 */
@Repository
public interface UserMapper {
    public User getUser(int id);
}
