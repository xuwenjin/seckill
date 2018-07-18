package com.xwj.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xwj.entity.Seckill;

public interface SeckillDao {

	/**
	 * �����
	 * 
	 * @param seckillId
	 *            ���id
	 * @param killTime
	 *            ��ɱʱ��
	 * @return Ӱ�������
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

	/**
	 * ����id��ѯ��ɱ����
	 * 
	 * @param seckillId
	 *            ���id
	 * @return
	 */
	Seckill queryById(long seckillId);

	/**
	 * ����ƫ������ѯ��ɱ��Ʒ�б�
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	void killByProcedure(Map<String, Object> paramMap);

}
