package com.springbank.exceptions;

public class AccountTerminationException extends RuntimeException {

	private static final long serialVersionUID = -7801677009024055626L;

	public AccountTerminationException(final String cause) {
		super(cause);
	}
	
	public AccountTerminationException(final String cause,final Throwable throwable) {
		super(cause,throwable);
	}

}
