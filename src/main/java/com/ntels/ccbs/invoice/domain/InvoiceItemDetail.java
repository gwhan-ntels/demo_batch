package com.ntels.ccbs.invoice.domain;

import com.ntels.ccbs.invoice.util.InvoiceUtils;

public class InvoiceItemDetail {

	private String chargeName;

	private String subscriptionPeriod;

	private String currency;

	private String unitPrice;

	private String quantity;

	private String total;
	
	public InvoiceItemDetail() {}

	public InvoiceItemDetail(String chargeName, String subscriptionPeriod, String currency, double unitPrice,
			double quantity, double total) {
		this.chargeName = chargeName;
		this.subscriptionPeriod = subscriptionPeriod;
		this.currency = currency;
		setUnitPrice(unitPrice);
		setQuantity(quantity);
		setTotal(total);
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getSubscriptionPeriod() {
		return subscriptionPeriod;
	}

	public void setSubscriptionPeriod(String subscriptionPeriod) {
		this.subscriptionPeriod = subscriptionPeriod;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = InvoiceUtils.getAmountFormat(unitPrice);
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = InvoiceUtils.getAmountFormat(quantity);
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = InvoiceUtils.getAmountFormat(total);
	}

}
