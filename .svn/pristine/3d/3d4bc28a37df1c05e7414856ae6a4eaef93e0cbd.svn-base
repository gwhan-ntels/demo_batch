package com.ntels.ccbs.batch.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

@Component
public class JdbcConnectionManager {
	
	@Autowired
	private DataSource dataSource;
	
//	private Map<Long, Connection> connectionMap;
//	private ThreadLocal<Boolean> isOpenWithTransaction;
	
	public JdbcConnectionManager() {
//		connectionMap = new ConcurrentHashMap<>();
//		isOpenWithTransaction = new ThreadLocal<>();
	}
	
	private long getId() {
		return Thread.currentThread().getId();
	}
	
	/**
	 * 현재 쓰레드의 열려있는 커넥션이 있는지 체크함.
	 * @return
	 */
	public boolean isOpenConnection() throws SQLException {
//		long id = getId();
//		if (connectionMap.containsKey(id) == true) {
//			Connection conn = connectionMap.get(id);
//			if (conn.isClosed() == true) {
//				return false;
//			} else {
//				return true;
//			}
//		} else {
//			return false;
//		}
		return true;
	}

	/**
	 * 새로운 커넥션을 열어준다.
	 * DataSourceUtils롤 통하여 DataSourceTransactionManager를 통한 커넥션을 가져오고
	 * 커넥션을 가져오기 실패했다면 새로운 커넥션을 열어준다. 이미 열려있는 커넥션이 있다면 추가로 열지 않는다.
	 * @return true : 새로운 커넥션이 열렸음, false : 이미 열려있는 커넥션이 있음.
	 */
	public synchronized boolean open() {
//		try {
//			// 이미 열려있는 커넥션이 있다면 추가로 열지 않는다.
//			if (isOpenConnection() == true) {
//				return false;
//			}
//			
//			long id = getId();
//			
//			// DataSourceTransactionManager로 트랜잭션에 연결된 커넥션을 가져온다.
//			Connection conn = null;
//			
//			try {
//				conn = DataSourceUtils.getConnection(dataSource);
//				isOpenWithTransaction.set(true);	
//			} catch (Exception e) {
//				conn = dataSource.getConnection();
//				isOpenWithTransaction.set(false);
//			}
//			
//			// 가져온 커넥션이 없으면 새로운 커넥션을 생성한다.
//			if (conn == null) {
//				isOpenWithTransaction.set(false);
//				conn = dataSource.getConnection();				
//			}
//
//			connectionMap.put(id, conn);
//			
//			return true;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		return false;
	}

	public synchronized void close() {
		
//		long id = getId();
//		
//		if (connectionMap.containsKey(id) == false) {
//			return;
//		}
//		
//		if (isOpenWithTransaction.get() == true) {
//			// 트랜젝션 매니저가 커넥션을 관리하므로 커넥션을 종료하지 않고 맵에서만 제거한다.
//			connectionMap.remove(id);
//		} else {
//			try {				
//				Connection conn = connectionMap.get(id);
//				if (conn != null && conn.isClosed() == false) {
//					conn.close();
//				}
//				
//				connectionMap.remove(id);
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}	
//		}
	}

	public Connection getConnection() {		
		return DataSourceUtils.getConnection(dataSource);
//		return connectionMap.get(getId());
	}

	public void closeAndRollback() {
//		try {
//			Connection conn = getConnection();
//			
//			if (conn != null) {
//				getConnection().rollback();
//				close();	
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
	}

	public void closeAndCommit() {
//		try {
//			close();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
	}
	
}
