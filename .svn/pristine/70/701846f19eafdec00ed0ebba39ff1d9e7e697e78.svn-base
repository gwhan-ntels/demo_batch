package com.ntels.ccbs.batch.py.tasklet;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.entity.AmbgOcc;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.PaymentResult;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.service.DepositService;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PaymentService.ProcessPaymentCallback;

@Component("nBlpyc01m02JobProcessor")
@Scope("step")
public class NBlpyc01m02JobProcessor implements ItemProcessor<Deposit, PaymentResult>, StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private DepositService depositService;
	
	@Value("#{jobParameters['regrId']}")
	private String regrId;
	
	@Value("#{jobParameters['workYymm']}")
	private String workYymm;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		
	}

	@Override
	public PaymentResult process(Deposit deposit) throws Exception {
		
		// TODO ACNT_CARD_NO 복호화 로직 추가
		deposit.setRegrId(regrId);
		
		PaymentResult result = new PaymentResult();
		result.setBillSeqNo(deposit.getBillSeqNo());
		
		Deposit updateDeposit = new Deposit();
		updateDeposit.setDpstSeqNo(deposit.getDpstSeqNo());
		
		if (StringUtils.isEmpty(deposit.getBillYymm()) == true) {
			deposit.setBillYymm(workYymm);
		}
		
		if (StringUtils.isEmpty(deposit.getPymAcntId()) == true) {
			// 납부계정ID가 존재하지 않으면 불명금 발생내역에 등록한다.
			clogService.writeLog(String.format("불명금발생내역에 등록 DPST_SEQ_NO[%s] PYM_ACNT_ID[%s]"
					, deposit.getDpstSeqNo(), deposit.getPymAcntId()));
			result.setAmbgOcc(makeAmbgOccFromDeposit(deposit));
			updateDeposit.setAmbgTgtYn("Y");
		} else {

			String billSeqNo = deposit.getBillSeqNo();
			String dpstSeqNo = deposit.getDpstSeqNo();
			String pymSeqNo = pyCommService.getPymSeqNo();
			double dpstAmt = deposit.getDpstAmt();
			double billAmt = deposit.getBillAmt();
			
			result.setPymSeqNo(pymSeqNo);
			
			// 완납 혹은 과납의 경우
			if (deposit.getBillAmt() <= deposit.getDpstAmt()) {
				clogService.writeLog("완납");
				result.setFullPayYn("Y");
				updateDeposit.setPayProcYn("Y");
				updateDeposit.setPayProcDt(CUtil.utilGetDateTime(2));

				// 수냅내역 데이터 생성
				Receipt receipt = new Receipt();
				receipt.setPymSeqNo(pymSeqNo);
				receipt.setBillSeqNo(billSeqNo);
				receipt.setBillYymm(deposit.getBillYymm());
				receipt.setBillCycl(deposit.getBillCycl());
				receipt.setBillDt(deposit.getBillDt());
				receipt.setPymAcntId(deposit.getPymAcntId());
				receipt.setPayProcDt(CUtil.utilGetDateTime(2));
				receipt.setDpstProcDt(deposit.getDpstProcDt());
				receipt.setDpstDt(deposit.getDpstDt());
				receipt.setDpstCl(deposit.getDpstCl());
				receipt.setPreRcptAmt(deposit.getRcptAmt());
				receipt.setPayObjAmt(dpstAmt);
				receipt.setPayAplyAmt(billAmt);
				receipt.setPrepayAplyAmt(dpstAmt - billAmt);
				receipt.setPayTp("1");
				receipt.setDpstSeqNo(deposit.getDpstSeqNo());
				receipt.setPrepayTransSeqNo("");
				receipt.setAmbgTransSeqNo("");
				receipt.setAssrTransSeqNo("");
				receipt.setCrncyCd(deposit.getCrncyCd());
				receipt.setExrate(deposit.getExrate());
				receipt.setExrateAplyDt(deposit.getExrateAplyDt());
				receipt.setCnclYn(deposit.getCnclYn());
				receipt.setRegDate(new Timestamp(new Date().getTime()));
				receipt.setRcptAmt(deposit.getBillAmt());
				receipt.setTransDt(deposit.getTransDt());
				receipt.setSoId(deposit.getSoId());
				receipt.setRegrId(deposit.getRegrId());
				
				result.addReceipt(receipt);
				
				CBillComm updateBillMast = new CBillComm();
				updateBillMast.setRcptAmt(deposit.getBillAmt());
				updateBillMast.setFullPayYn("Y");
				updateBillMast.setBillSeqNo(deposit.getBillSeqNo());
				
				result.setUpdateBillMast(updateBillMast);
				
				CBillComm updateBill = new CBillComm();
				updateBill.setChgDate(new Timestamp(new Date().getTime()));
				updateBill.setBillSeqNo(deposit.getBillSeqNo());
				
				result.addUpdateBill(updateBill);

				updateDeposit.setPayProcDt(CUtil.utilGetDateTime(2));
				
				result.setDeposit(updateDeposit);
				
				if (dpstAmt - billAmt > 0) {
					clogService.writeLog("선수금 발생 : " + (dpstAmt - billAmt));
					PrepayOcc prepayOcc = makePrepayOccFromDeposit(deposit, dpstAmt - billAmt);
					prepayOcc.setPrepayOccTgtSeqNo(pymSeqNo);
					result.setPrepayOcc(prepayOcc);
					updateDeposit.setPrepayTgtYn("Y");
				}
				
			} else {
				
				final String paramDpstSeqNo = dpstSeqNo;
				
				result = paymentService.processPayment(billSeqNo, pymSeqNo, dpstAmt, new ProcessPaymentCallback() {
					
					@Override
					public Receipt getReceipt() {
						
						Deposit deposit = depositService.getDepositForRcpt(paramDpstSeqNo);
						
						Receipt receipt = new Receipt();
						receipt.setPymAcntId(deposit.getPymAcntId());
						receipt.setDpstProcDt(deposit.getDpstProcDt());
						receipt.setDpstDt(deposit.getDpstDt());
						receipt.setDpstCl(deposit.getDpstCl());
						receipt.setDpstSeqNo(deposit.getDpstSeqNo());
						receipt.setCrncyCd(deposit.getCrncyCd());
						receipt.setExrate(deposit.getExrate());
						receipt.setExrateAplyDt(deposit.getExrateAplyDt());
						receipt.setCnclYn(deposit.getCnclYn());
						receipt.setTransDt(deposit.getTransDt());
						receipt.setSoId(deposit.getSoId());
						receipt.setRegrId(deposit.getRegrId());
						receipt.setPayTp("1");
						return receipt;
					}
				});
				result.setFullPayYn("N");
			}
			
//			result = paymentService.processPayment(billSeqNo, pymSeqNo, dpstSeqNo, dpstAmt);
//			
//			if (result.getReceiptList().size() > 0) {
//				result.setDeposit(deposit);;
//			}
//			
//			if (result.getRemainAmt() > 0) {
//				result.setPrepayOcc(makePrepayOccFromDeposit(deposit, result.getRemainAmt()));
//			}
		}

		return result;
	}
	
	private AmbgOcc makeAmbgOccFromDeposit(Deposit deposit) {
		AmbgOcc ambgOcc = new AmbgOcc();
		
		ambgOcc.setAmbgOccSeqNo(pyCommService.getAmbgOccSeqNo());
		ambgOcc.setAmbgOccDttm(CUtil.utilGetDateTime(1));
		
		String ambgOccTp = "01";
		
		if (deposit.getDpstCl().equals("01") == true) {
			ambgOccTp = "02";
		} else if (deposit.getDpstCl().equals("02") == true) {
			ambgOccTp = "03";
		} else if (deposit.getDpstCl().equals("03") == true) {
			ambgOccTp = "04";
		} else if (deposit.getDpstCl().equals("05") == true) {
			ambgOccTp = "06";
		}
		
		ambgOcc.setAmbgOccTp(ambgOccTp);
		ambgOcc.setAmbgOccResn("1");
		ambgOcc.setDpstTpSeqNo(deposit.getDpstSeqNo());
		ambgOcc.setDpstDt(deposit.getDpstDt());
		ambgOcc.setDpstProcDttm(CUtil.utilGetDateTime(1));
		ambgOcc.setDpstCl(deposit.getDpstCl());
		ambgOcc.setDpstBnkAcntCd(deposit.getDpstBnkAcntCd());
		ambgOcc.setPayCorpCd(deposit.getPayCorpCd());
		// TODO 고객 카드번호 암호화 로직 추가하기!
		ambgOcc.setAcntCardNo(deposit.getAcntCardNo());
		ambgOcc.setAmbgProcStat("1");
		ambgOcc.setAmbgOccAmt(deposit.getDpstAmt());
		ambgOcc.setAmbgTransAmt(0);
		ambgOcc.setAmbgBal(deposit.getDpstAmt());
		ambgOcc.setTransCmplYn("N");
		ambgOcc.setWonAmbgOccAmt(deposit.getWonDpstAmt());
		ambgOcc.setCrncyCd(deposit.getCrncyCd());
		ambgOcc.setExrate(deposit.getExrate());
		ambgOcc.setExrateAplyDt(deposit.getExrateAplyDt());
		ambgOcc.setRegrId(deposit.getRegrId());
		ambgOcc.setRegDate(new Timestamp(new Date().getTime()));
		ambgOcc.setCnclYn("N");
		ambgOcc.setCnclDttm("");
		ambgOcc.setSoId("00");
		ambgOcc.setTransDt(deposit.getTransDt());
		ambgOcc.setOccSoId("00");
		
		return ambgOcc;
	}

	private PrepayOcc makePrepayOccFromDeposit(Deposit deposit, double prepayAmt) {
		PrepayOcc prepayOcc = new PrepayOcc();

		prepayOcc.setPrepayOccSeqNo(pyCommService.getPrepayOccSeqNo());
		prepayOcc.setPymAcntId(deposit.getPymAcntId());
		prepayOcc.setPrepayOccDttm(CUtil.utilGetDateTime(1));
		prepayOcc.setPrepayOccTp("1");
		prepayOcc.setPrepayOccResn("1");
		prepayOcc.setPrepayOccTgtSeqNo(deposit.getDpstSeqNo());
		prepayOcc.setDpstDt(deposit.getDpstDt());
		prepayOcc.setDpstProcDttm(CUtil.utilGetDateTime(1));
		prepayOcc.setDpstCl(deposit.getDpstCl());
		prepayOcc.setPrepayProcStat("1");
		prepayOcc.setPrepayOccAmt(prepayAmt);
		prepayOcc.setPrepayTransAmt(0.0);
		prepayOcc.setPrepayBal(prepayAmt);
		prepayOcc.setTransCmplYn("N");
		prepayOcc.setWonPrepayOccAmt(0.0);
		prepayOcc.setCrncyCd(deposit.getCrncyCd());
		prepayOcc.setExrate(deposit.getExrate());
		prepayOcc.setExrateAplyDt(deposit.getExrateAplyDt());
		prepayOcc.setRegrId(deposit.getRegrId());
		prepayOcc.setRegDate(new Timestamp(new Date().getTime()));
		prepayOcc.setCnclYn("N");
		prepayOcc.setCnclDttm("");
		prepayOcc.setSoId("00");
		prepayOcc.setTransDt(deposit.getTransDt());

		return prepayOcc;
	}

}
