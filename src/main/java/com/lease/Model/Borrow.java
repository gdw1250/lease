package com.lease.Model;

import java.sql.Timestamp;

public class Borrow {
	private String out_trade_no;//商户订单号
	private String username;//用户名称
	private String goodid;//物品id
	private String borrowtime;//借出时间
	private Timestamp repey;//还回时间
	private float depositMoney;//计费方式
	private String product_code;//销售产品码
	private float total_amount;//订单总金额、押金
	private String subject;//订单标题
	private String body;//订单描述
	private float amount;//租借费用
	private float deposit;//押金
	private float status;//订单状态
	private String image;//图片
	public Borrow() {
		
	}
	
	public Borrow(String out_trade_no, String username, String borrowtime, Timestamp repey, float depositMoney,
			String product_code, float total_amount, String subject, String body) {
		super();
		this.out_trade_no = out_trade_no;
		this.username = username;
		this.borrowtime = borrowtime;
		this.repey = repey;
		this.depositMoney = depositMoney;
		this.product_code = product_code;
		this.total_amount = total_amount;
		this.subject = subject;
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getGoodid() {
		return goodid;
	}

	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}

	public String getBorrowtime() {
		return borrowtime;
	}
	public void setBorrowtime(String borrowtime) {
		this.borrowtime = borrowtime;
	}
	public Timestamp getRepey() {
		return repey;
	}
	public void setRepey(Timestamp repey) {
		this.repey = repey;
	}
	public float getDepositMoney() {
		return depositMoney;
	}
	public void setDepositMoney(float depositMoney) {
		this.depositMoney = depositMoney;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public float getStatus() {
		return status;
	}

	public void setStatus(float status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Borrow [out_trade_no=" + out_trade_no + ", username=" + username + ", goodid=" + goodid
				+ ", borrowtime=" + borrowtime + ", repey=" + repey + ", depositMoney=" + depositMoney
				+ ", product_code=" + product_code + ", total_amount=" + total_amount + ", subject=" + subject
				+ ", body=" + body + "]";
	}
	
	

}
