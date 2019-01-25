/*
Navicat MySQL Data Transfer

Source Server         : p2p-local
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : system_practice

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-19 15:01:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for task_scheduler
-- ----------------------------
DROP TABLE IF EXISTS `task_scheduler`;
CREATE TABLE `task_scheduler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(64) NOT NULL,
  `hostname` varchar(64) NOT NULL,
  `ip_address` varchar(64) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) DEFAULT '',
  `nick_name` varchar(64) DEFAULT '',
  `password` varchar(64) NOT NULL,
  `phone_number` char(11) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `ip_login` varchar(15) DEFAULT NULL,
  `date_login` date DEFAULT NULL,
  `register_time` date DEFAULT NULL,
  `avatar` varchar(1024) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_ip_login_uindex` (`ip_login`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ot_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `ot_sys_log`;
CREATE TABLE `ot_sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_type` tinyint(4) NOT NULL COMMENT '0 权限管理 1 系统设置 2 资金管理 3 用户管理 4 投资管理 5 推广运营 6 借款管理 7 内容管理',
  `name` varchar(200) NOT NULL COMMENT '权限名称',
  `uri` varchar(200) NOT NULL COMMENT '权限链接地址',
  `msg` varchar(2000) DEFAULT NULL COMMENT '操作说明',
  `admin_id` int(11) NOT NULL,
  `ip` varchar(200) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;