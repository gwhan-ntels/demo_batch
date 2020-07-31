package com.ntels.ccbs.batch.up.service;

import java.sql.Timestamp;
import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;
import com.ntels.ccbs.batch.up.common.entity.UpymBigBsAmt;
import com.ntels.ccbs.batch.up.entity.CtrtDetail;
import com.ntels.ccbs.batch.up.entity.DebtTgtPsn;
import com.ntels.ccbs.batch.up.entity.NBlupb01m02;
import com.ntels.ccbs.batch.up.entity.UpymCtrtMngTp;
import com.ntels.ccbs.batch.up.entity.UpymDetail;
import com.ntels.ccbs.batch.up.entity.UpymEntrustMast;

public interface NBlupb01m02Service {

	/**
	 * <pre>
	 * 청구마감정보 확인
	 * 청구마감정보 수가 0이면 내역이 없으므로
	 * 미납내역을 확인할 수 없다.
	 * </pre>
	 * @param inDate
	 * @param soId
	 * @return
	 */
	int checkClaimEnd(String inDate, String soId);
	
	/**
	 * <pre>
	 * </pre>
	 * @param nBlupb01m02
	 * @return
	 */
	int insertUpymJobMst(NBlupb01m02 nBlupb01m02);
	
	int getUpymJobDtlCount(NBlupb01m02 nBlupb01m02);
	
	int insertUpymJobDtl(NBlupb01m02 nBlupb01m02);
	
	UpymBigBsAmt getUpymBigBsAtm(NBlupb01m02 nBlupb01m02);
	
	LazyLoader<UpymDetail> getUpymDetailList(NBlupb01m02 nBlupb01m02);
	
	int insertUpymDtl(UpymDetail upymDetail);
	
	int insertUpymDtl(List<UpymDetail> upymDetailList);
	
	LazyLoader<UpymDetail> getUpymList(NBlupb01m02 nBlupb01m02);
	
	int insertUpym(UpymDetail upymDetail);
	
	int insertUpym(List<UpymDetail> upymDetailList);
	
	LazyLoader<CtrtDetail> getUpymCtrtDetail(NBlupb01m02 nBlupb01m02);
	
	int insertCtrtDetail(CtrtDetail ctrtDetail);
	
	int insertCtrtDetail(List<CtrtDetail> ctrtDetailList);
	
	LazyLoader<UpymEntrustMast> getUpymEntrustMastList(NBlupb01m02 nBlupb01m02);
	
	int insertUpymEntrustMast(UpymEntrustMast upymEntrustMast);
	
	int insertUpymEntrustMast(List<UpymEntrustMast> upymEntrustMastList);
	
	LazyLoader<AuthChgAppl> getAuthChgApplList(NBlupb01m02 nBlupb01m02);
	
	int insertAuthChgAppl(AuthChgAppl authChgAppl);
	
	int insertAuthChgAppl(List<AuthChgAppl> authChgApplList);
	
	/**
	 * 대손제각대상 내역을 조회한다.
	 * @param conn
	 * @param nBlupb01m02
	 * @return
	 */
	LazyLoader<DebtTgtPsn> getDebtTgtPsnList(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 대손제각대상 내역을 저장한다.
	 * @param conn
	 * @param debtTgtPsn
	 * @return
	 */
	int insertDebtTgtPsn(DebtTgtPsn debtTgtPsn);
	
	int insertDebtTgtPsn(List<DebtTgtPsn> debtTgtPsnList);
	
	String getUpymMngTp(NBlupb01m02 nBlupb01m02);
	
	LazyLoader<UpymCtrtMngTp> getUpymCtrtMngTpList(NBlupb01m02 nBlupb01m02);
	
	int insertUpymCtrtMngTp(UpymCtrtMngTp upymCtrtMngTp);
	
	int insertUpymCtrtMngTp(List<UpymCtrtMngTp> upymCtrtMngTpList);
	
	int monthsBetween(String startDate, String endDate);
	
	/**
	 * 제외대상자 처리
	 * @param upym_mng_tp
	 * @param now
	 * @param so_id
	 * @param in_date
	 * @return
	 */
	int updateEntrustMast(String upym_mng_tp, Timestamp now, String so_id, String in_date);
	
}
