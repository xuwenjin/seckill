package com.xwj.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwj.entity.Seckill;
import com.xwj.vo.Exposer;
import com.xwj.vo.SeckillExcution;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class SeckillServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService service;

	@Test
	public void testGetSeckillList() {
		List<Seckill> list = service.getSeckillList();
		logger.info("list={}", list);
	}

	@Test
	public void testGetById() {
		long seckillId = 1001;
		Seckill seckill = service.getById(seckillId);
		logger.info("seckill={}", seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long seckillId = 1001;
		Exposer exposer = service.exportSeckillUrl(seckillId);
		logger.info("exposer={}", exposer);
	}

	@Test
	public void testExecuteSeckill() {
		long seckillId = 1001;
		String userPhone = "122121";
		String md5 = "155276c74b31377986e4994d653b216a";
		SeckillExcution excution = service.executeSeckill(seckillId, userPhone, md5);
		logger.info("result={}", excution);
	}
	
	@Test
	public void testExecuteSeckillForPro() {
		long seckillId = 1001;
		String userPhone = "122122";
		Exposer exposer = service.exportSeckillUrl(seckillId);
		if (exposer.isExporsed()){
			String md5 = exposer.getMd5();
			SeckillExcution excution = service.executeSeckill(seckillId, userPhone, md5);
			logger.info("result={}", excution);
		}
	}

}
