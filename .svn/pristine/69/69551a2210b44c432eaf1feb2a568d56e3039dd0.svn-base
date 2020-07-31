package com.ntels.ccbs.batch.iv.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.TestCommon;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;

public class GenerateSplitPaymentDaoTests extends TestCommon {

	@Autowired
	private GenerateSplitPaymentDao generateSplitPaymentDao;
	
	private BillSpltPym billSpltPym;
	
	@Before
	public void setUp() {
		billSpltPym = new BillSpltPym();
		billSpltPym.setBillYymm("201702");
		billSpltPym.setSoId("00");
		billSpltPym.setBillCycl("01");
		billSpltPym.setBillDt("25");
		billSpltPym.setpSeq("1");
	}
	
	@Test
	public void getSplitPymCtrtInfoListTest() {
		generateSplitPaymentDao.getSplitPymCtrtInfoList(billSpltPym);
	}
	
	@Test
	public void getSplitPymCtrtInfoListLazyTest() {
		generateSplitPaymentDao.getSplitPymCtrtInfoListLazy(billSpltPym);
	}
	
	@Test
	public void getSpltPymResultListLazyTest() {
		generateSplitPaymentDao.getSpltPymResultListLazy(billSpltPym);
	}
	
}