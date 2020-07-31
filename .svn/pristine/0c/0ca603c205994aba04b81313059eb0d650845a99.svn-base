package com.ntels.ccbs.batch.iv.tasklet;

import java.util.List;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.SendMailService;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillInvoice;
import com.ntels.ccbs.batch.iv.service.NBlivb01m18Service;

@Component("nBlivb01m18Tasklet03")
@Scope("step")
public class NBlivb01m18Tasklet03 extends LazyLoaderLogingTasklet<BillInvoice, MimeMessagePreparator> {
	
	@Autowired
	private NBlivb01m18Service nBlivb01m18Service;
	
	@Autowired
	private SendMailService sendMailService;
	
	@Value("${invoice.pdf.base.path}")
	private String pdfBaseDir;
	
	@Value("${mail.dev}")
	private boolean isMailDev;
	
	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return true;
	}

	@Override
	protected LazyLoader<BillInvoice> getLoader() throws Exception {
		
		BillInvoice billInvoice = new BillInvoice();
		billInvoice.setBillYymm(billYymm);
		billInvoice.setSoId(soId);
		billInvoice.setBillCycl(billCycl);

		clog.writeLog("NBlivb01m18Tasklet03.getLoader billInvoice\n{}", clog.objectToString(billInvoice));
		
		return nBlivb01m18Service.getBillInvoiceListForEml(billInvoice);
	}

	@Override
	protected MimeMessagePreparator process(BillInvoice item) {
		return sendMailService.getMailPreparator(item);
	}

	@Override
	protected void write(List<MimeMessagePreparator> itemList) {
		// 메일 전송
		// 개발모드가 아닐 경우에만 메일을 전송한다.
		if (isMailDev == false) {
//			sendMailService.sendMailWithInvoice(itemList);	
		}
	}

	@Override
	protected RepeatStatus end() {
		return null;
	}

}
