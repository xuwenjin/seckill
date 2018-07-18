package com.xwj.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwj.dao.SeckillDao;
import com.xwj.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class RedisDaoTest {

	private long id = 1001;

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private SeckillDao seckillDao;

	@Test
	public void testSeckill() {
		Seckill seckill = redisDao.getSeckill(id);
		if (seckill == null) {
			seckill = seckillDao.queryById(id);
			String result = redisDao.putSeckill(seckill);
			System.out.println("result:" + result);
			redisDao.putSeckill(seckill);
			System.out.println(redisDao.getSeckill(id));
		}

	}

}
