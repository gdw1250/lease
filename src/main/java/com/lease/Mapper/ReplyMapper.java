package com.lease.Mapper;

import java.util.List;

import com.lease.Model.Reply;

public interface ReplyMapper {
	public int insertReply(Reply reply);//插入回复
	public List<Reply> selectReplyByComment(String commentid);
//	public int deleteNice(@Param("username")String username,@Param("commentid")String commentid);//取消
//	public int selectNiceStatus(@Param("username")String username,@Param("commentid")String commentid);
}
