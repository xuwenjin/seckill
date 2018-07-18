CREATE TABLE seckill(
	seckill_id INT NOT NULL AUTO_INCREMENT COMMENT '��Ʒ���id',
	NAME VARCHAR(120) NOT NULL COMMENT '��Ʒ����',
	number INT NOT NULL COMMENT '�����',
	start_time TIMESTAMP NOT NULL COMMENT '��ɱ��ʼʱ��',
	end_time TIMESTAMP NOT NULL COMMENT '��ɱ����ʱ��',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
	PRIMARY KEY(seckill_id),
	KEY idx_start_time(start_time),
	KEY idx_end_time(end_time),
	KEY idx_create_time(create_time)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '��ɱ����';

INSERT INTO seckill(NAME, number, start_time, end_time) VALUES 
('1000Ԫ��ɱiphone6', 100, '2018-4-21 00:00:00', '2018-4-22 00:00:00'),
('200Ԫ��ɱ����', 200, '2018-4-21 00:00:00', '2018-4-22 00:00:00'),
('300Ԫ��ɱС��4', 300, '2018-4-21 00:00:00', '2018-4-22 00:00:00'),
('500Ԫ��ɱnote', 400, '2018-4-21 00:00:00', '2018-4-22 00:00:00');

CREATE TABLE success_killed(
	seckill_id INT NOT NULL COMMENT '��Ʒ���id',
	user_phone VARCHAR(20) NOT NULL COMMENT '�û��ֻ���',
	state TINYINT NOT NULL DEFAULT -1 COMMENT '״̬��ʶ: -1��Ч, 0�ɹ�, 1�Ѹ���',
	create_time TIMESTAMP NOT NULL COMMENT '����ʱ��',
	PRIMARY KEY(seckill_id, user_phone), --��������
	KEY idx_create_time(create_time)
)ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '��ɱ�ɹ���ϸ��';



