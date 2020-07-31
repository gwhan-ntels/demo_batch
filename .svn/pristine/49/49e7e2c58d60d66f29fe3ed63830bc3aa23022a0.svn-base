package com.ntels.ccbs.batch.up.tasklet;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.entity.DebtTgtPsn;

@Component
public class DebtTgtPsnJobWriter extends NBlupb01m02CommonWriter implements ItemWriter<DebtTgtPsn> {

	@Override
	public void write(List<? extends DebtTgtPsn> debtPgtPsnList) throws Exception {
		getService().insertDebtTgtPsn(getObjectList(debtPgtPsnList));
	}

}
