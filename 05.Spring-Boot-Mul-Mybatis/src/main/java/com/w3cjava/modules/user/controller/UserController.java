package com.w3cjava.modules.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.w3cjava.common.utils.IdGen;
import com.w3cjava.modules.user.entity.User;
import com.w3cjava.modules.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService userService;
	//不使用事务注解@Transactional
    @RequestMapping(value = "/getDb1AllUser")
    public String getDbAllUser() {
        List<User> list1 = userService.getAllUserMaster();
        for (int i = 0; i < list1.size(); i++) {
        	System.out.println(list1.get(i).getId()+"-"+list1.get(i).getName()+"-"+list1.get(i).getAge());
		}
        List<User> list2 = userService.getAllUserSlave();
        for (int i = 0; i < list2.size(); i++) {
        	System.out.println(list2.get(i).getId()+"-"+list2.get(i).getName()+"-"+list2.get(i).getAge());
		}       
        return "master:"+list1+"</br>slave:"+list2;
    }

    
    
    //使用事务注解@Transactional
    @RequestMapping(value = "/getDbAllUserTest")
    public String getDbAllUserTest() {
        String list = userService.getAllUserTest();
        return list;
    }
    //主库master user信息
    @RequestMapping(value = "/getAllUserMaster")
    public String getAllUserMaster() {
        List<User> list = userService.getAllUserMaster();
        return "master:"+list;
    } 
    
    //从库slave user信息
    @RequestMapping(value = "/getAllUserSlave")
    public String getAllUserSlave() {
        List<User> list = userService.getAllUserSlave();
        return "slave:"+list;
    }
 
    @SuppressWarnings("unused")
	@RequestMapping(value = "/saveMaster")
    public String saveMaster() {
        User user = new User();
        user.setId(IdGen.uuid());
        user.setName("MasterTom");
        user.setAge(20);
        Integer rows = userService.saveMaster(user);//返回的是结果行数
        return "{id:"+user.getId()+"}";
    }
    
    
    
    @SuppressWarnings("unused")
	@RequestMapping(value = "/saveSlave")
    public String saveSlave() {
        User user = new User();
        user.setId(IdGen.uuid());
        user.setName("SlaveTom");
        user.setAge(20);
        Integer rows = userService.saveSlave(user);//返回的是结果行数
        return "{id:"+user.getId()+"}";
    }
}
