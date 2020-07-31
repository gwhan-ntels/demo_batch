package com.ntels.ccbs.batch.py.tasklet;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.common.tasklet.CommonTasklet.BAT_PROC_STAT;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;
import com.ntels.ccbs.batch.py.service.AmbgJdbcService;
import com.ntels.ccbs.batch.py.service.AssrService;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PrepayService;
import com.ntels.ccbs.batch.py.service.ReceiptService;

@Component("nBlpyc03m02JobTasklet")
@Scope("step")
public class NBlpyc03m02JobTasklet implements Tasklet, StepExecutionListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private ClogService clogService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private AssrService assrService;
	
	@Autowired
	private AmbgJdbcService ambgService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CommonService commonService;
	
	@Value("#{jobParameters['pgmId']}")
	private String pgmId;
	
	@Value("#{jobParameters['logFileName']}")
	private String logFileName;
	
	@Value("#{jobParameters['grpId']}")
	private String grpId;
	
	@Value("#{jobParameters['pgmExeSeqNo']}")
	private String pgmExeSeqNo;
	
	@Value("#{jobParameters['workYymm']}")
	private String workYymm;
	
	@Value("#{jobParameters['billCycl']}")
	private String billCycl;
	
	@Value("#{jobParameters['user']}")
	private String user;
	
	@Value("#{jobParameters['soId']}")
	private String soId;
	
	@Value("#{jobParameters['cnclRsn']}")
	private String cnclRsn;
	
	@Value("#{jobParameters['pymSeqNo']}")
	private String pymSeqNo;
	
	private Common common;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// 로그를 종료함
		
		common.setReadCnt(stepExecution.getReadCount());
		common.setWriteCnt(stepExecution.getWriteCount());
		common.setErrCnt(0);
		common.setProcCnt(0);
		common.setCmplCnt(0);

		if( stepExecution.getStatus() .toString() == "COMPLETED"){
			common.setBatProcStat(BAT_PROC_STAT.CMPL.code());
			System.out.println("STATUS CHK 정상");
		} else{
			System.out.println("STATUS CHK 비정상");
			common.setBatProcStat(BAT_PROC_STAT.ERR.code());
		}
		
		commonService.commonUpdBatPgmLog(common);
		
		logger.debug("NBlpyb10m02JobTasklet.afterStep");
		clogService.close();
		return ExitStatus.COMPLETED;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// 배치 실행 로그 생성
		common = new Common();
		common.setLogFile(logFileName);
	    common.setGrpId(grpId);
		common.setBsYymm(workYymm);
		common.setBillYymm(workYymm);
		common.setBatPgmId(pgmId);
		common.setSoId(soId);
		common.setPgmExeSeqNo(pgmExeSeqNo);
		common.setBillCycl(billCycl);
		common.setBatProcStat(BAT_PROC_STAT.IN.code());
		common.setLogFilePath(common.LOG_LOCAL);;

		common.setReadCnt(stepExecution.getReadCount());
		common.setWriteCnt(stepExecution.getWriteCount());
		
		// 기존 로그가 존재하면
		// 1. 배치 진행 상태가 재요청일 경우
		//	-> 배치 진행 상태를 작업중으로 변경 후 배치 작업 진행
		// 2. 그 외의 경우
		//	-> 잘못된 접근으로 에러 발생
		if (commonService.batPgmLogCount(common) > 0) {
			String batProcStat = commonService.batProcStat(common);
			
			if (batProcStat.equals(BAT_PROC_STAT.RETRY.code()) == true) {
				commonService.updateBatProcStat(common);	
			} else {
				throw new RuntimeException("잘못된 작업 요청입니다. bat_proc_stat : " + batProcStat);
			}
		} else {
			commonService.commonInsBatPgmLog(common);	
		}
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		
		String cnclYn = receiptService.getCancelYn(pymSeqNo);
		
		if ("Y".equals(cnclYn) == true) {
			clogService.writeLog("Receipt had already been canceled. " + pymSeqNo);
			throw new RuntimeException("Receipt had already been canceled. " + pymSeqNo);
		}
		
		List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailList(null, pymSeqNo);
	
		if (receiptDetailList.isEmpty() == true) {
			throw new RuntimeException("Receipt detail not exists!!");
		}

		// 수납금액의 합
		double payObjAmt = 0.0;
		
		// 청구내역의 수납금액 취소
		for (ReceiptDetail receiptDetail : receiptDetailList) {
			payObjAmt += receiptDetail.getRcptAmt();

			if (receiptDetail.getRcptAmt() != 0) {
				
				Double billRcptAmt = paymentService.getBillRcptAmt(receiptDetail.getBillSeqNo()
									, receiptDetail.getUseYymm(), receiptDetail.getProdCmpsId()
									, receiptDetail.getSvcCmpsId(), receiptDetail.getChrgItmCd());
				
				billRcptAmt = billRcptAmt == null ? 0 : billRcptAmt;
				
				if (billRcptAmt < receiptDetail.getRcptAmt()) {
					throw new RuntimeException(
							String.format("Bill amount is less than the cancellation amount. Bill amount[%f],Receipt amount[%f]"
									, billRcptAmt, receiptDetail.getRcptAmt()));
				}

				CBillComm bill = new CBillComm();

				bill.setRcptAmt(receiptDetail.getRcptAmt());
				bill.setBillSeqNo(receiptDetail.getBillSeqNo());
				bill.setUseYymm(receiptDetail.getUseYymm());
				bill.setProdCmpsId(receiptDetail.getProdCmpsId());
				bill.setSvcCmpsId(receiptDetail.getSvcCmpsId());
				bill.setChrgItmCd(receiptDetail.getChrgItmCd());

				paymentService.updateBillCancel(bill);	
			}

		}

		// 수납일련변호로 수납취소 처리
		receiptService.updateReceiptCancel(null, pymSeqNo);
		
		Receipt receipt = receiptService.getReceipt(pymSeqNo);
		receipt.setPayObjAmt(receipt.getPayObjAmt() - receipt.getPrepayAplyAmt());
		
		CBillComm updateBillMast = new CBillComm();
		updateBillMast.setBillSeqNo(receipt.getBillSeqNo());
		updateBillMast.setRcptAmt(receipt.getRcptAmt());
		
		paymentService.updateBillMastCancel(updateBillMast);
		
		String payTp = receipt.getPayTp();
