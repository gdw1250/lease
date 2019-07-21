package com.lease.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lease.Model.MyConfig;
import com.lease.Model.User;
import com.lease.Service.UserService;
import com.lease.config.AlipayConfig;

@Controller
public class TestController {

	@Autowired
	private UserService userService;
	@Autowired
	private MyConfig myconfig;
	@ResponseBody
	@RequestMapping("/hello")
	public String index() {
		return System.getProperty("user.dir");
	}
	@RequestMapping("/selectuser")
	public String selectUser(HttpServletRequest request){
		List<User> users=userService.selectUser();
		request.getSession().setAttribute("users", users);
//		model.addAttribute("users", users);
		System.out.println(users.toString());
		return "index2";
	}
	@RequestMapping("/formtest")
	public String formtest(){
		
		return "formtest";
	}
	@RequestMapping("/ajax")
	public String ajaxtest(){
		
		return "ajax";
	}
	
	@RequestMapping("/testnotify")
	public void testnotify(HttpServletRequest request) {
		System.out.println("成功");
	}
	
	@ResponseBody
	@RequestMapping("/getConfig")
	public String  getConfig() {
		
		return myconfig.imgAddress;
	}
}
