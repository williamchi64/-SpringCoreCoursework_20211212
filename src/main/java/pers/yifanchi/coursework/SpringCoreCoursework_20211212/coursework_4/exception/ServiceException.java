package pers.yifanchi.coursework.SpringCoreCoursework_20211212.coursework_4.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -4446259141972579043L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}
	
}
