package com.ntels.ccbs.batch.common.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ntels.ccbs.batch.common.entity.MailTemplate;
import com.ntels.ccbs.batch.iv.common.entity.BillInvoice;

@Service
public class SendMailServiceImpl implements SendMailService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Value("${mail.username}")
	private String senderAddress;
	
	@Value("${invoice.pdf.base.path}")
	private String pdfBaseDir;
	
	@Override
	public void sendMailWithInvoice(List<MimeMessagePreparator> mailList) {
		MimeMessagePreparator[] array = new MimeMessagePreparator[mailList.size()];
		
		for (int i = 0; i < mailList.size(); i++) {
			array[i] = mailList.get(i);
		}
		
		mailSender.send(array);
	}

	@Override
	public MimeMessagePreparator getMailPreparator(final BillInvoice billInvoice) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage message) throws Exception {

				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				
				helper.setTo(billInvoice.getEml());
				
				// 메일 제목
				StringBuffer subject = new StringBuffer(billInvoice.getPymAcntId())
						.append(" ")
						.append(billInvoice.getBillYymm())
						.append(" ")
						.append(billInvoice.getBillSeqNo())
						.append(" Invoice");

				// 전송 Invoice파일 명
				StringBuffer sendFileName = new StringBuffer(billInvoice.getBillYymm())
						.append("_")
						.append(billInvoice.getBillSeqNo())
						.append("_")
						.append(billInvoice.getPymAcntId())
						.append(".pdf");

				helper.setSubject(subject.toString());

				Map<String, Object> model = new HashMap<>();

				MailTemplate mailTemplate = new MailTemplate();
				mailTemplate.setSender(senderAddress);
				mailTemplate.setSubject(subject.toString());
				mailTemplate.setTo(billInvoice.getEml());
				mailTemplate.setFileName(sendFileName.toString());
				mailTemplate.setSendDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

				model.put("template", mailTemplate);

				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mail/mail_template.vm", "UTF-8", model);
				helper.setText(text, true);

				// 파일 첨부
				String year = billInvoice.getBillSeqNo().substring(0, 2);
				String month = billInvoice.getBillSeqNo().substring(2, 4);

				FileSystemResource res = new FileSystemResource(new File(pdfBaseDir + year + File.separator + month, billInvoice.getBillSeqNo() + ".pdf"));
				helper.addAttachment(sendFileName.toString(), res);	
			}
		};
		
		return preparator;
		
	}
	
}
