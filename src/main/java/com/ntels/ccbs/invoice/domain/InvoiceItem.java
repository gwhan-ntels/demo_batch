package com.ntels.ccbs.invoice.domain;

import org.apache.commons.lang.StringUtils;

import com.ntels.ccbs.invoice.util.InvoiceUtils;

import net.sf.jasperreports.engine.JRDataSource;

public class InvoiceItem {

	private String itemCode;

	private String serviceName;

	private String termStartDate;

	private String termEndDate;

	private String contractBasedOffer;

	private int gst;

	private String subTotal;

	private String gstAmount;

	private String totalDue;

	private JRDataSource chargeDataSource;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getTermStartDate() {
		return termStartDate;
	}

	public void setTermStartDate(String termStartDate) {
		this.termStartDate = termStartDate;
	}

	public String getTermEndDate() {
		return termEndDate;
	}

	public void setTermEndDate(String termEndDate) {
		this.termEndDate = termEndDate;
	}

	public String getContractBasedOffer() {
		return contractBasedOffer;
	}

	public void setContractBasedOffer(String contractBasedOffer) {
		this.contractBasedOffer = contractBasedOffer;
	}

	public int getGst() {
		return gst;
	}

	public void setGst(int gst) {
		this.gst = gst;
	}

	public String getSubTotal() {
		if (StringUtils.isEmpty(subTotal) == true) {
			return "0.00";
		}

		return subTotal;
	}
	
	public double getSubTotalDouble() {
		if (StringUtils.isEmpty(subTotal) == true) {
			return 0.00;
		}
		
		return Double.parseDouble(subTotal.replaceAll(",", ""));
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = InvoiceUtils.getAmountFormat(subTotal);
	}

	public String getGstAmount() {
		if (StringUtils.isEmpty(gstAmount) == true) {
			return "0.00";
		}
		
		return gstAmount;
	}
	
	public double getGstAmountDouble() {
		if (StringUtils.isEmpty(gstAmount) == true) {
			return 0.00;
		}
		
		return Double.parseDouble(gstAmount.replaceAll(",", ""));
	}

	public void setGstAmount(double gstAmount) {
		this.gstAmount = InvoiceUtils.getAmountFormat(gstAmount);
	}

	public String getTotalDue() {
		if (StringUtils.isEmpty(totalDue) == true) {
			return "0.00";
		}

		return totalDue;
	}

	public void setTotalDue(double totalDue) {
		this.totalDue = InvoiceUtils.getAmountFormat(totalDue);
	}

	public JRDataSource getChargeDataSource() {
		return chargeDataSource;
	}

	public void setChargeDataSource(JRDataSource chargeDataSource) {
		this.chargeDataSource = chargeDataSource;
	}

}
