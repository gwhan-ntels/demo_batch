package com.ntels.ccbs.batch.py.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.dao.PaymentDao;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.PaymentResult;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

@Service
public class PaymentServiceImpl extends BaseService implements PaymentService {
	
	@Autowired
	private ClogService clogService;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Override
	public PaymentResult processPayment(String billSeqNo, String pymSeqNo, double dpstAmt, ProcessPaymentCallback callback) {
		PaymentResult paymentResult = new PaymentResult();
		
		// 완납여부 체크를 하기위해 청구 마스터 테이블에서 납부 대상금액(미납금액)을 조회한다.
		Double unpayAmt = paymentDao.getUnpayAmtFromBillMast(billSeqNo);
		double remainAmt = dpstAmt - unpayAmt;
		paymentResult.setRemainAmt(remainAmt);
		
		double payAmt = 0;
		
		clogService.writeLog("");
		clogService.writeLog("==============================");
		clogService.writeLog(String.format(" billSeqNo %s에 대한 수납처리 시작! 입금금액 : %f, 미납금액 : %f"
				, billSeqNo, dpstAmt, unpayAmt));
		
		String pymAcntId = null;
		
		if (unpayAmt <= dpstAmt) {
			paymentResult.setFullPayYn("Y");
			// 입금 금액이 납부 대상금액보다 크거나 같다면 완납조건은 성립한다.
			clogService.writeLog("입금금액이 수납대상금액보다 같거나 크므로 완납.");
			payAmt = unpayAmt;
			
			// 수납내역에 등록
			// 수냅내역에 등록하기 위한 청구내역 조회
			clogService.writeLog("수납내역에 등록하기위한 청구내역 조회 billSeqNo : " + billSeqNo);
			List<CBillComm> billList = paymentDao.getBillListByBillSeqNo(billSeqNo);
			clogService.writeLog("수납내역에 등록하기위한 청구내역 조회 : " + billList.size());

			double sumPreRcptAmt = 0;
			double sumBillAmt = 0;
			double sumRcptAmt = 0;
			// 선수금 발생 금액
			double prepayAmt = dpstAmt - unpayAmt;
			
			String billYymm = null;
			String billCycl = null;
			String billDt = null;
			
			for (CBillComm bill : billList) {
				ReceiptDetail receiptDetail = new ReceiptDetail();
				receiptDetail.setPymSeqNo(pymSeqNo);
				receiptDetail.setProdCmpsId(bill.getProdCmpsId());
				receiptDetail.setSvcCmpsId(bill.getSvcCmpsId());
				receiptDetail.setChrgItmCd(bill.getChrgItmCd());
				receiptDetail.setBillAmt(bill.getBillAmt());
				receiptDetail.setPreRcptAmt(bill.getRcptAmt());
				receiptDetail.setRcptAmt(bill.getBillAmt());
				receiptDetail.setPreSoId(bill.getSoId());
				receiptDetail.setSoId(bill.getSoId());
				receiptDetail.setGrpId(bill.getGrpId());
				receiptDetail.setCustId(bill.getCustId());
				receiptDetail.setCtrtId(bill.getCtrtId());
				receiptDetail.setCrncyCd(bill.getCrncyCd());
				receiptDetail.setExrate(bill.getExrate());
				receiptDetail.setExrateAplyDt(bill.getExrateAplyDt());
				receiptDetail.setUseYymm(bill.getUseYymm());
				receiptDetail.setBillSeqNo(bill.getBillSeqNo());
				receiptDetail.setRegDate(now());

				paymentResult.addReceiptDetail(receiptDetail);

				sumBillAmt += bill.getBillAmt();
				sumRcptAmt += bill.getBillAmt();
				sumPreRcptAmt += bill.getRcptAmt();
				
				if (billYymm == null) {
					billYymm = bill.getBillYymm();
					billCycl = bill.getBillCycl();
					billDt = bill.getBillDt();
					pymAcntId = bill.getPymAcntId();
				}
			}
			
			// TBLIV_BILL테이블에 완납된 청구번호에 해당하는 모든 데이터를 완납처리함
			CBillComm updateBill = new CBillComm();
			updateBill.setBillSeqNo(billSeqNo);
			updateBill.setChgDate(now());
			paymentResult.addUpdateBill(updateBill);
			
			// 수납내역 등록
			Receipt receipt = callback.getReceipt();
			
			if (receipt.getPymAcntId() == null) {
				receipt.setPymAcntId(pymAcntId);
			}
			
			receipt.setPymSeqNo(pymSeqNo);
			receipt.setBillSeqNo(billSeqNo);
			receipt.setBillYymm(billYymm);
			receipt.setBillCycl(billCycl);
			receipt.setBillDt(billDt);
			receipt.setPayProcDt(getDt());
			receipt.setPreRcptAmt(sumPreRcptAmt);
			receipt.setPayObjAmt(dpstAmt);
			receipt.setPayAplyAmt(sumBillAmt);
			receipt.setPrepayAplyAmt(prepayAmt);
//			receipt.setPrepayTransSeqNo("");
//			receipt.setAmbgTransSeqNo("");
//			receipt.setAssrTransSeqNo("");
			receipt.setRegDate(now());
			receipt.setRcptAmt(sumRcptAmt);
			receipt.setCnclYn("N");
			
			paymentResult.addReceipt(receipt);
		} else {
			paymentResult.setFullPayYn("N");
			
			List<CBillComm> billList = paymentDao.getBillListByBillSeqNo(billSeqNo);
			
			if (billList.isEmpty() == true) {
				throw new RuntimeException();
			}
			
			payAmt = dpstAmt;
			
//			double payObjAmt = dpstAmt;
			double sumBillAmt = 0;
			double sumRcptAmt = 0;
			double sumPreRcptAmt = 0;
			
			String billYymm = null;
			String billCycl = null;
			String billDt = null;
			
			for (CBillComm bill : billList) {

				// 이미 완납이 된 청구이거나 입금금액이 더이상 남아있지 않다면 패스!
				if ("Y".equals(bill.getFullPayYn()) == true || dpstAmt <= 0) {
					continue;
				}
				
				clogService.writeLog("");
				
				// 청구금액
				double billAmt = bill.getBillAmt();
				// 이전 수납금액
//				double preRcptAmt = bill.getRcptAmt();
				// 수납금액
				double rcptAmt = 0;
				
				if (billAmt < 0) {
					// 청구금액이 0보다 작은 경우에는.. 
					rcptAmt = billAmt;
					dpstAmt += (-1) * billAmt;
				} else if (billAmt == dpstAmt) {
					// 청구금액과 입금금액이 같다면 남은 입금금액은 없다.
					rcptAmt = billAmt;
					dpstAmt = 0;
				} else if (billAmt > dpstAmt) {
					rcptAmt = dpstAmt;
					dpstAmt = 0;
				} else if (billAmt < dpstAmt) {
					rcptAmt = billAmt;
					dpstAmt = dpstAmt - billAmt;
				}
				
				clogService.writeLog(String.format(" BillSeqNo : %s, ChrgItmCd : %s, ProdCmpsId : %s, SvcCmpsId : %s"
						, bill.getBillSeqNo(), bill.getChrgItmCd(), bill.getProdCmpsId(), bill.getSvcCmpsId()));
				clogService.writeLog(" dpstAmt : " + dpstAmt);
				clogService.writeLog(" rcptAmt : " + rcptAmt);
				clogService.writeLog(" billAmt : " + billAmt);
				
				CBillComm updateBill = new CBillComm();
				updateBill.setRcptAmt(rcptAmt);
				updateBill.setChgDate(now());
				updateBill.setBillSeqNo(bill.getBillSeqNo());
				updateBill.setProdCmpsId(bill.getProdCmpsId());
				updateBill.setSvcCmpsId(bill.getSvcCmpsId());
				updateBill.setChrgItmCd(bill.getChrgItmCd());

				paymentResult.addUpdateBill(updateBill);

				// 수납 상세내역에 등록
				ReceiptDetail receiptDetail = new ReceiptDetail();
				receiptDetail.setPymSeqNo(pymSeqNo);
				receiptDetail.setProdCmpsId(bill.getProdCmpsId());
				receiptDetail.setSvcCmpsId(bill.getSvcCmpsId());
				receiptDetail.setChrgItmCd(bill.getChrgItmCd());
				receiptDetail.setBillAmt(bill.getBillAmt());
				receiptDetail.setPreRcptAmt(bill.getRcptAmt());
				receiptDetail.setRcptAmt(rcptAmt);
				receiptDetail.setPreSoId(bill.getSoId());
				receiptDetail.setSoId(bill.getSoId());
				receiptDetail.setGrpId(bill.getGrpId());
				receiptDetail.setCustId(bill.getCustId());
				receiptDetail.setCtrtId(bill.getCtrtId());
				receiptDetail.setCrncyCd(bill.getCrncyCd());
				receiptDetail.setExrate(bill.getExrate());
				receiptDetail.setExrateAplyDt(bill.getExrateAplyDt());
				receiptDetail.setUseYymm(bill.getUseYymm());
				receiptDetail.setBillSeqNo(bill.getBillSeqNo());
				receiptDetail.setRegDate(now());

				paymentResult.addReceiptDetail(receiptDetail);

				sumPreRcptAmt += bill.getRcptAmt();
				sumRcptAmt += rcptAmt;
				sumBillAmt += bill.getBillAmt();
				
				if (billYymm == null) {
					billYymm = bill.getBillYymm();
					billCycl = bill.getBillCycl();
					billDt = bill.getBillDt();
					pymAcntId = bill.getPymAcntId();
				}

			}
			
			// 수납내역 등록
			Receipt receipt = callback.getReceipt();
			
			if (receipt.getPymAcntId() == null) {
				receipt.setPymAcntId(pymAcntId);
			}
			
			receipt.setPymSeqNo(pymSeqNo);
			receipt.setBillSeqNo(billSeqNo);
			receipt.setBillYymm(billYymm);
			receipt.setBillCycl(billCycl);
			receipt.setBillDt(billDt);
			receipt.setPayProcDt(getDt());
			receipt.setPreRcptAmt(sumPreRcptAmt);
			receipt.setPayObjAmt(dpstAmt);
			receipt.setPayAplyAmt(sumBillAmt);
			receipt.setPrepayAplyAmt(0);
			receipt.setPrepayTransSeqNo("");
			receipt.setAmbgTransSeqNo("");
			receipt.setAssrTransSeqNo("");
			receipt.setRegDate(now());
			receipt.setRcptAmt(sumRcptAmt);
			receipt.setCnclYn("N");
			
			paymentResult.addReceipt(receipt);
		}

		CBillComm updateBill = new CBillComm();
		updateBill.setBillSeqNo(billSeqNo);
		updateBill.setRcptAmt(payAmt);
		updateBill.setChgDate(now());
		updateBill.setFullPayYn(paymentResult.getFullPayYn());
		paymentResult.setUpdateBillMast(updateBill);
		
		paymentResult.setPymAcntId(pymAcntId);

//		clogService.writeLog("수납처리 완료");
//		clogService.writeLog("결과 오브젝트 출력 : " + ToStringBuilder.reflectionToString(paymentResult, ToStringStyle.MULTI_LINE_STYLE));
//		clogService.writeLog("==============================");
		
		return paymentResult;
	}

