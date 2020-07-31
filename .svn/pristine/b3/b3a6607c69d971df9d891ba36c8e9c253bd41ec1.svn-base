package com.ntels.ccbs.batch.common.tasklet;

import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.common.tasklet.CommonTasklet.BAT_PROC_STAT;

public abstract class CommonItemReader<T> implements ItemReader<T>, StepExecutionListener {

	@Autowired
	protected CommonService commonService;

	@Autowired
	protected ClogService clogService;

	@Value("${fetchSize}")
	private int fetchSize;

	@Value("#{jobParameters['clcWrkNo']}")
	protected String clcWrkNo;

	@Value("#{jobParameters['soId']}")
	protected String soId;

	@Value("#{jobParameters['billYymm']}")
	protected String billYymm;

	@Value("#{jobParameters['billCycl']}")
	protected String billCycl;

	@Value("#{jobParameters['pgmId']}")
	protected String pgmId;

	@Value("#{jobParameters['pgmSeq']}")
	protected String pgmSeq;

	@Value("#{jobParameters['grpId']}")
	protected String grpId;

	@Value("#{jobParameters['pgmExeSeqNo']}")
	protected String pgmExeSeqNo;

	@Value("#{jobParameters['user']}")
	protected String user;

	@Value("#{jobParameters['workYymm']}")
	protected String workYymm;

	@Value("#{jobParameters['logFileName']}")
	protected String logFileName;

	protected Common common;

	private LazyLoader<T> itemLoader;
	private List<T> itemList;

	private boolean isEndLoadItem;

	/**
	 * <pre>
	 * 대용량 데이터를 조회할 때 지연로딩을 위한 데이터베이스 리소스를 가지고 있는 객체를 생성하여 리턴합니다.
	 * </pre>
	 * 
	 * @return
	 */
	protected abstract LazyLoader<T> getLoader() throws Exception;

	/**
	 * <pre>
	 * 조회 된 아이템을 ItemProcessor혹은 ItemWriter로 넘기기 전에 수행해야 할 작업이 있다면
	 * 이 메소드를 구현하여 작업을 진행합니다.
	 * 
	 * 수행 할 작업이 없다면 메소드의 내용은 작성하지 않아도 됩니다.
	 * </pre>
	 * 
	 * @param item
	 */
	protected void setItemDefaultValue(T item) {
	}

	/**
	 * <pre>
	 * 조회대상 전체 Row중 해당 아이템이 마지막 Row임을 알려줍니다.
	 * 마지막에 수행해야 할 작업이 있다면 이 메소드를 구현하여 작업을 수행합니다.
	 * 
	 * 수행 할 작업이 없다면 메소드의 내용은 작성하지 않아도 됩니다.
	 * </pre>
	 * 
	 * @param item
	 */
	protected void lastItem(T item) {
	}

	/**
	 * <pre>
	 * 스텝의 작업을 수행하기 전 TB_BAT_PGM_LOG에 로그를 남길것인가 여부
	 * 기본적으로 true의 상태이므로 로그를 남기지 말아야 하는 상황이라면
	 * 이 메소드를 상속하여 false를 넘기도록 한다.
	 * </pre>
	 * 
	 * @return
	 */
	protected boolean isInsertPgmLog() {
		return true;
	}

	/**
	 * <pre>
	 * 스텝의 작업이 끝난 후 TB_BAT_PGM_LOG에 작업 상태를 변경할것인가 여부
	 * 기본적으로 true의 상태이므로 로그를 변경하지 말아야 하는 상황이라면
	 * 이 메소드를 상속하여 false를 넘기도록 한다.
	 * </pre>
	 * 
	 * @return
	 */
	protected boolean isUpdatePgmLog() {
		return true;
	}
	
	protected Common getCommon() {
		
		if (common == null) {
			common = createCommon();
		}
		
		return common;
	}
	
	private Common createCommon() {
		Common common = new Common();
		common.setLogFile(logFileName);
		common.setGrpId(grpId);
		common.setBsYymm(workYymm);
		common.setBillYymm(workYymm);
		common.setBatPgmId(pgmId);
		common.setSoId(soId);
		common.setClcWrkNo(clcWrkNo);
		common.setpSeq(pgmSeq);
		common.setPgmExeSeqNo(pgmExeSeqNo);
		common.setBillCycl(billCycl);
		common.setBatProcStat(BAT_PROC_STAT.IN.code());
		common.setLogFilePath(common.LOG_LOCAL);
		
		return common;
	}

	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		// itemLoader가 초기화되지 않았다면 처음 read메소드가 실행된것.
		if (itemLoader == null) {
			isEndLoadItem = false;
			// 각 서브 클래스에서 itemLoader를 생성하여 전달
			itemLoader = getLoader();

			if (itemLoader == null) {
				return null;
			}
		}

		// ArrayList가 초기회되지 않았거나 모두 제거되어 비어있다면 itemLoader에서
		// fetchSize만큼의 리스트를 다시 받아온다.
		if (itemList == null || itemList.isEmpty() == true) {
			itemList = itemLoader.getItemList();

			// itemLoader로부터 넘겨받은 ArrayList의 사이즈가 fetchSize보다 적다면
			// 더 이상 fetch할 데이터가 없음.
			if (itemList.size() < fetchSize) {
				isEndLoadItem = true;
			}
		}

		// ArrayList에 객체가 아직 남아있다면..
		if (itemList.isEmpty() == false) {
			T item = itemList.remove(0);
			setItemDefaultValue(item);

			// 마지막 fetch작업을 수행하였고 ArrayList의 마지막 아이템일 경우
			if (isEndLoadItem == true && itemList.isEmpty() == true) {
				lastItem(item);
			}

			return item;
		}

		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// 배치 실행 로그 생성
		common = createCommon();

		if (isInsertPgmLog() == true) {

			common.setReadCnt(stepExecution.getReadCount());
			common.setWriteCnt(stepExecution.getWriteCount());

			// 기존 로그가 존재하면
			// 1. 배치 진행 상태가 재요청일 경우
			//	-> 배치 진행 상태를 작업중으로 변경 후 배치 작업 진행
			// 2. 그 외의 경우
			//	-> 잘못된 접근으로 에러 발생
			if (commonService.batPgmLogCount(common) > 0) {
				String batProcStat = commonService.batProcStat(common);

				if (batProcStat.equals(BAT_PROC_STAT.RETRY.code()) == true) {
					commonService.updateBatProcStat(common);
				} else {
					throw new RuntimeException("잘못된 작업 요청입니다. bat_proc_stat : " + batProcStat+"'");
				}
			} else {
				commonService.commonInsBatPgmLog(common);
			}
		}
		
		clogService.writeLog("{}.beforeStep common\n{}", getClass().getName(), clogService.objectToString(common));
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		clogService.writeAfterStepLog(stepExecution);
		
		if (isUpdatePgmLog() == true || stepExecution.getStatus() != BatchStatus.COMPLETED) {
			common.setReadCnt(stepExecution.getReadCount());
			common.setWriteCnt(stepExecution.getWriteCount());
			common.setErrCnt(0);
			common.setProcCnt(0);
			common.setCmplCnt(0);

			if (stepExecution.getStatus().toString() == "COMPLETED") {
				common.setBatProcStat(BAT_PROC_STAT.CMPL.code());
			} else {
				common.setBatProcStat(BAT_PROC_STAT.ERR.code());
			}

			commonService.commonUpdBatPgmLog(common);
		}

		return null;
	}

}
