package com.ntels.ccbs.batch.py.tasklet;

import java.util.ArrayList;
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

import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.entity.PaymentResult;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.service.NBlpyf02m02Service;
import com.ntels.ccbs.batch.py.service.PaymentService;
import com.ntels.ccbs.batch.py.service.PaymentService.ProcessPaymentCallback;
import com.ntels.ccbs.batch.py.service.PrepayService;
import com.ntels.ccbs.batch.py.service.ReceiptService;

@Component("nBlpyf02m02JobTasklet")
@Scope("step")
public class NBlpyf02m02JobTasklet implements Tasklet, StepExecutionListener {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private PrepayService prepayService;
	
	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private NBlpyf02m02Service nBlpyf02m02Service;
	
	// TODO 데이터 파싱 처리
	private String regrId = "Tester";
	// TODO 데이터 파싱 처리
	private String prepayOccSeq = "0000599727";
	// TODO 데이터 파싱 처리
	private double prepayBal = 12710.0;
	// TODO 데이터 파싱 처리
	private String pymAcntId = "PS00088949";
	// TODO 데이터 파싱 처리
	private String prepayGubun = "N";
	// TODO 데이터 파싱 처리
	private String flag = "1";
	// TODO 파싱해서 나온 데이터의 수
	private int prepayCnt = 1;
	
	private String[] dataArray;
	
	private double prepayAplyAmt;
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		clogService.writeLog("NBlpyf02m02JobTasklet.afterStep");
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		clogService.writeLog("NBlpyf02m02JobTasklet.beforeStep");
		dataArray = new String[] { pymAcntId };
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		clogService.writeLog("NBlpyf02m02JobTasklet.execute");
		
		if ("3".equals(flag) == true) {
			prepayAplyAmt = prepayBal;
			
			PrepayOcc prepayOcc = new PrepayOcc();
			prepayOcc.setPrepayOccSeqNo(prepayOccSeq);
			prepayOcc.setPrepayBal(prepayAplyAmt);
			prepayOcc.setPymAcntId(pymAcntId);
			prepayOcc.setRegrId(regrId);
			
			prepayService.updatePrepayOcc(prepayOcc);
			
			prepayOcc.setPrepayOccTp("05");
			String prepayTransSeqNo = prepayService.insertPrepayTransHistory(prepayOcc);
			
			prepayOcc.setPrepayOccTgtSeqNo(prepayTransSeqNo);
			prepayService.insertNewPrepayOccFromPrepayOcc(prepayOcc);
		} else {
			prepayAplyAmt = 0;
			
			for (int i = 0; i < prepayCnt; i++) {
				PrepayOcc prepayAmount = prepayService.getPrepayAmount(prepayOccSeq);
				
				if (i == 0) {
					if (prepayAmount.getPrepayBal() != prepayBal) {
						clogService.writeLog("Error reason(PrepayBal != inPrepayBal).");
						throw new Exception("Error reason(PrepayBal != inPrepayBal).");
					}
				}

				String billSeqNo = null;
				
				if ("1".equals(flag)) {
					String pymAcntId = dataArray[i];
					int cnt = pyCommService.getPymAcntCnt(pymAcntId);
					
					if (cnt <= 0) {
						clogService.writeLog("[updateBill]PYM_ACNT_ID NOT FOUND");
					}
					
					List<String> billSeqNoList = nBlpyf02m02Service.getBillSeqByPymAcntId(pymAcntId);
					if (billSeqNoList.size() <= 0) {
						throw new RuntimeException("청구 내역이 없습니다.");
					}
					
					billSeqNo = billSeqNoList.get(0);
				} else {
					billSeqNo = dataArray[i];
				}
				
				String pymSeqNo = pyCommService.getPymSeqNo();
				
				PaymentResult result = paymentService.processPayment(billSeqNo, pymSeqNo, prepayBal, new ProcessPaymentCallback() {
					
					@Override
					public Receipt getReceipt() {
						
						PrepayOcc prepayOcc = prepayService.getPrepayOcc(prepayOccSeq);
						
						Receipt receipt = new Receipt();
						receipt.setPymAcntId(prepayOcc.getPymAcntId());
						receipt.setDpstProcDt(prepayOcc.getDpstProcDttm().substring(0, 8));
						receipt.setDpstDt(prepayOcc.getDpstDt());
						receipt.setDpstCl("16");
						receipt.setDpstSeqNo(prepayOcc.getPrepayOccTgtSeqNo());
						receipt.setCrncyCd(prepayOcc.getCrncyCd());
						receipt.setExrate(prepayOcc.getExrate());
						receipt.setExrateAplyDt(prepayOcc.getExrateAplyDt());
						receipt.setCnclYn(prepayOcc.getCnclYn());
						receipt.setTransDt(prepayOcc.getTransDt());
						receipt.setSoId(prepayOcc.getSoId());
						receipt.setRegrId(prepayOcc.getRegrId());
						return receipt;
					}
				});
				
				Receipt receipt = result.getReceiptList().get(0);
				String prepayTransSeqNo = "";
				
				if (receipt.getRcptAmt() > 0.0) {
					PrepayOcc updatePrepayOcc = new PrepayOcc();
					updatePrepayOcc.setPrepayOccSeqNo(prepayOccSeq);
					updatePrepayOcc.setPrepayBal(receipt.getRcptAmt());
					updatePrepayOcc.setRegrId(regrId);
					prepayService.updatePrepayOcc(updatePrepayOcc);
					
					if (result.getPymAcntId().equals(pymAcntId) == true) {
						receipt.setPayTp("2");
						updatePrepayOcc.setPrepayOccTp("02");
					} else {
						receipt.setPayTp("3");
						updatePrepayOcc.setPrepayOccTp("03");
					}

					prepayTransSeqNo = prepayService.insertPrepayTransHistory(updatePrepayOcc);
				}
				
				List<CBillComm> updateBillMastList = new ArrayList<>();
				updateBillMastList.add(result.getUpdateBillMast());
				paymentService.updateBillMastRcptAmt(updateBillMastList);
				
				if ("Y".equals(result.getFullPayYn()) == true) {
					paymentService.updateFullPayBill(result.getUpdateBillList());
				} else {
					paymentService.updateBillRcptAmt(result.getUpdateBillList());	
				}
				
				result.getReceiptList().get(0).setPrepayTransSeqNo(prepayTransSeqNo);
				receiptService.insertReceipt(result.getReceiptList());
				receiptService.insertReceiptDetail(result.getReceiptDetailList());
				
				prepayAplyAmt = result.getRemainAmt();
				
				if (prepayAplyAmt > 0.0 || "Y".equals(prepayGubun) == true) {
					
					PrepayOcc prepayOcc = new PrepayOcc();
					prepayOcc.setPrepayOccSeqNo(prepayOccSeq);
					prepayOcc.setPrepayBal(prepayAplyAmt);
					prepayOcc.setRegrId(regrId);
					
					prepayService.updatePrepayOcc(prepayOcc);
					
					prepayOcc.setPrepayOccTp("05");
					prepayTransSeqNo = prepayService.insertPrepayTransHistory(prepayOcc);

					prepayOcc.setPrepayOccTgtSeqNo(prepayTransSeqNo);
					prepayService.insertNewPrepayOccFromPrepayOcc(prepayOcc);
				}
			}
		}
		
