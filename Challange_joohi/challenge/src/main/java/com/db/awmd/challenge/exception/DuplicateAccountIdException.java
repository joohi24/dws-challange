package com.db.awmd.challenge.exception;

public class DuplicateAccountIdException extends RuntimeException {

	/**
	 * Generated Serial version ID
	 */
	private static final long serialVersionUID = -609680503837641957L;

	public DuplicateAccountIdException(String message) {
    super(message);
  }
}
