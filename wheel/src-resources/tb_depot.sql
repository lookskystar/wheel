/*
MySQL Data Transfer
Source Host: localhost
Source Database: lundui
Target Host: localhost
Target Database: lundui
Date: 2013/5/8 17:56:04
*/

SET FOREIGN_KEY_CHECKS=0;
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
-- Records 
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
