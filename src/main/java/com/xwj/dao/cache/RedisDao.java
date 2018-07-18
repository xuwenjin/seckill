package com.xwj.dao.cache;

import com.xwj.entity.Seckill;
import com.xwj.util.ProtoStuffUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {

	private final JedisPool jedisPool;

	public RedisDao(String host, int port) {
		jedisPool = new JedisPool(host, port);
	}

	public Seckill getSeckill(long seckillId) {
		Jedis jedis = jedisPool.getResource();
		boolean hasJedis = jedis != null;
		// redis操作逻辑
		try {
			if (!hasJedis) {
				jedis = jedisPool.getResource();
			}
			try {
				String key = getSeckillRedisKey(seckillId);
				byte[] bytes = jedis.get(key.getBytes());
				if (bytes != null) {
					// 使用采用自定义序列化，protostuff
					Seckill seckill = ProtoStuffUtil.deserializer(bytes, Seckill.class);
					return seckill;
				}
			} finally {
				if (!hasJedis) {
					jedis.close();
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	public String putSeckill(Seckill seckill) {
		Jedis jedis = null;
		boolean hasJedis = jedis != null;
		try {
			if (!hasJedis) {
				jedis = jedisPool.getResource();
			}
			try {
				String key = getSeckillRedisKey(seckill.getSeckillId());
				byte[] bytes = ProtoStuffUtil.serializer(seckill);
				int timeout = 60 * 60; // 超时缓存，1小时
				String result = jedis.setex(key.getBytes(), timeout, bytes);
				return result;
			} finally {
				if (!hasJedis) {
					jedis.close();
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 根据id获取redis的key
	 *
	 * @param seckillId
	 *            商品id
	 * @return redis的key
	 */
	private String getSeckillRedisKey(long seckillId) {
		return "seckill:" + seckillId;
	}

}
