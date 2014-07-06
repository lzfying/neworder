/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50616
 Source Host           : localhost
 Source Database       : order

 Target Server Version : 50616
 File Encoding         : utf-8

 Date: 07/06/2014 16:58:22 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_combo`
-- ----------------------------
BEGIN;
INSERT INTO `back_combo` VALUES ('4', '111', '111', null, '13'), ('9', '123123', '123123', null, '19');
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
INSERT INTO `back_combo_back_combodetail` VALUES ('4', '1'), ('4', '2'), ('4', '14'), ('4', '15'), ('4', '16'), ('9', '24'), ('9', '25'), ('9', '26');
COMMIT;

-- ----------------------------
--  Table structure for `back_combodetail`
-- ----------------------------
DROP TABLE IF EXISTS `back_combodetail`;
CREATE TABLE `back_combodetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meal` tinyblob,
  `num` int(11) NOT NULL,
  `meal_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK504CA80787BF6BFE` (`meal_id`),
  CONSTRAINT `FK504CA80787BF6BFE` FOREIGN KEY (`meal_id`) REFERENCES `back_meal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_combodetail`
-- ----------------------------
BEGIN;
INSERT INTO `back_combodetail` VALUES ('1', null, '1', '2'), ('2', null, '1', '1'), ('5', null, '1', '2'), ('6', null, '1', '3'), ('7', null, '1', '1'), ('14', null, '1', '1'), ('15', null, '1', '2'), ('16', null, '1', '1'), ('21', null, '1', '1'), ('22', null, '1', '1'), ('23', null, '1', '2'), ('24', null, '1', '1'), ('25', null, '1', '2'), ('26', null, '1', '5');
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
  CONSTRAINT `FK92DFC30785CCCE56` FOREIGN KEY (`combo_id`) REFERENCES `back_combo` (`id`),
  CONSTRAINT `FK92DFC30787BF6BFE` FOREIGN KEY (`meal_id`) REFERENCES `back_meal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `back_customeraddress`
-- ----------------------------
DROP TABLE IF EXISTS `back_customeraddress`;
CREATE TABLE `back_customeraddress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userDetail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB4ECD2FE7544CA7E` (`userDetail_id`),
  CONSTRAINT `FKB4ECD2FE7544CA7E` FOREIGN KEY (`userDetail_id`) REFERENCES `meta_customerdetail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_customeraddress`
-- ----------------------------
BEGIN;
INSERT INTO `back_customeraddress` VALUES ('1', '123123', '370199', '123123123', null), ('2', '123123', '370199', '123123123', null), ('3', '123123123', '370199', '123123123123', null), ('4', '1111', '370199user.userDetail.address[1].address=2222', '1111', null), ('5', null, '370199', '2222', null), ('6', 'null', '370199', '2222', null), ('7', '123123', '370199', '123123123', null), ('8', '123123', '370199', '123123123', null), ('9', '123123', '370199', '123123123', null), ('10', '123123', '370199user.userDetail.address[1].address=ttttt', '123123123', null), ('11', null, '370199', 'ttttttttt', null), ('12', '123123', '370199user.userDetail.address[1].address=tttttuser.userDetail.address[1].address=null', '123123123', null), ('13', null, '370199user.userDetail.address[2].address=eeeee', 'ttttttttt', null), ('14', null, '370199', 'eeeeeee', null), ('19', '123123', '370199', '123123', null), ('20', '123123', '370199user.userDetail.address[1].address=eeee', '123123', null), ('21', null, '370199', 'eeeee', null), ('22', '123123', '370199user.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=rrrr', '123123', null), ('23', null, '370199', 'rrrr', null), ('24', '123123', '370199user.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=rrrruser.userDetail.address[1].address=eeee', '123123', null), ('25', null, '370199', 'eeee', null), ('26', '123123', '370199user.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=rrrruser.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=eeeee', '123123', null), ('27', null, '370199', 'eeeee', null), ('28', '123123', '370199user.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=rrrruser.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=eeeeeuser.userDetail.address[1].address=eeeee', '123123', null), ('29', null, '370199', 'eeeee', null), ('30', '123123', '370199user.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=rrrruser.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=eeeeeuser.userDetail.address[1].address=eeeee', '123123', null), ('31', 'ffff', '370199', 'fffff', null), ('32', '123123', '370199user.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=rrrruser.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=eeeeeuser.userDetail.address[1].address=eeeee', '123123', null), ('33', 'ffff', '370199', 'fffff', null), ('34', 'eeeee', '370199', 'eeeee', null), ('35', '123123', '370199user.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=rrrruser.userDetail.address[1].address=eeeeuser.userDetail.address[1].address=eeeeeuser.userDetail.address[1].address=eeeee', '123123', null), ('36', 'ffff', '370199', 'fffff', null), ('37', 'eeeee', '370199', 'eeeee', null), ('38', 'qqqqqeeee', '370199', 'qqqqqeee', null), ('39', '1222lsie', '370199', '123222311', null);
COMMIT;

