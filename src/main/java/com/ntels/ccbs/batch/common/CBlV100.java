/**
 * @FileName
 * CBlV100.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 5. 2.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.batch.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;

public class CBlV100 {

	
	public static final String BILL_PAYDUEDT   = "00057"; 
	public static final String BILL_EXCHRATEDT = "00076";
	public static final String BILL_BILLDT     = "00055"; 
	public static final String BILL_VAT        = "00021"; 

	public CBlSql clsBlSql;
	private Map<String, String> billStepMap    = new HashMap<String, String>();
	private Map<String, Object> billExRatepMap = new HashMap<String, Object>();
	
	public CBlV100() {
		clsBlSql = new CBlSql();
	}

	public int InitializeBillinfo(Connection conn, String so_id, String bill_yymm, String bill_cycl) throws IOException {
		int nRet = 0;

		nRet = this.setBillStepInfo(conn, so_id, bill_yymm, bill_cycl);
		if (nRet == 0)
			nRet = this.setBillExRateInfo(conn);

		return nRet;
	}

	public String getBillVal(String set_itm_id) {
		return billStepMap.get(billStepMap);

	}

	public long getExRateInfo(String crncy_cd) {
		return Long.parseLong(billStepMap.get(crncy_cd).toString());

	}

	
	private int setBillStepInfo(Connection conn, String so_id, String bill_yymm, String bill_cycl) throws IOException {
		int nRet = 0, i = 0;
		String strSql = new String();
		List<Object> vOut = new ArrayList<Object>();
		String strSetItmId = new String();
		String strSetVal = new String();

		strSql = clsBlSql.selTblivBillStp(so_id, bill_yymm, bill_cycl);
		vOut = CDaoLib.exeSelSQLM(conn, strSql);
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

	private int setBillExRateInfo(Connection conn) throws IOException {
		int nRet = 0, i = 0;
		String strSql = new String();
		List<Object> vOut = new ArrayList<Object>();

		strSql = clsBlSql.setTblivExRateInfo();
		vOut = CDaoLib.exeSelSQLM(conn,strSql);
		
		for (i = 1; i < vOut.size(); i++) {
			Map<String, Object> sqlMapCol = (Map<String, Object>) vOut.get(i);
			billExRatepMap.put(sqlMapCol.get("CRNCY_CD").toString(), sqlMapCol.get("EXRATE"));
		}
		if (vOut.size() <= 1)
			nRet = -1;

		return nRet;
	}

	public String getBillSeqNo(String bill_yymm, String bill_cycl, String bill_dt, String pym_acnt_id,  String seq)
	{
		
	    String bill_seq_no ;
	    
	    
   	       // bill_yymm(4-yymm)+bill_cycl(2)+ bill_dt( 2-dd) + pym_acnt_id ( 10 ) + seq(2)
	        bill_seq_no = bill_yymm.substring(2, 4) + bill_cycl + bill_dt + pym_acnt_id + seq;
	        return bill_seq_no;
	}
	
	
		
	
}
