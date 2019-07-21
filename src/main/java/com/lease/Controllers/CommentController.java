package com.lease.Controllers;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lease.Model.Comment;
import com.lease.Model.Reply;
import com.lease.Service.CommentService;

@Controller
public class CommentController {

	UUID uuid = UUID.randomUUID();

	@Autowired
	private CommentService commentService;

	/**
	 * 查看评论
	 * @param goodid 物品id
	 * @param model
	 * @return
	 */
	@RequestMapping("/getComments")
	public String getComments(String username,String goodid, Model model) {
		List<Comment> comments = commentService.commentByGood(username,goodid);
		model.addAttribute("comments", comments);
		return "comment::commentlist";
	}
	/**
	 * 提交评论，更新列表
	 * @param comment
	 * @param model
	 * @return
	 */
	@RequestMapping("/upComment")
	public String upComment(Comment comment,Model model) {
		commentService.upcomment(comment);
		List<Comment> comments = commentService.commentByGood(comment.getUsername(),comment.getGoodid());
		model.addAttribute("comments", comments);
		return "comment::commentlist";
	}
	
	
	/**
	 * 查看回复
	 * @param commentid
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/selectReply")
	public String selectReply(String commentid,Model model) {
		
		List<Reply> replys = commentService.selectReplyByComment(commentid);
		model.addAttribute("replys", replys);
		return "reply::replylist";
	}
	/**
	 * 添加回复
	 * @param reply
	 * @return
	 */
	@RequestMapping("/insertReply")
	public String insertReply(Reply reply) {
		commentService.insertReply(reply);
		return "forward:/selectReply";
	}
	/**
	 * 查询所有评论
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectAllComment")
	public List<Comment> selectAllComment(){
		
		
		return commentService.selectAllComment();
	}
	
	/**
	 * 批量删除评论
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteCommentMore")
	public String deleteCommentMore(@RequestParam("commentids[]")String[] commentids) {
		
		try {
			commentService.deleteCommentMore(commentids);
		}catch (Exception e) {
			e.toString();
		}
		
		return "删除成功";
	}
	

}
