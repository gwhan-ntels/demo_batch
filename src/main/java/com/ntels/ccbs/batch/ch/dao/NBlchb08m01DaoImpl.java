package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.mapper.NBlchb08m01Mapper;
import com.ntels.ccbs.batch.ch.entity.NBlchb08m01;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.util.QueryUtil;


/**
 * <PRE>
 * 1. ClassName: NBlchb08m01DaoImpl
 * 2. FileName : NBlchb08m01DaoImpl.java
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
public class NBlchb08m01DaoImpl extends LazyLoadingDao implements NBlchb08m01Dao {

	/** StandardChargeMapper Autowired.  */
	@Autowired
	private NBlchb08m01Mapper nBlchb08m01Mapper;

	
	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/ch/dao/mapper";
	}
	
	@Override
	protected String getMapperName() {
		return "NBlchb08m01Mapper";
	}
	
	public LazyLoader<NBlchb08m01> listNBlchb08m01Direct(Multi multi) {		
		return getLazyLoader(dbKind,"listNBlchb08m01", NBlchb08m01.class, multi);
	}

	public int saveNBlchb08m01Direct(Connection conn, List<Object> obj){
		
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML(dbKind,"com/ntels/ccbs/batch/ch/dao/mapper"
			,"NBlchb08m01Mapper"
			,"insert"
			,"saveNBlchb08m01"
			,NBlchb08m01.class
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
