package com.lease.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lease.Mapper.CommentMapper;
import com.lease.Mapper.ReplyMapper;
import com.lease.Model.Comment;
import com.lease.Model.Reply;

@Service
public class CommentService {
	UUID uuid = UUID.randomUUID();
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ReplyMapper replyMapper;
	
	public List<Comment> commentByGood(String username,String goodid) {
		return commentMapper.commentByGood(username,goodid);
		
	}
	public int upcomment(Comment comment) {
		comment.setCommentid(uuid.randomUUID().toString());
		comment.setGood(0);
		return commentMapper.upcomment(comment);
	}
	
	/**
	 * 添加回复
	 * @param reply
	 * @return
	 */
	public int insertReply(Reply reply) {
		reply.setReplyId(uuid.randomUUID().toString());
		return replyMapper.insertReply(reply);
	}
	/**
	 * 根据评论查看回复
	 * @param commentid
	 * @return
	 */
	public List<Reply> selectReplyByComment(String commentid){
		return replyMapper.selectReplyByComment(commentid);
	}
	
	//查询所有评论
	public List<Comment> selectAllComment(){
		return commentMapper.selectAllComment();
	};
	public int deleteCommentMore(String[] commentids) {
		return commentMapper.deleteCommentMore(commentids);
	};
}
