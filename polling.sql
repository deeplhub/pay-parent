-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.4.10-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.5116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 polling 的数据库结构
CREATE DATABASE IF NOT EXISTS `polling` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `polling`;

-- 导出  表 polling.notify_record 结构
CREATE TABLE IF NOT EXISTS `notify_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchant_no` varchar(50) NOT NULL COMMENT '商户编号',
  `merchant_order_no` varchar(50) NOT NULL COMMENT '商户订单号',
  `url` varchar(2000) NOT NULL COMMENT '通知URL',
  `status` varchar(50) NOT NULL COMMENT '100:成功 101:失败',
  `notify_times` int(11) NOT NULL COMMENT '通知次数',
  `limit_notify_times` int(11) NOT NULL COMMENT '限制通知次数',
  `notify_type` varchar(30) DEFAULT NULL COMMENT '通知类型',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ak_key_2` (`merchant_order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知记录表';

-- 正在导出表  polling.notify_record 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `notify_record` DISABLE KEYS */;
INSERT INTO `notify_record` (`id`, `merchant_no`, `merchant_order_no`, `url`, `status`, `notify_times`, `limit_notify_times`, `notify_type`, `edit_time`, `create_time`) VALUES
	(1, '1234567', '100001', 'https://www.baidu.com/?orderDate=20200613&orderNo=100001&orderPrice=0.000000&orderTime=20200613213735&payKey=asdfghjklqwertyuiopzxcvbnm&payWayCode=支付宝&tradeStatus=交易成功&trxNo=11111111&sign=FF5402962AB9E70C5EB6B6CA632F9782', '通知成功', 1, 5, '商户通知', '2020-06-17 20:59:08', '2020-06-17 20:59:08'),
	(2, '1234567', '100001', 'null?orderNo=100001&orderPrice=0.000000&orderTime=20200613213735&payKey=asdfghjklqwertyuiopzxcvbnm&payWayCode=支付宝&tradeStatus=交易成功&trxNo=11111111&sign=64E4CF6027594422BC19895778C90779', '通知成功', 1, 5, '商户通知', '2020-06-19 10:40:33', '2020-06-19 10:40:33');
/*!40000 ALTER TABLE `notify_record` ENABLE KEYS */;

-- 导出  表 polling.notify_record_log 结构
CREATE TABLE IF NOT EXISTS `notify_record_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notify_id` int(11) NOT NULL COMMENT '通知记录ID',
  `merchant_no` varchar(50) NOT NULL COMMENT '商户编号',
  `merchant_order_no` varchar(50) NOT NULL COMMENT '商户订单号',
  `request` varchar(2000) NOT NULL COMMENT '请求信息',
  `response` varchar(2000) NOT NULL COMMENT '返回信息',
  `http_status` varchar(50) NOT NULL COMMENT 'http状态',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知记录日志表';

-- 正在导出表  polling.notify_record_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `notify_record_log` DISABLE KEYS */;
INSERT INTO `notify_record_log` (`id`, `notify_id`, `merchant_no`, `merchant_order_no`, `request`, `response`, `http_status`, `edit_time`, `create_time`) VALUES
	(1, 1, '1234567', '100001', 'https://www.baidu.com/?orderDate=20200613&orderNo=100001&orderPrice=0.000000&orderTime=20200613213735&payKey=asdfghjklqwertyuiopzxcvbnm&payWayCode=支付宝&tradeStatus=交易成功&trxNo=11111111&sign=FF5402962AB9E70C5EB6B6CA632F9782', '', '200', '2020-06-17 20:59:08', '2020-06-17 20:59:08'),
	(2, 2, '1234567', '100001', 'null?orderNo=100001&orderPrice=0.000000&orderTime=20200613213735&payKey=asdfghjklqwertyuiopzxcvbnm&payWayCode=支付宝&tradeStatus=交易成功&trxNo=11111111&sign=64E4CF6027594422BC19895778C90779', '', '200', '2020-06-19 10:40:33', '2020-06-19 10:40:33');
/*!40000 ALTER TABLE `notify_record_log` ENABLE KEYS */;

