package com.ntels.ccbs.batch.ch.tasklet;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateFctr;
import com.ntels.ccbs.batch.ch.common.service.RateInfoService;
import com.ntels.ccbs.batch.ch.entity.NBlchb00m01;
import com.ntels.ccbs.batch.ch.service.NBlchb00m01Service;
import com.ntels.ccbs.batch.common.CUtil;


/**
 * <PRE>
 * 1. ClassName: NBlchb00m01Writer
 * 2. FileName : NBlchb00m01Writer.java
 * 3. Package  : com.ntels.ccbs.batch.ch.tasklet
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:16:24
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */

@Component
@Scope("step") //<-- parameter 필요할 경우 선언
public class NBlchb00m01Writer implements ItemWriter<NBlchb00m01>, StepExecutionListener {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	final static HashMap<String, String> fctrListMap = new HashMap<String, String>();
	
	String stepName;
	ItemWriter<NBlchb00m01> itemWriter;

	@Autowired
	private NBlchb00m01Service nBlchb00m01Service;
	
	@Autowired
	private RateInfoService rateInfoService;

	String prevCtrtId = null;
	String prevProd = null;
	String prevActDt = null;
	String prevInactDt = null;
	String chkMultiEnd = null;
	String listBillYymm = null;
	List<Object> objectlist = new ArrayList<Object>();
	List<RateFctr> listFctr;
	Multi multi = new Multi();
	
	/**
	 * job step이 시작 되기 전 실행
	 * 
	 * @param stepExecution void
	 */
	@BeforeStep
	public void getInterStepData(StepExecution stepExecution) {
		stepName = stepExecution.getStepName();
	}

	@Value("#{jobParameters['logFileName']}") String logFileName;
	@Value("#{jobParameters['clcWrkNo']}")    String clcWrkNo;
	@Value("#{jobParameters['soId']}")        String soId;
	@Value("#{jobParameters['billYymm']}")    String billYymm;
	@Value("#{jobParameters['billCycl']}")    String billCycl;
	@Value("#{jobParameters['pgmId']}")       String pgmId;
	@Value("#{jobParameters['pgmSeq']}")      String pgmSeq;
	@Value("#{jobParameters['grpId']}")       String grpId;
	@Value("#{jobParameters['pgmExeSeqNo']}") String pgmExeSeqNo;
	
	public void write(List<? extends NBlchb00m01> items) throws Exception {
		objectlist.clear();
		
		for (NBlchb00m01 list : items) {
			if (CUtil.isExistBetweenDays(multi.getStartDate(), multi.getEndDate(), list.getRateStrtDt()) || multi.getBillCycl().equals("Z0")) {
				list.setRenewDt("000000");
				setList(list);
			} else {
				list.setRenewDt(list.getBillYymm());
				setList(list);
			}
			objectlist.add(list);
		}
		nBlchb00m01Service.saveJdbcDirect(objectlist);
	}

