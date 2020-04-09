package com.w3cjava.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.w3cjava.common.annotation.DataBaseSource;
import com.w3cjava.modules.user.entity.User;

@Mapper
public interface UserDao{
	//使用xml配置形式查询
	@DataBaseSource("master")
	public int insertMaster(User entity);
	@DataBaseSource("slave")
	public int insertSlave(User entity);
	
	
	
	@DataBaseSource("slave")
    public List<User> getSlaveAllUser();
	@DataBaseSource("master")
    public List<User> getMasterAllUser();
}
