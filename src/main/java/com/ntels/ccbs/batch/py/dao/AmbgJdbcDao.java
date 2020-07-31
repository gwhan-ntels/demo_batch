package com.ntels.ccbs.batch.py.dao;

import java.sql.Timestamp;
import java.util.List;

import com.ntels.ccbs.batch.py.entity.AmbgOcc;
import com.ntels.ccbs.batch.py.entity.AmbgTransHistory;
import com.ntels.ccbs.batch.py.entity.Deposit;

public interface AmbgJdbcDao {

	/**
	 * 불명금(납부계정이 없는경우)에 대해서 불명금발생내역을 조회한다.
	 * @param deposit
	 * @return
	 */
	List<AmbgOcc> getAmbgOcc(Deposit deposit);
	
	/**
	 * 미리 생성된 불명금 내역을 시퀀스를 발급하여 등록한다.
	 * @param ambgOcc
	 * @return
	 */
	int insertAmbgOcc(List<AmbgOcc> ambgOcc);
	
	/**
	 * 불명금 발생내역을 취소한다.
	 * @param ambgOccList
	 * @return
	 */
	int updateAmbgCancel(List<AmbgOcc> ambgOccList);
	
	/**
	 * 불명금 대체내역을 취소처리한다.
	 * @param cnclDttm
	 * @param ambgTransSeqNo
	 * @return
	 */
	int updateAmbgTransHistCancel(String cnclDttm, String ambgTransSeqNo);

	/**
	 * 불명금 발생이력의 상태 및 금액을 업데이트한다.
	 * @param payObjAmt
	 * @param chgDate
	 * @param dpstTpSeqNo
	 * @return
	 */
	int updateAmbgOccStat(double payObjAmt, Timestamp chgDate, String dpstTpSeqNo);

	/**
	 * 불명금 관련 금액들을 조회한다.
	 * @param dpstTpSeqNo
	 * @return
	 */
	AmbgOcc getAmbgAmount(String dpstTpSeqNo);
	
	/**
	 * 불명금 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	AmbgOcc getAmbgBal(String ambgOccSeqNo);
	
	/**
	 * 보증금 발생 내역에 저장할 불명금 데이터 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	AmbgOcc getAmbgForAssr(String ambgOccSeqNo);
	
	/**
	 * 불명금 진행 상태 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	String getAmbgProcStat(String ambgOccSeqNo);
	
	/**
	 * 불명금발생내역을 수정한다.
	 * @param ambgOccList
	 * @return
	 */
	int updateAmbgOccByAmbgOccSeqNo(List<AmbgOcc> ambgOccList);
	
	/**
	 * 불명금대체이력에 등록한다.
	 * @param ambgTransHistoryList
	 * @return
	 */
	int insertAmbgTransHistory(List<AmbgTransHistory> ambgTransHistoryList);
	
}
