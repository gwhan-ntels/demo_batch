package com.ntels.ccbs.invoice.domain;

import com.ntels.ccbs.invoice.util.InvoiceUtils;

public class ChargeSummary {

	private String chargeName;

	private String itemCode;

	private double amount;

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStrAmount() {
		return InvoiceUtils.getAmountFormat(this.amount);
	}

}