		return null;
	}
	
//	private double updateBill(List<Bill> billList, final String pymAcntId) {
//		
//		final PrepayOcc prepayOcc = prepayService.getPrepayOcc(prepayOccSeq);
//		
//		return pyCommService.updateBill(billList, prepayOcc.getPrepayBal(), new UpdateBillCallback() {
//			
//			@Override
//			public void setPymAcntId(String pymAcntId) {
//				// do nothing
//			}
//
//			@Override
//			public Receipt presetReceipt(Bill bill) {
//				Receipt receipt = new Receipt();
//				CUtil.copyObjectValue(prepayOcc, receipt);
//				
//				receipt.setPayProcDt(CUtil.utilGetDateTime(2));
//				receipt.setDpstProcDt(prepayOcc.getDpstProcDttm().substring(1, 8));
//				
//				if (bill.getPymAcntId().equals(pymAcntId) == true) {
//					receipt.setPayTp("2");
//				} else {
//					receipt.setPayTp("3");
//				}
//				
//				receipt.setDpstCl("16");
//				
//				receipt.setCnclYn("N");
//				receipt.setTransDt(prepayOcc.getDpstProcDttm().substring(1, 8));
//				
//				return receipt;
//			}
//			
//			@Override
//			public AssrOcc presetAssrOcc(AssrOcc assrOcc) {
//				assrOcc.setSoId(prepayOcc.getSoId());
//				assrOcc.setDpstTpSeqNo(prepayOcc.getPrepayOccTgtSeqNo());
//				assrOcc.setDpstDt(prepayOcc.getDpstDt());
//				assrOcc.setDpstProcDttm(prepayOcc.getDpstProcDttm());
//				assrOcc.setDpstCl(prepayOcc.getDpstCl());
//				assrOcc.setTransDt(prepayOcc.getTransDt());
//				return assrOcc;
//			}
//			
//			@Override
//			public String getRegrId() {
//				return regrId;
//			}
//			
//			@Override
//			public String getPymSeqNo() {
//				// TODO 수납 시퀀스 발급
//				return "0000000000";
//			}
//			
//			@Override
//			public void afterJob(double sumTransAmt, Bill bill) {
//				if (sumTransAmt > 0.0) {
//					PrepayOcc updatePrepayOcc = new PrepayOcc();
//					updatePrepayOcc.setPrepayOccSeqNo(prepayOccSeq);
//					updatePrepayOcc.setPrepayBal(sumTransAmt);
//					updatePrepayOcc.setRegrId(regrId);
//					prepayService.updatePrepayOcc(prepayOcc);
//					
//					if (bill.getPymAcntId().equals(pymAcntId) == true) {
//						updatePrepayOcc.setPrepayOccTp("02");
//					} else {
//						updatePrepayOcc.setPrepayOccTp("03");
//					}
//					
//					prepayService.insertPrepayTransHistory(updatePrepayOcc);
//				}
//			}
//		});
//	}

}
