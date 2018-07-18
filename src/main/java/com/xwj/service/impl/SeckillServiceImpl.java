package com.xwj.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.xwj.dao.SeckillDao;
import com.xwj.dao.SuccessSeckillDao;
import com.xwj.dao.cache.RedisDao;
import com.xwj.entity.Seckill;
import com.xwj.entity.SuccessSeckill;
import com.xwj.enums.SeckillStatEnum;
import com.xwj.exception.RepeatKillException;
import com.xwj.exception.SeckillCloseException;
import com.xwj.exception.SeckillException;
import com.xwj.service.SeckillService;
import com.xwj.vo.Exposer;
import com.xwj.vo.SeckillExcution;

/**
 * 秒杀接口实现类
 * 
 * @author xuwenjin
 */
@Service
public class SeckillServiceImpl implements SeckillService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private SeckillDao seckillDao;

	@Resource
	private SuccessSeckillDao successSeckillDao;

	@Resource
	private RedisDao redisDao;

	/**
	 * md5盐值，用来混淆md5
	 */
	private static final String slat = "sdfkjsljf23942h1*&(*&#Q@H";

	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		// 超时维护缓存一致性
		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill == null) {
			seckill = seckillDao.queryById(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			}
			redisDao.putSeckill(seckill);
		}
		Date start = seckill.getStartTime();
		Date end = seckill.getEndTime();
		// 当前系统时间
		Date now = new Date();
		if (start.getTime() > now.getTime() || end.getTime() < now.getTime()) {
			return new Exposer(false, seckillId, now.getTime(), start.getTime(), end.getTime());
		}
		String md5 = getMd5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	protected String getMd5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Override
	@Transactional
	public SeckillExcution executeSeckill(long seckillId, String userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException {
		if (md5 == null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillException(SeckillStatEnum.DATA_REWRITE.getStateInfo());
		}
		Date nowTime = new Date();
		try {
			// 改库存
			int number = seckillDao.reduceNumber(seckillId, nowTime);
			if (number <= 0) {
				throw new SeckillCloseException("秒杀结束");
			} else {
				// 记录购买明细
				int insertCount = successSeckillDao.insertSuccessSeckill(seckillId, userPhone);
				if (insertCount <= 0) {
					throw new RepeatKillException(SeckillStatEnum.REPEAT_KILL.getStateInfo());
				} else {
					// 秒杀成功
					SuccessSeckill successKill = successSeckillDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExcution(seckillId, SeckillStatEnum.SUCCESS, successKill);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SeckillException(e.getMessage());
		}
	}

	@Override
	public SeckillExcution executeSeckillProcedure(long seckillId, String userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException {
		if (md5 == null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillException(SeckillStatEnum.DATA_REWRITE.getStateInfo());
		}
		Date killTime = new Date();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("seckillId", seckillId);
		paramMap.put("phone", userPhone);
		paramMap.put("killTime", killTime);
		paramMap.put("result", null);
		try {
			seckillDao.killByProcedure(paramMap);
			int result = (Integer) paramMap.get("result");
			if (result == 1) { // 秒杀成功
				SuccessSeckill successKill = successSeckillDao.queryByIdWithSeckill(seckillId, userPhone);
				return new SeckillExcution(seckillId, SeckillStatEnum.SUCCESS, successKill);
			} else {
				return new SeckillExcution(seckillId, SeckillStatEnum.stateOf(result));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new SeckillExcution(seckillId, SeckillStatEnum.INNER_ERROR);
		}
	}

}