-- 导出  表 polling.trade_payment_order 结构
CREATE TABLE IF NOT EXISTS `trade_payment_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `merchant_no` varchar(100) NOT NULL COMMENT '商户编号',
  `merchant_name` varchar(100) DEFAULT NULL COMMENT '商家名称',
  `merchant_order_no` varchar(30) NOT NULL COMMENT '商户订单号',
  `product_name` varchar(300) DEFAULT NULL COMMENT '商品名称',
  `order_amount` decimal(20,6) NOT NULL COMMENT '订单金额',
  `order_from` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `order_ip` varchar(50) DEFAULT NULL COMMENT '下单ip(客户端ip,在网关页面获取)',
  `order_referer_url` varchar(100) DEFAULT NULL COMMENT '下单页面，从哪个页面链接过来的(可用于防诈骗)',
  `return_url` varchar(100) DEFAULT NULL COMMENT '页面回调通知url',
  `notify_url` varchar(100) DEFAULT NULL COMMENT '后台异步通知url',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `cancel_reason` varchar(600) DEFAULT NULL COMMENT '订单撤销原因',
  `order_period` smallint(6) DEFAULT NULL COMMENT '订单有效期(单位分钟)',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  `pay_way_code` varchar(50) DEFAULT NULL COMMENT '支付方式编号',
  `pay_way_name` varchar(100) DEFAULT NULL COMMENT '支付方式名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '支付备注',
  `trx_type` varchar(30) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `trx_no` varchar(50) DEFAULT NULL COMMENT '支付流水号',
  `pay_type_code` varchar(50) DEFAULT NULL COMMENT '支付类型编号',
  `pay_type_name` varchar(100) DEFAULT NULL COMMENT '支付类型名称',
  `fund_into_type` varchar(30) DEFAULT NULL COMMENT '资金流入类型',
  `is_refund` varchar(30) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` int(11) DEFAULT NULL COMMENT '退款次数',
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='支付订单表';

-- 正在导出表  polling.trade_payment_order 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `trade_payment_order` DISABLE KEYS */;
INSERT INTO `trade_payment_order` (`id`, `merchant_no`, `merchant_name`, `merchant_order_no`, `product_name`, `order_amount`, `order_from`, `order_time`, `order_ip`, `order_referer_url`, `return_url`, `notify_url`, `status`, `cancel_reason`, `order_period`, `expire_time`, `pay_way_code`, `pay_way_name`, `remark`, `trx_type`, `trx_no`, `pay_type_code`, `pay_type_name`, `fund_into_type`, `is_refund`, `refund_times`, `success_refund_amount`, `create_time`) VALUES
	(1, '1234567', NULL, '100001', NULL, 0.000000, NULL, '2020-06-13 21:37:35', NULL, NULL, NULL, NULL, '交易成功', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '11111111', NULL, NULL, NULL, '101', 0, NULL, '2020-06-13 20:44:16');
/*!40000 ALTER TABLE `trade_payment_order` ENABLE KEYS */;

