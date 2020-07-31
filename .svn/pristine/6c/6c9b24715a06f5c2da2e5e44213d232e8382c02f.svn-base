package com.ntels.ccbs.batch;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ntels.ccbs.batch.ch.common.properties.CodeProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/common/spring-base.xml", "classpath:spring/common/jdbc-config.xml"})
public class TestCommon {

	@Autowired
	private CodeProperties codeProperties;
	
	@Test
	public void test() {
		assertNotNull(codeProperties);
	}
	
}
