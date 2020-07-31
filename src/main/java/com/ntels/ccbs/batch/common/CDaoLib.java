/**
 * @FileName
 * CDaoLib.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 4. 27.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.batch.common;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import com.ntels.ccbs.batch.util.QueryUtil;

public class CDaoLib {


	public static List<Object> exeSelSQLM(Connection conn, String sql, Vector<Object> vColum, Object obj ) throws IOException, Throwable, Throwable
	{
		List<Object> vResult = new ArrayList<Object>();
		PreparedStatement conPst = null;
	    ResultSet         conRs = null;
		int i = 0, j=0;

		Map<String, Object> sqlMap;
		vResult.clear();
	
		
		Class tt;
		tt = obj.getClass();
		
		try {
			conPst = conn.prepareStatement(sql);
			if ( vColum.size() >= 1  )
			{ 
			         
				for(i=0; i<vColum.size(); i++ ) 
				{	
				  if(vColum.get(i) instanceof Long)	 conPst.setLong(i+1, (Long)vColum.get(i));
				  else if (vColum.get(i) instanceof String )  conPst.setString(i+1, (String)vColum.get(i));
				  else if (vColum.get(i) instanceof Integer ) conPst.setInt(i+1, (Integer)vColum.get(i));
				  else if (vColum.get(i) instanceof Double ) conPst.setDouble(i+1, (Double)vColum.get(i));
				  else if (vColum.get(i) instanceof Float ) conPst.setFloat(i+1, (Float)vColum.get(i));
				  else if (vColum.get(i) instanceof java.sql.Timestamp ) conPst.setTimestamp(i+1, (java.sql.Timestamp)vColum.get(i));
				}	
			}
			
			conRs = conPst.executeQuery();
			ResultSetMetaData rMeta = conRs.getMetaData();

			i=0;
			j=0;
			while (conRs.next() == true) {

				Object o = tt.newInstance();
		        Method me1 = tt.getMethod("setValue", String.class, String.class);
				   
				j++;
				i=1;
				String a;
		  	//    System.out.println("conRs  :[ " + j + "]" + CUtil.utilGetDateTime(4));
				while (i <= rMeta.getColumnCount()) {	
					
					if ( conRs.getObject(i) == null) a = null ;
					else a = conRs.getObject(i).toString();
					me1.invoke(o,rMeta.getColumnName(i),a);
				   
					i++;
				}
				
				
				vResult.add(o);
			}
			conRs.close();
			conPst.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vResult;
	}

	/**
	 * @Method
	 * typeof
	 * @Return
	 * String
	 * @Date
	 * 2016. 4. 27.오전 10:03:42
	 * @Writter
	 * ntels_shlee
	 * @EditHistory
	 *
	 * @Discript
	 */
	private String typeof(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Object> exeSelSQLM(Connection conn,String sql ) throws IOException
	{
	
		List<Object> vResult = new ArrayList<Object>();
		PreparedStatement conPst;
	    ResultSet         conRs = null;
		int i = 0;

		Map<String, Object> sqlMap;
		
		
		vResult.clear();
		try {
			conPst = conn.prepareStatement(sql);
			conRs  = conPst.executeQuery(sql);
			ResultSetMetaData rMeta = conRs.getMetaData();
		
			while (conRs.next() == true) {
			    sqlMap = new HashMap<String, Object>();
				i = 1;
				while (i <= rMeta.getColumnCount()) {			
					
					sqlMap.put(rMeta.getColumnName(i), conRs.getObject(i));
					i++;
				}
				vResult.add(sqlMap);
			}
			conRs.close();
			conPst.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vResult;
	}
	
	public static int exeSQL(Connection conn, String sql, Vector<String> vOutCol, List<Object>  list) throws IOException, Exception, Exception
	{
	
		
		PreparedStatement conPst;
	    int i = 0;
        int nRet = 0;
        int l_size = 0;
        int lcnt = 0;
     	Class tt;
		Object obj;
		
		try {
			conPst = conn.prepareStatement(sql);
		
		
			 l_size = list.size();
		       
			 while ( lcnt < l_size )
			 {
		  	     obj = list.get(lcnt);
				 tt = obj.getClass();
				 if ( vOutCol.size() >= 1  )
				 { 
					 for(i=0; i<vOutCol.size(); i++ ) 
					 {	
					
						 Method me1 = tt.getMethod("getValue", String.class);
						 if(me1.invoke(obj,vOutCol.get(i)) instanceof Long)	 conPst.setLong(i+1, (Long)me1.invoke(obj,vOutCol.get(i)));
						 else if (me1.invoke(obj,vOutCol.get(i)) instanceof String )  conPst.setString(i+1, (String)me1.invoke(obj,vOutCol.get(i)));
						 else if (me1.invoke(obj,vOutCol.get(i)) instanceof Integer ) conPst.setInt(i+1, (Integer)me1.invoke(obj,vOutCol.get(i)));
						 else if (me1.invoke(obj,vOutCol.get(i)) instanceof Double ) conPst.setDouble(i+1, (Double)me1.invoke(obj,vOutCol.get(i)));
						 else if (me1.invoke(obj,vOutCol.get(i)) instanceof Float ) conPst.setFloat(i+1, (Float)me1.invoke(obj,vOutCol.get(i)));
						 else if (me1.invoke(obj,vOutCol.get(i)) instanceof java.sql.Timestamp ) conPst.setTimestamp(i+1, (java.sql.Timestamp)me1.invoke(obj,vOutCol.get(i)));
					 }	
					 conPst.addBatch();
				 }
			     lcnt ++;
			     
			}
		    conPst.executeBatch();
			conPst.close();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return nRet;
	}
	
	
	public static int exeSQL(Connection conn, String sql, Vector<String> vOutCol, Object obj) throws IOException, Exception, Exception
	{
	
		
		PreparedStatement conPst;
	    int i = 0;
        int nRet = 0;
        
    	Class tt;
		tt = obj.getClass();
		
		
		try {
			conPst = conn.prepareStatement(sql);
			if ( vOutCol.size() >= 1  )
			{ 
				
				
				for(i=0; i<vOutCol.size(); i++ ) 
				{	
					
			       Method me1 = tt.getMethod("getValue", String.class);
			       
			     //   System.out.println("vOutCol : " + vOutCol.get(i) + ":" + me1.invoke(obj,vOutCol.get(i)).toString());
			       
			       if(me1.invoke(obj,vOutCol.get(i)) instanceof Long)	 conPst.setLong(i+1, (Long)me1.invoke(obj,vOutCol.get(i)));
				   else if (me1.invoke(obj,vOutCol.get(i)) instanceof String )  conPst.setString(i+1, (String)me1.invoke(obj,vOutCol.get(i)));
				   else if (me1.invoke(obj,vOutCol.get(i)) instanceof Integer ) conPst.setInt(i+1, (Integer)me1.invoke(obj,vOutCol.get(i)));
				   else if (me1.invoke(obj,vOutCol.get(i)) instanceof Double ) conPst.setDouble(i+1, (Double)me1.invoke(obj,vOutCol.get(i)));
				   else if (me1.invoke(obj,vOutCol.get(i)) instanceof Float ) conPst.setFloat(i+1, (Float)me1.invoke(obj,vOutCol.get(i)));
				   else if (me1.invoke(obj,vOutCol.get(i)) instanceof java.sql.Timestamp ) conPst.setTimestamp(i+1, (java.sql.Timestamp)me1.invoke(obj,vOutCol.get(i)));
				}	
			}
			nRet    = conPst.executeUpdate();
			conPst.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nRet;
	}

	public static int exeSQL(Connection conn, String sql) throws IOException
	{
	
		
		PreparedStatement conPst;
	    int nRet = 0;
		 
		try {
			conPst = conn.prepareStatement(sql);
			nRet    = conPst.executeUpdate();
			conPst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nRet;
	}

}
