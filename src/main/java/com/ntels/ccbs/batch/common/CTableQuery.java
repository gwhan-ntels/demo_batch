/**
 * ----------------------------------------------------------------------------------------------------------
 * 파일 명       : CTableQuery.java
 * 프로젝트 명 : 
 * 작성일자     : 2016. 03. 04.
 * 작성자        : ntels_shlee
 * 설명          
 *     
 * 변경 이력    
 *     <버전>       <일자>         <작성자>         <설명 >
 *    ver 1.00 | 2016.03.04 | ntels_shlee |  최초 작성
 * ----------------------------------------------------------------------------------------------------------
 */
package com.ntels.ccbs.batch.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CTableQuery {
	private String Query;
	private String ColmnMap;
	public Vector<String> ColInfo = new Vector<String>();
	/**
	 * @return the query
	 */
	public String getQuery() {
		return Query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		Query = query;
	}
	
	
	public boolean getTableQuery(CDblib dblib, String pgm_id, String subId, String subTp) throws IOException
	{
		boolean   bRet;
		String   strSql;
		int      i;
		List<Object>        vRes = null;
		Map<String, Object> sqlMap;
		
		    strSql = "SELECT QUERY,COLMN_MAP FROM NTELS_TABLE_QUERY WHERE PGM_ID = '"
		           + pgm_id + "' AND PGM_SUB_ID = '" + subId  + "' AND PGM_SUB_TP='" + subTp + "'";
			bRet = true;
			vRes = dblib.exeSelSQLM(strSql);
			
		    sqlMap = (Map<String, Object>) vRes.get(1);
		    ColmnMap = (String) sqlMap.get("COLMN_MAP");
			Query = (String) sqlMap.get("QUERY");
			
			ColInfo.clear();
		
			if(  ColmnMap != null ) ColInfo = CUtil.utilToken(ColmnMap, ",");
			return bRet;
	}
	
	
	public int updatTable(List<Object> vRes,CDblib dblib ) throws Exception 
	{
		
		boolean  bRet;
		int nWcnt;
		
			
		    nWcnt = 0;
		    
		    
			try {
				if ( vRes.size() > 1 )
	            	nWcnt = dblib.exeSQL(vRes, this.getQuery(),this.ColInfo);
			} catch (IOException e) {
				e.printStackTrace();
				nWcnt = -1;
			}
			
			return nWcnt;
	}

	public int selTable(List<Object> vRes,CDblib dblib ) throws Exception 
	{
		
		boolean  bRet;
		int nWcnt;
		
			
		    nWcnt = 0;
		    
		    
			try {
				if ( vRes.size() > 1 )
	            	nWcnt = dblib.exeSQL(vRes, this.getQuery(),this.ColInfo);
			} catch (IOException e) {
				e.printStackTrace();
				nWcnt = -1;
			}
			
			return nWcnt;
	}

	
	/**
	 * @return the colmnMap
	 */
	public String getColmnMap() {
		return ColmnMap;
	}
	/**
	 * @param colmnMap the colmnMap to set
	 */
	public void setColmnMap(String colmnMap) {
		ColmnMap = colmnMap;
	}
	
	
	
	
}
