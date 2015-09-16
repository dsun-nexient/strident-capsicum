SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS `user` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`role` enum('ADMIN','USER') NOT NULL,
	`username` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`is_active` boolean NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `profile` (
	`id` int unsigned NOT NULL AUTO_INCREMENT,
	`user_id` int unsigned NOT NULL,
	`created` timestamp NOT NULL,
	`first_name` varchar(255) NOT NULL,
	`last_name` varchar(255) NOT NULL,
	`date_of_birth` timestamp NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `blog` (
	`profile_id` int unsigned NOT NULL,
	`created` timestamp NOT NULL,
	`content` text NOT NULL,
	FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `friends` (
	`profile_id` int unsigned NOT NULL,
	`friend_profile_id` int unsigned NOT NULL,
	FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`),
	FOREIGN KEY (`friend_profile_id`) REFERENCES `profile` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;