	@Override
	public Double getBillRcptAmt(String billSeqNo, String useYymm, String prodCmpsId, String svcCmpsId, String chrgItmCd) {
		CBillComm bill = new CBillComm();
		bill.setBillSeqNo(billSeqNo);
		bill.setUseYymm(useYymm);
		bill.setProdCmpsId(prodCmpsId);
		bill.setSvcCmpsId(svcCmpsId);
		bill.setChrgItmCd(chrgItmCd);
		
		return paymentDao.getBillRcptAmt(bill);
	}
	
	@Override
	public int insertRcptDtlSelectBill(List<ReceiptDetail> receiptDetailList) {
		return paymentDao.insertRcptDtlSelectBill(receiptDetailList);
	}
	
	@Override
	public int updateBillMastRcptAmt(List<CBillComm> billList) {
		return paymentDao.updateBillMastRcptAmt(billList);
	}
	
	@Override
	public int updateFullPayBill(CBillComm bill) {
		return paymentDao.updateFullPayBill(bill);
	}
	
	@Override
	public int updateFullPayBill(List<CBillComm> billList) {
		return paymentDao.updateFullPayBill(billList);
	}
	
	@Override
	public int updateBillRcptAmt(CBillComm bill) {
		return paymentDao.updateBillRcptAmt(bill);
	}
	
	@Override
	public int updateBillRcptAmt(List<CBillComm> billList) {
		return paymentDao.updateBillRcptAmt(billList);
	}
	
	@Override
	public int updateDpstProc(Deposit deposit) {
		return paymentDao.updateDpstProc(deposit);
	}
	
	@Override
	public int updateDpstProc(List<Deposit> depositList) {
		return paymentDao.updateDpstProc(depositList);
	}
	
	/**
	 * 입금취소를 할 때 참조했던 청구내역의 금액과 완납여부를 갱신한다.
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillCancel(CBillComm bill) {
		return paymentDao.updateBillCancel(bill);
	}
	
	@Override
	public int updateBillMastCancel(CBillComm bill) {
		return paymentDao.updateBillMastCancel(bill);
	}
	
	@Override
	public int updateBillMastCancel(List<CBillComm> billList) {
		return paymentDao.updateBillMastCancel(billList);
	}

	
}
