package com.ntels.ccbs.batch.iv.common.service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.dao.InvoiceDao;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.Invoice;
import com.ntels.ccbs.batch.iv.common.entity.IspInfo;

@Service
public class InvoiceServiceImpl extends BaseService implements InvoiceService {
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Override
	public Integer getInvcRissSeq(Invoice invoice) {
		
		checkEmpty(invoice.getBillSeqNo(), "billSeqNo");
		checkEmpty(invoice.getBillYymm(), "billYymm");
		checkEmpty(invoice.getSoId(), "soId");
		
		return invoiceDao.getInvcRissSeq(invoice);
	}
	
	@Override
	public List<Invoice> getM0000DByBillSeqNo(CBillComm bill) {

		checkEmpty(bill.getBillSeqNo(), "billSeqNo");
		checkEmpty(bill.getBillYymm(), "billYymm");
		checkEmpty(bill.getSoId(), "soId");
		checkEmpty(bill.getPymAcntId(), "pymAcntId");
		
		return invoiceDao.getM0000DByBillSeqNo(bill);
	}
	
	@Override
	public List<Invoice> getD1010DByBillSeqNo(CBillComm bill) {
		
		checkEmpty(bill.getBillSeqNo(), "billSeqNo");
		checkEmpty(bill.getBillYymm(), "billYymm");
		checkEmpty(bill.getSoId(), "soId");
		
		List<Invoice> invoiceList = invoiceDao.getD1010DByBillSeqNo(bill);
		sortAndRankInvcSectSeq(invoiceList, "1010");
		
		return invoiceList;
		
	}
	
	private void sortAndRankInvcSectSeq(List<Invoice> invoiceList, String prefix) {
		
		if (invoiceList == null || invoiceList.isEmpty() == true) {
			return;
		}
		
		// 인보이스 정렬
		invoiceList.sort(new Comparator<Invoice>() {

			@Override
			public int compare(Invoice o1, Invoice o2) {
				if (o1.getBillSeqNo().equals(o2.getBillSeqNo()) == false) {
					return o1.getBillSeqNo().compareTo(o2.getBillSeqNo());
				}

				return o1.getPrintPriNo() - o2.getPrintPriNo();
			}
		});
		
		int rank = 1;
		String oldBillSeqNo = "";
		
		// 출력우선순위 번호 매기기
		for (Invoice invoice : invoiceList) {
			if (oldBillSeqNo.equals(invoice.getBillSeqNo()) == false) {
				rank = 1;
				oldBillSeqNo = invoice.getBillSeqNo();
			}

			String rankTail = String.format("%06d", rank);
			rank++;
			
			invoice.setInvcSectSeq(Long.parseLong(prefix + rankTail));
		}
		
	}
	
	@Override
	public int insertInvoice(List<Invoice> invoiceList) {
		return invoiceDao.insertInvoice(invoiceList);
	}
	
	@Override
	public void printByBillSeqNo(CBillComm bill) {
		printUi(bill);
	}
	
	@Override
	public void printEmail() {
		
	}

	@Override
	public void printUi(CBillComm bill) {
		
		// 청구번호로부터 필요한 정보 추출하기
		String billYymm = "20" + bill.getBillSeqNo().substring(0, 4);
		String billCycl = bill.getBillSeqNo().substring(4, 6);
		String pymAcntId = bill.getBillSeqNo().substring(8, 18);
		
		bill.setBillYymm(billYymm);
		bill.setBillCycl(billCycl);
		bill.setPymAcntId(pymAcntId);
		
		BillCyclStp billCyclStp = new BillCyclStp();
		billCyclStp.setBillYymm(billYymm);
		billCyclStp.setBillCycl( billCycl);
		billCyclStp.setSoId(bill.getSoId());
		
		IspInfo ispInfo = new IspInfo(billCyclStp);

//		String useYymm = "";
//		
//		try {
//			useYymm = CUtil.addMonths(bill.getBillYymm(), -1);		
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		
		Invoice searchInvoice = new Invoice();
		searchInvoice.setBillSeqNo(bill.getBillSeqNo());
		searchInvoice.setSoId(bill.getSoId());
		searchInvoice.setBillYymm(billYymm);
		
		Integer invcRissSeq = getInvcRissSeq(searchInvoice);
		
		int newInvcRissSeq = 0;
//		int maxInvcRissSeq = 0;
		
		if (invcRissSeq == null) {
			if ("00".equals(billCycl) == true) {
				newInvcRissSeq = 1;
			} else {
				newInvcRissSeq = -1;
			}
			
//			maxInvcRissSeq = 0;
		} else {
			newInvcRissSeq = invcRissSeq + 1;
//			maxInvcRissSeq = invcRissSeq;
		}
		
		List<Invoice> m0000dList = getM0000DByBillSeqNo(bill);
		
		SimpleDateFormat ddMmYyyyFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
		
		for (Invoice invoice : m0000dList) {
			invoice.setInvcRissSeq((long) newInvcRissSeq);
			invoice.setInvcSectSeq(0L);
			invoice.setFld000("M0000D");
			
			try {
				invoice.setFld006(ddMmYyyyFormat.format(yyyyMMddFormat.parse(invoice.getFld006())) + "(DD/MM/YYYY)");
				invoice.setFld008(ddMmYyyyFormat.format(yyyyMMddFormat.parse(invoice.getFld008())) + "(DD/MM/YYYY)");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			invoice.setFld092(ispInfo.getEmail());
			invoice.setFld093(ispInfo.getTimNo());
			invoice.setFld094(ispInfo.getName());
			invoice.setFld095(ispInfo.getAddress());
			invoice.setFld096(ispInfo.getUrl());
			invoice.setFld097(ispInfo.getTel());
			invoice.setFld098(ispInfo.getFax());
			invoice.setFld099(getDttm());
		}
		
		insertInvoice(m0000dList);
		m0000dList.clear();
		m0000dList = null;
		
		List<Invoice> d1010dList = getD1010DByBillSeqNo(bill);
		
		for (Invoice invoice : d1010dList) {
			invoice.setInvcRissSeq((long) newInvcRissSeq);
			invoice.setFld000("D1010D");
		}
		
		insertInvoice(d1010dList);
		d1010dList.clear();
		d1010dList = null;
	
	}
	
}