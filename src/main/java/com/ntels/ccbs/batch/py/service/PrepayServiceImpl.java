package com.ntels.ccbs.batch.py.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.py.common.entity.ExrateInfo;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.dao.PrepayDao;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.PrepayTransHistory;
import com.ntels.ccbs.batch.py.entity.Receipt;

@Service
public class PrepayServiceImpl extends BaseService implements PrepayService {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private PrepayDao prepayDao;

	/**
	 * 선수금 발생내역을 등록한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public int insertPrepayOcc(String pymSeqNo, String prepayOccTp, String prepayOccResn, String regrId, double amt) {
		PrepayOcc prepayOcc = new PrepayOcc();

		prepayOcc.setPrepayOccTp(prepayOccTp);
		
		prepayOcc.setPrepayOccResn(prepayOccResn);
		prepayOcc.setPrepayProcStat("1");
		prepayOcc.setPrepayOccAmt(amt);
		prepayOcc.setPrepayTransAmt(0.0);
		prepayOcc.setPrepayBal(amt);
		prepayOcc.setWonPrepayOccAmt(0.0);
		prepayOcc.setRegrId(regrId);
		prepayOcc.setCnclYn("N");
		prepayOcc.setCnclDttm("");

		return insertPrepayOcc(prepayOcc, pymSeqNo);		
	}
	
	private int insertPrepayOcc(PrepayOcc prepayOcc, String pymSeqNo) {
		Receipt pyRcpt = receiptService.getReceipt(pymSeqNo);
		
		// TODO prepayOccSeqNo 시퀀스 발급처리!
		String prepayOccSeqNo = "0000000000";
		
		prepayOcc.setPrepayOccSeqNo(prepayOccSeqNo);
		prepayOcc.setPymAcntId(pyRcpt.getPymAcntId());
		prepayOcc.setPrepayOccDttm(getDttm());
		prepayOcc.setPrepayOccTgtSeqNo(pymSeqNo);
		prepayOcc.setDpstDt(pyRcpt.getDpstDt());
		prepayOcc.setDpstProcDttm(getDttm());
		prepayOcc.setDpstCl(pyRcpt.getDpstCl());
		prepayOcc.setTransCmplYn("N");
		prepayOcc.setCrncyCd(pyRcpt.getCrncyCd());
		prepayOcc.setExrate(pyRcpt.getExrate());
		prepayOcc.setExrateAplyDt(pyRcpt.getExrateAplyDt());
		prepayOcc.setRegDate(now());
		prepayOcc.setSoId(pyRcpt.getSoId());
		prepayOcc.setTransDt(pyRcpt.getTransDt());
		
		return prepayDao.insertPrepayOcc(prepayOcc);
	}
	
	/**
	 * 선수금 발생내역을 일괄등록한다.
	 * @param prepayOccList
	 * @return
	 */
	@Override
	public int insertPrepayOcc(List<PrepayOcc> prepayOccList) {
		return prepayDao.insertPrepayOcc(prepayOccList);
	}
	
	/**
	 * 선수금 발생내역을 일괄등록한다.
	 * @param prepayOccList
	 * @return
	 */
	@Override
	public int insertPrepayOcc(PrepayOcc prepayOcc) {
		return prepayDao.insertPrepayOcc(prepayOcc);
	}
	
	/**
	 * 선납입금정보에 입금취소처리
	 * @param regrId
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayCancel(String regrId, String dpstSeqNo) {
		checkEmpty(regrId, "regrId");
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		
//		String cnclResn = "선납입금취소";
//		String cnclDttm = getDttm();
		
		// TODO TBLPY_PREPD_DPST 테이블에 DPST_SEQ_NO컬럼이 없어서 진행 불가함..
		return 0;
//		return prepayDao.updatePrepayCancel(cnclResn, regrId, cnclDttm, dpstSeqNo);
	}
	
	/**
	 * 선수금대체이력및 선수금발생내역를 수정한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayOccCancel(String pymSeqNo) {
	
		checkEmpty(pymSeqNo, "pymSeqNo");
	
		int count = prepayDao.getPrepayCount(pymSeqNo);
		
		// 선수금발생내역에 있으면서 선수금대체내역에 있으면 처리할 수 없음
		if (count > 0) {
			throw new RuntimeException("count is greater than 0.");
		}
		
		String cnclDttm = getDttm();
		
		return prepayDao.updatePrepayOccCancel(cnclDttm, pymSeqNo);
		
	}
	
	/**
	 * 선수금 발생내역을 조회한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public PrepayOcc getPrepayDeposit(String pymSeqNo) {
		checkEmpty(pymSeqNo, "pymSeqNo");
		
		return prepayDao.getPrepayDeposit(pymSeqNo);
	}
	
	/**
	 * prepayTransSeqNo로 prepayOccSeqNo를 조회하여 선수금 발생내역을 취소처리한다.
	 * @param prepayTransSeqNo
	 * @return
	 */
	@Override
	public int updatePrepayOccByPrepayTransSeqNo(String prepayTransSeqNo) {
		checkEmpty(prepayTransSeqNo, "prepayTransSeqNo");
		String prepayOccSeqNo = prepayDao.getPrepayOccSeqNoFromPrepayTransHistory(prepayTransSeqNo);
		return prepayDao.updatePrepayOccCancelByPrepayOccSeqNo(prepayOccSeqNo);
	}

