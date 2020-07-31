package com.ntels.ccbs.batch.iv.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.TestCommon;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

public class GenerateBillWorkDaoTests extends TestCommon {

	@Autowired
	private GenerateBillWorkDao generateBillWorkDao;
	
	private BillWork billWork;
	
	@Before
	public void setUp() {
		billWork = new BillWork();
		billWork.setClcWrkNo("1");
		billWork.setpSeq("1");
		
		billWork.setSoId("00");
		billWork.setBillYymm("201702");
		billWork.setBillCycl("1");
		billWork.setBillDt("20170227");
		billWork.setPayDueDt("20170228");
		billWork.setExchRateAppDt("20151212");
	}
	
	@Test
	public void getChInfoListForBillWorkTest() {
		generateBillWorkDao.getChInfoListForBillWork(billWork);
	}
	
	@Test
	public void getChInfoListForBillWorkLazyTest() {
		generateBillWorkDao.getChInfoListForBillWorkLazy(billWork);
	}
	
}
