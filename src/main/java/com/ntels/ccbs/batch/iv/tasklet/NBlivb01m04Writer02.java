package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.NBlivb01m04Service;

/**
 *
 * ItemWriter 샘플.
 *
 * @see [Optional] 관련정보
 * 
 *      <PRE>
 * 1. ClassName: NBliv03m01Writer
 * 2. FileName : NBliv03m01Writer.java
 * 3. Package  : com.ntels.ccbs.batch.sample.tasklet
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:24:00
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 *      </PRE>
 */

@Component
public class NBlivb01m04Writer02 implements ItemWriter<CBillComm>, StepExecutionListener {

	@Autowired
	private NBlivb01m04Service clsService;

	@Autowired
	private ClogService clslog;

	@Autowired
	private CommonService clsCom;

	long j = 0;
	long i = 0;
	List<Object> Objectlist = new ArrayList<Object>();
	List<Object> ObjectDivlist = new ArrayList<Object>();

	List<Object> ObjCtrtList = new ArrayList<Object>();

	int nFcnt = 0, nWcnt = 0, nTmpWCnt = 0, nRcnt = 0, nRet = 1;
	// 사용자 변수 정의 영역
	// ---------------------------------------------------------
	//
	String strTmpDivKey = null;
	String strDivKey = null;

	int nNextFlag = 0;

	String strTmpCtrtId = null;
	String strTmpSpltPymTp = null;
	String strTmpBillSeqNo = null;
	String strCtrtId = null;
	String strSpltPymTp = null;
	String strToDivlCl = null;
	String strSoId = null;
	String strCrncyCd = null;
	String strNewBillSeqNo = null;
	String strToPayAcntId = null;
	String strPayAcntId = null;

	double dTotVal = 0;
	double dOrgBillAmt = 0;
	double dAddBillAmt = 0;
	double dCutBillAmt = 0;
	double dwonBillAmt = 0;
	double dCalAmt = 0;
	double dDifAmt = 0;
	double dLastBillAmt = 0;
	double dDiviAmt = 0;
	double dSumAmt = 0;
	double dBillAmt = 0;
	double dTmpBillAmt = 0;

	/*
	 * procType : 1 인 경우 분리 납부 금액 이상 2 인 경우 분리 납부 금액 이하
	 */

