/*
Navicat MySQL Data Transfer

Source Server         : self
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : lundui

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2013-05-09 22:55:52
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
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_axle
-- ----------------------------
INSERT INTO `tb_axle` VALUES ('1', '1001', '1', '2009-04-23 00:00:00', '株洲车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('2', '1001', '1', '2013-03-12 00:00:00', '长沙车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('3', '1003', '2', '2012-04-24 00:00:00', '广州车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('4', '1004', '3', '2011-04-13 00:00:00', '株洲车辆厂', '0');
INSERT INTO `tb_axle` VALUES ('5', '1005', '3', '2013-05-07 00:00:00', '长沙车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('6', '1006', '1', '2013-05-08 00:00:00', '长沙车辆厂', '1');

-- ----------------------------
-- Table structure for `tb_axle_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_axle_type`;
CREATE TABLE `tb_axle_type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `axle_num` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_axle_type
-- ----------------------------
INSERT INTO `tb_axle_type` VALUES ('1', 'LD8325');
INSERT INTO `tb_axle_type` VALUES ('2', 'LD8235');
INSERT INTO `tb_axle_type` VALUES ('3', 'LD8326');

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
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_depot
-- ----------------------------
INSERT INTO `tb_depot` VALUES ('1', '集团', '1', '0', '广州', '01');
INSERT INTO `tb_depot` VALUES ('2', '长沙车辆段', '2', '1', '长沙', '0102');
INSERT INTO `tb_depot` VALUES ('3', '广州车辆段', '2', '1', '广州', '0103');
INSERT INTO `tb_depot` VALUES ('4', '汕头车辆段', '2', '1', '汕头', '0104');
INSERT INTO `tb_depot` VALUES ('5', '肇庆车辆段', '2', '1', '肇庆', '0105');
INSERT INTO `tb_depot` VALUES ('6', '衡阳运用车间', '3', '2', '衡阳', '010206');
INSERT INTO `tb_depot` VALUES ('7', '长沙运用车间', '3', '2', '长沙', '010207');
INSERT INTO `tb_depot` VALUES ('8', '长沙检修车间', '3', '2', '长沙', '010208');
INSERT INTO `tb_depot` VALUES ('9', '怀化运用车间', '3', '2', '怀化', '010209');
INSERT INTO `tb_depot` VALUES ('10', '广州运用车间', '3', '3', '广州', '010358');
INSERT INTO `tb_depot` VALUES ('11', '石牌运用车间', '3', '3', '广州', '010311');
INSERT INTO `tb_depot` VALUES ('12', '深北运用车间', '3', '3', '深圳', '010312');
INSERT INTO `tb_depot` VALUES ('13', '广州车辆厂', '2', '1', '广州', '0113');
INSERT INTO `tb_depot` VALUES ('14', '肇庆运用车间', '3', '5', '肇庆', '010514');
INSERT INTO `tb_depot` VALUES ('15', '东莞东运用车间', '3', '4', '东莞', '010415');
INSERT INTO `tb_depot` VALUES ('16', '汕头运用车间', '3', '4', '汕头', '010416');

-- ----------------------------
-- Table structure for `tb_income`
-- ----------------------------
DROP TABLE IF EXISTS `tb_income`;
CREATE TABLE `tb_income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wheelset_id` int(11) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `reason` varchar(250) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ts_user_id` int(11) DEFAULT NULL,
  `status` smallint(2) NOT NULL DEFAULT '1' COMMENT '0表示不良好，1表示良好',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_income
-- ----------------------------
INSERT INTO `tb_income` VALUES ('1', '1', '长沙车辆厂', '2013-05-01 00:00:00', null, null, null, '1');
INSERT INTO `tb_income` VALUES ('2', '2', '长沙车辆厂', '2013-05-01 00:00:00', null, null, null, '1');
INSERT INTO `tb_income` VALUES ('3', '3', '长沙车辆段', '2013-05-03 00:00:00', '新组装', '1', null, '1');
INSERT INTO `tb_income` VALUES ('4', '4', '长沙车辆段', '2013-05-06 00:00:00', '新组装', '1', null, '1');
INSERT INTO `tb_income` VALUES ('5', '5', '长沙车辆段', '2013-05-07 00:00:00', '新组装', '1', null, '1');
INSERT INTO `tb_income` VALUES ('6', '2', '长沙车辆段', '2013-05-08 00:00:00', '收入良好', '1', null, '1');
INSERT INTO `tb_income` VALUES ('13', '2', '长沙车辆段', '2013-04-09 00:00:00', '良好', '1', null, '1');
INSERT INTO `tb_income` VALUES ('14', '3', '长沙车辆段', null, '良好', '1', null, '1');

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_jc_category
-- ----------------------------

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
  `company` varchar(100) DEFAULT NULL,
  `out_time` datetime NOT NULL,
  `xc` varchar(100) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `status` smallint(2) NOT NULL DEFAULT '1' COMMENT '0表示不良好，1表示良好',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_outgo
-- ----------------------------
INSERT INTO `tb_outgo` VALUES ('1', '1', null, '2013-05-04 00:00:00', null, '2', '0');
INSERT INTO `tb_outgo` VALUES ('2', '2', null, '2013-05-04 00:00:00', null, '1', '0');
INSERT INTO `tb_outgo` VALUES ('3', '3', null, '2013-05-06 00:00:00', null, '3', '0');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_mark` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('7', '车辆厂工人', 'CLCGR');
INSERT INTO `tb_role` VALUES ('6', '车辆段质检员', 'CLDZJY');
INSERT INTO `tb_role` VALUES ('4', '车辆段工人', 'CLDGR');
INSERT INTO `tb_role` VALUES ('5', '车辆段工长', 'CLDGZ');
INSERT INTO `tb_role` VALUES ('8', '车辆厂工长', 'CLCGZ');
INSERT INTO `tb_role` VALUES ('9', '车辆厂质检员', 'CLCZJY');
INSERT INTO `tb_role` VALUES ('10', '集团领导', 'JTLD');

-- ----------------------------
-- Table structure for `tb_temporary_repair`
-- ----------------------------
DROP TABLE IF EXISTS `tb_temporary_repair`;
CREATE TABLE `tb_temporary_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wheelset_id` int(11) NOT NULL,
  `hitch_desc` varchar(255) DEFAULT NULL,
  `find_user_id` int(11) NOT NULL COMMENT '发现人员id',
  `find_time` datetime NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '录入人员id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_temporary_repair
-- ----------------------------
INSERT INTO `tb_temporary_repair` VALUES ('1', '1', '测试测试测试', '1', '2013-05-03 00:00:00', '1');
INSERT INTO `tb_temporary_repair` VALUES ('2', '2', '天下男儿皆薄幸', '1', '2013-05-14 00:00:00', '1');
INSERT INTO `tb_temporary_repair` VALUES ('3', '2', '天下男儿皆薄幸', '1', '2013-05-03 00:00:00', '1');
INSERT INTO `tb_temporary_repair` VALUES ('4', '3', '测试', '2', '2013-05-06 00:00:00', '1');

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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '630530', 'zs', '123456', '2', '张三');
INSERT INTO `tb_user` VALUES ('2', '630531', 'ls', '123456', '5', '李四');
INSERT INTO `tb_user` VALUES ('3', '630532', 'ww', '123456', '6', '王五');

-- ----------------------------
-- Table structure for `tb_user_resources`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_resources`;
CREATE TABLE `tb_user_resources` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL DEFAULT '0',
  `resources_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_resources
-- ----------------------------
INSERT INTO `tb_user_resources` VALUES ('21', '7', '9');
INSERT INTO `tb_user_resources` VALUES ('20', '7', '6');
INSERT INTO `tb_user_resources` VALUES ('19', '7', '5');
INSERT INTO `tb_user_resources` VALUES ('18', '7', '11');
INSERT INTO `tb_user_resources` VALUES ('17', '7', '10');

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0',
  `role_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('8', '1', '7');
INSERT INTO `tb_user_role` VALUES ('7', '3', '10');

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
  `wheel_card_num` varchar(100) DEFAULT NULL,
  `axle_num` varchar(100) DEFAULT NULL,
  `axle_type` varchar(100) DEFAULT NULL,
  `wheel_type` varchar(100) DEFAULT NULL,
  `wheel_location` varchar(100) DEFAULT NULL,
  `wheel_type_id` int(20) NOT NULL DEFAULT '0',
  `factory` varchar(100) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `left_rim_thickness` int(20) DEFAULT NULL,
  `right_rim_thickness` int(20) DEFAULT NULL,
  `rim_thickness_unit` varchar(50) DEFAULT NULL,
  `left_diameter` int(20) DEFAULT NULL,
  `right_diamter` int(20) DEFAULT NULL,
  `diamter_unit` varchar(50) DEFAULT NULL,
  `left_flange_thickness` int(20) DEFAULT NULL,
  `right_flange_thickness` int(20) DEFAULT NULL,
  `flange_thickness_unit` varchar(50) DEFAULT NULL,
  `first_build_place` varchar(100) DEFAULT NULL,
  `first_build_time` date DEFAULT NULL,
  `last_build_place` varchar(100) DEFAULT NULL,
  `last_build_time` date DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `depot_id` int(20) NOT NULL,
  `jc_num` varchar(200) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wheel_record
-- ----------------------------
INSERT INTO `tb_wheel_record` VALUES ('1', '6508', '1001', 'LD8325', 'RD3', null, '1', '长沙车辆厂', '2013-03-04', '12', '13', null, '12', '13', null, '13', '13', null, null, '2013-04-15', '2013-04-25', null, '0', '2', null, null);
INSERT INTO `tb_wheel_record` VALUES ('2', '6333', '1020', 'LD8325', 'RD4', null, '1', '长沙车辆厂', '2012-04-25', '12', '12', null, '12', '12', null, '12', '12', null, null, '2012-08-23', '长沙', '2013-04-25', '1', '2', null, null);
INSERT INTO `tb_wheel_record` VALUES ('3', '6305', '1008', 'LD8220', 'RD3', null, '2', '长沙车辆厂', '2010-02-23', '12', '12', 'cm', '13', '13', 'cm', '14', '14', 'cm', null, '2012-04-11', '长沙', '2013-04-25', '0', '2', null, null);
INSERT INTO `tb_wheel_record` VALUES ('4', '6509', '1003', 'LD8315', 'RD3', null, '3', '长沙车辆厂', '2011-04-27', '13', '13', 'cm', '13', '13', 'cm', '13', '13', 'cm', '长沙', '2012-03-27', '长沙', '2013-04-08', '0', '2', null, null);
INSERT INTO `tb_wheel_record` VALUES ('5', '7702', '1001', 'LD8325', 'RD3', null, '0', '长沙车辆厂', '2013-05-06', '12', '11', 'cm', '12', '12', 'cm', '13', '13', 'cm', '长沙', '2013-05-06', '长沙', '2013-05-06', '0', '6', null, null);

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
  `max_stock` int(10) DEFAULT '0',
  `factory` varchar(100) NOT NULL,
  `applicable_models` varchar(200) DEFAULT NULL,
  `depot_id` int(20) NOT NULL,
  `bad_stock` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wheel_stock
-- ----------------------------
INSERT INTO `tb_wheel_stock` VALUES ('1', 'LD8325', 'RD3', '4', '4', '0', '0', '100', '长沙车辆厂', null, '2', '0');
INSERT INTO `tb_wheel_stock` VALUES ('2', 'LD8325', 'RD4', '3', '3', '0', '0', '100', '长沙车辆段', null, '2', '0');

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
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_resources
-- ----------------------------
INSERT INTO `td_resources` VALUES ('1', '运用单位轮对管理', '', '0', '/1');
INSERT INTO `td_resources` VALUES ('2', '检修单位轮对管理', '', '0', '/2');
INSERT INTO `td_resources` VALUES ('3', '集团领导查询', '', '0', '/3');
INSERT INTO `td_resources` VALUES ('4', '系统管理', '', '0', '/4');
INSERT INTO `td_resources` VALUES ('5', '轮对库存', 'inout/stockList.jsp', '1', '/1/5');
INSERT INTO `td_resources` VALUES ('6', '轮对履历', 'inout/recordList.jsp', '1', '/1/6');
INSERT INTO `td_resources` VALUES ('7', '备用轮对', 'inout/reserveLundui.jsp', '1', '/1/7');
INSERT INTO `td_resources` VALUES ('8', '轮对收入', '', '1', '/1/8');
INSERT INTO `td_resources` VALUES ('9', '轮对支出', '', '1', '/1/9');
INSERT INTO `td_resources` VALUES ('10', '轮对临修', '', '1', '/1/10');
INSERT INTO `td_resources` VALUES ('11', '轮对车轴', '', '1', '/1/11');
