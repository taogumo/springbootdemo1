package com.soft863.dao;

import com.soft863.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select ("select * from user where id = #{id}")
    User getUserByID(int id);

    @Select("select * from user where username=#{username} and password =#{password}")
    User loginByUser(User user);
    @Select ("select * from user")
    List<User> getAllUsers();
}
