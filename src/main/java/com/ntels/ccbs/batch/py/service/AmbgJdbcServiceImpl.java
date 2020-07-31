package com.ntels.ccbs.batch.py.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.py.common.service.PyCommService;
import com.ntels.ccbs.batch.py.dao.AmbgDao;
import com.ntels.ccbs.batch.py.entity.AmbgOcc;
import com.ntels.ccbs.batch.py.entity.AmbgTransHistory;

@Service
public class AmbgJdbcServiceImpl extends BaseService implements AmbgJdbcService {

	@Autowired
	private ClogService clogService;
	
	@Autowired
	private PyCommService pyCommService;
	
	@Autowired
	private AmbgDao ambgDao;
	
	@Override
	public int insertAmbgOcc(String regrId, String dpstSeqNo) {
		AmbgOcc ambgOcc = ambgDao.getAmbgOcc(dpstSeqNo);

		String ambgOccDttm = getDttm();
		
		ambgOcc.setAmbgOccDttm(ambgOccDttm);
		ambgOcc.setAmbgOccTp("01");
		ambgOcc.setAmbgOccResn("1");
		ambgOcc.setDpstTpSeqNo(dpstSeqNo);
		ambgOcc.setDpstProcDttm(ambgOccDttm);
		ambgOcc.setAmbgProcStat("1");
		ambgOcc.setAmbgTransAmt(0.0);
		ambgOcc.setTransCmplYn("N");
		ambgOcc.setRegrId(regrId);
		ambgOcc.setRegDate(now());
		ambgOcc.setCnclYn("N");
		ambgOcc.setCnclDttm("");
		
		return insertAmbgOcc(ambgOcc);
	}
	
	/**
	 * 미리 생성된 불명금 내역을 시퀀스를 발급하여 등록한다.
	 * @param ambgOcc
	 * @return
	 */
	@Override
	public int insertAmbgOcc(AmbgOcc ambgOcc) {
		String ambgOccSeqNo = pyCommService.getAmbgOccSeqNo();
		ambgOcc.setAmbgOccSeqNo(ambgOccSeqNo);
		List<AmbgOcc> ambgOccList = new ArrayList<>();
		ambgOccList.add(ambgOcc);
		
		return ambgDao.insertAmbgOcc(ambgOcc);
	}
	
	/**
	 * 미리 생성된 불명금 내역을 시퀀스를 발급하여 등록한다.
	 * @param ambgOcc
	 * @return
	 */
	@Override
	public int insertAmbgOcc(List<AmbgOcc> ambgOcc) {
		return ambgDao.insertAmbgOcc(ambgOcc);
	}
	
	/**
	 * 불명금 발생내역에 있으면서 불명금 대체내역에 있으면 취소가 되지 않는다.
	 * 그 경우가 아니라면 불명금 발생내역에서 취소처리
	 * @param dpstSeqNo
	 * @return
	 */
	@Override
	public int updateAmbgCancel(String dpstSeqNo) {
		int count = ambgDao.getAmbgCount(dpstSeqNo);
		
		if (count > 0) {
			throw new RuntimeException("[updateAmbg]미확인발생내역에 있으면서 미확인대체내역에 있으면 처리하면 안된다.");
		}
		
		String cnclDttm = getDttm();
		
		return ambgDao.updateAmbgCancel(cnclDttm, dpstSeqNo);
	}
	
	/**
	 * 불명금대체이력및 불명금발생내역를 수정한다.
	 * @param dpstSeqNo
	 * @param ambgTransSeqNo
	 * @param payObjAmt
	 * @return
	 */
	@Override
	public int updateAmbg(String dpstSeqNo, String ambgTransSeqNo, double payObjAmt) {
		checkEmpty(dpstSeqNo, "dpstSeqNo");
		checkEmpty(ambgTransSeqNo, "ambgTransSeqNo");
		
		ambgDao.updateAmbgTransHistCancel(getDttm(), ambgTransSeqNo);
		ambgDao.updateAmbgOccStat(payObjAmt, now(), dpstSeqNo);
		
		AmbgOcc ambgAmount = ambgDao.getAmbgAmount(dpstSeqNo);
		
	    if( ambgAmount.getAmbgOccAmt() < ambgAmount.getAmbgBal()) {
	    	clogService.writeLog("데이타보정이 필요한 데이타 입니다.!!!문의");
	    	clogService.writeLog(String.format("ambgOccAmt = [%f]", ambgAmount.getAmbgOccAmt()));
	    	clogService.writeLog(String.format("ambgTransAmt = [%f]", ambgAmount.getAmbgTransAmt()));
	    	clogService.writeLog(String.format("ambgBal = [%f]", ambgAmount.getAmbgBal()));
	    	// TODO 데이터정합성 에러.. 처리 필요
	    }    
		
		// TODO 성공실패 여부 돌려주기
		return 0;
	}
	
	/**
	 * 불명금 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	@Override
	public AmbgOcc getAmbgBal(String ambgOccSeqNo) {
		checkEmpty(ambgOccSeqNo, "ambgOccSeqNo");
		
		return ambgDao.getAmbgBal(ambgOccSeqNo);
	}
	
	/**
	 * 보증금 발생 내역에 저장할 불명금 데이터 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	@Override
	public AmbgOcc getAmbgForAssr(String ambgOccSeqNo) {
		checkEmpty(ambgOccSeqNo, "ambgOccSeqNo");
		
		return ambgDao.getAmbgForAssr(ambgOccSeqNo);
	}
	
	/**
	 * 불명금 진행 상태 조회
	 * @param ambgOccSeqNo
	 * @return
	 */
	@Override
	public String getAmbgProcStat(String ambgOccSeqNo) {
		checkEmpty(ambgOccSeqNo, "ambgOccSeqNo");
		
		return ambgDao.getAmbgProcStat(ambgOccSeqNo);
	}
	
	/**
	 * 불명금발생내역을 수정한다.
	 * @param ambgOcc
	 * @return
	 */
	@Override
	public int updateAmbgOccByAmbgOccSeqNo(AmbgOcc ambgOcc) {
		return ambgDao.updateAmbgOccByAmbgOccSeqNo(ambgOcc);
	}
	
	/**
	 * 불명금대체이력에 등록한다.
	 * @param ambgTransHistory
	 * @return
	 */
	@Override
	public int insertAmbgTransHistory(AmbgTransHistory ambgTransHistory) {
		return ambgDao.insertAmbgTransHistory(ambgTransHistory);
		
	}
	
}
