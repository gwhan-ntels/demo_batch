package com.ntels.ccbs.batch.ch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.ch.common.entity.DiscExcl;
import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.common.entity.RateInfo;
import com.ntels.ccbs.batch.ch.common.service.RateInfoService;
import com.ntels.ccbs.batch.ch.dao.NBlchb03m01Dao;
import com.ntels.ccbs.batch.ch.entity.NBlchb03m01;
import com.ntels.ccbs.batch.common.LazyLoader;


/**
 * <PRE>
 * 1. ClassName: NBlchb03m01ServiceImpl
 * 2. FileName : NBlchb03m01ServiceImpl.java
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
public class NBlchb03m01ServiceImpl implements NBlchb03m01Service {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** StandardChargeDao Autowired.  */
	@Autowired
	private NBlchb03m01Dao nBlchb03m01Dao;
	
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
	public boolean printStandardCharge(NBlchb03m01 nBlchb03m01){
		
		l.debug("common code := {}", nBlchb03m01);
		
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

	public LazyLoader<NBlchb03m01> listJdbcDirect(Multi multi) {
		return nBlchb03m01Dao.listNBlchb03m01Direct(multi);
//
//		Connection conn = null;
//		List<NBlchb03m01> list = null;
//		List<NBlchb03m01> resultList = new ArrayList<>();
//
//		multi.setChrgCtgry("D");
//		listDRate = rateInfoService.listRateItm(multi);
//		listDrateAttr = rateInfoService.listDrateAttr(multi);
//		listDiscExcl = rateInfoService.listDiscExcl(multi);
//		
//		String fctrVal = null; //팩터 값
//		Boolean fctrChk = false; //팩터 체크 결과
//		String prevProdCmpsId = "First";
//		String prevRateItmCd = "First";
//		String prevSvcRateItmTypCd = null;
//		boolean svcRateChk = false; //할인내역 편성 여부
//		Double prevTotDisAmt = 0.0;
//		Double prevTotAmt = 0.0;
//		Double rRateAmt = 0.0;
//		int fctrNo = 1;
//		int disChrgCnt = 0; /* 배타 할인 체크용 */
//		int matchCnt = 0; /* 팩터 체크용 */
//		boolean exclYn = false; //배타 할인 여부
//		boolean finalChk = false; //최종 내역 생성 여부
//		
//		try{
//			conn = dataSource.getConnection();
//			list = nBlchb03m01Dao.listNBlchb03m01Direct(conn, multi);
//			
//			for(NBlchb03m01 listTemp : list){
//				matchCnt = 0;
//				finalChk = false;
//				exclYn = false;
//				String realStdt = CUtil.getCompDate(listTemp.getActDt(), multi.getStartDate(), listTemp.getRactDt(), "MAX");
//				listTemp.setActDt(realStdt);
//				String realEddt = CUtil.getCompDate(listTemp.getInactDt(), multi.getEndDate(), listTemp.getRinactDt(), "MIN");
//				listTemp.setInactDt(realEddt);
//
//				listTemp.setUseCnt(Long.parseLong(CUtil.getUseDay(listTemp.getActDt(), listTemp.getInactDt(), 0)));
//				
//				int dayCycl = Integer.parseInt(CUtil.getUseDay(multi.getStartDate(), multi.getEndDate(), 0));
//
//				if(prevProdCmpsId.equals("First")){ //최초 대상이 없는 경우 계약, 상품id를 채워준다.
//					prevProdCmpsId = listTemp.getSvcCmpsId();
//					prevRateItmCd = listTemp.getRateItmCd();
//					prevSvcRateItmTypCd = listTemp.getSvcRateItmTypCd();
//					prevTotAmt = listTemp.getTotRchrg();
//					svcRateChk = true;
//				}else if(!prevProdCmpsId.equals(listTemp.getSvcCmpsId())){ //서비스 대상이 다르면 정보 및 계산, 할인총액, 할인내역 초기화
//					prevProdCmpsId = listTemp.getSvcCmpsId();
//					prevRateItmCd = listTemp.getRateItmCd();
//					prevSvcRateItmTypCd = listTemp.getSvcRateItmTypCd();
//					prevTotAmt = listTemp.getTotRchrg();
//					prevTotDisAmt = 0.0;
//					fctrNo = 1;
//					svcRateChk = false;
//					disChrgCd.clear();
//					disChrgCnt = 0;
//				}else if(!prevRateItmCd.equals(listTemp.getRateItmCd())
//					   ||!prevSvcRateItmTypCd.equals(listTemp.getSvcRateItmTypCd())){ 
//					if(!prevRateItmCd.equals(listTemp.getRateItmCd())){//RateItmCd만 바뀌면 팩터 초기화 
//						prevRateItmCd = listTemp.getRateItmCd();
//						fctrNo = 1;
//					} 
//					if(!prevSvcRateItmTypCd.equals(listTemp.getSvcRateItmTypCd())){ //요금항목이 바뀌면 할인내역으로 등록
//						prevSvcRateItmTypCd = listTemp.getSvcRateItmTypCd();
//						svcRateChk = true;
//					}
//				}
//				//할인배타체크 (체크테이블에서 리스트를 차례대로 불러와서 할인누적항목과 맵핑되면 다음 할인으로 처리한다.)
//				for(DiscExcl chkDiscExcl : listDiscExcl){
//					if(listTemp.getRateItmCd().equals(chkDiscExcl.getRateItmCd())){
//						for(Map.Entry<String, String> elem : disChrgCd.entrySet()){
//							if(elem.getValue().equals(chkDiscExcl.getExclSvcRateItmTypCd())){
//								exclYn = true;
//								break;
//							}
//						}
//					}
//					if(exclYn){ //배타이므로 배타체크를 해제한다.
//						break;
//					}
//				}
//				if(exclYn){ //배타체크되면, 다음 할인으로 이동한다.
//					continue;
//				}
//				
//				rRateAmt = listTemp.getUseAmt(); // *dayCycl / listTemp.getUseCnt()
//
//				useAmt = 0;
//				vat = 0;
//				Class<NBlchb03m01> cls = NBlchb03m01.class;
//				Object obj = listTemp;
//				Method m = null;
//				String listFctrCd = null;
//				String listFctrVal = null;
//				for(int funcNo = 1; funcNo < 10 ;funcNo++){ //현재는 9개의 팩터만 사용함
//					m = cls.getMethod("getFctrCd"+Integer.toString(funcNo));						
//					listFctrCd = (String) m.invoke(obj);
//					m = cls.getMethod("getFctrVal"+Integer.toString(funcNo));
//					listFctrVal = (String) m.invoke(obj);
//					if(listFctrCd == null || finalChk){
//						break;
//					}
//					for(int i=0; i< listDRate.size(); i++){
//						if(listDRate.get(i).getProdCd().compareTo(listTemp.getProdCd()) > 0){
//							break;
//						}else if(listDRate.get(i).getProdCd().compareTo(listTemp.getProdCd()) < 0){
//							continue;
//						}else if(listTemp.getRateItmCd().equals(listDRate.get(i).getRateItmCd())
//						  && listFctrCd.equals(listDRate.get(i).getFctrCd())
//					      && listTemp.getRsvcRateItmTypCd().equals(listDRate.get(i).getDisSvcRateItmTypCd())
//						  && fctrNo <= listDRate.get(i).getFctrNo()
//					      && listTemp.getRinactDt().equals(listDRate.get(i).getInactDt())
//					      && listTemp.getRactDt().equals(listDRate.get(i).getActDt())
//					      && listDRate.get(i).getFctrRefTyp().equals("T")){
//							//Fctr번호에 따른 기준값을 가져온다.
//							Long chkFctrNo = listDRate.get(i).getFctrNo();
//							if(chkFctrNo == 1){
//								fctrVal = listDRate.get(i).getFctrVal1();
//								fctrNo = 1;
//							}else if(chkFctrNo == 2){
//								fctrVal = listDRate.get(i).getFctrVal2();
//								fctrNo = 2;
//							}else if(chkFctrNo == 3){
//								fctrVal = listDRate.get(i).getFctrVal3();
//								fctrNo = 3;
//							}else if(chkFctrNo == 4){
//								fctrVal = listDRate.get(i).getFctrVal4();
//								fctrNo = 4;
//							}else if(chkFctrNo == 5){
//								fctrVal = listDRate.get(i).getFctrVal5();
//								fctrNo = 5;
//							}else if(chkFctrNo == 6){
//								fctrVal = listDRate.get(i).getFctrVal6();
//								fctrNo = 6;
//							}else if(chkFctrNo == 7){
//								fctrVal = listDRate.get(i).getFctrVal7();
//								fctrNo = 7;
//							}else if(chkFctrNo == 8){
//								fctrVal = listDRate.get(i).getFctrVal8();
//								fctrNo = 8;
//							}else if(chkFctrNo == 9){
//								fctrVal = listDRate.get(i).getFctrVal9();
//								fctrNo = 9;
//							}
//							
//							//기준값을 조건식과 맞추어 계산한다.
//							String chkCond = listDRate.get(i).getCondOperator(); 
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
//								   || (listFctrVal.compareTo(listDRate.get(i).getOprnd1()) >= 0
//									&& listFctrVal.compareTo(listDRate.get(i).getOprnd2()) <=0)
//							  	   || (listFctrVal.compareTo(listDRate.get(i).getOprnd1()) <= 0
//									&& listFctrVal.compareTo(listDRate.get(i).getOprnd2()) >=0) ){
//									fctrChk = true;
//								}else{ fctrChk = false;}
//							}
//							//팩터 대상 카운트
//							if(fctrChk){
//								matchCnt++;
//							}
//							if(listDRate.get(i).getFctrCnt() == matchCnt){
//								finalChk = true;
//							}
//							//마지막 처리내역이면서 정상인 경우에만 처리한다.
//							if(finalChk && fctrChk){
//								//속성별 계산된 값을 정의한다.
//								for(int j=0; j <listDrateAttr.size(); j++){
//									if(listDRate.get(i).getRateItmCd().equals(listDrateAttr.get(j).getRateItmCd())){
//									//VAL2 : DF049	AT00000031	할인_1.금액일할계산여부	PP00043 : 0 일할계산미적용 1 일할계산적용
//									//VAL3 : DF050	AT00000014	할인_2.단위	PP00045	: 1 % 2 할인금액 3 생성금액
//									//VAL4 : DF052	AT00000015	할인_4.적용방식	PP00049	:  G 원금액 N 할인후 금액
//									//VAL5 : DF055	AT00000041	과세_1.대상여부	PP00060	: 0 부과 안함 1 부과
//									val1 = listDrateAttr.get(j).getVal1(); 
//									val2 = listDrateAttr.get(j).getVal2(); 
//									val3 = listDrateAttr.get(j).getVal3();
//									val4 = listDrateAttr.get(j).getVal4();
//									val5 = listDrateAttr.get(j).getVal5();
//									break;
//									}
//								}
//								//정액금액인지 할인금액인지 정한다.
//								if(val4.equals("G")){
//									useAmt = (-1) * rRateAmt;
//								}else if(val4.equals("N")){
//									useAmt = (-1) * rRateAmt - prevTotDisAmt; 
//								}
//								//할인을 %, 생성금액일 경우 처리한다.
//								if(val3.equals("1")){
//									useAmt = useAmt * listDRate.get(i).getRateVal() /100;
//								}else if(val3.equals("2")){ //할인 금액으로 변경
//									useAmt = listDRate.get(i).getRateVal(); 
//								}else if(val3.equals("3")){ //정액과금 - 할인발생금액
//									useAmt = rRateAmt + (useAmt * listTemp.getUseCnt() / dayCycl);
//								}
//								//할인할 금액이 정해지면 할인금액일, 일할 계산일 경우에만 처리하고, 아니면 금액 그대로 둔다.
//								if(val2.equals("1") && val3.equals("2")){
//									useAmt = listTemp.getUseCnt() * useAmt / dayCycl;
//								}
//								//반올림처리
//								useAmt = CUtil.Round(useAmt, 0, 2);
//								listTemp.setUseAmt(useAmt);
//								//부가세를 처리한다.
//								if(val5.equals("1")){
//									vat = CUtil.Round(useAmt/10, 0, 2); //부가세는 반올림처리
//								} else {
//									vat = 0.0;
//								}
//								listTemp.setVat(vat);
//								break;
//							} //마지막 경우 계산처리
//						} // RateItem같은 경우
//					} // RateItem Factor 갯수만큼 FOR문 시작
//				} //FACTOR FOR
//				//최종 계산값을 리스트에 저장한다. 맵핑 카운트 초기화
//				if(finalChk && fctrChk){
//					prevTotAmt = prevTotAmt + useAmt;
//					//음수 발생하면 차액만큼 금액을 제한다. 재계산처리하고, 총액 비교금액을 0으로 변환한다.
//					if(prevTotAmt < 0){
//						useAmt = useAmt + (-1)*prevTotAmt;
//						vat = CUtil.Round(useAmt/10, 0, 2); //부가세는 반올림처리
//						prevTotAmt = 0.0;
//						listTemp.setUseAmt(useAmt);
//						listTemp.setVat(vat);
//					}
//
//					listTemp.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//			        if(listTemp.getUseAmt() != 0){
//						if(svcRateChk){
//							disChrgCnt++;
//							disChrgCd.put(Integer.toString(disChrgCnt), listTemp.getSvcRateItmTypCd());
//							svcRateChk = false;
//						}
//						prevTotDisAmt = prevTotDisAmt + useAmt;
//				        resultList.add(listTemp);
//						System.out.println(listTemp.getCtrtId()+listTemp.getSvcCmpsId()+listTemp.getRateItmCd()+disChrgCd);
////				        System.out.println(listTemp.toString());
//			        }
//				}
//			} //리스트 FOR
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
	}

	public int saveJdbcDirect(List<Object>  obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = nBlchb03m01Dao.saveNBlchb03m01Direct(conn, obj);
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