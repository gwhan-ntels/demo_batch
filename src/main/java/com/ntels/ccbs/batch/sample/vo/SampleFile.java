package com.ntels.ccbs.batch.sample.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleFile {

	private static final SimpleDateFormat loginDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String userId;

	private Long seq;

	private String platform;

	private String nationCode;

	private Date loginDate;

	private String loginYyyymmdd;

	private String loginHh;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}
	
	public void setSeq(String seq) {
		this.seq = Long.parseLong(seq);
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getNationCode() {
		return nationCode;
	}

	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	public void setLoginDate(String loginDate) throws ParseException {
		this.loginDate = loginDateFormat.parse(loginDate);
	}

	public String getLoginYyyymmdd() {
		return loginYyyymmdd;
	}

	public void setLoginYyyymmdd(String loginYyyymmdd) {
		this.loginYyyymmdd = loginYyyymmdd;
	}

	public String getLoginHh() {
		return loginHh;
	}

	public void setLoginHh(String loginHh) {
		this.loginHh = loginHh;
	}

}
