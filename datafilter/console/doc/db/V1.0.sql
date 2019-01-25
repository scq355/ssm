
-- ----------------------------
-- Table structure for filter_audit_log
-- ----------------------------
DROP TABLE IF EXISTS `filter_audit_log`;
CREATE TABLE `filter_audit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `operation` int(11) DEFAULT NULL COMMENT '0:通过，1：拒绝，2：恢复',
  `reason` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0：评论，1：投资，2：解盘，3：问答，4：商城',
  `audit_id` int(11) DEFAULT NULL COMMENT '审核表主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_comment_audit
-- ----------------------------
DROP TABLE IF EXISTS `filter_comment_audit`;
CREATE TABLE `filter_comment_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `img_urls` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `original_data` varchar(2000) DEFAULT NULL COMMENT '原始数据',
  `filter_data` varchar(2000) DEFAULT NULL COMMENT '展现出过滤词的数据',
  `status` int(11) DEFAULT NULL COMMENT '0：待审核 ,1：系统通过，2：人员通过 , 3：拒绝',
  `filter_type` int(11) DEFAULT NULL COMMENT '0：无，1：敏感词，2：非法词',
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ip` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_data_exception
-- ----------------------------
DROP TABLE IF EXISTS `filter_data_exception`;
CREATE TABLE `filter_data_exception` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` varchar(1000) DEFAULT NULL COMMENT '错误信息',
  `project_type` int(11) DEFAULT NULL COMMENT '0：评论，1：投资，2：解盘，3：问答，4：商城',
  `status` int(11) DEFAULT NULL COMMENT '0：已处理，1：未处理',
  `json` varchar(4000) DEFAULT NULL COMMENT '对象信息json',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deal_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_investment_audit
-- ----------------------------
DROP TABLE IF EXISTS `filter_investment_audit`;
CREATE TABLE `filter_investment_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `img_urls` varchar(255) DEFAULT NULL,
  `original_data` varchar(2000) DEFAULT NULL COMMENT '原始数据',
  `filter_data` varchar(2000) DEFAULT NULL COMMENT '展现出过滤词的数据',
  `data_type` int(11) DEFAULT NULL COMMENT '0：说说，1：评论',
  `status` int(11) DEFAULT NULL COMMENT '0：待审核 ,1：系统通过，2：人员通过 , 3：拒绝',
  `filter_type` int(11) DEFAULT NULL COMMENT '0：无，1：敏感词，2：非法词',
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_jiepan_audit
-- ----------------------------
DROP TABLE IF EXISTS `filter_jiepan_audit`;
CREATE TABLE `filter_jiepan_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `img_urls` varchar(255) DEFAULT NULL,
  `original_data` varchar(2000) DEFAULT NULL COMMENT '原始数据',
  `filter_data` varchar(2000) DEFAULT NULL COMMENT '展现出过滤词的数据',
  `status` int(11) DEFAULT NULL COMMENT '0：待审核 ,1：系统通过，2：人员通过 , 3：拒绝',
  `filter_type` int(11) DEFAULT NULL COMMENT '0：无，1：敏感词，2：非法词\r\n            \r\n            ',
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_market_audit
-- ----------------------------
DROP TABLE IF EXISTS `filter_market_audit`;
CREATE TABLE `filter_market_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `img_urls` varchar(255) DEFAULT NULL,
  `original_data` varchar(2000) DEFAULT NULL COMMENT '原始数据',
  `filter_data` varchar(2000) DEFAULT NULL COMMENT '展现出过滤词的数据',
  `status` int(11) DEFAULT NULL COMMENT '0：待审核 ,1：系统通过，2：人员通过 , 3：拒绝',
  `filter_type` int(11) DEFAULT NULL COMMENT '0：无，1：敏感词，2：非法词',
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_quiz_audit
-- ----------------------------
DROP TABLE IF EXISTS `filter_quiz_audit`;
CREATE TABLE `filter_quiz_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `img_urls` varchar(255) DEFAULT NULL,
  `original_data` varchar(2000) DEFAULT NULL COMMENT '原始数据',
  `filter_data` varchar(2000) DEFAULT NULL COMMENT '展现出过滤词的数据',
  `data_type` char(10) DEFAULT NULL COMMENT '0：问题，1：答案',
  `status` int(11) DEFAULT NULL COMMENT '0：待审核 ,1：系统通过，2：人员通过 , 3：拒绝',
  `filter_type` int(11) DEFAULT NULL COMMENT '0：无，1：敏感词，2：非法词',
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_admin
-- ----------------------------
CREATE TABLE `filter_admin` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `role_id` bigint(11) DEFAULT NULL COMMENT '用户角色ID',
  `last_login_ip` varchar(50) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `fail_number` int(11) DEFAULT NULL COMMENT '登录失败次数',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '用户状态: 1:激活状态，0：未激活',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_permission
