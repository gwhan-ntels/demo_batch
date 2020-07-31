package com.ntels.ccbs.batch.ch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.NBlchb05m01Dao;
import com.ntels.ccbs.batch.ch.entity.NBlchb05m01;
import com.ntels.ccbs.batch.common.LazyLoader;


/**
 * <PRE>
 * 1. ClassName: NBlchb05m01ServiceImpl
 * 2. FileName : NBlchb05m01ServiceImpl.java
 * 3. Package  : com.ntels.ccbs.batch.ch.service
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:20:20
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */
@Service
public class NBlchb05m01ServiceImpl implements NBlchb05m01Service {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** StandardChargeDao Autowired.  */
	@Autowired
	private NBlchb05m01Dao nBlchb05m01Dao;
//	@Autowired
//	private RateInfoService rateInfoService;
//
//	List<RateInfo> listRRate;
//	List<RateAttr> listRrateAttr;
//	private String val1 = null;
//	private String val2 = null;
//	private String val3 = null;
//	private String val4 = null;
//	private String val5 = null;
//	private double useAmt = 0.0;
//	private double vat = 0.0;
	
//	RateInfo listRate;
	
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
	public boolean printStandardCharge(NBlchb05m01 nBlchb05m01){
		
		l.debug("common code := {}", nBlchb05m01);
		
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
	public LazyLoader<NBlchb05m01> listJdbcDirect(Multi multi) {

		return nBlchb05m01Dao.listNBlchb05m01Direct(multi);
	}


//	public List<NBlchb05m01> listJdbcDirect(Multi multi) {
//
//		Connection conn = null;
//		List<NBlchb05m01> list = null;
//		List<NBlchb05m01> resultList = new ArrayList<>();
//
//		multi.setChrgCtgry("R");
//		listRRate = rateInfoService.listRateItm(multi);
//		listRrateAttr = rateInfoService.listRrateAttr(multi);
//		String fctrVal = null;
//		Boolean fctrChk = true;
//		int fctrNo = 1;
//		String prevProdCmpsId = "First";
//		int rRound = 0;
//		int matchCnt = 0; /* 팩터 체크용 */
//		boolean finalChk = false;
//		
//		
//		try{
//			conn = dataSource.getConnection();
//			list = nBlchb05m01Dao.listNBlchb05m01Direct(conn, multi);
//			
//			for(NBlchb05m01 listTemp : list){
//				matchCnt = 0;
//				finalChk = false;
//				String realStdt = CUtil.getCompDate(listTemp.getActDt(), multi.getStartDate(), listTemp.getRactDt(), "MAX");
//				listTemp.setActDt(realStdt);
//				String realEddt = CUtil.getCompDate(listTemp.getInactDt(), multi.getEndDate(), listTemp.getRinactDt(), "MIN");
//				listTemp.setInactDt(realEddt);
//
////				listTemp.setDd(CUtil.getUseDay(listTemp.getActDt(), listTemp.getInactDt(), 0));
//				listTemp.setUseCnt(Long.parseLong(CUtil.getUseDay(listTemp.getActDt(), listTemp.getInactDt(), 0)));
//				
//				int dayCycl = Integer.parseInt(CUtil.getUseDay(multi.getStartDate(), multi.getEndDate(), 0));
//				
//				if(prevProdCmpsId.equals("First")){ //최초 대상이 없는 경우 RateItemCd를 채워준다.
//					prevProdCmpsId = listTemp.getSvcCmpsId();
//				}else if(!prevProdCmpsId.equals(listTemp.getSvcCmpsId())){
//					prevProdCmpsId = listTemp.getSvcCmpsId();
//					fctrNo = 1;
//				}
//				
//				useAmt = 0;
//				vat = 0;
//				Class<NBlchb05m01> cls = NBlchb05m01.class;
//				Object obj = listTemp;
//				Method m = null;
//				String listFctrCd = null;
//				String listFctrVal = null;
//				for(int funcNo = 1; funcNo < 10 ;funcNo++){ //현재는 9개의 팩터만 사용함
//					m = cls.getMethod("getFctrCd"+Integer.toString(funcNo));						
//					listFctrCd = (String) m.invoke(obj);
//					m = cls.getMethod("getFctrVal"+Integer.toString(funcNo));
//					listFctrVal = (String) m.invoke(obj);
//					if(listFctrCd == null || listFctrCd == "*" || finalChk){
//						break;
//					}
//					// RateItem같은 경우 조건을 체크한다.
//					for(int i=0; i< listRRate.size(); i++){
//						if(listRRate.get(i).getProdCd().compareTo(listTemp.getProdCd()) > 0){
//							break;
//						}else if(listRRate.get(i).getProdCd().compareTo(listTemp.getProdCd()) < 0){
//							continue;
//						}else if(listTemp.getRateItmCd().equals(listRRate.get(i).getRateItmCd())
//  					      && listFctrCd.equals(listRRate.get(i).getFctrCd())
//						  && fctrNo <= listRRate.get(i).getFctrNo()
//					      && listTemp.getRinactDt().equals(listRRate.get(i).getInactDt())
//					      && listTemp.getRactDt().equals(listRRate.get(i).getActDt())
//					      && listRRate.get(i).getFctrRefTyp().equals("T")){
//							//Fctr번호에 따른 기준값을 가져온다.
//							Long chkFctrNo = listRRate.get(i).getFctrNo();
//							if(chkFctrNo == 1){
//								fctrVal = listRRate.get(i).getFctrVal1();
//								fctrNo = 1;
//							}else if(chkFctrNo == 2){
//								fctrVal = listRRate.get(i).getFctrVal2();
//								fctrNo = 2;
//							}else if(chkFctrNo == 3){
//								fctrVal = listRRate.get(i).getFctrVal3();
//								fctrNo = 3;
//							}else if(chkFctrNo == 4){
//								fctrVal = listRRate.get(i).getFctrVal4();
//								fctrNo = 4;
//							}else if(chkFctrNo == 5){
//								fctrVal = listRRate.get(i).getFctrVal5();
//								fctrNo = 5;
//							}else if(chkFctrNo == 6){
//								fctrVal = listRRate.get(i).getFctrVal6();
//								fctrNo = 6;
//							}else if(chkFctrNo == 7){
//								fctrVal = listRRate.get(i).getFctrVal7();
//								fctrNo = 7;
//							}else if(chkFctrNo == 8){
//								fctrVal = listRRate.get(i).getFctrVal8();
//								fctrNo = 8;
//							}else if(chkFctrNo == 9){
//								fctrVal = listRRate.get(i).getFctrVal9();
//								fctrNo = 9;
//							}
//							
//							//기준값을 조건식과 맞추어 계산한다.
//							String chkCond =listRRate.get(i).getCondOperator(); 
//							if(chkCond.equals("0")){ //NotEqual
//								if( fctrVal.equals("*") || !listFctrVal.equals(fctrVal)){
//									fctrChk = true;
//								}else{ fctrChk = false;}
//							}else if(chkCond.equals("1")){ //Equal
//								if( fctrVal.equals("*") || listFctrVal.equals(fctrVal)){
//									fctrChk = true;
//								}else{ fctrChk = false;}
//							}else if(chkCond.equals("2")){ //Large
//								if( fctrVal.equals("*") || listFctrVal.compareTo(fctrVal)>0){
//									fctrChk = true;
//								}else{ fctrChk = false;}
//							}else if(chkCond.equals("3")){ //Small
//								if( fctrVal.equals("*") || listFctrVal.compareTo(fctrVal)<0){
//									fctrChk = true;
//								}else{ fctrChk = false;}
//							}else if(chkCond.equals("4")){ //LargeEqual
//								if( fctrVal.equals("*") || listFctrVal.compareTo(fctrVal)>=0){
//									fctrChk = true;
//								}else{ fctrChk = false;}
//							}else if(chkCond.equals("5")){ //SmallEqual
//								if( fctrVal.equals("*") || listFctrVal.compareTo(fctrVal)<=0){
//									fctrChk = true;
//								}else{ fctrChk = false;}
//							}else if(chkCond.equals("6")){ //Between
//								if( fctrVal.equals("*")
//								   || (listFctrVal.compareTo(listRRate.get(i).getOprnd1()) >= 0
//									&& listFctrVal.compareTo(listRRate.get(i).getOprnd2()) <=0)
//							  	   || (listFctrVal.compareTo(listRRate.get(i).getOprnd1()) <= 0
//									&& listFctrVal.compareTo(listRRate.get(i).getOprnd2()) >=0) ){
//									fctrChk = true;
//								}else{ fctrChk = false;}
//							}
//						}
//						//팩터 대상 카운트
//						if(fctrChk){
//							matchCnt++; 
//						}
//						
//						if(listRRate.get(i).getFctrCnt() == matchCnt){
//							finalChk = true;
//						}
//						//마지막 처리내역이면서 정상인 경우에만 처리한다.
//						if(finalChk && fctrChk){
//							//속성별 계산된 값을 정의한다.
//							for(int j=0; j <listRrateAttr.size(); j++){
//								if(listRRate.get(i).getRateItmCd().equals(listRrateAttr.get(j).getRateItmCd())){
//								//VAL1 : DF058	AT00000007	요금_2.일할계산산입기준(개통/해지일)	PP00050	: 1 개통일포함, 해지일 미포함
//								//VAL2 : DF057	AT00000006	요금_1.일할계산기준일	PP00042 : 00 일할계산없음, 01 월일수 기준
//								//VAL3 : DF038	AT00000008	과금_4.금액올림방식	PP00036 : 1 올림 2 내림 3 반올림 4 올림없음
//								//VAL4 : DF039	AT00000010	과금_5.금액올림위치	PP00037 : -1 단단위로 맞춤 -2 소수점아래첫째자리로 맞춤 -3 소수점아래둘째자리로 맞춤 0 10단위로 맞춤 1 100단위로 맞춤
//								//VAL5 : DF055	AT00000041	과세_1.대상여부	PP00060	: 0 부과 안함 1 부과
//								val1 = listRrateAttr.get(j).getVal1(); 
//								val2 = listRrateAttr.get(j).getVal2(); 
//								val3 = listRrateAttr.get(j).getVal3();
//								val4 = listRrateAttr.get(j).getVal4();
//								val5 = listRrateAttr.get(j).getVal5();
//								break;
//								}
//							}
//							// 개통일 포함 여부
//							if(val1.equals("1")){
//								listTemp.setUseCnt(Long.parseLong(CUtil.getUseDay(listTemp.getActDt(), listTemp.getInactDt(), 0)));
//							}
//							//일할계산 여부
//							if(val2.equals("00")){
//								useAmt = listRRate.get(i).getRateVal();
//							} else if(val2.equals("01")){
//								useAmt = listTemp.getUseCnt() * listRRate.get(i).getRateVal() / dayCycl;
//							}
//							//올림 단위
//							if(Integer.parseInt(val3) == 1){rRound = 3;}
//							else if(Integer.parseInt(val3) == 2){rRound = 1;} 
//							else if(Integer.parseInt(val3) == 3){rRound = 2;}
//							useAmt = CUtil.Round(useAmt, Integer.parseInt(val4)+1, rRound);
//							listTemp.setUseAmt(useAmt);
//
//							//부가세 여부
//							if(val5.equals("1")){
//								vat = CUtil.Round(useAmt/10, 0, 2); //부가세는 반올림처리
//							} else {
//								vat = 0.0;
//							}
//							listTemp.setVat(vat);
//							break;
//						} //마지막 경우 계산처리
//					} // RateItem Factor 갯수만큼 FOR문 시작
//				} //펑션매핑 FOR
//				//최종 계산값을 리스트에 저장한다. 맵핑 카운트 초기화
//				if(finalChk && fctrChk){
//					listTemp.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//			        if(listTemp.getUseAmt() != 0){
//				        resultList.add(listTemp);
////				        System.out.println(listTemp.toString());
//			        }
//				}
//			} //리스트시작 FOR
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			if(conn != null){
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		return resultList;
//	}

	public int saveJdbcDirect(List<Object>  obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = nBlchb05m01Dao.saveNBlchb05m01Direct(conn, obj);
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