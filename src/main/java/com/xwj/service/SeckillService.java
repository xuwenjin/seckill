package com.xwj.service;

import java.util.List;

import com.xwj.entity.Seckill;
import com.xwj.exception.RepeatKillException;
import com.xwj.exception.SeckillCloseException;
import com.xwj.exception.SeckillException;
import com.xwj.vo.Exposer;
import com.xwj.vo.SeckillExcution;

public interface SeckillService {

	/**
	 * 获取所有秒杀记录
	 * 
	 * @return
	 */
	List<Seckill> getSeckillList();

	/**
	 * 单个查询秒杀记录
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);

	/**
	 * 秒杀开启，输出秒杀接口地址 否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);

	/**
	 * 执行秒杀操作
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExcution executeSeckill(long seckillId, String userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;
	
	/**
	 * 执行秒杀操作(使用存储过程)
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExcution executeSeckillProcedure(long seckillId, String userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;

}
