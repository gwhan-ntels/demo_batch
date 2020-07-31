package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;

public interface GenerateBillWorkDao {

	/**
	 * 청구내역 생성을 위한 계산 정보를 mybatis연동으로 조회한다.
	 * @param billWork
	 * @return
	 */
	List<BillWork> getChInfoListForBillWork(BillWork billWork);
	
	/**
	 * 청구내역 생성을 위한 계산 정보를 jdbc연동을 통하여 LazyLoader의 형태로 조회환다.
	 * @param billWork
	 * @return
	 */
	LazyLoader<BillWork> getChInfoListForBillWorkLazy(BillWork billWork);
	
	/**
	 * 청구내역 저장
	 * @param billWorkList
	 * @return
	 */
	int insertBillWork(List<BillWork> billWorkList);

	/**
	 * 자동이체 할인 내역 생성을 위한 청구내역 정보를 mybatis연동으로 조회한다.
	 * @param billWork
	 * @return
	 */
	List<BillWork> getAutoTransferDiscountInfoList(BillWork billWork);

	/**
	 * 자동이체 할인 내역 생성을 위한 청구내역 정보를 LazyLoader의 형태로 조회한다.
	 * @param billWork
	 * @return
	 */
	LazyLoader<BillWork> getAutoTransferDiscountInfoListLazy(BillWork billWork);

	/**
	 * 청구 후 조정대상 청구내역 조회 with mybatis
	 * @param billWork
	 * @return
	 */
	List<BillWork> getAdjInfoAfterBillList(BillWork billWork);

	/**
	 * 청구 후 조정대상 청구내역 조회 with LazyLoader
	 * @param billWork
	 * @return
	 */
	LazyLoader<BillWork> getAdjInfoAfterBillListLazy(BillWork billWork);
	
	/**
	 * 청구 전 조정대상 청구내역 조회 with mybatis
	 * @param billWork
	 * @return
	 */
	List<BillWork> getAdjInfoBeforeBillList(BillWork billWork);

	/**
	 * 청구 전 조정대상 청구내역 조회 with LazyLoader
	 * @param billWork
	 * @return
	 */
	LazyLoader<BillWork> getAdjInfoBeforeBillListLazy(BillWork billWork);

	/**
	 * 청구 전 조정내역 반영
	 * @param billWorkList
	 * @return
	 */
	int updateAdjBeforeBill(List<BillWork> billWorkList);
	
	/**
	 * 청구 후 조정내역 반영
	 * @param billWorkList
	 * @return
	 */
	int updateAdjAfterBill(List<BillWork> billWorkList);

	/**
	 * 계산 테이블에 조정 내역 반영
	 * @param billWorkList
	 * @return
	 */
	int updateAdjAply(List<BillWork> billWorkList);

	/**
	 * 조정금액 반영 내역이 있는지 조회
	 * @param billWork
	 * @return
	 */
	BillWork getBillWorkForAdj(BillWork billWork);
	
}
