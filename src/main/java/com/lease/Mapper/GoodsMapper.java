package com.lease.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lease.Model.Goods;

public interface GoodsMapper {
	public List<Goods> selectAllGoods();//查询所有物品
	public int upgoods(Goods goods);//上架物品
	public List<Goods> getUserGoods(String username);//根据用户名获得物品信息
	public int deleteUserGoods(String goodid);//删除物品
	public int updateUserGoods(Goods goods);//修改物品
	public int subGoodsNumber(String goodid);//减库存
	public int addGoodsNumber(String goodid);//加库存
	public List<Goods> updataGoodsList (@Param("sign")String sign);//排序物品
	public List<Goods> selectGoods(@Param("selectStr")String selectStr);//搜索物品
	public Goods selectGoodsByGoodid(String goodid);//根据id查询物品
	public List<Goods> selectTenGoods();//查询最新前十条数据
	public List<Goods> goodsApply();//查询未验证的物品
	public List<Goods> goodsAlready();//验证历史记录
	public int agree(String goodid);//同意物品上传
	public int refuse(@Param("notify")String notify,@Param("goodid")String goodid);//拒绝物品上架申请
 }
