package com.ntels.ccbs.batch.py.entity;

public class NBlpyb09m02 {

	// record data
	private int recordData;
	
	// group id
	private String groupId;
	
	// batch seq
	private int batchSeq;
	
	// 작업년월
	private String workYymm;
	
	// job base
	private String jobBase;
	
	// 등록자 ID
	private String user;
	
	// SO_ID
	private String soId;
	
	// 구분자로 자른 데이터 배열
	private String[] data;

	public int getRecordData() {
		return recordData;
	}

	public void setRecordData(int recordData) {
		this.recordData = recordData;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int getBatchSeq() {
		return batchSeq;
	}

	public void setBatchSeq(int batchSeq) {
		this.batchSeq = batchSeq;
	}

	public String getWorkYymm() {
		return workYymm;
	}

	public void setWorkYymm(String workYymm) {
		this.workYymm = workYymm;
	}

	public String getJobBase() {
		return jobBase;
	}

	public void setJobBase(String jobBase) {
		this.jobBase = jobBase;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSoId() {
		return soId;
	}

	public void setSoId(String soId) {
		this.soId = soId;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}
	
	
}
