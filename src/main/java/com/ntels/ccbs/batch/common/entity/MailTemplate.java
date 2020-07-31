package com.ntels.ccbs.batch.common.entity;

public class MailTemplate {

	private String sender;

	private String subject;

	private String to;

	private String fileName;

	private String sendDate;

	public String getSender() {
		return sender;
	}

	public String getSubject() {
		return subject;
	}

	public String getTo() {
		return to;
	}

	public String getFileName() {
		return fileName;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

}
