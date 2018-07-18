package com.xwj.vo;

import com.xwj.entity.SuccessSeckill;
import com.xwj.enums.SeckillStatEnum;

/**
 * 封装秒杀执行后结果
 * 
 * @author xuwenjin
 */
public class SeckillExcution {

	private long seckillId;

	// 秒杀执行结果
	private int state;

	// 状态表示
	private String stateInfo;

	private SuccessSeckill successKill;

	public SeckillExcution(long seckillId, SeckillStatEnum stat) {
		super();
		this.seckillId = seckillId;
		this.stateInfo = stat.getStateInfo();
	}
	
	public SeckillExcution(long seckillId, String stateInfo) {
		super();
		this.seckillId = seckillId;
		this.stateInfo = stateInfo;
	}

	public SeckillExcution(long seckillId, SeckillStatEnum stat, SuccessSeckill successKill) {
		super();
		this.seckillId = seckillId;
		this.stateInfo = stat.getStateInfo();
		this.successKill = successKill;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessSeckill getSuccessKill() {
		return successKill;
	}

	public void setSuccessKill(SuccessSeckill successKill) {
		this.successKill = successKill;
	}

	@Override
	public String toString() {
		return "SeckillExcution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo + "]";
	}

}
