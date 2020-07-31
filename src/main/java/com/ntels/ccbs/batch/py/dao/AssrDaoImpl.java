package com.ntels.ccbs.batch.py.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.py.dao.mapper.AssrMapper;
import com.ntels.ccbs.batch.py.entity.AssrOcc;

@Repository
public class AssrDaoImpl implements AssrDao {

	@Autowired
	private AssrMapper assrMapper;
	
	/**
	 * 보증금발생내역 테이블에 등록한다.
	 * @param pyAssrOcc
	 * @return
	 */
	@Override
	public int insertAssrOcc(AssrOcc pyAssrOcc) {
		return assrMapper.insertAssrOcc(pyAssrOcc);
	}
	
	/**
	 * 보증금발생내역에 있으면서 보증금대체내역에 있는지 체크
	 * @param dpstTpSeqNo
	 * @return
	 */
	@Override
	public int getAssrCount(String dpstTpSeqNo) {
		return assrMapper.getAssrCount(dpstTpSeqNo);
	}
	
	/**
	 * 보증금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param cnclDttm
	 * @param dpstTpSeqNo
	 * @return
	 */
	@Override
	public int updateAssrOccCancel(String cnclDttm, String dpstTpSeqNo) {
		return assrMapper.updateAssrOccCancel(cnclDttm, dpstTpSeqNo);
	}
	
	/**
	 * 보증금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param assrOcc
	 * @return
	 */
	@Override
	public int updateAssrOccCancelByAssrOccSeqNo(AssrOcc assrOcc) {
		return assrMapper.updateAssrOccCancelByAssrOccSeqNo(assrOcc);
	}
	
	/**
	 * 보증금발생내역 조회
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public List<AssrOcc> getAssrOccByPymSeqNo(String pymSeqNo) {
		return assrMapper.getAssrOccByPymSeqNo(pymSeqNo);
	}
	
	/**
	 * 보증금 대체이력을 취소처리한다.
	 * @param cnclDttm
	 * @param assrTransSeqNo
	 * @return
	 */
	@Override
	public int updateAssrTransHistCancel(String cnclDttm, String assrTransSeqNo) {
		return assrMapper.updateAssrTransHistCancel(cnclDttm, assrTransSeqNo);
	}
	
	/**
	 * assrTransSeqNo값을 가지고 assrOccSeqNo값을 조회한다.
	 * @param assrTransSeqNo
	 * @return
	 */
	@Override
	public String getAssrOccSeqNoByAssrTransSeqNo(String assrTransSeqNo) {
		return assrMapper.getAssrOccSeqNoByAssrTransSeqNo(assrTransSeqNo);
	}
	
	/**
	 * assrOccSeqNo에 해당하는 보증금 관련 금액들을 조회한다.
	 * @param assrOccSeqNo
	 * @return
	 */
	@Override
	public AssrOcc getAssrAmountByAssrOccSeqNo(String assrOccSeqNo) {
		return assrMapper.getAssrAmountByAssrOccSeqNo(assrOccSeqNo);
	}

	/**
	 * 보증금 발생이력의 상태 및 금액을 업데이트한다.
	 * @param payObjAmt
	 * @param chgDate
	 * @param assrOccSeqNo
	 * @return
	 */
	@Override
	public int updateAssrOccStat(double payObjAmt, Timestamp chgDate, String assrOccSeqNo) {
		return assrMapper.updateAssrOccStat(payObjAmt, chgDate, assrOccSeqNo);
	}
	
}
