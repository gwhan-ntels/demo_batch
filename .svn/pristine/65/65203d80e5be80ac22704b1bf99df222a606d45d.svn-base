package com.ntels.ccbs.batch.up.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.CtrtDetail;

@Component("nBlupb01m02JobWriter03")
public class NBlupb01m02JobWriter03 extends NBlupb01m02CommonWriter implements ItemWriter<CtrtDetail> {

	@Override
	public void write(List<? extends CtrtDetail> ctrtDetailList) throws Exception {
		
		List<CtrtDetail> insertList = new ArrayList<>();
		
		for (CtrtDetail ctrtDetail : ctrtDetailList) {
			insertList.add(ctrtDetail);
		}
		
		getService().insertCtrtDetail(insertList);
	}

}