-- ----------------------------
--  Table structure for `back_meal`
-- ----------------------------
DROP TABLE IF EXISTS `back_meal`;
CREATE TABLE `back_meal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `combo_id` bigint(20) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `combodetail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4F91C01B64DD7076` (`price_id`),
  KEY `FK4F91C01B85CCCE56` (`combo_id`),
  KEY `FK4F91C01B2DF76101` (`type_id`),
  KEY `FK4F91C01BBF56A7D6` (`combodetail_id`),
  CONSTRAINT `FK4F91C01B2DF76101` FOREIGN KEY (`type_id`) REFERENCES `back_meal_type` (`id`),
  CONSTRAINT `FK4F91C01B64DD7076` FOREIGN KEY (`price_id`) REFERENCES `back_mealprice` (`id`),
  CONSTRAINT `FK4F91C01B85CCCE56` FOREIGN KEY (`combo_id`) REFERENCES `back_combo` (`id`),
  CONSTRAINT `FK4F91C01BBF56A7D6` FOREIGN KEY (`combodetail_id`) REFERENCES `back_combodetail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_meal`
-- ----------------------------
BEGIN;
INSERT INTO `back_meal` VALUES ('1', '11111', '1111', null, null, '1', '1', null), ('2', '2222', '2222', null, null, '8', '4', null), ('3', '1111', '2222', null, null, '7', '3', null), ('5', '33333', '333', null, null, '18', '6', null);
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
  CONSTRAINT `FKFFFA87E64E96D4AB` FOREIGN KEY (`tags_id`) REFERENCES `back_tag` (`id`),
  CONSTRAINT `FKFFFA87E6E1F940A6` FOREIGN KEY (`back_meal_id`) REFERENCES `back_meal` (`id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_meal_type`
-- ----------------------------
BEGIN;
INSERT INTO `back_meal_type` VALUES ('1', '1', null), ('2', '2', null), ('3', '2', null), ('4', '2', null), ('6', '3', null);
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
INSERT INTO `back_mealprice` VALUES ('1', '1', '1111', null), ('2', '2', '222', null), ('6', '1', '1', null), ('7', '2', '222', null), ('8', '2', '2221111', null), ('10', '1', '1', null), ('13', '1', '1', null), ('15', '1', '1231231231', null), ('16', '1', '1231231231', null), ('18', '33', '33', null), ('19', '1', '1231231231', null);
COMMIT;

-- ----------------------------
--  Table structure for `back_order`
-- ----------------------------
DROP TABLE IF EXISTS `back_order`;
CREATE TABLE `back_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `orderNum` varchar(255) DEFAULT NULL,
  `orderPrice` double NOT NULL,
  `orderstate` varchar(255) DEFAULT NULL,
  `payWay` varchar(255) DEFAULT NULL,
  `receiver_addr` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `receiver_other` varchar(255) DEFAULT NULL,
  `receiver_tel` varchar(255) DEFAULT NULL,
  `User_id` bigint(20) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA2C865F647140EFE` (`User_id`),
  CONSTRAINT `FKA2C865F647140EFE` FOREIGN KEY (`User_id`) REFERENCES `meta_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_order`
-- ----------------------------
BEGIN;
INSERT INTO `back_order` VALUES ('18', '2014-06-19 17:52:48', '140619175248136', '123123', '3', null, '123123', '123123', '123123', '123123', null, '370199'), ('19', '2014-06-19 17:56:22', '140619175622031', '123123', '1', null, '123123', '123123', '123123', '123123', null, '370199'), ('21', '2014-06-19 23:20:51', '140619232051655', '123123', '1', null, '123123123123', '123123', '123123', '123123', null, null), ('22', '2014-06-19 23:26:13', '140619232613619', '123123', '2', null, '123123123123', '123123', '123123', '123123', null, '370199'), ('23', '2014-06-20 11:26:38', '140620112638227', '12.22', '1', null, '舜泰广场11F', '王小猪', '不要辣', '12300021331', null, '370199');
COMMIT;

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
--  Records of `back_order_back_orderdetail`
-- ----------------------------
BEGIN;
INSERT INTO `back_order_back_orderdetail` VALUES ('21', '50'), ('19', '53'), ('19', '54'), ('18', '59'), ('18', '60'), ('22', '64'), ('22', '65'), ('22', '66'), ('22', '67'), ('23', '71'), ('23', '72'), ('23', '73'), ('23', '74');
COMMIT;

-- ----------------------------
--  Table structure for `back_orderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `back_orderdetail`;
CREATE TABLE `back_orderdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `price` double NOT NULL,
  `priceType` varchar(255) DEFAULT NULL,
  `totalNum` int(11) NOT NULL,
  `combo_id` bigint(20) DEFAULT NULL,
  `meal_id` bigint(20) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC7CFADC785CCCE56` (`combo_id`),
  KEY `FKC7CFADC787BF6BFE` (`meal_id`),
  CONSTRAINT `FKC7CFADC785CCCE56` FOREIGN KEY (`combo_id`) REFERENCES `back_combo` (`id`),
  CONSTRAINT `FKC7CFADC787BF6BFE` FOREIGN KEY (`meal_id`) REFERENCES `back_meal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `back_orderdetail`
-- ----------------------------
BEGIN;
INSERT INTO `back_orderdetail` VALUES ('43', '1', '1111', null, '0', null, '1', null), ('44', '1', '222', null, '0', null, '2', null), ('45', '1', '1', null, '0', '4', null, null), ('48', '1', '222', null, '0', null, '2', null), ('49', '1', '1', null, '0', '4', null, null), ('50', '1', '1', null, '0', '4', null, null), ('51', '1', '1111', null, '0', null, '1', null), ('52', '1', '1', null, '0', '4', null, null), ('53', '1', '222', null, '0', null, '2', null), ('54', '1', '1', null, '0', '4', null, null), ('55', '1', '222', null, '0', null, '2', null), ('56', '1', '1111', null, '0', null, '1', null), ('57', '1', '222', null, '0', null, '2', null), ('58', '1', '1', null, '0', '4', null, null), ('59', '1', '222', null, '0', null, '2', null), ('60', '1', '1', null, '0', '4', null, null), ('61', '1', '1111', null, '0', null, '1', null), ('62', '1', '222', null, '0', null, '2', null), ('63', '1', '1', null, '0', '4', null, null), ('64', '1', '1111', null, '0', null, '1', null), ('65', '1', '222', null, '0', null, '2', null), ('66', '1', '222', null, '0', null, '2', null), ('67', '1', '1', null, '0', '4', null, null), ('68', '1', '222', null, '0', null, '2', null), ('69', '1', '222', null, '0', null, '2', null), ('70', '1', '1', null, '0', '4', null, null), ('71', '1', '2221111', null, '0', null, '2', null), ('72', '1', '2221111', null, '0', null, '2', null), ('73', '1', '1', null, '0', '4', null, null), ('74', '1', '1', null, '0', '4', null, null);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dic_area`
-- ----------------------------
BEGIN;
INSERT INTO `dic_area` VALUES ('1', '370102', '历下区'), ('2', '370199', '高新区');
COMMIT;

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
INSERT INTO `dic_meal_type` VALUES ('1', '主食', '1'), ('2', '菜品', '2'), ('3', '饮料', '3');
COMMIT;

