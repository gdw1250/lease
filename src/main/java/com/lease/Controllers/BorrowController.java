package com.lease.Controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.lease.Exception.PayException;
import com.lease.Model.Bill;
import com.lease.Model.Borrow;
import com.lease.Service.BorrowService;;

@Controller
public class BorrowController {

	UUID uuid = UUID.randomUUID();
	@Autowired
	private BorrowService borrowService;
	
	
	@ResponseBody
	@RequestMapping("/pay")//支付宝支付
	public String pay(Borrow borrow) throws AlipayApiException{
		
		return borrowService.pay(borrow);
	}
	
	@ResponseBody
	@RequestMapping("/notify")//支付宝异步通知
	public String notify(Borrow borrow) throws AlipayApiException{
		
		return borrowService.pay(borrow);
	}
	
	@ResponseBody
	@RequestMapping("/payByWallet")//通过钱包付款
	public String payByWallet(Borrow borrow) {
		try {
			borrowService.payByWallet(borrow);
		}catch (PayException e) {
			// TODO: handle exception
			return e.getMessage();
		}
			// TODO Auto-generated catch block
			
		
		return "支付成功";
	}
	
	/**
	 * 查询订单
	 * @param borrow
	 * @return
	 */
	@RequestMapping("/selectBorrow")
	public String selectBorrow(String username,Model model) {
		
		model.addAttribute("borrows",borrowService.selectByNotreturn(username));
		model.addAttribute("endBorrows",borrowService.selectByEnd(username));
		return "personal::historyborrow";
	}
	/**
	 * 退还物品
	 * @param borrow
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/backborrow")
	public String backborrow(Borrow borrow) {
		String stut;
		try {
			stut = borrowService.backborrow(borrow);
		
		}catch (Exception e) {
			return e.toString();
		}
		
		return stut;
	}
	@ResponseBody
	@RequestMapping("/charge")//充值
	public String charge(Bill bill){
		
		return borrowService.charge(bill);
	}
	
	@ResponseBody
	@RequestMapping("/chargenotify")//充值
	public String chargenotify(HttpServletRequest request){
		
		return borrowService.chargenotify(request);
	}

}