//		String soId = receipt.getSoId();
//		
//		PrepayOcc prepayOcc = null;
		
		// 수납코드 정의
		// 1: 정상수납, 2: 선수금수납, 3: 상이납자수납, 4: 미확인입금수납(불명금), 7: (-)매출수납, 9: 대체손각
		
		// 선수금수납의 경우
		if ("2".equals(payTp) == true) {
//			prepayOcc = prepayService.getPrepayDeposit(pymSeqNo);
		}
		
		if ("1".equals(payTp) == true || "3".equals(payTp) == true) {
//			prepayService.updatePrepayOccByPrepayTransSeqNo(receipt.getPrepayTransSeqNo());
			
			if (payObjAmt != 0.0) {
				// 선수금 발생내역에 등록
				String prepayOccSeqNo = pyCommService.getPrepayOccSeqNo();
				
				PrepayOcc newPrepayOcc = new PrepayOcc();
				newPrepayOcc.setPrepayOccSeqNo(prepayOccSeqNo);
				newPrepayOcc.setPymAcntId(receipt.getPymAcntId());
				newPrepayOcc.setPrepayOccDttm(CUtil.utilGetDateTime(1));
				newPrepayOcc.setPrepayOccTp("1");
				newPrepayOcc.setPrepayOccResn("5");
				newPrepayOcc.setPrepayOccTgtSeqNo(receipt.getPymSeqNo());
				newPrepayOcc.setDpstDt(receipt.getDpstDt());
				newPrepayOcc.setDpstProcDttm(receipt.getDpstProcDt());
				newPrepayOcc.setDpstCl(receipt.getDpstCl());
				newPrepayOcc.setPrepayProcStat("1");
				newPrepayOcc.setPrepayOccAmt(payObjAmt);
				newPrepayOcc.setPrepayTransAmt(0.0);
				newPrepayOcc.setPrepayBal(payObjAmt);
				newPrepayOcc.setTransCmplYn("N");
				newPrepayOcc.setWonPrepayOccAmt(payObjAmt);
				newPrepayOcc.setCrncyCd(receipt.getCrncyCd());
				newPrepayOcc.setExrate(receipt.getExrate());
				newPrepayOcc.setExrateAplyDt(receipt.getExrateAplyDt());
				newPrepayOcc.setRegrId(user);
				newPrepayOcc.setRegDate(new Timestamp(new Date().getTime()));
				newPrepayOcc.setCnclYn("N");
				newPrepayOcc.setCnclDttm("");
				newPrepayOcc.setChgDate(new Timestamp(new Date().getTime()));
				newPrepayOcc.setSoId(receipt.getSoId());
				newPrepayOcc.setTransDt(receipt.getTransDt());
				
				prepayService.insertPrepayOcc(newPrepayOcc);
			}
		} else if ("2".equals(payTp) == true) {
			prepayService.updatePrepayTransHistory(pymSeqNo, receipt.getPrepayTransSeqNo(), payObjAmt);
		} else if ("8".equals(payTp) == true) {
			assrService.updateAssrOcc(pymSeqNo, receipt.getAssrTransSeqNo(), payObjAmt);
		} else if ("4".equals(payTp) == true) {
			ambgService.updateAmbg(receipt.getDpstSeqNo(), receipt.getAmbgTransSeqNo(), payObjAmt);
		} else {
			clogService.writeLog(String.format("receipt type was wrong with the code value[%s]", payTp));
		}
		
		receiptService.insertReceiptCancelAppl(pymSeqNo, user);
		receiptService.insertReceiptCancel(cnclRsn, pymSeqNo, "0000000000", payTp);

		// 기존 pro*c소스를 컨버팅 했을 뿐.. 왜 이렇게 짜여져 있는지 전혀 이해할 수 없음..
		// 지금은 단지 컨버팅일 뿐이고 돌려볼 수 없으므로 그대로 하겠음..
