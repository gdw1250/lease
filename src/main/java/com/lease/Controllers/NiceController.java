package com.lease.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lease.Mapper.NiceMapper;
import com.lease.Model.Comment;
import com.lease.Model.Nice;
import com.lease.Service.CommentService;
import com.lease.Service.NiceService;

@Controller
public class NiceController {

	@Autowired
	private NiceService niceService;
	@Autowired
	CommentService commentService;
	
	@RequestMapping("/insertgood")
	public String insertgood(Nice nice,Model model,String goodid){
		
		int stat = niceService.selectNiceStatus(nice.getUsername(), nice.getCommentid());
		//是否已点赞
		if(stat==1) {
			niceService.deleteNice(nice.getUsername(), nice.getCommentid());
		}else {
			niceService.insertNice(nice);
		}
		List<Comment> comments = commentService.commentByGood(nice.getUsername(),goodid);
		model.addAttribute("comments", comments);
		return "comment::commentlist";
	}
	@RequestMapping("/deleteNice")
	public void deleteNice(String niceid){
//		niceService.deleteNice(niceid);
	}
	
	
}
