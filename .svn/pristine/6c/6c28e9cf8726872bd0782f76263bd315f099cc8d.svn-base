package com.ntels.ccbs.batch.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


/**
 * 
 */

/**
 * @author ntels_shlee
 * 
 */
public class CBL {

	public static final String LOG_PATH_CH = "\\LOG\\CH";
	public static final String LOG_PATH_IV = "\\LOG\\IV";
	public static final String LOG_PATH_PY = "\\LOG\\PY";
	public static final String LOG_PATH_UP = "\\LOG\\UP";

	public static final String BILL_PAYDUEDT = "00057"; 
	public static final String BILL_EXCHRATEDT = "00076";
	public static final String BILL_BILLDT = "00055"; 
	public static final String BILL_VAT = "00021"; 

	public final String LOG_ROOT = "c:\\tmp";
	public CBlSql clsBlSql;
	private Map<String, String> billStepMap = new HashMap<String, String>();
	private Map<String, Object> billExRatepMap = new HashMap<String, Object>();

	public CBL() {
		clsBlSql = new CBlSql();
	}

	public int InitializeBillinfo(CDblib cDblib, String so_id, String bill_yymm, String bill_cycl) throws IOException {
		int nRet = 0;

		nRet = this.setBillStepInfo(cDblib, so_id, bill_yymm, bill_cycl);
		if (nRet == 0)
			nRet = this.setBillExRateInfo(cDblib);

		return nRet;
	}

	public String getBillVal(String set_itm_id) {
		return billStepMap.get(billStepMap);

	}

	public long getExRateInfo(String crncy_cd) {
		return Long.parseLong(billStepMap.get(crncy_cd).toString());

	}

	public int UpdateClcMain(CDblib cDblib, String clcWrkNo, String gubun) throws IOException, Exception {
		int nRet = 0;
		String strSql = new String();

		strSql = clsBlSql.updTblcaClcMain(clcWrkNo, gubun);
		if (cDblib.exeSQL(strSql) == -1) {
			return -1;
		}
		return nRet;
	}

	private int setBillStepInfo(CDblib cDblib, String so_id, String bill_yymm, String bill_cycl) throws IOException {
		int nRet = 0, i = 0;
		String strSql = new String();
		List<Object> vOut = new ArrayList<Object>();
		String strSetItmId = new String();
		String strSetVal = new String();

		strSql = clsBlSql.selTblivBillStp(so_id, bill_yymm, bill_cycl);
		vOut = cDblib.exeSelSQLM(strSql);
		for (i = 1; i < vOut.size(); i++) {
			Map<String, Object> sqlMapCol = (Map<String, Object>) vOut.get(i);
			strSetItmId = sqlMapCol.get("SET_ITM_ID").toString();
			strSetVal = sqlMapCol.get("SET_VAL").toString();
			billStepMap.put(strSetItmId, strSetVal);
		}
		if (vOut.size() <= 1)
			nRet = -1;

		return nRet;
	}

	private int setBillExRateInfo(CDblib cDblib) throws IOException {
		int nRet = 0, i = 0;
		String strSql = new String();
		List<Object> vOut = new ArrayList<Object>();

		strSql = clsBlSql.setTblivExRateInfo();
		vOut = cDblib.exeSelSQLM(strSql);
		
		for (i = 1; i < vOut.size(); i++) {
			Map<String, Object> sqlMapCol = (Map<String, Object>) vOut.get(i);
			billExRatepMap.put(sqlMapCol.get("CRNCY_CD").toString(), sqlMapCol.get("EXRATE"));
		}
		if (vOut.size() <= 1)
			nRet = -1;

		return nRet;
	}

	public int WriteLogBill(CLogKey cLogKey, CDblib cDblib, CLog cLog, String gubun, String bat_proc_stat)
			throws IOException, Exception {
		int nRet = 0;
		String strSql = new String();
		StringBuffer msgBuff = new StringBuffer();
		List<Object> sqlRetList;
		String strClcYn = new String();

		// 1. SELECT * FROM TBL_BAT_PGM_MASTER
		strSql = clsBlSql.selTblivBatPgm(cLogKey.getPgmId());

		sqlRetList = new ArrayList<Object>();

		sqlRetList = cDblib.exeSelSQLM(strSql);
		if (sqlRetList.size() != 2) {
			msgBuff.delete(1, msgBuff.length());
			msgBuff.append("[SQL:]");
			msgBuff.append(strSql);
			msgBuff.append(" ] : error");
			cLog.WriteLog(msgBuff.toString());
			return -1;
		}

		Map<String, Object> sqlMap = (Map<String, Object>) sqlRetList.get(1);
		strClcYn = "Y";
		if (gubun.equals("I"))
			strClcYn = "N";

		strSql = clsBlSql.insTblivBatPgmLog(cLogKey.getLogFilePaht(), cLogKey.getLogFileNm(), bat_proc_stat,
				cLogKey.getGrpId(), cLogKey.getSoId(), cLogKey.getPgmId(), cLogKey.getPgmExeSeqNo() + cLogKey.getPSeq(),
				cLogKey.getBillYymm(), cLogKey.getBillCycl(), cLogKey.getRegId());

		if (cDblib.exeSQL(strSql) == -1) {
			strSql = clsBlSql.updTblivBatPgmLog(cLogKey.getReadCnt(), cLogKey.getProcCnt(), cLogKey.getErrCnt(),
					cLogKey.getWriteCnt(), cLogKey.getGrpId(), cLogKey.getSoId(), cLogKey.getPgmId(),
					cLogKey.getPgmExeSeqNo() + cLogKey.getPSeq(), cLogKey.getBillYymm(), bat_proc_stat);
			if (cDblib.exeSQL(strSql) == -1) {
				cLog.WriteLog("TblivBatPgmLog Error");
				return -1;
			}

		}
		if ((cLogKey.getGrpId().equals("00003") || cLogKey.getGrpId().equals("00004")
				|| cLogKey.getGrpId().equals("00005")) && sqlMap.get("CLS_STP_CL").equals("1")) {
			strSql = clsBlSql.insTblivClsInfo("1", cLogKey.getBillYymm(), cLogKey.getBillCycl(), strClcYn,
					cLogKey.getSoId(), cLogKey.getRegId());

			if (cDblib.exeSQL(strSql) == -1) {
				strSql = clsBlSql.updTblivClsInfo(cLogKey.getSoId(), cLogKey.getBillYymm(), "1", strClcYn);
				if (cDblib.exeSQL(strSql) == -1) {
					cLog.WriteLog("TblivClsInfo Error");
					return -1;
				}
			}
		}
		return nRet;
	}

}
