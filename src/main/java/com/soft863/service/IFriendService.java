package com.soft863.service;


import com.soft863.entity.FriendInfo;

import java.util.List;

public interface IFriendService {
//    int deleteByPrimaryKey(Integer id);

    int insert(FriendInfo record);
    List<FriendInfo> selectAll();
    FriendInfo selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKey(FriendInfo record);
}
