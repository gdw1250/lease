package com.lease.Mapper;

import com.lease.Model.Bill;

public interface BillMapper {
	int insertBill(Bill bill);//插入账单信息
	Bill selectBill(String billid);//查询账单

}
