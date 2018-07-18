package com.xwj.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwj.dao.SuccessSeckillDao;
import com.xwj.entity.SuccessSeckill;

@RunWith(SpringJUnit4ClassRunner.class)
//∏ÊÀﬂjunit£¨spring≈‰÷√Œƒº˛
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SuccessSeckillDaoTest {
	
	@Resource
	private SuccessSeckillDao dao;

	@Test
	public void testInsertSuccessSeckill() {
		int count = dao.insertSuccessSeckill(1001, "18207135556");
		System.out.println(count);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		SuccessSeckill kill = dao.queryByIdWithSeckill(1001, "18207135555");
		System.out.println(kill);
		
	}

}
