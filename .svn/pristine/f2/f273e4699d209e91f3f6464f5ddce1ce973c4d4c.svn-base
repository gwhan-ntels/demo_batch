package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.common.tasklet.CommonTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPymProcRslt;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.GenerateSplitPaymentService;

@Component
@Scope("step")
public class SplitPaymentTasklet extends CommonTasklet {
	
	@Autowired
	private GenerateSplitPaymentService generateSplitPaymentService;
	
	@Autowired
	private CommonService commonService;
	
	@Value("#{jobParameters['pgmSeq']}")
	private String pgmSeq;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
		init();
		
		BillSpltPym billSpltPym = new BillSpltPym();
		billSpltPym.setSoId(soId);
		billSpltPym.setBillYymm(billYymm);
		billSpltPym.setBillCycl(billCycl);
		billSpltPym.setpSeq(pgmSeq);
		billSpltPym.setBillDt(billYymm + getBillDt());

		// 분할납부 계약정보 조회
		LazyLoader<BillSpltPym> lazyLoader = generateSplitPaymentService.getSplitPymCtrtInfoList(billSpltPym);

		List<BillSpltPym> itemList = lazyLoader.getItemList();
		List<BillSpltPym> writeItemList = new ArrayList<>();
		
		// 조회된 데이터를 모두 돌면서 처리
		while (itemList.isEmpty() == false) {
			BillSpltPym item = itemList.remove(0);

			// 청구번호 생성
			String billSeqNo = commonService.getBillSeqNo(item.getBillYymm(), item.getBillCycl(),
					item.getBillDt().substring(6, 8), item.getPymAcntId(), "00");
			item.setBillSeqNo(billSeqNo);
			item.setRegDate(now());
			
			writeItemList.add(item);

			// 아이템이 커밋 인터벌만큼 채워지면 db에 쓰기
			if (writeItemList.size() >= interval) {
				writeBillSpltPym(writeItemList);
				writeItemList.clear();
			}

			if (itemList.isEmpty() == true) {
				itemList = lazyLoader.getItemList();
			}
		}
		
		// 분할납부계정 처리 결과 저장하기위한 데이터 조회
		lazyLoader = generateSplitPaymentService.getSpltPymResultList(billSpltPym);
		
		itemList = lazyLoader.getItemList();
		writeItemList.clear();
		
		String tmpCtrtId = null;
		List<BillSpltPymProcRslt> tmpList = new ArrayList<>();
		
		while (itemList.isEmpty() == false) {
			BillSpltPym item = itemList.remove(0);
			BillSpltPymProcRslt billSpltPymProcRslt = billSpltPymProcRstlFromBillSpltPym(item);
			
			billSpltPymProcRslt.setRegDate(now());
			billSpltPymProcRslt.setRcptAmt(0);
			billSpltPymProcRslt.setUpayAmt(0);
			
			// 계약ID별로 프로세스 진행
			if (tmpCtrtId.equals(billSpltPymProcRslt.getCtrtId()) == false) {
				if (tmpCtrtId != null) {
				}
				
				tmpCtrtId = billSpltPymProcRslt.getCtrtId();
			}
			
			tmpList.add(billSpltPymProcRslt);
			
		}
		
