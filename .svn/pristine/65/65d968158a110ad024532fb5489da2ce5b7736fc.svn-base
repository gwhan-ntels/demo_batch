package com.ntels.ccbs.batch.ch.standard.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.standard.dao.mapper.StandardChargeMapper;
import com.ntels.ccbs.batch.ch.standard.entity.StandardCharge;
import com.ntels.ccbs.batch.common.dao.mapper.CommonMapper;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.sample.vo.SearchParam;
import com.ntels.ccbs.batch.util.QueryUtil;

/**
 * StandardChargeDao 클래스
 * 
 * mybatis만을 사용한다면 QueryUtil 상속할 필요 없음.
 * 
 * <PRE>
 * 1. ClassName: StandardChargeDao
 * 2. FileName : StandardChargeDao.java
 * 3. Package  : com.ntels.ccbs.batch.sample.dao
 * 4. Comment  :
 * 5. 작성자   : Administrator
 * 6. 작성일   : 2016. 3. 7. 오전 9:03:06
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		Administrator :	2016. 3. 7.	: 신규 개발.
 * </PRE>
 */
@Repository
public class StandardChargeDaoImpl extends QueryUtil implements StandardChargeDao {

	/** StandardChargeMapper Autowired.  */
	@SuppressWarnings("unused")
	@Autowired
	private StandardChargeMapper standardChargeMapper;
	
//	public List<StandardCharge> listStandardCharge(SearchParam param) {
//		return standardChargeMapper.listStandardCharge(param);
//	}
//	
//	public List<StandardCharge> listStandardChargeJdbcTemplate(JdbcTemplate jdbcTemplate, SearchParam param) {
//		return jdbcTemplate.query(
//				QueryUtil.getSqlFromMybatisMapper("com/ntels/ccbs/batch/ch/standard/dao/mapper"
//												,"StandardChargeMapper"
//												, "select"
//												,"listStandardChargeOracle")
//			  , new Object[]{
//				/*param.getStartNum(), param.getEndNum()*/
//				}
//			  , new StandardChargeEntity());
//	}
//	
//	private static final class StandardChargeEntity implements RowMapper<StandardCharge> {
//	    public StandardCharge mapRow(ResultSet rs, int rowNum) throws SQLException {
//	    	StandardCharge StandardCharge = new StandardCharge();
//	    	StandardCharge.setActDt(rs.getString("ACT_DT"));
//	    	StandardCharge.setBillCycl(rs.getString("BILL_CYCL"));
//
//	    	return StandardCharge;
//	    }
//    }
//	
	public List<StandardCharge> listStandardChargeDirect(Connection conn, Common comm){
		
		List<StandardCharge> list = new ArrayList<StandardCharge>();

//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		try{
//			pstmt = conn.prepareStatement(QueryUtil.getSqlFromMybatisMapper("com/ntels/ccbs/batch/ch/standard/dao/mapper"
//					,"StandardChargeMapper"
//					, "select"
//					,"listStandardCharge"));
//			pstmt.setString(1, comm.getClcWrkNo());
//			pstmt.setString(2, comm.getBillYymm());

//			pstmt = QueryUtil.getSqlFromMybatisMapper3("com/ntels/ccbs/batch/ch/standard/dao/mapper"
//					,"StandardChargeMapper"
//					,"select"
//					,"listStandardCharge"
//					,Common.class
//					,comm
//					,pstmt) ;
//
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()){
//				StandardCharge cc = QueryUtil.getResultSetToObject(rs, StandardCharge.class);
//				
//				list.add(cc);
//			}
			list = QueryUtil.getListFromXML("com/ntels/ccbs/batch/ch/standard/dao/mapper"
					,"StandardChargeMapper"
					,"select"
					,"listStandardCharge"
					,StandardCharge.class
					,comm
					,conn) ;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			if(rs != null){
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}

//			if(pstmt != null){
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}			
//		}
		
		return list;
	}
	public int saveStandardChargeDirect(Connection conn, StandardCharge standardCharge){
		
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{
//			pstmt = conn.prepareStatement(QueryUtil.getSqlFromMybatisMapper("com/ntels/ccbs/batch/ch/standard/dao/mapper"
//					,"StandardChargeMapper"
//					,"insert"
//					,"saveStandardCharge"	));

			pstmt = QueryUtil.getSqlFromMybatisMapper3("com/ntels/ccbs/batch/ch/standard/dao/mapper"
					,"StandardChargeMapper"
					,"insert"
					,"saveStandardCharge"
					,StandardCharge.class
					,standardCharge 
					,pstmt);

			pstmt.setString( 1, standardCharge.getClcWrkNo());
			pstmt.setString( 2, standardCharge.getBillYymm());
			pstmt.setString( 3, standardCharge.getBillCycl());
			pstmt.setString( 4, standardCharge.getUseYymm());
			pstmt.setString( 5, standardCharge.getSvcCmpsId());
			pstmt.setString( 6, standardCharge.getRateItmCd());
			pstmt.setString( 7, standardCharge.getSoId());
			pstmt.setString( 8, standardCharge.getGrpId());
			pstmt.setString( 9, standardCharge.getPymAcntId());
			pstmt.setString(10, standardCharge.getCustId());
			pstmt.setString(11, standardCharge.getCtrtId());
			pstmt.setString(12, standardCharge.getProdCmpsId());
			pstmt.setLong(13, standardCharge.getUseAmt());
			pstmt.setString(14, standardCharge.getUseQtyUt());
			pstmt.setLong(15, standardCharge.getUseQty());
			pstmt.setLong(16, standardCharge.getUseCnt());
			pstmt.setString(17, standardCharge.getCrncyCd());
//			pstmt.setDate(18, (Date) standardCharge.getRegDate());
			
			result = pstmt.executeUpdate();

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
	public int saveStandardChargeDirect(Connection conn, List<Object> obj){
		
		PreparedStatement pstmt = null;
		int result = 0;
				
		try{

			result = QueryUtil.updListFromXML("com/ntels/ccbs/batch/ch/standard/dao/mapper"
			,"StandardChargeMapper"
			,"insert"
			,"saveStandardCharge"
			,StandardCharge.class
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
