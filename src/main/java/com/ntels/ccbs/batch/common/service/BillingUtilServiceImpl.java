package com.ntels.ccbs.batch.common.service;

import org.springframework.stereotype.Service;

@Service
public class BillingUtilServiceImpl implements BillingUtilService {

	/*
	 * (non-Javadoc)
	 * @see com.ntels.ccbs.batch.common.service.BillingUtilService#adjustLastDigit(double)
	 */
	@Override
	public double adjustLastDigit(double value) {
		
		// 최초 금액에서 100 123.13 12315
//		long adjustValue = (long) (value * 100.0);
		long adjustValue = (long) (value * 1.0);
		
		String strAdjustValue = Long.toString(adjustValue);
		
		// 12315 > 1231 * 10 > 12310
//		long preValue = Long.parseLong(strAdjustValue.substring(0, strAdjustValue.length() - 1)) * 10L;
		
		// 3
//		long lastDigit = Long.parseLong(strAdjustValue.substring(strAdjustValue.length() - 1, strAdjustValue.length()));

		// 12310 + 5 = 12315
//		if (lastDigit > 2 && lastDigit <= 7) {
//			preValue += 5;
//		} else if (lastDigit > 7) {
//			preValue += 10;
//		}
		long preValue = Long.parseLong(strAdjustValue.substring(0, strAdjustValue.length() - 1)) * 10L;
	
		
		// 123.15
//		return ((double) preValue) / 100.0;
		return ((double) preValue) / 1;
	}

}
