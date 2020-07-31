package com.ntels.ccbs.batch.ch.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CodeProperties {

	@Value("${currency_code_kr}")
	private String currencyCodeKr;

	@Value("${currency_code_jp}")
	private String currencyCodeJp;

	@Value("${currency_code_us}")
	private String currencyCodeUs;

	public String getCurrencyCodeKr() {
		return currencyCodeKr;
	}

	public void setCurrencyCodeKr(String currencyCodeKr) {
		this.currencyCodeKr = currencyCodeKr;
	}

	public String getCurrencyCodeJp() {
		return currencyCodeJp;
	}

	public void setCurrencyCodeJp(String currencyCodeJp) {
		this.currencyCodeJp = currencyCodeJp;
	}

	public String getCurrencyCodeUs() {
		return currencyCodeUs;
	}

	public void setCurrencyCodeUs(String currencyCodeUs) {
		this.currencyCodeUs = currencyCodeUs;
	}

}
