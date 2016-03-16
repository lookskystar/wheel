/*
MySQL Data Transfer
Source Host: localhost
Source Database: lundui
Target Host: localhost
Target Database: lundui
Date: 2013/9/14 22:45:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_axle
-- ----------------------------
CREATE TABLE `tb_axle` (
  `id` int(11) NOT NULL auto_increment,
  `axle_num` varchar(200) NOT NULL,
  `axle_type_id` int(11) NOT NULL,
  `make_date` datetime NOT NULL,
  `make_company` varchar(100) NOT NULL,
  `status` smallint(2) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_axle_type
-- ----------------------------
CREATE TABLE `tb_axle_type` (
  `id` int(20) NOT NULL auto_increment,
  `axle_num` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_depot
-- ----------------------------
CREATE TABLE `tb_depot` (
  `id` int(20) NOT NULL auto_increment,
  `depot_name` varchar(100) NOT NULL,
  `depot_type` int(2) NOT NULL,
  `parent_id` int(20) NOT NULL default '0',
  `depot_location` varchar(20) default NULL,
  `depot_code` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_depot_repair
-- ----------------------------
CREATE TABLE `tb_depot_repair` (
  `id` int(11) NOT NULL auto_increment,
  `jc_num` varchar(20) NOT NULL,
  `depot_code` varchar(100) default NULL,
  `depot_name` varchar(100) default NULL,
  `src_record_1` varchar(11) default NULL,
  `src_record_2` varchar(11) default NULL,
  `src_record_3` varchar(11) default NULL,
  `src_record_4` varchar(11) default NULL,
  `dest_recrord_1` varchar(11) default NULL,
  `dest_recrord_2` varchar(11) default NULL,
  `dest_recrord_3` varchar(11) default NULL,
  `dest_recrord_4` varchar(11) default NULL,
  `repair_date` date default NULL,
  `handler_user_num` int(20) default NULL,
  `handler_user_name` varchar(100) default NULL,
  `comfirm_user_num` int(20) default NULL,
  `comfirm_user_name` varchar(100) default NULL,
  `jc_num_rep` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_income
-- ----------------------------
CREATE TABLE `tb_income` (
  `id` int(11) NOT NULL auto_increment,
  `wheelset_id` int(11) default NULL,
  `depot_name` varchar(20) default NULL,
  `depot_code` varchar(20) default NULL,
  `in_time` datetime default NULL,
  `reason` tinyint(2) default NULL,
  `user_num` varchar(20) default NULL,
  `user_name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_jc
-- ----------------------------
CREATE TABLE `tb_jc` (
  `id` int(11) NOT NULL auto_increment,
  `jc_num` varchar(100) NOT NULL,
  `area` varchar(100) NOT NULL COMMENT '标识机车所属单位：长沙车辆段、广州车辆厂',
  `jc_type_id` int(11) NOT NULL COMMENT '关联机车的类型',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_jc_category
-- ----------------------------
CREATE TABLE `tb_jc_category` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL COMMENT '如：内燃车、电力车、和谐车等',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_jc_type
-- ----------------------------
CREATE TABLE `tb_jc_type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL COMMENT '如：DF4、DF4D、DF7、DF7D、SS3、SS3B等',
  `category_id` int(11) NOT NULL COMMENT '机车类别id，关联机车类别',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_outgo
-- ----------------------------
CREATE TABLE `tb_outgo` (
  `id` int(11) NOT NULL auto_increment,
  `wheelset_id` int(11) NOT NULL,
  `depot_code` varchar(20) default NULL,
  `out_time` datetime NOT NULL,
  `remark` varchar(200) default NULL,
  `user_name` varchar(20) default NULL,
  `user_num` varchar(20) NOT NULL,
  `target_unit` varchar(20) default NULL,
  `reason` smallint(2) NOT NULL default '1' COMMENT '0表示不良好，1表示良好',
  `depot_name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
CREATE TABLE `tb_role` (
  `id` int(20) NOT NULL auto_increment,
  `role_name` varchar(100) NOT NULL,
  `role_mark` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_temporary_repair
-- ----------------------------
CREATE TABLE `tb_temporary_repair` (
  `id` int(11) NOT NULL auto_increment,
  `wheelset_id` int(11) NOT NULL,
  `jx_num` varchar(20) default NULL,
  `depot_name` varchar(20) default NULL,
  `depot_code` varchar(20) default NULL,
  `position` varchar(100) default NULL,
  `treatment` tinyint(2) default NULL,
  `hitch_desc` varchar(255) default NULL,
  `out_wheelset_id` int(11) default NULL,
  `handler_user_num` varchar(20) default NULL,
  `handler_user_name` varchar(20) default NULL,
  `comfirm_user_num` varchar(20) default NULL,
  `comfirm_user_name` varchar(20) default NULL,
  `distance` int(15) default NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
CREATE TABLE `tb_user` (
  `id` int(20) NOT NULL auto_increment,
  `job_number` varchar(100) NOT NULL,
  `longin_name` varchar(100) NOT NULL,
  `login_pwd` varchar(100) NOT NULL,
  `depot_id` int(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user_resources
-- ----------------------------
CREATE TABLE `tb_user_resources` (
  `id` int(20) NOT NULL auto_increment,
  `role_id` int(20) NOT NULL default '0',
  `operator` tinyint(2) default '0',
  `resources_id` int(20) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=177 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
CREATE TABLE `tb_user_role` (
  `id` int(20) NOT NULL auto_increment,
  `user_id` int(10) NOT NULL default '0',
  `role_id` int(10) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wheel_recent_record
-- ----------------------------
CREATE TABLE `tb_wheel_recent_record` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `axle_num` varchar(100) NOT NULL,
  `axle_type` varchar(100) NOT NULL,
  `wheel_rec_id` int(20) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wheel_record
-- ----------------------------
CREATE TABLE `tb_wheel_record` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `axle_num` varchar(100) default NULL,
  `axle_type` varchar(100) default NULL,
  `wheel_type` varchar(100) default NULL,
  `axle_box_type` varchar(100) default NULL,
  `has_anti_skid` tinyint(2) default NULL,
  `has_axle_box_relay` tinyint(2) default NULL,
  `anti_skid_size` varchar(20) default NULL,
  `anti_skid_location` varchar(10) default NULL,
  `wheel_location` varchar(100) default NULL,
  `factory` varchar(100) default NULL,
  `box_create_date` date default NULL,
  `wheel_create_date` date default NULL,
  `create_date` date default NULL,
  `left_rim_thickness` double(10,2) default NULL,
  `right_rim_thickness` double(10,2) default NULL,
  `left_circular_wear` double(10,2) default NULL,
  `right_diamter` double(10,2) default NULL,
  `left_flange_thickness` double(10,2) default NULL,
  `right_flange_thickness` double(10,2) default NULL,
  `left_brake_disc_wear` double(10,2) default NULL,
  `status` tinyint(2) NOT NULL,
  `whereabouts` tinyint(2) NOT NULL,
  `jc_num` varchar(200) default NULL,
  `depot_code` varchar(20) default NULL,
  `depot_name` varchar(20) default NULL,
  `position` varchar(100) default NULL,
  `income_id` int(11) default NULL,
  `outlay_id` int(11) default NULL,
  `right_circular_wear` double(10,2) default NULL,
  `left_diameter` double(10,2) default NULL,
  `right_brake_disc_wear` double(10,2) default NULL,
  `inside_istance` double(10,2) default NULL,
  `axle_create_date` date default NULL COMMENT '轮轴组装日期',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wheel_stock
-- ----------------------------
CREATE TABLE `tb_wheel_stock` (
  `id` int(20) NOT NULL auto_increment,
  `axle_type` varchar(100) default NULL,
  `wheel_type` varchar(100) default NULL,
  `inventory` int(10) NOT NULL default '0',
  `good_num` int(10) NOT NULL default '0',
  `not_good_num` int(10) NOT NULL default '0',
  `min_stock` int(10) default '0',
  `zc_num` int(10) default '0',
  `sx_num` int(10) default '0',
  `bad_stock` int(10) default '0',
  `depot_code` varchar(100) NOT NULL,
  `depot_name` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wheel_type
-- ----------------------------
CREATE TABLE `tb_wheel_type` (
  `id` int(11) NOT NULL auto_increment,
  `wheel_num` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_xc
-- ----------------------------
CREATE TABLE `tb_xc` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL default '修程名' COMMENT '如：段修、段做厂修、临修、A2修等',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_zx_type
-- ----------------------------
CREATE TABLE `tb_zx_type` (
  `id` int(11) NOT NULL auto_increment,
  `type_name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for td_resources
-- ----------------------------
CREATE TABLE `td_resources` (
  `id` int(20) NOT NULL auto_increment,
  `resources_name` varchar(100) NOT NULL,
  `resources_url` varchar(300) NOT NULL,
  `parent_id` int(20) NOT NULL default '0',
  `path` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

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
INSERT INTO `tb_depot_repair` VALUES ('5', '3201', '010206', '衡阳运用车间', '31', '39', '36', '33', '37', '40', '41', '42', '2013-09-11', '4', '李四', null, null, null);
INSERT INTO `tb_depot_repair` VALUES ('6', '3201', '010206', '衡阳运用车间', '37', '40', '41', '42', '44', '33', '36', '39', '2013-09-11', '8', '车间管理员', null, null, null);
INSERT INTO `tb_depot_repair` VALUES ('7', '3201', '010206', '衡阳运用车间', '44', '40', '36', '39', '33', '37', '41', '42', '2013-09-11', '8', '车间管理员', null, null, null);
INSERT INTO `tb_income` VALUES ('32', '31', '衡阳运用车间', '010206', '2013-09-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('33', '32', '衡阳运用车间', '010206', '2013-09-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('34', '33', '衡阳运用车间', '010206', '2013-09-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('35', '34', '长沙检修车间', '010208', '2013-09-02 00:00:00', '1', '0005', '王五');
INSERT INTO `tb_income` VALUES ('36', '35', '怀化运用车间', '010209', '2013-09-01 00:00:00', '1', '11699', '吴六');
INSERT INTO `tb_income` VALUES ('37', '36', '衡阳运用车间', '010206', '2013-09-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('38', '37', '衡阳运用车间', '010206', '2013-09-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('39', '38', '衡阳运用车间', '010206', '2013-09-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('40', '39', '衡阳运用车间', '010206', '2013-09-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('41', '32', '衡阳运用车间', '010206', '2013-09-09 00:00:00', '3', '0004', '李四');
INSERT INTO `tb_income` VALUES ('42', '42', '衡阳运用车间', '010206', '2013-09-01 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('43', '43', '衡阳运用车间', '010206', '2013-09-03 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('44', '44', '衡阳运用车间', '010206', '2013-09-03 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('45', '45', '衡阳运用车间', '010206', '2013-09-03 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('46', '46', '衡阳运用车间', '010206', '2013-09-02 00:00:00', '1', '0004', '李四');
INSERT INTO `tb_income` VALUES ('47', '31', '衡阳运用车间', '010206', '2013-09-11 09:17:44', '6', '0004', '李四');
INSERT INTO `tb_income` VALUES ('48', '39', '衡阳运用车间', '010206', '2013-09-11 09:17:44', '6', '0004', '李四');
INSERT INTO `tb_income` VALUES ('49', '36', '衡阳运用车间', '010206', '2013-09-11 09:17:44', '6', '0004', '李四');
INSERT INTO `tb_income` VALUES ('50', '33', '衡阳运用车间', '010206', '2013-09-11 09:17:44', '6', '0004', '李四');
INSERT INTO `tb_income` VALUES ('51', '37', '衡阳运用车间', '010206', '2013-09-11 09:56:29', '6', '0008', '车间管理员');
INSERT INTO `tb_income` VALUES ('52', '40', '衡阳运用车间', '010206', '2013-09-11 09:56:29', '6', '0008', '车间管理员');
INSERT INTO `tb_income` VALUES ('53', '41', '衡阳运用车间', '010206', '2013-09-11 09:56:29', '6', '0008', '车间管理员');
INSERT INTO `tb_income` VALUES ('54', '42', '衡阳运用车间', '010206', '2013-09-11 09:56:29', '6', '0008', '车间管理员');
INSERT INTO `tb_income` VALUES ('55', '33', '衡阳运用车间', '010206', '2013-09-10 00:00:00', '3', '0004', '李四');
INSERT INTO `tb_income` VALUES ('56', '44', '衡阳运用车间', '010206', '2013-09-11 10:17:47', '6', '0008', '车间管理员');
INSERT INTO `tb_income` VALUES ('57', '40', '衡阳运用车间', '010206', '2013-09-11 10:17:47', '6', '0008', '车间管理员');
INSERT INTO `tb_income` VALUES ('58', '36', '衡阳运用车间', '010206', '2013-09-11 10:17:47', '6', '0008', '车间管理员');
INSERT INTO `tb_income` VALUES ('59', '39', '衡阳运用车间', '010206', '2013-09-11 10:17:47', '6', '0008', '车间管理员');
INSERT INTO `tb_jc_category` VALUES ('1', '内燃车');
INSERT INTO `tb_jc_category` VALUES ('2', '电力车');
INSERT INTO `tb_jc_category` VALUES ('3', '和谐车');
INSERT INTO `tb_outgo` VALUES ('16', '31', '010206', '2013-09-01 00:00:00', '上车号:3201,上车位:1', '李四', '0004', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('17', '32', '010206', '2013-09-09 00:00:00', '上车号:3201,上车位:2', '李四', '0004', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('18', '36', '010206', '2013-09-09 00:00:00', '上车号:3201,上车位:3', '李四', '0004', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('19', '33', '010206', '2013-09-09 00:00:00', '上车号:3201,上车位:4', '李四', '0004', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('20', '39', '010206', '2013-09-09 00:00:00', '临修装车,上车号：3201,上车位:2', '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('21', '46', '010206', '2013-09-11 00:00:00', '接收单位:衡阳运用车间', '李四', '0004', '010206', '2', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('22', '32', '010206', '2013-09-11 00:00:00', '接收单位:衡阳运用车间', '车间管理员', '0008', '010206', '2', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('23', '43', '010206', '2013-09-11 00:00:00', 'ggg', '李四', '0004', '01', '3', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('24', '38', '010206', '2013-09-07 00:00:00', '上车号:351942,上车位:1', '车间管理员', '0008', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('25', '37', '010206', '2013-09-11 09:17:44', null, '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('26', '40', '010206', '2013-09-11 09:17:44', null, '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('27', '41', '010206', '2013-09-11 09:17:44', null, '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('28', '42', '010206', '2013-09-11 09:17:44', null, '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('29', '31', '010206', '2013-09-11 00:00:00', '上车号:3201,上车位:1', '车间管理员', '0008', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('30', '45', '010206', '2013-09-12 00:00:00', '上车号:2204,上车位:1', '车间管理员', '0008', '01', '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('31', '44', '010206', '2013-09-11 09:56:29', null, '车间管理员', '0008', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('32', '33', '010206', '2013-09-11 09:56:29', null, '车间管理员', '0008', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('33', '36', '010206', '2013-09-11 09:56:29', null, '车间管理员', '0008', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('34', '39', '010206', '2013-09-11 09:56:29', null, '车间管理员', '0008', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('35', '40', '010206', '2013-09-10 00:00:00', '临修装车,上车号：3201,上车位:2', '李四', '0004', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('36', '33', '010206', '2013-09-11 10:17:47', null, '车间管理员', '0008', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('37', '37', '010206', '2013-09-11 10:17:47', null, '车间管理员', '0008', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('38', '41', '010206', '2013-09-11 10:17:47', null, '车间管理员', '0008', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('39', '42', '010206', '2013-09-11 10:17:47', null, '车间管理员', '0008', null, '1', '衡阳运用车间');
INSERT INTO `tb_outgo` VALUES ('40', '36', '010206', '2013-09-02 00:00:00', '接收单位:长沙检修车间', '车间管理员', '0008', '010208', '2', '衡阳运用车间');
INSERT INTO `tb_role` VALUES ('15', '检修车间工人', 'JXCJGR');
INSERT INTO `tb_role` VALUES ('14', '车间管理员', 'CJGLY');
INSERT INTO `tb_role` VALUES ('13', '段管理员', 'DGLY');
INSERT INTO `tb_role` VALUES ('12', '集团领导', 'JTLD');
INSERT INTO `tb_role` VALUES ('11', '管理员', 'GLY');
INSERT INTO `tb_temporary_repair` VALUES ('3', '31', '3201', '衡阳运用车间', '010206', '1', '2', '有裂纹', null, '0004', '李四', '0004', '李四', '8200', '2013-09-09 00:00:00');
INSERT INTO `tb_temporary_repair` VALUES ('4', '32', '3201', '衡阳运用车间', '010206', '2', '1', '也有裂纹', '39', '0004', '李四', '0004', '李四', '9600', '2013-09-09 00:00:00');
INSERT INTO `tb_temporary_repair` VALUES ('5', '33', '3201', '衡阳运用车间', '010206', '2', '1', '凹入', '40', '0004', '李四', '0004', '李四', null, '2013-09-10 00:00:00');
INSERT INTO `tb_temporary_repair` VALUES ('6', '40', '3201', '衡阳运用车间', '010206', '2', '2', ' 撒', null, '0004', '李四', '0004', '李四', null, '2013-09-11 00:00:00');
INSERT INTO `tb_temporary_repair` VALUES ('7', '42', '3201', '衡阳运用车间', '010206', '4', '2', '凹入', null, '0004', '李四', '0004', '李四', null, '2013-09-11 00:00:00');
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
INSERT INTO `tb_user_resources` VALUES ('174', '13', '1', '12');
INSERT INTO `tb_user_resources` VALUES ('173', '13', '1', '11');
INSERT INTO `tb_user_resources` VALUES ('172', '13', '0', '6');
INSERT INTO `tb_user_resources` VALUES ('171', '13', '0', '5');
INSERT INTO `tb_user_resources` VALUES ('170', '13', '0', '4');
INSERT INTO `tb_user_resources` VALUES ('169', '13', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('168', '13', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('167', '13', '0', '15');
INSERT INTO `tb_user_resources` VALUES ('166', '13', '0', '9');
INSERT INTO `tb_user_resources` VALUES ('165', '13', '0', '8');
INSERT INTO `tb_user_resources` VALUES ('154', '14', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('153', '14', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('152', '14', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('151', '14', '1', '15');
INSERT INTO `tb_user_resources` VALUES ('150', '14', '0', '9');
INSERT INTO `tb_user_resources` VALUES ('149', '14', '0', '8');
INSERT INTO `tb_user_resources` VALUES ('162', '15', '1', '4');
INSERT INTO `tb_user_resources` VALUES ('161', '15', '1', '3');
INSERT INTO `tb_user_resources` VALUES ('160', '15', '1', '2');
INSERT INTO `tb_user_resources` VALUES ('159', '15', '1', '15');
INSERT INTO `tb_user_resources` VALUES ('158', '15', '0', '9');
INSERT INTO `tb_user_resources` VALUES ('157', '15', '0', '8');
INSERT INTO `tb_user_resources` VALUES ('155', '14', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('156', '14', '1', '6');
INSERT INTO `tb_user_resources` VALUES ('163', '15', '1', '5');
INSERT INTO `tb_user_resources` VALUES ('164', '15', '1', '6');
INSERT INTO `tb_user_resources` VALUES ('175', '13', '1', '13');
INSERT INTO `tb_user_resources` VALUES ('176', '13', '1', '14');
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
INSERT INTO `tb_wheel_recent_record` VALUES ('1', '1001', 'LD8325', '1');
INSERT INTO `tb_wheel_recent_record` VALUES ('2', '1020', 'LD8325', '2');
INSERT INTO `tb_wheel_recent_record` VALUES ('3', '1001', 'LD8325', '3');
INSERT INTO `tb_wheel_recent_record` VALUES ('4', '1001', 'LD8325', '4');
INSERT INTO `tb_wheel_recent_record` VALUES ('5', '1001', 'LD8325', '5');
INSERT INTO `tb_wheel_record` VALUES ('31', '24589', 'ZX2025', 'RD3A', 'NUK', '1', '0', '80齿', '左边', null, '长沙车辆段', '2012-09-02', '2012-09-02', '2012-09-09', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '1', '0', '3201', '010206', '衡阳运用车间', '1', null, null, '80.00', '80.00', '80.00', '80.00', '2013-07-08');
INSERT INTO `tb_wheel_record` VALUES ('32', '16895', 'ZX2025', 'RD3A', null, '0', '0', null, null, null, '长沙车辆段', null, null, '2012-09-03', null, null, null, null, null, null, null, '1', '2', '3201', '010206', '衡阳运用车间', '2', null, null, null, null, null, null, null);
INSERT INTO `tb_wheel_record` VALUES ('40', '1837', 'LD8325', 'RD3A', 'NUK', '1', '0', '80尺', null, null, '株洲车辆厂', '2013-05-21', '2013-05-21', '2013-05-21', '102.00', '100.00', '0.00', '52.00', '120.00', '0.00', '120.00', '1', '0', null, '010206', '衡阳运用车间', null, null, null, '0.00', '50.00', '0.00', '45.00', '2013-05-21');
INSERT INTO `tb_wheel_record` VALUES ('33', '78563', 'ZX2025', 'RD3', null, '0', '0', null, null, null, '长沙检修车间', null, null, '2012-09-03', null, null, null, null, null, null, null, '1', '1', '3201', '010206', '衡阳运用车间', '1', null, null, null, null, null, null, null);
INSERT INTO `tb_wheel_record` VALUES ('34', '89516', 'ZX2306', 'RD4', 'NUK', '1', '0', '80齿', '左边', null, '怀化运用车间', '2012-08-27', '2012-08-27', '2012-08-27', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '0', '0', null, '010208', '长沙检修车间', null, null, null, '79.00', '80.00', '80.00', '80.00', '2013-07-08');
INSERT INTO `tb_wheel_record` VALUES ('35', '61335', 'ZX1208', 'RD4', 'NUK', '1', '0', '80齿', '左边', null, '怀化运用车间', '2012-08-27', '2012-08-27', '2012-08-27', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '0', '0', null, '010209', '怀化运用车间', null, null, null, '80.00', '80.00', '80.00', '80.00', '2013-08-05');
INSERT INTO `tb_wheel_record` VALUES ('36', '45826', 'ZX2306', 'RD4', 'NUK', '1', '0', '80齿', '左边', null, '衡阳运用车间', '2012-09-03', '2012-08-26', '2012-08-27', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '1', '2', null, '010206', '衡阳运用车间', null, null, null, '80.00', '80.00', '80.00', '80.00', '2013-08-28');
INSERT INTO `tb_wheel_record` VALUES ('37', '71586', 'ZX1208', 'RD4', 'NUK', '1', '0', '80齿', '左边', null, '衡阳运用车间', '2012-09-03', '2012-09-03', '2012-09-03', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '1', '1', '3201', '010206', '衡阳运用车间', '2', null, null, '80.00', '80.00', '80.00', '80.00', '2013-09-02');
INSERT INTO `tb_wheel_record` VALUES ('38', '15863', 'ZX2025', 'RD3A', 'NUK', '1', '0', '80齿', '左边', null, '衡阳运用车间', '2013-09-02', '2013-09-02', '2013-09-02', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '0', '1', '351942', '010206', '衡阳运用车间', '1', null, null, '80.00', '80.00', '80.00', '80.00', '2013-09-01');
INSERT INTO `tb_wheel_record` VALUES ('39', '13258', 'ZX2025', 'RD3A', 'NUK', '1', '0', '80齿', '左边', null, '怀化运用车间', '2013-09-01', '2013-09-01', '2013-09-01', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '1', '0', null, '010206', '衡阳运用车间', null, null, null, '80.00', '80.00', '80.00', '80.00', '2013-09-01');
INSERT INTO `tb_wheel_record` VALUES ('41', '29923', 'LD9015', 'RD4A', 'NUK', '1', '0', '80尺', null, null, '长沙车辆厂', null, null, '2013-05-19', '20.00', '100.00', '0.00', '10.00', '130.00', '0.00', '10.00', '1', '1', '3201', '010206', '衡阳运用车间', '3', null, null, '0.00', '120.00', '0.00', '140.00', null);
INSERT INTO `tb_wheel_record` VALUES ('42', '15468', 'ZX2679', 'RD3', 'NUK', '1', '0', '80齿', '左边', null, '衡阳运用车间', '2012-09-09', '2012-09-09', '2012-09-09', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '1', '1', '3201', '010206', '衡阳运用车间', '4', null, null, '80.00', '80.00', '80.00', '80.00', '2012-09-09');
INSERT INTO `tb_wheel_record` VALUES ('43', '1546', 'ZX2258', 'RD3A1', 'NUK', '1', '0', '80齿', '左边', null, '怀化运用车间', '2013-09-01', '2013-09-02', '2013-09-02', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '2', '3', null, '010206', '衡阳运用车间', null, null, null, '80.00', '80.00', '80.00', '80.00', '2013-09-01');
INSERT INTO `tb_wheel_record` VALUES ('44', '54956', 'ZX2025', 'RD3A', 'NUK', '1', '0', '80齿', '左边', null, '怀化运用车间', '2013-09-02', '2013-09-02', '2013-09-02', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '1', '0', null, '010206', '衡阳运用车间', null, null, null, '80.00', '80.00', '80.00', '80.00', '2013-09-03');
INSERT INTO `tb_wheel_record` VALUES ('45', '15496', 'ZX2306', 'RD3A1', 'NUK', '1', '0', '80齿', '左边', null, '长沙运用车间', '2013-09-02', '2013-09-02', '2013-09-02', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '0', '1', '2204', '010206', '衡阳运用车间', '1', null, null, '80.00', '80.00', '80.00', '80.00', '2013-09-02');
INSERT INTO `tb_wheel_record` VALUES ('46', '123456', 'ZX2025', 'RD3A', 'NUK', '1', '0', '80齿', '左边', null, '长沙运用车间', '2012-09-03', '2012-08-27', '2012-09-04', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '80.00', '0', '2', null, '010206', '衡阳运用车间', null, null, null, '80.00', '80.00', '80.00', '80.00', '2013-04-07');
INSERT INTO `tb_wheel_stock` VALUES ('9', 'ZX2025', 'RD3A', '5', '4', '1', '0', '1', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('10', 'ZX2025', 'RD3', '1', '0', '1', '0', '0', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('11', 'ZX2306', 'RD4', '1', '1', '0', '0', '0', '0', '0', '010208', '长沙检修车间');
INSERT INTO `tb_wheel_stock` VALUES ('12', 'ZX1208', 'RD4', '1', '1', '0', '0', '0', '0', '0', '010209', '怀化运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('13', 'ZX2306', 'RD4', '0', '0', '0', '0', '1', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('14', 'ZX1208', 'RD4', '1', '1', '0', '0', '0', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('15', 'LD8325', 'RD3A', '1', '1', '0', '0', '0', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('16', 'LD9015', 'RD4A', '1', '1', '0', '0', '0', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('17', 'ZX2679', 'RD3', '1', '1', '0', '0', '0', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('18', 'ZX2258', 'RD3A1', '1', '1', '0', '0', '0', '0', '0', '010206', '衡阳运用车间');
INSERT INTO `tb_wheel_stock` VALUES ('19', 'ZX2306', 'RD3A1', '1', '1', '0', '0', '0', '0', '0', '010206', '衡阳运用车间');
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
