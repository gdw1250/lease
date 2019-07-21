package com.lease.Model;

public class Comment {
	private String commentid;
	private String goodid;
	private String username;
	private String commenttime;
	private String comment;
	private int good;
	private String image;//用户头像
	private int nicenumber;
	private int status;
	private int replyCounted;
	public Comment() {
		
	}
	public Comment(String commentid, String goodid, String username, String commenttime, int good, String image) {
		super();
		this.commentid = commentid;
		this.goodid = goodid;
		this.username = username;
		this.commenttime = commenttime;
		this.good = good;
		this.image = image;
	}
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
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
	
	
	
	public String getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getNicenumber() {
		return nicenumber;
	}
	public void setNicenumber(int nicenumber) {
		this.nicenumber = nicenumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getReplyCounted() {
		return replyCounted;
	}
	public void setReplyCounted(int replyCounted) {
		this.replyCounted = replyCounted;
	}
	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", goodid=" + goodid + ", username=" + username + ", commenttime="
				+ commenttime + ", comment=" + comment + ", good=" + good + ", image=" + image + ", nicenumber="
				+ nicenumber + ", status=" + status + "]";
	}
	

	
}
