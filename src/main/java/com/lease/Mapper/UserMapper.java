package com.lease.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lease.Model.User;

public interface UserMapper {
	public List<User> selectAllUser();//查询所有用户
	public User userLogin (@Param("username")String username,@Param("password")String password);		//用户登录
	public int userregister(User user);		//用户注册
	public int vaildatename(String username) ;//名字验证
	public float selectmoneybyname(String username);//查询账户信息
	public int updataUserMoney(@Param("money")double money,@Param("username")String username);//更改账户信息
	public int updataUser(User user);//修改个人信息
	public User userByID(String userid);
	public int payMoney(@Param("price")float price,@Param("username")String username);//更改账户信息
	public int updataImg(@Param("userid")String userid,@Param("image")String image);//根据id修改图片
	public int recharge(@Param("money")float money,@Param("username")String username);//账户充值
	public User selectUserByBillid(String billid);//根据账单号查询用户信息
	public int deleteUsers(@Param("userids")String[] userids);//批量删除用户
	public int updataEdituser(User user);//管理员修改用户信息
	public int adminAddUser(User user);//管理员添加用户
	public User adminLogin(@Param("username")String username,@Param("password")String password);//管理员登录
	public int addMoneyByGoodid(@Param("goodid")String goodid,@Param("money")float money);//商品出租收益
	
}
