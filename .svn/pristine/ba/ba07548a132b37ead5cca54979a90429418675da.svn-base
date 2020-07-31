package com.ntels.ccbs.batch.common.service;

import java.util.List;

import com.ntels.ccbs.batch.common.entity.Common;

public interface CommonService {

	public List<Common> commonListExRate();
	public List<Common> commonListBillStp(String billYymm, String billCycl, String soId);
	public List<Common> commonListClcMain(String clcWrkNo);
	public int commonUpdClcMain(String statCd, String clcWrkNo);
	public int commonInsClsInfo(Common common);
	public int commonUpdClsMain(String clsYn, String clsTskCl, String billYymm, String soId);
	public List<Common> commonListBatPgm(String batPgmId);
	public int commonUpdBatPgmLog(Common common);
	public int commonInsBatPgmLog(Common common);
	public void commonExRateInfo();
	public void commonBillInfo(String billYymm, String billCycl, String soId);
	public long getExRateInfo(String crncy_cd);
	public String getBillVal(String set_itm_id);
	public String getBillSeqNo(String bill_yymm, String bill_cycl, String bill_dt, String pym_acnt_id,  String seq);
	public int createNewSequence(String modCd); 
	public List<Integer> createNewSequence(String modCd, int count);
	int batPgmLogCount(Common common);
	String batProcStat(Common common);
	int updateBatProcStat(Common common);
	
}