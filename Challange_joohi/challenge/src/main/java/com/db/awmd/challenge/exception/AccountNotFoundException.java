package com.db.awmd.challenge.exception;

public class AccountNotFoundException extends RuntimeException {

	/**
	* Generated Serial version Id
	*/
	private static final long serialVersionUID = 255721644032051440L;

	public AccountNotFoundException(String message) {
		super(message);
	}
}
