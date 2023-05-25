use webchat;

-- ----------------------------
-- Table structure for t_guest
-- ----------------------------
DROP TABLE IF EXISTS `t_guest`;
CREATE TABLE `t_guest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` varchar(32) NOT NULL,
  `guest_name` varchar(20) NOT NULL,
  `guest_type` tinyint(3) NOT NULL DEFAULT '2',
  `guest_status` tinyint(3) NOT NULL DEFAULT '0',
  `guest_ip` varchar(16) DEFAULT NULL,
  `room_id` varchar(32) NOT NULL,
  `enable_flag` tinyint(3) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`guest_id`) USING BTREE,
  UNIQUE KEY `uk_biz` (`guest_name`,`room_id`) USING BTREE
);

-- ----------------------------
-- Table structure for t_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_msg`;
CREATE TABLE `t_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` varchar(32) NOT NULL,
  `guest_id` varchar(32) NOT NULL,
  `guest_name` varchar(20) DEFAULT NULL,
  `msg_content` varchar(127) NOT NULL,
  `enable_flag` tinyint(3) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` varchar(32) NOT NULL,
  `room_name` varchar(20) NOT NULL,
  `room_limit` tinyint(3) NOT NULL DEFAULT '2',
  `room_password` varchar(64) DEFAULT NULL,
  `room_desc` varchar(127) DEFAULT NULL,
  `room_ip` varchar(16) NOT NULL DEFAULT '127.0.0.1',
  `room_port` int(11) NOT NULL DEFAULT '8080',
  `enable_flag` tinyint(3) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`room_name`) USING BTREE,
  UNIQUE KEY `uk_id` (`room_id`) USING BTREE
);
