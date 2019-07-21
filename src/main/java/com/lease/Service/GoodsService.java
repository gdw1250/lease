package com.lease.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lease.Mapper.GoodsMapper;
import com.lease.Model.Goods;
@Service
public class GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	public List<Goods> selectAllGoods(){
		return goodsMapper.selectAllGoods();
	}
	public int upgoods(Goods goods) {
		return goodsMapper.upgoods(goods);
	}
	
	public List<Goods> getUserGoods(String username){
		return goodsMapper.getUserGoods(username);
	}
	//删除物品
	public int deleteUserGoods(String goodid) {
		return goodsMapper.deleteUserGoods(goodid);
	}
	//修改物品
	public int updateUserGoods(Goods goods) {
		return goodsMapper.updateUserGoods(goods);
	}
	//物品排序
	public List<Goods> updataGoodsList(String sign){
		return goodsMapper.updataGoodsList(sign);
	}
	//搜索物品
	public  List<Goods> selectGoods(String selectStr){
		return goodsMapper.selectGoods(selectStr);
	}
	public Goods selectGoodsByGoodid(String goodid) {
		return goodsMapper.selectGoodsByGoodid(goodid);
	};
	
	public  List<Goods> selectTenGoods(){
		return goodsMapper.selectTenGoods();
	}
	//查询未验证的物品
	public List<Goods> goodsApply(){
		return goodsMapper.goodsApply();
	};
	//同意物品上传
	public int agree(String goodid) {
		return goodsMapper.agree(goodid);
	};
	//验证历史记录
	public List<Goods> goodsAlready(){
		return goodsMapper.goodsAlready();
	};
	public int refuse(String notify,String goodid) {
		return goodsMapper.refuse(notify, goodid);
	};
	
}
