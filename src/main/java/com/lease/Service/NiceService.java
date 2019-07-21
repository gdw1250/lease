package com.lease.Service;

import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lease.Mapper.NiceMapper;
import com.lease.Model.Nice;
@Service
public class NiceService {
	@Autowired
	NiceMapper niceMapper;
	UUID uuid = UUID.randomUUID();
	//插入点赞记录
	public int insertNice(Nice nice) {
		nice.setNiceid(uuid.randomUUID().toString());
		return niceMapper.insertNice(nice);
	};
	//取消
	public int deleteNice(String username,String commentid) {
		return niceMapper.deleteNice(username, commentid);
	};
	public int selectNiceStatus(String username,String commentid) {
		return niceMapper.selectNiceStatus(username, commentid);
	};
}

