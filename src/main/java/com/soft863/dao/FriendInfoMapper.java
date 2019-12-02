package com.soft863.dao;

import com.soft863.entity.FriendInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface FriendInfoMapper {

    @Insert("insert into friendinfo (name,address,remark,lng,lat) values (#{name},#{address},#{remark},#{lng},#{lat})")
    int insert(FriendInfo record);
/*    int deleteByPrimaryKey(Integer id);

    int insert(FriendInfo record);

    int insertSelective(FriendInfo record);

    FriendInfo selectByPrimaryKey(Integer id);*/

    @Select("select * from friendinfo")
    List<FriendInfo> selectAll();

/*    int updateByPrimaryKeySelective(FriendInfo record);

    int updateByPrimaryKey(FriendInfo record);*/
}