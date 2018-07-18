package com.xwj.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 秒杀常量类
 * 
 * @author xuwenjin
 */
public enum SeckillStatEnum {
	SUCCESS(1, "秒杀成功"), 
	END(0, "秒杀结束"), 
	REPEAT_KILL(-1, "重复秒杀"), 
	INNER_ERROR(-2, "系统异常"), 
	DATA_REWRITE(-3, "数据篡改");

	private int state;

	private String stateInfo;

	private SeckillStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static String stateOf(int state) {
		return Arrays.stream(values()).filter(item -> item.getState() == state).collect(Collectors.toList()).get(0)
				.getStateInfo();
	}

}
