package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.mapper.NBlchb03m01Mapper;
import com.ntels.ccbs.batch.ch.entity.NBlchb03m01;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.util.QueryUtil;

/**
 * <PRE>
 * 1. ClassName: NBlchb03m01DaoImpl
 * 2. FileName : NBlchb03m01DaoImpl.java
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
public class NBlchb03m01DaoImpl extends LazyLoadingDao implements NBlchb03m01Dao {

	/** StandardChargeMapper Autowired.  */
	@SuppressWarnings("unused")
	@Autowired
	private NBlchb03m01Mapper nBlchb03m01Mapper;
	
	
	@Value("${dbms.kind}")
	String dbKind;	
	
	
//	public List<NBlchb03m01> listNBlchb03m01Direct(Connection conn, Multi multi) {		
//		List<NBlchb03m01> list = new ArrayList<NBlchb03m01>();
//
//		try{
//			list = QueryUtil.getListFromXML("com/ntels/ccbs/batch/ch/dao/mapper"
//					,"NBlchb03m01Mapper"
//					,"select"
//					,"listNBlchb03m01"
//					,NBlchb03m01.class
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

	public int saveNBlchb03m01Direct(Connection conn, List<Object> obj){
		
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML(dbKind,"com/ntels/ccbs/batch/ch/dao/mapper"
			,"NBlchb03m01Mapper"
			,"insert"
			,"saveNBlchb03m01"
			,NBlchb03m01.class
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

	public LazyLoader<NBlchb03m01> listNBlchb03m01Direct(Multi multi) {
		return getLazyLoader(dbKind,"listNBlchb03m01", NBlchb03m01.class, multi);
	}

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/ch/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlchb03m01Mapper";
	}

}
