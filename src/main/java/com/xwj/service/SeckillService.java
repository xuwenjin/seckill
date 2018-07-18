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
	 * ��ȡ������ɱ��¼
	 * 
	 * @return
	 */
	List<Seckill> getSeckillList();

	/**
	 * ������ѯ��ɱ��¼
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);

	/**
	 * ��ɱ�����������ɱ�ӿڵ�ַ �������ϵͳʱ�����ɱʱ��
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);

	/**
	 * ִ����ɱ����
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExcution executeSeckill(long seckillId, String userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;
	
	/**
	 * ִ����ɱ����(ʹ�ô洢����)
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExcution executeSeckillProcedure(long seckillId, String userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;

}