	/**
	 * 선수금대체이력및 선수금발생내역를 수정한다.
	 * @param pymSeqNo
	 * @param prepayTransSeqNo
	 * @param payObjAmt
	 * @return
	 */
	@Override
	public int updatePrepayTransHistory(String pymSeqNo, String prepayTransSeqNo, double payObjAmt) {
		checkEmpty(pymSeqNo, "pymSeqNo");
		checkEmpty(prepayTransSeqNo, "prepayTransSeqNo");
		
		int check = prepayDao.updatePrepayTransHistoryCancel(prepayTransSeqNo);
		
		if (check < 0) {
			// TODO 갱신된 항목이 없다.. 어떻게 할 것인가?? 지금은 아무것도 하지 않음
		}
		
		String prepayOccSeqNo = prepayDao.getPrepayOccSeqNoFromPrepayTransHistory(prepayTransSeqNo);
		
		check = prepayDao.updatePrepayTransStat(payObjAmt, now(), prepayOccSeqNo);
		
		if (check < 0) {
			// TODO 갱신된 항목이 없다.. 어떻게 할 것인가?? 지금은 아무것도 하지 않음
		}
		
		PrepayOcc checkPrepayOcc = prepayDao.getPrepayAmount(prepayOccSeqNo);
		
		if (checkPrepayOcc.getPrepayOccAmt() < checkPrepayOcc.getPrepayBal()) {
	        clogService.writeLog("Correcting data is required.");
	        clogService.writeLog(String.format("prepayOccAmt = [%f]", checkPrepayOcc.getPrepayOccAmt()));
	        clogService.writeLog(String.format("prepayTransAmt = [%f]", checkPrepayOcc.getPrepayTransAmt()));
	        clogService.writeLog(String.format("prepayBal = [%f]", checkPrepayOcc.getPrepayBal()));
	        // TODO 여기는 오류임 처리요망!!
		}
		
		// TODO 완료 결과를 어떻게 돌려줄것인지 고민이 필요함.
		return 0;
	}
	
	/**
	 * 선수금발생내역을 수정한다.
	 * @param prepayOccSeqNo
	 * @param payAplyAmt
	 * @return
	 */
	@Override
	public int updatePrepayOcc(PrepayOcc prepayOcc) {
		
		String prepayProcStat = prepayDao.getPrepayProcStat(prepayOcc.getPrepayOccSeqNo());
		
		if ("2".equals(prepayProcStat) == true) {
			clogService.writeLog("Could not transfer because operation is in applying.(chPrePayProcStat::'2'");
		}
		
		PrepayOcc updatePrepayOcc = new PrepayOcc();
		updatePrepayOcc.setPrepayOccSeqNo(prepayOcc.getPrepayOccSeqNo());
		updatePrepayOcc.setPrepayBal(prepayOcc.getPrepayBal());
		updatePrepayOcc.setChgrId(prepayOcc.getRegrId());
		updatePrepayOcc.setChgDate(now());
		
		return prepayDao.updatePrepayOcc(updatePrepayOcc);
		
	}
	
	/**
	 * 
	 * @param prepayOcc
	 * @return
	 */
	@Override
	public int updatePrepayOccTransAmt(PrepayOcc prepayOcc) {
		return prepayDao.updatePrepayOccTransAmt(prepayOcc);
	}
	
	/**
	 * 
	 * @param prepayOccList
	 * @return
	 */
	@Override
	public int updatePrepayOccTransAmt(List<PrepayOcc> prepayOccList) {
		return prepayDao.updatePrepayOccTransAmt(prepayOccList);
	}
	