//		if ("1".equals(payTp) == true || "2".equals(payTp) == true || "3".equals(payTp) == true 
//				|| "4".equals(payTp) == true || "8".equals(payTp) == true) {
////			updatePrepayOccByPrepayTransSeqNo
//			if ("1".equals(payTp) == true || "3".equals(payTp) == true) {
//				prepayService.updatePrepayOccByPrepayTransSeqNo(receipt.getPrepayTransSeqNo());
//			}
//			
//			if (payObjAmt != 0.0) {
//				// 선수금 발생내역에 등록
//				String prepayOccSeqNo = pyCommService.getPrepayOccSeqNo();
//				
//				PrepayOcc newPrepayOcc = new PrepayOcc();
//				newPrepayOcc.setPrepayOccSeqNo(prepayOccSeqNo);
//				newPrepayOcc.setPymAcntId(receipt.getPymAcntId());
//				newPrepayOcc.setPrepayOccDttm(CUtil.utilGetDateTime(1));
//				newPrepayOcc.setPrepayOccTp("1");
//				newPrepayOcc.setPrepayOccResn("5");
//				newPrepayOcc.setPrepayOccTgtSeqNo(receipt.getPymSeqNo());
//				newPrepayOcc.setDpstDt(receipt.getDpstDt());
//				newPrepayOcc.setDpstProcDttm(receipt.getDpstProcDt());
//				newPrepayOcc.setDpstCl(receipt.getDpstCl());
//				newPrepayOcc.setPrepayProcStat("1");
//				newPrepayOcc.setPrepayOccAmt(payObjAmt);
//				newPrepayOcc.setPrepayTransAmt(0);
//				newPrepayOcc.setPrepayBal(payObjAmt);
//				newPrepayOcc.setTransCmplYn("N");
//				newPrepayOcc.setWonPrepayOccAmt(payObjAmt);
//				newPrepayOcc.setCrncyCd(receipt.getCrncyCd());
//				newPrepayOcc.setExrate(receipt.getExrate());
//				newPrepayOcc.setExrateAplyDt(receipt.getExrateAplyDt());
//				newPrepayOcc.setRegrId(regrId);
//				newPrepayOcc.setRegDate(new Timestamp(new Date().getTime()));
//				newPrepayOcc.setCnclYn("N");
//				newPrepayOcc.setCnclDttm("");
//				newPrepayOcc.setChgDate(new Timestamp(new Date().getTime()));
//				newPrepayOcc.setSoId(receipt.getSoId());
//				newPrepayOcc.setTransDt(receipt.getTransDt());
//				
//				prepayService.insertPrepayOcc(newPrepayOcc);
//			}

			// 입금구분 22, 선수금처리상태 != 2, 선수금취소여부 != Y인 입금구분이 22인 경우만 처리
