package com.ntels.ccbs.batch.sample.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.entity.CommonCode;
import com.ntels.ccbs.batch.sample.dao.CommonCodeDao;
import com.ntels.ccbs.batch.sample.vo.SearchParam;


/**
 * 공통 코드 Service.
 *
 * <PRE>
 * 1. ClassName: CommonCodeService
 * 2. FileName : CommonCodeService.java
 * 3. Package  : com.ntels.ccbs.cm.service.configuration
 * 4. 작성자   : smyun@ntels.com
 * 5. 작성일   : 2014. 4. 8. 오후 5:02:49
 * 6. 변경이력
 *		이름  :		일자	: 변경내용
 *     ———————————————————————————————————
 *		smyun :	2014. 4. 8.	: 신규 개발.
 * </PRE>
 */
@Service
public class CommonCodeServiceImpl implements CommonCodeService {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** CommonCodeDao Autowired.  */
	@Autowired
	private CommonCodeDao commonCodeDao;
	
	
	/** 
	 * DataSource Autowired.
	 * 
	 *  jdbcTemplate 사용을 위해서는 아래 선언 필요
	 * 
	 * */
	@Autowired
	private DataSource dataSource;

	/**
	 * 목록.
	 *
	 * @param condition 조회조건
	 * @return List<CommonCode>
	 */
	public List<CommonCode> list(SearchParam param) {

		List<CommonCode> list = commonCodeDao.listCommonCode(param);
		
		if(list == null)
			list = new ArrayList<CommonCode>();
		
		return list;
	}
	
	/**
	 * 출력.
	 * 
	 * @param commonCode 공통코드
	 * @return boolean
	 */
	public boolean printCommonCode(CommonCode commonCode){
		
		l.debug("common code := {}", commonCode);
		
		return true;
	}
	
	/**
	 * 목록.
	 * 
	 * transaction을 처리하기 위해서는 service에서 
	 * dataSource 컨트롤 필요.
	 *
	 * @param condition 조회조건
	 * @return List<CommonCode>
	 */
	public List<CommonCode> listJdbcTemplate(SearchParam param) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		return commonCodeDao.listCommonCodeJdbcTemplate(jdbcTemplate, param);
	}
	


	public List<CommonCode> listJdbcDirect(SearchParam param) {

		Connection conn = null;
		List<CommonCode> list = null;
		try{
			conn = dataSource.getConnection();
			list = commonCodeDao.listCommonCodeDirect(conn, param);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	
}