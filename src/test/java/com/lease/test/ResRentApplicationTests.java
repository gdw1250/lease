package com.lease.test;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;

import com.lease.LeaseApplication;
import com.lease.Mapper.GoodsMapper;
import com.lease.Mapper.UserMapper;
import com.lease.Model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=LeaseApplication.class)
public class ResRentApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Transactional
	@Test
	public void contextLoads() {
		User user = new User();
		user.setUserid("46");
		user.setUsername("加上事物");
		int seccuss  = userMapper.userregister(user);
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("故意抛出异常");
		}
		
	}
	@Test
	public void getpath() throws FileNotFoundException {
		System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
	}
	
	@Test
	public void subGoodsNumber() {
		System.out.println(goodsMapper.subGoodsNumber("3d5e4c9b-1e06-46c3-abaf-01f92fe911e2"));
	}
	@Test
	public void getPath() {

		String userHome = System.getProperties().getProperty("user.dir");
		System.out.println(userHome);
		System.out.println(File.separator);
	}
	
	@Test
	public void printString() {
		System.out.println("test");
	}

}
