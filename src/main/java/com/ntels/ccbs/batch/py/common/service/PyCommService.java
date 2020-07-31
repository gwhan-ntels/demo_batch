package com.ntels.ccbs.batch.py.common.service;

import com.ntels.ccbs.batch.py.common.entity.ExrateInfo;
import com.ntels.ccbs.batch.py.common.entity.PyCommEntity;

public interface PyCommService {
	
	boolean isTest();
	
	/**
	 * 환율 정보 조회
	 * @return
	 */
	ExrateInfo getExrateInfo();
	
	/**
	 * Job런처가 실행될 때 Arguments로 넘어온 데이터를 파싱하여
	 * PyCommEntity객체로 넘겨준다.
	 * @param arg
	 * @return
	 */
	PyCommEntity parseArgData(String arg);
	
	void insertBondRcpt(String orgId, String ifChk, String dpstSeqNo, String pymSeqNo, String regrId);
	
	/**
	 * 납부계정ID 조회
	 * @param pymAcntId
	 * @return
	 */
	int getPymAcntCnt(String pymAcntId);
	
	String getDpstSeqNo();
	
	String getEachDpstSeqNo();
	
	String getPymSeqNo();
	
	String getPrepayOccSeqNo();
	
	String getAmbgOccSeqNo();
	
	String getPrepayTransSeqNo();
	
	String getAmbgTransSeqNo();
	
}
