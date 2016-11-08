delimiter $$

CREATE TABLE IF NOT EXISTS `employee` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(64) NOT NULL,
  `first_name` varchar(256) NOT NULL DEFAULT '',
  `last_name` varchar(256) DEFAULT '',
  `type` enum('administrator','staff','manager') DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`emp_id`),
  UNIQUE KEY `emp_id_UNIQUE` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE IF NOT EXISTS `feedback` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `review_id` bigint(64) NOT NULL,
  `reviewer_emp_id` bigint(64) NOT NULL,
  `reviewee_emp_id` bigint(64) NOT NULL,
  `content` text,
  `state` enum('Complete','Incomplete') DEFAULT 'Incomplete',
  PRIMARY KEY (`id`),
  KEY `review_id_fk_idx` (`review_id`),
  KEY `fb_emp_id_fk_idx` (`reviewer_emp_id`),
  KEY `fb_emp_id_fk_idx1` (`reviewee_emp_id`),
  CONSTRAINT `fb_emp_id_fk` FOREIGN KEY (`reviewee_emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `review_id_fk` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE IF NOT EXISTS `review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reviewee_id` bigint(64) NOT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `modified_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `emp_id_fk_idx` (`reviewee_id`),
  CONSTRAINT `emp_id_fk` FOREIGN KEY (`reviewee_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1$$


