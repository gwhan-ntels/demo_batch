package com.ntels.ccbs.batch.py.dao.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.PrepayTransHistory;

@Component
public interface PrepayMapper {

	/**
	 * 수납내역에 등록된 데이타중에서  선수금적용액이 0인 아닌것을 선수금발생내역에 등록한다.
	 * @param pyPrepayOcc
	 * @return
	 */
	int insertPrepayOcc(@Param("prepayOcc") PrepayOcc prepayOcc);
	
	/**
	 * 선납입금취소
	 * @param cnclResn
	 * @param regrId
	 * @param cnclDttm
	 * @param dpstSeqNo
	 * @return
	 */
	int updatePrepayCancel(@Param("cnclResn") String cnclResn
							, @Param("regrId") String regrId
							, @Param("cnclDttm") String cnclDttm
							, @Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 선수금발생내역에 있으면서 선수금대체내역에 있는지 체크
	 * @param prepayOccTgtSeqNo
	 * @return
	 */
	int getPrepayCount(@Param("prepayOccTgtSeqNo") String prepayOccTgtSeqNo);
	
	/**
	 * 선수금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param cnclDttm
	 * @param prepayOccTgtSeqNo
	 * @return
	 */
	int updatePrepayOccCancel(@Param("cnclDttm") String cnclDttm, @Param("prepayOccTgtSeqNo") String prepayOccTgtSeqNo);
	
	/**
	 * 선수금 발생내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	PrepayOcc getPrepayDeposit(@Param("pymSeqNo") String pymSeqNo);
	
	/**
	 * prepayTransSeqNo로 prepayOccSeqNo를 조회한다.
	 * @param prepayTransSeqNo
	 * @return
	 */
	String getPrepayOccSeqNoFromPrepayTransHistory(@Param("prepayTransSeqNo") String prepayTransSeqNo);
	
	/**
	 * prepayOccSeq에 해당하는 선수금 발생내역을 취소처리한다. 
	 * @param prepayOccSeqNo
	 * @return
	 */
	int updatePrepayOccCancelByPrepayOccSeqNo(@Param("prepayOccSeqNo") String prepayOccSeqNo);
	
	/**
	 * 선수금 대체이력에서 취소상태를 Y로 업데에트한다.
	 * @param prepayTransSeqNo
	 * @return
	 */
	int updatePrepayTransHistoryCancel(@Param("prepayTransSeqNo") String prepayTransSeqNo);
	
	/**
	 * 선수금 대체금액 및 선수금 잔액을 조회한다.
	 * @param prepayOccSeqNo
	 * @return
	 */
	PrepayOcc getPrepayAmount(@Param("prepayOccSeqNo") String prepayOccSeqNo);

	/**
	 * TBLPY_PREPAY_OCC테이블에 대해서 선수금대체 금액 및 선수금 잔액과 선수금 대치 완료여부를 'N'으로 업데이트한다.
	 * @param payObjAmt
	 * @param prepayOccSeqNo
	 * @return
	 */
	int updatePrepayTransStat(@Param("payObjAmt") double payObjAmt, @Param("chgDate") Timestamp chgDate, @Param("prepayOccSeqNo") String prepayOccSeqNo);
	
	/**
	 * 선수금 진행 상태 조회
	 * @param prepayOccSeqNo
	 * @return
	 */
	String getPrepayProcStat(@Param("prepayOccSeqNo") String prepayOccSeqNo);
	
	/**
	 * 선수금발생내역을 수정한다.
	 * @param prepayOcc
	 * @return
	 */
	int updatePrepayOcc(@Param("prepayOcc") PrepayOcc prepayOcc);
	
	/**
	 * 
	 * @param prepayOcc
	 * @return
	 */
	int updatePrepayOccTransAmt(@Param("prepayOcc") PrepayOcc prepayOcc);
	
	/**
	 * 선수금관련 금액 조회
	 * @param prepayOccSeq
	 * @return
	 */
	PrepayOcc getPrepayBal(@Param("prepayOccSeq") String prepayOccSeq);
	
	/**
	 * 선수금대체이력에 등록한다.
	 * @param prepayTransHistory
	 * @return
	 */
	int insertPrepayTransHistory(@Param("prepayTransHistory") PrepayTransHistory prepayTransHistory);
	
	/**
	 * 선수금 발생이력 조회
	 * @param prepayOccSeqNo
	 * @return
	 */
	PrepayOcc getPrepayOcc(@Param("prepayOccSeqNo") String prepayOccSeqNo);
	
	/**
	 * 수납이 완료되지 않은 청구건에 대해 선수금 발생내역을 조회한다.
	 * @param soId
	 * @return
	 */
	List<PrepayOcc> getPrepayOccForUnpaidBill(@Param("soId") String soId);
	
	/**
	 * 
	 * @param prepayOccSeqNo
	 * @param prepayProcStat
	 * @param soId
	 * @return
	 */
	int updatePrepayOccProcStat(@Param("prepayOccSeqNo") String prepayOccSeqNo, @Param("prepayProcStat") String prepayProcStat, @Param("soId") String soId);
	
}