	/**
	 * 선수금 대체금액 및 선수금 잔액을 조회한다.
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public PrepayOcc getPrepayAmount(String prepayOccSeqNo) {
		if (StringUtils.isEmptyOrWhitespaceOnly(prepayOccSeqNo) == true) {
			clogService.writeLog("prepayOccSeqNo has not be empty");
			throw new RuntimeException("prepayOccSeqNo has not be empty");
		}
		
		return prepayDao.getPrepayAmount(prepayOccSeqNo);
	}
	
	/**
	 * 선수금대체이력에 등록한다.
	 * @param prepayOccSeqNo
	 * @param payAplyAmt
	 * @param prepayReplTp
	 * @param regrId
	 * @return
	 */
	@Override
	public String insertPrepayTransHistory(PrepayOcc prepayOcc) {
		String prepayTransSeqNo = pyCommService.getPrepayTransSeqNo();
		
		ExrateInfo exrateInfo = pyCommService.getExrateInfo();
		PrepayOcc prepayBal = getPrepayAmount(prepayOcc.getPrepayOccSeqNo());
		
		double balAmt = prepayBal.getPrepayOccAmt() - prepayBal.getPrepayTransAmt();
		
		PrepayTransHistory prepayTransHistory = new PrepayTransHistory();
		
		prepayTransHistory.setPrepayOccSeqNo(prepayOcc.getPrepayOccSeqNo());
		prepayTransHistory.setPrepayTransSeqNo(prepayTransSeqNo);
		prepayTransHistory.setTransProcDttm(getDttm());
		prepayTransHistory.setPrepayReplTp(prepayOcc.getPrepayOccTp());
		prepayTransHistory.setTransProcAmt(prepayOcc.getPrepayBal());
		prepayTransHistory.setProcMemo("Advance payment process");
		prepayTransHistory.setWonReplProcAmt(prepayOcc.getPrepayBal());
		prepayTransHistory.setCrncyCd(exrateInfo.getCrncyCd());
		prepayTransHistory.setExrate(exrateInfo.getExrate());
		prepayTransHistory.setExrateAplyDt(exrateInfo.getExrateAplyDt());
		prepayTransHistory.setRegrId(prepayOcc.getRegrId());
		prepayTransHistory.setRegDate(now());
		prepayTransHistory.setApprReqrId(prepayOcc.getRegrId());
		prepayTransHistory.setApprReqDttm(getDttm());
		prepayTransHistory.setDcsnProcStat("01");
		prepayTransHistory.setCnclYn("N");
		prepayTransHistory.setCnclDttm("");
		prepayTransHistory.setBalAmt(balAmt);
		
		prepayDao.insertPrepayTransHistory(prepayTransHistory);
		
		return prepayTransSeqNo;
	}
	
	/**
	 * 선수금대체이력에 등록한다.
	 * @param prepayTransHistory
	 * @return
	 */
	@Override
	public int insertPrepayTransHistory(List<PrepayTransHistory> prepayTransHistory) {
		return prepayDao.insertPrepayTransHistory(prepayTransHistory);
	}
	
	/**
	 * 기존의 선수금발생 내역의 데이터를 가지고 새로운 선수금 발생내역을 등록한다.
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public String insertNewPrepayOccFromPrepayOcc(PrepayOcc prepayOcc) {
		String newPrepayOccSeqNo = pyCommService.getPrepayOccSeqNo();
		
		PrepayOcc oldPrepayOcc = getPrepayOcc(prepayOcc.getPrepayOccSeqNo());
		PrepayOcc newPrepayOcc = new PrepayOcc();
		CUtil.copyObjectValue(oldPrepayOcc, newPrepayOcc);

		newPrepayOcc.setPrepayOccSeqNo(newPrepayOccSeqNo);
//		newPrepayOcc.setPymAcntId(prepayOcc.getPymAcntId());
		newPrepayOcc.setPrepayOccDttm(getDttm());
		newPrepayOcc.setPrepayOccTp("1");
		newPrepayOcc.setPrepayOccResn("1");
		newPrepayOcc.setPrepayOccTgtSeqNo(prepayOcc.getPrepayOccTgtSeqNo());	
		newPrepayOcc.setDpstProcDttm(getDttm());
		newPrepayOcc.setPrepayProcStat("1");
		newPrepayOcc.setPrepayOccAmt(prepayOcc.getPrepayBal());
		newPrepayOcc.setPrepayTransAmt(0.0);
		newPrepayOcc.setPrepayBal(prepayOcc.getPrepayBal());
		newPrepayOcc.setTransCmplYn("N");
		newPrepayOcc.setWonPrepayOccAmt(0.0);
		newPrepayOcc.setRegrId(prepayOcc.getRegrId());
		newPrepayOcc.setRegDate(now());
		newPrepayOcc.setCnclYn("N");
		newPrepayOcc.setCnclDttm("");
//		newPrepayOcc.setTransDt(prepayOcc.getTransDt());
		
		prepayDao.insertPrepayOcc(newPrepayOcc);
		
		return newPrepayOccSeqNo;
		
	}
	
	/**
	 * 선수금 발생이력 조회
	 * @param prepayOccSeqNo
	 * @return
	 */
	@Override
	public PrepayOcc getPrepayOcc(String prepayOccSeqNo) {
		return prepayDao.getPrepayOcc(prepayOccSeqNo);
	}
	
	/**
	 * 수납이 완료되지 않은 청구건에 대해 선수금 발생내역을 조회한다.
	 * @param soId
	 * @return
	 */
	@Override
	public List<PrepayOcc> getPrepayOccForUnpaidBill(String soId) {
		return prepayDao.getPrepayOccForUnpaidBill(soId);
	}
	
	@Override
	public int updatePrepayOccProcStat(PrepayOcc prepayOcc) {
		return prepayDao.updatePrepayOccProcStat(prepayOcc);
	}
	
	@Override
	public int updatePrepayOccProcStat(List<PrepayOcc> prepayOcc) {
		return prepayDao.updatePrepayOccProcStat(prepayOcc);
	}
	
		
}
