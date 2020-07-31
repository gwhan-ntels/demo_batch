package com.ntels.ccbs.batch.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * 하나의 배치 태스크에서 공유해서 사용해야하는 변수들을 관리하는 클래스
 * @author Cashyalla
 *
 */
@Component
public class ValueRepository {

	private Map<String, Object> values;

	public ValueRepository() {
		values = new ConcurrentHashMap<String, Object>();
	}
	
	public synchronized void setValue(String key, Object value) {
		values.put(key, value);
	}
	
	public synchronized Object getValue(String key) {
		return values.get(key);
	}

}
