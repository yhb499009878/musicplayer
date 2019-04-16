/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : music_player

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2018-08-22 14:56:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `tel` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', 'admin', 'xxxx', 'xxxx', 'admin', '2018-06-21 09:38:37', '2018-06-21 09:38:41', '110');

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `bulletin_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bulletin
-- ----------------------------
INSERT INTO `bulletin` VALUES ('11', '', '2018-07-02 15:14:45', '2018-07-02 15:14:45', '0', '2018-07-02 15:14:45');

-- ----------------------------
-- Table structure for kc
-- ----------------------------
DROP TABLE IF EXISTS `kc`;
CREATE TABLE `kc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `names` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `zjr` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `xx` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `descs` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `bfcs` int(11) DEFAULT NULL,
  `plcs` int(11) DEFAULT NULL,
  `sccs` int(11) DEFAULT NULL,
  `url1` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `url2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `tnames` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ishy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of kc
-- ----------------------------

-- ----------------------------
-- Table structure for liuyan
-- ----------------------------
DROP TABLE IF EXISTS `liuyan`;
CREATE TABLE `liuyan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descs` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `times` timestamp NULL DEFAULT NULL,
  `names` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of liuyan
-- ----------------------------

-- ----------------------------
-- Table structure for ly
-- ----------------------------
DROP TABLE IF EXISTS `ly`;
CREATE TABLE `ly` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `names` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `descs` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `kcid` int(11) DEFAULT NULL,
  `times` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ly
-- ----------------------------

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_id` varchar(100) DEFAULT NULL,
  `file_name` varchar(200) DEFAULT NULL,
  `system_file_name` varchar(200) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `mime_type` varchar(100) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `file_path` varchar(400) DEFAULT NULL,
  `md5` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('16', 'A46DA12AADE94E21B654281694883D81', '1.jpg', '20180621_100224_3609062-1.jpg-.jpg', '0', 'image/jpeg', '168582', 'data\\0\\0\\0\\201806\\20180621_100224_3609062-1.jpg-.jpg', 'aa39b0454f3d9ac45ac7a5fa1d2ddae1', '2018-06-21 10:02:24', '2018-06-21 10:02:24', '0');
INSERT INTO `resource` VALUES ('17', '0B7A301F305D470DBEC436076FCC2C76', 'hadoop?????.png', '20180621_102655_6772010-hadoop?????.png-.png', '0', 'image/png', '20753', 'data\\0\\0\\0\\201806\\20180621_102655_6772010-hadoop?????.png-.png', 'e90fe5701084104fc4b56c4f9e24de5e', '2018-06-21 10:26:55', '2018-06-21 10:26:55', '0');
INSERT INTO `resource` VALUES ('18', 'CE8B9A8244D54EED8DBDCB0B328295DA', 'Eric Saade - Take a Ride (Put \'Em In the Air).mp3', '20180621_104909_783932-Eric Saade - Take a Ride (Put \'Em In the Air).mp3-.mp3', '0', 'audio/mp3', '9100202', 'data\\0\\0\\0\\201806\\20180621_104909_783932-Eric Saade - Take a Ride (Put \'Em In the Air).mp3-.mp3', '1c73d144159af7d166aa1fa94f8249fd', '2018-06-21 10:49:09', '2018-06-21 10:49:09', '0');
INSERT INTO `resource` VALUES ('19', '5B10BA1F482A42D8A48217B7F2BA97CE', 'Lenka - My Love.mp3', '20180621_104941_5474160-Lenka - My Love.mp3-.mp3', '0', 'audio/mp3', '7673490', 'data\\0\\0\\0\\201806\\20180621_104941_5474160-Lenka - My Love.mp3-.mp3', 'e1e738ffbc3e6ab5bab038267b93c9a5', '2018-06-21 10:49:41', '2018-06-21 10:49:41', '0');
INSERT INTO `resource` VALUES ('20', '8F04EFF5768E429083EFB943BB5C5E05', 'Eric Saade - Take a Ride (Put \'Em In the Air).mp3', '20180621_105815_6895918-Eric Saade - Take a Ride (Put \'Em In the Air).mp3-.mp3', '0', 'audio/mp3', '9100202', 'data\\0\\0\\0\\201806\\20180621_105815_6895918-Eric Saade - Take a Ride (Put \'Em In the Air).mp3-.mp3', '1c73d144159af7d166aa1fa94f8249fd', '2018-06-21 10:58:15', '2018-06-21 10:58:15', '0');
INSERT INTO `resource` VALUES ('21', '7C84FB8CAF3144C9877CF6D1BFE25B86', 'Lenka - My Love.mp3', '20180621_110416_1037140-Lenka - My Love.mp3-.mp3', '0', 'audio/mp3', '7673490', 'data\\0\\0\\0\\201806\\20180621_110416_1037140-Lenka - My Love.mp3-.mp3', 'e1e738ffbc3e6ab5bab038267b93c9a5', '2018-06-21 11:04:16', '2018-06-21 11:04:16', '0');
INSERT INTO `resource` VALUES ('22', '08592AE43C0A431A82C08E8B0955D21E', '1.jpg', '20180621_110431_287304-1.jpg-.jpg', '0', 'image/jpeg', '168582', 'data\\0\\0\\0\\201806\\20180621_110431_287304-1.jpg-.jpg', 'aa39b0454f3d9ac45ac7a5fa1d2ddae1', '2018-06-21 11:04:31', '2018-06-21 11:04:31', '0');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sc
-- ----------------------------

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `song_name` varchar(100) DEFAULT NULL,
  `singer_name` varchar(100) DEFAULT NULL,
  `song_type` varchar(100) DEFAULT NULL,
  `poster_resource_id` varchar(100) DEFAULT NULL,
  `song_resource_id` varchar(100) DEFAULT NULL,
  `lyrics_resource_id` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES ('9', '1', '1', null, '08592AE43C0A431A82C08E8B0955D21E', '7C84FB8CAF3144C9877CF6D1BFE25B86', '', '2018-06-21 11:04:33', '2018-06-21 11:04:33', '0');

-- ----------------------------
-- Table structure for song_list
-- ----------------------------
DROP TABLE IF EXISTS `song_list`;
CREATE TABLE `song_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `song_list_name` varchar(100) DEFAULT NULL,
  `song_list_desc` varchar(100) DEFAULT NULL,
  `cover_resource_id` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song_list
-- ----------------------------

-- ----------------------------
-- Table structure for song_list_mapping
-- ----------------------------
DROP TABLE IF EXISTS `song_list_mapping`;
CREATE TABLE `song_list_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `song_id` bigint(20) DEFAULT NULL,
  `song_list_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song_list_mapping
-- ----------------------------
INSERT INTO `song_list_mapping` VALUES ('2', '4', '4', '2018-06-21 09:59:11', '2018-06-21 09:59:11', '0');
INSERT INTO `song_list_mapping` VALUES ('3', '5', '5', '2018-06-21 10:02:47', '2018-06-21 10:02:47', '0');
INSERT INTO `song_list_mapping` VALUES ('4', '6', '5', '2018-06-21 10:27:29', '2018-06-21 10:27:29', '0');

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `names` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of types
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `tel` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `isvip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'root', 'root', 'xxxx', 'xxxx', 'xxxx', 'xxxx', '2018-06-21 09:39:06', '2018-06-21 09:39:08', '111');
