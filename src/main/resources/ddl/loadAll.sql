CREATE TABLE IF NOT EXISTS `employee` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(64) NOT NULL,
  `first_name` varchar(256) NOT NULL DEFAULT '',
  `last_name` varchar(256) DEFAULT '',
  `type` enum('administrator','staff','manager') DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE IF NOT EXISTS `review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reviewer_id` bigint(64) DEFAULT NULL,
  `reviewee_id` bigint(64) NOT NULL,
  `content` text,
  `state` enum('Assigned','Draft','NeedsFeedback','FeedbackDraft','Closed') NOT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `modified_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `emp_id_fk_idx` (`reviewee_id`),
  CONSTRAINT `emp_id_fk` FOREIGN KEY (`reviewee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$
