package com.ntels.ccbs.batch.common.service;

import java.util.List;

import org.springframework.mail.javamail.MimeMessagePreparator;

import com.ntels.ccbs.batch.iv.common.entity.BillInvoice;

public interface SendMailService {

	void sendMailWithInvoice(List<MimeMessagePreparator> mailList);

	MimeMessagePreparator getMailPreparator(BillInvoice billInvoice);

}
