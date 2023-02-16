/*
 Navicat Premium Data Transfer

 Source Server         : my_server_mysql
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : 127.0.0.1:6666
 Source Schema         : www

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 16/02/2023 22:33:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` char(32) NOT NULL COMMENT '权限编号',
  `parent_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '父级编号',
  `type` int DEFAULT '1' COMMENT '类型 1.普通权限',
  `url` varchar(255) NOT NULL COMMENT '权限url',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('008c9784871e22432a58dfd72d095965', 'e69ff2f5f8f36b27e56c20fff48531b5', 1, '/role/updatePermission', '更新角色权限', '2023-02-15 22:26:43', '2023-02-15 22:26:43');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('019a964bfb1e2227a35e8b8a780c300c', 'e69ff2f5f8f36b27e56c20fff48531b5', 1, '/role/delete', '删除角色', '2023-02-15 22:20:54', '2023-02-15 22:20:54');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('0476c1e86a3c60ed0f6967704cf548d8', 'f14f48dcfaa2929f80add0364ef48f2a', 1, '/permission/add', '添加权限', '2023-02-15 22:27:02', '2023-02-15 22:27:02');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('2432d1e30d91c44a14d50374d43925f3', '', 2, '/system', '系统管理', '2023-02-15 22:22:29', '2023-02-15 22:17:09');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('2f748be9605bc9b9f7556442d9352f30', 'e69ff2f5f8f36b27e56c20fff48531b5', 1, '/role/list', '查找角色列表', '2023-02-15 22:25:51', '2023-02-15 22:25:51');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('47a0426b293002c4d7d290fdb4b80e7f', 'f14f48dcfaa2929f80add0364ef48f2a', 1, '/permission/delete', '删除权限', '2023-02-15 22:27:16', '2023-02-15 22:27:16');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('4820ad8462587cd5879624ce84ec48d0', '', 2, '/dashboard', '控制台', '2023-02-15 22:22:24', '2023-02-15 22:16:43');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('502514b02930b11ec380bdcf6886a412', '89c7cf3778eba457dcff2dcfe0bd3074', 1, '/user/update', '更新用户', '2023-02-15 22:31:16', '2023-02-15 22:31:16');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('658c80ed947004b6753f9231c3f460a6', 'f14f48dcfaa2929f80add0364ef48f2a', 1, '/permission/update', '修改权限', '2023-02-15 22:29:52', '2023-02-15 22:29:52');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('674c84f2685c8d2bea0454b92f9eb696', 'e69ff2f5f8f36b27e56c20fff48531b5', 1, '/role/find', '查找角色', '2023-02-15 22:21:25', '2023-02-15 22:21:25');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('75e77816b04b710cef97d6fc1eda7bfa', 'e69ff2f5f8f36b27e56c20fff48531b5', 1, '/role/add', '添加角色', '2023-02-15 22:20:39', '2023-02-15 22:20:39');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('89c7cf3778eba457dcff2dcfe0bd3074', '2432d1e30d91c44a14d50374d43925f3', 2, '/system/userList', '用户列表', '2023-02-15 22:22:13', '2023-02-15 22:19:30');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('a864742835a923363b54a5ff5d4c66de', 'e69ff2f5f8f36b27e56c20fff48531b5', 1, '/role/update', '修改角色', '2023-02-15 22:26:11', '2023-02-15 22:26:11');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('cdffd8ffd141143e8d8252e2b3035ccc', '89c7cf3778eba457dcff2dcfe0bd3074', 1, '/user/list', '查找用户列表', '2023-02-15 22:31:00', '2023-02-15 22:31:00');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('e69ff2f5f8f36b27e56c20fff48531b5', '2432d1e30d91c44a14d50374d43925f3', 2, '/system/roleList', '角色列表', '2023-02-15 22:22:04', '2023-02-15 22:18:11');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('e9c7fecdab9fc58b8f108976c0ad4e21', 'f14f48dcfaa2929f80add0364ef48f2a', 1, '/permission/find', '查找权限', '2023-02-15 22:27:59', '2023-02-15 22:27:59');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('ea67ce06249b613458956dc38dc25424', '89c7cf3778eba457dcff2dcfe0bd3074', 1, '/user/delete', '删除用户', '2023-02-15 22:30:40', '2023-02-15 22:30:40');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('ef1c1648e02db790da141e7824732bf9', 'f14f48dcfaa2929f80add0364ef48f2a', 1, '/permission/list', '查找权限列表', '2023-02-15 22:30:00', '2023-02-15 22:29:37');
INSERT INTO `permission` (`id`, `parent_id`, `type`, `url`, `description`, `update_time`, `create_time`) VALUES ('f14f48dcfaa2929f80add0364ef48f2a', '2432d1e30d91c44a14d50374d43925f3', 2, '/system/permissionList', '权限列表', '2023-02-15 22:21:52', '2023-02-15 22:18:51');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` char(32) NOT NULL COMMENT '角色编号',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色描述',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `role_name`, `role_description`, `update_time`, `create_time`) VALUES ('1ca4a861315c448891e5a7a2021ddaf3', 'admin', '超级管理员', '2023-02-12 23:04:42', '2023-02-12 23:04:42');
INSERT INTO `role` (`id`, `role_name`, `role_description`, `update_time`, `create_time`) VALUES ('6672289124459b03c838a13ac75d44af', 'normal', '普通角色', '2023-02-05 23:28:02', '2023-02-05 23:27:42');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关联编号',
  `role_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编号',
  `permissions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色权限',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` (`id`, `role_id`, `permissions`, `create_time`, `update_time`) VALUES ('03552694fc3bf112c99a61edb7ba954e', '1ca4a861315c448891e5a7a2021ddaf3', '2432d1e30d91c44a14d50374d43925f3,89c7cf3778eba457dcff2dcfe0bd3074,502514b02930b11ec380bdcf6886a412,cdffd8ffd141143e8d8252e2b3035ccc,ea67ce06249b613458956dc38dc25424,f14f48dcfaa2929f80add0364ef48f2a,658c80ed947004b6753f9231c3f460a6,ef1c1648e02db790da141e7824732bf9,e9c7fecdab9fc58b8f108976c0ad4e21,47a0426b293002c4d7d290fdb4b80e7f,0476c1e86a3c60ed0f6967704cf548d8,e69ff2f5f8f36b27e56c20fff48531b5,008c9784871e22432a58dfd72d095965,a864742835a923363b54a5ff5d4c66de,2f748be9605bc9b9f7556442d9352f30,674c84f2685c8d2bea0454b92f9eb696,019a964bfb1e2227a35e8b8a780c300c,75e77816b04b710cef97d6fc1eda7bfa,4820ad8462587cd5879624ce84ec48d0', '2023-02-13 00:17:32', '2023-02-15 22:52:54');
INSERT INTO `role_permission` (`id`, `role_id`, `permissions`, `create_time`, `update_time`) VALUES ('767e3cbed90fbcccb3f9734133b48704', '6672289124459b03c838a13ac75d44af', '2432d1e30d91c44a14d50374d43925f3,89c7cf3778eba457dcff2dcfe0bd3074,502514b02930b11ec380bdcf6886a412,cdffd8ffd141143e8d8252e2b3035ccc,ea67ce06249b613458956dc38dc25424,2f748be9605bc9b9f7556442d9352f30,674c84f2685c8d2bea0454b92f9eb696,4820ad8462587cd5879624ce84ec48d0', '2023-02-15 00:14:44', '2023-02-16 22:21:26');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(32) NOT NULL COMMENT '用户编号',
  `user_info_id` char(32) NOT NULL COMMENT '用户详情编号',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_username` (`username`) COMMENT '用户名索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `user_info_id`, `username`, `password`, `update_time`, `create_time`) VALUES ('b7d8ee6e6baa4bc1a7d76a94a217e8e1', '382f3308faf54ff9a66f533d6cae351a', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '2023-01-27 16:37:48', '2023-01-27 16:28:51');
INSERT INTO `user` (`id`, `user_info_id`, `username`, `password`, `update_time`, `create_time`) VALUES ('b7d8ee6e6baa4bc1a7d76a94a217e8e2', '382f3308faf54ff9a66f533d6cae351b', 'test01', 'E10ADC3949BA59ABBE56E057F20F883E', '2023-01-27 16:37:48', '2023-01-27 16:28:51');
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `avatar` varchar(512) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` (`id`, `role_id`, `nickname`, `avatar`, `update_time`, `create_time`) VALUES ('382f3308faf54ff9a66f533d6cae351a', '1ca4a861315c448891e5a7a2021ddaf3', 'admin', 'https://img1.baidu.com/it/u=1267713446,2162705257&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '2023-02-12 22:18:41', '2023-02-12 21:10:16');
INSERT INTO `user_info` (`id`, `role_id`, `nickname`, `avatar`, `update_time`, `create_time`) VALUES ('382f3308faf54ff9a66f533d6cae351b', '6672289124459b03c838a13ac75d44af', 'test_02', 'https://img1.baidu.com/it/u=1267713446,2162705257&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '2023-02-15 21:52:19', '2023-02-12 21:10:16');
COMMIT;

-- ----------------------------
-- View structure for user_info_role_view
-- ----------------------------
DROP VIEW IF EXISTS `user_info_role_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `user_info_role_view` AS select `a`.`id` AS `id`,`b`.`username` AS `username`,`a`.`nickname` AS `nickname`,`a`.`role_id` AS `role_id`,`a`.`avatar` AS `avatar`,`a`.`update_time` AS `update_time`,`a`.`create_time` AS `create_time` from ((`user_info` `a` left join `user` `b` on((`b`.`user_info_id` = `a`.`id`))) left join `role` `c` on((`c`.`id` = `a`.`role_id`)));

SET FOREIGN_KEY_CHECKS = 1;
