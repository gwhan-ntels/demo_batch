package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.mapper.NBlchb05m01Mapper;
import com.ntels.ccbs.batch.ch.entity.NBlchb05m01;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.util.QueryUtil;

/**
 * <PRE>
 * 1. ClassName: NBlchb05m01DaoImpl
 * 2. FileName : NBlchb05m01DaoImpl.java
 * 3. Package  : com.ntels.ccbs.batch.ch.dao
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:20:46
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */
@Repository
public class NBlchb05m01DaoImpl extends LazyLoadingDao implements NBlchb05m01Dao {

	/** StandardChargeMapper Autowired.  */
	@SuppressWarnings("unused")
	
	@Autowired
	private NBlchb05m01Mapper nBlchb02m01Mapper;
	/** StandardChargeMapper Autowired.  */

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/ch/dao/mapper";
	}
	
	@Override
	protected String getMapperName() {
		return "NBlchb05m01Mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;	
	
	
//	public List<NBlchb05m01> listNBlchb05m01Direct(Connection conn, Multi multi) {		
//		List<NBlchb05m01> list = new ArrayList<NBlchb05m01>();
//
//		try{
//			list = QueryUtil.getListFromXML("com/ntels/ccbs/batch/ch/dao/mapper"
//					,"NBlchb05m01Mapper"
//					,"select"
//					,"listNBlchb05m01"
//					,NBlchb05m01.class
//					,multi
//					,conn) ;
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//
//		}
//
//		return list;
//	}

	public LazyLoader<NBlchb05m01> listNBlchb05m01Direct(Multi multi) {
		// TODO Auto-generated method stub
		return getLazyLoader(dbKind,"listNBlchb05m01", NBlchb05m01.class, multi);
	}

	public int saveNBlchb05m01Direct(Connection conn, List<Object> obj){
		
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML(dbKind,"com/ntels/ccbs/batch/ch/dao/mapper"
			,"NBlchb05m01Mapper"
			,"insert"
			,"saveNBlchb05m01"
			,NBlchb05m01.class
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

}