-- ----------------------------
--  Table structure for `meta_customer`
-- ----------------------------
DROP TABLE IF EXISTS `meta_customer`;
CREATE TABLE `meta_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `userDetail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC7DB90D87544CA7E` (`userDetail_id`),
  CONSTRAINT `FKC7DB90D87544CA7E` FOREIGN KEY (`userDetail_id`) REFERENCES `meta_customerdetail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `meta_customer`
-- ----------------------------
BEGIN;
INSERT INTO `meta_customer` VALUES ('2', '123123', null, '123123', '22'), ('3', 'wangxiaozhu@222.com', null, '王小猪', '23');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `meta_customerdetail`
-- ----------------------------
BEGIN;
INSERT INTO `meta_customerdetail` VALUES ('1', null, '0', '123132123', '1'), ('2', null, '0', '123132123', '1'), ('3', null, '0', '123123', '1'), ('4', null, '0', '123123', '1'), ('5', null, '0', '123123', '1'), ('6', null, '0', '123123', '1'), ('7', null, '0', '123123', '1'), ('8', null, '0', '123132123', '1'), ('9', null, '0', '123132123', '1'), ('10', null, '0', '123132123', '1'), ('11', null, '0', '123132123', '1'), ('12', null, '0', '123132123', '1'), ('14', null, '0', '123123', '1'), ('15', null, '0', '123123', '1'), ('16', null, '0', '123123', '1'), ('17', null, '0', '123123', '1'), ('18', null, '0', '123123', '1'), ('19', null, '0', '123123', '1'), ('20', null, '0', '123123', '1'), ('21', null, '0', '123123', '1'), ('22', null, '0', '123123', '1'), ('23', null, '0', 'wxz', '2');
COMMIT;

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
  CONSTRAINT `FKFD1D8A54699AD94B` FOREIGN KEY (`address_id`) REFERENCES `back_customeraddress` (`id`),
  CONSTRAINT `FKFD1D8A54C3C48411` FOREIGN KEY (`meta_customerdetail_id`) REFERENCES `meta_customerdetail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `meta_customerdetail_back_customeraddress`
-- ----------------------------
BEGIN;
INSERT INTO `meta_customerdetail_back_customeraddress` VALUES ('1', '1'), ('2', '2'), ('3', '3'), ('5', '4'), ('5', '5'), ('6', '6'), ('8', '7'), ('9', '8'), ('10', '9'), ('11', '10'), ('11', '11'), ('12', '12'), ('12', '13'), ('12', '14'), ('14', '19'), ('15', '20'), ('15', '21'), ('16', '22'), ('16', '23'), ('17', '24'), ('17', '25'), ('18', '26'), ('18', '27'), ('19', '28'), ('19', '29'), ('20', '30'), ('20', '31'), ('21', '32'), ('21', '33'), ('21', '34'), ('22', '35'), ('22', '36'), ('22', '37'), ('22', '38'), ('23', '39');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
