package com.ntels.ccbs.batch.iv.tasklet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.iv.common.service.CBillService;
import com.ntels.ccbs.batch.iv.entity.NBlivb01m13;
import com.ntels.ccbs.batch.iv.service.NBlivb01m13Service;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.PrepayTransHistory;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;
import com.ntels.ccbs.batch.py.service.PrepayService;
import com.ntels.ccbs.batch.py.service.ReceiptService;

@Component("nBlivb01m13Tasklet")
@Scope("step")
public class NBlivb01m13Tasklet extends LazyLoaderLogingTasklet<PrepayOcc, NBlivb01m13> {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private CBillService cbillService;
	
	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private NBlivb01m13Service nBlivb01m13JdbcService;
	
	private String oldPymAcntId = "";
	private boolean isSkip = false;
	private double billAmt = 0;
	private int nextPosition = 0;
	
	@Override
	protected boolean isInsertPgmLog() {
		return true;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return true;
	}

	@Override
	protected LazyLoader<PrepayOcc> getLoader() throws Exception {
		CBillComm bill = new CBillComm();
		bill.setSoId(soId);
		bill.setpSeq(pgmSeq);

		return nBlivb01m13JdbcService.getPrepayOccForUnpaidBill(bill);
	}

	@Override
	protected NBlivb01m13 process(PrepayOcc prepayOcc) {
		NBlivb01m13 nBlivb01m13 = new NBlivb01m13();
		
//		int billCount = 0;
//		double billAplyAmt = 0;
		
		if (oldPymAcntId.equals(prepayOcc.getPymAcntId()) == true) {
			if (isSkip == true) {
				return nBlivb01m13;
			}
		}
		
		oldPymAcntId = prepayOcc.getPymAcntId();
		billAmt = 0;
		
		String prepayBillSeqNo = cbillService.getBillSeqNo(billYymm, billCycl, getBillDt(), prepayOcc.getPymAcntId(), "00");

		CBillComm searchBill = new CBillComm();
		searchBill.setBillSeqNo(prepayBillSeqNo);
		searchBill.setSoId(soId);
		
		List<CBillComm> cBillCommList = nBlivb01m13JdbcService.getUnpaidBillAmt(searchBill);
		
		if (cBillCommList == null || cBillCommList.isEmpty() == true) {
			isSkip = true;
			return nBlivb01m13;
		}
		
		isSkip = false;
		billAmt = cBillCommList.get(0).getBillAmt() - cBillCommList.get(0).getRcptAmt();
		clogService.writeLog("남은 청구 대상금액 : " + billAmt);
		clogService.writeLog("선수금 잔액 : " + prepayOcc.getPrepayBal());
//		billCount = cBillCommList.size();
		
		nextPosition = 0;
		
		// 청구금액이 선수금잔액 보다 작은 경우.. 선수금이 청구금액보다 크다.
		if (billAmt <= prepayOcc.getPrepayBal()) {
			isSkip = true;
			// 청구금액을 청구적용금액에 할당
//			billAplyAmt = billAmt;
			billAmt = 0;

			for (int i = nextPosition; i < cBillCommList.size(); i++) {
				
				CBillComm billComm = cBillCommList.get(i);

				billComm.setAplyAmt(billComm.getBillAmt() - billComm.getAplyAmt());
				OverpayBillAply overpayBillAply = makeOverpayBillAply(prepayOcc, billComm);
				
				nBlivb01m13.addOverpayBillAply(overpayBillAply);
				
				PrepayOcc updatePrepayOcc = new PrepayOcc();
				updatePrepayOcc.setPrepayOccSeqNo(overpayBillAply.getPrepayOccSeqNo());
				updatePrepayOcc.setPrepayProcStat("2");
				updatePrepayOcc.setSoId(overpayBillAply.getSoId());
				
				nBlivb01m13.addUpdatePrepayOcc(updatePrepayOcc);
				
//				int val = processReceipt(overpayBillAply, nBlivb01m13);
//				
//				if (val == 1) {
//					continue;
//				}
				
			}
		} else {
			// 청구금액의 합계
			double sumBillAmt = 0;
			
			for (int i = nextPosition; i < cBillCommList.size(); i++) {
				CBillComm billComm = cBillCommList.get(i);
				
				sumBillAmt += (billComm.getBillAmt() - billComm.getAplyAmt());
				
				if (prepayOcc.getPrepayBal() - sumBillAmt >= 0) {
					OverpayBillAply overpayBillAply = makeOverpayBillAply(prepayOcc, billComm);
					
					nBlivb01m13.addOverpayBillAply(overpayBillAply);
					
					PrepayOcc updatePrepayOcc = new PrepayOcc();
					updatePrepayOcc.setPrepayOccSeqNo(overpayBillAply.getPrepayOccSeqNo());
					updatePrepayOcc.setPrepayProcStat("2");
					updatePrepayOcc.setSoId(overpayBillAply.getSoId());
					
					nBlivb01m13.addUpdatePrepayOcc(updatePrepayOcc);
					
//					int val = processReceipt(overpayBillAply, nBlivb01m13);
//					
//					// 이미 수납이 되어있는 경우
//					if (val == 1) {
//						continue;
//					}
				} else {
					billComm.setAplyAmt((billComm.getBillAmt() - billComm.getAplyAmt())
										- (sumBillAmt - prepayOcc.getPrepayBal()));
					
					OverpayBillAply overpayBillAply = makeOverpayBillAply(prepayOcc, billComm);
					
					nBlivb01m13.addOverpayBillAply(overpayBillAply);
					
					PrepayOcc updatePrepayOcc = new PrepayOcc();
					updatePrepayOcc.setPrepayOccSeqNo(overpayBillAply.getPrepayOccSeqNo());
					updatePrepayOcc.setPrepayProcStat("2");
					updatePrepayOcc.setSoId(overpayBillAply.getSoId());
					
					nBlivb01m13.addUpdatePrepayOcc(updatePrepayOcc);
					
//					int val = processReceipt(overpayBillAply, nBlivb01m13);
//					
//					// 이미 수납이 되어있는 경우
//					if (val == 1) {
//						continue;
//					}
					
					nextPosition = i;
				}
			}
			
//			billAplyAmt = prepayOcc.getPrepayBal();
			billAmt -= prepayOcc.getPrepayBal();
		}
		
		return nBlivb01m13;
	}
	
