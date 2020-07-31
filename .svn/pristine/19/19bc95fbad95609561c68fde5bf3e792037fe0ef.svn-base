package com.ntels.ccbs.batch.up.common.dao;

import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.JdbcDao;
import com.ntels.ccbs.batch.up.entity.NBlupb01m02;

@Repository
public class UpCommonDaoImpl extends JdbcDao implements UpCommonDao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/up/common/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "UpCommonMapper";
	}
	
	@Override
	public int updateUpymJobDtl(NBlupb01m02 nBlupb01m02) {
		return updateOne("updateUpymJobDtl", nBlupb01m02);
	}	
}
