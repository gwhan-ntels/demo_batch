package com.ntels.ccbs.batch.up.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.UpymEntrustMast;

@Component("nBlupb01m02JobWriter04")
public class NBlupb01m02JobWriter04 extends NBlupb01m02CommonWriter implements ItemWriter<UpymEntrustMast> {

	@Override
	public void write(List<? extends UpymEntrustMast> upymEntrustMastList) throws Exception {
		
		List<UpymEntrustMast> insertList = new ArrayList<>();
		
		for (UpymEntrustMast upymEntrustMast : upymEntrustMastList) {
			insertList.add(upymEntrustMast);
		}
		
		getService().insertUpymEntrustMast(insertList);
	}

}