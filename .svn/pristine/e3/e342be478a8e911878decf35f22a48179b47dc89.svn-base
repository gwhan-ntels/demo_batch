package com.ntels.ccbs.batch.py.dao;

import java.sql.Timestamp;
import java.util.List;

import com.ntels.ccbs.batch.py.entity.AssrOcc;

public interface AssrDao {

	/**
	 * 보증금발생내역 테이블에 등록한다.
	 * @param assrOcc
	 * @return
	 */
	int insertAssrOcc(AssrOcc assrOcc);
	
	/**
	 * 보증금발생내역에 있으면서 보증금대체내역에 있는지 체크
	 * @param dpstTpSeqNo
	 * @return
	 */
	int getAssrCount(String dpstTpSeqNo);
	
	/**
	 * 보증금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param cnclDttm
	 * @param dpstTpSeqNo
	 * @return
	 */
	int updateAssrOccCancel(String cnclDttm, String dpstTpSeqNo);
	
	/**
	 * 보증금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param assrOcc
	 * @return
	 */
	int updateAssrOccCancelByAssrOccSeqNo(AssrOcc assrOcc);
	
	/**
	 * 보증금발생내역 조회
	 * @param pymSeqNo
	 * @return
	 */
	List<AssrOcc> getAssrOccByPymSeqNo(String pymSeqNo);
	
	/**
	 * 보증금 대체이력을 취소처리한다.
	 * @param cnclDttm
	 * @param assrTransSeqNo
	 * @return
	 */
	int updateAssrTransHistCancel(String cnclDttm, String assrTransSeqNo);
	
	/**
	 * assrTransSeqNo값을 가지고 assrOccSeqNo값을 조회한다.
	 * @param assrTransSeqNo
	 * @return
	 */
	String getAssrOccSeqNoByAssrTransSeqNo(String assrTransSeqNo);
	
	/**
	 * assrOccSeqNo에 해당하는 보증금 관련 금액들을 조회한다.
	 * @param assrOccSeqNo
	 * @return
	 */
	AssrOcc getAssrAmountByAssrOccSeqNo(String assrOccSeqNo);

	/**
	 * 보증금 발생이력의 상태 및 금액을 업데이트한다.
	 * @param payObjAmt
	 * @param chgDate
	 * @param assrOccSeqNo
	 * @return
	 */
	int updateAssrOccStat(double payObjAmt, Timestamp chgDate, String assrOccSeqNo);
	
}
