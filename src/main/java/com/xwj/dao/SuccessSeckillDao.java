package com.xwj.dao;

import org.apache.ibatis.annotations.Param;

import com.xwj.entity.SuccessSeckill;

public interface SuccessSeckillDao {

	/**
	 * ���빺����ϸ�����Թ����ظ�
	 * 
	 * @param seckillId
	 *            ���id
	 * @param userPhone
	 *            �û��ֻ���
	 * @return ���������
	 */
	int insertSuccessSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);

	/**
	 * ����seckillId��ѯ��ϸ����Я����ɱ��Ʒ����
	 * 
	 * @param seckillId
	 * @return
	 */
	SuccessSeckill queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);

}
