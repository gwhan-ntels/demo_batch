package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.entity.NBlivs02m02;

@Repository
public class NBlivs02m02DaoImpl extends LazyLoadingDao implements NBlivs02m02Dao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlivs02m02Mapper";
	}
	
	@Override
	public int deleteSaleSum(NBlivs02m02 nBlivs01m02) {
		return deleteOne("deleteSaleSum", nBlivs01m02);
	}
	
	@Override
	public LazyLoader<NBlivs02m02> getSaleSum(NBlivs02m02 nBlivs01m02) {
		return getLazyLoader("getSaleSum", NBlivs02m02.class, nBlivs01m02);
	}
	
	@Override
	public int insertSaleSum(List<NBlivs02m02> nBlivs01m02List) {
		return insert("insertSaleSum", NBlivs02m02.class, nBlivs01m02List);
	}

}
