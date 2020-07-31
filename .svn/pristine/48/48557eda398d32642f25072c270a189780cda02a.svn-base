package com.ntels.ccbs.batch.py.tasklet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.entity.AmbgOcc;
import com.ntels.ccbs.batch.py.entity.AmbgTransHistory;
import com.ntels.ccbs.batch.py.entity.PaymentResult;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.service.AmbgJdbcService;
import com.ntels.ccbs.batch.py.service.NBlpyd02m02Service;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PaymentService.ProcessPaymentCallback;
import com.ntels.ccbs.batch.py.service.PrepayService;
import com.ntels.ccbs.batch.py.service.ReceiptService;

/**
 * 불명금수납 처리
 * @author Cashyalla
 *
 */
@Component("nBlpyd02m02JobTasklet")
@Scope("step")
public class NBlpyd02m02JobTasklet implements Tasklet, StepExecutionListener {

	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private ClogService clogService;
	
	@Autowired
	private AmbgJdbcService ambgService;
	
	@Autowired
	private NBlpyd02m02Service nBlpyd02m02Service;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ReceiptService receiptService;
	
	// TODO 데이터 파싱으로 변경해야함..
	private String ambgOccSeqNo = "0000000004";
	
	// TODO 납부계정ID 혹은 청구년월
	private String[] dataArray;
	
	// TODO 데이터 파싱으로 처리
	// 구분 1 : 납부게정 2 : 청구년월
	private String flag = "1";
	
	// TODO 데이터 파싱으로 처리
	private double inAmbgBal = 20000;
	
	// TODO 데이터 파싱으로 처리
	private String regrId = "Tester";
	
	private String pymAcntId = "PS00086590";
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		clogService.writeLog("NBlpyd02m02JobTasklet.afterStep");
		clogService.writeAfterStepLog(stepExecution);
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		clogService.writeLog("NBlpyd02m02JobTasklet.beforeStep");
		dataArray = new String[] { "PS00086590" };
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

//		double prepayAplyAmt = 0.0;
		double exrate = 0.0;
		String exrateAplyDt = "";

		// 납부계정ID혹은 청구년월에 대한 처리
		for (int i = 0; i < 1; i++) {

			clogService.writeLog("불명금발생내역내역을 조회합니다");
			AmbgOcc ambgOcc = ambgService.getAmbgBal(ambgOccSeqNo);
			
			if (i == 0 && inAmbgBal != ambgOcc.getAmbgBal()) {
				clogService.writeLog("미확인잔액이 작업처리가 완료된 작업입니다.");
				return null;
			}
			
			String billSeqNo = null;
			
			if ("1".equals(flag)) {
				String pymAcntId = dataArray[i];
				int cnt = pyCommService.getPymAcntCnt(pymAcntId);
				
				if (cnt <= 0) {
					clogService.writeLog("[updateBill]PYM_ACNT_ID NOT FOUND");
				}
				
				List<String> billSeqNoList = nBlpyd02m02Service.getBillSeqByPymAcntId(pymAcntId);
				if (billSeqNoList.size() <= 0) {
					throw new RuntimeException("청구 내역이 없습니다.");
				}
				
				billSeqNo = billSeqNoList.get(0);
			} else {
				billSeqNo = dataArray[i];
			}
			
			String pymSeqNo = pyCommService.getPymSeqNo();
			
			PaymentResult result = paymentService.processPayment(billSeqNo, pymSeqNo, inAmbgBal, new ProcessPaymentCallback() {
				
				@Override
				public Receipt getReceipt() {
					
					final AmbgOcc ambgOcc = ambgService.getAmbgForAssr(ambgOccSeqNo);
					
					Receipt receipt = new Receipt();
					receipt.setDpstProcDt(ambgOcc.getDpstProcDttm().substring(0, 8));
					receipt.setDpstDt(ambgOcc.getDpstDt());
					receipt.setDpstCl("17");
					receipt.setDpstSeqNo(ambgOcc.getDpstTpSeqNo());
					receipt.setCrncyCd(ambgOcc.getCrncyCd());
					receipt.setExrate(ambgOcc.getExrate());
					receipt.setExrateAplyDt(ambgOcc.getExrateAplyDt());
					receipt.setCnclYn("N");
					receipt.setTransDt(ambgOcc.getTransDt());
					receipt.setSoId(ambgOcc.getSoId());
					receipt.setRegrId(ambgOcc.getRegrId());
					receipt.setPayTp("4");
					return receipt;
				}
			});
			
			inAmbgBal = result.getRemainAmt();
			Receipt receipt = result.getReceiptList().get(0);
			
			double transAmt = receipt.getRcptAmt();
			exrate = receipt.getExrate();
			exrateAplyDt = receipt.getExrateAplyDt();
			
			updateAmbgOccStat(transAmt, pymAcntId);
			String ambgTransSeqNo = "";
			
			if (transAmt > 0.0) {
				ambgTransSeqNo = insertAmbgTransHist(transAmt, "02", receipt.getExrate(), receipt.getExrateAplyDt());
			}
			
			List<CBillComm> updateBillMastList = new ArrayList<>();
			updateBillMastList.add(result.getUpdateBillMast());
			paymentService.updateBillMastRcptAmt(updateBillMastList);
			
			if ("Y".equals(result.getFullPayYn()) == true) {
				paymentService.updateFullPayBill(result.getUpdateBillList());
			} else {
				paymentService.updateBillRcptAmt(result.getUpdateBillList());	
			}
		
			receipt.setAmbgTransSeqNo(ambgTransSeqNo);
			receiptService.insertReceipt(result.getReceiptList());
			receiptService.insertReceiptDetail(result.getReceiptDetailList());
			
//			prepayAplyAmt = result.getRemainAmt();
		}
		
