package com.w3cjava.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.w3cjava.entity.User;

@Mapper
public interface UserDao{
	/**
	 * 
	 * @author cos
	 * @desc 使用@Param传参，@Param中定义的name对应了SQL中的#{name}，age对应了SQL中的#{age}
	 * @param name
	 * @return
	 */
    User findByName(@Param("name") String name);

    int insert(@Param("name") String name, @Param("age") Integer age);

    /**
     * 
     * @author cos
     * @desc 通过Map<String, Object>对象来作为传递参数的容器
     * @param map
     * @return
     */
    int insertByMap(Map<String, Object> map);
    
    /**
     * 
     * @author cos
     * @desc 使用对象
     * @param user
     * @return
     */
    
    int insert(User user);
    
    void update(User user);

    void delete(Long id);
    /**
     * 
     * @author cos
     * @desc 返回结果的绑定
     * @return
     */
    List<User> findAll();
}
