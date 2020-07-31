package com.ntels.ccbs.batch.py.dao;

import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.Deposit;

@Repository
public class NBlpyc01m02DaoImpl extends LazyLoadingDao implements NBlpyc01m02Dao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/py/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlpyc01m02Mapper";
	}
	
	@Override
	public LazyLoader<Deposit> getDepositList(CBillComm bill) {
		return getLazyLoader("getDepositList", Deposit.class, bill);
	}
	
}
