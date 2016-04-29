/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.24 : Database - slin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`slin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `slin`;

/*Table structure for table `t_token` */

DROP TABLE IF EXISTS `t_token`;

CREATE TABLE `t_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(1024) NOT NULL,
  `expires_in` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `t_token` */

insert  into `t_token`(`id`,`access_token`,`expires_in`,`createTime`) values (1,'EHFeiUw4HTVDNTyjZAfLzRx4B17dzlMcZpkpaBdl33sYSKwOjQNm8cPA6CQTriNUSJ2Z4ZdZNKTl7Yr2N5R3Wb_hhDuDAIx6ZwpbiEtflMyAhMn2ZDrnsilnmnYdA0JZGFXfAAACBT',7200,'2016-04-13 14:27:46'),(2,'qRKMfKwoSsRXnDEFm6xfNY6itiZ0PU6qLjsjZqqYjPzDzxZ7AwY0sRSQvbiBOC8J9z4Xo98gPaHwehYAlbhLlxUuQxBP16Qbx5Ui0MUgFIpgnmDTXVxQDPDjQVAQPYQYIPHjABAXEW',7200,'2016-04-13 15:04:08'),(3,'FLkCLfL3IoU5wqb8HKppnx9v3SXzo4wOdQGbfAxn4khH7aYWCa2LJ0vLRok_V1tvi-YJ4pHUOUQ_igLexreoe4P90L5X6x03hJrw94MJJESAMnq-UethKJIGnDjFEfs_JLWfAGARTE',7200,'2016-04-13 15:07:38'),(4,'UNwyRoBXpUG_ohL9S5h3hbewDqaQNPrkx3gO3vGlIulwhb3VhDDgPsIA5yRX1I0JbP77CXNVwmrHrV3DAMO46TKhE5mZCryoH_zapxe61rqumU_Hh0p7L2s2CyIDmpl1ADAfABATWT',7200,'2016-04-13 15:08:50'),(5,'R9yRcOBEAPjTh5E0s1OrU-yDAfS_To-TbmqS37ITDfMllcnx8iG_qSxO2hwj77FRjkyiAkMtb1KK5hr9Pzqe_axscRXCxBbm0xIcMXKzfn6AnOwHsR9BmwMLO-hHdZy4XMMbADABQQ',7200,'2016-04-13 15:11:27'),(6,'R3Cw1PoUbtNYEiZDm24wexWJDnoz16ch8qxlKLhxtTVq39TCPe6hS5KWWo6RbR7yuC2E4twrJOKL9VtaTd9GfgRfiGXORu2tw5WhuczgPVXxC9JUsLNGJJ9acrWxlA3iYDGfAGAKPS',7200,'2016-04-13 15:12:36'),(7,'l4_iWNfQ_CfET4Z1aDfujBDyPopQhofGWaXgDrwhcUwdoJplaA1iIggQNo4JtEnelGnLddPMJupErxT1_JNpxwvQpbcv4Q1KNG4EnYELdwGqcSdNH_Y1ghqEKJMANp2zEQNhAJAHTQ',7200,'2016-04-13 15:18:38'),(8,'CDcZ3W5p7-AJfGAIdOzlL6ZbRF80mE81UeKibJmy6w9cFS0s8ljQIz-_jsmN6GHTnw1hsic_nq-A2V5V9FJBjWyNJDQE6Jss5UoU1TFsi-lKOjK2MoehoRjvEDo-v_aBHZDgAJARJN',7200,'2016-04-13 17:03:39'),(9,'EHFeiUw4HTVDNTyjZAfLzXV6azOldDCBpBWqpg7LO8-eokYPA--XgOWF-l-Ix2xRsfDHwyXdiRWQn0drXSBFya0e-pnhgg4KdEc79EJWF5GOBwVGQfoT5GsxCe4lJ1jcNAJdABADQZ',7200,'2016-04-13 17:31:48'),(10,'E3mEYEZb6fK0Vy-vRWE_GU4l4m_ZRQn8rGxrMP5iw4xA496eISHhZeBNLYlb3oDComXQVAxpohgGPanUYD7VzMYpJyeC4xoV0jVCNc7C275QbjULYD5K7hMsZ4L-JEmsKAYfADAQGE',7200,'2016-04-26 08:24:01');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`user_name`,`password`,`age`) values (1,'测试','sfasgfaf',24);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
