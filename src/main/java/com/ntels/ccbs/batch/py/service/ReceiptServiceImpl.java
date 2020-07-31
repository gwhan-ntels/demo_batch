package com.ntels.ccbs.batch.py.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.py.dao.ReceiptDao;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptCancel;
import com.ntels.ccbs.batch.py.entity.ReceiptCancelAppl;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

@Service
public class ReceiptServiceImpl extends BaseService implements ReceiptService {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private ReceiptDao receiptDao;
	
	/**
	 * 청구내역에 수정된 데이타를 수납상세내역에 등록한다.
	 * @param receiptDetail
	 * @return
	 */
	@Override
	public int insertReceiptDetail(ReceiptDetail receiptDetail) {
		return receiptDao.insertReceiptDetail(receiptDetail);
	}
	
	/**
	 * 청구내역에 수정된 데이타를 수납상세내역에 등록한다.
	 * @param receiptDetailList
	 * @return
	 */
	@Override
	public int insertReceiptDetail(List<ReceiptDetail> receiptDetailList) {
		return receiptDao.insertReceiptDetail(receiptDetailList);
	}
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록한다.
	 * @param receipt
	 * @return
	 */
	@Override
	public int insertReceipt(Receipt receipt) {
		return receiptDao.insertReceipt(receipt);
	}
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록한다.
	 * @param receiptList
	 * @return
	 */
	@Override
	public int insertReceipt(List<Receipt> receiptList) {
		return receiptDao.insertReceipt(receiptList);
	}
	
	/**
	 * 대리점수납정보에 등록하기위해 RcptDtl정보를 조회한다.
	 * dpstSeqNo값이 있으면 dpstSeqNo값으로 조회를 하고
	 * dpstSeqNo값이 없으면 pymSeqNo값으로 조회를 한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public List<ReceiptDetail> getReceiptDetailforBondRcptTr(String dpstSeqNo, String pymSeqNo) {
		
		if (StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo) == true && StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo)) {
			clogService.writeLog("pymSeqNo 또는 dpstSeqNo값이 없어서 조회할 수 없습니다.");
			throw new RuntimeException("pymSeqNo 또는 dpstSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		return receiptDao.getReceiptDetailforBondRcptTr(dpstSeqNo, pymSeqNo);
	}
	
	/**
	 * 해당 수납 시퀀스 번호에 해당하는 납부 금액의 합을 가져온다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public double getSumRcptAmt(String pymSeqNo) {
		
		checkEmpty(pymSeqNo, "pymSeqNo");
		
		Double amt = receiptDao.getSumRcptAmt(pymSeqNo);
		
		if (amt == null) {
			return 0.0;
		}
		
		return amt;
	}
	
	/**
	 * 수납내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public Receipt getReceipt(String pymSeqNo) {
		
		checkEmpty(pymSeqNo, "pymSeqNo");
		
		return receiptDao.getReceipt(pymSeqNo);
	}
	
	/**
	 * 입금시퀀스에 해당하는 수납 상세내역을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public List<ReceiptDetail> getReceiptDetailList(String dpstSeqNo, String pymSeqNo) {
		
		if (StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo) == true 
				&& StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo)) {
			throw new RuntimeException("dpstSeqNo또는 pymSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		return receiptDao.getReceiptDetailList(dpstSeqNo, pymSeqNo);
	}
	
	/**
	 * 입금시퀀스에 해당하는 수납을 취소한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int updateReceiptCancel(String dpstSeqNo, String pymSeqNo) {
	
		if (StringUtils.isEmptyOrWhitespaceOnly(dpstSeqNo) == true 
				&& StringUtils.isEmptyOrWhitespaceOnly(pymSeqNo)) {
			throw new RuntimeException("dpstSeqNo또는 pymSeqNo값이 없어서 조회할 수 없습니다.");
		}
		
		return receiptDao.updateReceiptCancel(dpstSeqNo, pymSeqNo);
		
	}

	/**
	 * 취소된 입금내역 시퀀스로 입금구분값을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public String getCanceledDpstCl(String dpstSeqNo) {
		
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		
		return receiptDao.getCanceledDpstCl(dpstSeqNo);
		
	}
	
	/**
	 * 수납대상금액에서 수납적용금액을 뺀 값을 조회
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public List<Receipt> getReceiptBillInfo(String dpstSeqNo) {
		
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		
		return receiptDao.getReceiptBillInfo(dpstSeqNo);
	}
	
	/**
	 * 해당 수납번호로 이미 취소 여부를 조회한다.
	 * @return
	 */
	@Override
	public String getCancelYn(String pymSeqNo) {
		
		checkEmpty(pymSeqNo, "pymSeqNo");
		System.out.println("========>"+pymSeqNo);
		return receiptDao.getCancelYn(pymSeqNo);
	}
	
	/**
	 * 수납취소결재신청에 등록한다.
	 * @param pymSeqNo
	 * @param regrId
	 * @return
	 */
	@Override
	public int insertReceiptCancelAppl(String pymSeqNo, String regrId) {
		checkEmpty(pymSeqNo, "pymSeqNo");
		
		Receipt receipt = receiptDao.getReceipt(pymSeqNo);
		
		regrId = regrId == null ? " " : regrId;
		
		ReceiptCancelAppl receiptCancelAppl = new ReceiptCancelAppl();
		CUtil.copyObjectValue(receipt, receiptCancelAppl);
		
		receiptCancelAppl.setRcptPsnId(regrId);
		receiptCancelAppl.setRcptDttm(getDttm());
		receiptCancelAppl.setApprReqrId(regrId);
		receiptCancelAppl.setApprReqDttm(getDttm());
		receiptCancelAppl.setDcsnProcStat("01");
		receiptCancelAppl.setCnclResn("RECEIPT CANCELLATIONI");
		receiptCancelAppl.setRegDate(now());
		
		return receiptDao.insertReceiptCancelAppl(receiptCancelAppl);

	}
	
	/**
	 * 수납취소내역과 수납취소상세내역에 등록한다.
	 * @param cnclResn
	 * @param pymSeqNo
	 * @param receiptId 접수번호
	 * @return
	 */
	@Override
	public int insertReceiptCancel(String cnclResn, String pymSeqNo, String receiptId, String regrId) {
		checkEmpty(pymSeqNo, "pymSeqNo");
		checkEmpty(receiptId, "receiptId");
		
		Receipt receipt = receiptDao.getReceipt(pymSeqNo);
		
		ReceiptCancel receiptCancel = new ReceiptCancel();
		CUtil.copyObjectValue(receipt, receiptCancel);
		
		receiptCancel.setRcptId(receiptId);
		receiptCancel.setDpstTpSeqNo(receipt.getDpstSeqNo() == null ? "0000000000" : receipt.getDpstSeqNo());
		receiptCancel.setCnclrId(regrId);
		receiptCancel.setCnclDttm(getDttm());
		receiptCancel.setCnclResn(cnclResn);
		receiptCancel.setRegrId(regrId);
		receiptCancel.setRegDate(now());
		
		receiptDao.insertReceiptCancel(receiptCancel);
		
		List<ReceiptDetail> receiptDetail = receiptDao.getReceiptDetail(pymSeqNo);
		
		receiptDao.insertReceiptCancelDetail(receiptDetail);
		
		// TODO 성공 실패여부..
		return 0;
	}
	
}
