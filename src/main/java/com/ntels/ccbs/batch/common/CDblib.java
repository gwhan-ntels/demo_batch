package com.ntels.ccbs.batch.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author ntels_shlee
 *
 */
public class CDblib {

	private static String path = null;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/bss";
	private static String user = "ccbs";
	private static String password = "ccbs2016!01";
	private Connection conn;
	private static Statement conSt;
	private static ResultSet conRs;
	private static PreparedStatement conPst;
	
	
	public String getUser() {
		return user;
	}

	public String getPwd() {
		return password;
	}

	public boolean connect(String id, String pw) throws ClassNotFoundException {
		boolean bRet = true;
		conn = null;

		Class.forName(driver);
		try {
			conn = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return bRet;
	}

	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			conRs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Declare Cursor
	public void exeOpen(String sql) throws IOException {

		//System.out.println(";;;;" + sql);
		try {
			conPst = conn.prepareStatement(sql);
			//conPst.setString
			conRs = conPst.executeQuery(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Declare Cursor
	public void exeOpen(String sql, Vector<String> col, Map<String,String> col_v) throws IOException {

		int i=0;
		try {
			conPst = conn.prepareStatement(sql);
			//System.out.println("exeOpen : " + col.size() );
			if ( col.size() >= 1  )
			{ 
				for(i=0; i<col.size(); i++ ) conPst.setString(i+1, col_v.get(col.get(i)));
			}
			
			conRs = conPst.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public int exeSQL(List<Object> lVal, String sql, Vector vColum) throws IOException
	{

		int nRet = 0, i=0, nRCnt = 0, j=0;
		Vector<Vector<Object>> vIn = new Vector<Vector<Object>>();
		
		
		
	        if ( lVal.size() < 1 )
	        {	nRet = -1;
	            return nRet;
	        }
	        
	      	nRet = lVal.size();
	        i = 1;
	        while (i < nRet)
	        {
	        	Map<String, Object> sqlMapCol = (Map<String, Object>) lVal.get(i);
	            Vector<Object> vTmp = new Vector<Object>();
	            
	            for( j=0; j < vColum.size() ; j++ ) 
	            {  
	            	//System.out.println("COL : " + vColum.get(j));
	            	vTmp.add(sqlMapCol.get(vColum.get(j)));
	            }
	            
	         //   System.out.println(vTmp.toString());
	            vIn.add(vTmp);
	            i++;
	        }
		     
	        nRet = exeBatch(sql,  vIn);
	        if ( nRet < 0 )	nRet = -1;
		 	return nRet;
	}

	
	public Vector<Vector<String>> exeFetchV(int nMaxFCnt) throws IOException {
		Vector<Vector<String>> vResult = new Vector<Vector<String>>();

		int i = 0, j = 0;

		vResult.clear();
		try {

			conRs.setFetchSize(nMaxFCnt);
			i = 0;

			ResultSetMetaData rMeta = conRs.getMetaData();
			while (i < nMaxFCnt) {
				if (conRs.next() == true) {
					j = 1;
					Vector<String> vItem = new Vector<String>();
					while (j <= rMeta.getColumnCount()) {
						vItem.add(conRs.getString(j));
						j++;
					}
					vResult.add(vItem);

				}
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vResult;
	}

	public Vector<Vector<Object>> exeSelSQLV(String sql) throws IOException {
		Vector<Vector<Object>> vResult = new Vector<Vector<Object>>();

		int i = 0;

		vResult.clear();
		try {
			conPst = conn.prepareStatement(sql);
			conRs = conPst.executeQuery(sql);
			ResultSetMetaData rMeta = conRs.getMetaData();

			while (conRs.next() == true) {

				Vector<Object> vItem = new Vector<Object>();
				i = 1;

				while (i <= rMeta.getColumnCount()) {
					vItem.add(conRs.getString(i));
					i++;
				}
				vResult.add((Vector<Object>)vItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vResult;
	}

	public List<Object> exeFetchM(int nMaxFCnt) throws IOException {
		List<Object> vResult = new ArrayList<Object>();
		Map sqlCol = new HashMap();
		int i = 0, j = 0;

		vResult.clear();
		try {
			conRs.setFetchSize(nMaxFCnt);
			i = 0;

			ResultSetMetaData rMeta = conRs.getMetaData();
			i = 1;
			while (i <= rMeta.getColumnCount()) {
				sqlCol.put(i, rMeta.getColumnName(i));
				i++;
			}
			vResult.add(sqlCol);

			i = 0;
			while (i < nMaxFCnt) {
				if (conRs.next() == true) {
					j = 1;
					Map sqlMap = new HashMap();
					while (j <= rMeta.getColumnCount()) {
						// System.out.println("Key : " + rMeta.getColumnName(j)
						// + " Value : " + conRs.getObject(j) );
						sqlMap.put(rMeta.getColumnName(j), conRs.getObject(j));
						j++;
					}
					vResult.add(sqlMap);
				}
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vResult;
	}


	public List<Object> exeSelSQLM(String sql) throws IOException {
		List<Object> vResult = new ArrayList<Object>();
		Map sqlCol = new HashMap();
		Map sqlMap = new HashMap();

		int i = 0;

		vResult.clear();
		try {
			conPst = conn.prepareStatement(sql);
			conRs = conPst.executeQuery(sql);
			ResultSetMetaData rMeta = conRs.getMetaData();
			i = 1;

			while (i <= rMeta.getColumnCount()) {
				sqlCol.put(i, rMeta.getColumnName(i));
				i++;
			}
			vResult.add(sqlCol);

			while (conRs.next() == true) {
			    sqlMap = new HashMap();
				
				i = 1;
				while (i <= rMeta.getColumnCount()) {				
					// System.out.println("Key : " + rMeta.getColumnName(i) + "
					// Value : " + (String)conRs.getString(i) );
					sqlMap.put(rMeta.getColumnName(i), conRs.getObject(i));
					
					
					i++;
				}
				vResult.add(sqlMap);
			}
			//sqlMap.clear();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vResult;
	}

	public int exeBatch(String sql, Vector<Vector<Object>> v) throws IOException {

		int nRet = 0;
		int nCnt = 0;
		int i = 0, j = 0;
		int nAddCnt = 0;
		Vector<Object> subV;

		try {

			conPst.clearParameters();
			conPst = conn.prepareStatement(sql);
			i = 0;
			nCnt = 0;
			while (i < v.size()) {

				subV = v.get(i);
				j = 0;
				while (j < subV.size()) {
					//System.out.println("dkdkdkdkd : " + j + ":" +subV.toString() );
					conPst.setString(j+1, subV.get(j).toString());
					j++;
				}
				conPst.addBatch();
				i++;
				nRet = nRet + 1;
			}
			conPst.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return -1;
		}
		return nRet;
	}

	public int exeSQL(String sql) throws IOException {
		int nRet = 0;
		// PreparedStatement conStmt = null;

		try {
			conPst = conn.prepareStatement(sql);
			nRet = conPst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return -1;
		}
		return nRet;
	}

	public void display(Vector<Vector<Object>> v) {
		int i = 0, j = 0;
		Vector<Object> subV;
		i = 0;
		while (i < v.size()) {
			subV = v.get(i);
			j = 0;
			StringBuffer str = new StringBuffer();
			while (j < subV.size()) {
				str.append((String) subV.get(j));
				j++;
				if (j < subV.size())
					str.append(":");

			}
			i++;
			System.out.println("[" + i + "]" + str.toString());

		}
	}

	public void displayV(Vector<Object> v) {
		int i = 0, j = 0;

		i = 0;
		StringBuffer str = new StringBuffer();
		while (i < v.size()) {
			str.append((String) v.get(i));
			i++;
			if (i < v.size())
				str.append(":");
		}
		System.out.println("[-]" + str.toString());

	}

	public void displayMap(List<Object> v) {
		int i = 0, j = 0;

		i = 0;

		if (v.size() <= 0)
			return;

		Map<String, Object> sqlMapCol = (Map<String, Object>) v.get(0);
		i = 1;
		while (i < v.size()) {
			Map<String, Object> sqlMap = (Map<String, Object>) v.get(i);
			j = 0;
			StringBuffer str = new StringBuffer();
			j = 1;
			while (j <= sqlMap.size()) {
				str.append(" ");
				str.append(sqlMapCol.get(j).toString());
				str.append(" : ");
				str.append(sqlMap.get(sqlMapCol.get(j)).toString());

				j++;

			}
			i++;
			System.out.println("[" + (i - 1) + "]" + str.toString());

		}
	}

}
