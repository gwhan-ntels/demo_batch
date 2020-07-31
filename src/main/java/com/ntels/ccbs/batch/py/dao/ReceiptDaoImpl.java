package com.ntels.ccbs.batch.py.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.BatchDao;
import com.ntels.ccbs.batch.py.dao.mapper.ReceiptMapper;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.entity.Common;

import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptCancel;
import com.ntels.ccbs.batch.py.entity.ReceiptCancelAppl;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

@Repository
public class ReceiptDaoImpl extends LazyLoadingDao implements ReceiptDao {
	
	@Autowired
	private ReceiptMapper receiptMapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/py/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	protected String getMapperName() {
		return "receiptMapper";
	}
	
	@Override
	public int insertReceiptDetail(ReceiptDetail receiptDetail) {
		return receiptMapper.insertReceiptDetail(receiptDetail);
	}
	
	/**
	 * 청구내역에 수정된 데이타를 수납상세내역에 등록한다.
	 * 리스트로 넘어온 대량의 데이터를 배치로 넣기 위하여 SqlSession을 새로 얻어와
	 * 배치모드로 실행을 한다.
	 * @param pyRcptDtl
	 * @return
	 */
	@Override
	public int insertReceiptDetail(final List<ReceiptDetail> pyRcptDtlList) {
		int insertCount = batchJob(ReceiptMapper.class, new BatchJob<ReceiptMapper>() {

			@Override
			public int job(ReceiptMapper mapper) {
				int size = pyRcptDtlList.size();
				int cnt = 0;
				
				for (int i = 0; i < size; i++) {
					mapper.insertReceiptDetail(pyRcptDtlList.get(i));
					cnt++;
				}
				return cnt;
			}
		});
		
		return insertCount;
	}
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록한다.
	 * @param receipt
	 * @return
	 */
	@Override
	public int insertReceipt(Receipt receipt) {
		return receiptMapper.insertReceipt(receipt);
	}
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록한다.
	 * @param receiptList
	 * @return
	 */
	@Override
	public int insertReceipt(final List<Receipt> receiptList) {
		int insertCount = batchJob(ReceiptMapper.class, new BatchJob<ReceiptMapper>() {

			@Override
			public int job(ReceiptMapper mapper) {
				
				int size = receiptList.size();
				int cnt = 0;
				
				for (int i = 0; i < size; i++) {
					mapper.insertReceipt(receiptList.get(i));
					cnt++;
				}
				
				return cnt;
			}
		});
		
		return insertCount;
	}
	
	/**
	 * 대리점수납정보에 등록하기위해 RcptDtl정보를 조회한다.
	 * dpstSeqNo값이 있으면 dpstSeqNo값으로 조회를 하고
	 * dpstSeqNo값이 없으면 pymSeqNo값으로 조회를 한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public List<ReceiptDetail> getReceiptDetailforBondRcptTr(String dpstDeqNo, String pymSeqNo) {
		return receiptMapper.getReceiptDetailforBondRcptTr(dpstDeqNo, pymSeqNo);
	}
	
	/**
	 * 해당 수납 시퀀스 번호에 해당하는 납부 금액의 합을 가져온다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public Double getSumRcptAmt(String pymSeqNo) {
		return receiptMapper.getSumRcptAmt(pymSeqNo);
	}
	
	/**
	 * 수납내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public Receipt getReceipt(String pymSeqNo) {
		return receiptMapper.getReceipt(pymSeqNo);
	}
	
	/**
	 * 수납상세내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public List<ReceiptDetail> getReceiptDetail(String pymSeqNo) {
		return receiptMapper.getReceiptDetail(pymSeqNo);
	}
	
	/**
	 * 입금시퀀스에 해당하는 수납 상세내역을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public List<ReceiptDetail> getReceiptDetailList(String dpstSeqNo, String pymSeqNo) {
		ReceiptDetail receiptDetail = new ReceiptDetail();
		receiptDetail.setPymSeqNo(pymSeqNo);
		receiptDetail.setDpstSeqNo(dpstSeqNo);
		return getList(dbKind, "getReceiptDetailList", ReceiptDetail.class, receiptDetail);
		//return receiptMapper.getReceiptDetailList(dpstSeqNo, pymSeqNo);
	}
	
	/**
	 * 입금시퀀스에 해당하는 수납을 취소한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int updateReceiptCancel(String dpstSeqNo, String pymSeqNo) {
		ReceiptDetail receiptDetail = new ReceiptDetail();
		receiptDetail.setDpstSeqNo(dpstSeqNo);
		receiptDetail.setPymSeqNo(pymSeqNo);
		return updateOne(dbKind,"updateReceiptCancel",receiptDetail);
		//return receiptMapper.updateReceiptCancel(dpstSeqNo, pymSeqNo);
	}
	
	/**
	 * 취소된 입금내역 시퀀스로 입금구분값을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public String getCanceledDpstCl(String dpstSeqNo) {
		return receiptMapper.getCanceledDpstCl(dpstSeqNo);
	}
	
	/**
	 * 수납대상금액에서 수납적용금액을 뺀 값을 조회
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public List<Receipt> getReceiptBillInfo(String dpstSeqNo) {
		return receiptMapper.getReceiptBillInfo(dpstSeqNo);
	}
	
	/**
	 * 해당 수납번호로 이미 취소 여부를 조회한다.
	 * @return
	 */
	@Override
	public String getCancelYn(String pymSeqNo) {	
		//System.out.println("pymSeqNo=====>"+pymSeqNo);
		Receipt receipt = new Receipt();
		receipt.setPymSeqNo(pymSeqNo);
		Receipt a = (Receipt)getOne(dbKind, "getCancelYn", Receipt.class, receipt);		
		String cnclYn = a.getCnclYn();		
		return cnclYn;
		//return receiptMapper.getCancelYn(pymSeqNo);
	}
	
	/**
	 * 수납취소결재신청에 등록한다.
	 * @param receiptCancelAppl
	 * @return
	 */
	@Override
	public int insertReceiptCancelAppl(ReceiptCancelAppl receiptCancelAppl) {
		return receiptMapper.insertReceiptCancelAppl(receiptCancelAppl);
	}
	
	/**
	 * 수납취소내역에 등록한다.
	 * @return
	 */
	@Override
	public int insertReceiptCancel(ReceiptCancel receiptCancel) {
		return receiptMapper.insertReceiptCancel(receiptCancel);
	}
	
	/**
	 * 수납취소상세내역에 등록한다.
	 * @param receiptDetail
	 * @return
	 */
	@Override
	public int insertReceiptCancelDetail(final List<ReceiptDetail> receiptDetailList) {
		return batchJob(ReceiptMapper.class, new BatchJob<ReceiptMapper>() {

			@Override
			public int job(ReceiptMapper mapper) {
				int cnt = 0;
				
				for (ReceiptDetail receiptDetail : receiptDetailList) {
					receiptMapper.insertReceiptCancelDetail(receiptDetail);
					cnt++;
				}
				
				return cnt;
			}
		});
	}
	
}
