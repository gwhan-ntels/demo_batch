package com.ntels.ccbs.batch.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

public class LazyLoaderJdbc<T> implements LazyLoader<T> {

	private static TypeHandlerRegistry typeHandleRegistry;

	static {
		typeHandleRegistry = new TypeHandlerRegistry();
	}

	private Connection conn;
	private ResultSet rs;
	private Class<T> clazz;
	private Map<String, ColumnInfo> columnMap;
	private List<String> columnNameList;

	private class ColumnInfo {
		private Method setter;
		private TypeHandler<?> typeHandler;

		public Method getSetter() {
			return setter;
		}

		public void setSetter(Method setter) {
			this.setter = setter;
		}

		public TypeHandler<?> getTypeHandler() {
			return typeHandler;
		}

		public void setTypeHandler(TypeHandler<?> typeHandler) {
			this.typeHandler = typeHandler;
		}

	}

	public LazyLoaderJdbc(Connection conn, ResultSet rs, Class<T> clazz) {
		this.conn = conn;
		this.rs = rs;
		this.clazz = clazz;

		columnMap = new HashMap<>();
		columnNameList = new ArrayList<>();
		String tmpMethodName = null;

		try {
			ResultSetMetaData metaData = rs.getMetaData();

			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				try {
					String columnName = metaData.getColumnName(i);
					String fieldName = toCamelCase(columnName);
					Field field = clazz.getDeclaredField(fieldName);
					if(Character.isUpperCase(fieldName.charAt(1))){
						tmpMethodName = "set" + fieldName;
					}else{
						tmpMethodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1, fieldName.length());	
					}
					Method setter = clazz.getDeclaredMethod(tmpMethodName, field.getType());
//					Method setter = clazz.getDeclaredMethod("set" + Character.toUpperCase(fieldName.charAt(0))
//							+ fieldName.substring(1, fieldName.length()), field.getType());
					ColumnInfo columnInfo = new ColumnInfo();
					columnInfo.setSetter(setter);
					TypeHandler<?> handler = typeHandleRegistry.getTypeHandler(field.getType());
					columnInfo.setTypeHandler(handler);

					columnMap.put(columnName, columnInfo);
					columnNameList.add(columnName);
				} catch (Exception e) {
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private String toCamelCase(String str) {
		String[] split = str.toLowerCase().split("_");
		StringBuffer buf = new StringBuffer();

		if (split.length > 0) {
			buf.append(split[0]);

			for (int i = 1; i < split.length; i++) {
				buf.append(Character.toUpperCase(split[i].charAt(0)));
				buf.append(split[i].substring(1, split[i].length()));
			}
		}

		return buf.toString();
	}

	@Override
	public List<T> getItemList() {

		List<T> list = new ArrayList<>();

		try {
			for (int i = 0; i < rs.getFetchSize(); i++) {
				if (rs.next() == true) {
					T t = clazz.newInstance();
					for (String columnName : columnNameList) {
						ColumnInfo columnInfo = columnMap.get(columnName);
						TypeHandler<?> handler = columnInfo.getTypeHandler();
						Method setter = columnInfo.getSetter();
						setter.invoke(t, handler.getResult(rs, columnName));
					}

					list.add(t);
				} else {
					break;
				}
			}

			if (list == null || list.isEmpty() == true) {
				if (rs != null) {
					rs.close();
				}

//				if (conn != null) {
//					conn.close();
//				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return list;
	}

}
