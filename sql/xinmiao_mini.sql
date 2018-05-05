/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : xinmiao_mini

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 05/05/2018 16:58:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `apply_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `apply_user` int(11) NOT NULL COMMENT '申请人',
  `apply_company_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请的投资机构',
  `apply_status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '审核状态',
  `is_read` int(11) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment_user_id` int(11) NOT NULL,
  `comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `comment_company_id` int(11) NOT NULL,
  `coment_time` datetime(0) NOT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `company_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_ceo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_type` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img`  (
  `img_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img_pos` int(11) NOT NULL,
  `scene_id` int(11) NOT NULL,
  PRIMARY KEY (`img_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for in_apply
-- ----------------------------
DROP TABLE IF EXISTS `in_apply`;
CREATE TABLE `in_apply`  (
  `in_apply_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `in_apply_user_id` int(11) NOT NULL,
  `in_apply_status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`in_apply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permission_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限名',
  `permission_url` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限url',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for scene
-- ----------------------------
DROP TABLE IF EXISTS `scene`;
CREATE TABLE `scene`  (
  `scene_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `scene_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_id` int(11) NOT NULL,
  `scene_num` int(11) NOT NULL,
  PRIMARY KEY (`scene_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sub_comment
-- ----------------------------
DROP TABLE IF EXISTS `sub_comment`;
CREATE TABLE `sub_comment`  (
  `sub_comment_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `user_a_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_b_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sub_comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sub_comment_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `is_read` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`sub_comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_wx` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
