package com.ntels.ccbs.batch.py.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.py.dao.AssrDao;
import com.ntels.ccbs.batch.py.entity.AssrOcc;

@Service
public class AssrServiceImpl extends BaseService implements AssrService {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private AssrDao assrDao;
	
	@Value("${svc.rate.itm.typ.cd.assr.283}")
	private String svcRateItmTypeCdAssr283;
	
	@Value("${svc.rate.itm.typ.cd.assr.285}")
	private String svcRateItmTypeCdAssr285;
	
	@Value("${svc.rate.itm.typ.cd.assr.287}")
	private String svcRateItmTypeCdAssr287;
	
	@Value("${svc.rate.itm.typ.cd.assr.289}")
	private String svcRateItmTypeCdAssr289;
	
	@Value("${svc.rate.itm.typ.cd.assr.291}")
	private String svcRateItmTypeCdAssr291;
	
	/**
	 * 보증금발생내역 테이블에 등록한다.
	 * @param assrOcc
	 * @return
	 */
	@Override
	public int insertAssrOcc(AssrOcc assrOcc) {
		return assrDao.insertAssrOcc(assrOcc);
	}
	
	/**
	 * 보증금발생내역를 수정한다.
	 * @param seqNo
	 * @return
	 */
	@Override
	public int updateAssrOccCancel(String seqNo) {
		checkEmpty(seqNo, "seqNo");
		if (StringUtils.isEmptyOrWhitespaceOnly(seqNo) == true) {
			throw new RuntimeException("sqlNo값은 필수입니다.");
		}
		
		int count = assrDao.getAssrCount(seqNo);
		
		if (count > 0) {
			clogService.writeLog("보증금발생내역에 있으면서 보증금대체내역에 있으면 처리할 수 없습니다.");
			throw new RuntimeException("보증금발생내역에 있으면서 보증금대체내역에 있으면 처리할 수 없습니다.");
		}
		
		String cnclDttm = getDttm();
		
		return assrDao.updateAssrOccCancel(cnclDttm, seqNo);
	}
	
	/**
	 * 보증금발생내역을 취소한다.
	 * @param pymSeqNo
	 * @return
	 */
	@Override
	public int updateAssrOccCancelByPymSeqNu(String pymSeqNo) {
		
		List<AssrOcc> assrOccList = assrDao.getAssrOccByPymSeqNo(pymSeqNo);
		AssrOcc assrOcc = null;
		
		// rownum = 1
		if (assrOccList.size() > 0) {
			assrOcc = assrOccList.get(0);
		} else {
			// TODO 보증금발생내역이 조회되지 않았다.. 어떻게 끝낼것인가??
			return 0;
		}
		
		if (assrOcc.getAssrOccAmt() > assrOcc.getAssrBal()) {
			clogService.writeLog("보증금이 대체되어 취소할수 없습니다.");
			throw new RuntimeException("보증금이 대체되어 취소할수 없습니다.");
		}
		
		assrOcc.setCnclDttm(getDttm());
		assrOcc.setChgDate(now());
		
		return assrDao.updateAssrOccCancelByAssrOccSeqNo(assrOcc);
		
		
	}
	
	/**
	 * 보증금대체이력및 불명금발생내역를 수정한다.
	 * @param pymSeqNo
	 * @param assrTransSeqNo
	 * @param payObjAmt
	 * @return
	 */
	@Override
	public int updateAssrOcc(String pymSeqNo, String assrTransSeqNo, double payObjAmt) {
		checkEmpty(pymSeqNo, "pymSeqNo");
		checkEmpty(assrTransSeqNo, "assrTransSeqNo");
		
		int check = assrDao.updateAssrTransHistCancel(getDttm(), assrTransSeqNo);
		
		if (check < 0) {
			// TODO 보증금 대체이력 취소.. 갱신 된 행 없음
		}
		
		String assrOccSeqNo = assrDao.getAssrOccSeqNoByAssrTransSeqNo(assrTransSeqNo);
		
		if (StringUtils.isEmptyOrWhitespaceOnly(assrOccSeqNo)) {
			clogService.writeLog("assrOccSeqNo조회를 할 수 없습니다.");
			throw new RuntimeException("assrOccSeqNo조회를 할 수 없습니다.");
		}
	
		// TODO 여기서는 왜 조회했는지 모름.. 사용하는곳이 없는데...
		AssrOcc assrAmount = assrDao.getAssrAmountByAssrOccSeqNo(assrOccSeqNo);
		
		assrDao.updateAssrOccStat(payObjAmt, now(), assrOccSeqNo);
		
		assrAmount = assrDao.getAssrAmountByAssrOccSeqNo(assrOccSeqNo);
		
		if (assrAmount.getAssrOccAmt() < assrAmount.getAssrBal()) {
			clogService.writeLog("데이타보정이 필요한 데이타 입니다.!!!문의");
			clogService.writeLog(String.format("assrOccAmt = [%f]", assrAmount.getAssrOccAmt()));
			clogService.writeLog(String.format("assrTransAmt = [%f]", assrAmount.getAssrTransAmt()));
			clogService.writeLog(String.format("assrBal = [%f]", assrAmount.getAssrBal()));		
		}
		
		// TODO 성공 실패여부 돌려주기
		return 0;
	
	}
	
	/**
	 * 보증금 발생 대상인지 체크
	 * @param chrgItmCd
	 * @return
	 */
	@Override
	public boolean isAssrOccTarget(String chrgItmCd) {
		if (chrgItmCd.equals(svcRateItmTypeCdAssr283) == true
				|| chrgItmCd.equals(svcRateItmTypeCdAssr285) == true
				|| chrgItmCd.equals(svcRateItmTypeCdAssr287) == true
				|| chrgItmCd.equals(svcRateItmTypeCdAssr289) == true
				|| chrgItmCd.equals(svcRateItmTypeCdAssr291) == true) {
			return true;
		}
		
		return false;
	}
	
}
