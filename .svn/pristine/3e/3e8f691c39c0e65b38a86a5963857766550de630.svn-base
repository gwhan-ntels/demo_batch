package com.ntels.ccbs.batch.iv.common.entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.ntels.ccbs.batch.iv.common.service.CBLService;

public class IspInfo {

	@Autowired
	private CBLService cblService;
	
	private String timNo;
	
	private String name;
	
	private String address;
	
	private String url;
	
	private String tel;
	
	private String fax;
	
	private String email;

	public IspInfo(BillCyclStp billCyclStp) {
		this.timNo = cblService.getIspInfoTimNo(billCyclStp);
		this.name = cblService.getIspInfoName(billCyclStp);
		this.address = cblService.getIspInfoAddress(billCyclStp);
		this.url = cblService.getIspInfoUrl(billCyclStp);
		this.tel = cblService.getIspInfoTel(billCyclStp);
		this.fax = cblService.getIspInfoFax(billCyclStp);
		this.email = cblService.getIspInfoEmail(billCyclStp);
	}
	
	public String getTimNo() {
		return timNo;
	}

	public void setTimNo(String timNo) {
		this.timNo = timNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
