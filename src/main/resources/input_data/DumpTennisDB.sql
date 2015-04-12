CREATE DATABASE  IF NOT EXISTS `tennis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tennis`;
--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
CREATE TABLE `matches` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `player1` varchar(50) NOT NULL,
  `player2` varchar(50) NOT NULL,
  `status` varchar(12) NOT NULL,
  `start_ts` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `latest_ts` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sets_count_1` int(1) unsigned NOT NULL,
  `games_count_1` int(5) unsigned NOT NULL,
  `points_count_1` int(5) unsigned NOT NULL,
  `sets_count_2` int(1) unsigned NOT NULL,
  `games_count_2` int(5) unsigned NOT NULL,
  `points_count_2` int(5) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

LOCK TABLES `matches` WRITE;
INSERT INTO `matches` VALUES (1,'Federer','Nadal','ONGOING','2015-04-12 20:21:10','2015-04-12 20:21:10',0,0,0,0,0,0);
UNLOCK TABLES;
