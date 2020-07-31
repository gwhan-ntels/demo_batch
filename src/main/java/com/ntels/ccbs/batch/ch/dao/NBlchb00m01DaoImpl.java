package com.ntels.ccbs.batch.ch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.mapper.NBlchb00m01Mapper;
import com.ntels.ccbs.batch.ch.entity.NBlchb00m01;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.util.QueryUtil;


/**
 * <PRE>
 * 1. ClassName: nBlchb00m01DaoImpl
 * 2. FileName : nBlchb00m01DaoImpl.java
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
public class NBlchb00m01DaoImpl extends LazyLoadingDao implements NBlchb00m01Dao {

	/** StandardChargeMapper Autowired.  */
	@Autowired
	private NBlchb00m01Mapper nBlchb00m01Mapper;

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/ch/dao/mapper";
	}
	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	protected String getMapperName() {
		return "NBlchb00m01Mapper";
	}
	
	public LazyLoader<NBlchb00m01> listNBlchb00m01Direct(Multi multi) {
		return getLazyLoader(dbKind,"listNBlchb00m01", NBlchb00m01.class, multi,multi.getColId());
	}
/*
	public List<NBlchb00m01> listNBlchb00m01Direct(Multi multi){
		return nBlchb00m01Mapper.listNBlchb00m01(multi);
	}	
*/	
	public LazyLoader<NBlchb00m01> listNBlchb00m01DirectAll(Multi multi) {
		return getLazyLoader(dbKind,"listNBlchb00m01All", NBlchb00m01.class, multi,multi.getColId());
	}
	
	public int saveNBlchb00m01Direct(Connection conn, List<Object> obj){
		PreparedStatement pstmt = null;
		int result = 0;
	
		try{
			result = QueryUtil.updListFromXML(dbKind, "com/ntels/ccbs/batch/ch/dao/mapper","NBlchb00m01Mapper","insert","saveNBlchb00m01",NBlchb00m01.class,obj,conn);
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

	public int saveNBlchb00m01(NBlchb00m01 nblchb00m01) {
		return insertOne(dbKind, "saveNBlchb00m01", nblchb00m01);
		//	return nBlchb00m01Mapper.saveNBlchb00m01M(nblchb00m01);
	}

	public int saveIfCustInfoAll(Multi multi) {
		return insertOne(dbKind, "saveIfCustInfoAll", multi);
//		return nBlchb00m01Mapper.saveIfCustInfoAll(multi);
	}


	public int saveIfCustInfo(Multi multi) {
		return insertOne(dbKind, "saveIfCustInfo", multi);
	}
	
	public Multi getColId(Multi multi){
		return getOne(dbKind, "getColId", Multi.class, multi);
	}
	public Multi getFunc(Multi multi){
		String colId =  multi.getColId();
		return getOneDyn(dbKind, "getFunc", Multi.class, multi, colId);
	}
}
