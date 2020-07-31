package com.ntels.ccbs.batch.up.tasklet;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.up.common.entity.AuthChgAppl;

@Component("nBlupb01m02JobWriter05")
public class NBlupb01m02JobWriter05 extends NBlupb01m02CommonWriter implements ItemWriter<AuthChgAppl> {

	@Override
	public void write(List<? extends AuthChgAppl> authChgApplList) throws Exception {
		getService().insertAuthChgAppl(getObjectList(authChgApplList));
	}

}
