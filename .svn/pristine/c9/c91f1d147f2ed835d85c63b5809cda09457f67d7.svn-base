package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.mapper.NBlchb11m01Mapper;
import com.ntels.ccbs.batch.ch.entity.NBlchb11m01;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.util.QueryUtil;


/**
 * <PRE>
 * 1. ClassName: NBlchb11m01DaoImpl
 * 2. FileName : NBlchb11m01DaoImpl.java
 * 3. Package  : com.ntels.ccbs.batch.ch.dao
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 7. 28. 오전 10:56:02
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 7. 28.	: 신규 개발.
 * </PRE>
 */
@Repository
public class NBlchb11m01DaoImpl extends LazyLoadingDao implements NBlchb11m01Dao {

	@SuppressWarnings("unused")
	@Autowired
	private NBlchb11m01Mapper nBlchb11m01Mapper;
	
	@Value("${dbms.kind}")
	String dbKind;	
	

	public LazyLoader<NBlchb11m01> listChargeHist(Multi multi) {
		return getLazyLoader(dbKind,"listChargeHist", NBlchb11m01.class, multi);
	}
	
	public int saveChargeHist(Connection conn, List<Object> obj){
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML(dbKind,"com/ntels/ccbs/batch/ch/dao/mapper"
			,"NBlchb11m01Mapper"
			,"insert"
			,"saveChargeHist"
			,NBlchb11m01.class
			,obj
			,conn);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
		return result;
	}
		

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/ch/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlchb11m01Mapper";
	}

	public int saveChargeHist(NBlchb11m01 nblchb11m01) {
		return nBlchb11m01Mapper.saveChargeHist(nblchb11m01);
	}

	public int saveChargeHist1(Multi multi) {
		return nBlchb11m01Mapper.saveChargeHist1(multi);
	}
	
}
