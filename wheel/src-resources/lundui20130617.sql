/*
Navicat MySQL Data Transfer

Source Server         : self
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : lundui

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2013-06-17 21:20:38
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
INSERT INTO `tb_axle_type` VALUES ('1', 'LD8325');
INSERT INTO `tb_axle_type` VALUES ('2', 'LD8235');
INSERT INTO `tb_axle_type` VALUES ('3', 'LD8326');
INSERT INTO `tb_axle_type` VALUES ('4', 'LD9012');
INSERT INTO `tb_axle_type` VALUES ('5', 'LD9015');
INSERT INTO `tb_axle_type` VALUES ('6', 'LD9018');

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
INSERT INTO `tb_depot` VALUES ('1', '车辆处', '1', '0', '广州', '01');
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
  `depot_name` varchar(20) DEFAULT NULL,
  `depot_code` varchar(20) DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `reason` tinyint(2) DEFAULT NULL,
  `user_num` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_income
-- ----------------------------
INSERT INTO `tb_income` VALUES ('1', '1', '衡阳运用车间', '010206', '2013-05-21 00:00:00', '1', '10001', '测试1');
INSERT INTO `tb_income` VALUES ('2', '2', '衡阳运用车间', '010206', '2013-05-21 00:00:00', '1', '10001', '测试1');
INSERT INTO `tb_income` VALUES ('3', '3', '衡阳运用车间', '010206', '2013-05-21 00:00:00', '1', '10001', '测试1');
INSERT INTO `tb_income` VALUES ('4', '1', '衡阳运用车间', '010206', '2013-05-21 00:00:00', '3', '10001', '测试1');
INSERT INTO `tb_income` VALUES ('5', '2', '车辆处', '01', null, '1', '0010', '管理员');
INSERT INTO `tb_income` VALUES ('6', '3', '车辆处', '01', '2013-05-24 00:00:00', '1', '0010', '管理员');
INSERT INTO `tb_income` VALUES ('7', '5', '车辆处', '01', '2013-06-08 00:00:00', '1', '0010', '管理员');

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
  `reason` smallint(2) NOT NULL DEFAULT '1' COMMENT '0表示不良好，1表示良好',
  `depot_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_outgo
-- ----------------------------
INSERT INTO `tb_outgo` VALUES ('1', '1', '010206', '2013-05-21 00:00:00', '', '测试1', '10001', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('2', '3', '010206', '2013-05-21 00:00:00', '临修装车', '测试1', '10001', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('3', '3', '01', '2013-06-08 00:00:00', '', '管理员', '0010', '1', '车辆处');
INSERT INTO `tb_outgo` VALUES ('5', '2', '01', '2013-05-28 00:00:00', '轮对报废了', '管理员', '0010', '3', '车辆处');

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
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_temporary_repair
-- ----------------------------
INSERT INTO `tb_temporary_repair` VALUES ('1', '1', 'SS3B', '衡阳运用车间', '010206', '10', '1', '轮对出现裂缝', '3', '10001', '测试1', '630532', '王五', '80000', '2013-05-21 00:00:00');

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
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '630530', 'zs', '123456', '2', '张三');
INSERT INTO `tb_user` VALUES ('2', '630531', 'ls', '123456', '5', '李四');
INSERT INTO `tb_user` VALUES ('3', '630532', 'ww', '123456', '6', '王五');
INSERT INTO `tb_user` VALUES ('4', '000001', 'ld', '123456', '1', '领导');
INSERT INTO `tb_user` VALUES ('5', '0010', 'admin', '123456', '1', '管理员');
INSERT INTO `tb_user` VALUES ('7', '10001', 'cs1', '123456', '6', '测试1');

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
) ENGINE=MyISAM AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_resources
-- ----------------------------
INSERT INTO `tb_user_resources` VALUES ('98', '7', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('97', '7', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('96', '7', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('95', '7', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('86', '11', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('85', '11', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('84', '11', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('87', '11', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('88', '11', '1', '6');
INSERT INTO `tb_user_resources` VALUES ('89', '11', '1', '8');
INSERT INTO `tb_user_resources` VALUES ('90', '11', '1', '9');
INSERT INTO `tb_user_resources` VALUES ('91', '11', '1', '11');
INSERT INTO `tb_user_resources` VALUES ('92', '11', '1', '12');
INSERT INTO `tb_user_resources` VALUES ('93', '11', '1', '13');
INSERT INTO `tb_user_resources` VALUES ('94', '11', '1', '14');
INSERT INTO `tb_user_resources` VALUES ('99', '7', '1', '6');

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0',
  `role_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('8', '1', '7');
INSERT INTO `tb_user_role` VALUES ('7', '3', '10');
INSERT INTO `tb_user_role` VALUES ('9', '5', '11');
INSERT INTO `tb_user_role` VALUES ('10', '7', '7');

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
  `create_date` date DEFAULT NULL,
  `left_rim_thickness` double(10,2) DEFAULT NULL,
  `right_rim_thickness` double(10,2) DEFAULT NULL,
  `left_diameter` double(10,2) DEFAULT NULL,
  `right_diamter` double(10,2) DEFAULT NULL,
  `left_flange_thickness` double(10,2) DEFAULT NULL,
  `right_flange_thickness` double(10,2) DEFAULT NULL,
  `inside_istance` double(10,2) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `whereabouts` tinyint(2) NOT NULL,
  `jc_num` varchar(200) DEFAULT NULL,
  `depot_code` varchar(20) DEFAULT NULL,
  `depot_name` varchar(20) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wheel_record
-- ----------------------------
INSERT INTO `tb_wheel_record` VALUES ('1', '1001', 'LD8325', 'RD3A', null, '0', '0', null, null, null, '株洲车辆厂', '2013-05-21', null, null, null, null, null, null, null, '1', '0', 'SS3B', '010206', '衡阳运用车间', '10');
INSERT INTO `tb_wheel_record` VALUES ('2', '1002', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '广州车辆厂', '2012-05-07', '40.00', '40.00', '100.00', '100.00', '12.00', '12.00', '130.00', '0', '3', null, '01', '车辆处', null);
INSERT INTO `tb_wheel_record` VALUES ('3', '1003', 'LD8326', 'RD4', 'NUK', '1', '0', '80尺', '左边', null, '株洲车辆厂', '2012-05-01', '40.00', '4.00', '120.00', '120.00', '10.00', '10.00', '200.00', '0', '1', '3201', '01', '车辆处', '10');
INSERT INTO `tb_wheel_record` VALUES ('4', '1004', 'LD2102', 'RD3', null, '1', '0', '80尺', '右边', null, '长沙车辆段', '2013-02-15', '20.00', '5.00', '100.00', '100.00', '10.00', '10.00', '210.00', '0', '0', null, '0103', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('5', '1005', 'LD9015', 'RD4A', 'NUK', '1', '0', '80尺', '左边', null, '长沙车辆厂', '2013-05-19', '100.00', '20.00', '120.00', '10.00', '130.00', '10.00', '140.00', '0', '0', null, '01', '车辆处', null);

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
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wheel_stock
-- ----------------------------
INSERT INTO `tb_wheel_stock` VALUES ('1', 'LD8325', 'RD3A', '2', '1', '1', '10', '0', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('2', 'LD8326', 'RD4', '0', '0', '0', '2', '1', '0', '5', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('3', 'LD8325', 'RD3A', '0', '0', '0', '12', '0', '1', '0', '01', '车辆处');
INSERT INTO `tb_wheel_stock` VALUES ('4', 'LD8326', 'RD4', '0', '0', '0', '20', '1', '0', '0', '01', '车辆处');
INSERT INTO `tb_wheel_stock` VALUES ('5', 'LD8325', 'RD4A', '0', '0', '0', '5', '0', '0', '10', '0102', '长沙车辆段');
INSERT INTO `tb_wheel_stock` VALUES ('6', 'LD9015', 'RD4A', '1', '1', '0', '5', '0', '0', '0', '01', '车辆处');

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
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

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
