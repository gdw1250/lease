package com.lease.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lease.Exception.PayException;
import com.lease.Mapper.BillMapper;
import com.lease.Mapper.BorrowMapper;
import com.lease.Mapper.GoodsMapper;
import com.lease.Mapper.UserMapper;
import com.lease.Model.Bill;
import com.lease.Model.Borrow;
import com.lease.config.AlipayConfig;

@Service
public class BorrowService {

	@Autowired
	private BorrowMapper borrowMapper;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private BillMapper billMapper;

	public String pay(Borrow borrow) throws AlipayApiException {//
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + borrow.getOut_trade_no() + "\"," + "\"total_amount\":\""
				+ borrow.getTotal_amount() + "\"," + "\"subject\":\"" + borrow.getSubject() + "\"," + "\"body\":\""
				+ borrow.getBody() + "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// alipayClient.pageExecute(alipayRequest);

		String result = alipayClient.pageExecute(alipayRequest).getBody();
		return result;
	}

	public String notify(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
				AlipayConfig.sign_type); // 调用SDK验证签名

		// ——请在这里编写您的程序（以下代码仅作参考）——

		/*
		 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
		 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		 * 4、验证app_id是否为该商户本身。
		 */
		if (signVerified) {// 验证成功
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// if(trade_status.equals("TRADE_FINISHED")){
			// //判断该笔订单是否在商户网站中已经做过处理
			// //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			// //如果有做过处理，不执行商户的业务程序
			//
			// //注意：
			// //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			// }else if (trade_status.equals("TRADE_SUCCESS")){
			// //判断该笔订单是否在商户网站中已经做过处理
			// //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			// //如果有做过处理，不执行商户的业务程序
			//
			// //注意：
			// //付款完成后，支付宝系统发送该交易状态通知
			// }
			//
			return "success";

		} else {// 验证失败
			return "fail";

			// 调试用，写文本函数记录程序运行情况是否正常
			// String sWord = AlipaySignature.getSignCheckContentV1(params);
			// AlipayConfig.logResult(sWord);
		}

	}

	@Transactional
	public String payByWallet(Borrow borrow) throws PayException {

//		// 查询账户余额
//		double remainder = userMapper.selectmoneybyname(borrow.getUsername());
//		// 如果账号余额不足提示
//
//		if (remainder < borrow.getTotal_amount()) {
//			return "账户余额不足";
//		} else {// 账户余额足够实现付款
//				// 减库存
			int subnumber = goodsMapper.subGoodsNumber(borrow.getGoodid());
			if (subnumber == 0) {
				throw new PayException("库存不足");
			} else if (subnumber < 0) {
				throw new PayException("交易异常");
			} else {
				int upuser=userMapper.payMoney(borrow.getDeposit(), borrow.getUsername());
				if(upuser<0) {
					throw new PayException("交易失败");
				}else if(upuser==0){
					throw new PayException("账户余额不足");
				}
				// 添加订单表
				int borrowstat = borrowMapper.addOrderfrom(borrow);
				if(borrowstat<=0) {
					throw new PayException("交易失败");
				}
				return "支付成功";

			}
		

	}

	// 根据用户名查询未归还的订单
	public List<Borrow> selectByNotreturn(String username) {
		return borrowMapper.selectByNotreturn(username);
	};

	// 根据用户名查询已完成的订单
	public List<Borrow> selectByEnd(String username) {
		return borrowMapper.selectByEnd(username);
	};

	// 根据id查询订单信息
	public Borrow borrowById(String out_trade_no) {
		return borrowMapper.borrowById(out_trade_no);
	};

	@Transactional
	public String backborrow(Borrow borrow)  throws RuntimeException {
			// 查询用户账户信息
			float money = userMapper.selectmoneybyname(borrow.getUsername());
			// 如果账号余额不足提示
			money = money + borrow.getDeposit() - borrow.getAmount();
			
			if (money < 0) {
				return "账户余额不足";
			} else {// 账户余额足够实现付款
				// 修改账单信息
				int borstatus = borrowMapper.updateBorrowByID(borrow);
				if(borstatus==0) {
					throw new RuntimeException("退还失败");
				}
				//加库存
				int addnumber = goodsMapper.addGoodsNumber(borrow.getGoodid());
				if(addnumber==0) {
					throw new RuntimeException("修改库存失败");
				}
				
				// 修改用户账户信息
				int ustatus=userMapper.updataUserMoney(money, borrow.getUsername());
				if(ustatus==0) {
					throw new RuntimeException("支付失败");
				}
				
				//修改物品所有者账户信息
				int business=userMapper.addMoneyByGoodid(borrow.getGoodid(), borrow.getAmount());
				if(business==0) {
					throw new RuntimeException("系统出错，请稍后再试");
				}
				return "支付成功";		
			}
	}
	/**
	 * 插入账单信息
	 * @param bill
	 * @return
	 */
	private int insertBill(Bill bill) {
		return billMapper.insertBill(bill);
	}
	/**
	 * 账户充值
	 * @param bill
	 * @return
	 */
	
	public String  charge(Bill bill) {
		// 获得初始化的AlipayClient
				AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
						AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
						AlipayConfig.sign_type);
				// 设置请求参数
				AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
				alipayRequest.setReturnUrl(AlipayConfig.return_url);
				alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
				alipayRequest.setBizContent("{\"out_trade_no\":\"" +bill.getBillid() + "\"," + "\"total_amount\":\""
						+ bill.getAmount() + "\"," + "\"subject\":\"" + bill.getSubject() + "\"," + "\"body\":\""
						+ bill.getBody() + "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

				// alipayClient.pageExecute(alipayRequest);

				String result;
				try {
					result = alipayClient.pageExecute(alipayRequest).getBody();
					//保存订单
					billMapper.insertBill(bill);
					//用户账户添加
					userMapper.recharge(bill.getAmount(), bill.getUsername());
				} catch (AlipayApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return e.toString();
				}catch(RuntimeException e) {
					e.printStackTrace();
					return e.toString();
				}
				return result;
	}
	
	
	public String chargenotify(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.toString();
			}
			params.put(name, valueStr);
		}

		boolean signVerified;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
					AlipayConfig.sign_type);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		} // 调用SDK验证签名

		// ——请在这里编写您的程序（以下代码仅作参考）——

		/*
		 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
		 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		 * 4、验证app_id是否为该商户本身。
		 */
		if (signVerified) {// 验证成功
			String total_amount;
			// 商户订单号
			try {
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
				// 支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
				total_amount = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
				
				//修改用户金额
				System.out.println("成功");

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.toString();
			}

			return "success"+total_amount;

		} else {// 验证失败
			return "fail";

			// 调试用，写文本函数记录程序运行情况是否正常
			// String sWord = AlipaySignature.getSignCheckContentV1(params);
			// AlipayConfig.logResult(sWord);
		}

	}
}
