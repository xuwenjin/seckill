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
		// redis�����߼�
		try {
			if (!hasJedis) {
				jedis = jedisPool.getResource();
			}
			try {
				String key = getSeckillRedisKey(seckillId);
				byte[] bytes = jedis.get(key.getBytes());
				if (bytes != null) {
					// ʹ�ò����Զ������л���protostuff
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
				int timeout = 60 * 60; // ��ʱ���棬1Сʱ
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
	 * ����id��ȡredis��key
	 *
	 * @param seckillId
	 *            ��Ʒid
	 * @return redis��key
	 */
	private String getSeckillRedisKey(long seckillId) {
		return "seckill:" + seckillId;
	}

}
