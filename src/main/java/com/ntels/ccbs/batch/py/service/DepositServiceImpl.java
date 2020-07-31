package com.ntels.ccbs.batch.py.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.py.dao.DepositDao;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.DepositCancel;
import com.ntels.ccbs.batch.py.entity.EachDeposit;

@Service
public class DepositServiceImpl extends BaseService implements DepositService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClogService clogService;
	
	@Autowired
	private DepositDao depositDao;
	
	/**
	 * 건별입금내역을 저장한다.
	 * @param eachDeposit
	 * @return
	 */
	@Override
	public int insertEachDeposit(EachDeposit eachDeposit) {		
		return depositDao.insertEachDeposit(eachDeposit);
	}
	
	/**
	 * 건별입금내역에 등록된 데이타를 조회한다.
	 * @param eachDpstSeq
	 * @return
	 */
	@Override
	public EachDeposit getEachDeposit(EachDeposit eachDeposit) {
		checkEmpty(eachDeposit.getEachDpstSeq(), "eachDpstSeq");
		
		return depositDao.getEachDeposit(eachDeposit);
	}
	
	/**
	 * 건별입금내역의 데이터를 토대로 입금내역을 등록한다.
	 * @param eachDpositSeq
	 * @return
	 */
	@Override
	public int insertDepositFromEachDeposit(EachDeposit eachDeposit) {
		String dpstSeqNo = eachDeposit.getDpstSeqNo();
		eachDeposit = getEachDeposit(eachDeposit);
		eachDeposit.setDpstSeqNo(dpstSeqNo);
		
		// 가져온 데이터를 바탕으로 PyDpst객체를 생성한다.
		Deposit deposit = new Deposit();
		
		CUtil.copyObjectValue(eachDeposit, deposit);
		
		deposit.setDpstProcDt(getDt()); // date YYYYMMDD
		deposit.setDpstTp("3");
		deposit.setDpstTpSeqNo(eachDeposit.getEachDpstSeq());
		
		if (eachDeposit.getDpstCl().equals("05") == true) {
			deposit.setPayCorpTp("02");
		} else if (eachDeposit.getDpstCl().equals("11") == true) {
			deposit.setPayCorpTp("03");
		} else {
			deposit.setPayCorpTp("01");
		}
		
		if (StringUtils.isNotEmpty(eachDeposit.getDpstBnkAcntCd())) {
			if (eachDeposit.getDpstCl().equals("05") == true) {
				deposit.setPayCorpCd(eachDeposit.getDpstBnkAcntCd().substring(0, 2));
			} else {
				deposit.setPayCorpCd(eachDeposit.getDpstBnkAcntCd().substring(0, 3));
			}	
		}
		
		// TODO 고객 카드 번호 암호화 추가 필요함
		deposit.setAcntCardNo(eachDeposit.getAcntCardNo());
		deposit.setPayProcYn("N");
		deposit.setPayProcDt("");
		deposit.setAmbgTgtYn("N");
		deposit.setCnclYn("N");
		deposit.setPayCnclYn("N");
		deposit.setRegDate(now());
		deposit.setPrepayTgtYn("N");
		
		return depositDao.insertDeposit(deposit);
	}
	
	/**
	 * 입금내역에 등록된 데이타에 대해서 건별입금내역의 입금처리일자를 수정한다.
	 * @param eachDeposit
	 * @return
	 */
	@Override
	public int updateEachDeposit(EachDeposit eachDeposit) {
		
		if (eachDeposit == null || StringUtils.isEmpty(eachDeposit.getEachDpstSeq()) == true) {
			clogService.writeLog("eachDpstSeq값을 찾을 수 없어 업데이트를 수행할 수 없습니다.");
			throw new RuntimeException("eachDpstSeq값을 찾을 수 없어 업데이트를 수행할 수 없습니다.");
		}
		
		eachDeposit.setDpstProcDt(getDt());
		
		return depositDao.updateEachDeposit(eachDeposit);
	}
	
	/**
	 * 입금금액을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public double getDpstAmt(String dpstSeqNo) {
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		
		Double value = depositDao.getDpstAmt(dpstSeqNo);
		
		if (value == null) {
			return 0.0;
		}
		
		return value;
	}
	
	/**
	 * 보증금발생 이력을 저장하기 위해 참조하는 Dpst를 가져온다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public Deposit getDepositForAssrOcc(String dpstSeqNo) {
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		
		return depositDao.getDepositForAssrOcc(dpstSeqNo);
	}
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록하기위해 참조하는 Dpst를 가져온다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public Deposit getDepositForRcpt(String dpstSeqNo) {
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		
		return depositDao.getDepositForRcpt(dpstSeqNo);
	}
	
	/**
	 * 수납 처리 여부를 Y로 수정해준다.
	 * @param dpstSeqNo
	 * @param payProcDt
	 * @return
	 */
	@Override
	public int updatePayProcDt(String dpstSeqNo, String payProcDt) {
		return depositDao.updatePayProcDt(dpstSeqNo, payProcDt);
	}
	
	/**
	 * <pre>
	 * 건별입금을 취소한다.
	 * TBLPY_DPST테이블의 CNCL_YN의 값을 조회하여 해당 값이 Y라면 이미 취소된 내역이다.
	 * 값이 N이라면 테이블의 CNCL_YN의 값을 Y로 업데이트 한 후 TBLPY_DPST_CNCL에 취소 내역을 등록한다.
	 * </pre>
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public Deposit updateCancelDeposit(String dpstSeqNo) {
		
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		
		// TODO FOR UPDATE NOWAIT 이라는 오라클 전용 sql을 사용하였는데 이를 어떻게 처리할지..
		Deposit deposit = depositDao.getDepositForCancel(dpstSeqNo);
		
		if (deposit.getCnclYn().equals("Y") == true) {
			logger.debug("Payment had already been canceled.");
			throw new RuntimeException("Payment had already been canceled.");
		}
		
		// 입금내역에 취소상태를 취소(Y)로 업데이트
		int update = depositDao.updateCnclYn("Y", dpstSeqNo);
		
		if (update <= 0) {
			logger.debug("Oracle Error: TBLPY_DPST(Update)");
			throw new RuntimeException("Oracle Error: TBLPY_DPST(Update)");
		}
		
		return deposit;
	}
	
	/**
	 * 취소된 입금내역을 TBLPY_DPST_CNCL에 등록한다.
	 * @param regrId
	 * @param reason
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int insertDepositCancelInfo(String regrId, String reason, String dpstSeqNo) {
		
		checkEmpty(regrId, "regrId");
		checkEmpty(reason, "reason");
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		
		DepositCancel depositCancel = depositDao.getDepositCancelInfo(dpstSeqNo);
		depositCancel.setCnclResn(reason);
		depositCancel.setCnclDttm(getDttm());
		depositCancel.setCnclrId(regrId);
		depositCancel.setRegDate(now());
		
		return depositDao.insertDepositCancel(depositCancel);
	}
	
	/**
	 * 일괄 수납처리를 위한 입금내역을 조회한다.
	 * @param parallelCnt
	 * @param parallelNo
	 * @return
	 */
	@Override
	public List<Deposit> getDepositListForBatchReceipt(int parallelCnt, int parallelNo) {
		return depositDao.getDepositListForBatchReceipt(parallelCnt, parallelNo);
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
		return depositDao.updateDepositBatchReceipt(ambgTgtYn, payProcDt, prepayTgtYn, dpstSeqNo);
	}
	
}
