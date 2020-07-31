package com.ntels.ccbs.batch.iv.dao.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ntels.ccbs.batch.iv.common.entity.BillWork;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/batch-nBlivb01m02-reader.xml"})
public class GenerateBillWorkMapperTests {

	@Autowired
	private GenerateBillWorkMapper generateBillWorkMapper;
	
	private BillWork billWork;
	
	@Before
	public void setUp() {
		billWork = new BillWork();
		billWork.setSoId("00");
	}
	
	@Test
	public void getChInfoListForBillWorkTests() {
		generateBillWorkMapper.getChInfoListForBillWork(billWork);
	}
	
	@Test
	public void getAutoTransferDiscountInfoListTest() {
		generateBillWorkMapper.getAutoTransferDiscountInfoList(billWork);
	}
	
	@Test
	public void getAdjBillInfoListTest() {
		generateBillWorkMapper.getAdjInfoAfterBillList(billWork);
	}
	
}