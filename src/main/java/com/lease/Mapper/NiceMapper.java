package com.lease.Mapper;

import org.apache.ibatis.annotations.Param;

import com.lease.Model.Nice;

public interface NiceMapper {
	public int insertNice(Nice nice);//插入点赞记录
	public int deleteNice(@Param("username")String username,@Param("commentid")String commentid);//取消
	public int selectNiceStatus(@Param("username")String username,@Param("commentid")String commentid);
}
