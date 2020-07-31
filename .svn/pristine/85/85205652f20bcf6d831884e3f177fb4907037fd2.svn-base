package com.ntels.ccbs.batch.py.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.BatchDao;
import com.ntels.ccbs.batch.py.dao.mapper.AmbgMapper;
import com.ntels.ccbs.batch.py.entity.AmbgOcc;
import com.ntels.ccbs.batch.py.entity.AmbgTransHistory;

@Repository
public class AmbgDaoImpl extends BatchDao implements AmbgDao {

	@Autowired
	private AmbgMapper ambgMapper;
	
	/**
	 * 불명금(납부계정이 없는경우)에 대해서 불명금발생내역을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public AmbgOcc getAmbgOcc(String dpstSeqNo) {
		return ambgMapper.getAmbgOcc(dpstSeqNo);
	}
	
	/**
	 * 불명금(납부계정이 없는경우)에 대해서 불명금발생내역에 등록한다.
	 * @param pyAmbgOcc
	 * @return
	 */
	@Override
	public int insertAmbgOcc(AmbgOcc pyAmbgOcc) {
		return ambgMapper.insertAmbgOcc(pyAmbgOcc);
	}
	
	/**
	 * 미리 생성된 불명금 내역을 시퀀스를 발급하여 등록한다.
	 * @param ambgOcc
	 * @return
	 */
	@Override
	public int insertAmbgOcc(final List<AmbgOcc> ambgOcc) {
		int insertCount = batchJob(AmbgMapper.class, new BatchJob<AmbgMapper>() {

			@Override
			public int job(AmbgMapper mapper) {
				int cnt = 0;
				for (int i = 0; i < ambgOcc.size(); i++) {
					mapper.insertAmbgOcc(ambgOcc.get(i));
					cnt++;
				}
				return cnt;
			}
		});
		
		return insertCount;
	}
	
	/**
	 * 불명금발생내역에 있으면서 불명금대체내역에 있는가.. 조회
	 * @param dpstTpSeqNo
	 * @return
	 */
	@Override
	public int getAmbgCount(String dpstTpSeqNo) {
		return ambgMapper.getAmbgCount(dpstTpSeqNo);
	}
	
	/**
	 * 불명금 발생내역을 취소한다.
	 * @param cnclDttm
	 * @param dpstTpSeqNo
	 * @return
	 */
	@Override
	public int updateAmbgCancel(String cnclDttm, String dpstTpSeqNo) {
		return ambgMapper.updateAmbgCancel(cnclDttm, dpstTpSeqNo);
	}
	
	/**
	 * 불명금 대체내역을 취소처리한다.
	 * @param cnclDttm
	 * @param ambgTransSeqNo
	 * @return
	 */
	@Override
	public int updateAmbgTransHistCancel(String cnclDttm, String ambgTransSeqNo) {
		return ambgMapper.updateAmbgTransHistCancel(cnclDttm, ambgTransSeqNo);
	}

	/**
	 * 불명금 발생이력의 상태 및 금액을 업데이트한다.
	 * @param payObjAmt
	 * @param chgDate
	 * @param dpstTpSeqNo
	 * @return
	 */
	@Override
	public int updateAmbgOccStat(double payObjAmt, Timestamp chgDate, String dpstTpSeqNo) {
		return ambgMapper.updateAmbgOccStat(payObjAmt, chgDate, dpstTpSeqNo);
	}

	/**
	 * 불명금 관련 금액들을 조회한다.
	 * @param dpstTpSeqNo
	 * @return
	 */
	@Override
	public AmbgOcc getAmbgAmount(String dpstTpSeqNo) {
		return ambgMapper.getAmbgAmount(dpstTpSeqNo);
	}
	
	/**
	 * 불명금 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	@Override
	public AmbgOcc getAmbgBal(String ambgOccSeqNo) {
		return ambgMapper.getAmbgBal(ambgOccSeqNo);
	}
	
	/**
	 * 보증금 발생 내역에 저장할 불명금 데이터 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	@Override
	public AmbgOcc getAmbgForAssr(String ambgOccSeqNo) {
		return ambgMapper.getAmbgForAssr(ambgOccSeqNo);
	}
	
	/**
	 * 불명금 진행 상태 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	@Override
	public String getAmbgProcStat(String ambgOccSeqNo) {
		return ambgMapper.getAmbgProcStat(ambgOccSeqNo);
	}
	
	/**
	 * 불명금발생내역을 수정한다.
	 * @param ambgOcc
	 * @return
	 */
	@Override
	public int updateAmbgOccByAmbgOccSeqNo(AmbgOcc ambgOcc) {
		return ambgMapper.updateAmbgOccByAmbgOccSeqNo(ambgOcc);
	}
	
	/**
	 * 불명금대체이력에 등록한다.
	 * @param ambgTransHistory
	 * @return
	 */
	@Override
	public int insertAmbgTransHistory(AmbgTransHistory ambgTransHistory) {
		return ambgMapper.insertAmbgTransHistory(ambgTransHistory);
	}
	
}
