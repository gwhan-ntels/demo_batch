package com.ntels.ccbs.batch.up.tasklet;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.UpymDetail;

@Component
public class NBlupb01m02JobWriter01 extends NBlupb01m02CommonWriter implements ItemWriter<UpymDetail> {

	@Override
	public void write(List<? extends UpymDetail> list) throws Exception {
		getService().insertUpymDtl(getObjectList(list));
	}

}
