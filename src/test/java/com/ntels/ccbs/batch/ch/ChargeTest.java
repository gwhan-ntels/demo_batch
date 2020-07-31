package com.ntels.ccbs.batch.ch;

import org.junit.Test;

import com.ntels.ccbs.batch.TestCommon;
import com.ntels.ccbs.batch.ch.common.entity.Multi;

public class ChargeTest extends TestCommon {

	@Test
	public void nblchb00m01Test() {
		String testStr = "FT101!T!TCMCT_SVC_CMPS_INFO_EXT!LOCAL!;FT103!T!TCMCT_SVC_CMPS_INFO_EXT!SVC_LVL!;FT201!F!!!FBL_FT201( RATE_STRT_DT);FT104!T!TCMCT_SVC_CMPS_INFO_EXT!RT_ID!;FT203!F!!!FBL_FT203(MODEL_NO,BASIC_CORE_CNT,ADD_CORE_CNT);FT210!F!!!FBL_FT210(MODEL_NO,BASIC_CORE_CNT,FIX_RATE,USE_RATE);FT206!F!!!FBL_FT206(ADD_CORE_CNT);FT211!F!!!FBL_FT211(MODEL_NO,ADD_CORE_CNT,FIX_RATE,USE_RATE);FT207!F!!!FBL_FT207( SO_ID, CTRT_ID);FT212!F!!!FBL_FT212(MODEL_NO,FIX_RATE,USE_RATE);FT109!T!TCMCT_SVC_CMPS_INFO_EXT!MODEL_CD!";
		String str[] = testStr.split(";");

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
								funcVal += "'"
										+ getFctrValMethod(list,
												getVal[j].trim()) + "')";
							} else {
								funcVal += "'"
										+ getFctrValMethod(list,
												getVal[j].trim()) + "',";
							}
						}
						colId = funcNm + funcVal;

						multi.setColId(colId);

						Multi multiTmp = nBlchb00m01Service.getFunc(multi);
						invokeObj(list, "setFctrCd" + (k + 1), str1[0]);
						invokeObj(list, "setFctrVal" + (k + 1),
								multiTmp.getSetVal());

					}
				}
			}
		}
	}

}
