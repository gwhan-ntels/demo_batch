package com.ntels.ccbs.batch.sample.dao;

import java.sql.Connection;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ntels.ccbs.batch.entity.CommonCode;
import com.ntels.ccbs.batch.sample.vo.SearchParam;

public interface CommonCodeDao {

	/**
	 * mybatis 예제
	 * 
	 * @param param
	 * @return List<CommonCode>
	 */
	public List<CommonCode> listCommonCode(SearchParam param);

	/**
	 * Spring jdbcTemplate 예제
	 * 
	 * @param param
	 * @return List<CommonCode>
	 */
	public List<CommonCode> listCommonCodeJdbcTemplate(
			JdbcTemplate jdbcTemplate, SearchParam param);

	/**
	 * jdbc direct 접속 dao 
	 * 
	 * @param conn
	 * @return List<CommonCode>
	 */
	public List<CommonCode> listCommonCodeDirect(Connection conn, SearchParam param);

}