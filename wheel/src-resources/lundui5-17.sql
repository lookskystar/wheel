/*
MySQL Data Transfer
Source Host: localhost
Source Database: lundui
Target Host: localhost
Target Database: lundui
Date: 2013/5/17 17:51:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_axle
-- ----------------------------
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
-- Table structure for tb_axle_type
-- ----------------------------
CREATE TABLE `tb_axle_type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `axle_num` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_depot
-- ----------------------------
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
-- Table structure for tb_income
-- ----------------------------
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
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_jc
-- ----------------------------
CREATE TABLE `tb_jc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jc_num` varchar(100) NOT NULL,
  `area` varchar(100) NOT NULL COMMENT '标识机车所属单位：长沙车辆段、广州车辆厂',
  `jc_type_id` int(11) NOT NULL COMMENT '关联机车的类型',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_jc_category
-- ----------------------------
CREATE TABLE `tb_jc_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '如：内燃车、电力车、和谐车等',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_jc_type
-- ----------------------------
CREATE TABLE `tb_jc_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '如：DF4、DF4D、DF7、DF7D、SS3、SS3B等',
  `category_id` int(11) NOT NULL COMMENT '机车类别id，关联机车类别',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_outgo
-- ----------------------------
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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
CREATE TABLE `tb_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_mark` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_temporary_repair
-- ----------------------------
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
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
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
-- Table structure for tb_user_resources
-- ----------------------------
CREATE TABLE `tb_user_resources` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL DEFAULT '0',
  `resources_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
CREATE TABLE `tb_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0',
  `role_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wheel_recent_record
-- ----------------------------
CREATE TABLE `tb_wheel_recent_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `axle_num` varchar(100) NOT NULL,
  `axle_type` varchar(100) NOT NULL,
  `wheel_rec_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wheel_record
-- ----------------------------
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
  `left_rim_thickness` double(10,0) DEFAULT NULL,
  `right_rim_thickness` double(10,0) DEFAULT NULL,
  `left_diameter` double(10,0) DEFAULT NULL,
  `right_diamter` double(10,0) DEFAULT NULL,
  `left_flange_thickness` double(10,0) DEFAULT NULL,
  `right_flange_thickness` double(10,0) DEFAULT NULL,
  `inside_istance` double(10,0) DEFAULT NULL,
  `status` tinyint(2) NOT NULL,
  `whereabouts` tinyint(2) NOT NULL,
  `jc_num` varchar(200) DEFAULT NULL,
  `depot_code` varchar(20) DEFAULT NULL,
  `depot_name` varchar(20) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wheel_stock
-- ----------------------------
CREATE TABLE `tb_wheel_stock` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `axle_type` varchar(100) DEFAULT NULL,
  `wheel_type` varchar(100) DEFAULT NULL,
  `inventory` int(10) NOT NULL DEFAULT '0',
  `good_num` int(10) NOT NULL DEFAULT '0',
  `not_good_num` int(10) NOT NULL DEFAULT '0',
  `min_stock` int(10) DEFAULT '0',
  `bad_stock` int(10) DEFAULT '0',
  `depot_code` varchar(100) NOT NULL,
  `depot_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wheel_type
-- ----------------------------
CREATE TABLE `tb_wheel_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wheel_num` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_xc
-- ----------------------------
CREATE TABLE `tb_xc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '修程名' COMMENT '如：段修、段做厂修、临修、A2修等',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_zx_type
-- ----------------------------
CREATE TABLE `tb_zx_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_resources
-- ----------------------------
CREATE TABLE `td_resources` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `resources_name` varchar(100) NOT NULL,
  `resources_url` varchar(300) NOT NULL,
  `parent_id` int(20) NOT NULL DEFAULT '0',
  `path` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_axle` VALUES ('1', '1001', '1', '2009-04-23 00:00:00', '株洲车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('2', '1001', '1', '2013-03-12 00:00:00', '长沙车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('3', '1003', '2', '2012-04-24 00:00:00', '广州车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('4', '1004', '3', '2011-04-13 00:00:00', '株洲车辆厂', '0');
INSERT INTO `tb_axle` VALUES ('5', '1005', '3', '2013-05-07 00:00:00', '长沙车辆厂', '1');
INSERT INTO `tb_axle` VALUES ('6', '1006', '1', '2013-05-08 00:00:00', '长沙车辆厂', '1');
INSERT INTO `tb_axle_type` VALUES ('1', 'LD8325');
INSERT INTO `tb_axle_type` VALUES ('2', 'LD8235');
INSERT INTO `tb_axle_type` VALUES ('3', 'LD8326');
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
INSERT INTO `tb_income` VALUES ('1', '1', '长沙车辆段', '0102', '2013-05-01 00:00:00', '0', '1', null);
INSERT INTO `tb_income` VALUES ('2', '2', '长沙车辆段', '0102', '2013-05-01 00:00:00', '0', '1', null);
INSERT INTO `tb_income` VALUES ('3', '3', '长沙车辆段', '0102', '2013-05-03 00:00:00', '0', '1', null);
INSERT INTO `tb_income` VALUES ('4', '4', '长沙车辆段', '0102', '2013-05-06 00:00:00', '0', '1', null);
INSERT INTO `tb_income` VALUES ('5', '5', '长沙车辆段', '0102', '2013-05-07 00:00:00', '0', '1', null);
INSERT INTO `tb_income` VALUES ('6', '2', '长沙车辆段', '0102', '2013-05-08 00:00:00', '0', '1', null);
INSERT INTO `tb_income` VALUES ('13', '2', '长沙车辆段', '0102', '2013-04-09 00:00:00', '0', '1', null);
INSERT INTO `tb_income` VALUES ('14', '11', '长沙车辆段', '0102', '2013-05-17 00:00:00', '1', null, null);
INSERT INTO `tb_income` VALUES ('15', '12', '长沙车辆段', '0102', '2013-05-17 00:00:00', '1', null, null);
INSERT INTO `tb_income` VALUES ('16', '13', '长沙车辆段', '0102', '2013-05-16 00:00:00', '1', '630530', null);
INSERT INTO `tb_outgo` VALUES ('1', '1', null, '2013-05-04 00:00:00', null, null, '2', '0', null);
INSERT INTO `tb_outgo` VALUES ('2', '2', null, '2013-05-04 00:00:00', null, null, '1', '0', null);
INSERT INTO `tb_outgo` VALUES ('3', '3', null, '2013-05-06 00:00:00', null, null, '3', '0', null);
INSERT INTO `tb_role` VALUES ('7', '车辆厂工人', 'CLCGR');
INSERT INTO `tb_role` VALUES ('6', '车辆段质检员', 'CLDZJY');
INSERT INTO `tb_role` VALUES ('4', '车辆段工人', 'CLDGR');
INSERT INTO `tb_role` VALUES ('5', '车辆段工长', 'CLDGZ');
INSERT INTO `tb_role` VALUES ('8', '车辆厂工长', 'CLCGZ');
INSERT INTO `tb_role` VALUES ('9', '车辆厂质检员', 'CLCZJY');
INSERT INTO `tb_role` VALUES ('10', '集团领导', 'JTLD');
INSERT INTO `tb_temporary_repair` VALUES ('1', '1', null, null, null, null, null, '测试测试测试', null, null, null, null, null, '2013-05-03 00:00:00');
INSERT INTO `tb_temporary_repair` VALUES ('2', '2', null, null, null, null, null, '天下男儿皆薄幸', null, null, null, null, null, '2013-05-14 00:00:00');
INSERT INTO `tb_temporary_repair` VALUES ('3', '2', null, null, null, null, null, '天下男儿皆薄幸', null, null, null, null, null, '2013-05-03 00:00:00');
INSERT INTO `tb_temporary_repair` VALUES ('4', '3', null, null, null, null, null, '测试', null, null, null, null, null, '2013-05-06 00:00:00');
INSERT INTO `tb_user` VALUES ('1', '630530', 'zs', '123456', '2', '张三');
INSERT INTO `tb_user` VALUES ('2', '630531', 'ls', '123456', '5', '李四');
INSERT INTO `tb_user` VALUES ('3', '630532', 'ww', '123456', '6', '王五');
INSERT INTO `tb_user_resources` VALUES ('21', '7', '9');
INSERT INTO `tb_user_resources` VALUES ('20', '7', '6');
INSERT INTO `tb_user_resources` VALUES ('19', '7', '5');
INSERT INTO `tb_user_resources` VALUES ('18', '7', '11');
INSERT INTO `tb_user_resources` VALUES ('17', '7', '10');
INSERT INTO `tb_user_role` VALUES ('8', '1', '7');
INSERT INTO `tb_user_role` VALUES ('7', '3', '10');
INSERT INTO `tb_wheel_recent_record` VALUES ('1', '1001', 'LD8325', '1');
INSERT INTO `tb_wheel_recent_record` VALUES ('2', '1020', 'LD8325', '2');
INSERT INTO `tb_wheel_recent_record` VALUES ('3', '1001', 'LD8325', '3');
INSERT INTO `tb_wheel_recent_record` VALUES ('4', '1001', 'LD8325', '4');
INSERT INTO `tb_wheel_recent_record` VALUES ('5', '1001', 'LD8325', '5');
INSERT INTO `tb_wheel_record` VALUES ('1', '1001', 'LD8325', 'RD3', null, null, null, null, null, null, '长沙车辆厂', '2013-03-04', '12', '13', '12', '13', '13', '13', null, '0', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('2', '1020', 'LD8325', 'RD4', null, null, null, null, null, null, '长沙车辆厂', '2012-04-25', '12', '12', '12', '12', '12', '12', null, '1', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('3', '1008', 'LD8220', 'RD3', null, null, null, null, null, null, '长沙车辆厂', '2010-02-23', '12', '12', '13', '13', '14', '14', null, '0', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('4', '1003', 'LD8315', 'RD3', null, null, null, null, null, null, '长沙车辆厂', '2011-04-27', '13', '13', '13', '13', '13', '13', null, '0', '1', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('5', '1001', 'LD8325', 'RD3', null, null, null, null, null, null, '长沙车辆厂', '2013-05-06', '12', '11', '12', '12', '13', '13', null, '0', '1', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('6', '1020', 'LD8326', 'RD3', 'ZX001', '1', '1', null, null, null, '长沙车辆厂', null, '14', '15', '13', '11', '12', '12', '12', '1', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('7', '1021', 'LD8325', 'RD3A', 'NUK', '1', null, '80尺', 'left', null, null, null, '120', '112', '815', '815', '11', '11', '20', '0', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('8', '1022', 'LD8326', 'AM96', 'NUK', '1', null, '80尺', 'left', null, null, null, '12', '13', '22', '22', '22', '22', '22', '0', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('9', '1023', 'LD8325', 'RC4', 'NUK', '1', '0', '80尺', 'left', null, null, null, '55', '5', '55', '25', '5', '5', '5', '0', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('10', '1024', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', 'left', null, '株洲车辆厂', '2012-05-14', '12', '11', '12', '12', '12', '12', '12', '0', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('11', '1025', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', 'left', null, '', '2013-05-17', '12', '12', '12', '12', '12', '12', '12', '0', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('12', '1026', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', 'left', null, '株洲车辆厂', '2012-05-08', '10', '10', '10', '10', '10', '10', '10', '1', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_record` VALUES ('13', '1127', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', '左边', null, '株洲', '2013-03-12', '22', '22', '222', '222', '111', '11', '11', '0', '0', null, '0102', '长沙车辆段', null);
INSERT INTO `tb_wheel_stock` VALUES ('1', 'LD8325', 'RD3', '3', '3', '0', '10', '20', '0102', '长沙车辆厂');
INSERT INTO `tb_wheel_stock` VALUES ('2', 'LD8325', 'RD4', '3', '3', '0', '10', '20', '0102', '长沙车辆厂');
INSERT INTO `tb_wheel_stock` VALUES ('3', 'LD8325', 'RD3A', '3', '2', '1', '0', '0', '0102', '长沙车辆段');
INSERT INTO `tb_wheel_type` VALUES ('1', 'RD3A');
INSERT INTO `tb_wheel_type` VALUES ('2', 'RD3');
INSERT INTO `tb_wheel_type` VALUES ('3', 'RD4');
INSERT INTO `tb_wheel_type` VALUES ('4', 'RD4A');
INSERT INTO `tb_wheel_type` VALUES ('5', 'RD3A1');
INSERT INTO `tb_wheel_type` VALUES ('6', 'RC4');
INSERT INTO `tb_wheel_type` VALUES ('7', 'AM96');
INSERT INTO `tb_xc` VALUES ('1', '新组装');
INSERT INTO `tb_xc` VALUES ('2', '段修');
INSERT INTO `tb_xc` VALUES ('3', '段做厂修');
INSERT INTO `tb_xc` VALUES ('4', '临修');
INSERT INTO `tb_xc` VALUES ('5', '送修');
INSERT INTO `tb_zx_type` VALUES ('1', 'NUK');
INSERT INTO `tb_zx_type` VALUES ('2', 'NUK1');
INSERT INTO `tb_zx_type` VALUES ('3', 'NUK2');
INSERT INTO `tb_zx_type` VALUES ('4', 'NJK1');
INSERT INTO `tb_zx_type` VALUES ('5', 'NJK2');
INSERT INTO `tb_zx_type` VALUES ('6', 'NUHJK');
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
