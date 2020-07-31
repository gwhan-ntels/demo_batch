package com.ntels.ccbs.batch.common.exception;

public class BatchException extends Exception {

	private static final long serialVersionUID = -2295498037867299373L;

	private BatchError batchError;

	public BatchException() {
		super();
	}
	
	public BatchException(String message) {
		super(message);
	}
	
	public BatchException(BatchError batchError) {
		super(batchError.getMessage());
		setBatchError(batchError);
	}

	public BatchError getBatchError() {
		return batchError;
	}

	public void setBatchError(BatchError batchError) {
		this.batchError = batchError;
	}

}