	private OverpayBillAply makeOverpayBillAply(PrepayOcc prepayOcc, CBillComm bill) {
		OverpayBillAply overpayBillAply = new OverpayBillAply();
		CUtil.copyObjectValue(prepayOcc, overpayBillAply);
		overpayBillAply.setPreSoId(prepayOcc.getSoId());
		overpayBillAply.setBillCrtDt(CUtil.utilGetDateTime(2));
		overpayBillAply.setBillSeqNo(bill.getBillSeqNo());
		overpayBillAply.setBillYymm(billYymm);
		overpayBillAply.setBillCycl(billCycl);
		overpayBillAply.setBillDt(getBillDt());
		overpayBillAply.setSoId(bill.getSoId());
		overpayBillAply.setBillMmTp(bill.getBillMmTp());
		overpayBillAply.setCustId(bill.getCustId());
		overpayBillAply.setCtrtId(bill.getCtrtId());
		overpayBillAply.setPrepayBal(prepayOcc.getPrepayBal());
		overpayBillAply.setBillAplyAmt(bill.getBillAmt());
		overpayBillAply.setPayProcYn("N");
		overpayBillAply.setRegDate(new Timestamp(new Date().getTime()));
		
		return overpayBillAply;
	}
	
	@SuppressWarnings("unused")
	private int processReceipt(OverpayBillAply overpayBillAply, NBlivb01m13 returnObj) {
		clogService.writeLog("선수금 수납 처리 시작");
		
        String billYymm = overpayBillAply.getBillYymm();
        String billCycl = overpayBillAply.getBillCycl();
        String billDt = overpayBillAply.getBillDt().substring(6, overpayBillAply.getBillDt().length());
        String pymAcntId = overpayBillAply.getPymAcntId();
        String soId = overpayBillAply.getSoId();
        
        String checkBillSeqNo = billYymm.substring(2, 6) + billCycl + billDt + pymAcntId;
        
        double payObjAmt = 0;
        
        OverpayBillAply check = new OverpayBillAply();
        check.setBillSeqNo(checkBillSeqNo);
        check.setSoId(soId);
        check.setBillYymm(billYymm);
        check.setBillCycl(billCycl);
        check.setPymAcntId(pymAcntId);
        
        List<OverpayBillAply> list = nBlivb01m13JdbcService.getOverpayBillInfo(check);
        
        for (int i = 0; i < list.size(); i++) {
        	OverpayBillAply oba = list.get(i);
        	
        	CBillComm searchBill = new CBillComm();
        	searchBill.setBillSeqNo(oba.getBillSeqNo());
        	searchBill.setSoId(oba.getSoId());
        	searchBill.setPymAcntId(oba.getPymAcntId());
        	
        	double rcptAmt = nBlivb01m13JdbcService.getRemainRcptAmtFromBillWrk(searchBill);
        	
        	// 이미 완납이 되어있다면 1을 리턴한다.
        	if (rcptAmt <= 0) {
        		return 1;
        	}
        	
			// TODO 시퀀스발급 처리
			String pymSeqNo = pyCommService.getPymSeqNo();
			String dpstSeqNo = pyCommService.getDpstSeqNo();
			String prepayTransSeqNo = pyCommService.getPrepayTransSeqNo();
        	
        	List<CBillComm> billWrkList = nBlivb01m13JdbcService.getBillWrkInfoForPrepay(searchBill);
        	
        	boolean isContinue = true;
        	
        	double prepayAplyAmt = 0;
        	double sumPayObjAmt = 0;
        	double sumPayAplyAmt = 0;
        	double preRcptAmt = 0;
        	rcptAmt = 0;
        	
        	CBillComm refBill = null;
        	
        	for (CBillComm cBillComm : billWrkList) {
        		if (refBill == null) {
        			refBill = cBillComm;
        		}
        		
        		// 선수금을 반영하려고 하는 청구내역의 지점ID, 청구대상월구분, 완납분은 청구금액과 이전수납액만 누적하고 Skip 처리함
        		if (isContinue == false || cBillComm.getSoId().equals(oba.getSoId()) == false
        				|| cBillComm.getBillMmTp().equals(oba.getBillMmTp()) == false
        				|| "N".equals(cBillComm.getFullPayYn()) == false) {
        			
        			ReceiptDetail receiptDetail = new ReceiptDetail();
        			CUtil.copyObjectValue(cBillComm, receiptDetail);
        			
        			receiptDetail.setPymSeqNo(pymSeqNo);
        			receiptDetail.setPreRcptAmt(cBillComm.getRcptAmt());
        			receiptDetail.setRcptAmt(0);
        			receiptDetail.setPreSoId(oba.getSoId());
        			
        			returnObj.addReceiptDetail(receiptDetail);
        			
        			sumPayAplyAmt += cBillComm.getBillAmt();
        			preRcptAmt += cBillComm.getRcptAmt();
        			
        			continue;

        		}
        		
        		// 청구내역의 청구금액과 선수금청구반영내역의내역의 선수금 계산.
        		if (cBillComm.getBillAmt() - cBillComm.getRcptAmt() >= oba.getBillAplyAmt()) {
        			payObjAmt = oba.getBillAplyAmt();
        			isContinue = false;
        			prepayAplyAmt = 0;
        		} else {
        			// 입금금액에서 청구금액을 빼고 남은 금액을 다시 입금금액으로 잡는다.
        			oba.setBillAplyAmt(cBillComm.getBillAmt() - cBillComm.getRcptAmt());
        			payObjAmt = oba.getBillAplyAmt();
        		}
        		
        		sumPayObjAmt += payObjAmt;
        		sumPayAplyAmt += cBillComm.getBillAmt();
        		preRcptAmt += cBillComm.getRcptAmt();
        		rcptAmt += payObjAmt;
        		
				CBillComm updateBill = new CBillComm();
				updateBill.setRcptAmt(payObjAmt);
				updateBill.setBillSeqNo(cBillComm.getBillSeqNo());
				updateBill.setUseYymm(cBillComm.getUseYymm());
				updateBill.setProdCmpsId(cBillComm.getProdCd());
				updateBill.setSvcCmpsId(cBillComm.getSvcCmpsId());
				updateBill.setChrgItmCd(cBillComm.getChrgItmCd());
				updateBill.setChgDate(new Timestamp(new Date().getTime()));

				returnObj.addUpdateBill(updateBill);
//				billDao.updateBillWrkRcptAmt(updateBill);
				
				ReceiptDetail receiptDetail = new ReceiptDetail();
				CUtil.copyObjectValue(cBillComm, receiptDetail);
				receiptDetail.setPymSeqNo(pymSeqNo);
				receiptDetail.setPreRcptAmt(cBillComm.getRcptAmt());
				receiptDetail.setRcptAmt(payObjAmt);
				receiptDetail.setPreSoId(oba.getSoId());
				
				returnObj.addReceiptDetail(receiptDetail);
        		
        	}
        	
        	// 처리한 청구내역데이터가 있다는 의미
        	if (refBill != null) {
        		Receipt receipt = new Receipt();
        		CUtil.copyObjectValue(refBill, receipt);
        		receipt.setPymAcntId(pymAcntId);
        		receipt.setPreRcptAmt(preRcptAmt);
        		receipt.setPayObjAmt(sumPayObjAmt);
        		receipt.setPayAplyAmt(sumPayAplyAmt);
        		receipt.setPrepayAplyAmt(prepayAplyAmt);
        		receipt.setRcptAmt(rcptAmt);
        		receipt.setDpstSeqNo(dpstSeqNo);
        		receipt.setPrepayTransSeqNo(prepayTransSeqNo);
        		receipt.setTransDt(overpayBillAply.getTransDt());
        		receipt.setDpstDt(overpayBillAply.getDpstDt());
        		
        		returnObj.addReceipt(receipt);
        		
        		OverpayBillAply updateOba = new OverpayBillAply();
        		updateOba.setBillSeqNo(oba.getBillSeqNo());
        		updateOba.setSoId(oba.getSoId());
        		updateOba.setBillMmTp(oba.getBillMmTp());
        		updateOba.setPrepayOccSeqNo(oba.getPrepayOccSeqNo());
        		
        		returnObj.addOverpayBill(updateOba);
        		
        		PrepayOcc updatePrepayOcc = new PrepayOcc();
        		updatePrepayOcc.setPrepayOccSeqNo(oba.getPrepayOccSeqNo());
        		updatePrepayOcc.setPrepayTransAmt(sumPayObjAmt);
        		updatePrepayOcc.setChgrId(user);
        		updatePrepayOcc.setChgDate(new Timestamp(new Date().getTime()));
        		
        		returnObj.addUpdatePrepayOccTrans(updatePrepayOcc);
        		
        		PrepayTransHistory prepayTransHistory = new PrepayTransHistory();
        		prepayTransHistory.setPrepayOccSeqNo(oba.getPrepayOccSeqNo());
        		prepayTransHistory.setPrepayTransSeqNo(prepayTransSeqNo);
        		prepayTransHistory.setTransProcDttm(CUtil.utilGetDateTime(1));
        		prepayTransHistory.setPrepayReplTp("01");
        		prepayTransHistory.setTransProcAmt(sumPayObjAmt);
        		prepayTransHistory.setProcMemo("Deducted By The Prepaid Amount");
        		prepayTransHistory.setWonReplProcAmt(sumPayAplyAmt);
        		prepayTransHistory.setCrncyCd(refBill.getCrncyCd());
        		prepayTransHistory.setExrate(refBill.getExrate());
        		prepayTransHistory.setExrateAplyDt(refBill.getExrateAplyDt());
        		prepayTransHistory.setRegrId(user);
        		prepayTransHistory.setRegDate(new Timestamp(new Date().getTime()));
        		prepayTransHistory.setDcsnProcStat("05");
        		prepayTransHistory.setCnclYn("N");
        		prepayTransHistory.setCnclDttm(null);
        		
        		returnObj.addPrepayTransHist(prepayTransHistory);
        		
        	}
        	
        }

        return 0;
		
	}

