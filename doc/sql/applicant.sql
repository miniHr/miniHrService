/*
Navicat MySQL Data Transfer

Source Server         : miniHr
Source Server Version : 50095
Source Host           : 116.62.209.238:3306
Source Database       : miniHr

Target Server Type    : MYSQL
Target Server Version : 50095
File Encoding         : 65001

Date: 2017-05-21 16:13:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `applicant`
-- ----------------------------
DROP TABLE IF EXISTS `applicant`;
CREATE TABLE `applicant` (
  `id` int(11) NOT NULL auto_increment,
  `type` int(10) NOT NULL,
  `count` int(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applicant
-- ----------------------------
INSERT INTO `applicant` VALUES ('1', '1', '900');
INSERT INTO `applicant` VALUES ('2', '2', '705');
INSERT INTO `applicant` VALUES ('3', '7', '457');
INSERT INTO `applicant` VALUES ('4', '8', '559');
INSERT INTO `applicant` VALUES ('5', '9', '533');
INSERT INTO `applicant` VALUES ('6', '10', '560');
