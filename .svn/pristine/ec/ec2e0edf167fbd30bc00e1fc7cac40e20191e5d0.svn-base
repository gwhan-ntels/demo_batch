package com.ntels.ccbs.batch.up.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.common.service.UpBaseService;
import com.ntels.ccbs.batch.up.dao.NBlupb02m08Dao;
import com.ntels.ccbs.batch.up.entity.NBlupb02m08;

@Service
public class NBlupb02m08ServiceImpl extends UpBaseService implements NBlupb02m08Service {

	@Autowired
	private NBlupb02m08Dao nBlupb02m08Dao;
	
	@Override
	public LazyLoader<AuthChgAppl> getAuthChgApplList(NBlupb02m08 nBlupb02m08) {
		return nBlupb02m08Dao.getAuthChgApplList(nBlupb02m08);
	}

	@Override
	public int insertAuthChgAppl(AuthChgAppl authChgAppl) {
		return nBlupb02m08Dao.insertAuthChgAppl(authChgAppl);
	}

	@Override
	public int insertAuthChgAppl(List<AuthChgAppl> authChgApplList) {
		return nBlupb02m08Dao.insertAuthChgAppl(authChgApplList);
	}

}