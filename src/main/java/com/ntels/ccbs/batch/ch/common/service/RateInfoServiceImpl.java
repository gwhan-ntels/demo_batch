package com.ntels.ccbs.batch.ch.common.service;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.ch.common.dao.RateInfoDao;
import com.ntels.ccbs.batch.ch.common.entity.DiscExcl;
import com.ntels.ccbs.batch.ch.common.entity.IntergratedList;
import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.common.entity.RateFctr;
import com.ntels.ccbs.batch.ch.common.entity.RateInfo;
import com.ntels.ccbs.batch.common.CUtil;


/**
 * 공통 코드 Service.
 *
 * <PRE>
 * 1. ClassName: RateInfoService
 * 2. FileName : RateInfoService.java
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
public class RateInfoServiceImpl implements RateInfoService {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** RateInfoDao Autowired.  */
	@Autowired
	private RateInfoDao RateInfoDao;
	
	
	/** 
	 * DataSource Autowired.
	 * 
	 *  jdbcTemplate 사용을 위해서는 아래 선언 필요
	 * 
	 * */

	/**
	 * 목록.
	 *
	 * @param condition 조회조건
	 * @return List<RateInfo>
	 */
	public List<RateInfo> list(String chrgCtgry) {

		List<RateInfo> list = RateInfoDao.listRateInfo(chrgCtgry);
		
		if(list == null)
			list = new ArrayList<RateInfo>();
		
		return list;
	}
	
	@SuppressWarnings("unused")
	public Multi listMulti(Multi multi) throws ParseException{
		Multi list = null;
		//TBLCH_MULTI_CYCL 테이블에서 정보 조회
		list = RateInfoDao.listMulti(multi); 
	
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar cal = Calendar.getInstance(); //시작일자

		String sDt = multi.getBillYymm()+list.getUseStDt(); //청구시작일자
		cal.setTime(format.parse(sDt));//개월수에 따른 시작일자로 변환
		cal.add(Calendar.MONTH, list.getUseStMm());
		sDt = format.format(cal.getTime());
		list.setStartDate(sDt); 

		Calendar cal2 = Calendar.getInstance(); //종료일자
		String edDt = multi.getBillYymm()+"01";  //청구종료월
		cal2.setTime(format.parse(edDt)); //개월수에 따른 종료일자로 변환
		cal2.add(Calendar.MONTH, list.getUseEdMm()); 
		edDt = format.format(cal2.getTime());

		if(list.getRealUseEdDt().equals("0") || list.getRealUseEdDt().equals("00")){  //월말일자 + 1
			list.setUseEdDt(CUtil.getLastDay(edDt.substring(0, 6)));
		}

		edDt = CUtil.getNextDay(edDt.substring(0, 6)+list.getUseEdDt());  //청구종료일자 + 1
		list.setEndDate(edDt);
		
		//나머지값을 채운다.
		list.setBillCycl(multi.getBillCycl());
		list.setSeq(multi.getSeq());
		list.setBillYymm(multi.getBillYymm());
		list.setClcWrkNo(multi.getClcWrkNo());
		list.setChrgCtgry(multi.getChrgCtgry());
		list.setpSeq(multi.getpSeq());
		list.setRegDate(multi.getRegDate());

		list.setToday(list.getBillYymm()+list.getBillDt()); //청구작업일자 셋팅

		//멀티계약의 범위를 셋팅한다.
		Multi multiTmp = this.listMultiCtrt(multi);
		list.setStrtNo(multiTmp.getStrtNo());
		list.setEndNo(multiTmp.getEndNo());

		if(list == null)
			list = new Multi();
		return list;
	}

	public List<RateInfo> listRateItm(Multi multi){
		
		List<RateInfo> list = RateInfoDao.listRateItm(multi);
		
		if(list == null)
			list = new ArrayList<RateInfo>();
		
		return list;
	}

	public List<RateAttr> listRrateAttr(Multi multi){
		
		List<RateAttr> list = RateInfoDao.listRrateAttr(multi);
		
		if(list == null)
			list = new ArrayList<RateAttr>();
		
		return list;
	}
	
	public List<RateAttr> listDrateAttr(Multi multi){
		
		List<RateAttr> list = RateInfoDao.listDrateAttr(multi);
		
		if(list == null)
			list = new ArrayList<RateAttr>();
		
		return list;
	}
	
	public Multi listMultiCtrt(Multi multi){
		
		Multi list = RateInfoDao.listMultiCtrt(multi);
		
		if(list == null){
			list = new Multi();
			list.setStrtNo("C000000000");
			list.setEndNo("CZZZZZZZZZ");
		}
		
		return list;
	}
	public List<DiscExcl> listDiscExcl(Multi multi){
		
		List<DiscExcl> list = RateInfoDao.listDiscExcl(multi);
		
		if(list == null)
			list = new ArrayList<DiscExcl>();
		
		return list;
	}

	public List<RateFctr> listRateFctr(Multi multi){
		
		List<RateFctr> list = RateInfoDao.listRateFctr(multi);
		
		if(list == null)
			list = new ArrayList<RateFctr>();
		
		return list;
	}

	public List<RateAttr> listCrateAttr(Multi multi){
		
		List<RateAttr> list = RateInfoDao.listCrateAttr(multi);
		
		if(list == null)
			list = new ArrayList<RateAttr>();
		
		return list;
	}

	/**
	 * 출력.
	 * 
	 * @param RateInfo 공통코드
	 * @return boolean
	 */
	public boolean printRateInfo(RateInfo RateInfo){
		
		l.debug("common code := {}", RateInfo);
		
		return true;
	}

	public String getFctrVal(IntergratedList list, String fctrCd) {
		String fctrVal = null;

		Class<IntergratedList> cls = IntergratedList.class;
		Object obj = list;
		Method m = null;
		String listFctrCd = null;

		try {
			for (int funcNo = 1; funcNo < 20; funcNo++) {
				m = cls.getMethod("getFctrCd" + Integer.toString(funcNo));
				listFctrCd = (String) m.invoke(obj);

				if (listFctrCd.equals(fctrCd)) {
					m = cls.getMethod("getFctrVal" + Integer.toString(funcNo));
					fctrVal = (String) m.invoke(obj);

					break;
				}
			}
		} catch (Exception e) {
		}
		return fctrVal;
	}

	public int getDelimiterCnt(String str, String delimiter) {
		int delimiterCnt = 0;

		for (String spiltString : str.split(delimiter)) {
			delimiterCnt++;
		}

		delimiterCnt--;

		return delimiterCnt;
	}

	public String[] splitString(String str, String delimiter) {
		int delimiterCnt = getDelimiterCnt(str, delimiter);
		String[] oprnd1Array = new String[delimiterCnt + 1];
		int i = 0;

		for (String spiltString : str.split(delimiter)) {
			oprnd1Array[i] = spiltString.trim();
			i++;
		}

		return oprnd1Array;
	}

	public boolean getOprndChk(RateInfo rateInfo, IntergratedList list, String listFctrVal) {
		Boolean bCompCondChk = false;
		int delimiterCnt = 0;
		String chkCond = null;
		String rateFctrVal_1 = null;
		String rateFctrVal_2 = null;
		String[] oprnd1Array = null;
		String[] oprnd2Array = null;
		
		System.err.println("rateInfo = " + rateInfo);
		System.err.println("listFctrVal = " + listFctrVal);
		
		if (rateInfo.getOprndRefFl().equals("0")) {
			delimiterCnt = getDelimiterCnt(rateInfo.getOprndCdA(), ",");
	
			oprnd1Array = new String[delimiterCnt + 1];
			oprnd1Array = this.splitString(rateInfo.getOprndCdA(), ",");

			for (int i = 0; i < delimiterCnt + 1; i++) {
				rateFctrVal_1 = oprnd1Array[i];
				chkCond = rateInfo.getCondOperator();

				if (chkCond.equals("0")) { //NotEqual
					if (!listFctrVal.equals(rateFctrVal_1)) {
						bCompCondChk = true;
					}
				} else if (chkCond.equals("1")) { //Equal
					if (listFctrVal.equals(rateFctrVal_1)) {
						bCompCondChk = true;
					}
				} else if (chkCond.equals("2")) { //Large
					if (listFctrVal.compareTo(rateFctrVal_1) > 0) {
						bCompCondChk = true;
					}
				} else if (chkCond.equals("3")) { //Small
					if (listFctrVal.compareTo(rateFctrVal_1) < 0) {
						bCompCondChk = true;
					}
				} else if (chkCond.equals("4")) { //LargeEqual
					if (listFctrVal.compareTo(rateFctrVal_1) >= 0) {
						bCompCondChk = true;
					}
				} else if (chkCond.equals("5")) { //SmallEqual
					if (listFctrVal.compareTo(rateFctrVal_1) <= 0) {
						bCompCondChk = true;
					}
				} else if (rateInfo.getCondOperator().equals("6")) { //Between
					delimiterCnt = getDelimiterCnt(rateInfo.getOprndCdB(), ",");
					oprnd2Array = new String[delimiterCnt + 1];
					oprnd2Array = this.splitString(rateInfo.getOprndCdB(), ",");

					rateFctrVal_2 = oprnd2Array[i];

					if (rateFctrVal_1.equals("*")
							|| rateFctrVal_2.equals("*")
							|| (listFctrVal.compareTo(rateFctrVal_1) >= 0
							&& listFctrVal.compareTo(rateFctrVal_2) <=0)
					  	    || (listFctrVal.compareTo(rateFctrVal_1) <= 0
							&& listFctrVal.compareTo(rateFctrVal_2) >=0) ) {
						bCompCondChk = true;
					}
				}
			}
		} else {
			bCompCondChk = true;
		}

		return bCompCondChk;
	}

	public boolean getFctrChk(RateInfo rateInfo, IntergratedList list, String listFctrVal, Long rateFctrNo) {
		Boolean bCompFctrChk = false;
		String rateFctrVal_1 = null;
		String rateFctrVal_2 = null;
		String chkCond = null;

		if (rateInfo.getOprndRefFl().equals("0")) {
			if (rateFctrNo == 1) {
				rateFctrVal_1 = rateInfo.getFctrVal1();
			} else if (rateFctrNo == 2) {
				rateFctrVal_1 = rateInfo.getFctrVal2();
			} else if (rateFctrNo == 3) {
				rateFctrVal_1 = rateInfo.getFctrVal3();
			} else if (rateFctrNo == 4) {
				rateFctrVal_1 = rateInfo.getFctrVal4();
			} else if (rateFctrNo == 5) {
				rateFctrVal_1 = rateInfo.getFctrVal5();
			} else if (rateFctrNo == 6) {
				rateFctrVal_1 = rateInfo.getFctrVal6();
			} else if (rateFctrNo == 7) {
				rateFctrVal_1 = rateInfo.getFctrVal7();
			} else if (rateFctrNo == 8) {
				rateFctrVal_1 = rateInfo.getFctrVal8();
			} else if (rateFctrNo == 9) {
				rateFctrVal_1 = rateInfo.getFctrVal9();
			}
		} else {

			if (rateFctrNo == 1) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			} else if (rateFctrNo == 2) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			} else if (rateFctrNo == 3) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			} else if (rateFctrNo == 4) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			} else if (rateFctrNo == 5) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			} else if (rateFctrNo == 6) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			} else if (rateFctrNo == 7) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			} else if (rateFctrNo == 8) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			} else if (rateFctrNo == 9) {
				String fctrCd = rateInfo.getOprndFctrCdA();
				rateFctrVal_1 = getFctrVal(list, fctrCd);
			}
		}

		//기준값을 조건식과 맞추어 계산한다.
		chkCond = rateInfo.getCondOperator();

		if (chkCond.equals("0")) { //NotEqual
			if (rateFctrVal_1.equals("*") || !listFctrVal.equals(rateFctrVal_1)) {
				bCompFctrChk = true;
			}
		} else if (chkCond.equals("1")) { //Equal
			if (rateFctrVal_1.equals("*") || listFctrVal.equals(rateFctrVal_1)) {
				bCompFctrChk = true;
			}
		} else if (chkCond.equals("2")) { //Large
			if (rateFctrVal_1.equals("*") || listFctrVal.compareTo(rateFctrVal_1) > 0) {
				bCompFctrChk = true;
			}
		} else if (chkCond.equals("3")) { //Small
			if (rateFctrVal_1.equals("*") || listFctrVal.compareTo(rateFctrVal_1) < 0) {
				bCompFctrChk = true;
			}
		} else if (chkCond.equals("4")) { //LargeEqual
			if (rateFctrVal_1.equals("*") || listFctrVal.compareTo(rateFctrVal_1) >= 0) {
				bCompFctrChk = true;
			}
		} else if (chkCond.equals("5")) { //SmallEqual
			if (rateFctrVal_1.equals("*") || listFctrVal.compareTo(rateFctrVal_1) <= 0) {
				bCompFctrChk = true;
			}
		} else if (chkCond.equals("6")) { //Between
			if (rateInfo.getOprndRefFl().equals("1")) {
				if (rateFctrNo == 1) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				} else if (rateFctrNo == 2) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				} else if (rateFctrNo == 3) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				} else if (rateFctrNo == 4) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				} else if (rateFctrNo == 5) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				} else if (rateFctrNo == 6) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				} else if (rateFctrNo == 7) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				} else if (rateFctrNo == 8) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				} else if (rateFctrNo == 9) {
					String fctrCd = rateInfo.getOprndFctrCdB();
					rateFctrVal_2 = getFctrVal(list, fctrCd);
				}
			}

			if (rateFctrVal_1.equals("*")
					|| rateFctrVal_2.equals("*")
			        || (listFctrVal.compareTo(rateFctrVal_1) >= 0
				    && listFctrVal.compareTo(rateFctrVal_2) <=0)
		  	        || (listFctrVal.compareTo(rateFctrVal_1) <= 0
				    && listFctrVal.compareTo(rateFctrVal_2) >=0) ) {
				bCompFctrChk = true;
			}
		}

		return bCompFctrChk;
	}

}