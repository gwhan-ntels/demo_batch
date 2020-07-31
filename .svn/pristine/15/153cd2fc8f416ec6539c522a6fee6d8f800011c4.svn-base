package com.ntels.ccbs.batch.up.tasklet;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.UpymCtrtMngTp;

@Component("nBlupb01m02JobWriter07")
public class NBlupb01m02JobWriter07 extends NBlupb01m02CommonWriter implements ItemWriter<UpymCtrtMngTp> {

	@Override
	public void write(List<? extends UpymCtrtMngTp> upymCtrtMngTpList) throws Exception {
		getService().insertUpymCtrtMngTp(getObjectList(upymCtrtMngTpList));
	}

}
