/*
 Navicat Premium Data Transfer

 Source Server         : mycat（从1）
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 10.0.3.150:3306
 Source Schema         : mydb_ms

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 29/01/2019 17:55:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for age1
-- ----------------------------
DROP TABLE IF EXISTS `age1`;
CREATE TABLE `age1`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for age2
-- ----------------------------
DROP TABLE IF EXISTS `age2`;
CREATE TABLE `age2`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user1
-- ----------------------------
DROP TABLE IF EXISTS `user1`;
CREATE TABLE `user1`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user1
-- ----------------------------
INSERT INTO `user1` VALUES (1, 'mycat从-写-user1', NULL);
INSERT INTO `user1` VALUES (2, 'aaa', 303);
INSERT INTO `user1` VALUES (3, 'aaa', 849);
INSERT INTO `user1` VALUES (4, 'aaa', 3);
INSERT INTO `user1` VALUES (5, 'aaa', 195);
INSERT INTO `user1` VALUES (6, 'aaa', 789);

-- ----------------------------
-- Table structure for user2
-- ----------------------------
DROP TABLE IF EXISTS `user2`;
CREATE TABLE `user2`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user2
-- ----------------------------
INSERT INTO `user2` VALUES (2, 'mycat从-写-user2', NULL);
INSERT INTO `user2` VALUES (3, 'aaa', 943);
INSERT INTO `user2` VALUES (4, 'aaa', 181);
INSERT INTO `user2` VALUES (5, 'aaa', 895);
INSERT INTO `user2` VALUES (6, 'aaa', 913);
INSERT INTO `user2` VALUES (7, 'aaa', 925);
INSERT INTO `user2` VALUES (8, 'aaa', 661);
INSERT INTO `user2` VALUES (9, 'aaa', 85);

-- ----------------------------
-- Table structure for user3
-- ----------------------------
DROP TABLE IF EXISTS `user3`;
CREATE TABLE `user3`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user3
-- ----------------------------
INSERT INTO `user3` VALUES (3, 'mycat从-写-user3', NULL);
INSERT INTO `user3` VALUES (4, 'aaa', 341);
INSERT INTO `user3` VALUES (5, 'aaa', 851);
INSERT INTO `user3` VALUES (6, 'aaa', 623);

SET FOREIGN_KEY_CHECKS = 1;