		if (inAmbgBal > 0.0) {
			updateAmbgOccStat(inAmbgBal, pymAcntId);
			insertAmbgTransHist(inAmbgBal, "05", exrate, exrateAplyDt);
			insertPrepayOcc(pymAcntId, inAmbgBal);
		}
		
		return null;
	}
	
	private int updateAmbgOccStat(double sumAmbgTransAmt, String pymAcntId) {
		String ambgProcStat = ambgService.getAmbgProcStat(ambgOccSeqNo);
		
		if ("2".equals(ambgProcStat) == true) {
			clogService.writeLog("미확인금처리상태가 미확인입금대체적용중이면 처리할 수 없습니다.");
			throw new RuntimeException("미확인금처리상태가 미확인입금대체적용중이면 처리할 수 없습니다.");
		}
		
		AmbgOcc ambgOcc = new AmbgOcc();
		ambgOcc.setAmbgOccSeqNo(ambgOccSeqNo);
		ambgOcc.setAmbgTransAmt(sumAmbgTransAmt);
		ambgOcc.setAmbgBal(sumAmbgTransAmt);
		ambgOcc.setPymAcntId(pymAcntId);
		ambgOcc.setChgrId(regrId);
		ambgOcc.setChgDate(new Timestamp(new Date().getTime()));
		
		return ambgService.updateAmbgOccByAmbgOccSeqNo(ambgOcc);
	}
	
	private String insertAmbgTransHist(double sumAmbgTransAmt, String ambgReplTp, double exrate, String exrateAplyDt) {
		
		String procMemo = "";
		
		if ("02".equals(ambgReplTp) == true) {
			procMemo = "미확인수납처리";
		} else {
			procMemo = "선수금발생";
		}

		AmbgOcc ambgAmount = ambgService.getAmbgBal(ambgOccSeqNo);
		
		AmbgTransHistory ambgTransHistory = new AmbgTransHistory();
		ambgTransHistory.setAmbgOccSeqNo(ambgOccSeqNo);
		ambgTransHistory.setAmbgTransSeqNo(pyCommService.getAmbgTransSeqNo());
		ambgTransHistory.setTransProcDttm(CUtil.utilGetDateTime(1));
		ambgTransHistory.setAmbgReplTp(ambgReplTp);
		ambgTransHistory.setTransProcAmt(sumAmbgTransAmt);
		ambgTransHistory.setProcMemo(procMemo);
		ambgTransHistory.setWonReplProcAmt(sumAmbgTransAmt);
		ambgTransHistory.setCrncyCd("KRW");
		ambgTransHistory.setExrate(exrate);
		ambgTransHistory.setExrateAplyDt(exrateAplyDt);
		ambgTransHistory.setRegrId(regrId);
		ambgTransHistory.setRegDate(new Timestamp(new Date().getTime()));
		ambgTransHistory.setApprReqrId(regrId);
		ambgTransHistory.setApprReqDttm(CUtil.utilGetDateTime(1));
		ambgTransHistory.setDcsnProcStat("01");
		ambgTransHistory.setCnclYn("N");
		ambgTransHistory.setCnclDttm("");
		ambgTransHistory.setBalAmt(ambgAmount.getAmbgOccAmt() - ambgAmount.getAmbgTransAmt());
		
		ambgService.insertAmbgTransHistory(ambgTransHistory);
		
		return ambgTransHistory.getAmbgTransSeqNo();
	}
	
	private int insertPrepayOcc(String pymAcntId, double prepayAmt) {
		PrepayOcc prepayOcc = new PrepayOcc();
		
		AmbgOcc ambgOcc = ambgService.getAmbgForAssr(ambgOccSeqNo);
		
		prepayOcc.setPrepayOccSeqNo(pyCommService.getPrepayOccSeqNo());
		prepayOcc.setPymAcntId(pymAcntId);
		prepayOcc.setPrepayOccDttm(CUtil.utilGetDateTime(1));
		prepayOcc.setPrepayOccTp("2");
		prepayOcc.setPrepayOccResn("1");
		prepayOcc.setPrepayOccTgtSeqNo(ambgOccSeqNo);
		prepayOcc.setDpstDt(ambgOcc.getDpstDt());
		prepayOcc.setDpstProcDttm(ambgOcc.getDpstProcDttm());
		prepayOcc.setDpstCl(ambgOcc.getDpstCl());
		prepayOcc.setPrepayProcStat("1");
		prepayOcc.setPrepayOccAmt(prepayAmt);
		prepayOcc.setPrepayTransAmt(0.0);
		prepayOcc.setPrepayBal(prepayAmt);
		prepayOcc.setTransCmplYn("N");
		prepayOcc.setWonPrepayOccAmt(prepayAmt);
		prepayOcc.setCrncyCd(ambgOcc.getCrncyCd());
		prepayOcc.setExrate(ambgOcc.getExrate());
		prepayOcc.setExrateAplyDt(ambgOcc.getExrateAplyDt());
		prepayOcc.setRegrId(regrId);
		prepayOcc.setRegDate(new Timestamp(new Date().getTime()));
		prepayOcc.setCnclYn("N");
		prepayOcc.setCnclDttm("");
		prepayOcc.setSoId("00");
		prepayOcc.setTransDt(ambgOcc.getTransDt());
		
		return prepayService.insertPrepayOcc(prepayOcc);
	}

}
