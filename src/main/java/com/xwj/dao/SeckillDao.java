package com.xwj.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xwj.entity.Seckill;

public interface SeckillDao {

	/**
	 * 减库存
	 * 
	 * @param seckillId
	 *            库存id
	 * @param killTime
	 *            秒杀时间
	 * @return 影响的行数
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

	/**
	 * 根据id查询秒杀对象
	 * 
	 * @param seckillId
	 *            库存id
	 * @return
	 */
	Seckill queryById(long seckillId);

	/**
	 * 根据偏移量查询秒杀商品列表
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	void killByProcedure(Map<String, Object> paramMap);

}