	@Override
	protected void write(List<NBlivb01m13> itemList) {
		List<OverpayBillAply> insertOverpayBillAplyList = new ArrayList<>();
		List<PrepayOcc> updatePrepayOccList = new ArrayList<>();
		List<Receipt> receiptList = new ArrayList<>();
		List<ReceiptDetail> receiptDetailList = new ArrayList<>();
		List<CBillComm> updateBillList = new ArrayList<>();
		List<OverpayBillAply> updateOverpayBillAplyList = new ArrayList<>();
		List<PrepayOcc> updatePrepayOccTransList = new ArrayList<>();
		List<PrepayTransHistory> insertPrepayTransHistList = new ArrayList<>();
		
		for (NBlivb01m13 nBlivb01m13 : itemList) {
			if (nBlivb01m13.getOverpayBillAplyList() != null) {
				insertOverpayBillAplyList.addAll(nBlivb01m13.getOverpayBillAplyList());
			}
			
			if (nBlivb01m13.getUpdatePrepayOccList() != null) {
				updatePrepayOccList.addAll(nBlivb01m13.getUpdatePrepayOccList());
			}
			
			if (nBlivb01m13.getReceiptList() != null) {
				receiptList.addAll(nBlivb01m13.getReceiptList());
			}
			
			if (nBlivb01m13.getReceiptDetailList() != null) {
				receiptDetailList.addAll(nBlivb01m13.getReceiptDetailList());
			}
			
			if (nBlivb01m13.getUpdateBillList() != null) {
				updateBillList.addAll(nBlivb01m13.getUpdateBillList());
			}
			
			if (nBlivb01m13.getOverpayBillList() != null) {
				updateOverpayBillAplyList.addAll(nBlivb01m13.getOverpayBillList());
			}
			
			if (nBlivb01m13.getUpdatePrepayOccTransList() != null) {
				updatePrepayOccTransList.addAll(nBlivb01m13.getUpdatePrepayOccTransList());
			}
			
			if (nBlivb01m13.getPrepayTransHistList() != null) {
				insertPrepayTransHistList.addAll(nBlivb01m13.getPrepayTransHistList());
			}
			
		}
		
		nBlivb01m13JdbcService.insertOverpayBillAply(insertOverpayBillAplyList);
		prepayService.updatePrepayOccProcStat(updatePrepayOccList);
		receiptService.insertReceipt(receiptList);
		receiptService.insertReceiptDetail(receiptDetailList);
		nBlivb01m13JdbcService.updateBillWrkRcptAmt(updateBillList);
		nBlivb01m13JdbcService.updateOverpayBillAply(updateOverpayBillAplyList);
		prepayService.updatePrepayOccTransAmt(updatePrepayOccTransList);
		prepayService.insertPrepayTransHistory(insertPrepayTransHistList);	
	}

	@Override
	protected RepeatStatus end() {
		// TODO Auto-generated method stub
		return null;
	}

}
