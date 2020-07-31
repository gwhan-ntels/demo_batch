package com.ntels.ccbs.batch.iv.dao.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.TestCommon;
import com.ntels.ccbs.batch.iv.common.entity.BillSpltPym;

public class GenerateSplitPaymentMapperTests extends TestCommon {

	@Autowired
	private GenerateSplitPaymentMapper generateSplitPaymentMapper;
	
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
		generateSplitPaymentMapper.getSplitPymCtrtInfoList(billSpltPym);
	}
	
	@Test
	public void getSpltPymResultListTest() {
		generateSplitPaymentMapper.getSpltPymResultList(billSpltPym);
	}
	
}