	public int SetDivByAmt(int procType, double dDivVal) {

		String strNewBillSeqNo = null;

		int nCnt = 0;
		int nret = 0;

		double dDivAmt = 0;
		double dDivFAmt = 0;

		if (procType == 2) {
			nCnt = 0;
			nret = 1;
			while (nCnt < ObjCtrtList.size()) {

				CBillComm newItem = new CBillComm();
				CUtil.copyObjectValue((CBillComm) ObjCtrtList.get(nCnt), newItem);
				strNewBillSeqNo = clsCom.getBillSeqNo(newItem.getBillYymm(), newItem.getBillCycl(), newItem.getBillDt(), newItem.getToPymAcntId(), "00");
				newItem.setPymAcntId(newItem.getToPymAcntId());
				newItem.setBillSeqNo(strNewBillSeqNo);
				ObjectDivlist.add(newItem);
				nCnt++;
			}
			return nret;
		}

		/*-----------------------------*/
		if (procType == 3) {
			nCnt = 0;
			nret = 1;
			while (nCnt < ObjCtrtList.size()) {

				CBillComm addItem = new CBillComm();
				CUtil.copyObjectValue((CBillComm) ObjCtrtList.get(nCnt), addItem);
				Objectlist.add(addItem);
				nCnt++;
			}
			return nret;
		}

		/*--------------------------------*/
		nCnt = 0;
		nret = 1;

		dDivAmt = dDivVal;
		while (nCnt < ObjCtrtList.size()) {
			CBillComm addItem = new CBillComm();
			CBillComm newItem = new CBillComm();

			CUtil.copyObjectValue((CBillComm) ObjCtrtList.get(nCnt), addItem);
			CUtil.copyObjectValue((CBillComm) ObjCtrtList.get(nCnt), newItem);

			if (dDivAmt > 0) {

				if (addItem.getBillAmt() > 0 && addItem.getBillAmt() >= dDivAmt)
					dDivFAmt = addItem.getBillAmt() - dDivAmt;
				else if (addItem.getBillAmt() > 0 && addItem.getBillAmt() < dDivAmt)
					dDivFAmt = addItem.getBillAmt();

				dDivAmt = dDivAmt - dDivFAmt;

				addItem.setAdjPrvBillAmt(addItem.getBillAmt() - dDivFAmt);
				addItem.setBillAmt(addItem.getBillAmt() - dDivFAmt);
				addItem.setWonBillAmt(addItem.getBillAmt() - dDivFAmt);

				Objectlist.add(addItem);

				// System.out.println("Objectlist : BILL_SEQ_NO -" +
				// addItem.getBillSeqNo()
				// + " CTRT_ID -" + addItem.getCtrtId()
				// + " PYM - " + addItem.getPymAcntId()
				// + " CHRG_ITM -" + addItem.getChrgItmCd()
				// + " BILL_AMT -" + addItem.getBillAmt().toString()
				// + " PRE_ADJ - " + addItem.getAdjPrvBillAmt().toString());

				strNewBillSeqNo = clsCom.getBillSeqNo(newItem.getBillYymm(), newItem.getBillCycl(), newItem.getBillDt(), newItem.getToPymAcntId(), "00");
				newItem.setPymAcntId(newItem.getToPymAcntId());
				newItem.setBillSeqNo(strNewBillSeqNo);

				newItem.setAdjPrvBillAmt(dDivFAmt);
				newItem.setBillAmt(dDivFAmt);
				newItem.setWonBillAmt(dDivFAmt);
				ObjectDivlist.add(newItem);

				// System.out.println("Objectlist : BILL_SEQ_NO -" +
				// addItem.getBillSeqNo()
				// + " CTRT_ID -" + addItem.getCtrtId()
				// + " PYM - " + addItem.getPymAcntId()
				// + " CHRG_ITM -" + addItem.getChrgItmCd()
				// + " BILL_AMT -" + addItem.getBillAmt().toString()
				// + " PRE_ADJ - " + addItem.getAdjPrvBillAmt().toString());
				//
				// System.out.println("ObjectDivlist : BILL_SEQ_NO -" +
				// newItem.getBillSeqNo()
				// + " CTRT_ID -" + newItem.getCtrtId()
				// + " PYM - " + newItem.getPymAcntId()
				// + " CHRG_ITM -" + newItem.getChrgItmCd()
				// + " BILL_AMT -" + newItem.getBillAmt().toString()
				// + " PRE_ADJ - " + newItem.getAdjPrvBillAmt().toString());

			} else
				Objectlist.add(addItem);
			nCnt++;
		}
		return nret;
	}

	public int SetDivByRate(double dDivVal) {

		String strNewBillSeqNo = null;

		int nCnt = 0;
		int nret = 0;

		double dBillAmt = 0;

		double dRate = 0;

		while (nCnt < ObjCtrtList.size()) {
			CBillComm newItem = new CBillComm();
			CUtil.copyObjectValue((CBillComm) ObjCtrtList.get(nCnt), newItem);

			dRate = Double.valueOf(newItem.getItllmtVal());
			dBillAmt = CUtil.Round(newItem.getBillAmt() * dRate, 0, 3);
			strNewBillSeqNo = clsCom.getBillSeqNo(newItem.getBillYymm(), newItem.getBillCycl(), newItem.getBillDt(), newItem.getToPymAcntId(), "00");
			newItem.setPymAcntId(newItem.getToPymAcntId());
			newItem.setBillSeqNo(strNewBillSeqNo);

			newItem.setAdjPrvBillAmt(dBillAmt);
			newItem.setBillAmt(dBillAmt);
			newItem.setWonBillAmt(dBillAmt);
			ObjectDivlist.add(newItem);
			nCnt++;
		}
		return nret;
	}