	private void setList(NBlchb00m01 list) throws Exception, Exception {
		if (!fctrListMap.get(list.getProdCd()).isEmpty()) {

			String str[] = fctrListMap.get(list.getProdCd()).split(";");

			for (int k = 0; k < str.length; k++) {
				String str1[] = str[k].split("!");

				for (int kk = 0; kk < str1.length; kk++) {
					if (kk == 0) {

					}

					if (str1[1] == "T" || str1[1].equals("T")) {
						if (kk == 3) {
							// 메소드로 따로 뽑을 것
							String colList[] = list.getColunmId().split("\\|");
							String orgColId = "";
							
							for (int j = 0; j < colList.length; j++) {

								orgColId = colList[j];
								String str2[] = orgColId.split("=");
								String fctrKey = str2[0];
								String fctrVal = "";

								if (str2.length == 2) {
									fctrVal = str2[1];
								} else if (str2.length == 1) {
									fctrVal = "";
								}
								if (str1[3] == fctrKey || fctrKey.equals(str1[3])) {

									invokeObj(list, "setFctrCd" + (k + 1), str1[0]);
									invokeObj(list, "setFctrVal" + (k + 1), fctrVal);
								}
							}

						}

					} else if (str1[1] == "F" || str1[1].equals("F")) {
						if (kk == 4) {
							String colId = "";
							// 임시 주석 처리 실제로 주서 풀고 실행 할 것 str1[4] 값이
							// FBL_FT108(PROD_CD,RATE_ITEM_CD,LVL_ID,FACTOR_NO,PRICE_TYPE)형태로되어
							// 있지 않으면 Error 발생
							String func = str1[4];
							String funcStr[] = func.split("\\(");
							String funcNm = funcStr[0];
							String value = funcStr[1].replaceAll("\\)", "");
							String funcVal = "(";
							String getVal[] = value.split(",");

							for (int j = 0; j < getVal.length; j++) {

								if (j == getVal.length - 1) {
									funcVal += "'" + getFctrValMethod(list, getVal[j].trim()) + "')";
								} else {
									funcVal += "'" + getFctrValMethod(list, getVal[j].trim()) + "',";
								}
							}
							colId = funcNm + funcVal;

							multi.setColId(colId);

							Multi multiTmp = nBlchb00m01Service.getFunc(multi);
							invokeObj(list, "setFctrCd" + (k + 1), str1[0]);
							invokeObj(list, "setFctrVal" + (k + 1), multiTmp.getSetVal());

						}
					}
				}
			}
		}
	}

	public void invokeObj(Object cls, String methodKey, String methodValue){
		try{
			String methodNm = methodKey;
			Method method = cls.getClass().getMethod(methodNm, new Class[]{String.class});
			Object[] paramObj = new Object[]{methodValue};
			method.invoke(cls,paramObj);			
		}catch(NoSuchMethodException nsme){
			System.out.println(nsme.toString());
		}catch(Exception e){
			System.out.println(e);
		}
	}

	private String getFctrValMethod(NBlchb00m01 list, String colmnID) throws Exception, Exception {
		Method m;
		Class cls = null;
		String methID = "";
		String str[] = colmnID.split("_");
		String val = "";

		for (int i = 0; i < str.length; i++) {
			methID += str[i].charAt(0) + str[i].substring(1, str[i].length()).toLowerCase();
		}

		methID = "get" + methID;

		cls = list.getClass();
		m = cls.getMethod(methID, null);
		val = m.invoke(list, null).toString();
		return val;

	}
	
//	private String getFctrVal(NBlchb00m01 list, String colmnId) {
//		String sCamelID = null;
//		String listFctrVal = null;
//
//		Class<NBlchb00m01> cls = NBlchb00m01.class;
//		Object obj = list;
//		Method m = null;
//
//		sCamelID = toCamelCase(colmnId);
//
//		try {
//			m = cls.getMethod("get" + sCamelID);
//			listFctrVal = (String) m.invoke(obj);
//		} catch (Exception e) {
//		}
//
//		return listFctrVal;
//
//	}
//
//	private String toCamelCase(String target) {
//		StringBuffer buffer = new StringBuffer();
//
//		for (String token : target.toLowerCase().split("_")) {
//			buffer.append(StringUtils.capitalize(token));
//		}
//
//		return buffer.toString();
//	}

	public void beforeStep(StepExecution stepExecution) {
		multi.setSoId(soId);
		multi.setBillYymm(billYymm);
		multi.setClcWrkNo(clcWrkNo);
		multi.setBillCycl(billCycl);
		multi.setMultiCycl(billCycl.substring(0, 1));
		multi.setSeq(billCycl.substring(1, 2));
		multi.setpSeq("2");
		multi.setRegDate(new java.sql.Timestamp(new java.util.Date().getTime()));

        try {
			multi = rateInfoService.listMulti(multi);
			listFctr = rateInfoService.listRateFctr(multi);
			
			for(int i =0; i<listFctr.size(); i++){
				fctrListMap.put(listFctr.get(i).getProdCd(), listFctr.get(i).getFctrIn());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

}