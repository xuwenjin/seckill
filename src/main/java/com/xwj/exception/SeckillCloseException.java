package com.xwj.exception;

/**
 * 秒杀结束后异常(运行期异常)
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
