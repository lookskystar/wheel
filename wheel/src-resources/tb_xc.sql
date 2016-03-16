/*
MySQL Data Transfer
Source Host: localhost
Source Database: lundui
Target Host: localhost
Target Database: lundui
Date: 2013/4/28 15:10:32
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_xc
-- ----------------------------
CREATE TABLE `tb_xc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '修程名' COMMENT '如：段修、段做厂修、临修、A2修等',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_xc` VALUES ('1', '新组装');
INSERT INTO `tb_xc` VALUES ('2', '段修');
INSERT INTO `tb_xc` VALUES ('3', '段做厂修');
INSERT INTO `tb_xc` VALUES ('4', '临修');
INSERT INTO `tb_xc` VALUES ('5', '送修');
INSERT INTO `tb_xc` VALUES ('6', 'A2修');
INSERT INTO `tb_xc` VALUES ('7', 'A3修');
INSERT INTO `tb_xc` VALUES ('8', '解体车');
INSERT INTO `tb_xc` VALUES ('9', '其他');
