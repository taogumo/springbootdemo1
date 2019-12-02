package com.soft863.service.imp;

import com.soft863.dao.UserMapper;
import com.soft863.entity.User;
import com.soft863.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-12 11:25
 **/
@Service
@CacheConfig
public class UserServiceImp implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable("userCache")
    public User getUserByID(int id) {
        return userMapper.getUserByID (id);
    }

    @CacheEvict("userCache")
    public User getUserByID1(int id) {
        return userMapper.getUserByID (id);
    }
    public User logUser(User user) {
        return userMapper.loginByUser (user);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers ();
    }
}
