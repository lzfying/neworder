/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50616
 Source Host           : localhost
 Source Database       : order

 Target Server Version : 50616
 File Encoding         : utf-8

 Date: 07/25/2014 00:56:23 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `back_adminuser`
-- ----------------------------
DROP TABLE IF EXISTS `back_adminuser`;
CREATE TABLE `back_adminuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isAdmin` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `showname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_adminuser`
-- ----------------------------
BEGIN;
INSERT INTO `back_adminuser` VALUES ('1', b'1', '1', 'test', 'test');
COMMIT;

-- ----------------------------
--  Table structure for `back_combo`
-- ----------------------------
DROP TABLE IF EXISTS `back_combo`;
CREATE TABLE `back_combo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA21E103664DD7076` (`price_id`),
  CONSTRAINT `FKA21E103664DD7076` FOREIGN KEY (`price_id`) REFERENCES `back_mealprice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_combo`
-- ----------------------------
BEGIN;
INSERT INTO `back_combo` VALUES ('1', '烧牛腩+香米+蚝油生菜+酸梅汁/甜橙汁', '烧牛腩套餐', null, '16'), ('2', '照烧鸡排+香米+土豆丝+酸梅汁/甜橙汁', '照烧鸡排套餐', null, '17'), ('3', '青椒里脊丝+香米+耗油生菜+酸梅汁/甜橙汁', '青椒里脊丝套餐', null, '18'), ('4', '甜椒土豆丝+香米+酸梅汁/甜橙汁', '甜椒土豆丝套餐', null, '19');
COMMIT;

