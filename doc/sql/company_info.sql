/*
Navicat MySQL Data Transfer

Source Server         : testminiHr
Source Server Version : 50095
Source Host           : 116.62.209.238:3306
Source Database       : testminiHr

Target Server Type    : MYSQL
Target Server Version : 50095
File Encoding         : 65001

Date: 2017-05-31 20:51:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `company_info`
-- ----------------------------
DROP TABLE IF EXISTS `COMPANY_INFO`;
CREATE TABLE `COMPANY_INFO` (
  `ID` int(11) NOT NULL auto_increment COMMENT '公司ID',
  `COMPANY_NAME` varchar(32) NOT NULL default '' COMMENT '公司名',
  `IMAGE` varchar(16) default '' COMMENT '公司图标',
  `SCALE` varchar(16) default '' COMMENT '公司规模',
  `ADDRESS` varchar(64) default '' COMMENT '公司地址',
  `WELFARE` varchar(64) default '' COMMENT '公司福利',
  `NAME` varchar(16) NOT NULL default '' COMMENT '联系人',
  `PHONE` varchar(16) NOT NULL default '' COMMENT '联系电话',
  `BOOTH_ID` int(8) NOT NULL COMMENT '展位ID',
  `CREATE_DT` timestamp NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATER` varchar(19) default '' COMMENT '创建人',
  `UPDATE_DT` timestamp NULL default NULL on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `UPDATER` varchar(19) default '' COMMENT '更新人',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

