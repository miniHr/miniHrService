/*
Navicat MySQL Data Transfer

Source Server         : miniHrRoot
Source Server Version : 50520
Source Host           : 116.62.209.238:3306
Source Database       : testminiHr

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-06-12 10:32:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `RESUME`
-- ----------------------------
DROP TABLE IF EXISTS `RESUME`;
CREATE TABLE `RESUME` (
  `ID` int(16) NOT NULL AUTO_INCREMENT COMMENT '简历编号',
  `USER_ID` int(8) NOT NULL COMMENT '用户ID',
  `JOB_ID` int(8) NOT NULL COMMENT '职位ID',
  `COMPANY_ID` int(8) NOT NULL COMMENT '公司ID',
  `STATE` char(1) NOT NULL COMMENT '简历等级',
  `CREATE_DT` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(19) DEFAULT NULL COMMENT '创建人',
  `UPDATE_DT` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `UPDATER` varchar(19) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
