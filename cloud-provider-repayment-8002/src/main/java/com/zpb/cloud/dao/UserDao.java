package com.zpb.cloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @dec :
 * @Date: 2020/4/15
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Mapper
public interface UserDao {

    @Update("UPDATE `user` SET age=#{age} WHERE id =#{id}")
    public Integer update(@Param("id") Long id,@Param("age") Integer age);
}
