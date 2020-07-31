package com.ntels.ccbs.batch.iv.tasklet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ntels.ccbs.barcode.BarcodeGenerator;
import com.ntels.ccbs.barcode.domain.BarcodeForm;
import com.ntels.ccbs.barcode.domain.BarcodeForm.BarcodeType;
import com.ntels.ccbs.barcode.domain.BarcodeForm.ImageFormat;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.tasklet.LazyLoaderLogingTasklet;
import com.ntels.ccbs.batch.iv.common.entity.BillInvoice;
import com.ntels.ccbs.batch.iv.service.NBlivb01m18Service;
import com.ntels.ccbs.invoice.domain.ChargeSummary;
import com.ntels.ccbs.invoice.domain.InvoiceItem;
import com.ntels.ccbs.invoice.domain.InvoiceItemDetail;
import com.ntels.ccbs.invoice.domain.VadsInvoice;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Component("nBlivb01m18Tasklet02")
@Scope("step")
public class NBlivb01m18Tasklet02 extends LazyLoaderLogingTasklet<BillInvoice, BillInvoice> {

	public static final String FILE_EXTENSION_JASPER = ".jasper";
	public static final String FILE_EXTENSION_JRXML = ".jrxml";
	
	@Autowired
	private NBlivb01m18Service nBlivb01m18Service;
	
	@Autowired
	private BarcodeGenerator barcodeGenerator;
	
	@Value("${invoice.resources.path}")
	private String invoiceResourcesDir;
	
	@Value("${invoice.barcode.base.path}")
	private String barcodeBaseDir;
	
	@Value("${invoice.pdf.base.path}")
	private String pdfBaseDir;
	
	@Value("${sr.cd.tax}")
	private String vatChrgItmCd;
	
	@Value("${sr.cd.rounding}")
	private String roundingChrgItmCd;
	
	private List<BillInvoice> billSeqNoSet;
	private BillInvoice prevInfo;

	private VadsInvoice masterInfo;
	private List<ChargeSummary> chargeSummaries;
	private List<InvoiceItem> invoiceDetails;
	private InvoiceItem invoiceItem;
	private List<InvoiceItemDetail> detailList;
	
	private SimpleDateFormat readDateFormat;
	private SimpleDateFormat writeDateFormat;
	
	@Override
	protected boolean isInsertPgmLog() {
		return false;
	}

	@Override
	protected boolean isUpdatePgmLog() {
		return false;
	}

	@Override
	protected LazyLoader<BillInvoice> getLoader() throws Exception {

		readDateFormat = new SimpleDateFormat("yyyyMMdd");
		writeDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		billSeqNoSet = new ArrayList<>();
		
		BillInvoice billInvoice = new BillInvoice();
		billInvoice.setBillYymm(billYymm);
		billInvoice.setSoId(soId);
		billInvoice.setBillCycl(billCycl);
		
		clog.writeLog("NBlivb01m18Tasklet.getLoader billInvoice\n{}", clog.objectToString(billInvoice));
		
		return nBlivb01m18Service.getBillInvoiceListForPrint(billInvoice);
	}

