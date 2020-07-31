package com.ntels.ccbs.batch.py.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.JdbcDao;
import com.ntels.ccbs.batch.py.dao.mapper.PrepayMapper;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.PrepayTransHistory;

@Repository
public class PrepayDaoImpl extends JdbcDao implements PrepayDao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/py/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "PrepayMapper";
	}
	
	@Autowired
	private PrepayMapper prepayMapper;
	
	/**
	 * 수납내역에 등록된 데이타중에서  선수금적용액이 0인 아닌것을 선수금발생내역에 등록한다.
	 * @param prepayOcc
	 * @return
	 */
	@Override
	public int insertPrepayOcc(PrepayOcc prepayOcc) {
		return prepayMapper.insertPrepayOcc(prepayOcc);
	}
	
	/**
	 * 선수금 발생내역을 일괄등록한다.
	 * @param prepayOccList
	 * @return
	 */
	@Override
	public int insertPrepayOcc(final List<PrepayOcc> prepayOccList) {
		int insertCount = batchJob(PrepayMapper.class, new BatchJob<PrepayMapper>() {

			@Override
			public int job(PrepayMapper mapper) {
				int cnt = 0;
				for (int i = 0; i < prepayOccList.size(); i++) {
					mapper.insertPrepayOcc(prepayOccList.get(i));
					cnt++;
				}
				return cnt;
			}
		});
		
		return insertCount;
	}
	
	/**
	 * 선납입금취소
	 * @param cnclResn
	 * @param regrId
	 * @param cnclDttm
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayCancel(String cnclResn, String regrId, String cnclDttm, String dpstSeqNo) {
		return prepayMapper.updatePrepayCancel(cnclResn, regrId, cnclDttm, dpstSeqNo);
	}
	
	/**
	 * 선수금발생내역에 있으면서 선수금대체내역에 있는지 체크
	 * @param prepayOccTgtSeqNo
	 * @return
	 */
	@Override
	public int getPrepayCount(String prepayOccTgtSeqNo) {
		return prepayMapper.getPrepayCount(prepayOccTgtSeqNo);
	}
	
	/**
	 * 선수금발생내역(취소여부, 취소일자)을 수정한다.
	 * @param cnclDttm
	 * @param prepayOccTgtSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayOccCancel(String cnclDttm, String prepayOccTgtSeqNo) {
		return prepayMapper.updatePrepayOccCancel(cnclDttm, prepayOccTgtSeqNo);
	}
	
	/**
	 * 선수금 발생내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public PrepayOcc getPrepayDeposit(String pymSeqNo) {
		return prepayMapper.getPrepayDeposit(pymSeqNo);
	}
	
	/**
	 * prepayTransSeqNo로 prepayOccSeqNo를 조회한다.
	 * @param prepayTransSeqNo
	 * @return
	 */
	@Override
	public String getPrepayOccSeqNoFromPrepayTransHistory(String prepayTransSeqNo) {
		return prepayMapper.getPrepayOccSeqNoFromPrepayTransHistory(prepayTransSeqNo);
	}
	
	/**
	 * prepayOccSeq에 해당하는 선수금 발생내역을 취소처리한다. 
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayOccCancelByPrepayOccSeqNo(String prepayOccSeqNo) {
		return prepayMapper.updatePrepayOccCancelByPrepayOccSeqNo(prepayOccSeqNo);
	}
	
	/**
	 * 선수금 대체이력에서 취소상태를 Y로 업데에트한다.
	 * @param prepayTransSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayTransHistoryCancel(String prepayTransSeqNo) {
		return prepayMapper.updatePrepayTransHistoryCancel(prepayTransSeqNo);
	}
	
	/**
	 * 선수금 대체금액 및 선수금 잔액을 조회한다.
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public PrepayOcc getPrepayAmount(String prepayOccSeqNo) {
		return prepayMapper.getPrepayAmount(prepayOccSeqNo);
	}

	/**
	 * TBLPY_PREPAY_OCC테이블에 대해서 선수금대체 금액 및 선수금 잔액과 선수금 대치 완료여부를 'N'으로 업데이트한다.
	 * @param payObjAmt
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayTransStat(double payObjAmt, Timestamp chgDate, String prepayOccSeqNo) {
		return prepayMapper.updatePrepayTransStat(payObjAmt, chgDate, prepayOccSeqNo);
	}
	
	/**
	 * 선수금 진행 상태 조회
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public String getPrepayProcStat(String prepayOccSeqNo) {
		return prepayMapper.getPrepayProcStat(prepayOccSeqNo);
	}
	
	/**
	 * 선수금발생내역을 수정한다.
	 * @param prepayOcc
	 * @return
	 */
	@Override
	public int updatePrepayOcc(PrepayOcc prepayOcc) {
		return prepayMapper.updatePrepayOcc(prepayOcc);
	}
	
	/**
	 * 
	 * @param prepayOcc
	 * @return
	 */
	@Override
	public int updatePrepayOccTransAmt(PrepayOcc prepayOcc) {
		return prepayMapper.updatePrepayOccTransAmt(prepayOcc);
	}
	
	/**
	 * 
	 * @param prepayOccList
	 * @return
	 */
	@Override
	public int updatePrepayOccTransAmt(final List<PrepayOcc> prepayOccList) {
		int updateCount = batchJob(PrepayMapper.class, new BatchJob<PrepayMapper>() {

			@Override
			public int job(PrepayMapper mapper) {
				int cnt = 0;
				
				for (PrepayOcc prepayOcc : prepayOccList) {
					mapper.updatePrepayOccTransAmt(prepayOcc);
					cnt++;
				}
				
				return cnt;
			}
		});
		
		return updateCount;
	}

	/**
	 * 선수금대체이력에 등록한다.
	 * @param prepayTransHistory
	 * @return
	 */
	@Override
	public int insertPrepayTransHistory(PrepayTransHistory prepayTransHistory) {
		return prepayMapper.insertPrepayTransHistory(prepayTransHistory);
	}
	
	/**
	 * 선수금대체이력에 등록한다.
	 * @param prepayTransHistory
	 * @return
	 */
	@Override
	public int insertPrepayTransHistory(final List<PrepayTransHistory> prepayTransHistoryList) {
		int insertCount = batchJob(PrepayMapper.class, new BatchJob<PrepayMapper>() {

			@Override
			public int job(PrepayMapper mapper) {
				int cnt = 0;
				
				for (PrepayTransHistory prepayTransHistory : prepayTransHistoryList) {
					mapper.insertPrepayTransHistory(prepayTransHistory);
					cnt++;
				}
				
				return cnt;
			}
		});
		
		return insertCount;
	}
	
	/**
	 * 선수금 발생이력 조회
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public PrepayOcc getPrepayOcc(String prepayOccSeqNo) {
		return prepayMapper.getPrepayOcc(prepayOccSeqNo);
	}
	
	/**
	 * 수납이 완료되지 않은 청구건에 대해 선수금 발생내역을 조회한다.
	 * @param soId
	 * @return
	 */
	@Override
	public List<PrepayOcc> getPrepayOccForUnpaidBill(String soId) {
		return prepayMapper.getPrepayOccForUnpaidBill(soId);
	}
	
	@Override
	public int updatePrepayOccProcStat(PrepayOcc prepayOcc) {
		return updateOne("updatePrepayOccProcStat", prepayOcc);
	}
	
	@Override
	public int updatePrepayOccProcStat(List<PrepayOcc> prepayOcc) {
		return update("updatePrepayOccProcStat", PrepayOcc.class, prepayOcc);
	}

}
