package com.w3cjava.modules.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w3cjava.modules.user.dao.UserDao;
import com.w3cjava.modules.user.entity.User;
@Service
public class UserService{
	@Autowired
    private UserDao userDao;
    //使用数据源master查询
	//@Transactional(readOnly=true)
    public List<User> getAllUserMaster(){
        return userDao.getMasterAllUser();
    }
    //使用数据源slave查询
	//@Transactional(readOnly=true)
    public List<User> getAllUserSlave(){
        return userDao.getSlaveAllUser();
    }
 
    
    
	@Transactional(readOnly=true)
    public String getAllUserTest(){
		List<User> list1 = userDao.getMasterAllUser();
		List<User> list2 = userDao.getSlaveAllUser();
        return "master:"+list1+"</br>slave:"+list2;
    }	
	
    //使用数据源master插入数据
    //@Transactional(readOnly=false)
    public int saveMaster(User user){
    	int m = userDao.insertMaster(user);
        return m;
    }
    
    //使用数据源slave插入数据
    //@Transactional(readOnly=false)
    public int saveSlave(User user){
    	int m = userDao.insertSlave(user);
        return m;
    }
    
    
    
}
