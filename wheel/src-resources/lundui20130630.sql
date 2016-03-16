/*
Navicat MySQL Data Transfer

Source Server         : self
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : lundui

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2013-06-30 23:20:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_axle`
-- ----------------------------
DROP TABLE IF EXISTS `tb_axle`;
CREATE TABLE `tb_axle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `axle_num` varchar(200) NOT NULL,
  `axle_type_id` int(11) NOT NULL,
  `make_date` datetime NOT NULL,
  `make_company` varchar(100) NOT NULL,
  `status` smallint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_axle
-- ----------------------------
INSERT INTO `tb_axle` VALUES ('1', '1001', '1', '2009-04-23 00:00:00', '株洲车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('2', '1001', '1', '2013-03-12 00:00:00', '长沙车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('3', '1003', '2', '2012-04-24 00:00:00', '广州车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('4', '1004', '3', '2011-04-13 00:00:00', '株洲车辆厂', '0');
INSERT INTO `tb_axle` VALUES ('5', '1005', '3', '2013-05-07 00:00:00', '长沙车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('6', '1006', '1', '2013-05-08 00:00:00', '长沙车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('7', '1007', '5', '2013-06-01 14:42:58', '长沙车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('8', '1008', '6', '2013-06-03 14:43:40', '长沙车辆厂', '1');

-- ----------------------------
-- Table structure for `tb_axle_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_axle_type`;
CREATE TABLE `tb_axle_type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `axle_num` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_axle_type
-- ----------------------------
INSERT INTO `tb_axle_type` VALUES ('1', 'ZX2025');
INSERT INTO `tb_axle_type` VALUES ('2', 'ZX1208');
INSERT INTO `tb_axle_type` VALUES ('3', 'ZX2306');
INSERT INTO `tb_axle_type` VALUES ('4', 'ZX2258');
INSERT INTO `tb_axle_type` VALUES ('5', 'ZX2679');
INSERT INTO `tb_axle_type` VALUES ('6', 'ZX3306');

-- ----------------------------
-- Table structure for `tb_depot`
-- ----------------------------
DROP TABLE IF EXISTS `tb_depot`;
CREATE TABLE `tb_depot` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `depot_name` varchar(100) NOT NULL,
  `depot_type` int(2) NOT NULL,
  `parent_id` int(20) NOT NULL DEFAULT '0',
  `depot_location` varchar(20) DEFAULT NULL,
  `depot_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_depot
-- ----------------------------
INSERT INTO `tb_depot` VALUES ('1', '广铁集团', '1', '0', '广州', '01');
INSERT INTO `tb_depot` VALUES ('2', '长沙车辆段', '2', '1', '长沙', '0102');
INSERT INTO `tb_depot` VALUES ('3', '广州车辆段', '2', '1', '广州', '0103');
INSERT INTO `tb_depot` VALUES ('4', '汕头车辆段', '2', '1', '汕头', '0104');
INSERT INTO `tb_depot` VALUES ('5', '肇庆车辆段', '2', '1', '肇庆', '0105');
INSERT INTO `tb_depot` VALUES ('6', '衡阳运用车间', '3', '2', '衡阳', '010206');
INSERT INTO `tb_depot` VALUES ('7', '长沙运用车间', '3', '2', '长沙', '010207');
INSERT INTO `tb_depot` VALUES ('8', '长沙检修车间', '4', '2', '长沙', '010208');
INSERT INTO `tb_depot` VALUES ('9', '怀化运用车间', '3', '2', '怀化', '010209');
INSERT INTO `tb_depot` VALUES ('10', '广州运用车间', '3', '3', '广州', '010358');
INSERT INTO `tb_depot` VALUES ('11', '石牌运用车间', '3', '3', '广州', '010311');
INSERT INTO `tb_depot` VALUES ('12', '深北运用车间', '3', '3', '深圳', '010312');
INSERT INTO `tb_depot` VALUES ('13', '广州车辆厂', '2', '1', '广州', '0113');
INSERT INTO `tb_depot` VALUES ('14', '肇庆运用车间', '3', '5', '肇庆', '010514');
INSERT INTO `tb_depot` VALUES ('15', '东莞东运用车间', '3', '4', '东莞', '010415');
INSERT INTO `tb_depot` VALUES ('16', '汕头运用车间', '3', '4', '汕头', '010416');

-- ----------------------------
-- Table structure for `tb_depot_repair`
-- ----------------------------
DROP TABLE IF EXISTS `tb_depot_repair`;
CREATE TABLE `tb_depot_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jc_num` varchar(20) NOT NULL,
  `depot_code` varchar(100) DEFAULT NULL,
  `depot_name` varchar(100) DEFAULT NULL,
  `src_record_1` varchar(11) DEFAULT NULL,
  `src_record_2` varchar(11) DEFAULT NULL,
  `src_record_3` varchar(11) DEFAULT NULL,
  `src_record_4` varchar(11) DEFAULT NULL,
  `dest_recrord_1` varchar(11) DEFAULT NULL,
  `dest_recrord_2` varchar(11) DEFAULT NULL,
  `dest_recrord_3` varchar(11) DEFAULT NULL,
  `dest_recrord_4` varchar(11) DEFAULT NULL,
  `repair_date` date DEFAULT NULL,
  `handler_user_num` int(20) DEFAULT NULL,
  `handler_user_name` varchar(100) DEFAULT NULL,
  `comfirm_user_num` int(20) DEFAULT NULL,
  `comfirm_user_name` varchar(100) DEFAULT NULL,
  `jc_num_rep` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_depot_repair
-- ----------------------------
INSERT INTO `tb_depot_repair` VALUES ('1', '3201', null, null, null, null, null, null, null, null, null, null, '2013-06-30', null, null, null, null, 'SS3B');

-- ----------------------------
-- Table structure for `tb_income`
-- ----------------------------
DROP TABLE IF EXISTS `tb_income`;
CREATE TABLE `tb_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wheelset_id` int(11) DEFAULT NULL,
  `depot_name` varchar(20) DEFAULT NULL,
  `depot_code` varchar(20) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `reason` tinyint(2) DEFAULT NULL,
  `user_num` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_income
-- ----------------------------
INSERT INTO `tb_income` VALUES ('1', '1', '衡阳运用车间', '010206', '2013-05-21 00:00:00', '1', '10001', '测试1');
INSERT INTO `tb_income` VALUES ('2', '2', '衡阳运用车间', '010206', '2013-05-21 00:00:00', '1', '10001', '测试1');
INSERT INTO `tb_income` VALUES ('3', '3', '衡阳运用车间', '010206', '2013-05-21 00:00:00', '1', '10001', '测试1');
INSERT INTO `tb_income` VALUES ('4', '1', '衡阳运用车间', '010206', '2013-05-21 00:00:00', '3', '10001', '测试1');
INSERT INTO `tb_income` VALUES ('5', '2', '车辆处', '01', null, '1', '0010', '管理员');
INSERT INTO `tb_income` VALUES ('7', '5', '车辆处', '01', '2013-06-08 00:00:00', '1', '0010', '管理员');
INSERT INTO `tb_income` VALUES ('11', '2', '车辆处', '01', '2013-06-18 00:00:00', '1', '0010', '管理员');
INSERT INTO `tb_income` VALUES ('12', '15', '衡阳运用车间', '010206', '2013-06-30 00:00:00', null, '0004', null);
INSERT INTO `tb_income` VALUES ('13', '16', '衡阳运用车间', '010206', '2013-06-30 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('14', '17', '衡阳运用车间', '010206', '2013-07-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('15', '3', '衡阳运用车间', '010206', '2013-06-30 00:00:00', '3', '0004', '李四');
INSERT INTO `tb_income` VALUES ('16', '18', '衡阳运用车间', '010206', '2013-06-30 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('17', '19', '衡阳运用车间', '010206', '2013-02-25 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('18', '20', '衡阳运用车间', '010206', '2013-05-09 00:00:00', '1', '0004', '李四');

-- ----------------------------
-- Table structure for `tb_jc`
-- ----------------------------
DROP TABLE IF EXISTS `tb_jc`;
CREATE TABLE `tb_jc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jc_num` varchar(100) NOT NULL,
  `area` varchar(100) NOT NULL COMMENT '标识机车所属单位：长沙车辆段、广州车辆厂',
  `jc_type_id` int(11) NOT NULL COMMENT '关联机车的类型',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_jc
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_jc_category`
-- ----------------------------
DROP TABLE IF EXISTS `tb_jc_category`;
CREATE TABLE `tb_jc_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '如：内燃车、电力车、和谐车等',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_jc_category
-- ----------------------------
INSERT INTO `tb_jc_category` VALUES ('1', '内燃车');
INSERT INTO `tb_jc_category` VALUES ('2', '电力车');
INSERT INTO `tb_jc_category` VALUES ('3', '和谐车');

-- ----------------------------
-- Table structure for `tb_jc_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_jc_type`;
CREATE TABLE `tb_jc_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '如：DF4、DF4D、DF7、DF7D、SS3、SS3B等',
  `category_id` int(11) NOT NULL COMMENT '机车类别id，关联机车类别',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_jc_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_outgo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_outgo`;
CREATE TABLE `tb_outgo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wheelset_id` int(11) NOT NULL,
  `depot_code` varchar(20) DEFAULT NULL,
  `out_time` datetime NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_num` varchar(20) NOT NULL,
  `target_unit` varchar(20) DEFAULT NULL,
  `reason` smallint(2) NOT NULL DEFAULT '1' COMMENT '0表示不良好，1表示良好',
  `depot_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_outgo
-- ----------------------------
INSERT INTO `tb_outgo` VALUES ('1', '1', '010206', '2013-05-21 00:00:00', '', '测试1', '10001', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('2', '3', '010206', '2013-05-21 00:00:00', '临修装车', '测试1', '10001', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('3', '3', '01', '2013-06-08 00:00:00', '', '管理员', '0010', null, '1', '车辆处');
INSERT INTO `tb_outgo` VALUES ('5', '2', '01', '2013-05-28 00:00:00', '轮对报废了', '管理员', '0010', null, '3', '车辆处');
INSERT INTO `tb_outgo` VALUES ('6', '1', '01', '2013-06-18 00:00:00', '上车号:2305,上车位:16', '管理员', '0010', null, '1', '车辆处');
INSERT INTO `tb_outgo` VALUES ('7', '1', '01', '2013-06-30 00:00:00', ', ', '管理员', '0010', '01', '5', '广铁集团');
INSERT INTO `tb_outgo` VALUES ('8', '5', '01', '2013-06-30 00:00:00', '', '管理员', '0010', '010206', '2', '广铁集团');
INSERT INTO `tb_outgo` VALUES ('9', '14', '010206', '2013-07-02 00:00:00', '上车号:SS3B,上车位:2', '李四', '0004', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('10', '17', '010206', '2013-06-30 00:00:00', '接收单位:长沙检修车间', '李四', '0004', '010208', '2', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('11', '15', '010206', '2013-06-30 00:00:00', '临修装车,上车号：3201,上车位:1', '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('12', '16', '010206', '2013-06-30 00:00:00', '上车号:SS3B,上车位:1', '李四', '0004', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('13', '3', '010206', '2013-06-30 00:00:00', '上车号:3201,上车位:3', '李四', '0004', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('14', '18', '010206', '2013-06-30 00:00:00', '上车号:3201,上车位:2', '李四', '0004', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('15', '20', '010206', '2013-06-30 00:00:00', '上车号:3201,上车位:4', '李四', '0004', '01', '1', '衡阳运用车间');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_mark` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('15', '检修车间工人', 'JXCJGR');
INSERT INTO `tb_role` VALUES ('14', '车间工人', 'CJGR');
INSERT INTO `tb_role` VALUES ('13', '车辆段工人', 'CLDGR');
INSERT INTO `tb_role` VALUES ('12', '集团领导', 'JTLD');
INSERT INTO `tb_role` VALUES ('11', '管理员', 'GLY');

-- ----------------------------
-- Table structure for `tb_temporary_repair`
-- ----------------------------
DROP TABLE IF EXISTS `tb_temporary_repair`;
CREATE TABLE `tb_temporary_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wheelset_id` int(11) NOT NULL,
  `jx_num` varchar(20) DEFAULT NULL,
  `depot_name` varchar(20) DEFAULT NULL,
  `depot_code` varchar(20) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `treatment` tinyint(2) DEFAULT NULL,
  `hitch_desc` varchar(255) DEFAULT NULL,
  `out_wheelset_id` int(11) DEFAULT NULL,
  `handler_user_num` varchar(20) DEFAULT NULL,
  `handler_user_name` varchar(20) DEFAULT NULL,
  `comfirm_user_num` varchar(20) DEFAULT NULL,
  `comfirm_user_name` varchar(20) DEFAULT NULL,
  `distance` int(15) DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_temporary_repair
-- ----------------------------
INSERT INTO `tb_temporary_repair` VALUES ('1', '1', 'SS3B', '衡阳运用车间', '010206', '10', '1', '轮对出现裂缝', '3', '10001', '测试1', '630532', '王五', '80000', '2013-05-21 00:00:00');
INSERT INTO `tb_temporary_repair` VALUES ('2', '3', '3201', '衡阳运用车间', '010206', '1', '1', '出问题了，测试测试', '15', '0004', '李四', '0004', '李四', null, '2013-06-30 00:00:00');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `job_number` varchar(100) NOT NULL,
  `longin_name` varchar(100) NOT NULL,
  `login_pwd` varchar(100) NOT NULL,
  `depot_id` int(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('12', '0005', 'ww', '123456', '8', '王五');
INSERT INTO `tb_user` VALUES ('11', '0004', 'ls', '123456', '6', '李四');
INSERT INTO `tb_user` VALUES ('10', '0002', 'zs', '123456', '2', '张三');
INSERT INTO `tb_user` VALUES ('5', '0010', 'admin', '123456', '1', '管理员');
INSERT INTO `tb_user` VALUES ('9', '0001', 'ld', '123456', '1', '集团临到');

-- ----------------------------
-- Table structure for `tb_user_resources`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_resources`;
CREATE TABLE `tb_user_resources` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL DEFAULT '0',
  `operator` tinyint(2) DEFAULT '0',
  `resources_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_resources
-- ----------------------------
INSERT INTO `tb_user_resources` VALUES ('98', '7', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('97', '7', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('96', '7', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('95', '7', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('124', '11', '1', '14');
INSERT INTO `tb_user_resources` VALUES ('123', '11', '1', '13');
INSERT INTO `tb_user_resources` VALUES ('122', '11', '1', '12');
INSERT INTO `tb_user_resources` VALUES ('121', '11', '1', '11');
INSERT INTO `tb_user_resources` VALUES ('120', '11', '0', '6');
INSERT INTO `tb_user_resources` VALUES ('119', '11', '0', '5');
INSERT INTO `tb_user_resources` VALUES ('118', '11', '0', '4');
INSERT INTO `tb_user_resources` VALUES ('117', '11', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('116', '11', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('115', '11', '0', '15');
INSERT INTO `tb_user_resources` VALUES ('114', '11', '1', '9');
INSERT INTO `tb_user_resources` VALUES ('99', '7', '1', '6');
INSERT INTO `tb_user_resources` VALUES ('113', '11', '1', '8');
INSERT INTO `tb_user_resources` VALUES ('112', '4', '1', '8');
INSERT INTO `tb_user_resources` VALUES ('125', '12', '1', '8');
INSERT INTO `tb_user_resources` VALUES ('126', '12', '1', '9');
INSERT INTO `tb_user_resources` VALUES ('127', '13', '0', '15');
INSERT INTO `tb_user_resources` VALUES ('128', '13', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('129', '13', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('130', '13', '0', '4');
INSERT INTO `tb_user_resources` VALUES ('131', '13', '0', '5');
INSERT INTO `tb_user_resources` VALUES ('132', '13', '0', '6');
INSERT INTO `tb_user_resources` VALUES ('133', '13', '1', '11');
INSERT INTO `tb_user_resources` VALUES ('134', '13', '1', '12');
INSERT INTO `tb_user_resources` VALUES ('135', '13', '1', '13');
INSERT INTO `tb_user_resources` VALUES ('136', '13', '1', '14');
INSERT INTO `tb_user_resources` VALUES ('137', '14', '1', '15');
INSERT INTO `tb_user_resources` VALUES ('138', '14', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('139', '14', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('140', '14', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('141', '14', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('142', '14', '1', '6');
INSERT INTO `tb_user_resources` VALUES ('143', '15', '1', '15');
INSERT INTO `tb_user_resources` VALUES ('144', '15', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('145', '15', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('146', '15', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('147', '15', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('148', '15', '1', '6');

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0',
  `role_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('11', '1', '4');
INSERT INTO `tb_user_role` VALUES ('7', '3', '10');
INSERT INTO `tb_user_role` VALUES ('9', '5', '11');
INSERT INTO `tb_user_role` VALUES ('10', '7', '7');
INSERT INTO `tb_user_role` VALUES ('12', '9', '12');
INSERT INTO `tb_user_role` VALUES ('13', '10', '13');
INSERT INTO `tb_user_role` VALUES ('14', '11', '14');
INSERT INTO `tb_user_role` VALUES ('15', '12', '15');

-- ----------------------------
-- Table structure for `tb_wheel_recent_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wheel_recent_record`;
CREATE TABLE `tb_wheel_recent_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `axle_num` varchar(100) NOT NULL,
  `axle_type` varchar(100) NOT NULL,
  `wheel_rec_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wheel_recent_record
-- ----------------------------
INSERT INTO `tb_wheel_recent_record` VALUES ('1', '1001', 'LD8325', '1');
INSERT INTO `tb_wheel_recent_record` VALUES ('2', '1020', 'LD8325', '2');
INSERT INTO `tb_wheel_recent_record` VALUES ('3', '1001', 'LD8325', '3');
INSERT INTO `tb_wheel_recent_record` VALUES ('4', '1001', 'LD8325', '4');
INSERT INTO `tb_wheel_recent_record` VALUES ('5', '1001', 'LD8325', '5');

-- ----------------------------
-- Table structure for `tb_wheel_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wheel_record`;
CREATE TABLE `tb_wheel_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `axle_num` varchar(100) DEFAULT NULL,
  `axle_type` varchar(100) DEFAULT NULL,
  `wheel_type` varchar(100) DEFAULT NULL,
  `axle_box_type` varchar(100) DEFAULT NULL,
  `has_anti_skid` tinyint(2) DEFAULT NULL,
  `has_axle_box_relay` tinyint(2) DEFAULT NULL,
  `anti_skid_size` varchar(20) DEFAULT NULL,
  `anti_skid_location` varchar(10) DEFAULT NULL,
  `wheel_location` varchar(100) DEFAULT NULL,
  `factory` varchar(100) DEFAULT NULL,
  `box_create_date` date DEFAULT NULL,
  `wheel_create_date` date DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `left_rim_thickness` double(10,2) DEFAULT NULL,
  `right_rim_thickness` double(10,2) DEFAULT NULL,
  `left_circular_wear` double(10,2) DEFAULT NULL,
  `right_diamter` double(10,2) DEFAULT NULL,
  `left_flange_thickness` double(10,2) DEFAULT NULL,
  `right_flange_thickness` double(10,2) DEFAULT NULL,
  `left_brake_disc_wear` double(10,2) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `whereabouts` tinyint(2) NOT NULL,
  `jc_num` varchar(200) DEFAULT NULL,
  `depot_code` varchar(20) DEFAULT NULL,
  `depot_name` varchar(20) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `income_id` int(11) DEFAULT NULL,
  `outlay_id` int(11) DEFAULT NULL,
  `right_circular_wear` double(10,2) DEFAULT NULL,
  `left_diameter` double(10,2) DEFAULT NULL,
  `right_brake_disc_wear` double(10,2) DEFAULT NULL,
  `inside_istance` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wheel_record
-- ----------------------------
INSERT INTO `tb_wheel_record` VALUES ('1', '00837', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '株洲车辆厂', null, null, '2013-05-21', '100.00', '102.00', null, '52.00', '120.00', '120.00', null, '2', '3', null, '01', '车辆处', null, null, null, null, '50.00', null, '45.00');
INSERT INTO `tb_wheel_record` VALUES ('2', '02295', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '广州车辆厂', null, null, '2012-05-07', '40.00', '40.00', '6.00', '100.00', '12.00', '12.00', '5.00', '0', '0', null, '01', '车辆处', null, null, null, '7.00', '100.00', '6.00', '130.00');
INSERT INTO `tb_wheel_record` VALUES ('3', '05385', 'LD8326', 'RD4', 'NUK', '1', '0', '80尺', '左边', null, '株洲车辆厂', null, null, '2012-05-01', '40.00', '4.00', null, '120.00', '10.00', '10.00', null, '1', '1', '3201', '010206', '衡阳运用车间', '3', null, null, null, '120.00', null, '200.00');
INSERT INTO `tb_wheel_record` VALUES ('4', '12369', 'LD2102', 'RD3', null, '1', '0', '80尺', '右边', null, '长沙车辆段', null, null, '2013-02-15', '20.00', '5.00', null, '100.00', '10.00', '10.00', null, '0', '0', null, '0103', '长沙车辆段', null, null, null, null, '100.00', null, '210.00');
INSERT INTO `tb_wheel_record` VALUES ('5', '20923', 'LD9015', 'RD4A', 'NUK', '1', '0', '80尺', '左边', null, '长沙车辆厂', null, null, '2013-05-19', '100.00', '20.00', null, '10.00', '130.00', '10.00', null, '0', '2', null, '01', '车辆处', null, null, null, null, '120.00', null, '140.00');
INSERT INTO `tb_wheel_record` VALUES ('6', '22274', 'ZX0001', 'RD3', null, '1', '0', '100', null, null, '长沙车辆厂', null, null, '2013-05-23', '100.00', '20.00', null, '20.00', '100.00', '10.00', null, '0', '0', null, '0103', '长沙车辆段', null, null, null, null, '100.00', null, '130.00');
INSERT INTO `tb_wheel_record` VALUES ('7', '26508', 'ZX3725', 'RD4', 'NUK', '1', '0', '90尺', '左边', null, '长沙车辆厂', null, null, '2013-05-25', '110.00', '30.00', '5.00', '30.00', '102.00', '25.00', '6.00', '0', '0', null, '0103', '长沙车辆段', null, null, null, '3.00', '120.00', '5.00', '125.00');
INSERT INTO `tb_wheel_record` VALUES ('8', '26903', 'ZX3301', 'RD3', 'NUK', '1', '0', '90尺', '右边', null, '广州车辆厂', null, null, '2013-05-25', '100.00', '25.00', '3.00', '20.00', '102.00', '10.00', '5.00', '0', '0', null, '0103', '长沙车辆段', null, null, null, '6.00', '122.00', '6.00', '125.00');
INSERT INTO `tb_wheel_record` VALUES ('9', '28372', 'ZX2505', 'RD4', 'NUK', '1', '0', '80尺', '左边', null, '广州车辆厂', null, null, '2013-05-25', '105.00', '30.00', '5.00', '22.00', '108.00', '10.00', '6.00', '0', '0', null, '01', '车辆处', null, null, null, '6.00', '126.00', '5.00', '130.00');
INSERT INTO `tb_wheel_record` VALUES ('10', '3308', 'ZX2505', 'RD4', 'NUK', '1', '0', '80尺', '左边', null, '广州车辆厂', null, null, '2013-05-25', '110.00', '30.00', '4.00', '20.00', '110.00', '10.00', '7.00', '0', '0', null, '01', '车辆处', null, null, null, '7.00', '125.00', '7.00', '126.00');
INSERT INTO `tb_wheel_record` VALUES ('11', '3425', 'ZX3301', 'RD3', 'NUK', '1', '0', '90尺', '右边', null, '长沙车辆厂', null, null, '2013-05-25', '120.00', '25.00', '5.00', '20.00', '122.00', '10.00', '5.00', '0', '0', null, '0103', '长沙车辆段', null, null, null, '6.00', '120.00', '6.00', '120.00');
INSERT INTO `tb_wheel_record` VALUES ('12', '5258', 'ZX6678', 'RD4A', 'NUK', '1', '0', '80尺', '左边', null, '长沙车辆厂', null, null, '2013-05-26', '125.00', '20.00', '5.00', '20.00', '125.00', '15.00', '5.00', '0', '0', null, '0103', '长沙车辆段', null, null, null, '3.00', '100.00', '3.00', '100.00');
INSERT INTO `tb_wheel_record` VALUES ('13', '1002', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '长沙车辆厂', null, null, '2013-05-21', '50.00', '50.00', '12.00', '12.00', '12.00', '12.00', '12.00', '0', '0', null, '01', '广铁集团', null, null, null, '2.00', '112.00', '12.00', '12.00');
INSERT INTO `tb_wheel_record` VALUES ('14', '1044', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '株洲车辆厂', '2013-06-02', '2013-06-03', '2012-06-11', '30.00', '30.00', '20.00', '10.00', '10.00', '10.00', '12.00', '0', '1', 'SS3B', '010206', '衡阳运用车间', '2', null, null, '20.00', '10.00', '12.00', '12.00');
INSERT INTO `tb_wheel_record` VALUES ('15', '2345', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '株洲车辆厂', '2013-04-29', '2013-05-29', '2013-04-23', '12.00', '12.00', '12.00', '12.00', '12.00', '12.00', '12.00', '0', '1', '3201', '010206', '衡阳运用车间', '1', null, null, '12.00', '12.00', '12.00', '12.00');
INSERT INTO `tb_wheel_record` VALUES ('16', '1234', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '广州车辆厂', '2013-06-06', '2013-06-04', '2013-04-09', '12.00', '12.00', '12.00', '12.00', '12.00', '12.00', '12.00', '0', '1', 'SS3B', '010206', '衡阳运用车间', '1', null, null, '12.00', '12.00', '12.00', '12.00');
INSERT INTO `tb_wheel_record` VALUES ('17', '1235', 'LD8325', 'RD3A', 'NUK', '0', '0', '80尺', '左边', null, '广州车辆厂', '2013-06-10', '2013-06-10', '2013-04-23', '12.00', '12.00', '12.00', '12.00', '12.00', '12.00', '12.00', '0', '2', null, '010206', '衡阳运用车间', null, null, null, '12.00', '12.00', '12.00', '12.00');
INSERT INTO `tb_wheel_record` VALUES ('18', '02512', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '长沙车辆厂', '2013-01-03', '2013-01-05', '2013-01-15', '10.00', '10.00', '5.00', '25.00', '100.00', '100.00', '100.00', '0', '1', '3201', '010206', '衡阳运用车间', '2', null, null, '5.00', '25.00', '102.00', '20.00');
INSERT INTO `tb_wheel_record` VALUES ('19', '10275', 'ZX2025', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '长沙车辆厂', '2012-01-26', '2012-02-20', '2012-03-06', '10.00', '10.00', '2.00', '10.00', '100.00', '100.00', '120.00', '0', '0', null, '010206', '衡阳运用车间', null, null, null, '3.00', '10.00', '120.00', '12.00');
INSERT INTO `tb_wheel_record` VALUES ('20', '11304', 'ZX2025', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '广州车辆厂', '2013-01-17', '2013-01-23', '2013-02-01', '10.00', '10.00', '5.00', '12.00', '100.00', '120.00', '120.00', '0', '1', '3201', '010206', '衡阳运用车间', '4', null, null, '6.00', '10.00', '120.00', '10.00');

-- ----------------------------
-- Table structure for `tb_wheel_stock`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wheel_stock`;
CREATE TABLE `tb_wheel_stock` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `axle_type` varchar(100) DEFAULT NULL,
  `wheel_type` varchar(100) DEFAULT NULL,
  `inventory` int(10) NOT NULL DEFAULT '0',
  `good_num` int(10) NOT NULL DEFAULT '0',
  `not_good_num` int(10) NOT NULL DEFAULT '0',
  `min_stock` int(10) DEFAULT '0',
  `zc_num` int(10) DEFAULT '0',
  `sx_num` int(10) DEFAULT '0',
  `bad_stock` int(10) DEFAULT '0',
  `depot_code` varchar(100) NOT NULL,
  `depot_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wheel_stock
-- ----------------------------
INSERT INTO `tb_wheel_stock` VALUES ('1', 'LD8325', 'RD3A', '3', '0', '1', '10', '4', '1', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('2', 'LD8326', 'RD4', '0', '0', '0', '2', '1', '0', '5', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('3', 'LD8325', 'RD3A', '3', '4', '-1', '12', '1', '1', '0', '01', '车辆处');
INSERT INTO `tb_wheel_stock` VALUES ('4', 'LD8326', 'RD4', '0', '0', '0', '20', '1', '0', '0', '01', '车辆处');
INSERT INTO `tb_wheel_stock` VALUES ('5', 'LD8325', 'RD4A', '0', '0', '0', '5', '0', '0', '10', '0102', '长沙车辆段');
INSERT INTO `tb_wheel_stock` VALUES ('6', 'LD9015', 'RD4A', '0', '0', '0', '5', '0', '1', '0', '01', '车辆处');
INSERT INTO `tb_wheel_stock` VALUES ('7', 'ZX2025', 'RD3A', '1', '1', '0', '0', '1', '0', '0', '010206', '衡阳运用车间');

-- ----------------------------
-- Table structure for `tb_wheel_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wheel_type`;
CREATE TABLE `tb_wheel_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wheel_num` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wheel_type
-- ----------------------------
INSERT INTO `tb_wheel_type` VALUES ('1', 'RD3A');
INSERT INTO `tb_wheel_type` VALUES ('2', 'RD3');
INSERT INTO `tb_wheel_type` VALUES ('3', 'RD4');
INSERT INTO `tb_wheel_type` VALUES ('4', 'RD4A');
INSERT INTO `tb_wheel_type` VALUES ('5', 'RD3A1');
INSERT INTO `tb_wheel_type` VALUES ('6', 'RC4');
INSERT INTO `tb_wheel_type` VALUES ('7', 'AM96');

-- ----------------------------
-- Table structure for `tb_xc`
-- ----------------------------
DROP TABLE IF EXISTS `tb_xc`;
CREATE TABLE `tb_xc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '修程名' COMMENT '如：段修、段做厂修、临修、A2修等',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_xc
-- ----------------------------
INSERT INTO `tb_xc` VALUES ('1', '新组装');
INSERT INTO `tb_xc` VALUES ('2', '段修');
INSERT INTO `tb_xc` VALUES ('3', '段做厂修');
INSERT INTO `tb_xc` VALUES ('4', '临修');
INSERT INTO `tb_xc` VALUES ('5', '送修');

-- ----------------------------
-- Table structure for `tb_zx_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_zx_type`;
CREATE TABLE `tb_zx_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_zx_type
-- ----------------------------
INSERT INTO `tb_zx_type` VALUES ('1', 'NUK');
INSERT INTO `tb_zx_type` VALUES ('2', 'NUK1');
INSERT INTO `tb_zx_type` VALUES ('3', 'NUK2');
INSERT INTO `tb_zx_type` VALUES ('4', 'NJK1');
INSERT INTO `tb_zx_type` VALUES ('5', 'NJK2');
INSERT INTO `tb_zx_type` VALUES ('6', 'NUHJK');

-- ----------------------------
-- Table structure for `td_resources`
-- ----------------------------
DROP TABLE IF EXISTS `td_resources`;
CREATE TABLE `td_resources` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `resources_name` varchar(100) NOT NULL,
  `resources_url` varchar(300) NOT NULL,
  `parent_id` int(20) NOT NULL DEFAULT '0',
  `path` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_resources
-- ----------------------------
INSERT INTO `td_resources` VALUES ('1', '轮对管理', '', '0', '/1');
INSERT INTO `td_resources` VALUES ('2', '轮对履历', 'inout/wheelRecordAction!jxRecordList.do', '1', '/1/2');
INSERT INTO `td_resources` VALUES ('3', '轮对库存', 'inout/wheelStockAction!stockList.do', '1', '/1/3');
INSERT INTO `td_resources` VALUES ('4', '轮对收入', 'inout/incomeAction!incomeList.do', '1', '/1/4');
INSERT INTO `td_resources` VALUES ('5', '轮对支出', 'inout/outlayAction!outlayList.do', '1', '/1/5');
INSERT INTO `td_resources` VALUES ('6', '轮对临修', 'inout/tempRepairAction!tmpRepairList.do', '1', '/1/6');
INSERT INTO `td_resources` VALUES ('7', '查询统计', '', '0', '/7');
INSERT INTO `td_resources` VALUES ('8', '库存查看', 'lead/leadAction!getReport.do', '7', '/7/8');
INSERT INTO `td_resources` VALUES ('9', '临修故障统计', 'lead/leadAction!getTempRepair.do', '7', '/7/9');
INSERT INTO `td_resources` VALUES ('10', '系统管理', '', '0', '/10');
INSERT INTO `td_resources` VALUES ('11', '角色管理', 'system/roleAction!roleList.do', '10', '/10/11');
INSERT INTO `td_resources` VALUES ('12', '人员管理', 'system/userAction!userList.do', '10', '/10/12');
INSERT INTO `td_resources` VALUES ('13', '车间管理', 'system/depotAction!listAll.do', '10', '/10/13');
INSERT INTO `td_resources` VALUES ('14', '库存报警管理', 'system/alarmAction!getAlermList.do', '10', '/10/14');
INSERT INTO `td_resources` VALUES ('15', '轮对段修', 'inout/depotRepair!getDepotRepairList.do', '1', '/1/15');
