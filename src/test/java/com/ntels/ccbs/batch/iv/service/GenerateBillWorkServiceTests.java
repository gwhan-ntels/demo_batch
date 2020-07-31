package com.ntels.ccbs.batch.iv.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.TestCommon;
import com.ntels.ccbs.batch.common.exception.BatchError;
import com.ntels.ccbs.batch.common.exception.BatchException;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

public class GenerateBillWorkServiceTests extends TestCommon {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GenerateBillWorkService generateBillWorkService;
	
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
		try {
			generateBillWorkService.getChInfoListForBillWork(billWork);
		} catch (BatchException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = BatchException.class)
	public void getChInfoListForBillWorkEmptySoIdTest() throws BatchException {
		try {
			billWork.setSoId(null);
			generateBillWorkService.getChInfoListForBillWork(billWork);	
		} catch (BatchException e) {
			assertThat(e.getBatchError().getMessage(), is(BatchError.EMPTY_SO_ID.getMessage()));
			logger.info("BatchException message : {}", e.getBatchError().getMessage());
			throw e;
		}
	}
	
	@Test(expected = BatchException.class)
	public void getChInfoListForBillWorkEmptyBillYymmTest() throws BatchException {
		billWork.setBillYymm(null);
		generateBillWorkService.getChInfoListForBillWork(billWork);
	}
	
	@Test
	public void getChInfoListForBillWorkLazyTest() {
		try {
			generateBillWorkService.getChInfoListForBillWorkLazy(billWork);
		} catch (BatchException e) {
			e.printStackTrace();
		}
	}
	
}
