package com.ntels.ccbs.batch.py.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import com.ntels.ccbs.batch.common.dao.BatchDao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.dao.mapper.PaymentMapper;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

@Repository
public class PaymentDaoImpl extends LazyLoadingDao implements PaymentDao {
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/py/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	protected String getMapperName() {
		return "paymentMapper";
	}
	
	@Override
	public Double getBillRcptAmt(CBillComm bill) {
		return getOne(dbKind, "getBillRcptAmt", CBillComm.class, bill);
		//return paymentMapper.getBillRcptAmt(bill);
	}
	
	@Override
	public Double getUnpayAmtFromBillMast(String billSeqNo) {
		return paymentMapper.getUnpayAmtFromBillMast(billSeqNo);
	}
	
	@Override
	public CBillComm getBillMastInfo(String billSeqNo) {
		return paymentMapper.getBillMastInfo(billSeqNo);
	}
	
	@Override
	public List<CBillComm> getBillListByBillSeqNo(String billSeqNo) {
		return paymentMapper.getBillListByBillSeqNo(billSeqNo);
	}
	
	@Override
	public int insertRcptDtlSelectBill(final List<ReceiptDetail> receiptDetailList) {
		return batchJob(PaymentMapper.class, new BatchJob<PaymentMapper>() {

			@Override
			public int job(PaymentMapper mapper) {
				int cnt = 0;
				
				for (ReceiptDetail receiptDetail : receiptDetailList) {
					mapper.insertRcptDtlSelectBill(receiptDetail);
				}
				
				return cnt;
			}
		});
	}
	
	@Override
	public int insertRcptSelectBill(Receipt receipt) {
		return paymentMapper.insertRcptSelectBill(receipt);
	}
	
	@Override
	public int updateBillMastRcptAmt(final List<CBillComm> billList) {
		return batchJob(PaymentMapper.class, new BatchJob<PaymentMapper>() {

			@Override
			public int job(PaymentMapper mapper) {
				int cnt = 0;
				
				for (CBillComm bill : billList) {
					mapper.updateBillMastRcptAmt(bill);
					cnt++;
				}
				
				return cnt;
			}
		});
	}
	
	@Override
	public int updateFullPayBill(CBillComm bill) {
		return paymentMapper.updateFullPayBill(bill);
	}
	
	@Override
	public int updateFullPayBill(final List<CBillComm> billList) {
		return batchJob(PaymentMapper.class, new BatchJob<PaymentMapper>() {

			@Override
			public int job(PaymentMapper mapper) {
				int cnt = 0;
				
				for (CBillComm bill : billList) {
					mapper.updateFullPayBill(bill);
					cnt++;
				}
				
				return cnt;
			}
		});
	}
	
	@Override
	public int updateBillRcptAmt(CBillComm bill) {
		return paymentMapper.updateBillRcptAmt(bill);
	}
	
	@Override
	public int updateBillRcptAmt(final List<CBillComm> billList) {
		return batchJob(PaymentMapper.class, new BatchJob<PaymentMapper>() {

			@Override
			public int job(PaymentMapper mapper) {
				int cnt = 0;
				
				for (CBillComm bill : billList) {
					mapper.updateBillRcptAmt(bill);
					cnt++;
				}
				
				return cnt;
			}
		});
	}
	
	@Override
	public int updateDpstProc(Deposit deposit) {
		return paymentMapper.updateDpstProc(deposit);
	}
	
	@Override
	public int updateDpstProc(final List<Deposit> depositList) {
		return batchJob(PaymentMapper.class, new BatchJob<PaymentMapper>() {

			@Override
			public int job(PaymentMapper mapper) {
				int cnt = 0;
				
				for (Deposit deposit : depositList) {
					mapper.updateDpstProc(deposit);
					cnt++;
				}
				
				return cnt;
			}
		});
	}
	
	/**
	 * 입금취소를 할 때 참조했던 청구내역의 금액과 완납여부를 갱신한다.
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillCancel(CBillComm bill) {
		return updateOne(dbKind, "updateBillCancel", bill);
		//return paymentMapper.updateBillCancel(bill);
	}
	
	/**
	 * 입금취소를 할 때 참조했던 청구내역의 금액과 완납여부를 갱신한다.
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillCancel(final List<CBillComm> billList) {
		return batchJob(PaymentMapper.class, new BatchJob<PaymentMapper>() {

			@Override
			public int job(PaymentMapper mapper) {
				int cnt = 0;
				for (CBillComm bill : billList) {
					mapper.updateBillCancel(bill);
					cnt++;
				}
				return cnt;
			}
		});
	}
	
	@Override
	public int updateBillMastCancel(CBillComm bill) {
		return paymentMapper.updateBillMastCancel(bill);
	}
	
	@Override
	public int updateBillMastCancel(final List<CBillComm> billList) {
		return batchJob(PaymentMapper.class, new BatchJob<PaymentMapper>() {

			@Override
			public int job(PaymentMapper mapper) {
				int cnt = 0;
				for (CBillComm bill : billList) {
					mapper.updateBillMastCancel(bill);
					cnt++;
				}
				return cnt;
			}
		});
	}
	
}
