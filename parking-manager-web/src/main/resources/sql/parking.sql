/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : parking

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 14/01/2020 16:57:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `carId` int(10) NOT NULL AUTO_INCREMENT,
  `carType` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `deleteFlag` int(2) NULL DEFAULT NULL COMMENT '删除标志1已删除,0未删除',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userId` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`carId`) USING BTREE,
  INDEX `fk_user`(`userId`) USING BTREE,
  CONSTRAINT `fk_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for car_stall
-- ----------------------------
DROP TABLE IF EXISTS `car_stall`;
CREATE TABLE `car_stall`  (
  `carId` int(10) NOT NULL,
  `stallId` int(10) NOT NULL,
  `startTime` datetime(0) NULL DEFAULT NULL,
  `endTime` datetime(0) NULL DEFAULT NULL,
  `consumption` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`carId`, `stallId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menuId` int(10) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` int(10) NULL DEFAULT NULL COMMENT '父id',
  `perms` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `orderNum` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `menuType` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型: 0目录 1菜单 2按钮',
  `visiable` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态0显示,1隐藏',
  `createBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'admin' COMMENT '创建者',
  `updateBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `updateTime` datetime(0) NULL DEFAULT '1970-01-01 12:00:00' COMMENT '更新时间',
  `createTime` datetime(0) NULL DEFAULT '1970-01-01 12:00:00',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 170 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (30, '系统管理', 0, NULL, '#', NULL, 1, NULL, '0', 'admin', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (31, '用户管理', 30, 'user:*', '/user', NULL, 1, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (32, '部门管理', 30, 'dept:*', '/dept', NULL, 2, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (33, '角色管理', 30, 'role:*', '/role', NULL, 3, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (34, '菜单管理', 30, 'menu:*', '/menu', NULL, 4, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (35, '岗位管理', 30, 'workpost:*', '/workpost', NULL, 5, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (150, '用户查询', 31, 'user:select', '#', NULL, 1, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (151, '用户新增', 31, 'user:insert', '#', NULL, 2, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (152, '用户修改', 31, 'user:update', '#', NULL, 3, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (153, '用户删除', 31, 'user:delete', '#', NULL, 4, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (154, '部门查询', 32, 'dept:select', '#', NULL, 1, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (155, '部门新增', 32, 'dept:insert', '#', NULL, 2, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (156, '部门修改', 32, 'dept:update', '#', NULL, 3, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (157, '部门删除', 32, 'dept:delete', NULL, NULL, 4, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (158, '角色查询', 33, 'role:select', '#', NULL, 1, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (159, '角色新增', 33, 'role:insert', '#', NULL, 2, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (160, '角色修改', 33, 'role:update', '#', NULL, 3, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (161, '角色删除', 33, 'role:delete', '#', NULL, 4, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (162, '菜单查询', 34, 'menu:select', '#', NULL, 1, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (163, '菜单新增', 34, 'menu:insert', '#', NULL, 2, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (164, '菜单修改', 34, 'menu:update', '#', NULL, 3, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (165, '菜单删除', 34, 'menu:delete', '#', NULL, 4, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (166, '岗位查询', 35, 'workpost:select', '#', NULL, 1, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (167, '岗位新增', 35, 'workpost:insert', '#', NULL, 2, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (168, '岗位修改', 35, 'workpost:update', '#', NULL, 3, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');
INSERT INTO `menu` VALUES (169, '岗位删除', 35, 'workpost:delete', '#', NULL, 4, NULL, '0', '', '', '1970-01-01 12:00:00', '1970-01-01 12:00:00', '');

-- ----------------------------
-- Table structure for park_area
-- ----------------------------
DROP TABLE IF EXISTS `park_area`;
CREATE TABLE `park_area`  (
  `parkAreaId` int(10) NOT NULL AUTO_INCREMENT COMMENT '停车区域主键',
  `parkAreaName` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区域名称',
  `community` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在小区',
  `charges` int(10) NOT NULL COMMENT '收费标准',
  PRIMARY KEY (`parkAreaId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int(10) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `deleteFlag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '删除标志 1未删除 0已删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `createBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updateBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `updateTime` datetime(0) NULL DEFAULT '1970-01-01 12:00:00',
  `createTime` datetime(0) NULL DEFAULT '1970-01-01 12:00:00',
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `rId` int(10) NOT NULL,
  `mId` int(10) NOT NULL,
  PRIMARY KEY (`rId`, `mId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stall
-- ----------------------------
DROP TABLE IF EXISTS `stall`;
CREATE TABLE `stall`  (
  `stallId` int(10) NOT NULL COMMENT '停车位id',
  `stallName` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位名称',
  `stallType` int(1) NOT NULL COMMENT '停车位类型,1在租,0已售',
  `parkAreaId` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`stallId`) USING BTREE,
  INDEX `fk_park_area`(`parkAreaId`) USING BTREE,
  CONSTRAINT `fk_park_area` FOREIGN KEY (`parkAreaId`) REFERENCES `park_area` (`parkAreaId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `integral` int(10) NULL DEFAULT NULL COMMENT '积分',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `deleteFlag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志 1已删除,0未删除',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别 0男 1女 2未知',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (100, 'zhangsan', '12345', NULL, NULL, '', '0', '0');
INSERT INTO `user` VALUES (101, 'lisi', '12345', NULL, NULL, '', '0', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `uId` int(10) NOT NULL,
  `rId` int(10) NOT NULL,
  PRIMARY KEY (`uId`, `rId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
