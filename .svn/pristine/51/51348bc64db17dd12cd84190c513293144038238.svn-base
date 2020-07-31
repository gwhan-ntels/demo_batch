package com.ntels.ccbs.batch.py.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.py.entity.Deposit;
import com.ntels.ccbs.batch.py.entity.DepositCancel;
import com.ntels.ccbs.batch.py.entity.EachDeposit;

@Component
public interface DepositMapper {

	/**
	 * 건별입금등록를 건별입금내역에 등록한다.
	 * @param pyEachDpst
	 * @return
	 */
	int insertEachDeposit(@Param("eachDeposit") EachDeposit eachDeposit);
	
	/**
	 * 건별입금내역에 등록된 데이타를 조회한다.
	 * @param eachDeposit
	 * @return
	 */
	EachDeposit getEachDeposit(@Param("eachDeposit") EachDeposit eachDeposit);
	
	/**
	 * 건별입금내역에 등록된 데이타를 입금내역에 등록한다.
	 * @param pyDpst
	 * @return
	 */
	int insertDeposit(@Param("deposit") Deposit deposit);
	
	/**
	 * 입금내역에 등록된 데이타에 대해서 건별입금내역의 입금처리일자를 수정한다.
	 * @param eachDeposit
	 * @return
	 */
	int updateEachDeposit(@Param("eachDeposit") EachDeposit eachDeposit);
	
	/**
	 * 입금금액을 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	Double getDpstAmt(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 보증금발생 이력을 저장하기 위해 참조하는 Dpst를 가져온다.
	 * @param dpstSeqNo
	 * @return
	 */
	Deposit getDepositForAssrOcc(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 입금내역에 등록된 데이타를 수납내역에 등록하기위해 참조하는 Dpst를 가져온다.
	 * @param dpstSeqNo
	 * @return
	 */
	Deposit getDepositForRcpt(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 입금취소를 위한 데이터를 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	Deposit getDepositForCancel(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 입금 취소여부를 갱신한다.
	 * @param cnclYn
	 * @param dpstSeqNo
	 * @return
	 */
	int updateCnclYn(@Param("cnclYn") String cnclYn, @Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 수납 처리 여부를 Y로 수정해준다.
	 * @param dpstSeqNo
	 * @param payProcDt
	 * @return
	 */
	int updatePayProcDt(@Param("dpstSeqNo") String dpstSeqNo, @Param("payProcDt") String payProcDt);
	
	/**
	 * 선수금 타겟 여부 갱신
	 * @param payProcDt
	 * @param dpstSeqNo
	 * @return
	 */
	int updatePrepayTgtYn(@Param("payProcDt") String payProcDt, @Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * TBLPY_DPST_CNCL에 등록할 데이터를 조회한다.
	 * @param dpstSeqNo
	 * @return
	 */
	DepositCancel getDepositCancelInfo(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * TBLPY_DPST_CNCL테이블에 취소내역을 등록한다.
	 * @param depositCancel
	 * @return
	 */
	int insertDepositCancel(@Param("depositCancel") DepositCancel depositCancel);
	
	/**
	 * 입금내역의 해당 불명금대상여부를 'Y'로 해준다.
	 * @param dpstSeqNo
	 * @return
	 */
	int updateAmngTgtYn(@Param("dpstSeqNo") String dpstSeqNo);
	
	/**
	 * 일괄 수납처리를 위한 입금내역을 조회한다.
	 * @param parallelCnt
	 * @param parallelNo
	 * @return
	 */
	List<Deposit> getDepositListForBatchReceipt(@Param("parallelCnt") int parallelCnt, @Param("parallelNo") int parallelNo);
	
	/**
	 * 입금내역 수납처리 결과반영
	 * @param ambgTgtYn
	 * @param payProcDt
	 * @param prepayTgtYn
	 * @param dpstSeqNo
	 */
	int updateDepositBatchReceipt(@Param("ambgTgtYn") String ambgTgtYn
									,@Param("payProcDt") String payProcDt
									,@Param("prepayTgtYn") String prepayTgtYn
									,@Param("dpstSeqNo") String dpstSeqNo);
	
}
