package com.ntels.ccbs.batch.common.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/batch-nBlivb01m02-reader.xml"})
public class BillingUtilServiceTests {

	@Autowired
	private BillingUtilService billingUtilService;
	
	@Test
	public void test() {
		assertNotNull(billingUtilService);
	}
	
	@Test
	public void adjustLastDigitTest() {
		double value1 = 123.123;
		value1 = billingUtilService.adjustLastDigit(value1);
		assertThat(value1, is(123.125));
	}
	
}
