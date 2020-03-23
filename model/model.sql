/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : model

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 19/07/2019 00:02:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `b_bID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `b_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `b_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `b_month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `b_money` float(10, 2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bonus
-- ----------------------------
INSERT INTO `bonus` VALUES (1, '372401195506012858', '371522199904018483', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (2, '37242319660625462X', '372401195506012858', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (3, '372428195601100039', '37242319660625462X', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (4, '370125196111013036', '372401195506012858', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (5, '372401195512180575', '37242319660625462X', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (6, '372401195908280038', '370125196111013036', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (7, '371402197308012616', '370125196111013036', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (8, '372426196706300346', '372401195908280038', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (9, '372421196706173971', '372401195908280038', '2019', '06', 2000.00);
INSERT INTO `bonus` VALUES (10, '372401197604030020', '372401195512180575', '2019', '06', 2000.00);

-- ----------------------------
-- Table structure for bonus_record
-- ----------------------------
DROP TABLE IF EXISTS `bonus_record`;
CREATE TABLE `bonus_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_FID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bonus_record
-- ----------------------------
INSERT INTO `bonus_record` VALUES (1, '刘佃法', '372401195506012858', '371522199904018483', '2019-06-11 09:07:39', '2000.0');
INSERT INTO `bonus_record` VALUES (2, '吕桂荣', '37242319660625462X', '372401195506012858', '2019-06-11 14:59:33', '2000.0');
INSERT INTO `bonus_record` VALUES (3, '鲍明胜', '372428195601100039', '37242319660625462X', '2019-06-11 16:23:36', '2000.0');
INSERT INTO `bonus_record` VALUES (4, '尹振东', '370125196111013036', '372401195506012858', '2019-06-11 16:57:38', '2000.0');
INSERT INTO `bonus_record` VALUES (5, '王昌盛', '372401195512180575', '37242319660625462X', '2019-06-11 17:02:06', '2000.0');
INSERT INTO `bonus_record` VALUES (6, '尚明强', '372401195908280038', '370125196111013036', '2019-06-11 17:36:59', '2000.0');
INSERT INTO `bonus_record` VALUES (7, '于学斗', '371402197308012616', '370125196111013036', '2019-06-11 17:37:50', '2000.0');
INSERT INTO `bonus_record` VALUES (8, '王伟', '372426196706300346', '372401195908280038', '2019-06-11 17:40:03', '2000.0');
INSERT INTO `bonus_record` VALUES (9, '乔海军', '372421196706173971', '372401195908280038', '2019-06-12 16:50:17', '2000.0');
INSERT INTO `bonus_record` VALUES (10, '秦风艳', '372401197604030020', '372401195512180575', '2019-06-13 16:04:09', '2000.0');

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `card_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `card_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cash
-- ----------------------------
DROP TABLE IF EXISTS `cash`;
CREATE TABLE `cash`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `c_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_acount` double(11, 2) NOT NULL,
  `c_total` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `c_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for checka
-- ----------------------------
DROP TABLE IF EXISTS `checka`;
CREATE TABLE `checka`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `FID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for consume
-- ----------------------------
DROP TABLE IF EXISTS `consume`;
CREATE TABLE `consume`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_win` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_omoney` decimal(10, 2) NOT NULL,
  `c_agio` decimal(10, 2) NOT NULL,
  `c_price` decimal(10, 2) NOT NULL,
  `c_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consume
-- ----------------------------
INSERT INTO `consume` VALUES (1, '德州白酒', '372401195506012858', 2000.00, 1.00, 2000.00, '2019-06-11 09:07:39', '平台支付');
INSERT INTO `consume` VALUES (2, '德州白酒', '37242319660625462X', 2000.00, 1.00, 2000.00, '2019-06-11 14:59:33', '平台支付');
INSERT INTO `consume` VALUES (3, '德州白酒', '372428195601100039', 2000.00, 1.00, 2000.00, '2019-06-11 16:23:36', '平台支付');
INSERT INTO `consume` VALUES (4, '德州白酒', '370125196111013036', 2000.00, 1.00, 2000.00, '2019-06-11 16:57:38', '平台支付');
INSERT INTO `consume` VALUES (5, '德州白酒', '372401195512180575', 2000.00, 1.00, 2000.00, '2019-06-11 17:02:06', '平台支付');
INSERT INTO `consume` VALUES (6, '德州白酒', '372401195908280038', 2000.00, 1.00, 2000.00, '2019-06-11 17:36:59', '平台支付');
INSERT INTO `consume` VALUES (7, '德州白酒', '371402197308012616', 2000.00, 1.00, 2000.00, '2019-06-11 17:37:50', '平台支付');
INSERT INTO `consume` VALUES (8, '德州白酒', '372426196706300346', 2000.00, 1.00, 2000.00, '2019-06-11 17:40:03', '平台支付');
INSERT INTO `consume` VALUES (9, '德州白酒', '372421196706173971', 2000.00, 1.00, 2000.00, '2019-06-12 16:50:17', '平台支付');
INSERT INTO `consume` VALUES (10, '德州白酒', '372401197604030020', 2000.00, 1.00, 2000.00, '2019-06-13 16:04:09', '平台支付');

-- ----------------------------
-- Table structure for formal_order
-- ----------------------------
DROP TABLE IF EXISTS `formal_order`;
CREATE TABLE `formal_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_price` float(10, 2) NOT NULL,
  `order_number` int(11) NOT NULL,
  `order_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_cope` tinyint(1) DEFAULT NULL,
  `order_wineid` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of formal_order
-- ----------------------------
INSERT INTO `formal_order` VALUES (1, 2000.00, 1, '2019-06-11 09:07:39', '德州', '13705345982', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (2, 2000.00, 1, '2019-06-11 14:59:33', '德州', '13705345759', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (3, 2000.00, 1, '2019-06-11 16:23:36', '德州市经济开发区天玉铭城小区', '13605347229', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (4, 2000.00, 1, '2019-06-11 16:57:38', '德州德城区消防支队家属院', '13869227398', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (5, 2000.00, 1, '2019-06-11 17:02:06', '德州', '13805342666', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (6, 2000.00, 1, '2019-06-11 17:36:59', '德州', '13805349868', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (7, 2000.00, 1, '2019-06-11 17:37:50', '德州', '13573451861', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (8, 2000.00, 1, '2019-06-11 17:40:03', '德州', '13605342728', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (9, 2000.00, 1, '2019-06-12 16:50:17', '德州', '18353489075', '注意运输', 0, 1);
INSERT INTO `formal_order` VALUES (10, 2000.00, 1, '2019-06-13 16:04:09', '德城区鲁班御景园', '13953419879', '注意运输', 0, 1);

-- ----------------------------
-- Table structure for last_balance
-- ----------------------------
DROP TABLE IF EXISTS `last_balance`;
CREATE TABLE `last_balance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bonus_account` float(10, 2) DEFAULT NULL,
  `branch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `addtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for level
-- ----------------------------
DROP TABLE IF EXISTS `level`;
CREATE TABLE `level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `level_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `level_fID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of level
-- ----------------------------
INSERT INTO `level` VALUES (1, '372401195506012858', '13705345982', '371522199904018483&L');
INSERT INTO `level` VALUES (2, '37242319660625462X', '13705345759', '372401195506012858&L');
INSERT INTO `level` VALUES (3, '370125196111013036', '13869227398', '372401195506012858&R');
INSERT INTO `level` VALUES (4, '372428195601100039', '13605347229', '37242319660625462X&L');
INSERT INTO `level` VALUES (5, '372401195512180575', '13805342666', '37242319660625462X&R');
INSERT INTO `level` VALUES (6, '372401195908280038', '13805349868', '370125196111013036&L');
INSERT INTO `level` VALUES (7, '371402197308012616', '13573451861', '370125196111013036&R');
INSERT INTO `level` VALUES (8, '372426196706300346', '13605342728', '372401195908280038&L');
INSERT INTO `level` VALUES (9, '372421196706173971', '18353489075', '372401195908280038&R');
INSERT INTO `level` VALUES (10, '372401197604030020', '13953419879', '372401195512180575&L');

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_olevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `p_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_acount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_ordernumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `r_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `userId` int(4) DEFAULT NULL,
  `roleId` int(4) DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for settlement
-- ----------------------------
DROP TABLE IF EXISTS `settlement`;
CREATE TABLE `settlement`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `s_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_sale` float(10, 2) NOT NULL,
  `s_achievement` float(10, 2) NOT NULL,
  `s_subtotal` float(10, 2) NOT NULL,
  `s_Winconsume` float(10, 2) UNSIGNED DEFAULT NULL,
  `s_Deconsume` float(10, 2) UNSIGNED DEFAULT NULL,
  `s_deposit` float(10, 2) DEFAULT NULL,
  `s_balance` float(10, 2) DEFAULT NULL,
  `s_big` float(10, 2) DEFAULT NULL,
  `s_small` float(10, 2) DEFAULT NULL,
  `s_total` float(10, 2) NOT NULL,
  `s_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_fund` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `s_tax` float(10, 2) DEFAULT NULL,
  `s_as_deposit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `s_cba` float(10, 2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of settlement
-- ----------------------------
INSERT INTO `settlement` VALUES (1, '顶级推荐人', '371522199904018483', '业务员', 400.00, 0.00, 0.00, 0.00, 0.00, 2000.00, 400.00, 0.00, 0.00, 280.00, '2019-06-02', '100', 20.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (2, '刘佃法', '372401195506012858', '业务员', 800.00, 0.00, 0.00, 0.00, 0.00, 0.00, 800.00, 0.00, 0.00, 560.00, '2019-06-11', '200', 40.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (3, '吕桂荣', '37242319660625462X', '业务员', 800.00, 0.00, 0.00, 0.00, 0.00, 0.00, 800.00, 0.00, 0.00, 560.00, '2019-06-11', '200', 40.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (4, '尹振东', '370125196111013036', '业务员', 800.00, 0.00, 0.00, 0.00, 0.00, 0.00, 800.00, 0.00, 0.00, 560.00, '2019-06-11', '200', 40.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (5, '鲍明胜', '372428195601100039', '业务员', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '2019-06-11', '0', 0.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (6, '王昌盛', '372401195512180575', '业务员', 400.00, 0.00, 0.00, 0.00, 0.00, 0.00, 400.00, 0.00, 0.00, 280.00, '2019-06-11', '100', 20.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (7, '尚明强', '372401195908280038', '业务员', 800.00, 0.00, 0.00, 0.00, 0.00, 0.00, 800.00, 0.00, 0.00, 560.00, '2019-06-11', '200', 40.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (8, '于学斗', '371402197308012616', '业务员', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '2019-06-11', '0', 0.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (9, '王伟', '372426196706300346', '业务员', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '2019-06-11', '0', 0.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (10, '乔海军', '372421196706173971', '业务员', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '2019-06-12', '0', 0.00, '2000.0', 0.00);
INSERT INTO `settlement` VALUES (11, '秦风艳', '372401197604030020', '业务员', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, '2019-06-13', '0', 0.00, '2000.0', 0.00);

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `wineId` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopcart
-- ----------------------------
INSERT INTO `shopcart` VALUES (11, '372401195506012858', 12, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `u_regDate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_adress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_phone`(`u_phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '18315969275', '顶级推荐人', '男', '371522199904018483', '2019-06-01', '代理商', '111', '555666', '1');
INSERT INTO `user` VALUES (2, '13705345982', '刘佃法', '男', '372401195506012858', '2019-06-11', '业务员', '德州', '123456', '1');
INSERT INTO `user` VALUES (3, '13705345759', '吕桂荣', '女', '37242319660625462X', '2019-06-11', '业务员', '德州', '123456', '1');
INSERT INTO `user` VALUES (4, '13869227398', '尹振东', '男', '370125196111013036', '2019-06-11', '业务员', '德州德城区消防支队家属院', '123456', '1');
INSERT INTO `user` VALUES (5, '13605347229', '鲍明胜', '男', '372428195601100039', '2019-06-11', '业务员', '德州市经济开发区天玉铭城小区', '123456', '1');
INSERT INTO `user` VALUES (6, '13805342666', '王昌盛', '男', '372401195512180575', '2019-06-11', '业务员', '德州', '123456', '1');
INSERT INTO `user` VALUES (7, '13805349868', '尚明强', '男', '372401195908280038', '2019-06-11', '业务员', '德州', '123456', '1');
INSERT INTO `user` VALUES (8, '13573451861', '于学斗', '男', '371402197308012616', '2019-06-11', '业务员', '德州', '123456', '1');
INSERT INTO `user` VALUES (9, '13605342728', '王伟', '女', '372426196706300346', '2019-06-11', '业务员', '德州', '123456', '1');
INSERT INTO `user` VALUES (10, '18353489075', '乔海军', '男', '372421196706173971', '2019-06-12', '业务员', '德州', '123456', '1');
INSERT INTO `user` VALUES (11, '13953419879', '秦风艳', '女', '372401197604030020', '2019-06-13', '业务员', '德城区鲁班御景园', '123456', '1');

-- ----------------------------
-- Table structure for wine_info
-- ----------------------------
DROP TABLE IF EXISTS `wine_info`;
CREATE TABLE `wine_info`  (
  `wine_id` int(11) NOT NULL AUTO_INCREMENT,
  `wine_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `wine_price` decimal(10, 2) NOT NULL,
  `wine_category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `wine_number` int(11) NOT NULL,
  `wine_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `wine_dec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`wine_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wine_info
-- ----------------------------
INSERT INTO `wine_info` VALUES (1, '德州白酒', 2000.00, '122', 38632, 'https://s2.ax1x.com/2019/04/23/EEcHhQ.md.jpg', '还久');
INSERT INTO `wine_info` VALUES (2, '白酒精品', 1000.00, '622', 30000, 'https://s2.ax1x.com/2019/04/23/EEcqpj.md.jpg', '对方的');
INSERT INTO `wine_info` VALUES (3, '金荷园', 500.00, '533', 50000, 'https://s2.ax1x.com/2019/04/23/EEc7tg.md.jpg', '地方');
INSERT INTO `wine_info` VALUES (4, '鸡尾酒', 100.00, '这是好酒', 25386, 'https://s2.ax1x.com/2019/03/14/AA454I.jpg', '如果是');
INSERT INTO `wine_info` VALUES (5, '高档贵宾酒水', 200.00, '高档酒水', 262, 'https://s2.ax1x.com/2019/04/23/EEcTAS.jpg', '的方向');
INSERT INTO `wine_info` VALUES (6, '山东青岛啤酒', 50.00, '啤酒', 5637, 'https://s2.ax1x.com/2019/03/14/AA47gf.jpg', '刚刚');
INSERT INTO `wine_info` VALUES (7, '平明酒', 300.00, '斟酒', 2563, 'https://s2.ax1x.com/2019/03/14/AA4T8P.md.jpg', '启动');
INSERT INTO `wine_info` VALUES (8, '喜庆红酒', 3000.00, '红酒', 449, 'https://s2.ax1x.com/2019/03/14/AA4hEd.jpg', '天秤座');
INSERT INTO `wine_info` VALUES (9, '地龙酒水', 10000.00, '好酒', 121, 'https://s2.ax1x.com/2019/03/14/AA4WHH.jpg', '法国人非常');
INSERT INTO `wine_info` VALUES (10, '庆祝酒水', 30000.00, '香酒', 4522, 'https://s2.ax1x.com/2019/03/14/AA4RDe.jpg', '符串');
INSERT INTO `wine_info` VALUES (11, '赤阳烈酒', 253.70, '烈酒', 320, 'https://s2.ax1x.com/2019/03/14/AA42uD.jpg', '风格非常');
INSERT INTO `wine_info` VALUES (12, '祝融酒', 345.80, '浩瀚酒水', 11999, 'https://s2.ax1x.com/2019/03/14/AA4oCt.jpg', '法国三十五');

SET FOREIGN_KEY_CHECKS = 1;
