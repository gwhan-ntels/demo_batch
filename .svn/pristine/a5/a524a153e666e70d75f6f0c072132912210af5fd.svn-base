package com.ntels.ccbs.batch.sample.tasklet;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.entity.CommonCode;
import com.ntels.ccbs.batch.sample.service.CommonCodeService;
import com.ntels.ccbs.batch.sample.vo.SearchParam;

/**
 *
 * ItemReader 샘플.
 *
 * @see	 [Optional] 관련정보
 * <PRE>
 * 1. ClassName: SampleReader
 * 2. FileName : SampleReader.java
 * 3. Package  : com.ntels.ccbs.batch.sample.tasklet
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:22:46
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 * </PRE>
 */
@Component
//@Scope("step") //<-- parameter 필요할 경우 선언
public class SampleReader implements ItemReader<CommonCode> {

	/** HistoryService Autowired. */
	@Autowired
	private CommonCodeService commonCodeService;

	/** 시작여부를 판단하여 최초 데이터 로딩을 처리함. */
	private boolean isStarted = false;

	/** 배치 처리 대상 리스트. */
	List<CommonCode> list;

	/**
	 *
	 * 데이터 로딩 메소드.
	 *
	 * @warning	[Optional]함수의 제약사항이나 주의해야 할 점
	 * @see	[Optional]관련 정보(관련 함수, 관련 모듈)
	 */
	private void dataSet(){
		SearchParam c = new SearchParam();
		c.setStartNum(0);
		c.setEndNum(30);
		list = commonCodeService.list(c);		
	}

	/*
	 * 전달 파라미터 필요할 경우 선언
	@Value("#{jobParameters['date']}")
	String date;
	*/ 
	
	public CommonCode read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		
		if(isStarted == false){
			isStarted = true;
			dataSet();
		}

		if (!list.isEmpty()) {
			return list.remove(0);
		}
		
		return null;
	}

}
