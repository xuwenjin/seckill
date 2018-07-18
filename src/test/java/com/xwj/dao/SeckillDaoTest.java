package com.xwj.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwj.dao.SeckillDao;
import com.xwj.entity.Seckill;

/**
 * 配置spring和junit整合，junit启动时自动加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit，spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SeckillDaoTest {
	
	@Resource
	private SeckillDao dao;

	@Test
	public void testQueryById() {
		long id = 1001;
		Seckill seckill = dao.queryById(id);
		System.out.println(seckill);
	}

	@Test
	public void testReduceNumber() {
		Date date = new Date();
		long count = dao.reduceNumber(1001, date);
		System.out.println(count);
	}

	@Test
	public void testQueryAll() {
		//java不会保存形参, queryAll(offset, limit) =>queryAll(arg0, arg1)
		//当只有一个参数时，是正常的
		List<Seckill> secList = dao.queryAll(0, 100);
		secList.stream().forEach(d -> {
			System.out.println(d);
		});
	}

}
