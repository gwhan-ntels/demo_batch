package com.ntels.ccbs.batch.sample.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.entity.CommonCode;
import com.ntels.ccbs.batch.sample.vo.SearchParam;

/**
 * 공통 코드 Mapper.
 *
 * <PRE>
 * 1. ClassName: CommonCodeMapper
 * 2. FileName : CommonCodeMapper.java
 * 3. Package  : com.ntels.ccbs.cm.dao.configuration
 * 4. 작성자   : smyun@ntels.com
 * 5. 작성일   : 2014. 4. 8. 오후 5:02:49
 * 6. 변경이력
 *		이름  :		일자	: 변경내용
 *     ———————————————————————————————————
 *		smyun :	2014. 4. 8.	: 신규 개발.
 * </PRE>
 */
@Component
public interface CommonCodeMapper {

	/**
	 * 목록.
	 *
	 * @param condition 조회조건
	 * @return List<CommonCode>
	 */
	List<CommonCode> listCommonCode(SearchParam param);

}