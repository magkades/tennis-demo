package org.magkades.service;


/**
 * Class to map application related exceptions
 *
 */
public class AppException extends Exception {

	private static final long serialVersionUID = -8999932578270387947L;

	/** application specific error code */
	int code; 

	/** detailed error description for developers*/
	String developerMessage;	
	
	/**
	 * 
	 * @param code
	 * @param message
	 * @param developerMessage
	 */
	public AppException(int code, String message,
						String developerMessage) {
		super(message);
		this.code = code;
		this.developerMessage = developerMessage;
	}

	public AppException() { }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
}
