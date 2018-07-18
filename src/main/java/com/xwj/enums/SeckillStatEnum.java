package com.xwj.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * ��ɱ������
 * 
 * @author xuwenjin
 */
public enum SeckillStatEnum {
	SUCCESS(1, "��ɱ�ɹ�"), 
	END(0, "��ɱ����"), 
	REPEAT_KILL(-1, "�ظ���ɱ"), 
	INNER_ERROR(-2, "ϵͳ�쳣"), 
	DATA_REWRITE(-3, "���ݴ۸�");

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
