/**
 * @FileName
 * CBillMastDaoImpl.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 5. 9.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.batch.iv.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.iv.common.dao.mapper.CBillMastMapper;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.util.QueryUtil;

@Repository
public class CBillMastDaoImpl extends QueryUtil implements CBillMastDao {

	/** StandardChargeMapper Autowired.  */
	@SuppressWarnings("unused")
	@Autowired
	private CBillMastMapper clsMapper;
	

	public int saveInfoDirect(Connection conn, List<Object> obj){			
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML("com/ntels/ccbs/batch/iv/common/dao/mapper"
           			,"CBillMastMapper"
			        ,"insert"
			        ,"saveInfo"
			        ,CBillComm.class
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
	
	public int updateInfoDirect(Connection conn, List<Object> obj){			
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML("com/ntels/ccbs/batch/iv/common/dao/mapper"
			,"CBillMastMapper"
			,"update"
			,"updateInfo"
			,CBillComm.class
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
	public int deleteInfoDirect(Connection conn, List<Object> obj){			
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML("com/ntels/ccbs/batch/iv/common/dao/mapper"
			,"CBillMapper"
			,"delete"
			,"deleteInfo"
			,CBillComm.class
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
