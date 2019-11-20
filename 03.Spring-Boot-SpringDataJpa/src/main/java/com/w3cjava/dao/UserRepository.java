package com.w3cjava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.w3cjava.entity.User;
/**
 * 
 * @class  UserRepository
 * @version 1.0.0
 * @author cos
 * @desc   创建对应的Repository接口实现对该实体的数据访问
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
