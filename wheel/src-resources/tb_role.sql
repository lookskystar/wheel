/*
MySQL Data Transfer
Source Host: localhost
Source Database: lundui
Target Host: localhost
Target Database: lundui
Date: 2013/4/22 14:45:35
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
CREATE TABLE `tb_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_mark` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '集团领导', 'JTLD');
INSERT INTO `tb_role` VALUES ('2', '车辆段工人', 'CLDGR');
INSERT INTO `tb_role` VALUES ('3', '车辆段工长', 'CLDGZ');
INSERT INTO `tb_role` VALUES ('4', '车辆段质检员', 'CLDZJY');
INSERT INTO `tb_role` VALUES ('5', '车辆厂工人', 'CLCGR');
INSERT INTO `tb_role` VALUES ('6', '车辆厂工长', 'CLCGZ');
INSERT INTO `tb_role` VALUES ('7', '车辆厂质检员', 'CLCZJY');
