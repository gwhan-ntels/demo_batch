package com.ntels.ccbs.batch.py.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.dao.NBlpyc01m02Dao;
import com.ntels.ccbs.batch.py.entity.Deposit;

@Service
public class NBlpyc01m02ServiceImpl implements NBlpyc01m02Service {

	@Autowired
	private NBlpyc01m02Dao nBlpyc01m02Dao;
	
	@Override
	public LazyLoader<Deposit> getDepositList(CBillComm bill) {
		return nBlpyc01m02Dao.getDepositList(bill);
	}
	
}
