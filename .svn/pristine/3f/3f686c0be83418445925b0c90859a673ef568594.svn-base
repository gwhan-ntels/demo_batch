package com.ntels.ccbs.batch.py.tasklet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.ntels.ccbs.batch.py.common.entity.ExrateInfo;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.EachDeposit;
import com.ntels.ccbs.batch.py.entity.PaymentResult;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.service.AmbgJdbcService;
import com.ntels.ccbs.batch.py.service.DepositService;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PaymentService.ProcessPaymentCallback;
import com.ntels.ccbs.batch.py.service.PrepayService;
import com.ntels.ccbs.batch.py.service.ReceiptService;

/**
 * 건별입금등록 처리
 * @author Cashyalla
 *
 */
@Component("nBlpyb09m02JobTasklet")
@Scope("step")
public class NBlpyb09m02JobTasklet implements Tasklet, StepExecutionListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClogService clogService;

	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private AmbgJdbcService ambgService;
	
	@Value("#{jobParameters['pakcageName']}")
	private String pakcageName;

	@Value("#{jobParameters['className']}")
	private String className;

	@Value("#{jobParameters['execDate']}")
	private String execDate;

	@Value("#{jobParameters['pgmId']}")
	private String pgmId;

	@Value("#{jobParameters['recordData']}")
	private String recordData;

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

	@Value("#{jobParameters['eachDpstSeq']}")
	private String eachDpstSeq;

	@Value("#{jobParameters['dpstCl']}")
	private String dpstCl;

	@Value("#{jobParameters['cashDpstCl']}")
	private String cashDpstCl;

	@Value("#{jobParameters['dpstDt']}")
	private String dpstDt;

	@Value("#{jobParameters['dpstAmt']}")
	private Double dpstAmt;

	@Value("#{jobParameters['dpstFeeTp']}")
	private String dpstFeeTp;

	@Value("#{jobParameters['pymAcntId']}")
	private String pymAcntId;

	@Value("#{jobParameters['smry']}")
	private String smry;

	@Value("#{jobParameters['dpstBnkAcntId']}")
	private String dpstBnkAcntId;

	@Value("#{jobParameters['transDt']}")
	private String transDt;

	@Value("#{jobParameters['acntCardNo']}")
	private String acntCardNo;

	@Value("#{jobParameters['rcptEmpId']}")
	private String rcptEmpId;

	@Value("#{jobParameters['rcptBillEmpId']}")
	private String rcptBillEmpId;

	@Value("#{jobParameters['ifChk']}")
	private String ifChk;

	@Value("#{jobParameters['orgId']}")
	private String orgId;

	@Value("#{jobParameters['billInfoArray']}")
	private String billInfoArray;
	
	@Value("#{jobParameters['logFileName']}")
	private String logFileName;
	
	@Autowired
	private CommonService commonService;
	
	private Common common;
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		
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
		
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		clogService.writeLog("[process09m02]===========================");
		clogService.writeLog("[process09m02] 1. Add Single Deposit   & Receipt Process    " );
		clogService.writeLog("[process09m02]===========================" );
		
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
		
		logger.info("pakcageName : {}", pakcageName);
		logger.info("className : {}", className);
		logger.info("execDate : {}", execDate);
		logger.info("pgmId : {}", pgmId);
		logger.info("recordData : {}", recordData);
		logger.info("grpId : {}", grpId);
		logger.info("pgmExeSeqNo : {}", pgmExeSeqNo);
		logger.info("workYymm : {}", workYymm);
		logger.info("billCycl : {}", billCycl);
		logger.info("user : {}", user);
		logger.info("soId : {}", soId);
		logger.info("eachDpstSeq : {}", eachDpstSeq);
		logger.info("dpstCl : {}", dpstCl);
		logger.info("cashDpstCl : {}", cashDpstCl);
		logger.info("dpstDt : {}", dpstDt);
		logger.info("dpstAmt : {}", dpstAmt);
		logger.info("dpstFeeTp : {}", dpstFeeTp);
		logger.info("pymAcntId : {}", pymAcntId);
		logger.info("smry : {}", smry);
		logger.info("dpstBnkAcntId : {}", dpstBnkAcntId);
		logger.info("transDt : {}", transDt);
		logger.info("acntCardNo : {}", acntCardNo);
		logger.info("rcptEmpId : {}", rcptEmpId);
		logger.info("rcptBillEmpId : {}", rcptBillEmpId);
		logger.info("ifChk : {}", ifChk);
		logger.info("orgId : {}", orgId);
		logger.info("billInfoArray : {}", billInfoArray);
		
		EachDeposit eachDeposit = new EachDeposit();
		eachDeposit.setEachDpstSeq(eachDpstSeq);
		eachDeposit.setDpstCl(dpstCl);
		eachDeposit.setCashDpstCl(cashDpstCl);
		eachDeposit.setDpstDt(dpstDt);
		eachDeposit.setDpstAmt(dpstAmt);
		eachDeposit.setDpstFeeTp("1");
		eachDeposit.setFeeAmt(0.0);
		eachDeposit.setBillSeqNo("");
		eachDeposit.setSoId(soId);
		eachDeposit.setGrpId(grpId);
		eachDeposit.setPymAcntId(pymAcntId);
		eachDeposit.setSmry(smry);
		eachDeposit.setDpstBnkAcntCd(dpstBnkAcntId);
		eachDeposit.setTransDt(transDt);
		eachDeposit.setWonDpstAmt(eachDeposit.getDpstAmt());
		eachDeposit.setAcntCardNo(acntCardNo);
		eachDeposit.setRcptEmpId(rcptEmpId);
		eachDeposit.setRcptBillEmpId(rcptBillEmpId);
		eachDeposit.setIfChk(ifChk);
		eachDeposit.setOrgId(orgId);
		eachDeposit.setRegrId(user);
		
		// 청구 정보 파싱
		if (StringUtils.isNotEmpty(billInfoArray) == true) {
			String[] array = billInfoArray.split("\\|");
			
			String[] billSeqArray = new String[array.length];
			String[] billYymmArray = new String[array.length];

			for (int i = 0; i < array.length; i++) {
				String[] array2 = array[i].split("@");
				
				billSeqArray[i] = array2[0];
				billYymmArray[i] = array2[1];
			}
			
			eachDeposit.setBillSeqArray(billSeqArray);
			eachDeposit.setBillYymmArray(billYymmArray);;
		}
		
		ExrateInfo exrateInfo = pyCommService.getExrateInfo();
		
		eachDeposit.setExrate(exrateInfo.getExrate());				// 환율
		eachDeposit.setExrateAplyDt(exrateInfo.getExrateAplyDt());	// 환율 적용일자
		clogService.writeLog("exrateAplyDt : " + eachDeposit.getExrateAplyDt());

		
		int loopCnt = eachDeposit.getBillSeqArray().length;
		
		// 청구일련번호가 들어오지 않아도 한번은 실행이되어야한다.
		if (loopCnt == 0) {
			loopCnt = 1;
		}

		// 건별 입금내역에 등록
		clogService.writeLog("===========================");
		clogService.writeLog(" insertEachDpst function ");
		clogService.writeLog("===========================");
		
		Deposit updateDeposit = new Deposit();
		
		String dpstSeqNo = "";
		
		// 건별 입금내역에 등록
		if (eachDeposit.getDpstCl().equals("05") == false) {
			// 신용카드결제인경우 건별 입금 일련번호는 화면에서 넘겨온 번호로 넣는다.
			// VADS에서는 현금 결제만 있다고 함. 2017.05.12
			eachDeposit.setEachDpstSeq(pyCommService.getEachDpstSeqNo());
		}
		
		clogService.writeLog(String.format("[insertEachDeposit]EACH_DPST_SEQ: (%s)", eachDeposit.getEachDpstSeq()));
		
		eachDeposit.setBillSeqNo(eachDeposit.getBillSeqArray()[0]);
		dpstSeqNo = pyCommService.getDpstSeqNo();
		eachDeposit.setDpstSeqNo(dpstSeqNo);
		updateDeposit.setDpstSeqNo(dpstSeqNo);
		clogService.writeLog(String.format("[insertEachDeposit]DPST_SEQ_NO: (%s)", eachDeposit.getDpstSeqNo()));
		eachDeposit.setRegDate(new Timestamp(new Date().getTime()));
		
		int insertCnt = depositService.insertEachDeposit(eachDeposit);
		
		if (insertCnt <= 0) {
			clogService.writeLog("FAIL INSERT TBLPY_EACH_DPST");
			clogService.writeLog("건별입금내역 등록 실패");
			throw new RuntimeException("FAIL INSERT TBLPY_EACH_DPST");
		}

		// 입금내역에 등록
		clogService.writeLog("===========================");
		clogService.writeLog(" insertDpst function  ");
		clogService.writeLog("===========================");
		insertCnt = depositService.insertDepositFromEachDeposit(eachDeposit);
		
		if (insertCnt <= 0) {
			clogService.writeLog("FAIL INSERT TBLPY_DPST");
			clogService.writeLog("입금내역 등록 실패");
			throw new RuntimeException("FAIL INSERT TBLPY_DPST");
		}
		
		// 건별입금내역 입금처리일시를 수정한다.
		clogService.writeLog("====================================");
		clogService.writeLog("  updateEachDpst function  ");
		clogService.writeLog("====================================");
		insertCnt = depositService.updateEachDeposit(eachDeposit);
		
		if (insertCnt <= 0) {
			clogService.writeLog("FAIL UPDATE DPST PROC DT");
			clogService.writeLog("입금내역처리일시 수정 실패");
			throw new RuntimeException("FAIL UPDATE DPST PROC DT");
		}
		
		double dpstAmt = depositService.getDpstAmt(dpstSeqNo);
		Receipt lastReceipt = null;
		
		for (int i = 0; i < loopCnt; i++) {
			
			// 수납상세내역 시퀀스는 한번만 발급하여 동일한 값을 사용한다.
			String pymSeqNo = pyCommService.getPymSeqNo();
			
			String billSeqNo = eachDeposit.getBillSeqArray()[i];

			int pymAcntCnt = pyCommService.getPymAcntCnt(eachDeposit.getPymAcntId());
			
			// 납부계정ID가 존재하지 앟으면 수납처리가 불가능하다. 불명금발생 등록!
			if (StringUtils.isEmpty(eachDeposit.getPymAcntId()) == true || pymAcntCnt == 0) {
				clogService.writeLog("====================================");
				clogService.writeLog(" insertAmbgOcc function       ");
				clogService.writeLog("====================================");
				ambgService.insertAmbgOcc(eachDeposit.getRegrId(), dpstSeqNo);
				updateDeposit.setAmbgTgtYn("Y");
				paymentService.updateDpstProc(updateDeposit);
				// 불명금으로 전환되었기 때문에 남은 입금금액 없음!
				dpstAmt = 0;
				break;
			} else {
				// 납부계정ID 존재시 수납 처리
				clogService.writeLog("===========================================");
				clogService.writeLog(" updateBill function  ");
				clogService.writeLog("===========================================");

				final String paramDpstSeqNo = dpstSeqNo;

				PaymentResult result = paymentService.processPayment(billSeqNo, pymSeqNo, dpstAmt, new ProcessPaymentCallback() {
					
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
				
				clogService.writeLog("수납처리 완료 후 생성된 데이터 반영 처리");
				List<CBillComm> updateBillMastList = new ArrayList<CBillComm>();
				updateBillMastList.add(result.getUpdateBillMast());
				int update = paymentService.updateBillMastRcptAmt(updateBillMastList);
				clogService.writeLog("TBLIV_BILL_MAST테이블 수납금액 반영 결과 : " + update);
				
				List<CBillComm> updateBillList = result.getUpdateBillList();
				
				for (CBillComm bill : updateBillList) {
					if ("Y".equals(result.getFullPayYn()) == true) {
						update = paymentService.updateFullPayBill(bill);						
					} else {
						update = paymentService.updateBillRcptAmt(bill);
					}

					clogService.writeLog(String.format("billSeqNo : %s, useYymm : %s, prodCmpsId : %s, svcCmpsId : %s"
							, bill.getBillSeqNo(), bill.getUseYymm(), bill.getProdCmpsId(), bill.getSvcCmpsId()));
					clogService.writeLog("청구내역에 대한 수납금액 반영 결과 : " + update);
				}

				int insert = receiptService.insertReceipt(result.getReceiptList());
				clogService.writeLog("수납내역 등록 : " + insert);
				
				insert = receiptService.insertReceiptDetail(result.getReceiptDetailList());
				lastReceipt = result.getReceiptList().get(result.getReceiptList().size() - 1);
				clogService.writeLog("수납상세내역 등록 : " + insert);

				if (insert > 0) {
					// 입금내역의 수납처리여부를 Y로 수정해준다.
					depositService.updatePayProcDt(dpstSeqNo, CUtil.utilGetDateTime(2));
				}

				dpstAmt = result.getRemainAmt();
				clogService.writeLog("수납처리 완료 후 남은 금액 : " + dpstAmt);
			}
		}
		
		if (dpstAmt > 0) {
			clogService.writeLog("선수금이 발생하였음");
			String prepayOccSeqNo = pyCommService.getPrepayOccSeqNo();
			
			PrepayOcc prepayOcc = new PrepayOcc();
			prepayOcc.setPrepayOccSeqNo(prepayOccSeqNo);
			prepayOcc.setPymAcntId(lastReceipt.getPymAcntId());
			prepayOcc.setPrepayOccDttm(CUtil.utilGetDateTime(1));
			prepayOcc.setPrepayOccTp("1");
			prepayOcc.setPrepayOccResn("1");
			prepayOcc.setPrepayOccTgtSeqNo(lastReceipt.getPymSeqNo());
			prepayOcc.setDpstDt(lastReceipt.getDpstDt());
			prepayOcc.setDpstProcDttm(CUtil.utilGetDateTime(1));
			prepayOcc.setDpstCl(lastReceipt.getDpstCl());
			prepayOcc.setPrepayProcStat("1");
			prepayOcc.setPrepayOccAmt(dpstAmt);
			prepayOcc.setPrepayTransAmt(0.0);
			prepayOcc.setPrepayBal(dpstAmt);
			prepayOcc.setTransCmplYn("N");
			prepayOcc.setWonPrepayOccAmt(0.0);
			prepayOcc.setCrncyCd(lastReceipt.getCrncyCd());
			prepayOcc.setExrate(lastReceipt.getExrate());
			prepayOcc.setExrateAplyDt(lastReceipt.getExrateAplyDt());
			prepayOcc.setRegrId(user);
			prepayOcc.setRegDate(new Timestamp(new Date().getTime()));
			prepayOcc.setCnclYn("N");
			prepayOcc.setCnclDttm("");
			prepayOcc.setSoId(lastReceipt.getSoId());
			prepayOcc.setTransDt(lastReceipt.getTransDt());
			
			prepayService.insertPrepayOcc(prepayOcc);
			updateDeposit.setPayProcDt(CUtil.utilGetDateTime(2));
			updateDeposit.setPrepayTgtYn("Y");
			updateDeposit.setPayProcYn("Y");
			
			paymentService.updateDpstProc(updateDeposit);
			
		} else {
			// 선수금 발생없이 수납처리만 되었음!
			updateDeposit.setPayProcDt(CUtil.utilGetDateTime(2));
			updateDeposit.setPayProcYn("Y");
			paymentService.updateDpstProc(updateDeposit);
		}
		
		
		return null;
	}

}
