package com.ntels.ccbs.batch.iv.dao.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.TestCommon;
import com.ntels.ccbs.batch.iv.common.entity.Arrears;

public class ArrearsMapperTests extends TestCommon {

	@Autowired
	private ArrearsMapper arrearsMapper;
	
	private Arrears arrears;
	
	@Before
	public void setUp() {
		arrears = new Arrears();
		arrears.setSoId("00");
		arrears.setBillYymm("201702");
		arrears.setBillCycl("01");
		arrears.setBillDt("20170225");
		
		arrears.setBefBillYymm("201701");
		arrears.setBefPayDueDt("20170125");
		
		arrears.setChAppYn("Y");
		arrears.setChAppYymm("201602");
		arrears.setChDelayImposeYn("Y");
		arrears.setpSeq("1");
	}
	
	@Test
	public void test() {
		arrearsMapper.getArrearsInfoList(arrears);
	}
	
}
