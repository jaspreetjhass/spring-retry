package com.springbank.exceptions;

public class InSufficientFundException extends RuntimeException {

	private static final long serialVersionUID = -7801677009024055626L;

	public InSufficientFundException(final String cause) {
		super(cause);
	}
	
	public InSufficientFundException(final String cause,final Throwable throwable) {
		super(cause,throwable);
	}

}
