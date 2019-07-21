package com.lease.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lease.Model.Comment;

public interface CommentMapper {

	public List<Comment> commentByGood(@Param("username")String username, @Param("goodid")String goodid);//根据物品查看评论
	public int upcomment(Comment comment);//添加评论
	public int addcommentgood(String commentid);//点赞功能
	public List<Comment> selectAllComment();//查询所有评论
	public int deleteCommentMore(@Param("commentids")String[] commentids);//批量删除评论
}
