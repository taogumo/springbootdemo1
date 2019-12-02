package com.soft863.service.imp;

import com.soft863.dao.FriendInfoMapper;
import com.soft863.entity.FriendInfo;
import com.soft863.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-08 11:24
 **/
@Service
@CacheConfig(cacheNames = "friend")
public class FriendServerImp implements IFriendService {

    @Autowired
    private FriendInfoMapper friendInfoMapper;

    @Override
//    @CachePut(value = "friendCache")
    public int insert(FriendInfo record) {
        return friendInfoMapper.insert (record);
    }

    @Override
//    @Cacheable(value = "friendCache")
    public List<FriendInfo> selectAll() {
        return friendInfoMapper.selectAll ();
    }

    @Override
    public FriendInfo selectByPrimaryKey(Integer id) {
        return null;
    }
}
