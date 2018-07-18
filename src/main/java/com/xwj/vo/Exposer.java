package com.xwj.vo;

/**
 * 暴露秒杀地址VO
 * 
 * @author xuwenjin
 */
public class Exposer {
	
	//是否开启秒杀
	private boolean exporsed;
	
	private String md5;
	
	private long seckillId;
	
	//系统当前时间(毫秒)
	private long now;
	
	//秒杀开始
	private long start;
	
	//秒杀结束
	private long end;

	public Exposer(boolean exporsed, String md5, long seckillId) {
		super();
		this.exporsed = exporsed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}
	
	public Exposer(boolean exporsed, long seckillId, long now, long start, long end) {
		super();
		this.exporsed = exporsed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public Exposer(boolean exporsed, long seckillId) {
		super();
		this.exporsed = exporsed;
		this.seckillId = seckillId;
	}

	public boolean isExporsed() {
		return exporsed;
	}

	public void setExporsed(boolean exporsed) {
		this.exporsed = exporsed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Exposer [exporsed=" + exporsed + ", md5=" + md5 + ", seckillId=" + seckillId + ", now=" + now
				+ ", start=" + start + ", end=" + end + "]";
	}
	
}
