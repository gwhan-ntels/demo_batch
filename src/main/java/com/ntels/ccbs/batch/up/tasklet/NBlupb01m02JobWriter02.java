package com.ntels.ccbs.batch.up.tasklet;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.UpymDetail;

@Component("nBlupb01m02JobWriter02")
public class NBlupb01m02JobWriter02 extends NBlupb01m02CommonWriter implements ItemWriter<UpymDetail> {

	@Override
	public void write(List<? extends UpymDetail> upymList) throws Exception {
		getService().insertUpym(getObjectList(upymList));
	}

}
