package com.lease.Model;

import java.sql.Date;

public class Goods {
private String goodid;
private String username;
private String goodname;
private int goodsnumber;
private String detail;
private Date uptime;
private float price;
private String image;
private int commentnumber;//评论数量
private int status;//物品状态
private float deposit;//押金
private String notify;//通知

public Goods() {
	
}
public Goods(String goodid, String userid, String goodname, int goodsnumber, String detail, Date uptime, float price,
		String image) {
	super();
	this.goodid = goodid;
	this.username = username;
	this.goodname = goodname;
	this.goodsnumber = goodsnumber;
	this.detail = detail;
	this.uptime = uptime;
	this.price = price;
	this.image = image;
}
public String getGoodid() {
	return goodid;
}
public void setGoodid(String goodid) {
	this.goodid = goodid;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getGoodname() {
	return goodname;
}
public void setGoodname(String goodname) {
	this.goodname = goodname;
}
public int getGoodsnumber() {
	return goodsnumber;
}
public void setGoodsnumber(int goodsnumber) {
	this.goodsnumber = goodsnumber;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public Date getUptime() {
	return uptime;
}
public void setUptime(Date uptime) {
	this.uptime = uptime;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public String getImage() {
	return image;
}

public int getCommentnumber() {
	return commentnumber;
}
public void setCommentnumber(int commentnumber) {
	this.commentnumber = commentnumber;
}
public void setImage(String image) {
	this.image = image;
}

public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public float getDeposit() {
	return deposit;
}
public void setDeposit(float deposit) {
	this.deposit = deposit;
}
public String getNotify() {
	return notify;
}
public void setNotify(String notify) {
	this.notify = notify;
}
@Override
public String toString() {
	return "Goods [goodid=" + goodid + ", username=" + username + ", goodname=" + goodname + ", goodsnumber="
			+ goodsnumber + ", detail=" + detail + ", uptime=" + uptime + ", price=" + price + ", image=" + image + "]";
}

}
