package com.ntels.ccbs.batch.py.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.JdbcDao;
import com.ntels.ccbs.batch.py.entity.AmbgOcc;
import com.ntels.ccbs.batch.py.entity.AmbgTransHistory;
import com.ntels.ccbs.batch.py.entity.Deposit;

@Repository
public class AmbgJdbcDaoImpl extends JdbcDao implements AmbgJdbcDao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/py/ambg/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "AmbgJdbcMapper";
	}

	@Override
	public List<AmbgOcc> getAmbgOcc(Deposit deposit) {
		return getList("getAmbgOcc", new AmbgOcc(), deposit);
	}

	@Override
	public int insertAmbgOcc(List<AmbgOcc> ambgOcc) {
		return insert("insertAmbgOcc", new AmbgOcc(), ambgOcc);
	}

	@Override
	public int updateAmbgCancel(List<AmbgOcc> ambgOccList) {
		return update("updateAmbgCancel", new AmbgOcc(), ambgOccList);
	}

	@Override
	public int updateAmbgTransHistCancel(String cnclDttm, String ambgTransSeqNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAmbgOccStat(double payObjAmt, Timestamp chgDate, String dpstTpSeqNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AmbgOcc getAmbgAmount(String dpstTpSeqNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmbgOcc getAmbgBal(String ambgOccSeqNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmbgOcc getAmbgForAssr(String ambgOccSeqNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAmbgProcStat(String ambgOccSeqNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateAmbgOccByAmbgOccSeqNo(List<AmbgOcc> ambgOccList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAmbgTransHistory(List<AmbgTransHistory> ambgTransHistoryList) {
		// TODO Auto-generated method stub
		return 0;
	}

}
