package com.xwj.dao;

import org.apache.ibatis.annotations.Param;

import com.xwj.entity.SuccessSeckill;

public interface SuccessSeckillDao {

	/**
	 * 插入购买明细，可以过滤重复
	 * 
	 * @param seckillId
	 *            库存id
	 * @param userPhone
	 *            用户手机号
	 * @return 插入的行数
	 */
	int insertSuccessSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);

	/**
	 * 根据seckillId查询明细，并携带秒杀产品对象
	 * 
	 * @param seckillId
	 * @return
	 */
	SuccessSeckill queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);

}
