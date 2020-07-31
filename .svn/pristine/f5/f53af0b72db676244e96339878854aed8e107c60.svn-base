package com.ntels.ccbs.batch.py.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.py.dao.mapper.DepositMapper;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.DepositCancel;
import com.ntels.ccbs.batch.py.entity.EachDeposit;

@Repository
public class DepositDaoImpl implements DepositDao {

	@Autowired
	private DepositMapper depositMapper;
	
	/**
	 * 건별입금등록를 건별입금내역에 등록한다.
	 * @param pyEachDpst
	 * @return
	 */
	@Override
	public int insertEachDeposit(EachDeposit eachDeposit) {
		return depositMapper.insertEachDeposit(eachDeposit);
	}
	
	/**
	 * 건별입금내역에 등록된 데이타를 조회한다.
	 * @param eachDeposit
	 * @return
	 */
	@Override
	public EachDeposit getEachDeposit(EachDeposit eachDeposit) {
		return depositMapper.getEachDeposit(eachDeposit);
	}
	
	/**
	 * 건별입금내역에 등록된 데이타를 입금내역에 등록한다.
	 * @param pyDpst
	 * @return
	 */
	@Override
	public int insertDeposit(Deposit deposit) {
		return depositMapper.insertDeposit(deposit);
	}
	
	/**
	 * 입금내역에 등록된 데이타에 대해서 건별입금내역의 입금처리일자를 수정한다.
	 * @param eachDeposit
	 * @return
	 */
	@Override
	public int updateEachDeposit(EachDeposit eachDeposit) {
		return depositMapper.updateEachDeposit(eachDeposit);
	}
	
	/**
	 * 입금금액을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public Double getDpstAmt(String dpstSeqNo) {
		return depositMapper.getDpstAmt(dpstSeqNo);
	}
	
	/**
	 * 보증금발생 이력을 저장하기 위해 참조하는 Dpst를 가져온다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public Deposit getDepositForAssrOcc(String dpstSeqNo) {
		return depositMapper.getDepositForAssrOcc(dpstSeqNo);
	}
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록하기위해 참조하는 Dpst를 가져온다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public Deposit getDepositForRcpt(String dpstSeqNo) {
		return depositMapper.getDepositForRcpt(dpstSeqNo);
	}
	
	/**
	 * 입금취소를 위한 데이터를 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public Deposit getDepositForCancel(String dpstSeqNo) {
		return depositMapper.getDepositForCancel(dpstSeqNo);
	}
	
	/**
	 * 입금 취소여부를 갱신한다.
	 * @param cnclYn
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int updateCnclYn(String cnclYn, String dpstSeqNo) {
		return depositMapper.updateCnclYn(cnclYn, dpstSeqNo);
	}
	
	/**
	 * 수납 처리 여부를 Y로 수정해준다.
	 * @param dpstSeqNo
	 * @param payProcDt
	 * @return
	 */
	@Override
	public int updatePayProcDt(String dpstSeqNo, String payProcDt) {
		return depositMapper.updatePayProcDt(dpstSeqNo, payProcDt);
	}
	
	/**
	 * 선수금 타겟 여부 갱신
	 * @param payProcDt
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayTgtYn(String payProcDt, String dpstSeqNo) {
		return depositMapper.updatePrepayTgtYn(payProcDt, dpstSeqNo);
	}
	
	/**
	 * TBLPY_DPST_CNCL에 등록할 데이터를 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public DepositCancel getDepositCancelInfo(String dpstSeqNo) {
		return depositMapper.getDepositCancelInfo(dpstSeqNo);
	}
	
	/**
	 * TBLPY_DPST_CNCL테이블에 취소내역을 등록한다.
	 * @param depositCancel
	 * @return
	 */
	@Override
	public int insertDepositCancel(DepositCancel depositCancel) {
		return depositMapper.insertDepositCancel(depositCancel);
	}
	
	/**
	 * 입금내역의 해당 불명금대상여부를 'Y'로 해준다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int updateAmngTgtYn(String dpstSeqNo) {
		return depositMapper.updateAmngTgtYn(dpstSeqNo);
	}
	
	/**
	 * 일괄 수납처리를 위한 입금내역을 조회한다.
	 * @param parallelCnt
	 * @param parallelNo
	 * @return
	 */
	@Override
	public List<Deposit> getDepositListForBatchReceipt(int parallelCnt, int parallelNo) {
		return depositMapper.getDepositListForBatchReceipt(parallelCnt, parallelNo);
	}
	
	/**
	 * 입금내역 수납처리 결과반영
	 * @param ambgTgtYn
	 * @param payProcDt
	 * @param prepayTgtYn
	 * @param dpstSeqNo
	 */
	@Override
	public int updateDepositBatchReceipt(String ambgTgtYn, String payProcDt, String prepayTgtYn, String dpstSeqNo) {
		return depositMapper.updateDepositBatchReceipt(ambgTgtYn, payProcDt, prepayTgtYn, dpstSeqNo);
	}
	
}
