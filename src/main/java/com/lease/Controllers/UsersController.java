package com.lease.Controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lease.Model.MyConfig;
import com.lease.Model.User;
import com.lease.Service.GoodsService;
import com.lease.Service.UserService;

@Controller
public class UsersController {

	UUID uuid = UUID.randomUUID();
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private MyConfig myconfig;
	/**
	 * 登录验证
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpSession session,Model model) {
		if(session.getAttribute("user")==null)
			return "login";
		else {
			model.addAttribute("goods",goodsService.selectAllGoods());
			model.addAttribute("goodnew",goodsService.selectTenGoods());
			return "comment";
			
			}
	}
	
	@RequestMapping("/selec")
	public String selectUser(Model model){
		List<User> users=userService.selectUser();
		model.addAttribute("users", users);
		System.out.println(users.toString());
		return "index2";
	}
	
	/**
	 * 跳转个人信息页面
	 * @return
	 */
	@RequestMapping("/personal")
	public String personalpage(String userid,HttpSession session){
		User user = userService.userByID(userid);
		session.setAttribute("user", user);
		return "personal";
	}
	
	/**
	 * 支付后跳转页面
	 * @return
	 */
	@RequestMapping("/returnPersonal")
	public String returnPersonal(String out_trade_no,HttpSession session){
		User user = userService.selectUserByBillid(out_trade_no);
		session.setAttribute("user", user);
		
		return "personal";
	}

	/**
	 * 登录用户密码验证
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validatelogin")
	public String validatelogin(String username,String password) {
		return userService.userLogin(username,password)!=null?"{\"valid\":true}":"{\"valid\":false}";
	}
	

	/***
	 * 登录
	 * @param username
	 * @param password
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String username,String password,HttpSession session) {
		//创建session
		
		//判断是否登录成功
		User user =userService.userLogin(username,password);
		//将用户数据保存在session中
		if(user!=null) {
			session.setAttribute("user", user);
			
		}
		// 
		return "redirect:/index";
	}
	
	@RequestMapping("/cancel")
	public String cancel(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/index";
	}
	
	@ResponseBody
	@RequestMapping("/register")
	public String userregister(User user) {
		user.setUserid(uuid.randomUUID().toString());
		return userService.userregister(user)!=0?"注册成功":"注册失败";	
	}
	
	/**
	 * 管理登录用户密码验证
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validateAdminlogin")
	public String validateAdminlogin(String username,String password) {
		return userService.adminLogin(username,password)!=null?"{\"valid\":true}":"{\"valid\":false}";
	}
	/**
	 * 管理系统
	 * @param session
	 * @return
	 */
	@RequestMapping("supervise")
	public String supervise(HttpSession session) {
		
		if(session.getAttribute("adminUser")==null)
			return "adminLogin";
		else {
			
			return "supervise";
			
			}
	}
	/**
	 * 管理系统登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/adminlogin")
	public String adminlogin(String username,String password,HttpSession session) {
		//创建session
		
		//判断是否登录成功
		User user =userService.adminLogin(username, password);
		//将用户数据保存在session中
		if(user!=null) {
			session.setAttribute("adminUser", user);
			
		}
		// 
		return "redirect:/supervise";
	}
	/**
	 * 管理员注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/adminCancel")
	public String adminCancel(HttpSession session) {
		session.removeAttribute("adminUser");
		return "redirect:/supervise";
	}
	
	
	/**
	 * 名字重复验证
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validatename")
	public String validate(String username) {
		return userService.vaildatename(username)!=0?"{\"valid\":false}":"{\"valid\":true}";
	}
	
	
	@RequestMapping("/uploadpage")
	public String uploadpage(MultipartFile image) {
		
		return "fileupload";
	}
	/***
	 * 修改个人信息
	 * @param user
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/updatauser")
	public String updatauser(User user) {
		int  stat ;
		try {
			stat= userService.updataUser(user);
		} catch (Exception e) {
			return e.toString();
		}
		return stat==1?"报存成功":"保存失败";	
	}
	/**
	 * 管理修改用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updataEdituser")
	public String updataEdituser(User user) {
		int  stat ;
		try {
			stat= userService.updataEdituser(user);
		} catch (Exception e) {
			return e.toString();
		}
		return stat==1?"报存成功":"保存失败";	
	}
	
	/**
	 * 管理添加用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/adminAddUser")
	public String adminAddUser(User user) {
		user.setUserid(uuid.randomUUID().toString());
		int  stat ;
		try {
			stat= userService.adminAddUser(user);
		} catch (Exception e) {
			return e.toString();
		}
		return stat==1?"添加成功":"添加失败";	
	}
	
	
	/***
	 * 个人信息页面局部刷新
	 * @return
	 */
	@RequestMapping("/perrefurbish")
	public String personal(String elementid,String userid,HttpSession session){
		 
		session.setAttribute("user", userService.userByID(userid));
		return "personal::"+elementid;
	}
	
	/**
	 * 返回页面内容
	 * @param elementid
	 * @return
	 */
	@RequestMapping("/returnpage")
	public String returnpage(String elementid){
		return "personal::"+elementid;
	}
	
	/**
	 * 根据用户id 修改头像
	 * @param userid
	 * @return
	 */
	@RequestMapping("/updataHeadImg")
	public String updataHeadImg(String userid,MultipartFile croppedImage) {
		
		//判断目录是否为空
		String path =myconfig.imgAddress + "user"+userid+File.separator+"headimage"+File.separator;
        File imgdir = new File(path);
        //判断文件夹是否存在
        
        if(!imgdir.exists()) {
        	imgdir.mkdirs(); 
        }
        try {
        	 File saveimg = new File(path+"head"+userid+".jpg");
			 croppedImage.transferTo(saveimg);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
        userService.updataImg(userid, "user"+userid+File.separator+"headimage"+File.separator+"head"+userid+".jpg");
		return "personal";
	}
	
	@ResponseBody
	@RequestMapping("/fileupload")
	public String fileupload(MultipartFile image) throws IllegalStateException, IOException {
		String fileName = image.getOriginalFilename();
        String filePath = "E:\\SoftDowmloads\\ChromeDownloads\\ResRent\\src\\main\\resources\\static\\images\\";
        File dest = new File(filePath + fileName);
        image.transferTo(dest);
		return image.getName();
	}
	
	@ResponseBody
	@RequestMapping("/saveimg")
	public String saveimg(MultipartFile croppedImage) throws IllegalStateException, IOException {
		String fileName = croppedImage.getOriginalFilename();
        File dest = new File("C:\\Users\\Administrator\\Desktop\\测试\\img.jpg");
        
        croppedImage.transferTo(dest);
		return null;
	}
	@ResponseBody
	@RequestMapping("/getUserList")
	public List<User>  getUserList() {
		return userService.selectUser();
	}
	
	/**
	 *批量删除用户
	 */
	@ResponseBody
	@RequestMapping("/deleteUsers")
	public String  deleteUsers(@RequestParam("userid[]")String[] userids) {
		
		try {
			userService.deleteUsers(userids);
		}catch (Exception e) {
			return e.toString();
		}
		return "删除成功";
	}
	
	
	
	
	
}