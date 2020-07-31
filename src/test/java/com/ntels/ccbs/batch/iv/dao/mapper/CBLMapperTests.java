package com.ntels.ccbs.batch.iv.dao.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.TestCommon;
import com.ntels.ccbs.batch.iv.common.dao.mapper.CBLMapper;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;

public class CBLMapperTests extends TestCommon {

	@Autowired
	private CBLMapper cblMapper;
	
	private BillCyclStp billCyclStp;
	
	@Before
	public void setUp() {
		billCyclStp = new BillCyclStp();
		billCyclStp.setBillYymm("201702");
		billCyclStp.setBillCycl("1");
		billCyclStp.setSetItmId("00057");
		billCyclStp.setSoId("00");
	}
	
	@Test
	public void getCyclStpSetValTest() {
		cblMapper.getCyclStpSetVal(billCyclStp);
	}
	
}
