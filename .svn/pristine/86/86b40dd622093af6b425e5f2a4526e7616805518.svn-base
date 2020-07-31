package com.ntels.ccbs.batch.py.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.py.common.entity.ExrateInfo;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.dao.NBlpyb09m02Dao;
import com.ntels.ccbs.batch.py.dao.NBlpyb09m02JdbcDao;
import com.ntels.ccbs.batch.py.entity.EachDeposit;

@Service
public class NBlpyb09m02JdbcServiceImpl extends BaseService implements NBlpyb09m02JdbcService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private NBlpyb09m02Dao nBlpyb09m02Dao;
	
	@Autowired
	private NBlpyb09m02JdbcDao nBlpyb09m02JdbcDao;
	
	@Value("${crncy.cd}")
	private String crncyCd;
	
	/**
	 * 청구일련번호의 데이터 갯수(청구일련번호, 청구년월)
	 */
	@Value("${py09m02.data.cnt}")
	private int dataCnt;
	
	/**
	 * 청구일련번호를 구성하는 데이터 쌍의 갯수
	 * (청구일련번호, 청구년월)
	 */
	@Value("${py09m02.bill.seq.pair.cnt}")
	private int billSeqPairCnt;

	@Override
	public EachDeposit getEachDpstFromParsedData(String[] data) {
		
		EachDeposit pyEachDpst = new EachDeposit();

	    pyEachDpst.setEachDpstSeq(data[0]);					//신용카드SEQ
	    pyEachDpst.setDpstCl(data[1]);						//입금구분
	    pyEachDpst.setCashDpstCl(data[2]);					//현금입금구분
	    pyEachDpst.setDpstDt(data[3]);						//입금일자
	    pyEachDpst.setDpstAmt(Double.parseDouble(data[4]));	//입금금액
	    pyEachDpst.setDpstFeeTp(data[5]);					//입금수수료유형
	    pyEachDpst.setFeeAmt(0.0);							//수수료
	    pyEachDpst.setBillSeqNo("");
	    pyEachDpst.setSoId(data[6].trim());
	    pyEachDpst.setGrpId(String.format("%.10s", ""));
	    pyEachDpst.setPymAcntId(data[7]);
	    pyEachDpst.setCustId(String.format("%.10s", ""));
	    pyEachDpst.setCtrtId(String.format("%.10s", ""));
	    pyEachDpst.setSmry(data[8]);
	    pyEachDpst.setDpstBnkAcntCd(data[9]);
	    pyEachDpst.setTransDt(data[10]);					//이체일자
	    pyEachDpst.setDpstProcDt(String.format("%.8s", ""));
	    pyEachDpst.setDpstSeqNo(String.format("%.10s", ""));
	    pyEachDpst.setWonDpstAmt(pyEachDpst.getDpstAmt());
	    pyEachDpst.setAcntCardNo(data[11]);					//신용카드 번호 현금이면 공백
	    pyEachDpst.setRcptEmpId(data[12]);					//수금사원ID
	    pyEachDpst.setRcptBillEmpId(data[13]);				//영수사원ID
	    pyEachDpst.setIfChk(data[14]);						//대리점수납구분
	    pyEachDpst.setOrgId(data[15]);						//조직ID
	    pyEachDpst.setCrncyCd(crncyCd);
		
		ExrateInfo exrateInfo = pyCommService.getExrateInfo();
		
		pyEachDpst.setExrate(exrateInfo.getExrate());				// 환율
		pyEachDpst.setExrateAplyDt(exrateInfo.getExrateAplyDt());	// 환율 적용일자

		int billSeqCnt = 0;
		int flag = 0;

		int billSeqPairs = (dataCnt - (dataCnt - billSeqPairCnt)) / billSeqPairCnt;
		
		String[] billSeqArray = new String[billSeqPairs];
		String[] billYymmArray = new String[billSeqPairs];
		
		int loopCnt = dataCnt - (dataCnt - billSeqPairCnt);
		
		for (int i = 0; i < loopCnt; i++) {
			
			if (flag == 0) {
				// 청구일련번호
				flag = 1;
				
				billSeqArray[billSeqCnt] = data[i + (dataCnt - billSeqPairCnt)];
				
				if (pyEachDpst.getIfChk().equals("1") == true && StringUtils.isEmptyOrWhitespaceOnly(billSeqArray[billSeqCnt])) {
					throw new RuntimeException("대리점 수납이면서 청구일련번호가 없습니다.");
				}
			} else if (flag == 1) {
				// 청구년월
				flag = 0;
				
				billYymmArray[billSeqCnt] = data[i + (dataCnt - billSeqPairCnt)];

				if (pyEachDpst.getIfChk().equals("1") == true && StringUtils.isEmptyOrWhitespaceOnly(billYymmArray[billSeqCnt])) {
					throw new RuntimeException("대리점 수납이면서 청구년월이 없습니다.");
				}
				
				billSeqCnt++;
			}
		}
		
		pyEachDpst.setBillSeqArray(billSeqArray);
		pyEachDpst.setBillYymmArray(billYymmArray);
		
		return pyEachDpst;
	}
	
	/**
	 * 해당 청구번호를 가지고 TBLIV_BILL_MAST테이븚에서 미납금액을 조회한다.
	 * @param billSeqNo
	 * @return
	 */
	@Override
	public Double getUnpayAmtFromBillMast(String billSeqNo) {
		return nBlpyb09m02Dao.getUnpayAmtFromBillMast(billSeqNo);
	}
	
	/**
	 * 청구 마스터 테이블(TBLIV_BILL_MAST)에 수납금액을 갱신시킨다.
	 * 만약 더 이상 수납을 할 금액이 없다면 FULL_PAY_YN = 'Y'로 갱신
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillMastRcptAmt(CBillComm bill) {
		bill.setChgDate(now());
		List<CBillComm> billList = new ArrayList<>();
		billList.add(bill);
		return nBlpyb09m02JdbcDao.updateBillMastRcptAmt(billList);
	}
	
	/**
	 * 청구 마스터 테이블(TBLIV_BILL_MAST)에 수납금액을 갱신시킨다.
	 * 만약 더 이상 수납을 할 금액이 없다면 FULL_PAY_YN = 'Y'로 갱신
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillMastRcptAmt(List<CBillComm> billList) {
		return nBlpyb09m02JdbcDao.updateBillMastRcptAmt(billList);
	}
	
	/**
	 * 청구 테이블(TBLIV_BILL)에 수납금액을 갱신시킨다.
	 * 만약 더 이상 수납을 할 금액이 없다면 FULL_PAY_YN = 'Y'로 갱신
	 * 필수 데이터 : billSeqNo, useYymm, prodCmpsId, svcCmpsId
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillRcptAmt(CBillComm bill) {
		bill.setChgDate(now());
		List<CBillComm> billList = new ArrayList<>();
		billList.add(bill);
		return nBlpyb09m02JdbcDao.updateBillRcptAmt(billList);
	}

	/**
	 * 청구 테이블(TBLIV_BILL)에 수납금액을 갱신시킨다.
	 * 만약 더 이상 수납을 할 금액이 없다면 FULL_PAY_YN = 'Y'로 갱신
	 * 필수 데이터 : billSeqNo, useYymm, prodCmpsId, svcCmpsId
	 * @param bill
	 * @return
	 */
	@Override
	public int updateBillRcptAmt(List<CBillComm> billList) {
		return nBlpyb09m02JdbcDao.updateBillRcptAmt(billList);
	}
	
	/**
	 * 청구 테이블(TBLIV_BILL)에 청구번호에 해당하는 청구내역들을
	 * 완납 처리한다.
	 * @param bill
	 * @return
	 */
	@Override
	public int updateFullPayBill(CBillComm bill) {
		List<CBillComm> billList = new ArrayList<>();
		billList.add(bill);
		return nBlpyb09m02JdbcDao.updateFullPayBill(billList);
	}
	
	/**
	 * 청구 테이블(TBLIV_BILL)에 청구번호에 해당하는 청구내역들을
	 * 완납 처리한다.
	 * @param bill
	 * @return
	 */
	@Override
	public int updateFullPayBill(List<CBillComm> billList) {
		return nBlpyb09m02JdbcDao.updateFullPayBill(billList);
	}

}
