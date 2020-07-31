package com.ntels.ccbs.batch.common.launcher;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ntels.ccbs.batch.common.dao.CommonDao;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.BinaryValidationService;

public class BinaryValidationChecker {

	static Logger logger = LoggerFactory.getLogger(BinaryValidationChecker.class);
	
	public static void main(String[] args) {
		
		logger.info("BinaryValidationChecker.main");
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/batch-nBlivb01m18-tasklet.xml");
		
		logger.info("Load CommonDao test");
		CommonDao commonDao = context.getBean(CommonDao.class);
		logger.info("commonDao from sprin context is null ? {}", commonDao == null);
		
		if (commonDao == null) {
			logger.info("Fail!! Binary Validation Check");
		} else {
			
			logger.info("Load ex rate list for Test!");
			List<Common> exRateList = commonDao.commonListExRate();
			logger.info("exRateList size : {}", exRateList.size());

			for (Common common : exRateList) {
				logger.info("ex rate\n{}", ToStringBuilder.reflectionToString(common, ToStringStyle.MULTI_LINE_STYLE));
			}
			
			BinaryValidationService s = context.getBean(BinaryValidationService.class);
			s.printProperty();

			logger.info("Success!! Binary Validation Check");
		}
	}
	
}
