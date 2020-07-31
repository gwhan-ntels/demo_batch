package com.ntels.ccbs.batch.util;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 
* framework의 transaction과는 별도로
* 개별 connection 이 필요한 경우 이용
*
* <PRE>
* 1. ClassName: DBUtil
* 2. FileName : DBUtil.java
* 3. Package : com.skt.ssp.nisf.util
* 4. 작성자 : Administrator
* 5. 작성일 : 2015. 6. 2. 오후 3:45:37
* 6. 변경이력
* 이름 : 일자 : 변경내용
* ————————————————————————————————————————
* Administrator : 2015. 6. 2. : 신규 개발.
* </PRE>
 */
public class QueryUtil {
	
	/** logger. */
	static Logger logger = LoggerFactory.getLogger(QueryUtil.class);

	
	/** sql 저장소. */
	final static HashMap<String, String> sqls = new HashMap<String, String>();
	final static HashMap<Integer, String> inSqls = new HashMap<Integer, String>();
	final static HashMap<Integer, String> getSqls = new HashMap<Integer, String>();
	final static HashMap<Integer, String> setSqls = new HashMap<Integer, String>();
	final static HashMap<String, String> typSqls = new HashMap<String, String>();
	
	/**
	 * 
	* TODO ..
	*
	* @param path 'com/xxx/xxx'
	* @param mapperName xxxxMapper
	* @param type insert ? update ? sql ?
	* @param id attribute id
	* @return String
	 */
	public static String getSqlFromMybatisMapper(String path, String mapperName, String type, String id){
		
		String key = mapperName+"."+id;
		String mpath = path.endsWith("/") ? path : path+"/";
		
		if(sqls.containsKey(key));
		else{
			InputStream is = null;
			try {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+mapperName+".xml");
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	
				doc.getDocumentElement().normalize();
				
				NodeList nodeLst = doc.getElementsByTagName(type);
				for (int s = 0; s < nodeLst.getLength(); s++) {
					Node node = nodeLst.item(s);
					Element fstElmnt = (Element) node;
					if(fstElmnt.getAttribute("id").equals(id)){
						sqls.put(key, fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?"));
						break;
					}
				}
			}catch(Exception e){
				logger.error("{}", e);
			}finally{
				if(is != null){
					try{
						is.close();
						is = null;
					}catch(Exception e){
						logger.error("{}", e);
					}
				}
			}
		}
		
		String sql = sqls.get(key);
		
		if(sql == null)
			logger.error("{}.{} {} SQL 정보가 없습니다.",path, mapperName, id);
		
		return sql == null ? "" : sql.trim();
	}

	public static String getSqlFromMybatisMapper(String dbType, String path, String mapperName, String type, String id){
		
		String key = mapperName+"."+id;
		String mpath = path.endsWith("/") ? path : path+"/";
		
		if(sqls.containsKey(key));
		else{
			InputStream is = null;
			try {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+dbType+"/"+mapperName+".xml");
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	
				doc.getDocumentElement().normalize();
				
				NodeList nodeLst = doc.getElementsByTagName(type);
				for (int s = 0; s < nodeLst.getLength(); s++) {
					Node node = nodeLst.item(s);
					Element fstElmnt = (Element) node;
					if(fstElmnt.getAttribute("id").equals(id)){
						sqls.put(key, fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?"));
						break;
					}
				}
			}catch(Exception e){
				logger.error("{}", e);
			}finally{
				if(is != null){
					try{
						is.close();
						is = null;
					}catch(Exception e){
						logger.error("{}", e);
					}
				}
			}
		}
		
		String sql = sqls.get(key);
		
		if(sql == null)
			logger.error("{}.{} {} SQL 정보가 없습니다.",path, mapperName, id);
		
		return sql == null ? "" : sql.trim();
	}

	
	public static <T> PreparedStatement getSqlFromMybatisMapper3(String path, String mapperName, String type, String id, Class<T> c, Object obj, PreparedStatement ps) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String tmpSql = null;
		int i = 0;
		Field[] fields = c.getDeclaredFields();
		PreparedStatement pre = ps;
		Method m = null;
		Method m2 = null;

		String mpath = path.endsWith("/") ? path : path+"/";
		
		InputStream is = null;
			try {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+mapperName+".xml");
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	
				doc.getDocumentElement().normalize();
				
