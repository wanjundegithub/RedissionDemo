package com.company.dao;

import com.company.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> findAll();

    User findById(@Param("id") Integer id);

    List<User> findByName(@Param("name") String name);

    Integer deleteById(@Param("id") Integer id);

    Integer updateUser(@Param("user") User user);

    Integer insertUser(@Param("user") User user);
}
