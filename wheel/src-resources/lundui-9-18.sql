/*
MySQL Data Transfer
Source Host: localhost
Source Database: lundui
Target Host: localhost
Target Database: lundui
Date: 2013/9/18 10:05:59
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
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_axle_type
-- ----------------------------
CREATE TABLE `tb_axle_type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `axle_num` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_depot_repair
-- ----------------------------
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
  `handler_user_num` varchar(20) DEFAULT NULL,
  `handler_user_name` varchar(100) DEFAULT NULL,
  `comfirm_user_num` int(20) DEFAULT NULL,
  `comfirm_user_name` varchar(100) DEFAULT NULL,
  `jc_num_rep` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_factory_repair
-- ----------------------------
CREATE TABLE `tb_factory_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jc_num` varchar(100) NOT NULL,
  `depot_name` varchar(100) NOT NULL,
  `depot_code` varchar(100) NOT NULL,
  `repair_date` datetime DEFAULT NULL,
  `factory` varchar(100) DEFAULT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `src_record_1` int(11) NOT NULL,
  `src_record_2` int(11) NOT NULL,
  `src_record_3` int(11) NOT NULL,
  `src_record_4` int(11) NOT NULL,
  `dest_recrord_1` int(11) NOT NULL,
  `dest_recrord_2` int(11) NOT NULL,
  `dest_recrord_3` int(11) NOT NULL,
  `dest_recrord_4` int(11) NOT NULL,
  `handler_user_num` varchar(20) DEFAULT NULL,
  `handler_user_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
  `target_unit` varchar(20) DEFAULT NULL,
  `reason` smallint(2) NOT NULL DEFAULT '1' COMMENT '0表示不良好，1表示良好',
  `depot_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
CREATE TABLE `tb_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_mark` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

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
  `distance` int(15) DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user_resources
-- ----------------------------
CREATE TABLE `tb_user_resources` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL DEFAULT '0',
  `operator` tinyint(2) DEFAULT '0',
  `resources_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=221 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
CREATE TABLE `tb_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0',
  `role_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

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
  `axle_create_date` date DEFAULT NULL COMMENT '轮轴组装日期',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

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
  `zc_num` int(10) DEFAULT '0',
  `sx_num` int(10) DEFAULT '0',
  `bad_stock` int(10) DEFAULT '0',
  `depot_code` varchar(100) NOT NULL,
  `depot_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_axle_type` VALUES ('1', 'RD3');
INSERT INTO `tb_axle_type` VALUES ('2', 'RD4');
INSERT INTO `tb_axle_type` VALUES ('3', 'RD3A');
INSERT INTO `tb_axle_type` VALUES ('4', 'RD4A');
INSERT INTO `tb_axle_type` VALUES ('5', 'RD3A1');
INSERT INTO `tb_axle_type` VALUES ('6', 'RC3');
INSERT INTO `tb_axle_type` VALUES ('7', 'RC4');
INSERT INTO `tb_depot` VALUES ('1', '广铁集团', '1', '0', '广州', '01');
INSERT INTO `tb_depot` VALUES ('2', '长沙车辆段', '2', '1', '长沙', '0102');
INSERT INTO `tb_depot` VALUES ('3', '广州车辆段', '2', '1', '广州', '0103');
INSERT INTO `tb_depot` VALUES ('4', '汕头车辆段', '2', '1', '汕头', '0104');
INSERT INTO `tb_depot` VALUES ('5', '肇庆客运段', '2', '1', '肇庆', '0105');
INSERT INTO `tb_depot` VALUES ('6', '衡阳运用车间', '3', '2', '衡阳', '010206');
INSERT INTO `tb_depot` VALUES ('7', '长沙运用车间', '3', '2', '长沙', '010207');
INSERT INTO `tb_depot` VALUES ('8', '长沙检修车间', '4', '2', '长沙', '010208');
INSERT INTO `tb_depot` VALUES ('9', '怀化运用车间', '3', '2', '怀化', '010209');
INSERT INTO `tb_depot` VALUES ('10', '广州运用车间', '3', '3', '广州', '010358');
INSERT INTO `tb_depot` VALUES ('11', '石牌运用车间', '3', '3', '广州', '010311');
INSERT INTO `tb_depot` VALUES ('12', '深北运用车间', '3', '3', '深圳', '010312');
INSERT INTO `tb_depot` VALUES ('13', '广州车辆厂', '2', '1', '广州', '0113');
INSERT INTO `tb_depot` VALUES ('15', '东莞东运用车间', '3', '4', '东莞', '010415');
INSERT INTO `tb_depot` VALUES ('16', '汕头运用车间', '3', '4', '汕头', '010416');
INSERT INTO `tb_factory_repair` VALUES ('2', 'SS3B', '衡阳运用车间', '010206', '2013-09-18 00:00:00', null, null, '66', '71', '72', '73', '65', '67', '68', '69', '0004', '李四');
INSERT INTO `tb_income` VALUES ('78', '66', '衡阳运用车间', '010206', '2013-09-18 10:03:43', '7', '0004', '李四');
INSERT INTO `tb_income` VALUES ('79', '71', '衡阳运用车间', '010206', '2013-09-18 10:03:43', '7', '0004', '李四');
INSERT INTO `tb_income` VALUES ('80', '72', '衡阳运用车间', '010206', '2013-09-18 10:03:43', '7', '0004', '李四');
INSERT INTO `tb_income` VALUES ('81', '73', '衡阳运用车间', '010206', '2013-09-18 10:03:43', '7', '0004', '李四');
INSERT INTO `tb_jc_category` VALUES ('1', '内燃车');
INSERT INTO `tb_jc_category` VALUES ('2', '电力车');
INSERT INTO `tb_jc_category` VALUES ('3', '和谐车');
INSERT INTO `tb_outgo` VALUES ('56', '65', '010206', '2013-09-18 10:03:43', null, '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('57', '67', '010206', '2013-09-18 10:03:43', null, '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('58', '68', '010206', '2013-09-18 10:03:43', null, '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('59', '69', '010206', '2013-09-18 10:03:43', null, '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_role` VALUES ('15', '检修车间工人', 'JXCJGR');
INSERT INTO `tb_role` VALUES ('14', '车间管理员', 'CJGLY');
INSERT INTO `tb_role` VALUES ('13', '段管理员', 'DGLY');
INSERT INTO `tb_role` VALUES ('12', '集团领导', 'JTLD');
INSERT INTO `tb_role` VALUES ('11', '管理员', 'GLY');
INSERT INTO `tb_user` VALUES ('12', '0005', 'ww', '123456', '8', '王五');
INSERT INTO `tb_user` VALUES ('11', '0004', 'ls', '123456', '6', '李四');
INSERT INTO `tb_user` VALUES ('10', '0002', 'zs', '123456', '2', '张三');
INSERT INTO `tb_user` VALUES ('5', '0010', 'admin', '123456', '1', '管理员');
INSERT INTO `tb_user` VALUES ('9', '0001', 'jtld', '123456', '1', '集团领导');
INSERT INTO `tb_user` VALUES ('13', '0006', 'dld', '123456', '2', '段领导');
INSERT INTO `tb_user` VALUES ('14', '0007', 'cjld', '123456', '6', '车间领导');
INSERT INTO `tb_user` VALUES ('15', '0008', 'cjgly', '123456', '6', '车间管理员');
INSERT INTO `tb_user` VALUES ('16', '11699', 'wl', '123456', '9', '吴六');
INSERT INTO `tb_user_resources` VALUES ('98', '7', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('97', '7', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('96', '7', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('95', '7', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('219', '11', '1', '13');
INSERT INTO `tb_user_resources` VALUES ('218', '11', '1', '12');
INSERT INTO `tb_user_resources` VALUES ('217', '11', '1', '11');
INSERT INTO `tb_user_resources` VALUES ('216', '11', '0', '6');
INSERT INTO `tb_user_resources` VALUES ('215', '11', '0', '5');
INSERT INTO `tb_user_resources` VALUES ('214', '11', '0', '4');
INSERT INTO `tb_user_resources` VALUES ('213', '11', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('212', '11', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('211', '11', '0', '16');
INSERT INTO `tb_user_resources` VALUES ('210', '11', '0', '15');
INSERT INTO `tb_user_resources` VALUES ('209', '11', '1', '9');
INSERT INTO `tb_user_resources` VALUES ('99', '7', '1', '6');
INSERT INTO `tb_user_resources` VALUES ('208', '11', '1', '8');
INSERT INTO `tb_user_resources` VALUES ('112', '4', '1', '8');
INSERT INTO `tb_user_resources` VALUES ('125', '12', '1', '8');
INSERT INTO `tb_user_resources` VALUES ('126', '12', '1', '9');
INSERT INTO `tb_user_resources` VALUES ('206', '13', '1', '13');
INSERT INTO `tb_user_resources` VALUES ('205', '13', '1', '12');
INSERT INTO `tb_user_resources` VALUES ('204', '13', '1', '11');
INSERT INTO `tb_user_resources` VALUES ('203', '13', '0', '6');
INSERT INTO `tb_user_resources` VALUES ('202', '13', '0', '5');
INSERT INTO `tb_user_resources` VALUES ('201', '13', '0', '4');
INSERT INTO `tb_user_resources` VALUES ('200', '13', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('199', '13', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('198', '13', '0', '16');
INSERT INTO `tb_user_resources` VALUES ('197', '13', '0', '15');
INSERT INTO `tb_user_resources` VALUES ('193', '14', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('192', '14', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('191', '14', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('190', '14', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('189', '14', '1', '16');
INSERT INTO `tb_user_resources` VALUES ('188', '14', '1', '15');
INSERT INTO `tb_user_resources` VALUES ('184', '15', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('183', '15', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('182', '15', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('181', '15', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('180', '15', '1', '16');
INSERT INTO `tb_user_resources` VALUES ('179', '15', '1', '15');
INSERT INTO `tb_user_resources` VALUES ('187', '14', '0', '9');
INSERT INTO `tb_user_resources` VALUES ('186', '14', '0', '8');
INSERT INTO `tb_user_resources` VALUES ('178', '15', '0', '9');
INSERT INTO `tb_user_resources` VALUES ('177', '15', '0', '8');
INSERT INTO `tb_user_resources` VALUES ('196', '13', '0', '9');
INSERT INTO `tb_user_resources` VALUES ('195', '13', '0', '8');
INSERT INTO `tb_user_resources` VALUES ('185', '15', '1', '6');
INSERT INTO `tb_user_resources` VALUES ('194', '14', '1', '6');
INSERT INTO `tb_user_resources` VALUES ('207', '13', '1', '14');
INSERT INTO `tb_user_resources` VALUES ('220', '11', '1', '14');
INSERT INTO `tb_user_role` VALUES ('11', '1', '4');
INSERT INTO `tb_user_role` VALUES ('7', '3', '10');
INSERT INTO `tb_user_role` VALUES ('9', '5', '11');
INSERT INTO `tb_user_role` VALUES ('10', '7', '7');
INSERT INTO `tb_user_role` VALUES ('12', '9', '12');
INSERT INTO `tb_user_role` VALUES ('13', '10', '13');
INSERT INTO `tb_user_role` VALUES ('14', '11', '14');
INSERT INTO `tb_user_role` VALUES ('15', '12', '15');
INSERT INTO `tb_user_role` VALUES ('16', '13', '12');
INSERT INTO `tb_user_role` VALUES ('17', '14', '12');
INSERT INTO `tb_user_role` VALUES ('18', '15', '14');
INSERT INTO `tb_user_role` VALUES ('19', '16', '14');
INSERT INTO `tb_wheel_record` VALUES ('65', '00837', 'RD3', 'KD', '206橡胶', '1', '0', '80尺', null, null, '株洲车辆厂', '2013-05-21', '2013-05-21', '2013-05-21', '102.00', '100.00', '0.00', '52.00', '120.00', '0.00', '120.00', '0', '1', 'SS3B', '010206', '衡阳运用车间', '1', null, null, '0.00', '50.00', '0.00', '45.00', '2013-05-21');
INSERT INTO `tb_wheel_record` VALUES ('66', '20924', 'RD4', 'KD', 'CW-2E', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '1', '0', null, '010206', '衡阳运用车间', null, null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_record` VALUES ('67', '20924', 'RD4', 'KD', 'CW-2E', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '0', '1', 'SS3B', '010206', '衡阳运用车间', '2', null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_record` VALUES ('68', '20925', 'RD4', 'KKD', 'SW-200', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '0', '1', 'SS3B', '010206', '衡阳运用车间', '3', null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_record` VALUES ('69', '20926', 'RD3A', 'KKD', 'SW-200', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '0', '1', 'SS3B', '010206', '衡阳运用车间', '4', null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_record` VALUES ('70', '20927', 'RD3A', 'KKD', '209金属', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '0', '0', null, '010206', '衡阳运用车间', null, null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_record` VALUES ('71', '20928', 'RD3A', 'KKD', '209金属', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '1', '0', null, '010206', '衡阳运用车间', null, null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_record` VALUES ('72', '20930', 'RD3A', 'KKD', 'CLC-242', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '1', '0', null, '010206', '衡阳运用车间', null, null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_record` VALUES ('73', '20929', 'RD3A', 'KKD', 'CLC-242', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '1', '0', null, '010206', '衡阳运用车间', null, null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_stock` VALUES ('27', 'RD3', 'KD', '0', '0', '0', '0', '1', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('28', 'RD4', 'KD', '1', '0', '1', '0', '1', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('29', 'RD4', 'KKD', '0', '0', '0', '0', '1', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('30', 'RD3A', 'KKD', '4', '1', '3', '0', '1', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_type` VALUES ('1', 'KD');
INSERT INTO `tb_wheel_type` VALUES ('2', 'KKD');
INSERT INTO `tb_wheel_type` VALUES ('3', 'KDQ');
INSERT INTO `tb_xc` VALUES ('1', '新组装');
INSERT INTO `tb_xc` VALUES ('2', '段修');
INSERT INTO `tb_xc` VALUES ('3', '段做厂修');
INSERT INTO `tb_xc` VALUES ('4', '临修');
INSERT INTO `tb_xc` VALUES ('5', '送修');
INSERT INTO `tb_zx_type` VALUES ('1', '206金属');
INSERT INTO `tb_zx_type` VALUES ('2', '206橡胶');
INSERT INTO `tb_zx_type` VALUES ('3', '209金属');
INSERT INTO `tb_zx_type` VALUES ('4', '209橡胶');
INSERT INTO `tb_zx_type` VALUES ('5', 'CW-2');
INSERT INTO `tb_zx_type` VALUES ('6', 'CW-2E');
INSERT INTO `tb_zx_type` VALUES ('7', 'SW-160');
INSERT INTO `tb_zx_type` VALUES ('8', '209HS');
INSERT INTO `tb_zx_type` VALUES ('9', 'CW-220');
INSERT INTO `tb_zx_type` VALUES ('10', 'SW-200');
INSERT INTO `tb_zx_type` VALUES ('11', 'CLC-242');
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
INSERT INTO `td_resources` VALUES ('15', '客车段修', 'inout/depotRepair!getDepotRepairList.do', '1', '/1/15');
INSERT INTO `td_resources` VALUES ('16', '轮对厂修', 'inout/factoryRepair!list.do', '1', '/1/16');
