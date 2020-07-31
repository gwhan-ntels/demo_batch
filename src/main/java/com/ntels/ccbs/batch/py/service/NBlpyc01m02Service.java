package com.ntels.ccbs.batch.py.service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.Deposit;

public interface NBlpyc01m02Service {

	LazyLoader<Deposit> getDepositList(CBillComm bill);
	
}
