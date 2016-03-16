/*
MySQL Data Transfer
Source Host: localhost
Source Database: lundui
Target Host: localhost
Target Database: lundui
Date: 2013/9/17 23:08:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_factory_repair
-- ----------------------------
CREATE TABLE `tb_factory_repair` (
  `id` int(11) NOT NULL auto_increment,
  `jc_num` varchar(100) NOT NULL,
  `depot_name` varchar(100) NOT NULL,
  `depot_code` varchar(100) NOT NULL,
  `repair_date` datetime default NULL,
  `factory` varchar(100) default NULL,
  `reason` varchar(200) default NULL,
  `src_record_1` int(11) default NULL,
  `src_record_2` int(11) default NULL,
  `src_record_3` int(11) default NULL,
  `src_record_4` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
