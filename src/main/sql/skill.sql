CREATE TABLE seckill(
	seckill_id INT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
	NAME VARCHAR(120) NOT NULL COMMENT '商品名称',
	number INT NOT NULL COMMENT '库存量',
	start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
	end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(seckill_id),
	KEY idx_start_time(start_time),
	KEY idx_end_time(end_time),
	KEY idx_create_time(create_time)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '秒杀库存表';

INSERT INTO seckill(NAME, number, start_time, end_time) VALUES 
('1000元秒杀iphone6', 100, '2018-4-21 00:00:00', '2018-4-22 00:00:00'),
('200元秒杀红米', 200, '2018-4-21 00:00:00', '2018-4-22 00:00:00'),
('300元秒杀小米4', 300, '2018-4-21 00:00:00', '2018-4-22 00:00:00'),
('500元秒杀note', 400, '2018-4-21 00:00:00', '2018-4-22 00:00:00');

CREATE TABLE success_killed(
	seckill_id INT NOT NULL COMMENT '商品库存id',
	user_phone VARCHAR(20) NOT NULL COMMENT '用户手机号',
	state TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识: -1无效, 0成功, 1已付款',
	create_time TIMESTAMP NOT NULL COMMENT '创建时间',
	PRIMARY KEY(seckill_id, user_phone), --联合主键
	KEY idx_create_time(create_time)
)ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '秒杀成功明细表';



