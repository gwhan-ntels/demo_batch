package com.ntels.ccbs.batch.common.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageSource messageSource;
	
	private Locale messageLocale = Locale.KOREA;
	
	@Override
	public String getMessage(String id) {
		return messageSource.getMessage(id, null, messageLocale);
	}
	
	@Override
	public String getMessage(String id, Object... msg) {
		return messageSource.getMessage(id, msg, messageLocale);
	}
	
}
