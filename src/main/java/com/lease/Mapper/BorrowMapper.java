package com.lease.Mapper;

import java.util.List;

import com.lease.Model.Borrow;

public interface BorrowMapper {

	public int addOrderfrom(Borrow borrow);//添加订单
	public List<Borrow> selectByNotreturn(String username);//根据用户名查询未归还的订单
	public List<Borrow> selectByEnd(String username);//根据用户名查询已完成的订单
	public Borrow borrowById(String out_trade_no);//根据id查询订单信息
	public int updateBorrowByID(Borrow borrow);//根据id修改订单信息
}
