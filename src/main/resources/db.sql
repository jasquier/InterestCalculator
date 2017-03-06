CREATE DATABASE IF NOT EXISTS 'springICdb';
USE 'springICdb';

DROP TABLE IF EXISTS 'accounts';
CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL,
  `ledger_balance` double DEFAULT NULL,
  `account_type` varchar(50) NOT NULL,
  `interest_rate` double DEFAULT NULL,
  `over_draft_penalty` bigint(20) DEFAULT NULL,
  `required_minimum_balance` bigint(20) DEFAULT NULL,
  `is_minimum_balance_required` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `accounts_accountNumber_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS 'account_history';
CREATE TABLE `account_history` (
  `account_num` bigint(20) NOT NULL DEFAULT '0',
  `amount` bigint(20) DEFAULT '0',
  `date_occurred_on` date NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `accountHistory_accounts_accountNumber_fk` (`account_num`),
  CONSTRAINT `account_history_accounts_id_fk` FOREIGN KEY (`account_num`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `reccuring_transactions`;
CREATE TABLE `reccuring_transactions` (
  `account_num` bigint(20) DEFAULT '0',
  `frequency` int(11) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `reccuringTransactions_accounts_accountNumber_fk` (`account_num`),
  CONSTRAINT `reccuring_transactions_accounts_id_fk` FOREIGN KEY (`account_num`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;