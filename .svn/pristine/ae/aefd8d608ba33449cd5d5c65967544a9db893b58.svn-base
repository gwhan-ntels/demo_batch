package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.entity.NBlivs01m02;

@Repository
public class NBlivs01m02DaoImpl extends LazyLoadingDao implements NBlivs01m02Dao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlivs01m02Mapper";
	}
	
	@Override
	public int deleteBillSum(NBlivs01m02 nBlivs01m02) {
		return deleteOne("deleteBillSum", nBlivs01m02);
	}
	
	@Override
	public LazyLoader<NBlivs01m02> getBillSum(NBlivs01m02 nBlivs01m02) {
		return getLazyLoader("getBillSum", NBlivs01m02.class, nBlivs01m02);
	}
	
	@Override
	public int insertBillSum(List<NBlivs01m02> nBlivs01m02List) {
		return insert("insertBillSum", NBlivs01m02.class, nBlivs01m02List);
	}

}