-- ----------------------------
CREATE TABLE `filter_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_key` varchar(50) DEFAULT NULL,
  `permission_name` varchar(255) DEFAULT NULL,
  `sort_code` varchar(50) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_role
-- ----------------------------
CREATE TABLE `filter_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_key` varchar(50) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_role_permission
-- ----------------------------
CREATE TABLE `filter_role_permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) DEFAULT NULL,
  `permission_id` bigint(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `roleid_index` (`role_id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_source
-- ----------------------------
CREATE TABLE `filter_source` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `source_key` varchar(50) DEFAULT NULL,
  `source_name` varchar(50) DEFAULT NULL,
  `audit_rule` tinyint(4) DEFAULT '1' COMMENT '1:先发后审  2:先审后发  3.动态审核',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `updated_index` (`updated_at`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_source_timeset
-- ----------------------------
CREATE TABLE `filter_source_timeset` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `source_id` bigint(11) DEFAULT NULL,
  `start_hour` int(11) DEFAULT '0' COMMENT '单位：小时',
  `end_hour` int(11) DEFAULT '1' COMMENT '单位：小时',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `updated_index` (`updated_at`),
  KEY `sourceid_index` (`source_id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for filter_vocabulary
-- ----------------------------
CREATE TABLE `filter_vocabulary` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(50) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '1:敏感词  2:非法词',
  `delete_flag` tinyint(4) DEFAULT '0',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `updated_index` (`updated_at`),
  KEY `content_type_index` (`content`,`type`)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `filter_black_user`;
CREATE TABLE `filter_black_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `black_end_time` datetime DEFAULT NULL,
  `black_reason` varchar(100) DEFAULT NULL,
  `black_state` tinyint(4) DEFAULT NULL COMMENT '1:冻结  2:解冻',
  `black_way` tinyint(4) DEFAULT NULL COMMENT '1：账户冻结  2：IP冻结',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userid_index` (`user_id`),
  KEY `ip_index` (`ip`)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `filter_white_user`;
CREATE TABLE `filter_white_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `white_reason` varchar(255) DEFAULT NULL,
  `white_type` int(11) DEFAULT NULL,
  `delete_flag` tinyint(4) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userid_index` (`user_id`),
  KEY `ip_index` (`ip`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `sys_config` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `config_key` varchar(255) DEFAULT NULL,
  `show_name` varchar(255) DEFAULT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `filter_source` (`id`, `source_key`, `source_name`, `audit_rule`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('1', 'comment', '评论', '1', '叶青锋', '叶青锋', now(), now());
INSERT INTO `filter_source` (`id`, `source_key`, `source_name`, `audit_rule`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('2', 'investment', '投资圈', '1', '叶青锋', '叶青锋', now(), now());
INSERT INTO `filter_source` (`id`, `source_key`, `source_name`, `audit_rule`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('3', 'jiepan', '解盘', '1', '叶青锋', '叶青锋', now(), now());
INSERT INTO `filter_source` (`id`, `source_key`, `source_name`, `audit_rule`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('4', 'quiz', '问答', '1', '叶青锋', '叶青锋', now(), now());
INSERT INTO `filter_source` (`id`, `source_key`, `source_name`, `audit_rule`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('5', 'market', '商城', '1', '叶青锋', '叶青锋', now(), now());

INSERT INTO `filter_role` (`id`, `role_key`, `role_name`, `remark`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('-1', 'super_admin', '超级管理员', NULL, '系统', '系统', now(), now());

INSERT INTO `sys_config` (`config_key`, `show_name`, `config_value`, `remark`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('checkAuthCode', '是否启用谷歌身份认', '0', '0为禁用，1为启用', '系统', '系统', '2017-05-13 17:45:07', '2017-05-13 17:45:07');
INSERT INTO `sys_config` (`config_key`, `show_name`, `config_value`, `remark`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('daySubmitCountMax', '24小时最大提交量', '100', '违反冻结三天', '系统', '系统', '2017-05-13 17:45:07', '2017-05-13 17:45:07');
INSERT INTO `sys_config` (`config_key`, `show_name`, `config_value`, `remark`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('daySensitiveWordMax', '24小时最大敏感词次数', '10', '违反冻结12小时', '系统', '系统', '2017-05-13 17:45:41', '2017-05-13 17:45:41');
INSERT INTO `sys_config` (`config_key`, `show_name`, `config_value`, `remark`, `creator`, `modifier`, `created_at`, `updated_at`) VALUES ('dayIllegalWordMax', '24小时最大非法词次数', '5', '违反冻结三天', '系统', '系统', '2017-05-13 17:45:49', '2017-05-13 17:45:49');
