package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.dao.NBlivs01m02Dao;
import com.ntels.ccbs.batch.iv.entity.NBlivs01m02;

@Service
public class NBlivs01m02ServiceImpl implements NBlivs01m02Service {

	@Autowired
	private NBlivs01m02Dao nBlivs01m02Dao;
	
	@Override
	public int deleteBillSum(NBlivs01m02 nBlivs01m02) {
		return nBlivs01m02Dao.deleteBillSum(nBlivs01m02);
	}

	@Override
	public LazyLoader<NBlivs01m02> getBillSum(NBlivs01m02 nBlivs01m02) {
		return nBlivs01m02Dao.getBillSum(nBlivs01m02);
	}

	@Override
	public int insertBillSum(List<NBlivs01m02> nBlivs01m02List) {
		return nBlivs01m02Dao.insertBillSum(nBlivs01m02List);
	}

}
