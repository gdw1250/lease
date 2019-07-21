package com.lease.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lease.Mapper.UserMapper;
import com.lease.Model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public List<User> selectUser() {
		return userMapper.selectAllUser();
		
	}
	public User userLogin(String username, String password) {
		
		return userMapper.userLogin(username,password);			
	}
	public int userregister(User user) {
		
		return userMapper.userregister(user);			
	}
	
	public int vaildatename(String username) {
		
		return userMapper.vaildatename(username);			
	}
	public int updataUser(User user) {
		return userMapper.updataUser(user);
	}
	public User userByID(String userid) {
		return userMapper.userByID(userid);
	}
	public int updataImg(String userid,String image) {
		return userMapper.updataImg(userid, image);
	};
	public User selectUserByBillid(String billid) {
		return userMapper.selectUserByBillid(billid);
	}
	//批量删除用户
	public int deleteUsers(String[] userids) {
		return userMapper.deleteUsers(userids);
	};
	//管理员修改用户信息
	public int updataEdituser(User user) {
		return userMapper.updataEdituser(user);
	};
	//管理员添加用户
	public int adminAddUser(User user) {
		return userMapper.adminAddUser(user);
	};
	public User adminLogin(String username, String password) {
		return userMapper.adminLogin(username, password);
	}
}
