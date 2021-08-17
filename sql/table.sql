-- rc.rc_action definition

CREATE TABLE `rc_action` (
		`id` bigint NOT NULL AUTO_INCREMENT,
		`pid` bigint NOT NULL,
		`itemName` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`itemId` bigint DEFAULT NULL,
		`name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`isIndex` int DEFAULT NULL,
		`sort` int DEFAULT NULL,
		`created` bigint DEFAULT NULL,
		`updated` bigint DEFAULT NULL,
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rc.rc_config definition

CREATE TABLE `rc_config` (
		`id` bigint NOT NULL AUTO_INCREMENT,
		`keyword` varchar(32) DEFAULT NULL,
		`value` varchar(128) DEFAULT NULL,
		`comments` varchar(128) DEFAULT NULL,
		`created` bigint DEFAULT NULL,
		`updated` bigint DEFAULT NULL,
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rc.rc_item definition

CREATE TABLE `rc_item` (
		`id` bigint NOT NULL AUTO_INCREMENT,
		`name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`status` tinyint DEFAULT NULL,
		`created` bigint DEFAULT NULL,
		`updated` bigint DEFAULT NULL,
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rc.rc_role definition

CREATE TABLE `rc_role` (
		`id` bigint NOT NULL AUTO_INCREMENT,
		`name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`note` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`created` bigint DEFAULT NULL,
		`updated` bigint DEFAULT NULL,
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rc.rc_role_action definition

CREATE TABLE `rc_role_action` (
		`id` bigint NOT NULL AUTO_INCREMENT,
		`roleId` bigint DEFAULT NULL,
		`actionId` bigint DEFAULT NULL,
		`created` bigint DEFAULT NULL,
		`updated` bigint DEFAULT NULL,
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rc.rc_user definition

CREATE TABLE `rc_user` (
		`id` bigint NOT NULL AUTO_INCREMENT,
		`company` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`job_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`cardNum` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
		`status` tinyint DEFAULT NULL,
		`created` bigint DEFAULT NULL,
		`updated` bigint DEFAULT NULL,
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rc.rc_user_role definition

CREATE TABLE `rc_user_role` (
		`id` bigint NOT NULL AUTO_INCREMENT,
		`userId` bigint NOT NULL,
		`roleId` bigint NOT NULL,
		`created` bigint DEFAULT NULL,
		`updated` bigint DEFAULT NULL,
		PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;