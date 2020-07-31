package com.ntels.ccbs.batch.ch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.NBlchb01m01Dao;
import com.ntels.ccbs.batch.ch.dao.mapper.NBlchb01m01Mapper;
import com.ntels.ccbs.batch.ch.entity.NBlchb01m01;
import com.ntels.ccbs.batch.common.CUtil;

/**
 * <PRE>
 * 1. ClassName: NBlchb01m01ServiceImpl
 * 2. FileName : NBlchb01m01ServiceImpl.java
 * 3. Package  : com.ntels.ccbs.batch.ch.service
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:21:33
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */
@Service
public class NBlchb01m01ServiceImplTest implements NBlchb01m01ServiceTest {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** StandardChargeDao Autowired.  */
	@Autowired
	private NBlchb01m01Dao nBlchb01m01Dao;
	@Autowired
	private NBlchb01m01Mapper nBlchb01m01Mapper;
	
	
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
	 * @return List<StandardCharge>
	 */
	/**
	 * 출력.
	 * 
	 * @param StandardCharge 공통코드
	 * @return boolean
	 */
	public boolean printStandardCharge(NBlchb01m01 nBlchb01m01){
		
		l.debug("common code := {}", nBlchb01m01);
		
		return true;
	}
	
	/**
	 * 목록.
	 * 
	 * transaction을 처리하기 위해서는 service에서 
	 * dataSource 컨트롤 필요.
	 * @param condition 조회조건
	 * @return List<StandardCharge>
	 */

	public List<NBlchb01m01> listJdbcDirect(Multi multi) {

		Connection conn = null;
		List<NBlchb01m01> list = null;
		List<NBlchb01m01> resultList = new ArrayList<>();
		String prevCtrtId = null;
		String prevProd = null;
		String prevActDt = null;
		String prevInactDt = null;
		JdbcCursorItemReader<NBlchb01m01> reader = new JdbcCursorItemReader();
		int i = 0;
		int skip = 0;

		try{
			reader.setDataSource(this.dataSource);
			reader.setFetchSize(1000);
			reader.setRowMapper((RowMapper<NBlchb01m01>) nBlchb01m01Mapper.listNBlchb01m01(multi));
			int count = 0;
			ExecutionContext executionContext = new ExecutionContext();
			reader.open(executionContext);
//			while(NBlchb01m01 != null)
//			conn = dataSource.getConnection();
//			list = nBlchb01m01Dao.listNBlchb01m01Direct(conn, multi);

			for(NBlchb01m01 listTemp : list){
				//계약과 상품구성정보가 동일하면 INACT 일자를 변경한다.
				//신규시 전계약 정보 저장
				skip = 0;
				if(i == 0){
					prevCtrtId = listTemp.getCtrtId();
					prevProd = listTemp.getProdCmpsId();
					prevActDt = listTemp.getActDt();
					prevInactDt = listTemp.getInactDt();
				}
				//최초가 아닌 계약과 상품구성id가 동일한 경우 act, inact를 비교한다.
				if(i != 0 && prevCtrtId.equals(listTemp.getCtrtId()) && prevProd.equals(listTemp.getProdCmpsId())){
					//inact일자는 99991231일 수 있으므로 체크 제외
					if(prevActDt.equals(listTemp.getActDt())){
						l.debug("동일계약, ACTDT중복"+listTemp.getCtrtId()+","+listTemp.getActDt());
						skip = 1;
					}else{
						//act가 다르면 기존 act와 현재 inact와 비교하여 작은 값을 inact에 넣는다.
						prevInactDt = listTemp.getInactDt();
						listTemp.setInactDt(CUtil.getCompDate(prevActDt, listTemp.getInactDt() , "MIN"));
						prevActDt = listTemp.getActDt();
					}
					i++; 
				//계약과 상품구성id가 다르면 전 구성정보를 저장을 위해 초기화	
				}else{
					prevCtrtId = listTemp.getCtrtId();
					prevProd = listTemp.getProdCmpsId();
					prevActDt = listTemp.getActDt();
					prevInactDt = listTemp.getInactDt();
					i++;
				}
				if(skip == 0){
					listTemp.setActDt(CUtil.getCompDate(listTemp.getActDt(), multi.getStartDate(), "MAX"));
					listTemp.setInactDt(CUtil.getCompDate(listTemp.getInactDt(), listTemp.getTermDt(), multi.getEndDate(), "MIN"));
					listTemp.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));
					if(listTemp.getActDt().equals(listTemp.getInactDt())){
						l.debug("ACTDT,INACT 동일일자 SKIP : "+listTemp.getCtrtId()+","+listTemp.getActDt()+","+listTemp.getInactDt());
					} else{
						resultList.add(listTemp);
					}
				}
			}
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
		
		return resultList;
	}

	public int saveJdbcDirect(List<Object>  obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = nBlchb01m01Dao.saveNBlchb01m01Direct(conn, obj);
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
		
		return result;

	}
}