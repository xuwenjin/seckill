package com.xwj.exception;

/**
 * �ظ���ɱ�쳣(�������쳣)
 * 
 * @author xuwenjin
 */
@SuppressWarnings("serial")
public class RepeatKillException extends RuntimeException {

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}
	
	

}
