package com.ntels.ccbs.batch.sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.entity.CommonCode;
import com.ntels.ccbs.batch.sample.dao.mapper.CommonCodeMapper;
import com.ntels.ccbs.batch.sample.vo.SearchParam;
import com.ntels.ccbs.batch.util.QueryUtil;

/**
 * CommonCodeDao 클래스
 * 
 * mybatis만을 사용한다면 QueryUtil 상속할 필요 없음.
 * 
 * <PRE>
 * 1. ClassName: CommonCodeDao
 * 2. FileName : CommonCodeDao.java
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
public class CommonCodeDaoImplMybatis extends QueryUtil implements CommonCodeDao {

	/** commonCodeMapper Autowired.  */
	@Autowired
	private CommonCodeMapper commonCodeMapper;
	
	public List<CommonCode> listCommonCode(SearchParam param) {
		return commonCodeMapper.listCommonCode(param);
	}
	
	public List<CommonCode> listCommonCodeJdbcTemplate(JdbcTemplate jdbcTemplate, SearchParam param) {
		return jdbcTemplate.query(
				QueryUtil.getSqlFromMybatisMapper("com/ntels/ccbs/batch/sample/dao/mapper"
												,"CommonCodeMapper"
												, "select"
												,"listCommonCodeOracle")
			  , new Object[]{
				/*param.getStartNum(), param.getEndNum()*/
				}
			  , new CommonCodeEntity());
	}
	
	private static final class CommonCodeEntity implements RowMapper<CommonCode> {
	    public CommonCode mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	CommonCode commonCode = new CommonCode();
	        commonCode.setGroupCode(rs.getString("GROUP_CODE"));
	        commonCode.setDetailCode(rs.getString("DETAIL_CODE"));
	        commonCode.setCodeName(rs.getString("CODE_NAME"));
	        commonCode.setDisplayOrder(rs.getString("DISPLAY_ORDER"));
	        commonCode.setToday(rs.getString("TODAY"));
	        return commonCode;
	    }
    }
	
	public List<CommonCode> listCommonCodeDirect(Connection conn, SearchParam param){
		
		List<CommonCode> list = new ArrayList<CommonCode>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement(QueryUtil.getSqlFromMybatisMapper("com/ntels/ccbs/batch/sample/dao/mapper"
					,"CommonCodeMapper"
					, "select"
					,"listCommonCodeOracle"));
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CommonCode cc = QueryUtil.getResultSetToObject(rs, CommonCode.class);
				list.add(cc);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
		return list;
	}
}
