package com.ntels.ccbs.batch.common;

import java.util.List;

public interface LazyLoader<T> {

	List<T> getItemList();

}
