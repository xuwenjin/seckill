package com.xwj.exception;

/**
 * ��ɱ�������쳣(�������쳣)
 * 
 * @author xuwenjin
 */
@SuppressWarnings("serial")
public class SeckillCloseException extends RuntimeException {

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloseException(String message) {
		super(message);
	}

}
