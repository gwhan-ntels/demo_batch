package com.ntels.ccbs.batch.py.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.py.dao.mapper.NBlpyb09m02Mapper;

@Repository
public class NBlpyb09m02DaoImpl implements NBlpyb09m02Dao {

	@Autowired
	private NBlpyb09m02Mapper nBlpyb09m02Mapper;
	
	@Override
	public Double getUnpayAmtFromBillMast(String billSeqNo) {
		return nBlpyb09m02Mapper.getUnpayAmtFromBillMast(billSeqNo);
	}
	
}
