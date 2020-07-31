package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.entity.NBlivs02m02;

public interface NBlivs02m02Dao {

	int deleteSaleSum(NBlivs02m02 nBlivs01m02);
	
	LazyLoader<NBlivs02m02> getSaleSum(NBlivs02m02 nBlivs01m02);
	
	int insertSaleSum(List<NBlivs02m02> nBlivs01m02List);

}
