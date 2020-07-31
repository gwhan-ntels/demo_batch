package com.ntels.ccbs.batch.common.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.ntels.ccbs.batch.util.QueryUtil;

public abstract class JdbcDao extends BatchDao {
	
	@Autowired
	protected DataSource dataSource;
	
	protected abstract String getMapperPath();
	protected abstract String getMapperName();
	
	protected Connection getConnection() {
		Connection conn = DataSourceUtils.getConnection(dataSource);
		return conn;
	}
	
	@SuppressWarnings("unchecked")
	public <R> List<R> getList(String id, Class<?> resultType, Object parameter) {
		
		Connection conn = getConnection();
		
		List<? extends Object> resultList = new ArrayList<>();
		try {
			resultList = QueryUtil.getListFromXML(getMapperPath(), getMapperName()
							, "select", id, resultType, parameter, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			
		}

		List<R> finalList = new ArrayList<>();

		for (Object obj : resultList) {
			finalList.add((R) obj);
		}

		return finalList;
	}
	
    public <R> List<R> getList(String dbType, String id, Class<?> resultType, Object parameter) {
		
		Connection conn = getConnection();
		
		List<? extends Object> resultList = new ArrayList<>();
		try {
			resultList = QueryUtil.getListFromXML(dbType,getMapperPath(), getMapperName()
							, "select", id, resultType, parameter, conn);
		} catch (Exception e) {
		
			throw new RuntimeException(e);
		} finally {
			
		}

		List<R> finalList = new ArrayList<>();

		for (Object obj : resultList) {
			finalList.add((R) obj);
		}
       
		return finalList;
	}
    
    
	public <R> R getOneDyn(String dbType, String id, Class<?> resultType, Object parameter, String funID) {
		
		List<R> itemList = getOneDynList(dbType,id, resultType, parameter, funID);
		if (itemList == null || itemList.isEmpty() == true) {
			return null;
		}
		
		if (itemList.size() > 1) {
			throw new RuntimeException("Select one. But, returned many result.");
		}
		
		return itemList.get(0);
		
	}
    
	@SuppressWarnings("unchecked")
    public <R> List<R> getOneDynList(String dbType, String id, Class<?> resultType, Object parameter, String funID) {
		   
		Connection conn = getConnection();
		
		List<? extends Object> resultList = new ArrayList<>();
		
			
		try {
			resultList = QueryUtil.getListFromXML(dbType,getMapperPath(), getMapperName()
							, "select", id, resultType, parameter, conn, funID);
		} catch (Exception e) {
		
			throw new RuntimeException(e);
		} finally {
			
		}
		
		List<R> finalList = new ArrayList<>();

		for (Object obj : resultList) {
			finalList.add((R) obj);
		}

		return finalList;

	}


	
	public <R> R getOne(String id, Class<?> resultType, Object parameter) {
		
		List<R> itemList = getList(id, resultType, parameter);

		if (itemList == null || itemList.isEmpty() == true) {
			return null;
		}
		
		if (itemList.size() > 1) {
			throw new RuntimeException("Select one. But, returned many result.");
		}
		
		return itemList.get(0);
		
	}
	
    public <R> R getOne(String dbType, String id, Class<?> resultType, Object parameter) {
		
		List<R> itemList = getList(dbType,id, resultType, parameter);

		if (itemList == null || itemList.isEmpty() == true) {
			return null;
		}
		
		if (itemList.size() > 1) {
			throw new RuntimeException("Select one. But, returned many result.");
		}

		return itemList.get(0);
		
	}

	@SuppressWarnings("unchecked")
	@Deprecated
		
	public <R> List<R> getList(String id, R resultType, Object parameter) {
		
		Connection conn = getConnection();
		
		List<? extends Object> resultList = new ArrayList<>();
		try {
			resultList = QueryUtil.getListFromXML(getMapperPath(), getMapperName()
							, "select", id, resultType.getClass(), parameter, conn);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		List<R> finalList = new ArrayList<>();
		
		for (Object obj : resultList) {
			finalList.add((R) obj);
		}
		
		return finalList;
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	
	public <R> List<R> getList(String dbType, String id, R resultType, Object parameter) {
		
		Connection conn = getConnection();
		
		List<? extends Object> resultList = new ArrayList<>();
		try {
			resultList = QueryUtil.getListFromXML(dbType, getMapperPath(), getMapperName()
							, "select", id, resultType.getClass(), parameter, conn);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		List<R> finalList = new ArrayList<>();
		
		for (Object obj : resultList) {
			finalList.add((R) obj);
		}
		
		return finalList;
	}
	
	
	public <R, P> int update(String id, Class<R> type, List<P> list) {
		
		Connection conn = getConnection();
		
		try {
			
			List<Object> updateList = new ArrayList<>();
			
			for (P p : list) {
				updateList.add(p);
			}
			
			return QueryUtil.updListFromXML(getMapperPath(), getMapperName()
					, "update", id, type, updateList, conn);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <R, P> int update(String dbType, String id, Class<R> type, List<P> list) {
		
		Connection conn = getConnection();
		
		try {
			
			List<Object> updateList = new ArrayList<>();
			
			for (P p : list) {
				updateList.add(p);
			}
			
			System.out.println("update=============" + dbType + "getMapperPath :" + getMapperPath() + "getMapperName :" + getMapperName() + "id = " + id) ;
			return QueryUtil.updListFromXML(dbType, getMapperPath(), getMapperName()
					, "update", id, type, updateList, conn);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <P> int updateOne(String id, P parameter) {
		
		List<P> updateList = new ArrayList<>();
		updateList.add(parameter);
		
		return update(id, parameter.getClass(), updateList);
		
	}
	public <P> int updateOne(String dbType, String id, P parameter) {
		
		List<P> updateList = new ArrayList<>();
		updateList.add(parameter);
		
		return update(dbType,id, parameter.getClass(), updateList);
		
	}
	
	@Deprecated
	public <R, P> int update(String id, R type, List<P> list) {
		
		Connection conn = getConnection();
		
		try {
			
			List<Object> updateList = new ArrayList<>();
			
			for (P p : list) {
				updateList.add(p);
			}
			
			return QueryUtil.updListFromXML(getMapperPath(), getMapperName()
					, "update", id, type.getClass(), updateList, conn);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Deprecated
	public <R, P> int update(String dbType, String id, R type, List<P> list) {
		
		Connection conn = getConnection();
		
		try {
			
			List<Object> updateList = new ArrayList<>();
			
			for (P p : list) {
				updateList.add(p);
			}
			
			return QueryUtil.updListFromXML(dbType,getMapperPath(), getMapperName()
					, "update", id, type.getClass(), updateList, conn);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public <R, P> int insert(String id, Class<R> type, List<P> list) {
		Connection conn = getConnection();
		
		try {
			
			List<Object> insertList = new ArrayList<>();
			
			for (P p : list) {
				insertList.add(p);
			}
			
			return QueryUtil.updListFromXML(getMapperPath(), getMapperName()
					, "insert", id, type, insertList, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public <R, P> int insert(String dbType,String id, Class<R> type, List<P> list) {
		Connection conn = getConnection();
		
		try {
			
			List<Object> insertList = new ArrayList<>();
			
			for (P p : list) {
				insertList.add(p);
			}
			
			return QueryUtil.updListFromXML(dbType,getMapperPath(), getMapperName()
					, "insert", id, type, insertList, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public <P> int insertOne(String id, P parameter) {
		
		List<P> insertList = new ArrayList<>();
		insertList.add(parameter);
		return insert(id, parameter.getClass(), insertList);
		
	}
	public <P> int insertOne(String dbType, String id, P parameter) {
		
		List<P> insertList = new ArrayList<>();
		insertList.add(parameter);
		
		return insert(dbType, id, parameter.getClass(), insertList);
		
	}
	
	@Deprecated
	public <R, P> int insert(String id, R type, List<P> list) {

		Connection conn = getConnection();
		
		try {
			
			List<Object> insertList = new ArrayList<>();
			
			for (P p : list) {
				insertList.add(p);
			}
			
			return QueryUtil.updListFromXML(getMapperPath(), getMapperName()
					, "insert", id, type.getClass(), insertList, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Deprecated
	public <R, P> int insert(String dbType, String id, R type, List<P> list) {

		Connection conn = getConnection();
		
		try {
			
			List<Object> insertList = new ArrayList<>();
			
			for (P p : list) {
				insertList.add(p);
			}
			
			return QueryUtil.updListFromXML(dbType, getMapperPath(), getMapperName()
					, "insert", id, type.getClass(), insertList, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public <R, P> int delete(String id, Class<R> type, List<P> list) {
		Connection conn = getConnection();
		
		try {
			
			List<Object> deletetList = new ArrayList<>();
			
			for (P p : list) {
				deletetList.add(p);
			}

			return QueryUtil.updListFromXML(getMapperPath(), getMapperName()
					, "delete", id, type, deletetList, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public <R, P> int delete(String dbType, String id, Class<R> type, List<P> list) {
		Connection conn = getConnection();
		
		try {
			
			List<Object> deletetList = new ArrayList<>();
			
			for (P p : list) {
				deletetList.add(p);
			}

			return QueryUtil.updListFromXML(dbType, getMapperPath(), getMapperName()
					, "delete", id, type, deletetList, conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <P> int deleteOne(String id, P parameter) {
		
		List<P> deleteList = new ArrayList<>();
		deleteList.add(parameter);
		
		return delete(id, parameter.getClass(), deleteList);
		
	}
	public <P> int deleteOne(String dbType, String id, P parameter) {
		
		List<P> deleteList = new ArrayList<>();
		deleteList.add(parameter);
		
		return delete(dbType, id, parameter.getClass(), deleteList);
		
	}
}
