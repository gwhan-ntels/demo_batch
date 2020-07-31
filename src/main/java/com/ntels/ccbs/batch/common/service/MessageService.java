package com.ntels.ccbs.batch.common.service;

public interface MessageService {

	String getMessage(String id);
	
	String getMessage(String id, Object... msg);
	
}
