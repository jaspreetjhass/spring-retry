package com.springbank.exceptions;

public class AccountCreationException extends RuntimeException {

	private static final long serialVersionUID = -7801677009024055626L;

	public AccountCreationException(final String cause) {
		super(cause);
	}
	
	public AccountCreationException(final String cause,final Throwable throwable) {
		super(cause,throwable);
	}

}
