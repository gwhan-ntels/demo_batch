package com.ntels.ccbs.batch.common.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BatchDao {

	public interface BatchJob<T> {
		int job(T mapper);
	}
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	protected <M> int batchJob(Class<M> mapperType, BatchJob<M> callback) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		M mapper = sqlSession.getMapper(mapperType);
		int insertCount = 0;
		
		try {
			insertCount = callback.job(mapper);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw new RuntimeException(e);
		} finally {
			sqlSession.close();
		}
		
		return insertCount;
	}
	
}
