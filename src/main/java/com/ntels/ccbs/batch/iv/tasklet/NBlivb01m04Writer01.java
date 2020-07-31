package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.service.NBlivb01m04Service;

/**
 *
 * ItemWriter 샘플.
 *
 * @see [Optional] 관련정보
 * 
 *      <PRE>
 * 1. ClassName: NBliv03m01Writer
 * 2. FileName : NBliv03m01Writer.java
 * 3. Package  : com.ntels.ccbs.batch.sample.tasklet
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:24:00
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 *      </PRE>
 */

@Component
public class NBlivb01m04Writer01 implements ItemWriter<CBillComm> {

	@Autowired
	private ClogService clslog;

	@Autowired
	private NBlivb01m04Service clsService;

	@Autowired
	private CommonService commonService;

	long j = 0;
	long i = 0;
	List<Object> Objectlist = new ArrayList<Object>();

	public void write(List<? extends CBillComm> items) throws Exception {

		Objectlist.clear();
		if (i == 0) {
			clslog.writeLog("[Write Start :" + CUtil.utilGetDateTime(4));
		}

		for (CBillComm list : items) {
			i++;
			j++;

			list.setBillSeqNo(commonService.getBillSeqNo(list.getBillYymm(), list.getBillCycl(),
					list.getBillDt().substring(6, 8), list.getPymAcntId(), "00"));
			list.setTimeInfo();
			Objectlist.add(list);
		}
		clsService.saveJdbcDirect(Objectlist);
		if (i == 10000) {
			clslog.writeLog("[" + j + ":" + CUtil.utilGetDateTime(4));
			i = 0;
		}

	}

}