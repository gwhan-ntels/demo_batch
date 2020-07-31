package com.ntels.ccbs.batch.common.tasklet;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.ntels.ccbs.batch.common.LazyLoader;

public abstract class LazyLoaderTasklet<I, O> extends CommonTasklet {
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${fetchSize}")
	private int fetchSize;
	
	private LazyLoader<I> lazyLoader;
	private List<I> itemList;
	private List<O> writeItemList;
	
	private int readCnt = 0;
	private int writeCnt = 0;
	
	protected boolean isEndLoadItem = false;
	protected boolean isLastItem = false;
	
	public LazyLoaderTasklet() {
	}
	
	protected int getReadCnt() {
		return readCnt;
	}
	
	protected int getWriteCnt() {
		return writeCnt;
	}
	
	private I getItem() {
		
		if (lazyLoader == null) {
			return null;
		}
		
		if (itemList == null || itemList.isEmpty() == true) {
			itemList = lazyLoader.getItemList();
			
			// itemLoader로부터 넘겨받은 ArrayList의 사이즈가 fetchSize보다 적다면
			// 더 이상 fetch할 데이터가 없음.
			if (itemList.size() < fetchSize) {
				isEndLoadItem = true;
			}
		}
		
		// ArrayList에 객체가 아직 남아있다면..
		if (itemList != null && itemList.isEmpty() == false) {
			I item = itemList.remove(0);

			// 마지막 fetch작업을 수행하였고 ArrayList의 마지막 아이템일 경우
			if (isEndLoadItem == true && itemList.isEmpty() == true) {
				isLastItem = true;
			}

			return item;
		}
		
		return null;
	}
	
	protected abstract LazyLoader<I> getLoader() throws Exception;
	
	protected abstract O process(I item);
	
	protected O lastProcess(I item) {
		return null;
	}

	protected abstract void write(List<O> itemList);

	protected abstract RepeatStatus end();
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
		init();

		lazyLoader = getLoader();

		I item;
		I tempItem = null;
		writeItemList = new ArrayList<>();
		
		while ((item = getItem()) != null) {
			O writeItem = process(item);
			tempItem = item;
			
			if (writeItem != null) {
				writeItemList.add(writeItem);	
			}

			if (writeItemList.size() > interval) {
				writeCnt += writeItemList.size();
				write(writeItemList);
				
				DataSourceUtils.getConnection(dataSource).commit();
			}

			readCnt++;
		}
		
		lastProcess(tempItem);
		
		if (writeItemList.isEmpty() == false) {
			writeCnt += writeItemList.size();
			write(writeItemList);
			
			DataSourceUtils.getConnection(dataSource).commit();
		}
		
		return end();
	}
	
}
