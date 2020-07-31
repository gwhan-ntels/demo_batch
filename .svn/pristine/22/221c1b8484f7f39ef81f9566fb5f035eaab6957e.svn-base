package com.ntels.ccbs.batch.py.tasklet;

import java.util.ArrayList;
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

import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.common.tasklet.CommonTasklet.BAT_PROC_STAT;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;
import com.ntels.ccbs.batch.py.service.AmbgJdbcService;
import com.ntels.ccbs.batch.py.service.AssrService;
import com.ntels.ccbs.batch.py.service.DepositService;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PrepayService;
import com.ntels.ccbs.batch.py.service.ReceiptService;

@Component("nBlpyb10m02JobTasklet")
@Scope("step")
public class NBlpyb10m02JobTasklet implements Tasklet, StepExecutionListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private AmbgJdbcService ambgService;
	
	@Autowired
	private AssrService assrService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ClogService clogService;
	
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
	
	@Value("#{jobParameters['dpstSeqNo']}")
	private String dpstSeqNo;
	
	//@Value
	// TODO NBlpyb02m02JobTasket Arg데이터를 JobLauncher로부터 받아오도록 변경해야함.
//	private String data = "0136BLPYB09M02_0_99999_1002260061_201605_01_ccbs_10_그냥취소하겠음!_0000000000_여기는 무슨 데이터인지 모르겠음_1_0000000000";
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
		
//		PyCommEntity pyCommEntity = pyCommService.parseArgData(data);
		
		String regrId = user;
//		String ifChk = pyCommEntity.getData()[3];
//		String orgId = pyCommEntity.getData()[4];

		// TBLPY_DPST테이블에서 취소처리
		Deposit deposit = depositService.updateCancelDeposit(dpstSeqNo);

		boolean existReceiptData = true;
		
		int insertCount = depositService.insertDepositCancelInfo(regrId, cnclRsn, dpstSeqNo);
		
		if (insertCount <= 0) {
			// 취소내역이 등록되지 않았음!!
		}
		
		if (deposit.getDpstTp().equals("4") == true) {
			// 선납계약정보에서 입급취소처리
			// TODO TCMBL_PREPAY_CTRT에서 금액을 조회한 후 prepdAplyAmt or aplDgr이 0보다 크면
			// 이미 적용중인 선납계약의 입금이므로 취소불가 처리를 하고
			// 그렇지 않은 경우에는 갱신처리를 해주어야함. 하지만 현재 TCMBL_PREPAY_CTRT테이블이 제거되어 해당 작업을 수행할 수 없음
			
			// 선납입금정보에 입금취소처리
			prepayService.updatePrepayCancel(regrId, dpstSeqNo);
			
			// 종료
			return RepeatStatus.FINISHED;
		}
		
		if (deposit.getPayProcYn().equals("N") == true) {
			// 미확인발생내역의 취소처리
			ambgService.updateAmbgCancel(dpstSeqNo);
		} else if (deposit.getPayProcYn().equals("Y") == true) {
			// 수납 상세내역 조회
			List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailList(dpstSeqNo, null);

			// 청구내역의 수납금액,완납여부를 수정한다.
			for (ReceiptDetail receiptDetail : receiptDetailList) {

				CBillComm bill = new CBillComm();
				
				bill.setRcptAmt(receiptDetail.getRcptAmt());
				bill.setBillSeqNo(receiptDetail.getBillSeqNo());
				bill.setUseYymm(receiptDetail.getUseYymm());
				bill.setProdCmpsId(receiptDetail.getProdCmpsId());
				bill.setSvcCmpsId(receiptDetail.getSvcCmpsId());
				bill.setChrgItmCd(receiptDetail.getChrgItmCd());

				paymentService.updateBillCancel(bill);
				
			}
			
			List<Receipt> billInfoReceiptList = receiptService.getReceiptBillInfo(dpstSeqNo);
			
			// 수납내역의 데이터를 바탕으로 TBLIV_BILL_MAST테이블의 데이터를 수정한다.
			List<CBillComm> updateBillMastList = new ArrayList<>();
			for (Receipt receipt : billInfoReceiptList) {
				CBillComm bill = new CBillComm();
				bill.setBillSeqNo(receipt.getBillSeqNo());
				bill.setRcptAmt(receipt.getRcptAmt());
				
				updateBillMastList.add(bill);
			}

			paymentService.updateBillMastCancel(updateBillMastList);

			// 수납내역의 취소여부('Y')를 수정한다.
			receiptService.updateReceiptCancel(dpstSeqNo, null);
			
//			String dpstCl = receiptService.getCanceledDpstCl(dpstSeqNo);
			
//			if (dpstCl.equals("11") == true && ifChk.equals("1") == true) {
//				pyCommService.insertBondRcpt(orgId, ifChk, dpstSeqNo, null, regrId);
//			}
			
			if (billInfoReceiptList.isEmpty() == true) {
				existReceiptData = false;
			} else {
				existReceiptData = true;
			}

			for (Receipt receipt : billInfoReceiptList) {
				// 선수금발생내역를 수정한다.
				prepayService.updatePrepayOccCancel(receipt.getPymSeqNo());
				
				// 보증금발생내역를 수정한다.
				assrService.updateAssrOccCancel(dpstSeqNo);
			}
		}
		
		// TODO 뭔가 찜찜한 로직이므로 나중에 다시한번 체크하자!
		// 입금에서 바로 선수금으로 간 경우의 선수금발생내역를 수정한다.
		if (existReceiptData == false) {
			prepayService.updatePrepayOccCancel(dpstSeqNo);
		}
		
		return RepeatStatus.FINISHED;
	}

}
