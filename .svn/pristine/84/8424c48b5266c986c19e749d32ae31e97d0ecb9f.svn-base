package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.mapper.NBlchb04m01Mapper;
import com.ntels.ccbs.batch.ch.entity.NBlchb04m01;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.util.QueryUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * <PRE>
 * 1. ClassName: NBlchb04m01DaoImpl
 * 2. FileName : NBlchb04m01DaoImpl.java
 * 3. Package  : com.ntels.ccbs.batch.ch.dao
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:21:03
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */
@Repository
public class NBlchb04m01DaoImpl extends LazyLoadingDao implements NBlchb04m01Dao {

	/** StandardChargeMapper Autowired.  */
	@Autowired
	private NBlchb04m01Mapper nBlchb04m01Mapper;

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/ch/dao/mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	protected String getMapperName() {
		return "NBlchb04m01Mapper";
	}
	
	public LazyLoader<NBlchb04m01> listNBlchb04m01Direct(Multi multi) {	
		//if(dbKind == "MYSQL" || dbKind.equals("MYSQL")){		
			return getLazyLoader(dbKind,"listNBlchb04m01", NBlchb04m01.class, multi);
		//}else{
		//	return getLazyLoader("listNBlchb04m01", NBlchb04m01.class, multi);
		//}		
		
	}

	public int saveNBlchb04m01Direct(Connection conn, List<Object> obj){
		
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML(dbKind,"com/ntels/ccbs/batch/ch/dao/mapper"
			,"NBlchb04m01Mapper"
			,"insert"
			,"saveNBlchb04m01"
			,NBlchb04m01.class
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

	public int saveSvcCmpsMt(Multi multi) {
		return insertOne(dbKind, "saveSvcCmpsMt", multi);
		//return nBlchb04m01Mapper.saveSvcCmpsMt(multi);
	}
	

}
