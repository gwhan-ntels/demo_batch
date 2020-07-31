package com.ntels.ccbs.batch.iv.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.service.CBillService;
import com.ntels.ccbs.batch.iv.service.NBlivb01m03Service;

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
public class NBlivb01m03Writer01 implements ItemWriter<CBillComm> {

	@Autowired
	private NBlivb01m03Service clsService;

	@Autowired
	private CBillService clsCommService;
	
	@Autowired
	private ClogService clog;

	long j = 0;
	long i = 0;
	List<Object> Objectlist = new ArrayList<Object>();

	// public void write(List<? extends NBliv03m01> lists) throws Exception {
	public void write(List<? extends CBillComm> items) throws Exception {

		clog.writeLog("BLIV01M03-01 WRITE--------------------------------------");
		Objectlist.clear();
		// System.out.println("Writer Start");
		// System.out.println("WRITE LIST = " + items);
		Objectlist.clear();
		if (i == 0)
			clog.writeLog("[Write Start :" + CUtil.utilGetDateTime(4));

		for (CBillComm list : items) {
			i++;
			j++;
			list.setTimeInfo();

			list.setBillSeqNo(clsCommService.getBillSeqNo(list.getBillYymm(), list.getBillCycl(),
					list.getBillDt().substring(4, 6), list.getPymAcntId(), "00"));
			Objectlist.add(list);
		}
		clsService.saveJdbcDirect(Objectlist);
		if (i == 10000) {
			clog.writeLog("[" + j + ":" + CUtil.utilGetDateTime(4));
			i = 0;
		}

	}

}