	public int SetDivByCtgry(String val) {
		String strNewBillSeqNo = null;

		int nCnt = 0;
		int nret = 0;
		String strVal = null;

		while (nCnt < ObjCtrtList.size()) {
			CBillComm addItem = new CBillComm();
			CUtil.copyObjectValue((CBillComm) ObjCtrtList.get(nCnt), addItem);
			strVal = addItem.getItllmtVal();
			if (addItem.getChrgCtgry().equals(strVal) == true) {
				strNewBillSeqNo = clsCom.getBillSeqNo(addItem.getBillYymm(), addItem.getBillCycl(), addItem.getBillDt(), addItem.getToPymAcntId(), "00");
				addItem.setPymAcntId(addItem.getToPymAcntId());
				addItem.setBillSeqNo(strNewBillSeqNo);
				ObjectDivlist.add(addItem);
			}
			nCnt++;
		}
		return nret;
	}

	public int SetDivByChgItemCd(String val) {
		String strNewBillSeqNo = null;

		int nCnt = 0;
		int nret = 0;
		String strVal = null;

		while (nCnt < ObjCtrtList.size()) {
			CBillComm addItem = new CBillComm();
			CUtil.copyObjectValue((CBillComm) ObjCtrtList.get(nCnt), addItem);

			strVal = addItem.getItllmtVal();

			if (addItem.getChrgItmCd().equals(strVal) == true) {
				strNewBillSeqNo = clsCom.getBillSeqNo(addItem.getBillYymm(), addItem.getBillCycl(), addItem.getBillDt(), addItem.getToPymAcntId(), "00");
				addItem.setPymAcntId(addItem.getToPymAcntId());
				addItem.setBillSeqNo(strNewBillSeqNo);
				ObjectDivlist.add(addItem);
			}
			nCnt++;
		}
		return nret;
	}

	/*
	 * procType : 1 인 경우 분리 납부 금액 이상 
	 *            2 인 경우 분리 납부 금액 이하
	 */
	public int SetDivProc() throws Exception {

		CBillComm addItem;

		int nCnt = 0;
		int nret = 0;
		double dTotSum = 0;
		double dDivAmt = 0;
		String strTmpSpltPymTp = null;
		String strItllmt = null;
		String strItmVal = null;

		if (ObjCtrtList.size() <= 0)
			return 1;

		while (nCnt < ObjCtrtList.size()) {
			addItem = (CBillComm) ObjCtrtList.get(nCnt);
			dTotSum += addItem.getBillAmt();
			nCnt++;
			if (nCnt == 1) {
				strTmpSpltPymTp = addItem.getSpltPymTp();
				strItmVal = addItem.getItllmtVal();
				strItllmt = addItem.getItllmtCl();
			}
		}

		nret = 1;

		switch (strTmpSpltPymTp) {
		case "1":
			dDivAmt = Double.valueOf(strItmVal);
			if (strItllmt.equals("1") == true && dTotSum >= dDivAmt)
				nret = SetDivByAmt(1, dDivAmt);
			else if (strItllmt.equals("2") == true && dTotSum < dDivAmt)
				nret = SetDivByAmt(2, dDivAmt);
			else
				nret = SetDivByAmt(3, dDivAmt);
			break;

		case "2":
			dDivAmt = Double.valueOf(strItmVal);
			nret = SetDivByRate(dDivAmt);
			break;
		case "3":
			nret = SetDivByCtgry(strItmVal);
			break;
		case "4":
			nret = SetDivByChgItemCd(strItmVal);
			break;

		default:
			nret = -1;
		}

		nret = clsService.saveWrkDirect(Objectlist);

		if (nret < 0) {
			clslog.writeLog("saveWrkDirect Error ");
			return nret;
		}
		if (nret >= 0)
			nret = clsService.saveDivJdbcDirect(ObjectDivlist);

		if (nret < 0) {
			clslog.writeLog("saveDivJdbcDirect Error ");
			return nret;
		}

		Objectlist.clear();
		ObjectDivlist.clear();
		ObjCtrtList.clear();
		ObjCtrtList.clear();
		return nret;
	}

