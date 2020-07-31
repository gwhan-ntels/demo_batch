package com.ntels.ccbs.batch.common.service;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.exception.BatchError;
import com.ntels.ccbs.batch.common.exception.BatchException;

public class BaseService {

	@Autowired
	protected MessageService messageService;
	
	protected void checkEmpty(String value, String valName) {
		if (StringUtils.isEmpty(value) == true) {
			throw new RuntimeException(messageService.getMessage("log.err.empty.value", valName));
		}
	}
	
	/**
	 * 파라미터 스트링의 길이를 체크한다. 
	 * @param value
	 * @param msg
	 */
	protected void checkLength(String value, int length, String valName) {
		checkEmpty(value, valName);
		
		if (value.length() > length) {
			throw new RuntimeException(messageService.getMessage("log.err.long.value", valName, length));
		}
	}
	
	/**
	 * yyyyMMddHHmmss형식의 날짜 반환
	 * @return
	 */
	protected String getDttm() {
		return CUtil.utilGetDateTime(1);
	}
	
	/**
	 * yyyyMMdd형식의 날짜 반환
	 * @return
	 */
	protected String getDt() {
		return CUtil.utilGetDateTime(2);
	}
	
	/**
	 * 각 DB별 date형식으로 자동 변환해주는 Timestamp를 지금 시각으로 설정하여 돌려줌.
	 * @return
	 */
	protected Timestamp now() {
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * SO ID가 필수 값인 경우, 이 값이 비어있는지 체크하여 비어있다면 BatchException을 발생시킨다.
	 * @param soId
	 * @throws BatchException
	 */
	protected void checkEmptySoId(String soId) throws BatchException {
		if (StringUtils.isEmpty(soId) == true) {
			throw new BatchException(BatchError.EMPTY_SO_ID);
		}
	}
	
	/**
	 * BILL YYMM이 필수 값인 경우, 이 값이 비어있는지 체크하여 비어있다면 BatchException을 발생시킨다.
	 * @param billYymm
	 * @throws BatchException
	 */
	protected void checkEmptyBillYymm(String billYymm) throws BatchException {
		if (StringUtils.isEmpty(billYymm) == true) {
			throw new BatchException(BatchError.EMPTY_BILL_YYMM);
		}
	}
	
	/**
	 * BILL CYCL이 필수 값인 경우, 이 값이 비어있는지 체크하여 비어있다면 BatchException을 발생시킨다.
	 * @param billCycl
	 * @throws BatchException
	 */
	protected void checkEmptyBillCycl(String billCycl) throws BatchException {
		if (StringUtils.isEmpty(billCycl) == true) {
			throw new BatchException(BatchError.EMPTY_BILL_CYCL);
		}
	}
	
	/**
	 * BILL DT가 필수 값인 경우, 이 값이 비어있는지 체크하여 비어있다면 BatchException을 발생시킨다.
	 * @param billDt
	 * @throws BatchException
	 */
	protected void checkEmptyBillDt(String billDt) throws BatchException {
		if (StringUtils.isEmpty(billDt) == true) {
			throw new BatchException(BatchError.EMPTY_BILL_DT);
		}
	}
	
	/**
	 * P SEQ가 필수 값인 경우, 이 값이 비어있는지 체크하여 비어있다면 BatchException을 발생시킨다.
	 * @param pSeq
	 * @throws BatchException
	 */
	protected void checkEmptypSeq(String pSeq) throws BatchException {
		if (StringUtils.isEmpty(pSeq) == true) {
			throw new BatchException(BatchError.EMPTY_P_SEQ);
		}
	}
	
}