-- 导出  表 polling.trade_payment_record 结构
CREATE TABLE IF NOT EXISTS `trade_payment_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `merchant_no` varchar(50) NOT NULL COMMENT '商家编号',
  `merchant_name` varchar(300) DEFAULT NULL COMMENT '商家名称',
  `merchant_order_no` varchar(50) NOT NULL COMMENT '商户订单号',
  `product_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `order_amount` decimal(20,6) DEFAULT NULL COMMENT '订单金额',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `status` varchar(30) DEFAULT NULL COMMENT '状态',
  `pay_way_code` varchar(50) DEFAULT NULL COMMENT '支付方式编号',
  `pay_way_name` varchar(100) DEFAULT NULL COMMENT '支付方式名称',
  `pay_success_time` datetime DEFAULT NULL COMMENT '支付成功时间',
  `pay_type_name` varchar(100) DEFAULT NULL COMMENT '支付类型名称',
  `pay_type_code` varchar(50) DEFAULT NULL COMMENT '支付类型编号',
  `trx_no` varchar(50) NOT NULL COMMENT '支付流水号',
  `bank_order_no` varchar(50) DEFAULT NULL COMMENT '银行订单号',
  `bank_trx_no` varchar(50) DEFAULT NULL COMMENT '银行流水号',
  `bank_return_msg` varchar(2000) DEFAULT NULL COMMENT '银行返回信息',
  `payer_user_no` varchar(50) DEFAULT NULL COMMENT '付款人编号',
  `payer_name` varchar(60) DEFAULT NULL COMMENT '付款人名称',
  `payer_pay_amount` decimal(20,6) DEFAULT NULL COMMENT '付款方支付金额',
  `payer_fee` decimal(20,6) DEFAULT NULL COMMENT '付款方手续费',
  `payer_account_type` varchar(30) DEFAULT NULL COMMENT '付款方账户类型',
  `receiver_user_no` varchar(15) DEFAULT NULL COMMENT '收款人编号',
  `receiver_name` varchar(60) DEFAULT NULL COMMENT '收款人名称',
  `receiver_pay_amount` decimal(20,6) DEFAULT NULL COMMENT '收款方收款金额',
  `receiver_fee` decimal(20,6) DEFAULT NULL COMMENT '收款方手续费',
  `receiver_account_type` varchar(30) DEFAULT NULL COMMENT '收款方账户类型',
  `order_from` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `order_ip` varchar(30) DEFAULT NULL COMMENT '下单ip(客户端ip,从网关中获取)',
  `order_referer_url` varchar(100) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `return_url` varchar(100) DEFAULT NULL COMMENT '页面回调通知url',
  `notify_url` varchar(100) DEFAULT NULL COMMENT '后台异步通知url',
  `is_refund` varchar(30) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` int(11) DEFAULT NULL,
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `trx_type` varchar(30) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `fund_into_type` varchar(30) DEFAULT NULL COMMENT '资金流入类型',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='支付记录表';

-- 正在导出表  polling.trade_payment_record 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `trade_payment_record` DISABLE KEYS */;
INSERT INTO `trade_payment_record` (`id`, `merchant_no`, `merchant_name`, `merchant_order_no`, `product_name`, `order_amount`, `creater`, `status`, `pay_way_code`, `pay_way_name`, `pay_success_time`, `pay_type_name`, `pay_type_code`, `trx_no`, `bank_order_no`, `bank_trx_no`, `bank_return_msg`, `payer_user_no`, `payer_name`, `payer_pay_amount`, `payer_fee`, `payer_account_type`, `receiver_user_no`, `receiver_name`, `receiver_pay_amount`, `receiver_fee`, `receiver_account_type`, `order_from`, `order_ip`, `order_referer_url`, `return_url`, `notify_url`, `is_refund`, `refund_times`, `success_refund_amount`, `trx_type`, `fund_into_type`, `remark`, `complete_time`, `create_time`) VALUES
	(1, '1234567', NULL, '100001', NULL, 0.000000, NULL, '交易成功', '支付宝', NULL, '2020-06-19 10:40:32', NULL, '即时到账', '11111111', '100001', NULL, '订单交易成功', NULL, NULL, 1.000000, 1.000000, NULL, NULL, NULL, 0.000000, 0.000000, NULL, NULL, NULL, NULL, NULL, NULL, '101', 0, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `trade_payment_record` ENABLE KEYS */;

-- 导出  表 polling.user_pay_config 结构
CREATE TABLE IF NOT EXISTS `user_pay_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `status` varchar(36) NOT NULL,
  `create_time` datetime NOT NULL,
  `edit_time` datetime DEFAULT NULL,
  `audit_status` varchar(45) DEFAULT NULL,
  `is_auto_sett` varchar(36) NOT NULL DEFAULT 'no',
  `product_code` varchar(50) NOT NULL COMMENT '支付产品编号',
  `product_name` varchar(200) NOT NULL COMMENT '支付产品名称',
  `user_no` varchar(50) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `risk_day` int(11) DEFAULT NULL,
  `pay_key` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `fund_into_type` varchar(50) DEFAULT NULL,
  `pay_secret` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='支付设置表';

-- 正在导出表  polling.user_pay_config 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_pay_config` DISABLE KEYS */;
INSERT INTO `user_pay_config` (`id`, `version`, `status`, `create_time`, `edit_time`, `audit_status`, `is_auto_sett`, `product_code`, `product_name`, `user_no`, `user_name`, `risk_day`, `pay_key`, `remark`, `fund_into_type`, `pay_secret`) VALUES
	(1, 1, '1', '2020-06-13 20:48:29', '2020-06-13 20:48:23', NULL, 'no', '1111', '1111', '1234567', '支付宝', NULL, 'asdfghjklqwertyuiopzxcvbnm', NULL, NULL, 'abcdefghijklmnopqrstuvwxyz');
/*!40000 ALTER TABLE `user_pay_config` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
