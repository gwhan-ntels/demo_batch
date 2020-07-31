package com.ntels.ccbs.batch.up.dao;

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

public interface NBlupb01m02Dao {

	/**
	 * 마감정보에서 청구마감정보 확인
	 * 결과가 0이면 마감건이 없음.
	 * @param inDate
	 * @param chSoId
	 * @return
	 */
	int checkClaimEnd(String inDate, String soId);
	
	/**
	 * 성공 로그를 조회하여 작업 완료여부를 체크한다.
	 * @param nBlupb01m02
	 * @return
	 */
	int getUpymJobDtlCount(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 대상기준값 조회
	 * UPYM_AMT_FROM, UPYM_AMT_TO값이 각각 double타입으로 조회된다.
	 * MyBatis사용해서 조회
	 * @param nBlupb01m02
	 * @return
	 */
	UpymBigBsAmt getUpymBigBsAmt(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 
	 * @param so_id
	 * @param in_date
	 * @return
	 */
	String getUpymMngTp(String soId, String inDate);
	
	/**
	 * 미납 상세 정보를 조회한다.
	 * @return
	 */
	LazyLoader<UpymDetail> getUpymDtlList(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 미납 내역을 조회한다.
	 */
	LazyLoader<UpymDetail> getUpymList(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 작업 내역을 기록하는듯 한데 정확하게는 아직 파악이 되지않음.
	 */
	int insertUpymJobMst(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 미납 상세 작업 내역을 기록한다.
	 */
	int insertUpymJobDtl(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 미납 상세 데이터를 기록한다.
	 */
	int insertUpymDtl(UpymDetail upymDetail);

	/**
	 * 미납 상세 데이터를 기록한다.
	 */	
	int insertUpymDtl(List<UpymDetail> upymDetailList);
	
	/**
	 * 미납 내역을 기록한다.
	 */
	int insertUpym(UpymDetail upymDetail);

	/**
	 * 미납 내역을 기록한다.
	 */
	int insertUpym(List<UpymDetail> upymDetailList);
	
	LazyLoader<CtrtDetail> getUpymCtrtDetail(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 회선기준 미납내역 저장
	 * @param ctrtDetail
	 * @return
	 */
	int insertUpymCtrt(CtrtDetail ctrtDetail);

	/**
	 * 회선기준 미납내역 저장
	 * @param ctrtDetail
	 * @return
	 */
	int insertUpymCtrt(List<CtrtDetail> ctrtDetailList);
	
	/**
	 * 직권변경신청 내역을 조회한다.
	 * @param conn
	 * @param nBlupb01m02
	 * @return
	 */
	LazyLoader<AuthChgAppl> getAuthChgApplList(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 직권변경신청 내역을 저장한다.
	 * @param conn
	 * @param authChgAppl
	 * @return
	 */
	int insertAuthChgAppl(AuthChgAppl authChgAppl);
	
	/**
	 * 직권변경신청 내역을 저장한다.
	 * @param conn
	 * @param authChgAppl
	 * @return
	 */	
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
	
	/**
	 * 대손제각대상 내역을 저장한다.
	 * @param conn
	 * @param debtTgtPsn
	 * @return
	 */
	int insertDebtTgtPsn(List<DebtTgtPsn> debtTgtPsnList);
	
	/**
	 * 정해진 대응유형 이외의 대응유형 조회
	 * @param nBlupb01m02
	 * @return
	 */
	LazyLoader<UpymCtrtMngTp> getUpymCtrtMngTpList(NBlupb01m02 nBlupb01m02);
	
	/**
	 * 정해진 대응유형 이외의 대응유형 저장
	 * @param upymCtrtMngTp
	 * @return
	 */
	int insertUpymCtrtMngTp(UpymCtrtMngTp upymCtrtMngTp);
	
	/**
	 * 정해진 대응유형 이외의 대응유형 저장
	 * @param conn
	 * @param upymCtrtMngTpList
	 * @return
	 */
	int insertUpymCtrtMngTp(List<UpymCtrtMngTp> upymCtrtMngTpList);

	/**
	 * 수납센터/채권추심 내역 조회
	 * @param conn
	 * @param nBlupb01m02
	 * @return
	 */
	LazyLoader<UpymEntrustMast> getUpymEntrustMastList(NBlupb01m02 nBlupb01m02);

	/**
	 * 수납센터/채권추심 내역 저장
	 * @param conn
	 * @param upymEntrustMast
	 * @return
	 */
	int insertUpymEntrustMast(UpymEntrustMast upymEntrustMast);
	
	/**
	 * 수납센터/채권추심 내역 저장
	 * @param conn
	 * @param upymEntrustMast
	 * @return
	 */
	int insertUpymEntrustMast(List<UpymEntrustMast> upymEntrustMastList);
	
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
