package org.magkades.service;


/**
 * Class to map application related exceptions
 *
 */
public class AppException extends Exception {

	// application specific error code
	private int code;

	/**
	 * 
	 * @param code
	 * @param message
	 */
	public AppException(int code, String message) {
		super(message);
		this.code = code;
	}

	public AppException() { }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
