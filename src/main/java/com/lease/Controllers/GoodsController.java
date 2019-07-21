package com.lease.Controllers;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lease.Model.Goods;
import com.lease.Model.MyConfig;
import com.lease.Service.GoodsService;

@Controller
public class GoodsController {

	UUID uuid = UUID.randomUUID();

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private MyConfig myconfig;

	/**
	 * 物品上架
	 * @param goods
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upgoods")
	public String upgoods(Goods goods,MultipartFile croppedImage,String userid) {
	
		goods.setGoodid(UUID.randomUUID().toString());
		//获取文件目录位置
		
		//判断目录是否为空
		String path = myconfig.imgAddress+"user"+userid+File.separator+"goodsimage"+File.separator;
        File imgdir = new File(path);
        //判断文件夹是否存在
        
        if(!imgdir.exists()) {
        	imgdir.mkdirs(); 
        }
        try {
        	 File saveimg = new File(path+goods.getGoodid()+".jpg");
			 croppedImage.transferTo(saveimg);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
        goods.setImage("user"+userid+File.separator+"goodsimage"+File.separator+goods.getGoodid()+".jpg");
		
		goodsService.upgoods(goods);
		return "添加成功";
	}
	/**
	 * 修改物品图片
	 * @param croppedImage
	 * @param goodid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updataGoodsImage")
	public String updataGoodsImage(MultipartFile croppedImage,String goodid) {
		Goods goods = goodsService.selectGoodsByGoodid(goodid);
		
		try {
			File updataimg = new File(myconfig.imgAddress+goods.getImage());
			croppedImage.transferTo(updataimg);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
		return "forward:/getUserGoods";
	}
	
	/**
	 * 获取用户上传物品
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping("/getUserGoods")
	public String getUserGoods(String username,Model model) {
	
	model.addAttribute("usergoods",goodsService.getUserGoods(username));
		return "personal::changediv";
	}
	
	
	
	
	/**
	 * 用户删除物品
	 * @param goodid
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteUserGoods")
	public String deleteUserGoods(String goodid) {
	
		goodsService.deleteUserGoods(goodid);
		return "forward:/getUserGoods";
	}
	
	@RequestMapping("/updataUserGoods")
	public String updataUserGoods(Goods goods) {
	
		goodsService.updateUserGoods(goods);
		return "forward:/getUserGoods";
	}
	/**
	 * 排序物品列表
	 * @param sign
	 * @param model
	 * @return
	 */
	@RequestMapping("/updataGoodsList")
	public String updataGoodsList(String sign,Model model) {
	
		model.addAttribute("goods", goodsService.updataGoodsList(sign));

		return "comment::updateGoodsList";
		
	}
	
	/**
	 * 搜索物品列表
	 * @param sign
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectGoodsList")
	public String selectGoodsList(String selectStr,Model model) {
	
		model.addAttribute("goods", goodsService.selectGoods(selectStr));

		return "comment::updateGoodsList";
		
	}
	
	
	/**
	 *申请列表信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/goodsApply")
	public String goodsApply(Model model) {
	
		model.addAttribute("goods",goodsService.goodsApply());
		return "supervise::goodslist";
		
	}
	
	
	
	@RequestMapping("/goodsAlreadyList")
	public String goodsAlreadyList(Model model) {
	
		model.addAttribute("goods",goodsService.goodsAlready());
		return "supervise::goodsAlreadyList";
		
	}
	/**
	 * 审核通过
	 * @param goodid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/agree")
	public String agree(String goodid) {
	
		if(goodsService.agree(goodid)==1) {
			return "审核成功";
		}else{
			return "审核失败";
		}
	}
	/**
	 * 审核不通过
	 * @param goodid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/refuse")
	public String refuse(String goodid,String notify) {
	
		if(goodsService.refuse(notify, goodid)==1) {
			return "审核成功";
		}else{
			return "审核失败";
		}
	}
	

}
