package com.lease.Model;

public class Nice {
	private String niceid;
	private String username;
	private String commentid;
	public String getNiceid() {
		return niceid;
	}
	public void setNiceid(String niceid) {
		this.niceid = niceid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	@Override
	public String toString() {
		return "Nice [niceid=" + niceid + ", username=" + username + ", commentid=" + commentid + "]";
	}
	
}
