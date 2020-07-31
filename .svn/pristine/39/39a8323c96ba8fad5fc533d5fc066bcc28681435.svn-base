package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.dao.NBlivs02m02Dao;
import com.ntels.ccbs.batch.iv.entity.NBlivs02m02;

@Service
public class NBlivs02m02ServiceImpl implements NBlivs02m02Service {

	@Autowired
	private NBlivs02m02Dao nBlivs02m02Dao;
	
	@Override
	public int deleteSaleSum(NBlivs02m02 nBlivs01m02) {
		return nBlivs02m02Dao.deleteSaleSum(nBlivs01m02);
	}

	@Override
	public LazyLoader<NBlivs02m02> getSaleSum(NBlivs02m02 nBlivs01m02) {
		return nBlivs02m02Dao.getSaleSum(nBlivs01m02);
	}

	@Override
	public int insertSaleSum(List<NBlivs02m02> nBlivs01m02List) {
		return nBlivs02m02Dao.insertSaleSum(nBlivs01m02List);
	}

}
