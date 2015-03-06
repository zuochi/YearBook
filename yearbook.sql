/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : yearbook

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2015-01-12 18:57:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advice
-- ----------------------------
DROP TABLE IF EXISTS `advice`;
CREATE TABLE `advice` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '建议id',
  `context` varchar(200) NOT NULL COMMENT '建议内容',
  `signup_date` date NOT NULL COMMENT '建议日期',
  `user_id` int(10) NOT NULL COMMENT '建议用户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`),
  KEY `FKAB3E7CF87EE64178` (`user_id`),
  CONSTRAINT `FKAB3E7CF87EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_advice_user_id_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for at_notify
-- ----------------------------
DROP TABLE IF EXISTS `at_notify`;
CREATE TABLE `at_notify` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '@提醒id',
  `at_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '@提醒日期',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `user_bid` int(10) NOT NULL COMMENT '被@提醒用户id',
  `photo_bid` int(10) DEFAULT NULL COMMENT '照片id',
  `bbs_bid` int(10) DEFAULT NULL COMMENT '留言id',
  `reply_bid` int(10) DEFAULT NULL COMMENT '评论id',
  `photoDesc_bid` int(10) DEFAULT NULL COMMENT '照片描述id',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  `status` int(11) DEFAULT NULL COMMENT '0:未读;1:已读',
  PRIMARY KEY (`id`),
  KEY `FKA36DF8D53AED4BC4` (`photo_bid`),
  KEY `FKA36DF8D577CE0DF2` (`user_bid`),
  KEY `FKA36DF8D57EE64178` (`user_id`),
  KEY `FKA36DF8D53C917FC6` (`bbs_bid`),
  KEY `FKA36DF8D553B9D934` (`reply_bid`),
  KEY `FKA36DF8D5ADB6B0D5` (`photoDesc_bid`),
  CONSTRAINT `FKA36DF8D53AED4BC4` FOREIGN KEY (`photo_bid`) REFERENCES `photo` (`id`),
  CONSTRAINT `FKA36DF8D53C917FC6` FOREIGN KEY (`bbs_bid`) REFERENCES `bbs` (`id`),
  CONSTRAINT `FKA36DF8D553B9D934` FOREIGN KEY (`reply_bid`) REFERENCES `reply` (`id`),
  CONSTRAINT `FKA36DF8D577CE0DF2` FOREIGN KEY (`user_bid`) REFERENCES `user` (`id`),
  CONSTRAINT `FKA36DF8D57EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKA36DF8D5ADB6B0D5` FOREIGN KEY (`photoDesc_bid`) REFERENCES `photo` (`id`),
  CONSTRAINT `FK_at_notify_bbs_bid_reference` FOREIGN KEY (`bbs_bid`) REFERENCES `bbs` (`id`),
  CONSTRAINT `FK_at_notify_photoDesc_bid_reference` FOREIGN KEY (`photoDesc_bid`) REFERENCES `photo` (`id`),
  CONSTRAINT `FK_at_notify_photo_bid_reference` FOREIGN KEY (`photo_bid`) REFERENCES `photo` (`id`),
  CONSTRAINT `FK_at_notify_reply_bid_reference` FOREIGN KEY (`reply_bid`) REFERENCES `reply` (`id`),
  CONSTRAINT `FK_at_notify_user_bid_reply_reference` FOREIGN KEY (`user_bid`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_at_notify_user_id_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bbs
-- ----------------------------
DROP TABLE IF EXISTS `bbs`;
CREATE TABLE `bbs` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `context` varchar(200) NOT NULL COMMENT '留言内容',
  `signup_date` date NOT NULL COMMENT '留言日期',
  `user_id` int(10) NOT NULL COMMENT '留言用户id',
  `lol` int(11) DEFAULT NULL COMMENT '赞的人数',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`),
  KEY `FK17C337EE64178` (`user_id`),
  CONSTRAINT `FK17C337EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_bbs_user_id_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for friend_list
-- ----------------------------
DROP TABLE IF EXISTS `friend_list`;
CREATE TABLE `friend_list` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '好友列表id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `friend_id` int(10) NOT NULL COMMENT '好友id',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `FK52BB519F7EE64178` (`user_id`),
  KEY `FK52BB519F423A4DC5` (`friend_id`),
  CONSTRAINT `FK52BB519F423A4DC5` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK52BB519F7EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_firend_list_friendid_reference` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_firend_list_userid_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for head_photo
-- ----------------------------
DROP TABLE IF EXISTS `head_photo`;
CREATE TABLE `head_photo` (
  `id` int(10) NOT NULL COMMENT '头像id',
  `url_s` varchar(200) DEFAULT NULL COMMENT '头像路径 细',
  `url_m` varchar(200) DEFAULT NULL COMMENT '头像路径 中',
  `url_l` varchar(200) DEFAULT NULL COMMENT '头像路径 大',
  `url_o` varchar(200) DEFAULT NULL COMMENT '头像路径 原',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for i_want_top
-- ----------------------------
DROP TABLE IF EXISTS `i_want_top`;
CREATE TABLE `i_want_top` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `signup_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '申请日期',
  `photo_id` int(10) NOT NULL COMMENT '申请图片id',
  `user_id` int(10) NOT NULL COMMENT '申请用户id',
  `status` int(1) NOT NULL COMMENT '0:未处理;1:已处理',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`),
  KEY `FK4EB191BC7EE64178` (`user_id`),
  KEY `FK4EB191BC217FF89C` (`photo_id`),
  CONSTRAINT `FK4EB191BC217FF89C` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`),
  CONSTRAINT `FK4EB191BC7EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_i_want_top_photo_id_reference` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`),
  CONSTRAINT `FK_i_want_top_user_id_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '照片id',
  `name` varchar(1500) DEFAULT NULL,
  `url` varchar(200) NOT NULL COMMENT '照片路径',
  `upload_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传日期',
  `user_id` int(10) NOT NULL COMMENT '上传用户id',
  `album_id` int(10) DEFAULT NULL COMMENT '相册id',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  `url_thumb` varchar(200) NOT NULL COMMENT '缩略图路径',
  `is_accusation` int(11) DEFAULT NULL COMMENT '0:未举报 1:被举报',
  PRIMARY KEY (`id`),
  KEY `FK65B3E327EE64178` (`user_id`),
  KEY `FK65B3E32A3C9C166` (`album_id`),
  CONSTRAINT `FK65B3E327EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK65B3E32A3C9C166` FOREIGN KEY (`album_id`) REFERENCES `photo_album` (`id`),
  CONSTRAINT `FK_photo_album_reference` FOREIGN KEY (`album_id`) REFERENCES `photo_album` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_photo_user_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=469 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for photo_album
-- ----------------------------
DROP TABLE IF EXISTS `photo_album`;
CREATE TABLE `photo_album` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '相册id',
  `name` varchar(10) NOT NULL COMMENT '相册名字',
  `photo_url` varchar(200) DEFAULT NULL COMMENT '照片封面',
  `permission` int(1) DEFAULT NULL COMMENT '权限 0:私有 1:公开 2:仅好友 3:密码',
  `password` varchar(30) DEFAULT NULL COMMENT '密码',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`),
  KEY `FK551232227EE64178` (`user_id`),
  CONSTRAINT `FK551232227EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_photo_album_user_id_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for private_letter
-- ----------------------------
DROP TABLE IF EXISTS `private_letter`;
CREATE TABLE `private_letter` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '私信id',
  `context` varchar(200) NOT NULL COMMENT '私信内容',
  `signup_date` date NOT NULL COMMENT '私信日期',
  `user_id` int(10) NOT NULL COMMENT '私信用户id',
  `user_bid` int(10) NOT NULL COMMENT '被私信用户id',
  `status` int(1) NOT NULL COMMENT '0:未读;1:已读',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`),
  KEY `FKEAD6526277CE0DF2` (`user_bid`),
  KEY `FKEAD652627EE64178` (`user_id`),
  CONSTRAINT `FKEAD6526277CE0DF2` FOREIGN KEY (`user_bid`) REFERENCES `user` (`id`),
  CONSTRAINT `FKEAD652627EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_private_letter_user_bid_reference` FOREIGN KEY (`user_bid`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_private_letter_user_id_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
  `id` int(10) NOT NULL DEFAULT '0',
  `name` varchar(10) NOT NULL COMMENT '系名字',
  `context` varchar(100) DEFAULT NULL COMMENT '系简介',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '回复id',
  `context` varchar(1000) DEFAULT NULL,
  `signup_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `user_bid` int(10) NOT NULL COMMENT '被回复用户id',
  `photo_bid` int(10) DEFAULT NULL COMMENT '照片id',
  `bbs_bid` int(10) DEFAULT NULL COMMENT '留言id',
  `private_letter_bid` int(10) DEFAULT NULL COMMENT '私信id',
  `status` int(1) NOT NULL COMMENT '0:未读;1:已读',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  `is_accusation` int(11) DEFAULT NULL COMMENT '0:未举报 1:被举报',
  PRIMARY KEY (`id`),
  KEY `FK67612EA3AED4BC4` (`photo_bid`),
  KEY `FK67612EA77CE0DF2` (`user_bid`),
  KEY `FK67612EA7EE64178` (`user_id`),
  KEY `FK67612EA3C917FC6` (`bbs_bid`),
  KEY `FK67612EA1E2A0F4B` (`private_letter_bid`),
  CONSTRAINT `FK67612EA1E2A0F4B` FOREIGN KEY (`private_letter_bid`) REFERENCES `private_letter` (`id`),
  CONSTRAINT `FK67612EA3AED4BC4` FOREIGN KEY (`photo_bid`) REFERENCES `photo` (`id`),
  CONSTRAINT `FK67612EA3C917FC6` FOREIGN KEY (`bbs_bid`) REFERENCES `bbs` (`id`),
  CONSTRAINT `FK67612EA77CE0DF2` FOREIGN KEY (`user_bid`) REFERENCES `user` (`id`),
  CONSTRAINT `FK67612EA7EE64178` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_bbs_bid_reference` FOREIGN KEY (`bbs_bid`) REFERENCES `bbs` (`id`),
  CONSTRAINT `FK_private_letter_bid_reference` FOREIGN KEY (`private_letter_bid`) REFERENCES `private_letter` (`id`),
  CONSTRAINT `FK_reply_photo_bid_reference` FOREIGN KEY (`photo_bid`) REFERENCES `photo` (`id`),
  CONSTRAINT `FK_reply_user_bid_reply_reference` FOREIGN KEY (`user_bid`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_reply_user_id_reference` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for school_year
-- ----------------------------
DROP TABLE IF EXISTS `school_year`;
CREATE TABLE `school_year` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '届id',
  `year` varchar(10) NOT NULL COMMENT '年份',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL COMMENT '(0：女,1：男)',
  `sign` varchar(50) DEFAULT NULL,
  `head_photo_id` int(10) DEFAULT NULL COMMENT '头像',
  `profession_id` int(10) DEFAULT NULL,
  `signup_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_logintime` timestamp NULL DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `we_chat` varchar(20) DEFAULT NULL COMMENT '微信',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮件',
  `school_year_id` int(10) DEFAULT NULL COMMENT '届',
  `is_delete` int(1) DEFAULT NULL COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`),
  KEY `FK36EBCB712BB958` (`profession_id`),
  KEY `FK36EBCB4309051B` (`head_photo_id`),
  KEY `FK36EBCB7D4C2981` (`school_year_id`),
  CONSTRAINT `FK36EBCB4309051B` FOREIGN KEY (`head_photo_id`) REFERENCES `head_photo` (`id`),
  CONSTRAINT `FK36EBCB712BB958` FOREIGN KEY (`profession_id`) REFERENCES `profession` (`id`),
  CONSTRAINT `FK36EBCB7D4C2981` FOREIGN KEY (`school_year_id`) REFERENCES `school_year` (`id`),
  CONSTRAINT `FK_head_photo_id_reference` FOREIGN KEY (`head_photo_id`) REFERENCES `head_photo` (`id`),
  CONSTRAINT `FK_profession_id_reference` FOREIGN KEY (`profession_id`) REFERENCES `profession` (`id`),
  CONSTRAINT `FK_school_year_id_reference` FOREIGN KEY (`school_year_id`) REFERENCES `school_year` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
