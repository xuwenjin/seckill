package com.xwj.exception;

/**
 * ��ɱ���ҵ���쳣
 * 
 * @author xuwenjin
 */
@SuppressWarnings("serial")
public class SeckillException extends RuntimeException {

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillException(String message) {
		super(message);
	}

}
