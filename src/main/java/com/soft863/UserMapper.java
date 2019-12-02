package com.soft863;

import com.soft863.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select ("select * from user where id = #{id}")
    User getUserByID(int id);
}
