package com.w3cjava.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.w3cjava.SpringBootMybatisApplication;
import com.w3cjava.dao.UserDao;
import com.w3cjava.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootMybatisApplication.class})
public class UserServiceTest {
	@Autowired
	private UserDao userDao;
	@Test
	@Transactional
	@Rollback
	public void insert() throws Exception {
		userDao.insert("AAA", 20);
	}
	
	@Test
	@Transactional
	@Rollback
	public void findByName() throws Exception {
		userDao.insert("AAA", 20);
		User u = userDao.findByName("AAA");
		Assert.assertEquals(20, u.getAge().intValue());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testMap() throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("name", "CCC");
		map.put("age", 40);
		userDao.insertByMap(map);
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testuserDao() throws Exception {
		// insert一条数据，并select出来验证
		userDao.insert("AAA", 20);
		User u = userDao.findByName("AAA");
		Assert.assertEquals(20, u.getAge().intValue());
		// update一条数据，并select出来验证
		u.setAge(30);
		userDao.update(u);
		u = userDao.findByName("AAA");
		Assert.assertEquals(30, u.getAge().intValue());
		// 删除这条数据，并select验证
		userDao.delete(u.getId());
		u = userDao.findByName("AAA");
		Assert.assertEquals(null, u);
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void testSelectMapper() throws Exception {
		List<User> userList = userDao.findAll();
		for(User user : userList) {
			Assert.assertEquals(null, user.getId());
			Assert.assertNotEquals(null, user.getName());
		}
	}
}
