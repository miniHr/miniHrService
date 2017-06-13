/*
Navicat MySQL Data Transfer

Source Server         : testminiHr
Source Server Version : 50520
Source Host           : 116.62.209.238:3306
Source Database       : testminiHr

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-06-13 16:17:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `JOB`
-- ----------------------------
DROP TABLE IF EXISTS `JOB`;
CREATE TABLE `JOB` (
  `ID` int(8) NOT NULL AUTO_INCREMENT COMMENT '职位ID',
  `COMPANY_ID` int(8) NOT NULL COMMENT '公司ID',
  `JOB_NAME` varchar(16) DEFAULT NULL COMMENT '职位名称',
  `JOB_DETAIL` varchar(128) NOT NULL COMMENT '职位详情',
  `JOB_SALARY` varchar(16) NOT NULL,
  `GENDER` char(1) DEFAULT NULL COMMENT '性别要求',
  `AGE` varchar(16) DEFAULT NULL COMMENT '年龄要求',
  `INDUSTRY` varchar(16) DEFAULT NULL COMMENT '行业要求',
  `WORKTIME` int(4) DEFAULT NULL COMMENT '从业年龄要求',
  `EDUCATION` varchar(16) DEFAULT NULL COMMENT '学历要求',
  `MAJOR` varchar(32) DEFAULT NULL COMMENT '专业要求',
  `CREATE_DT` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(19) DEFAULT NULL COMMENT '创建人',
  `UPDATE_DT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `UPDATER` varchar(19) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='职位表';