	@Override
	protected BillInvoice process(BillInvoice item) {
		
		if (prevInfo == null || prevInfo.equalsBill(item) == false) {

			try {
				if (billSeqNoSet.isEmpty() == false) {
					generatePdf(billSeqNoSet);	
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			billSeqNoSet.clear();
			prevInfo = item;
		}

		billSeqNoSet.add(item);
		
		if (isLastItem == true) {

			if (billSeqNoSet.isEmpty() == false) {
				try {
					generatePdf(billSeqNoSet);	
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		}

		return item;
	}
	
	private void generatePdf(List<BillInvoice> invoiceList) throws ParseException, JRException, IOException {
		
		chargeSummaries = new ArrayList<>();
		invoiceDetails = new ArrayList<>();
		detailList = new ArrayList<>();
		
		String prevProdCd = null;
		BillInvoice taxInfo = null;
		
		for (int i = 0; i < invoiceList.size(); i++) {
			
			BillInvoice item = invoiceList.get(i);
			clog.writeLog(clog.objectToString(item));
			
			if (i == 0) {
				
				prevProdCd = item.getProdCd();
				prevInfo = item;
				
				// 청구서 마스터 정보 생성
				masterInfo = new VadsInvoice();
				masterInfo.setCurrency(item.getCrncyCd());
				
				masterInfo.setAccount(item.getPymAcntId());
				masterInfo.setInvoiceNo(item.getBillSeqNo());
				masterInfo.setInvoiceDate(writeDateFormat.format(new Date()));
				masterInfo.setPreviousDues(item.getUnpayAmt());
				masterInfo.setPayments(item.getRcptAmt());
				masterInfo.setAdjustments(item.getAdjAmt());
				masterInfo.setCurrentCharges(item.getTotBillAmt());
				masterInfo.setRevenueCode(item.getZipCd());
				
				masterInfo.setCustNm(item.getCustNm());
				masterInfo.setBaseAddr(item.getBaseAddr() + " " + item.getAddrDtl());
				masterInfo.setAddrDtl(item.getCity() + " " + item.getStateNm());
				masterInfo.setEml(item.getEml());
				
				double totalAmountDue = item.getTotBillAmt() - item.getRcptAmt() + item.getAdjAmt();
				
				masterInfo.setTotalAmountDue(totalAmountDue);
				
				masterInfo.setResourcesDir(invoiceResourcesDir);
				
				if (totalAmountDue <= 0) {
					masterInfo.setPaymentDueDate("PAID");
				} else {
					try {
						masterInfo.setPaymentDueDate(writeDateFormat.format(readDateFormat.parse(item.getPayDueDt())));	
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				
				masterInfo.setDeposit(item.getPrepayBal());
				
				invoiceItem = new InvoiceItem();
				invoiceItem.setItemCode(item.getProdCd());
				invoiceItem.setServiceName(item.getProdNm());
				setTermDate(item, invoiceItem);
				invoiceItem.setContractBasedOffer(item.getPromCnt() + " Months");
			}
			
			if (item.getChrgItmCd().equals(vatChrgItmCd) == true || item.getChrgItmCd().equals(roundingChrgItmCd) == true) {
				if (item.getChrgItmCd().equals(vatChrgItmCd) == true) {
					taxInfo = item;
				} else if (item.getChrgItmCd().equals(roundingChrgItmCd) == true) {
					ChargeSummary chargeSummary = new ChargeSummary();
					chargeSummary.setChargeName("Total Due");
					chargeSummary.setAmount(item.getTotBillAmt() - item.getBillAmt());
					
					chargeSummaries.add(chargeSummary);
					
					chargeSummary = new ChargeSummary();
					chargeSummary.setChargeName("Rounding Adjustments");
					chargeSummary.setAmount(item.getBillAmt());
					
					chargeSummaries.add(chargeSummary);
					
					chargeSummary = new ChargeSummary();
					chargeSummary.setChargeName("Total Current Charges");
					chargeSummary.setAmount(item.getTotBillAmt());
					
					chargeSummaries.add(chargeSummary);
					
					masterInfo.setChargeSummaryDataSource(new JRBeanCollectionDataSource(chargeSummaries));
				}
				continue;
			}
			
			if (prevProdCd.equals(item.getProdCd()) == true) {
				try {
					InvoiceItemDetail itemDetail = new InvoiceItemDetail(item.getSvcRateItmTypNm()
							, writeDateFormat.format(readDateFormat.parse(item.getPayDueDt())), item.getCrncyCd(), item.getBillAmt() / (double) item.getUsgCnt(), item.getUsgCnt(), item.getBillAmt());
					clog.writeLog("INVOICE ITEM DETAIL ADD : CHRG ITM NM : {}, BILL AMT : {}", item.getSvcRateItmTypNm(), item.getBillAmt());
					
					String preiod = "";
					
					if (StringUtils.isEmpty(item.getUseStrtDt()) == false) {
						preiod = getTermStrtDate(item.getUseStrtDt());
					}
					
					if (StringUtils.isEmpty(item.getUseEndDt()) == false) {
						preiod += "-";
						preiod += getTermEndDate(item.getUseEndDt());
					}
					
					itemDetail.setSubscriptionPeriod(preiod);;
					
					detailList.add(itemDetail);	
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				invoiceItem.setSubTotal(invoiceItem.getSubTotalDouble() + item.getBillAmt());
			} else {
				
				clog.writeLog("CHANGE PROD PREV CD : {}, CURRENT CD : {}", prevProdCd, item.getProdCd());
				
				for (InvoiceItemDetail detail : detailList) {
					clog.writeLog("ITEM DETAIL\n{}", clog.objectToString(detail));
				}
				invoiceItem.setChargeDataSource(new JRBeanCollectionDataSource(detailList));
				
				invoiceItem.setGst(6);
				
				if (taxInfo != null) {
					invoiceItem.setGstAmount(taxInfo.getBillAmt());	
				}

				invoiceItem.setTotalDue(invoiceItem.getSubTotalDouble() + invoiceItem.getGstAmountDouble());
				invoiceDetails.add(invoiceItem);
				
				invoiceItem = new InvoiceItem();
				invoiceItem.setItemCode(item.getProdCd());
				invoiceItem.setServiceName(item.getProdNm());
				
				setTermDate(item, invoiceItem);
				invoiceItem.setContractBasedOffer(item.getPromCnt() + " Months");
				
				detailList = new ArrayList<>();
				
				try {
					InvoiceItemDetail itemDetail = new InvoiceItemDetail(item.getSvcRateItmTypNm()
							, writeDateFormat.format(readDateFormat.parse(item.getPayDueDt())), item.getCrncyCd(), item.getBillAmt() / (double) item.getUsgCnt(), item.getUsgCnt(), item.getBillAmt());
					clog.writeLog("INVOICE ITEM DETAIL ADD : CHRG ITM NM : {}, BILL AMT : {}", item.getSvcRateItmTypNm(), item.getBillAmt());
					
					String preiod = "";
					
					if (StringUtils.isEmpty(item.getUseStrtDt()) == false) {
						preiod = getTermStrtDate(item.getUseStrtDt());
					}

					if (StringUtils.isEmpty(item.getUseEndDt()) == false) {
						preiod += "-";
						preiod += getTermEndDate(item.getUseEndDt());
					}

					itemDetail.setSubscriptionPeriod(preiod);;
					
					detailList.add(itemDetail);	
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

				invoiceItem.setSubTotal(invoiceItem.getSubTotalDouble() + item.getBillAmt());
				
				prevProdCd = item.getProdCd();
			}
		}

		for (InvoiceItemDetail detail : detailList) {
			clog.writeLog("ITEM DETAIL\n{}", clog.objectToString(detail));
		}
		invoiceItem.setChargeDataSource(new JRBeanCollectionDataSource(detailList));
		
		invoiceItem.setGst(6);
		
		if (taxInfo != null) {
			invoiceItem.setGstAmount(taxInfo.getBillAmt());	
		}
		
		invoiceItem.setTotalDue(invoiceItem.getSubTotalDouble() + Double.parseDouble(invoiceItem.getGstAmount()));
		invoiceDetails.add(invoiceItem);
		
		generatePdf();
	}
	
	private void setTermDate(BillInvoice billInvoice, InvoiceItem invoiceItem) throws ParseException {
		invoiceItem.setTermStartDate(getTermStrtDate(billInvoice.getUseStrtDt()));
		invoiceItem.setTermEndDate(getTermEndDate(billInvoice.getUseEndDt()));
	}
	
	private String getTermStrtDate(String strtDt) throws ParseException {
		String termStrtDate = writeDateFormat.format(readDateFormat.parse(strtDt));
		clog.writeLog("GET TERM STRT DATE strtDt : {}, parsedDt : {}", strtDt, termStrtDate);
		return termStrtDate;
	}
	
	private String getTermEndDate(String endDt) throws ParseException {
		// 사용 종료일은 1일 빼주도록 한다.
		Date termEndDate = readDateFormat.parse(endDt);
		Calendar cal = Calendar.getInstance();
		cal.setTime(termEndDate);
		cal.add(Calendar.DATE, -1);
		
		String strTermEndDate = writeDateFormat.format(cal.getTime()); 
		clog.writeLog("GET TERM END DATE endDt : {}, parsedDt : {}", endDt, strTermEndDate);
		return strTermEndDate;
	}
	
	private void generatePdf() throws ParseException, JRException, IOException {
		
		if (masterInfo == null || StringUtils.isEmpty(masterInfo.getInvoiceNo()) == true) {
			return;
		}
		
		clog.writeLog("=========================================================");
		clog.writeLog("GENERATE PDF");
		clog.writeLog("MASTER INFO\n{}", clog.objectToString(masterInfo));
		clog.writeLog("SUMMARYS");
		for (ChargeSummary summary : chargeSummaries) {
			clog.writeLog("summary\n{}", clog.objectToString(summary));
		}
		clog.writeLog("INVOICE DETAIL SIZE : {}", invoiceDetails.size());
		for (InvoiceItem invoiceDetail : invoiceDetails) {
			clog.writeLog("INVOICE ITEM\n{}", clog.objectToString(invoiceDetail));
		}
		clog.writeLog("=========================================================");
		
		Map<String, Object> params = new HashMap<>();
		
		// 첫번째 더미 데이터
		invoiceDetails.add(0, new InvoiceItem());
		
		params.put("VADS_INVOICE", masterInfo);
		params.put("LIST_SIZE", invoiceDetails.size());
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(invoiceDetails);
		params.put("SECOND_DATASOURCE", jrDataSource);

//
		// 바코드 생성
		BarcodeForm barcodeForm = new BarcodeForm();
		barcodeForm.setBarcodeData(masterInfo.getInvoiceNo());
		barcodeForm.setBarcodeType(BarcodeType.CODE39);
		barcodeForm.setImageFormat(ImageFormat.PNG);
		
		String year = masterInfo.getInvoiceNo().substring(0, 2);
		String month = masterInfo.getInvoiceNo().substring(2, 4);
		
		String barcodeDir = barcodeBaseDir + year + File.separator + month;
		
		File barcodeFile = new File(barcodeDir, masterInfo.getInvoiceNo() + "." + barcodeForm.getImageFormat().getFormat());
		barcodeForm.setOutputFile(barcodeFile);

		barcodeGenerator.ganerateToFile(barcodeForm);

		params.put("barcodeImg", barcodeFile.getPath());
		
		// jasper파일 체크
		String invoiceName = "VadsInvoice";
		String summaryName = "vads_charge_summary";
		String detailName = "vads_charge_item_detail";
		
		File master = new File(getJasperFilePath(invoiceName));
		
		if (master.exists() == false) {
			compileJasper(invoiceName);
		}
		
		if (new File(getJasperFilePath(summaryName)).exists() == false) {
			compileJasper(summaryName);
		}
		
		if (new File(getJasperFilePath(detailName)).exists() == false) {
			compileJasper(detailName);
		}

		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(master.getPath());
		byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, params, jrDataSource);
//
		File outFile = new File(pdfBaseDir + year + File.separator + month, masterInfo.getInvoiceNo() + ".pdf");
		
		if (outFile.getParentFile().exists() == false) {
			outFile.getParentFile().mkdirs();
		}
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(outFile);
			fos.write(bytes);
			fos.flush();	
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	private void compileJasper(String jasperName) throws JRException {
		JasperCompileManager.compileReportToFile(getJrxmlFilePath(jasperName), getJasperFilePath(jasperName));
	}
	
	private String getJasperFilePath(String jasperName) {
		return invoiceResourcesDir + jasperName + FILE_EXTENSION_JASPER;
	}
	
	private String getJrxmlFilePath(String jrxmlName) {
		return invoiceResourcesDir + jrxmlName + FILE_EXTENSION_JRXML;
	}

	@Override
	protected void write(List<BillInvoice> itemList) {
		// 아무것도 안함
	}

	@Override
	protected RepeatStatus end() {
		return null;
	}

}
