package com.ntels.ccbs.batch.iv.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.dao.mapper.InvoiceMapper;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.Invoice;

@Repository
public class InvoiceDaoImpl extends LazyLoadingDao implements InvoiceDao {

	@Autowired
	private InvoiceMapper invoiceMapper;

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/common/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "InvoiceMapper";
	}
	
	@Override
	public Integer getInvcRissSeq(Invoice invoice) {
		return invoiceMapper.getInvcRissSeq(invoice);
	}
	
	@Override
	public List<Invoice> getM0000DByBillSeqNo(CBillComm bill) {
		return getList("getM0000DByBillSeqNo", Invoice.class, bill);
	}
	
	@Override
	public List<Invoice> getD1010DByBillSeqNo(CBillComm bill) {
		return getList("getD1010DByBillSeqNo", Invoice.class, bill);
	}
	
	@Override
	public int insertInvoice(List<Invoice> invoiceList) {
		return insert("insertInvoice", Invoice.class, invoiceList);
	}
	
}
