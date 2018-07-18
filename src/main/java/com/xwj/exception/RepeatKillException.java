package com.xwj.exception;

/**
 * 重复秒杀异常(运行期异常)
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
