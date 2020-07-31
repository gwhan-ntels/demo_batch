package com.ntels.ccbs.batch.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BinaryValidationServiceImpl implements BinaryValidationService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${server_mode}")
	private String serverMode;
	@Value("${invoice.resources.path}")
	private String invoiceResourcesPath;
	@Value("${invoice.barcode.base.path}")
	private String invoiceBarcodeBasePath;
	@Value("${invoice.pdf.base.path}")
	private String invoicePdfBasePath;
	@Value("${mail.host}")
	private String mailHost;
	@Value("${mail.port}")
	private String mailPort;
	@Value("${mail.username}")
	private String mailUserName;
	@Value("${mail.password}")
	private String mailPassword;
	@Value("${mail.smtp.auth}")
	private String mailSmtpAuth;
	@Value("${mail.socketFactory.port}")
	private String mailSocketFactoryPort;
	@Value("${mail.socketFactory.class}")
	private String mailSocketFactoryClass;
	@Value("${mail.socketFactory.fallback}")
	private String mailSocketFactoryFallback;
	@Value("${mail.ssl.enable}")
	private String mailSslEnable;
	
	@Override
	public void printProperty() {
		logger.info("serverMode : {}", serverMode);
		logger.info("invoiceResourcesPath : {}", invoiceResourcesPath);
		logger.info("invoiceBarcodeBasePath : {}", invoiceBarcodeBasePath);
		logger.info("invoicePdfBasePath : {}", invoicePdfBasePath);
		logger.info("mailHost : {}", mailHost);
		logger.info("mailPort : {}", mailPort);
		logger.info("mailUserName : {}", mailUserName);
		logger.info("mailPassword : {}", mailPassword);
		logger.info("mailSmtpAuth : {}", mailSmtpAuth);
		logger.info("mailSocketFactoryPort : {}", mailSocketFactoryPort);
		logger.info("mailSocketFactoryClass : {}", mailSocketFactoryClass);
		logger.info("mailSocketFactoryFallback : {}", mailSocketFactoryFallback);
		logger.info("mailSslEnable : {}", mailSslEnable);
	}

}