-- ----------------------------
--  Table structure for `back_combo_back_combodetail`
-- ----------------------------
DROP TABLE IF EXISTS `back_combo_back_combodetail`;
CREATE TABLE `back_combo_back_combodetail` (
  `back_combo_id` bigint(20) NOT NULL,
  `details_id` bigint(20) NOT NULL,
  UNIQUE KEY `details_id` (`details_id`),
  KEY `FKF1DFACF072CD8EAE` (`back_combo_id`),
  KEY `FKF1DFACF019E0E13` (`details_id`),
  CONSTRAINT `FKF1DFACF019E0E13` FOREIGN KEY (`details_id`) REFERENCES `back_combodetail` (`id`),
  CONSTRAINT `FKF1DFACF072CD8EAE` FOREIGN KEY (`back_combo_id`) REFERENCES `back_combo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_combo_back_combodetail`
-- ----------------------------
BEGIN;
INSERT INTO `back_combo_back_combodetail` VALUES ('1', '2'), ('1', '3'), ('2', '4'), ('2', '5'), ('3', '6'), ('3', '7'), ('4', '8'), ('4', '9');
COMMIT;

-- ----------------------------
--  Table structure for `back_combodetail`
-- ----------------------------
DROP TABLE IF EXISTS `back_combodetail`;
CREATE TABLE `back_combodetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `meal_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK504CA80787BF6BFE` (`meal_id`),
  CONSTRAINT `FK504CA80787BF6BFE` FOREIGN KEY (`meal_id`) REFERENCES `back_meal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_combodetail`
-- ----------------------------
BEGIN;
INSERT INTO `back_combodetail` VALUES ('1', '1', '1'), ('2', '1', '1'), ('3', '1', '8'), ('4', '1', '2'), ('5', '1', '8'), ('6', '1', '3'), ('7', '1', '8'), ('8', '1', '4'), ('9', '1', '8');
COMMIT;

-- ----------------------------
--  Table structure for `back_comment`
-- ----------------------------
DROP TABLE IF EXISTS `back_comment`;
CREATE TABLE `back_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `commentstate` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `combo_id` bigint(20) DEFAULT NULL,
  `meal_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK92DFC30785CCCE56` (`combo_id`),
  KEY `FK92DFC30787BF6BFE` (`meal_id`),
  CONSTRAINT `FK92DFC30787BF6BFE` FOREIGN KEY (`meal_id`) REFERENCES `back_meal` (`id`),
  CONSTRAINT `FK92DFC30785CCCE56` FOREIGN KEY (`combo_id`) REFERENCES `back_combo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_customeraddress`
-- ----------------------------
DROP TABLE IF EXISTS `back_customeraddress`;
CREATE TABLE `back_customeraddress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `bakphone` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userDetail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB4ECD2FE7544CA7E` (`userDetail_id`),
  CONSTRAINT `FKB4ECD2FE7544CA7E` FOREIGN KEY (`userDetail_id`) REFERENCES `meta_customerdetail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_meal`
-- ----------------------------
DROP TABLE IF EXISTS `back_meal`;
CREATE TABLE `back_meal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `combodetail_id` bigint(20) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4F91C01BBF56A7D6` (`combodetail_id`),
  KEY `FK4F91C01B64DD7076` (`price_id`),
  KEY `FK4F91C01B2DF76101` (`type_id`),
  CONSTRAINT `FK4F91C01B2DF76101` FOREIGN KEY (`type_id`) REFERENCES `back_meal_type` (`id`),
  CONSTRAINT `FK4F91C01B64DD7076` FOREIGN KEY (`price_id`) REFERENCES `back_mealprice` (`id`),
  CONSTRAINT `FK4F91C01BBF56A7D6` FOREIGN KEY (`combodetail_id`) REFERENCES `back_combodetail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_meal`
-- ----------------------------
BEGIN;
INSERT INTO `back_meal` VALUES ('1', '烧牛腩+香米+蚝油生菜', '烧牛腩盖饭', null, null, '14', '13'), ('2', '照烧鸡排+香米+土豆丝', '照烧鸡排饭', null, null, '10', '10'), ('3', '青椒里脊丝+香米+耗油生菜', '青椒里脊丝盖饭', null, null, '9', '9'), ('4', '甜椒土豆丝+香米', '甜椒土豆丝盖饭', null, null, '8', '8'), ('5', '土豆丝卷饼', '土豆丝卷饼', null, null, '5', '5'), ('6', '清凉解暑，生津止渴', '酸梅汁', null, null, '6', '6'), ('7', '补充维C，美容养颜', '甜橙汁', null, null, '7', '7'), ('8', '酸梅汁/甜橙汁', '酸梅汁/甜橙汁', null, null, '15', '14');
COMMIT;

-- ----------------------------
--  Table structure for `back_meal_back_comment`
-- ----------------------------
DROP TABLE IF EXISTS `back_meal_back_comment`;
CREATE TABLE `back_meal_back_comment` (
  `back_meal_id` bigint(20) NOT NULL,
  `comments_id` bigint(20) NOT NULL,
  UNIQUE KEY `comments_id` (`comments_id`),
  KEY `FK8329B0ABE1F940A6` (`back_meal_id`),
  KEY `FK8329B0ABC35B5601` (`comments_id`),
  CONSTRAINT `FK8329B0ABC35B5601` FOREIGN KEY (`comments_id`) REFERENCES `back_comment` (`id`),
  CONSTRAINT `FK8329B0ABE1F940A6` FOREIGN KEY (`back_meal_id`) REFERENCES `back_meal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_meal_back_tag`
-- ----------------------------
DROP TABLE IF EXISTS `back_meal_back_tag`;
CREATE TABLE `back_meal_back_tag` (
  `back_meal_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  PRIMARY KEY (`back_meal_id`,`tags_id`),
  KEY `FKFFFA87E64E96D4AB` (`tags_id`),
  KEY `FKFFFA87E6E1F940A6` (`back_meal_id`),
  CONSTRAINT `FKFFFA87E6E1F940A6` FOREIGN KEY (`back_meal_id`) REFERENCES `back_meal` (`id`),
  CONSTRAINT `FKFFFA87E64E96D4AB` FOREIGN KEY (`tags_id`) REFERENCES `back_tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_meal_type`
-- ----------------------------
DROP TABLE IF EXISTS `back_meal_type`;
CREATE TABLE `back_meal_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mealType` int(11) NOT NULL,
  `meal_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK990A60DE87BF6BFE` (`meal_id`),
  CONSTRAINT `FK990A60DE87BF6BFE` FOREIGN KEY (`meal_id`) REFERENCES `back_meal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_meal_type`
-- ----------------------------
BEGIN;
INSERT INTO `back_meal_type` VALUES ('1', '1', null), ('2', '1', null), ('3', '1', null), ('4', '1', null), ('5', '1', null), ('6', '3', null), ('7', '3', null), ('8', '1', null), ('9', '1', null), ('10', '1', null), ('11', '1', null), ('12', '3', null), ('13', '1', null), ('14', '3', null);
COMMIT;

-- ----------------------------
--  Table structure for `back_mealdesc`
-- ----------------------------
DROP TABLE IF EXISTS `back_mealdesc`;
CREATE TABLE `back_mealdesc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_mealprice`
-- ----------------------------
DROP TABLE IF EXISTS `back_mealprice`;
CREATE TABLE `back_mealprice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `discount` double NOT NULL,
  `price` double NOT NULL,
  `strategy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_mealprice`
-- ----------------------------
BEGIN;
INSERT INTO `back_mealprice` VALUES ('1', '10', '15', null), ('2', '10', '11', null), ('3', '10', '10', null), ('4', '10', '8', null), ('5', '10', '8', null), ('6', '10', '1.5', null), ('7', '10', '2', null), ('8', '10', '8', null), ('9', '10', '10', null), ('10', '10', '11', null), ('11', '10', '15', null), ('12', '10', '15', null), ('13', '10', '2', null), ('14', '10', '15', null), ('15', '10', '2', null), ('16', '10', '15', null), ('17', '10', '11', null), ('18', '10', '10', null), ('19', '10', '8', null);
COMMIT;

-- ----------------------------
--  Table structure for `back_order`
-- ----------------------------
DROP TABLE IF EXISTS `back_order`;
CREATE TABLE `back_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `orderNum` varchar(255) DEFAULT NULL,
  `orderPrice` int(11) NOT NULL,
  `orderstate` varchar(255) DEFAULT NULL,
  `payWay` varchar(255) DEFAULT NULL,
  `receiver_addr` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `receiver_other` varchar(255) DEFAULT NULL,
  `receiver_tel` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA2C865F647140EFE` (`user_id`),
  CONSTRAINT `FKA2C865F647140EFE` FOREIGN KEY (`user_id`) REFERENCES `meta_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_order_back_orderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `back_order_back_orderdetail`;
CREATE TABLE `back_order_back_orderdetail` (
  `back_order_id` bigint(20) NOT NULL,
  `orderDetails_id` bigint(20) NOT NULL,
  KEY `FK6F7694F0E188B6AE` (`back_order_id`),
  KEY `FK6F7694F0862451A1` (`orderDetails_id`),
  CONSTRAINT `FK6F7694F0862451A1` FOREIGN KEY (`orderDetails_id`) REFERENCES `back_orderdetail` (`id`),
  CONSTRAINT `FK6F7694F0E188B6AE` FOREIGN KEY (`back_order_id`) REFERENCES `back_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_orderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `back_orderdetail`;
CREATE TABLE `back_orderdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) DEFAULT NULL,
  `mealName` varchar(255) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `price` double NOT NULL,
  `priceType` varchar(255) DEFAULT NULL,
  `totalNum` int(11) NOT NULL,
  `combo_id` bigint(20) DEFAULT NULL,
  `meal_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC7CFADC785CCCE56` (`combo_id`),
  KEY `FKC7CFADC787BF6BFE` (`meal_id`),
  CONSTRAINT `FKC7CFADC787BF6BFE` FOREIGN KEY (`meal_id`) REFERENCES `back_meal` (`id`),
  CONSTRAINT `FKC7CFADC785CCCE56` FOREIGN KEY (`combo_id`) REFERENCES `back_combo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_tag`
-- ----------------------------
DROP TABLE IF EXISTS `back_tag`;
CREATE TABLE `back_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_treenode`
-- ----------------------------
DROP TABLE IF EXISTS `back_treenode`;
CREATE TABLE `back_treenode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_treenode`
-- ----------------------------
BEGIN;
INSERT INTO `back_treenode` VALUES ('1', null, '系统管理', '0', '0'), ('2', '/admin/order/orderadmin', '订单管理', '1', '1'), ('3', '/admin/user/useradmin', '用户管理', '1', '1'), ('4', '/admin/meal/mealadmin', '菜品管理', '1', '1'), ('5', '/admin/combo/comboadmin', '套餐管理', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `dic_area`
-- ----------------------------
DROP TABLE IF EXISTS `dic_area`;
CREATE TABLE `dic_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dic_meal_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_meal_type`;
CREATE TABLE `dic_meal_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dic_meal_type`
-- ----------------------------
BEGIN;
INSERT INTO `dic_meal_type` VALUES ('1', '菜品', '1'), ('2', '套餐', '2'), ('3', '饮料', '3');
COMMIT;

-- ----------------------------
--  Table structure for `meta_customer`
-- ----------------------------
DROP TABLE IF EXISTS `meta_customer`;
CREATE TABLE `meta_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `userDetail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC7DB90D87544CA7E` (`userDetail_id`),
  CONSTRAINT `FKC7DB90D87544CA7E` FOREIGN KEY (`userDetail_id`) REFERENCES `meta_customerdetail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `meta_customer_back_customeraddress`
-- ----------------------------
DROP TABLE IF EXISTS `meta_customer_back_customeraddress`;
CREATE TABLE `meta_customer_back_customeraddress` (
  `meta_customer_id` bigint(20) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  UNIQUE KEY `address_id` (`address_id`),
  KEY `FK5A83D405F7F732D1` (`meta_customer_id`),
  KEY `FK5A83D405699AD94B` (`address_id`),
  CONSTRAINT `FK5A83D405699AD94B` FOREIGN KEY (`address_id`) REFERENCES `back_customeraddress` (`id`),
  CONSTRAINT `FK5A83D405F7F732D1` FOREIGN KEY (`meta_customer_id`) REFERENCES `meta_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `meta_customerdetail`
-- ----------------------------
DROP TABLE IF EXISTS `meta_customerdetail`;
CREATE TABLE `meta_customerdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mail` varchar(255) DEFAULT NULL,
  `point` int(11) NOT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `sex` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `meta_customerdetail_back_customeraddress`
-- ----------------------------
DROP TABLE IF EXISTS `meta_customerdetail_back_customeraddress`;
CREATE TABLE `meta_customerdetail_back_customeraddress` (
  `meta_customerdetail_id` bigint(20) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  UNIQUE KEY `address_id` (`address_id`),
  KEY `FKFD1D8A54699AD94B` (`address_id`),
  KEY `FKFD1D8A54C3C48411` (`meta_customerdetail_id`),
  CONSTRAINT `FKFD1D8A54C3C48411` FOREIGN KEY (`meta_customerdetail_id`) REFERENCES `meta_customerdetail` (`id`),
  CONSTRAINT `FKFD1D8A54699AD94B` FOREIGN KEY (`address_id`) REFERENCES `back_customeraddress` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
