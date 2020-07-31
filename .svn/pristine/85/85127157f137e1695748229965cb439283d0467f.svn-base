package com.ntels.ccbs.batch.up.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.entity.NBlupb02m08;

public interface NBlupb02m08Service {
	
	/**
	 * 직권 해지 대상 내역 조회
	 * @param nBlupb02m08
	 * @return
	 */
	LazyLoader<AuthChgAppl> getAuthChgApplList(NBlupb02m08 nBlupb02m08);
	
	/**
	 * 직권 해지 대상 내역 저장
	 * @param authChgAppl
	 * @return
	 */
	int insertAuthChgAppl(AuthChgAppl authChgAppl);
	
	/**
	 * 직권 해지 대상 내역 저장
	 * @param authChgAppl
	 * @return
	 */
	int insertAuthChgAppl(List<AuthChgAppl> authChgApplList);
	
}
