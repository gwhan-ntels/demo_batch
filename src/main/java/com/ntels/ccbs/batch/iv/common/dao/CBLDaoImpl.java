package com.ntels.ccbs.batch.iv.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.dao.mapper.CBLMapper;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.BillStp;
import com.ntels.ccbs.batch.iv.common.entity.BillWork;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;
import com.ntels.ccbs.batch.util.QueryUtil;

@Repository
public class CBLDaoImpl extends LazyLoadingDao  implements CBLDao {

	@Autowired
	private CBLMapper cblMapper;
	
	private static final String mapperPath = "com/ntels/ccbs/batch/iv/common/dao/mapper";
	private static final String mapperName = "CBLMapper";
	
	@Override
	protected String getMapperPath() {		
		return "com/ntels/ccbs/batch/iv/common/dao/mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	protected String getMapperName() {
		return "CBLMapper";
	}
	
	@Override
	public List<BillCyclStp> getCyclStpSetVal(BillCyclStp billCyclStp) {
		return getList(dbKind, "getCyclStpSetVal", BillCyclStp.class, billCyclStp);
		//return cblMapper.getCyclStpSetVal(billCyclStp);
	}
	
	@Override
	public List<BillCyclStp> getCyclStpSetVal(Connection conn, BillCyclStp billCyclStp) {
		try {
			return QueryUtil.getListFromXML(dbKind,mapperPath, mapperName, "select", "getCyclStpSetVal", BillCyclStp.class, billCyclStp, conn);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @param billCyclStp
	 * @return
	 */
	@Override
	public List<BillCyclStp> getSetLoc(BillCyclStp billCyclStp) {
		return getList(dbKind,"getSetLoc", BillCyclStp.class, billCyclStp);
		//return cblMapper.getSetLoc(billCyclStp);
	}
	
	/**
	 * 
	 * @param conn
	 * @param billCyclStp
	 * @return
	 */
	@Override
	public List<String> getSetLoc(Connection conn, BillCyclStp billCyclStp) {
		try {
			return QueryUtil.getListFromXML(dbKind,mapperPath, mapperName, "select", "getSetLoc", String.class, billCyclStp, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @param billCyclStp
	 * @return
	 */
	@Override
	public List<BillStp> getStpSetVal(BillCyclStp billCyclStp) {
		return getList(dbKind,"getStpSetVal", BillStp.class, billCyclStp);
		//return cblMapper.getStpSetVal(billCyclStp);
	}
	
	/**
	 * 
	 * @param conn
	 * @param billCyclStp
	 * @return
	 */
	@Override
	public List<BillStp> getStpSetVal(Connection conn, BillCyclStp billCyclStp) {
		try {
			return QueryUtil.getListFromXML(dbKind,mapperPath, mapperName, "select", "getStpSetVal", BillStp.class, billCyclStp, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 환율 적용일자 조회
	 * @param currentDate
	 * @return
	 */
	@Override
	public String getExrateAplyDt(String currentDate) {
		BillCyclStp billCyclStp = new BillCyclStp();
		billCyclStp.setCurrentDate(currentDate);
		billCyclStp = getOne(dbKind, "getExrateAplyDt", BillCyclStp.class, billCyclStp);	
		//System.out.println("billCyclStp.getExrateAplyDt(=====================================>"+billCyclStp.getExrateAplyDt());
		return billCyclStp.getExrateAplyDt();
		
		//return getOne(dbKind, "getExrateAplyDt", BillCyclStp.class, billCyclStp);	
		//return cblMapper.getExrateAplyDt(currentDate);
	}
	
}
