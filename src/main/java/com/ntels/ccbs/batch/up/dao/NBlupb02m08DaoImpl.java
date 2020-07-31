package com.ntels.ccbs.batch.up.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.entity.NBlupb02m08;

@Repository
public class NBlupb02m08DaoImpl extends LazyLoadingDao implements NBlupb02m08Dao {
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/up/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlupb02m08Mapper";
	}
	
	@Override
	public LazyLoader<AuthChgAppl> getAuthChgApplList(NBlupb02m08 nBlupb02m08) {
		return getLazyLoader("getAuthChgApplList", AuthChgAppl.class, nBlupb02m08);
	}

	@Override
	public int insertAuthChgAppl(AuthChgAppl authChgAppl) {
		return insertOne("insertAuthChgAppl", authChgAppl);
	}
	
	@Override
	public int insertAuthChgAppl(List<AuthChgAppl> authChgApplList) {
		return insert("insertAuthChgAppl", AuthChgAppl.class, authChgApplList);
	}

}