//			if (prepayOcc != null && payObjAmt != 0.0 && "Y".equals(prepayOcc.getCnclYn())
//					&& "22".equals(prepayOcc.getDpstCl()) == true && "2".equals(prepayOcc.getPrepayProcStat()) == false) {
//				prepayService.updatePrepayTransHistory(pymSeqNo, receipt.getPrepayTransSeqNo(), payObjAmt);
//			} else {
//				if (payObjAmt != 0.0) {
//					// 선수금 발생내역에 등록
//					String prepayOccSeqNo = pyCommService.getPrepayOccSeqNo();
//					
//					PrepayOcc newPrepayOcc = new PrepayOcc();
//					newPrepayOcc.setPrepayOccSeqNo(prepayOccSeqNo);
//					newPrepayOcc.setPymAcntId(receipt.getPymAcntId());
//					newPrepayOcc.setPrepayOccDttm(CUtil.utilGetDateTime(1));
//					newPrepayOcc.setPrepayOccTp("1");
//					newPrepayOcc.setPrepayOccResn("5");
//					newPrepayOcc.setPrepayOccTgtSeqNo(receipt.getPymSeqNo());
//					newPrepayOcc.setDpstDt(receipt.getDpstDt());
//					newPrepayOcc.setDpstProcDttm(receipt.getDpstProcDt());
//					newPrepayOcc.setDpstCl(receipt.getDpstCl());
//					newPrepayOcc.setPrepayProcStat("1");
//					newPrepayOcc.setPrepayOccAmt(payObjAmt);
//					newPrepayOcc.setPrepayTransAmt(0);
//					newPrepayOcc.setPrepayBal(payObjAmt);
//					newPrepayOcc.setTransCmplYn("N");
//					newPrepayOcc.setWonPrepayOccAmt(payObjAmt);
//					newPrepayOcc.setCrncyCd(receipt.getCrncyCd());
//					newPrepayOcc.setExrate(receipt.getExrate());
//					newPrepayOcc.setExrateAplyDt(receipt.getExrateAplyDt());
//					newPrepayOcc.setRegrId(regrId);
//					newPrepayOcc.setRegDate(new Timestamp(new Date().getTime()));
//					newPrepayOcc.setCnclYn("N");
//					newPrepayOcc.setCnclDttm("");
//					newPrepayOcc.setChgDate(new Timestamp(new Date().getTime()));
//					newPrepayOcc.setSoId(receipt.getSoId());
//					newPrepayOcc.setTransDt(receipt.getTransDt());
//					
//					prepayService.insertPrepayOcc(newPrepayOcc);
//				}
//			}
//			
//			assrService.updateAssrOccCancelByPymSeqNu(pymSeqNo);
//		} else if ("2".equals(payTp) == true) {
//			prepayService.updatePrepayTransHistory(pymSeqNo, receipt.getPrepayTransSeqNo(), payObjAmt);
//		} else if ("8".equals(payTp) == true) {
//			assrService.updateAssrOcc(pymSeqNo, receipt.getAssrTransSeqNo(), payObjAmt);
//		} else if ("4".equals(payTp) == true) {
//			ambgService.updateAmbg(receipt.getDpstSeqNo(), receipt.getAmbgTransSeqNo(), payObjAmt);
//		} else {
//			clogService.writeLog(String.format("receipt type was wrong with the code value[%s]", payTp));
//		}
//		
//		receiptService.insertReceiptCancelAppl(pymSeqNo, regrId);
//		receiptService.insertReceiptCancel(cnclResn, pymSeqNo, receiptId, payTp);
		
		return RepeatStatus.FINISHED;
	}

}