		return RepeatStatus.FINISHED;
	}
	
	private int writeBillSpltPym(List<BillSpltPym> billSpltPymList) {
		logger.info("writeBillSpltPym list size : {}", billSpltPymList.size());
		int insertCnt = generateSplitPaymentService.insertSpltPymList(billSpltPymList);
		logger.info("writeBillSpltPym insert size : {}", insertCnt);
		return insertCnt;
	}
	
	/**
	 * BillSpltPym객체를 BillSpltPymProcRstl로 변환 후 반환
	 * @param item
	 * @return
	 */
	private BillSpltPymProcRslt billSpltPymProcRstlFromBillSpltPym(BillSpltPym item) {
		BillSpltPymProcRslt billSpltPymProcRslt = new BillSpltPymProcRslt();
		
		billSpltPymProcRslt.setBillSeqNo(item.getBillSeqNo());
		billSpltPymProcRslt.setUseYymm(item.getUseYymm());
		billSpltPymProcRslt.setProdCmpsId(item.getProdCmpsId());
		billSpltPymProcRslt.setSvcCmpsId(item.getSvcCmpsId());
		billSpltPymProcRslt.setChrgItmCd(item.getChrgItmCd());
		billSpltPymProcRslt.setBillYymm(item.getBillYymm());
		billSpltPymProcRslt.setBillCycl(item.getBillCycl());
		billSpltPymProcRslt.setBillDt(item.getBillDt());
		billSpltPymProcRslt.setSoId(item.getSoId());
		billSpltPymProcRslt.setGrpId(item.getGrpId());
		billSpltPymProcRslt.setPymAcntId(item.getPymAcntId());
		billSpltPymProcRslt.setCtrtId(item.getCtrtId());
		billSpltPymProcRslt.setProdCd(item.getProdCd());
		billSpltPymProcRslt.setSvcCd(item.getSvcCd());
		billSpltPymProcRslt.setBizCl(item.getBizCl());
		billSpltPymProcRslt.setProdGrp(item.getProdGrp());
		billSpltPymProcRslt.setSvcGrp(item.getSvcGrp());
		billSpltPymProcRslt.setBillMmTp(item.getBillMmTp());
		billSpltPymProcRslt.setSaleTp(item.getSaleTp());
		billSpltPymProcRslt.setUsgCnt(item.getUsgCnt());
		billSpltPymProcRslt.setUsgAmt(item.getUsgAmt());
		billSpltPymProcRslt.setAdjPrvBillAmt(item.getAdjPrvBillAmt());
		billSpltPymProcRslt.setAdjAmt(item.getAdjAmt());
		billSpltPymProcRslt.setBillAmt(item.getBillAmt());
		billSpltPymProcRslt.setPayDueDt(item.getPayDueDt());
		billSpltPymProcRslt.setSpltPymTp(item.getSpltPymTp());
		billSpltPymProcRslt.setChrgCtgry(item.getChrgCtgry());
		billSpltPymProcRslt.setAtrtCorpId(item.getAtrtCorpId());
		billSpltPymProcRslt.setAtrtEmpId(item.getAtrtEmpId());
		billSpltPymProcRslt.setWonBillAmt(item.getWonBillAmt());
		billSpltPymProcRslt.setCrncyCd(item.getCrncyCd());
		billSpltPymProcRslt.setExrate(item.getExrate());
		billSpltPymProcRslt.setExrateAplyDt(item.getExrateAplyDt());
		billSpltPymProcRslt.setRegDate(now());
		
		return billSpltPymProcRslt;
	}
	
	private int splitProcess(List<BillSpltPymProcRslt> itemList) {
		
		int ret = 0;
		
		if (itemList == null || itemList.isEmpty() == true) {
			return 1;
		}
		
		// 청구금액의 총 합
		double totSum = 0;
		double divAmt = 0;
		String tmpSpltPymTp = null;
		String itmVal = null;
		String itllmt = null;
		
		for (int i = 0; i < itemList.size(); i++) {
			BillSpltPymProcRslt item = itemList.get(i);
			totSum += item.getBillAmt();
			
			if (i == 0) {
				tmpSpltPymTp = item.getSpltPymTp();
				itmVal = item.getItllmtVal();
				itllmt = item.getItllmtCl();
			}
		}
		
		divAmt = Double.parseDouble(itmVal);
		
		switch (tmpSpltPymTp) {
		case "1" :
			List<BillWork> billWorkList = new ArrayList<>();
			
			if ("1".equals(itllmt) == true && totSum >= divAmt) {
				billWorkList = splitByAmt(1, divAmt, itemList);
			} else if ("2".equals(itllmt) == true && totSum < divAmt) {
				billWorkList = splitByAmt(2, divAmt, itemList);
			} else {
				billWorkList = splitByAmt(3, divAmt, itemList);
			}
			break;
		case "2" :
			break;
		case "3" :
			break;
		case "4" :
			break;
		default :
		}
		
		return ret;
		
	}
	
	private List<BillWork> splitByAmt(int procType, double divVal, List<BillSpltPymProcRslt> itemList) {
		String strNewBillSeqNo = null;

		double dDivAmt = 0;
		double dDivFAmt = 0;
		
		List<BillWork> billWorkList = new ArrayList<>();

		// 분리 납부 금액 이하
		if (procType == 2) {
			
			for (BillSpltPymProcRslt item : itemList) {
				BillWork billWork = new BillWork();
				CUtil.copyObjectValue(item, billWork);
				
				String newBillSeqNo = commonService.getBillSeqNo(billWork.getBillYymm(), billWork.getBillCycl(), billWork.getBillDt(),
						item.getToPymAcntId(), "00");
				billWork.setPymAcntId(item.getToPymAcntId());
				billWork.setBillSeqNo(newBillSeqNo);
				billWorkList.add(billWork);
			}
			
			return billWorkList;
		}

		if (procType == 3) {

			for (BillSpltPymProcRslt item : itemList) {
				BillWork billWork = new BillWork();
				CUtil.copyObjectValue(item, billWork);
				billWorkList.add(billWork);
			}
			
			return billWorkList;
		}

		// procType 1이면 분리 납부 금액 이상
		dDivAmt = divVal;
		
		for (BillSpltPymProcRslt item : itemList) {
			BillWork addItem = new BillWork();
			BillWork newItem = new BillWork();
			
			CUtil.copyObjectValue(item, addItem);
			CUtil.copyObjectValue(item, newItem);
			
			if (dDivAmt > 0) {
				if (addItem.getBillAmt() > 0 && addItem.getBillAmt() >= dDivAmt) {
					dDivFAmt = addItem.getBillAmt() - dDivAmt;	
				} else if (addItem.getBillAmt() > 0 && addItem.getBillAmt() < dDivAmt) {
					dDivFAmt = addItem.getBillAmt();	
				}
				
				dDivAmt = dDivAmt - dDivFAmt;

				addItem.setAdjPrvBillAmt(addItem.getBillAmt() - dDivFAmt);
				addItem.setBillAmt(addItem.getBillAmt() - dDivFAmt);
				addItem.setWonBillAmt(addItem.getBillAmt() - dDivFAmt);
				
				billWorkList.add(addItem);
				
				strNewBillSeqNo = commonService.getBillSeqNo(newItem.getBillYymm(), newItem.getBillCycl(), newItem.getBillDt(),
						item.getToPymAcntId(), "00");
				newItem.setPymAcntId(item.getToPymAcntId());
				newItem.setBillSeqNo(strNewBillSeqNo);

				newItem.setAdjPrvBillAmt(dDivFAmt);
				newItem.setBillAmt(dDivFAmt);
				newItem.setWonBillAmt(dDivFAmt);
				
				billWorkList.add(newItem);
			} else {
				billWorkList.add(addItem);
			}
		}
		
		return billWorkList;
		
	}

}