				NodeList nodeLst = doc.getElementsByTagName(type);
				for (int s = 0; s < nodeLst.getLength(); s++) {
					Node node = nodeLst.item(s);
					Element fstElmnt = (Element) node;
					if(fstElmnt.getAttribute("id").equals(id)){
						tmpSql = fstElmnt.getFirstChild().getNodeValue().trim();
					}
				}
			}catch(Exception e){
				logger.error("{}", e);
			}finally{
				while(tmpSql.contains("#")){
					i ++;
				
					String methodName = tmpSql.substring(tmpSql.indexOf("{")+1,tmpSql.indexOf("}"));
					for(Field f : Arrays.asList(fields)){
						if(f.getName().equals(methodName)){
							if(Character.isUpperCase(methodName.charAt(1))){
								methodName = "get"+methodName.substring(0);
							}else{
								methodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
							}
						Class<?> parameterClass = f.getType();
						 m = ReflectionUtils.findMethod(c, methodName);
						if(isString(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setString", Integer.TYPE, new String().getClass());
						}else if(isLong(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setLong", Integer.TYPE, Long.TYPE);
						}else if(isInteger(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setInt", Integer.TYPE, Integer.TYPE);
						}else if(isFloat(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setFloat", Integer.TYPE, Float.TYPE);
						}else if(isDouble(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setDouble", Integer.TYPE, Double.TYPE);
						}else if(isDate(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setDate", Integer.TYPE, new Date(0).getClass());
						}else if(isTimestamp(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setTimestamp", Integer.TYPE, new Timestamp(0).getClass());
						}else{
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setString", Integer.TYPE, new String().getClass());
						}
						tmpSql = tmpSql.replaceFirst("#\\{.*?\\}", "?");
						break;
						}
					}
//					m2.invoke(pre, i, m.invoke(obj));	
					ReflectionUtils.invokeMethod(m2, pre, i, ReflectionUtils.invokeMethod(m,obj));
					if(tmpSql.contains("#")){
						continue;
					}else{
						break;
					}
				} 

				if(is != null){
					try{
						is.close();
						is = null;
					}catch(Exception e){
						logger.error("{}", e);
					}
				}
			}
		
		if(pre == null)
			logger.error("{}.{} {} SQL 정보가 없습니다.",path, mapperName, id);
		
		return pre;
	}

	public static <T> PreparedStatement getSqlFromMybatisMapper3(String dbType,String path, String mapperName, String type, String id, Class<T> c, Object obj, PreparedStatement ps) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String tmpSql = null;
		int i = 0;
		Field[] fields = c.getDeclaredFields();
		PreparedStatement pre = ps;
		Method m = null;
		Method m2 = null;

		String mpath = path.endsWith("/") ? path : path+"/";
		
		InputStream is = null;
			try {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+dbType+"/"+mapperName+".xml");
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	
				doc.getDocumentElement().normalize();
				
				NodeList nodeLst = doc.getElementsByTagName(type);
				for (int s = 0; s < nodeLst.getLength(); s++) {
					Node node = nodeLst.item(s);
					Element fstElmnt = (Element) node;
					if(fstElmnt.getAttribute("id").equals(id)){
						tmpSql = fstElmnt.getFirstChild().getNodeValue().trim();
					}
				}
			}catch(Exception e){
				logger.error("{}", e);
			}finally{
				while(tmpSql.contains("#")){
					i ++;
				
					String methodName = tmpSql.substring(tmpSql.indexOf("{")+1,tmpSql.indexOf("}"));
					for(Field f : Arrays.asList(fields)){
						if(f.getName().equals(methodName)){
							if(Character.isUpperCase(methodName.charAt(1))){
								methodName = "get"+methodName.substring(0);
							}else{
								methodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
							}
						Class<?> parameterClass = f.getType();
						 m = ReflectionUtils.findMethod(c, methodName);
						if(isString(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setString", Integer.TYPE, new String().getClass());
						}else if(isLong(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setLong", Integer.TYPE, Long.TYPE);
						}else if(isInteger(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setInt", Integer.TYPE, Integer.TYPE);
						}else if(isFloat(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setFloat", Integer.TYPE, Float.TYPE);
						}else if(isDouble(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setDouble", Integer.TYPE, Double.TYPE);
						}else if(isDate(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setDate", Integer.TYPE, new Date(0).getClass());
						}else if(isTimestamp(parameterClass)){
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setTimestamp", Integer.TYPE, new Timestamp(0).getClass());
						}else{
							m2 = ReflectionUtils.findMethod(PreparedStatement.class, "setString", Integer.TYPE, new String().getClass());
						}
						tmpSql = tmpSql.replaceFirst("#\\{.*?\\}", "?");
						break;
						}
					}
//					m2.invoke(pre, i, m.invoke(obj));	
					ReflectionUtils.invokeMethod(m2, pre, i, ReflectionUtils.invokeMethod(m,obj));
					if(tmpSql.contains("#")){
						continue;
					}else{
						break;
					}
				} 

				if(is != null){
					try{
						is.close();
						is = null;
					}catch(Exception e){
						logger.error("{}", e);
					}
				}
			}
		
		if(pre == null)
			logger.error("{}.{} {} SQL 정보가 없습니다.",path, mapperName, id);
		
		return pre;
	}

	public static <T> List<T> getListFromXML(String path, String mapperName, String type, String id, Class<T> c, Object obj, Connection conn) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
// 맵퍼 패스, 맵퍼명, id, 리턴타입, 파라미터객체, 커넥션 정보
		String trimSql = null;
		String sql = null;
		String mpath = path.endsWith("/") ? path : path+"/";
		int i = 0;
		Method m = null;
		Field[] fields = obj.getClass().getDeclaredFields();
//				c.getDeclaredFields();

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<T> list = new ArrayList<T>();
		inSqls.clear();
		getSqls.clear();
//		setSqls.clear();
		typSqls.clear();
		
		Class<? extends Object> paramc= obj.getClass();
		
		String key = mapperName+"."+id;
		String key2 = "trim"+mapperName+"."+id;

		//기존 쿼리가 존재하면 해당 맵에서 재사용한다. 아니면 XML을 파싱하여 데이터를 얻어온다.
		if(sqls.containsKey(key)){
			sql = sqls.get(key);
			trimSql = sqls.get(key2);
		}
		else{
			InputStream is = null;
			try {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+mapperName+".xml");
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	
				doc.getDocumentElement().normalize();
				
				NodeList nodeLst = doc.getElementsByTagName(type);
				for (int s = 0; s < nodeLst.getLength(); s++) {
					Node node = nodeLst.item(s);
					Element fstElmnt = (Element) node;
					if(fstElmnt.getAttribute("id").equals(id)){
						trimSql = fstElmnt.getFirstChild().getNodeValue().trim(); //순수 trim 쿼리
						sql = fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?").trim(); //치환 쿼리
						sqls.put(key, sql);
						sqls.put(key2, trimSql);
						break;
					}
				}
			}catch(Exception e){
				logger.error("{}", e);
			}finally{
				if(is != null){
					try{
						is.close();
						is = null;
					}catch(Exception e){
						logger.error("{}", e);
					}
				}
			}
		}
		//쿼리중의 가변 데이터를 해시맵에 저장
		while(trimSql.contains("#")){
			i ++;
			String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
			inSqls.put(i, methodName);
			String tmpMethodName = null;
			if(Character.isUpperCase(methodName.charAt(1))){
				tmpMethodName = "get"+methodName.substring(0);
			}else{
				tmpMethodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
			}
			getSqls.put(i, tmpMethodName);
//			tmpMethodName = "set"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
//			setSqls.put(i, tmpMethodName);
			trimSql = trimSql.replaceFirst("#\\{.*?\\}", "?");
		}
		//SQL connection
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//SQL 생성 및 EXECUTION
		try {
		//VO에서 타입가져오기
			if(i>0){
				for(Field f : Arrays.asList(fields)){
					Class<?> parameterClass = f.getType();
					typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
				}
			}
		//VO에서 getter찾기
			while(i>0){
//				System.out.println("getSeql.get() :" + getSqls.get(i).toString());
//				System.out.println("inSeql.get() :" + inSqls.get(i).toString());
//				System.out.println("inSeql.get() :" + typSqls.get(inSqls.get(i)));
//				
				
				m = ReflectionUtils.findMethod(paramc, getSqls.get(i));
				if(typSqls.get(inSqls.get(i)).equals("String")){
					ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
				}else if(typSqls.get(inSqls.get(i)).equals("Long")){
					ps.setLong(i, (long) ReflectionUtils.invokeMethod(m,obj));
				}else if(typSqls.get(inSqls.get(i)).equals("Date")){
					ps.setDate(i, (java.sql.Date) ReflectionUtils.invokeMethod(m,obj));
				}else if(typSqls.get(inSqls.get(i)).equals("Interger")){
					ps.setInt(i, (int) ReflectionUtils.invokeMethod(m,obj));
				}else if(typSqls.get(inSqls.get(i)).equals("Float")){
					ps.setFloat(i, (Float) ReflectionUtils.invokeMethod(m,obj));
				}else if(typSqls.get(inSqls.get(i)).equals("Double")){
					ps.setDouble(i, (double) ReflectionUtils.invokeMethod(m,obj));
				}else if(typSqls.get(inSqls.get(i)).equals("Timestamp")){
					ps.setTimestamp(i, (Timestamp) ReflectionUtils.invokeMethod(m,obj));
				}else{
					ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
				}
				i--;
			}
		
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			//리턴타입으로 ResultSet에 담는다.
			T cc = c.newInstance();
			
			while(rs.next()){
				cc = QueryUtil.getResultSetToObject(rs, c);
        		list.add(cc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  list;
	}
	
	
		

	public static <T> List<T> getListFromXML(String dbType,String path, String mapperName, String type, String id, Class<T> c, Object obj, Connection conn, String vSql) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// 맵퍼 패스, 맵퍼명, id, 리턴타입, 파라미터객체, 커넥션 정보
				String trimSql = null;
				String sql = null;
				String mpath = path.endsWith("/") ? path : path+"/";
				int i = 0;
				Method m = null;
				Field[] fields = obj.getClass().getDeclaredFields();
//						c.getDeclaredFields();

				PreparedStatement ps = null;
				ResultSet rs = null;
				
				List<T> list = new ArrayList<T>();
				inSqls.clear();
				getSqls.clear();
				setSqls.clear();
				typSqls.clear();
				
				Class<? extends Object> paramc= obj.getClass();
				
				String key = mapperName+"."+id;
				String key2 = "trim"+mapperName+"."+id;
				
				
				sqls.clear();
				//기존 쿼리가 존재하면 해당 맵에서 재사용한다. 아니면 XML을 파싱하여 데이터를 얻어온다.
				if(sqls.containsKey(key)){
					sql = sqls.get(key);
					trimSql = sqls.get(key2);
					
				}
				else{
					InputStream is = null;
					try {
						is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+dbType+"/"+mapperName+".xml");
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
						doc.getDocumentElement().normalize();
						
						NodeList nodeLst = doc.getElementsByTagName(type);

						for (int s = 0; s < nodeLst.getLength(); s++) {
							Node node = nodeLst.item(s);
							Element fstElmnt = (Element) node;
							if(fstElmnt.getAttribute("id").equals(id)){
								trimSql = fstElmnt.getFirstChild().getNodeValue().trim(); //순수 trim 쿼리
								sql = fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?").trim(); //치환 쿼리
	
								if ( sql.contains("$COL_NM") ) {									
									sql = sql.replace("$COL_NM", vSql);
								}
								if ( trimSql.contains("$COL_NM") ) trimSql = trimSql.replace("$COL_NM", vSql);
							
								sqls.put(key, sql);
								sqls.put(key2, trimSql);
								break;
							}
						}
					}catch(Exception e){
						logger.error("{}", e);
					}finally{
						if(is != null){
							try{
								is.close();
								is = null;
							}catch(Exception e){
								logger.error("{}", e);
							}
						}
					}
				}
				//쿼리중의 가변 데이터를 해시맵에 저장
				while(trimSql.contains("#")){
					i ++;
					String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
					inSqls.put(i, methodName);
					String tmpMethodName = null;
					if(Character.isUpperCase(methodName.charAt(1))){
						tmpMethodName = "get"+methodName.substring(0);
					}else{
						tmpMethodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
					}
					getSqls.put(i, tmpMethodName);
//					tmpMethodName = "set"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
//					setSqls.put(i, tmpMethodName);
					trimSql = trimSql.replaceFirst("#\\{.*?\\}", "?");
				}
				//SQL connection
				try {
					ps = conn.prepareStatement(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//SQL 생성 및 EXECUTION
				try {
				//VO에서 타입가져오기
					if(i>0){
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
					}
				//VO에서 getter찾기
					while(i>0){
//						System.out.println("getSeql.get() :" + getSqls.get(i).toString());
//						System.out.println("inSeql.get() :" + inSqls.get(i).toString());
//						System.out.println("inSeql.get() :" + typSqls.get(inSqls.get(i)));
//						
						
						m = ReflectionUtils.findMethod(paramc, getSqls.get(i));
						if(typSqls.get(inSqls.get(i)).equals("String")){
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Long")){
							ps.setLong(i, (long) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Date")){
							ps.setDate(i, (java.sql.Date) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Interger")){
							ps.setInt(i, (int) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Float")){
							ps.setFloat(i, (Float) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Double")){
							ps.setDouble(i, (double) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Timestamp")){
							ps.setTimestamp(i, (Timestamp) ReflectionUtils.invokeMethod(m,obj));
						}else{
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}
						i--;
					}
				
					rs = ps.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					//리턴타입으로 ResultSet에 담는다.
					T cc = c.newInstance();
					
					while(rs.next()){
						cc = QueryUtil.getResultSetToObject(rs, c);
		        		list.add(cc);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return  list;
			}


	public static <T> List<T> getListFromXML(String dbType,String path, String mapperName, String type, String id, Class<T> c, Object obj, Connection conn) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// 맵퍼 패스, 맵퍼명, id, 리턴타입, 파라미터객체, 커넥션 정보
				String trimSql = null;
				String sql = null;
				String mpath = path.endsWith("/") ? path : path+"/";
				int i = 0;
				Method m = null;
				Field[] fields = obj.getClass().getDeclaredFields();
//						c.getDeclaredFields();

				PreparedStatement ps = null;
				ResultSet rs = null;
				
				List<T> list = new ArrayList<T>();
				inSqls.clear();
				getSqls.clear();
//				setSqls.clear();
				typSqls.clear();
				
				Class<? extends Object> paramc= obj.getClass();
				
				String key = mapperName+"."+id;
				String key2 = "trim"+mapperName+"."+id;

				//기존 쿼리가 존재하면 해당 맵에서 재사용한다. 아니면 XML을 파싱하여 데이터를 얻어온다.
				if(sqls.containsKey(key)){
					sql = sqls.get(key);
					trimSql = sqls.get(key2);
				}
				else{
					InputStream is = null;
					try {
						is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+dbType+"/"+mapperName+".xml");
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
						doc.getDocumentElement().normalize();
						
						NodeList nodeLst = doc.getElementsByTagName(type);
						for (int s = 0; s < nodeLst.getLength(); s++) {
							Node node = nodeLst.item(s);
							Element fstElmnt = (Element) node;
							if(fstElmnt.getAttribute("id").equals(id)){
								trimSql = fstElmnt.getFirstChild().getNodeValue().trim(); //순수 trim 쿼리
								sql = fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?").trim(); //치환 쿼리
								sqls.put(key, sql);
								sqls.put(key2, trimSql);
								break;
							}
						}
					}catch(Exception e){
						logger.error("{}", e);
					}finally{
						if(is != null){
							try{
								is.close();
								is = null;
							}catch(Exception e){
								logger.error("{}", e);
							}
						}
					}
				}
				//쿼리중의 가변 데이터를 해시맵에 저장
				while(trimSql.contains("#")){
					i ++;
					String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
					inSqls.put(i, methodName);
					String tmpMethodName = null;
					if(Character.isUpperCase(methodName.charAt(1))){
						tmpMethodName = "get"+methodName.substring(0);
					}else{
						tmpMethodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
					}
					getSqls.put(i, tmpMethodName);
//					tmpMethodName = "set"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
//					setSqls.put(i, tmpMethodName);
					trimSql = trimSql.replaceFirst("#\\{.*?\\}", "?");
				}
				//SQL connection
				try {
					ps = conn.prepareStatement(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//SQL 생성 및 EXECUTION
				try {
				//VO에서 타입가져오기
					if(i>0){
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
					}
				//VO에서 getter찾기
					while(i>0){
//						System.out.println("getSeql.get() :" + getSqls.get(i).toString());
//						System.out.println("inSeql.get() :" + inSqls.get(i).toString());
//						System.out.println("inSeql.get() :" + typSqls.get(inSqls.get(i)));
//						
						
						m = ReflectionUtils.findMethod(paramc, getSqls.get(i));
						if(typSqls.get(inSqls.get(i)).equals("String")){
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Long")){
							ps.setLong(i, (long) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Date")){
							ps.setDate(i, (java.sql.Date) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Interger")){
							ps.setInt(i, (int) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Float")){
							ps.setFloat(i, (Float) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Double")){
							ps.setDouble(i, (double) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Timestamp")){
							ps.setTimestamp(i, (Timestamp) ReflectionUtils.invokeMethod(m,obj));
						}else{
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}
						i--;
					}
				
					rs = ps.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					//리턴타입으로 ResultSet에 담는다.
					T cc = c.newInstance();
					
					while(rs.next()){
						cc = QueryUtil.getResultSetToObject(rs, c);
		        		list.add(cc);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return  list;
			}

	public static ResultSet getListFromOpen(String path, String mapperName, String type, String id, Object obj, Connection conn, int MaxSize) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// 맵퍼 패스, 맵퍼명, id, 리턴타입, 파라미터객체, 커넥션 정보
				String trimSql = null;
				String sql = null;
				String mpath = path.endsWith("/") ? path : path+"/";
				int i = 0;
				Method m = null;
				Field[] fields = obj.getClass().getDeclaredFields();

				PreparedStatement ps = null;
				ResultSet rs = null;
				
				inSqls.clear();
				getSqls.clear();
//				setSqls.clear();
				typSqls.clear();
				
				Class<? extends Object> paramc= obj.getClass();
				
				String key = mapperName+"."+id;
				String key2 = "trim"+mapperName+"."+id;

				//기존 쿼리가 존재하면 해당 맵에서 재사용한다. 아니면 XML을 파싱하여 데이터를 얻어온다.
				if(sqls.containsKey(key)){
					sql = sqls.get(key);
					trimSql = sqls.get(key2);
				}
				else{
					InputStream is = null;
					try {
						is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+mapperName+".xml");
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
						doc.getDocumentElement().normalize();
						
						NodeList nodeLst = doc.getElementsByTagName(type);
						for (int s = 0; s < nodeLst.getLength(); s++) {
							Node node = nodeLst.item(s);
							Element fstElmnt = (Element) node;
							if(fstElmnt.getAttribute("id").equals(id)){
								trimSql = fstElmnt.getFirstChild().getNodeValue().trim(); //순수 trim 쿼리
								sql = fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?").trim(); //치환 쿼리
								sqls.put(key, sql);
								sqls.put(key2, trimSql);
								break;
							}
						}
					}catch(Exception e){
						logger.error("{}", e);
					}finally{
						if(is != null){
							try{
								is.close();
								is = null;
							}catch(Exception e){
								logger.error("{}", e);
							}
						}
					}
				}
				
				// sql이 없으면 에러
				if (trimSql == null) {
					throw new IllegalArgumentException("SQL을 찾을 수 없습니다. path : " + path + ", mapper : " + mapperName + ", type : " + type + ", id : " + id);
				}
				
				//쿼리중의 가변 데이터를 해시맵에 저장
				while(trimSql.contains("#")){
					i ++;
					String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
					inSqls.put(i, methodName);
					String tmpMethodName = null;
					if(Character.isUpperCase(methodName.charAt(1))){
						tmpMethodName = "get"+methodName.substring(0);
					}else{
						tmpMethodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
					}
					getSqls.put(i, tmpMethodName);
//					tmpMethodName = "set"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
//					setSqls.put(i, tmpMethodName);
					trimSql = trimSql.replaceFirst("#\\{.*?\\}", "?");
				}
				//SQL connection
				try {
					ps = conn.prepareStatement(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//SQL 생성 및 EXECUTION
				try {
				//VO에서 타입가져오기
					if(i>0){
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
						
						// 부모 클래스에서 타입 가져오기
						Class<?> superClass = obj.getClass().getSuperclass();
						fields = superClass.getDeclaredFields();
						
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
					}
				//VO에서 getter찾기
					while(i>0){
//						System.out.println("getSeql.get() :" + getSqls.get(i).toString());
//						System.out.println("inSeql.get() :" + inSqls.get(i).toString());
//						System.out.println("inSeql.get() :" + typSqls.get(inSqls.get(i)));
//						
						m = ReflectionUtils.findMethod(paramc, getSqls.get(i));
						if(typSqls.get(inSqls.get(i)).equals("String")){
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Long")){
							ps.setLong(i, (long) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Date")){
							ps.setDate(i, (java.sql.Date) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Integer")){
							ps.setInt(i, (int) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Float")){
							ps.setFloat(i, (Float) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Double")){
							ps.setDouble(i, (double) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Timestamp")){
							ps.setTimestamp(i, (Timestamp) ReflectionUtils.invokeMethod(m,obj));
						}else{
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}
						i--;
					}
				
					rs = ps.executeQuery();
					rs.setFetchSize(MaxSize);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return  rs;
	}
	public static ResultSet getListFromOpen(String dbType, String path, String mapperName, String type, String id, Object obj, Connection conn, int MaxSize) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// 맵퍼 패스, 맵퍼명, id, 리턴타입, 파라미터객체, 커넥션 정보
				String trimSql = null;
				String sql = null;
				String mpath = path.endsWith("/") ? path : path+"/";
				int i = 0;
				Method m = null;
				Field[] fields = obj.getClass().getDeclaredFields();

				PreparedStatement ps = null;
				ResultSet rs = null;
				
				inSqls.clear();
				getSqls.clear();
//				setSqls.clear();
				typSqls.clear();
				
				Class<? extends Object> paramc= obj.getClass();
				
				String key = mapperName+"."+id;
				String key2 = "trim"+mapperName+"."+id;

				//기존 쿼리가 존재하면 해당 맵에서 재사용한다. 아니면 XML을 파싱하여 데이터를 얻어온다.
				if(sqls.containsKey(key)){
					sql = sqls.get(key);
					trimSql = sqls.get(key2);
				}
				else{
					InputStream is = null;
					try {
						is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+dbType+"/"+mapperName+".xml");
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
						doc.getDocumentElement().normalize();
						
						NodeList nodeLst = doc.getElementsByTagName(type);
						for (int s = 0; s < nodeLst.getLength(); s++) {
							Node node = nodeLst.item(s);
							Element fstElmnt = (Element) node;
							if(fstElmnt.getAttribute("id").equals(id)){
								trimSql = fstElmnt.getFirstChild().getNodeValue().trim(); //순수 trim 쿼리
								sql = fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?").trim(); //치환 쿼리
								sqls.put(key, sql);
								sqls.put(key2, trimSql);
								break;
							}
						}
					}catch(Exception e){
						logger.error("{}", e);
					}finally{
						if(is != null){
							try{
								is.close();
								is = null;
							}catch(Exception e){
								logger.error("{}", e);
							}
						}
					}
				}
				
				//쿼리중의 가변 데이터를 해시맵에 저장

			
				while(trimSql.contains("#")){
					i ++;
					String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
					inSqls.put(i, methodName);
					String tmpMethodName = null;
					if(Character.isUpperCase(methodName.charAt(1))){
						tmpMethodName = "get"+methodName.substring(0);
					}else{
						tmpMethodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
					}
					getSqls.put(i, tmpMethodName);
//					tmpMethodName = "set"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
//					setSqls.put(i, tmpMethodName);
					trimSql = trimSql.replaceFirst("#\\{.*?\\}", "?");
				}
					//SQL connection
				try {
					ps = conn.prepareStatement(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//SQL 생성 및 EXECUTION
				try {
				//VO에서 타입가져오기
					if(i>0){
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
						
						// 부모 클래스에서 타입 가져오기
						Class<?> superClass = obj.getClass().getSuperclass();
						fields = superClass.getDeclaredFields();
						
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
					}
				//VO에서 getter찾기
					while(i>0){
						System.out.println("getSeql.get() :" + getSqls.get(i).toString());
						System.out.println("inSeql.get() :" + inSqls.get(i).toString());
						System.out.println("inSeql.get() :" + typSqls.get(inSqls.get(i)));
//						
						m = ReflectionUtils.findMethod(paramc, getSqls.get(i));
						if(typSqls.get(inSqls.get(i)).equals("String")){
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Long")){
							ps.setLong(i, (long) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Date")){
							ps.setDate(i, (java.sql.Date) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Integer")){
							ps.setInt(i, (int) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Float")){
							ps.setFloat(i, (Float) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Double")){
							ps.setDouble(i, (double) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Timestamp")){
							ps.setTimestamp(i, (Timestamp) ReflectionUtils.invokeMethod(m,obj));
						}else{
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}		
						i--;
					}
				
					rs = ps.executeQuery();
					rs.setFetchSize(MaxSize);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return  rs;
	}

	
	
	public static ResultSet getListFromOpen(String dbType, String path, String mapperName, String type, String id, Object obj, Connection conn, int MaxSize, String vsql) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// 맵퍼 패스, 맵퍼명, id, 리턴타입, 파라미터객체, 커넥션 정보, 동적  변수

				String trimSql = null;
				String sql = null;
				String mpath = path.endsWith("/") ? path : path+"/";
				int i = 0;
				Method m = null;
				Field[] fields = obj.getClass().getDeclaredFields();

				PreparedStatement ps = null;
				ResultSet rs = null;
				
				inSqls.clear();
				getSqls.clear();
//				setSqls.clear();
				typSqls.clear();
				
				Class<? extends Object> paramc= obj.getClass();
				
				String key = mapperName+"."+id;
				String key2 = "trim"+mapperName+"."+id;

				ResultSetMetaData meta = null;
				int colCnt = 0;
				String[] colNm ;
				int rowCnt = 0;
				String[][] resObj ;
				HashMap colMap = new HashMap();
				
				//기존 쿼리가 존재하면 해당 맵에서 재사용한다. 아니면 XML을 파싱하여 데이터를 얻어온다.
				if(sqls.containsKey(key)){
					sql = sqls.get(key);
					trimSql = sqls.get(key2);
				}
				else{
					InputStream is = null;
					try {
						is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+dbType+"/"+mapperName+".xml");
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
						doc.getDocumentElement().normalize();
						
						NodeList nodeLst = doc.getElementsByTagName(type);
						for (int s = 0; s < nodeLst.getLength(); s++) {
							Node node = nodeLst.item(s);
							Element fstElmnt = (Element) node;
							if(fstElmnt.getAttribute("id").equals(id)){
							
								
								
								
								trimSql = fstElmnt.getFirstChild().getNodeValue().trim(); //순수 trim 쿼리
								sql = fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?").trim(); //치환 쿼리
								
								if ( sql.contains("$COL_NM") ) {									
									sql = sql.replace("$COL_NM", vsql);
								}
								if ( trimSql.contains("$COL_NM") ) trimSql = trimSql.replace("$COL_NM", vsql);
								
								sqls.put(key, sql);
								sqls.put(key2, trimSql);
								break;
							}
						}
					}catch(Exception e){
						logger.error("{}", e);
					}finally{
						if(is != null){
							try{
								is.close();
								is = null;
							}catch(Exception e){
								logger.error("{}", e);
							}
						}
					}
				}
				
				// sql이 없으면 에러
				if (trimSql == null) {
					throw new IllegalArgumentException("SQL을 찾을 수 없습니다. path : " + path + ", mapper : " + mapperName + ", type : " + type + ", id : " + id);
				}

				while(trimSql.contains("$")){
					i ++;
					String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
					trimSql = trimSql.replaceFirst("$", vsql);
				}
				
				//쿼리중의 가변 데이터를 해시맵에 저장

			
				while(trimSql.contains("#")){
					i ++;
					String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
					inSqls.put(i, methodName);
					String tmpMethodName = null;
					if(Character.isUpperCase(methodName.charAt(1))){
						tmpMethodName = "get"+methodName.substring(0);
					}else{
						tmpMethodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
					}
					getSqls.put(i, tmpMethodName);
//					tmpMethodName = "set"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
//					setSqls.put(i, tmpMethodName);
					trimSql = trimSql.replaceFirst("#\\{.*?\\}", "?");
				}
				//SQL connection
				try {
					ps = conn.prepareStatement(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//SQL 생성 및 EXECUTION
				try {
				//VO에서 타입가져오기
					if(i>0){
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
						
						// 부모 클래스에서 타입 가져오기
						Class<?> superClass = obj.getClass().getSuperclass();
						fields = superClass.getDeclaredFields();
						
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
					}
				//VO에서 getter찾기
					while(i>0){
						System.out.println("getSeql.get() :" + getSqls.get(i).toString());
						System.out.println("inSeql.get() :" + inSqls.get(i).toString());
						System.out.println("inSeql.get() :" + typSqls.get(inSqls.get(i)));
//						
						
						
						m = ReflectionUtils.findMethod(paramc, getSqls.get(i));
						
						//System.out.println("findMethod==================================>"+m);
						//System.out.println("invokeMethod==================================>"+ ReflectionUtils.invokeMethod(m,obj));
						
						if(typSqls.get(inSqls.get(i)).equals("String")){
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Long")){
							ps.setLong(i, (long) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Date")){
							ps.setDate(i, (java.sql.Date) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Integer")){
							ps.setInt(i, (int) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Float")){
							ps.setFloat(i, (Float) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Double")){
							ps.setDouble(i, (double) ReflectionUtils.invokeMethod(m,obj));
						}else if(typSqls.get(inSqls.get(i)).equals("Timestamp")){
							ps.setTimestamp(i, (Timestamp) ReflectionUtils.invokeMethod(m,obj));
						}else{
							ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
						}
						i--;
					}
				
					rs = ps.executeQuery();
					rs.setFetchSize(MaxSize);
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
				
			
				
				return  rs;
	}	
	
	
	public static <T> List<T> getListFromXML(ResultSet rs, Class<T> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception{
		// 맵퍼 패스, 맵퍼명, id, 리턴타입, 파라미터객체, 커넥션 정보

				List<T> list = new ArrayList<T>();
				int i = 0;
				
				try {
					//리턴타입으로 ResultSet에 담는다.
					T cc = c.newInstance();
				    while ( i < rs.getFetchSize() )	
					{
				    	if ( rs.next() == true )
				    	{
					    	cc = QueryUtil.getResultSetToObject(rs, c);
		        		    list.add(cc);
				    	    i ++;
				  //  	    System.out.println(i);
				    	} else {
				    	    System.out.println("Checking");
				    		i = rs.getFetchSize();
				    		
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return  list;
	}

	
	public static <T>int updListFromXML(String path, String mapperName, String type, String id, Class<T> c, List<Object> list, Connection conn) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// 맵퍼 패스, 맵퍼명, id, 리턴타입, 리스트객체, 커넥션 정보
				String trimSql = null;
				String sql = null;
				String mpath = path.endsWith("/") ? path : path+"/";
				int i = 0;
				int j = 0;
				int maxI = 0;
				Method m = null;
				Field[] fields = c.getDeclaredFields();
				Object obj = null;

				PreparedStatement ps = null;
				
				inSqls.clear();
				getSqls.clear();
				typSqls.clear();
				setSqls.clear();
				
				String key = mapperName+"."+id;
				String key2 = "trim"+mapperName+"."+id;

				//기존 쿼리가 존재하면 해당 맵에서 재사용한다. 아니면 XML을 파싱하여 데이터를 얻어온다.
				if(sqls.containsKey(key)){
					sql = sqls.get(key);
					trimSql = sqls.get(key2);
				}
				else{
					InputStream is = null;
					try {
						is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+mapperName+".xml");
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
						doc.getDocumentElement().normalize();
						
						NodeList nodeLst = doc.getElementsByTagName(type);
						for (int s = 0; s < nodeLst.getLength(); s++) {
							Node node = nodeLst.item(s);
							Element fstElmnt = (Element) node;
							if(fstElmnt.getAttribute("id").equals(id)){
								trimSql = fstElmnt.getFirstChild().getNodeValue().trim(); //순수 trim 쿼리
								sqls.put(key2, trimSql);
								sql = fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?").trim(); //치환 쿼리
								sqls.put(key, sql);
								break;
							}
						}
					}catch(Exception e){
						logger.error("{}", e);
					}finally{
						if(is != null){
							try{
								is.close();
								is = null;
							}catch(Exception e){
								logger.error("{}", e);
							}
						}
					}
				}
					//쿼리중의 가변 데이터를 해시맵에 저장
					while(trimSql.contains("#")){
						i ++;
						String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
						inSqls.put(i, methodName);
						String tmpMethodName = null;
						if(Character.isUpperCase(methodName.charAt(1))){
							tmpMethodName = "get"+methodName.substring(0);
						}else{
							tmpMethodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
						}
						getSqls.put(i, tmpMethodName);
						if(Character.isUpperCase(methodName.charAt(1))){
							tmpMethodName = "set"+methodName.substring(0);
						}else{
							tmpMethodName = "set"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
						}
						setSqls.put(i, tmpMethodName);
						trimSql = trimSql.replaceFirst("#\\{.*?\\}", "?");
					}
					maxI = i;
					//SQL connection
					try {
						ps = conn.prepareStatement(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					//VO에서 타입가져오기
					if(i>0){
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
					}
					//SQL 생성 및 EXECUTION
					try {
						for( j=0 ; j<list.size() ; j++){
							obj = list.get(j);
							//VO에서 getter찾기
							for(i=1; i <= maxI ; i++){
//								System.out.println("getSeql.get() :" + getSqls.get(i).toString());
//								System.out.println("inSeql.get() :" + inSqls.get(i).toString());
//								System.out.println("inSeql.get() :" + typSqls.get(inSqls.get(i)));
								
								m = ReflectionUtils.findMethod(c, getSqls.get(i));

								Object value = ReflectionUtils.invokeMethod(m,obj);
								
								if (value == null) {
									ps.setString(i, null);
									continue;
								}
								
								if(typSqls.get(inSqls.get(i)).equals("String")){
									ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Long")){
									ps.setLong(i, (long) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Date")){
									ps.setDate(i, (Date) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Integer")){
									ps.setInt(i, (int) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Float")){
									ps.setFloat(i, (Float) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Double")){
									ps.setDouble(i, (double) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Timestamp")){
									ps.setTimestamp(i, (Timestamp) ReflectionUtils.invokeMethod(m,obj));
								}else{
									ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
								}
							}						
							ps.addBatch();
						}
						ps.executeBatch();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return j;
	}

	public static <T>int updListFromXML(String dbType, String path, String mapperName, String type, String id, Class<T> c, List<Object> list, Connection conn) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// 맵퍼 패스, 맵퍼명, id, 리턴타입, 리스트객체, 커넥션 정보
				String trimSql = null;
				String sql = null;
				String mpath = path.endsWith("/") ? path : path+"/";
				int i = 0;
				int j = 0;
				int maxI = 0;
				Method m = null;
				Field[] fields = c.getDeclaredFields();
				Object obj = null;

				PreparedStatement ps = null;
				
				inSqls.clear();
				getSqls.clear();
				typSqls.clear();
				setSqls.clear();
				
				String key = mapperName+"."+id;
				String key2 = "trim"+mapperName+"."+id;

				//기존 쿼리가 존재하면 해당 맵에서 재사용한다. 아니면 XML을 파싱하여 데이터를 얻어온다.
				if(sqls.containsKey(key)){
					sql = sqls.get(key);
					trimSql = sqls.get(key2);
				}
				else{
					InputStream is = null;
					try {
						is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+dbType+"/"+mapperName+".xml");
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
						doc.getDocumentElement().normalize();
						
						NodeList nodeLst = doc.getElementsByTagName(type);
						for (int s = 0; s < nodeLst.getLength(); s++) {
							Node node = nodeLst.item(s);
							Element fstElmnt = (Element) node;
							if(fstElmnt.getAttribute("id").equals(id)){
								trimSql = fstElmnt.getFirstChild().getNodeValue().trim(); //순수 trim 쿼리
								sqls.put(key2, trimSql);
								sql = fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?").trim(); //치환 쿼리
								sqls.put(key, sql);
								break;
							}
						}
					}catch(Exception e){
						logger.error("{}", e);
					}finally{
						if(is != null){
							try{
								is.close();
								is = null;
							}catch(Exception e){
								logger.error("{}", e);
							}
						}
					}
				}
					//쿼리중의 가변 데이터를 해시맵에 저장
					while(trimSql.contains("#")){
						i ++;
						String methodName = trimSql.substring(trimSql.indexOf("{")+1,trimSql.indexOf("}"));
						inSqls.put(i, methodName);
						String tmpMethodName = null;
						if(Character.isUpperCase(methodName.charAt(1))){
							tmpMethodName = "get"+methodName.substring(0);
						}else{
							tmpMethodName = "get"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
						}
						getSqls.put(i, tmpMethodName);
						if(Character.isUpperCase(methodName.charAt(1))){
							tmpMethodName = "set"+methodName.substring(0);
						}else{
							tmpMethodName = "set"+Character.toUpperCase(methodName.charAt(0))+methodName.substring(1);
						}
						setSqls.put(i, tmpMethodName);
						trimSql = trimSql.replaceFirst("#\\{.*?\\}", "?");
					}
					maxI = i;
					//SQL connection
					try {
						ps = conn.prepareStatement(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					//VO에서 타입가져오기
					if(i>0){
						for(Field f : Arrays.asList(fields)){
							Class<?> parameterClass = f.getType();
							typSqls.put(f.getName().toString(), parameterClass.getName().substring(parameterClass.getName().lastIndexOf(".")+1));
						}
					}
					//SQL 생성 및 EXECUTION
					try {
						for( j=0 ; j<list.size() ; j++){
							obj = list.get(j);
							//VO에서 getter찾기
							for(i=1; i <= maxI ; i++){
//								System.out.println("getSeql.get() :" + getSqls.get(i).toString());
//								System.out.println("inSeql.get() :" + inSqls.get(i).toString());
//								System.out.println("inSeql.get() :" + typSqls.get(inSqls.get(i)));
								
								m = ReflectionUtils.findMethod(c, getSqls.get(i));

								Object value = ReflectionUtils.invokeMethod(m,obj);
								
								if (value == null) {
									ps.setString(i, null);
									continue;
								}
								
								if(typSqls.get(inSqls.get(i)).equals("String")){
									ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Long")){
									ps.setLong(i, (long) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Date")){
									ps.setDate(i, (Date) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Integer")){
									ps.setInt(i, (int) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Float")){
									ps.setFloat(i, (Float) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Double")){
									ps.setDouble(i, (double) ReflectionUtils.invokeMethod(m,obj));
								}else if(typSqls.get(inSqls.get(i)).equals("Timestamp")){
									ps.setTimestamp(i, (Timestamp) ReflectionUtils.invokeMethod(m,obj));
								}else{
									ps.setString(i, (String) ReflectionUtils.invokeMethod(m,obj));
								}
							}						
							ps.addBatch();
						}
						ps.executeBatch();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return j;
	}

/**
	 * ResultSet을 클래스 인스턴스를 생성하여, 
	 * 값 설정 후 리턴 함
	 * 
	 * @param rs ResultSet
	 * @param c Object 클래스
	 * @return T
	 */
	public static <T> T getResultSetToObject(ResultSet rs, Class<T> c) {
        try {
        	
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			List<String> columnNames = new ArrayList<>();
			
			for (int i = 1; i < count + 1; i++) {
				columnNames.add(metaData.getColumnName(i));
			}
        	
        	Field[] fields = c.getDeclaredFields();
        	
        	//객체 인스턴스 생성
			T x = c.newInstance();
        	
			for(Field f : Arrays.asList(fields)){
						
				// 객체 파라미터 설정
				// setter 객체를 기준으로 하기 때문에 1개로 가정
				Class<?> parameterClass = f.getType();
				
				// setter 메소스 설정
				String methodName = null;
				if(Character.isUpperCase(f.getName().charAt(1))){
					methodName = "set"+f.getName().substring(0);
				}else{
					methodName = "set"+Character.toUpperCase(f.getName().charAt(0))+f.getName().substring(1);
				}
				
				Method m = ReflectionUtils.findMethod(c, methodName, parameterClass);
				String columnName = f.getName().replaceAll("([A-Z])", "_$1").toUpperCase();
				boolean isMatch = false;
				
				for (int i = 0; i < columnNames.size(); i++) {
					if (columnName.equals(columnNames.get(i)) == true) {
						isMatch = true;
						columnNames.remove(i);
						break;
					}
				}
				
				if (isMatch == true) {
					if(isString(parameterClass)){
						ReflectionUtils.invokeMethod(m, x, rs.getString(columnName));
					} else if(isLong(parameterClass)){
						ReflectionUtils.invokeMethod(m, x, rs.getLong(columnName));
					} else if(isDate(parameterClass)){
						ReflectionUtils.invokeMethod(m, x, rs.getDate(columnName));
					} else if(isInteger(parameterClass)){
						ReflectionUtils.invokeMethod(m, x, rs.getInt(columnName));
					} else if(isFloat(parameterClass)){
						ReflectionUtils.invokeMethod(m, x, rs.getFloat(columnName));
					} else if(isDouble(parameterClass)){
						ReflectionUtils.invokeMethod(m, x, rs.getDouble(columnName));
					} else if(isTimestamp(parameterClass)){
						ReflectionUtils.invokeMethod(m, x, rs.getTimestamp(columnName));
					} else{
						ReflectionUtils.invokeMethod(m, x, rs.getString(columnName));
					}
				}
				
				// camel case 형태의 property 명을 DB 칼럼명 규칙으로 변경
				// mybatis의 <setting name="mapUnderscoreToCamelCase" value="true" /> 기능과 유사
				// ex) userName => USER_NAME
//				if(parameterClass.getName().equals("java.lang.String")){
//					ReflectionUtils.invokeMethod(m, x, rs.getString(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				} else if(parameterClass.getName().equals("java.lang.Long")){
//					ReflectionUtils.invokeMethod(m, x, rs.getLong(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				} else if(parameterClass.getName().equals("java.util.Date")){
//					ReflectionUtils.invokeMethod(m, x, rs.getDate(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				} else if(parameterClass.getName().equals("java.sql.Date")){
//					ReflectionUtils.invokeMethod(m, x, rs.getDate(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				} else if(parameterClass.getName().equals("java.lang.Integer")){
//					ReflectionUtils.invokeMethod(m, x, rs.getInt(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				} else if(parameterClass.getName().equals("java.lang.Float")){
//					ReflectionUtils.invokeMethod(m, x, rs.getFloat(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				} else if(parameterClass.getName().equals("java.lang.Double")){
//					ReflectionUtils.invokeMethod(m, x, rs.getDouble(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				} else if(parameterClass.getName().equals("java.sql.Timestamp")){
//					ReflectionUtils.invokeMethod(m, x, rs.getTimestamp(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				} else{
//					ReflectionUtils.invokeMethod(m, x, rs.getString(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
//				}
			}		
        	
        	return x;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
	
	private static boolean isString(Class<?> clazz) {
		if (clazz.getName().equals(String.class.getName()) == true) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isLong(Class<?> clazz) {
		if (clazz.getName().equals(Long.class.getName()) || clazz.getName().equals("long")) {
			return true;
		}
		
		return false;
			
	}
	
	private static boolean isDate(Class<?> clazz) {
		if (clazz.getName().equals(java.sql.Date.class.getName())
				|| clazz.getName().equals(java.util.Date.class.getName())) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isInteger(Class<?> clazz) {
		if (clazz.getName().equals(Integer.class.getName()) || clazz.getName().equals("int")) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isFloat(Class<?> clazz) {
		if (clazz.getName().equals(Float.class.getName()) || clazz.getName().equals("float")) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isDouble(Class<?> clazz) {
		if (clazz.getName().equals(Double.class.getName()) || clazz.getName().equals("double")) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isTimestamp(Class<?> clazz) {
		if (clazz.getName().equals(Timestamp.class.getName()) == true) {
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) {
		System.out.println(QueryUtil.getSqlFromMybatisMapper("com/ntels/ccbs/batch/sample/dao/mapper","CommonCodeMapper", "select","listCommonCodOracle"));
	}
	
	
}