	public int MainDivProc(CBillComm obj) throws Exception {

		int ret = 0;

		strCtrtId = obj.getCtrtId();
		if (strTmpCtrtId != strCtrtId) {
			if (strTmpCtrtId != null) {
				ret = SetDivProc();
			}
			strTmpCtrtId = strCtrtId;
		}
		ObjCtrtList.add(obj);
		return ret;
	}

	public void write(List<? extends CBillComm> items) throws Exception {

		Objectlist.clear();
		ObjectDivlist.clear();
		ObjCtrtList.clear();

		int result = 0;

		if (i == 0)
			System.out.println("[Write Start :" + CUtil.utilGetDateTime(4));

		for (CBillComm list : items) {

			i++;
			j++;
			list.setTimeInfo();

			list.setRcptAmt(0.0);
			list.setUpayAmt(0.0);

			result = MainDivProc(list);
			if (result < 0)
				return;

		}
		SetDivProc();

		int j = 0;

		// System.out.println("Objectlist
		// ----------------------------------------------------------");
		// while(j < Objectlist.size() )
		// {
		// cbill = (CBillComm)Objectlist.get(j);
		// System.out.println("["+j+"] : BILL_SEQ_NO -" + cbill.getBillSeqNo()
		// + " CTRT_ID -" + cbill.getCtrtId()
		// + " PYM - " + cbill.getPymAcntId()
		// + " CHRG_ITM -" + cbill.getChrgItmCd()
		// + " BILL_AMT -" + cbill.getBillAmt().toString()
		// + " PRE_ADJ - " + cbill.getAdjPrvBillAmt().toString());
		// j++;
		// }
		//

		// System.out.println("ObjectDivlist
		// ----------------------------------------------------------");
		// j=0;
		// while(j < ObjectDivlist.size() )
		// {
		// cbill = (CBillComm)ObjectDivlist.get(j);
		// System.out.println("["+j+"] : BILL_SEQ_NO -" + cbill.getBillSeqNo()
		// + " CTRT_ID -" + cbill.getCtrtId()
		// + " PYM - " + cbill.getPymAcntId()
		// + " CHRG_ITM -" + cbill.getChrgItmCd()
		// + " BILL_AMT -" + cbill.getBillAmt().toString()
		// + " PRE_ADJ - " + cbill.getAdjPrvBillAmt().toString());
		// j++;
		// }
		// System.out.println("Objectlist
		// ----------------------------------------------------------");

		if (i == 10000) {
			System.out.println("[" + j + ":" + CUtil.utilGetDateTime(4));
			i = 0;
		}

	}

	public void beforeStep(StepExecution stepExecution) {

		System.out.println("Read Count = " + stepExecution.getReadCount() + " Read Skip =" + stepExecution.getReadSkipCount());
		System.out.println("Write =" + stepExecution.getWriteCount() + " Write Skip =" + stepExecution.getWriteSkipCount());
		System.out.println("getCommitCount =" + stepExecution.getCommitCount() + " getRollbackCount =" + stepExecution.getRollbackCount());
		System.out.println("getStepName =" + stepExecution.getStepName() + " getSummary =" + stepExecution.getSummary());
		System.out.println("getId =" + stepExecution.getId() + " getJobExecutionId =" + stepExecution.getJobExecutionId());
		System.out.println("getClass =" + stepExecution.getClass() + " getExitStatus =" + stepExecution.getExitStatus());
		System.out.println("getStartTime =" + stepExecution.getStartTime() + " getEndTime =" + stepExecution.getEndTime());
		System.out.println("getStatus =" + stepExecution.getStatus() + " getEndTime =" + stepExecution.getFailureExceptions());

	}

	public ExitStatus afterStep(StepExecution stepExecution) {

		int nret = 0;
		try {
			nret = SetDivProc();

			if (nret < 0)
				clslog.writeLog("SetDivProc Error!");
			else
				clslog.writeLog("SerDivProc Success !");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}