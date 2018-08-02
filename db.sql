CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `library`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookmarks`
--

DROP TABLE IF EXISTS `bookmarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmarks` (
  `user_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ на пользователя.',
  `book_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ на книгу',
  `page_number` smallint(5) unsigned zerofill NOT NULL COMMENT 'Номер страницы, на которой сделанна закладка.',
  `lang` varchar(2) NOT NULL,
  `last_update` datetime NOT NULL,
  KEY `fk_users_has_books_books1_idx` (`book_id`),
  KEY `fk_users_has_books_users1_idx` (`user_id`),
  KEY `fk_bookmarks_lang` (`lang`),
  CONSTRAINT `fk_bookmakrs_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_bookmarks_lang` FOREIGN KEY (`lang`) REFERENCES `languages` (`lang`),
  CONSTRAINT `fk_users_has_books_books1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Закладки в книгах.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmarks`
--

LOCK TABLES `bookmarks` WRITE;
/*!40000 ALTER TABLE `bookmarks` DISABLE KEYS */;
INSERT INTO `bookmarks` VALUES (00003,00021,00035,'ru','2018-07-18 17:35:14'),(00003,00023,00079,'ru','2018-07-18 17:35:14'),(00003,00025,00037,'ru','2018-07-18 17:35:14'),(00006,00021,02000,'ru','2018-07-18 17:35:14'),(00007,00025,00623,'ru','2018-07-18 17:35:14'),(00001,00017,00003,'ru','2018-08-02 12:39:48'),(00001,00018,00003,'en','2018-07-23 13:50:09'),(00001,00019,00004,'en','2018-07-18 18:15:08'),(00001,00022,00003,'en','2018-07-18 17:56:57'),(00001,00023,00002,'en','2018-07-23 13:53:24'),(00001,00021,00002,'en','2018-07-23 13:53:37'),(00001,00024,00003,'en','2018-07-23 13:53:53'),(00001,00020,00002,'en','2018-07-23 13:55:38'),(00001,00025,00002,'en','2018-07-23 13:55:38'),(00001,00042,00002,'en','2018-07-23 13:55:38');
/*!40000 ALTER TABLE `bookmarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `book_id` smallint(5) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Суррогатный ключ',
  `def_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Название книги по умолчанию (на английском)',
  `def_description` text COMMENT 'Описание книги по умолчанию  (на английском)',
  `publish_year` smallint(5) unsigned zerofill DEFAULT NULL COMMENT 'Год издания книги.',
  `price` decimal(5,2) NOT NULL COMMENT 'Стоимость книги.',
  `pages` smallint(5) unsigned zerofill DEFAULT NULL COMMENT 'Количество страниц книги.',
  `def_authors` varchar(100) DEFAULT NULL,
  `publishing_house_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ на издательский дом.',
  `def_pdf_file_url` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Ссылка на pdf файл по умолчанию (на английском)',
  `cover_url` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Ссылка на обожку книги.',
  `def_text_file_url` varchar(75) NOT NULL,
  PRIMARY KEY (`book_id`),
  KEY `fk_books_publishing_houses1_idx` (`publishing_house_id`),
  FULLTEXT KEY `fullText` (`def_name`),
  CONSTRAINT `fk_books_publishing_houses1` FOREIGN KEY (`publishing_house_id`) REFERENCES `publishing_houses` (`publishing_house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='Книги.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (00017,'The Collector','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in tincidunt justo, at eleifend orci. Etiam mauris turpis, interdum sit amet mi at, fermentum consectetur nisl. Curabitur sapien magna, sagittis nec risus ut, efficitur imperdiet nisl. Phasellus nec tortor quis enim commodo dictum. Vivamus convallis maximus laoreet. Ut a molestie ipsum. Maecenas consectetur metus felis, sed faucibus neque auctor ac. Aenean sed lacinia ipsum, vitae vulputate enim. Aenean eget tellus arcu. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin gravida vestibulum turpis, eu ullamcorper odio.',02015,20.58,00544,NULL,00002,'qwe','/images/books/book-1.jpg','/WEB-INF/classes/text/book1en.txt'),(00018,'The Hound of the Baskervilles. ','Donec in facilisis orci. Nam porta nisl risus, et fermentum arcu egestas eget. Maecenas libero lacus, rhoncus quis tincidunt lobortis, ornare interdum leo. Suspendisse tincidunt tellus nulla, ac auctor tortor ultrices at. Quisque luctus, augue a hendrerit vehicula, ligula nibh iaculis nibh, pellentesque bibendum risus arcu non magna. Sed eu mauris at justo semper malesuada at et libero. Fusce eleifend, tellus vel fringilla tempor, nibh lectus tempor massa, quis tempus nunc odio eu libero. Mauris sodales lorem eget bibendum tempor. Nulla ac interdum arcu. Fusce egestas a urna a pretium. Nullam at tincidunt nisl. Donec posuere posuere felis id scelerisque. In hac habitasse platea dictumst. Mauris eu neque et nulla varius sagittis in sit amet nulla. Nunc at lectus ut libero facilisis facilisis.',02016,6.77,00160,'Sondra Wilson',00003,'/pdf/-2134042633.pdf','/images/books/book-2.jpg','/WEB-INF/classes/text/book2en.txt'),(00019,'A Scandal in Bohemia and Othe','Morbi euismod vel ipsum sed pharetra. Etiam nec finibus justo. Vivamus ut velit eros. Duis augue turpis, feugiat vel venenatis a, euismod vel ligula. Praesent sed vestibulum arcu, eu imperdiet ex. Nam euismod ligula auctor, interdum massa et, hendrerit nulla. Sed et felis vel ligula faucibus maximus. Etiam sed auctor turpis. In in eleifend felis, in lobortis nibh.',02016,16.01,00320,'Angel Gardner',00004,'/pdf/-2134042633.pdf','/images/books/book-3.jpg','/WEB-INF/classes/text/book3en.txt'),(00020,'The Adventure of the Dancing Men.','Aliquam eu hendrerit ipsum. Donec tempor faucibus nulla sed aliquam. Vestibulum malesuada leo eu libero rhoncus, viverra viverra justo varius. Fusce eget urna felis. Maecenas in placerat enim. Aliquam aliquam, nulla id volutpat luctus, dui nibh molestie urna, eu imperdiet mauris urna eu elit. Curabitur suscipit quam bibendum lacus ultrices, tincidunt semper elit accumsan. Vivamus tempor dapibus metus, tincidunt accumsan arcu hendrerit et. Proin quis elit lacus. In ornare rutrum neque. Etiam pharetra tortor in est tincidunt, tincidunt fermentum massa faucibus. Aenean rutrum semper diam ac vestibulum.',02016,6.57,00128,'Phyllis Gill',00005,'/pdf/-2134042633.pdf','/images/books/book-4.jpg','/WEB-INF/classes/text/book4en.txt'),(00021,'Ten Little Niggers','Vivamus et magna quis massa suscipit lacinia sit amet sed turpis. Proin fermentum vestibulum massa sed efficitur. Sed ultricies neque mauris, a faucibus nibh auctor non. Morbi ultrices ultricies elit eu fermentum. Nullam non risus aliquet, tempor nisi vitae, vestibulum risus. Nam eu dui magna. Duis accumsan quis turpis sit amet consectetur.',02010,10.00,00340,'Charles Hill',00006,'/pdf/-2134042633.pdf','/images/books/book-5.jpg','/WEB-INF/classes/text/book5en.txt'),(00022,'The Adventures of Sherlock Holmes','Fusce scelerisque magna non ligula eleifend consequat. Donec interdum felis ac nisl sodales rutrum. Proin nec mollis neque. Morbi vel pulvinar dui, fermentum lobortis tortor. Mauris ipsum leo, placerat nec laoreet a, auctor sagittis nisl. Mauris vel facilisis risus, sit amet finibus leo. Praesent mattis auctor posuere. Fusce non fermentum tellus. Maecenas pellentesque erat vitae eros aliquam, eu aliquet justo blandit. Donec volutpat nec augue non elementum. Duis suscipit congue enim. Integer quis libero gravida, congue enim id, maximus magna.',02008,23.43,00280,'Angie Day',00007,'/pdf/-2134042633.pdf','/images/books/book-6.jpg','/WEB-INF/classes/text/book6en.txt'),(00023,'Evil under the Sun','Fusce porta convallis nulla vitae blandit. Mauris rutrum massa ac dapibus venenatis. Integer quis magna ut turpis commodo elementum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec vel nisi mi. Duis ut dui diam. Vivamus hendrerit risus at nisi vehicula, id tristique enim tempus. Nam ut pretium mauris. Nam aliquam dignissim sapien a pulvinar.',02014,7.87,00190,'Evan Babcock',00008,'/pdf/-2134042633.pdf','/images/books/book-7.jpg','/WEB-INF/classes/text/book7en.txt'),(00024,'The Murder of Roger Ackroyd','Nunc et porttitor eros. Aenean bibendum risus a ante bibendum, a ornare quam dictum. Aliquam euismod ante quis tortor placerat, ut suscipit eros interdum. Nunc quis ligula diam. Aenean ullamcorper metus sit amet nunc condimentum consectetur. Duis auctor sed nibh vulputate laoreet. Morbi id nisi vehicula, porta nulla id, pharetra quam. Quisque vulputate vehicula metus, et fringilla odio sodales vitae. Nullam volutpat eget purus venenatis accumsan. Praesent urna dui, ornare vel leo a, imperdiet placerat nisi. Mauris porta iaculis purus a interdum. Quisque non arcu quam. Quisque nunc nulla, ullamcorper eget mi sit amet, hendrerit congue lectus. Praesent tellus nulla, tincidunt eget massa quis, rutrum convallis diam. Nullam dictum egestas ipsum, eget dignissim sem pellentesque et.',02013,4.96,00453,'Jennifer Hancock',00009,'/pdf/-2134042633.pdf','/images/books/book-8.jpg','/WEB-INF/classes/text/book1en.txt'),(00025,'The Night Manager','Phasellus sed urna vulputate metus fermentum fermentum. Duis eleifend vulputate ex, ac feugiat risus ullamcorper vitae. Fusce vitae magna in ipsum sagittis porttitor sed consectetur tellus. Praesent commodo, tellus eget scelerisque tincidunt, felis felis placerat tellus, at ornare mi nibh in neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In quis rhoncus dui. Morbi posuere tincidunt massa.',02000,3.67,00623,'Ethane Ford',00010,'/pdf/-2134042633.pdf','/images/books/book-9.jpg','/WEB-INF/classes/text/book2en.txt'),(00038,'MyBook1','MyBook1',02014,9.50,00024,'Gavin Robinson',00013,'/pdf/-2134042633.pdf','/images/books/297770952.jpeg','/WEB-INF/classes/text/-1236547980.txt'),(00042,'Death Note. Black Edition.','A college student, Lita Yagami, has brilliant prospects for the future and not the slightest idea than to fill the present. The guy is going crazy with boredom. But everything changes when he finds the notebook Shinigami - the god of death. Anyone whose name appears on her page will die. Light decides to use the Death Note to rid the world of evil. Where will these good intentions lead?',02018,23.20,00456,'',00020,'/pdf/-833234119.pdf','/images/books/537238932.jpg','/WEB-INF/classes/text/1758656362.txt'),(00047,'Rick and Morty','If you follow the incredible adventures of poor Morty and his grandfather, an alcoholic, scientist and part-time intergalactic criminal Rick Sanchez and tired of waiting for the release of the third season, then this book will definitely help you pass the time in anticipation. Prepare for a new portion of madness, but now in comics, because such stories you have not heard or seen!',02017,12.00,00400,'Z. Gorman, D. Royland',00021,'/pdf/-2134042633.pdf','/images/books/749146449.jpg','/WEB-INF/classes/text/1744109293.txt'),(00048,'Millicent Donovan','A college student, Lita Yagami, has brilliant prospects for the future and not the slightest idea than to fill the present. The guy is going crazy with boredom. But everything changes when he finds the notebook Shinigami - the god of death. Anyone whose name appears on her page will die. Light decides to use the Death Note to rid the world of evil. Where will these good intentions lead?',01234,123.00,00123,'Sondra Wilson',00020,'/pdf/-2134042633.pdf','/images/books/-276453379.jpg','/WEB-INF/classes/text/1613085396.txt'),(00066,'qweqwrqwr','qwrsdg dsg',02017,12.00,00634,'',00020,'/pdf/116931265.pdf','/images/books/1178704906.jpg','/WEB-INF/classes/text/437013467.txt');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books_has_genres`
--

DROP TABLE IF EXISTS `books_has_genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books_has_genres` (
  `book_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ жанра\n',
  `genre_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ книги',
  PRIMARY KEY (`book_id`,`genre_id`),
  KEY `fk_books_has_genres_genres1_idx` (`genre_id`),
  KEY `fk_books_has_genres_books1_idx` (`book_id`),
  CONSTRAINT `fk_books_has_genres_books1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_books_has_genres_genres1` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Связь многие ко многим для книг и жанров';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books_has_genres`
--

LOCK TABLES `books_has_genres` WRITE;
/*!40000 ALTER TABLE `books_has_genres` DISABLE KEYS */;
INSERT INTO `books_has_genres` VALUES (00017,00001),(00017,00002),(00048,00002),(00066,00002),(00018,00003),(00042,00003),(00019,00004),(00020,00005),(00047,00005),(00066,00005),(00020,00006),(00047,00006),(00021,00007),(00042,00007),(00022,00008),(00047,00008),(00023,00009),(00047,00009),(00024,00010),(00066,00010),(00024,00011),(00025,00012);
/*!40000 ALTER TABLE `books_has_genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genres` (
  `genre_id` smallint(5) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Суррогатный ключ жанра',
  `def_genre_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Жанр',
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='Жанры';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (00001,'History'),(00002,'Thriller'),(00003,'Romance'),(00004,'Satire'),(00005,'Horror'),(00006,'Religious'),(00007,'Health'),(00008,'Cook—books'),(00009,'Children’s books'),(00010,'Politics'),(00011,'Fantasy'),(00012,'Travel books');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `lang` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Внутренний ключ языка',
  `language_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Полное название языка',
  PRIMARY KEY (`lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица языков.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES ('en','English'),('ru','Russian');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `news_id` smallint(5) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Суррогатный ключ',
  `def_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Заголовок на английском.',
  `def_text` text NOT NULL COMMENT 'Новость(текст) на английском',
  `publish_date` datetime NOT NULL COMMENT 'Дата написания новости.',
  `user_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ на пользователя, написавшего новость.',
  `photo_url` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Указатель на фотографию к новости.',
  `thumbs_url` varchar(75) NOT NULL,
  PRIMARY KEY (`news_id`),
  KEY `fk_news_users1_idx` (`user_id`),
  CONSTRAINT `fk_news_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='Новости библиотеки';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (00006,'15) Pellentesque dignissim nisl sit amet sagittis tincidunt.',' Vestibulum et tempor lorem, a viverra sapien. Mauris mattis fermentum odio rhoncus posuere. Sed vitae placerat dui. Aenean scelerisque, odio ut consectetur fermentum, nulla nisi rhoncus turpis, quis aliquet mauris dolor ac nisi. Quisque nibh ante, volutpat vitae enim eget, varius sollicitudin odio. Morbi lacinia eget velit in sodales. Cras ullamcorper nibh quam, porttitor placerat ex auctor ut.','2018-05-21 11:22:03',00001,'/images/news/news-1.jpg','/images/thumbs/news/news-1.jpg'),(00007,'3) Vestibulum ullamcorper orci sit amet nibh gravida, at consequat urna bibendum.',' Nam id elit varius, interdum massa id, interdum enim. Vestibulum placerat ullamcorper nisl eget vehicula. Proin in tempor enim, ac posuere quam. Sed aliquet euismod mollis. Maecenas aliquet auctor eros eu lobortis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc sit amet odio lacus. Proin dictum eros neque, non egestas felis faucibus vel.','2018-06-27 11:23:03',00001,'/images/news/news-2.jpg','/images/thumbs/news/news-2.jpg'),(00008,'20) Quisque sed dolor non ligula placerat vehicula ac in turpis.',' Praesent pulvinar vitae purus vitae aliquet. Vivamus id enim nulla. Curabitur eget accumsan mi. Praesent sodales dignissim neque, quis scelerisque mi congue et. Nam laoreet auctor ligula, et varius ante. Morbi lobortis consectetur lorem ut aliquam. Nunc ac eros augue. Fusce augue sapien, dignissim non turpis sed, sagittis tempus libero. Phasellus lacinia quis augue nec dignissim.','2018-04-21 11:22:53',00003,'/images/news/news-3.jpg','/images/thumbs/news/news-3.jpg'),(00009,'18) Quisque porta justo ut tortor venenatis elementum.',' Vivamus sem lectus, blandit id volutpat eget, commodo mollis ipsum. Duis tempus fermentum diam, elementum mollis lacus volutpat ut. Phasellus quis metus eget dui venenatis condimentum. Integer tempor nisi non velit tristique, non vehicula mi feugiat. Phasellus sapien massa, accumsan eu lorem ut, viverra vulputate mi. In eu lectus iaculis, fermentum mauris a, mattis odio. Nulla auctor nibh massa, ac eleifend ipsum feugiat a. Sed dictum congue magna. Nullam vitae dui dictum, consectetur nibh non, tempus magna. In sit amet mi tincidunt, auctor ipsum nec, auctor felis. Vivamus malesuada ligula eget porttitor volutpat. Cras quis odio in metus ultricies placerat id vitae risus. Nulla velit mauris, fringilla eu leo et, ultrices auctor libero. Curabitur non rhoncus nunc, eget viverra nibh.','2018-05-01 11:24:03',00001,'/images/news/news-4.jpg','/images/thumbs/news/news-4.jpg'),(00010,'8) Pellentesque auctor elit ullamcorper ante tincidunt, eu imperdiet ipsum feugiat.',' Donec quam metus, elementum sit amet malesuada id, vehicula eu dui. Mauris lobortis sit amet sapien at euismod. Duis libero arcu, rutrum eu odio a, bibendum ornare purus. Sed et maximus arcu, vel lacinia nisi. Vestibulum metus diam, gravida vel ligula non, accumsan tristique tortor. Praesent tempor enim sed libero sagittis, sed vehicula ipsum commodo. Mauris rutrum interdum diam ac sollicitudin.','2018-06-21 12:22:03',00003,'/images/news/news-5.jpg','/images/thumbs/news/news-5.jpg'),(00011,'11) Integer imperdiet arcu ut magna viverra cursus.',' Aenean sit amet eleifend sem. Quisque id imperdiet lacus. Duis sed malesuada velit. Nullam porta nibh sed pretium congue. In hac habitasse platea dictumst. Sed nec vestibulum ligula. Suspendisse vitae sollicitudin enim. Etiam eu magna ac lectus tristique volutpat eu non mauris. Maecenas ultrices feugiat porta. Duis consectetur fermentum tellus vitae pulvinar.','2018-06-20 11:25:03',00001,'/images/news/news-6.jpg','/images/thumbs/news/news-6.jpg'),(00012,'10) Donec non ligula sed mauris tincidunt ultrices.',' Suspendisse non magna et ex rutrum posuere. Fusce et risus vitae orci lacinia mollis. Integer posuere ligula orci, sed consectetur orci feugiat in. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse pulvinar arcu scelerisque, hendrerit turpis sit amet, sollicitudin libero. Morbi pellentesque mollis dapibus. Duis libero est, lobortis id sodales at, vestibulum a turpis. Ut vehicula ornare lectus. Duis at velit turpis. Cras at nulla sed ex hendrerit ornare non ut justo. Curabitur mollis neque sit amet leo tempus placerat. Mauris porta, metus vitae tristique efficitur, risus tellus hendrerit augue, eget tristique massa est nec tellus. Curabitur neque enim, condimentum at eros quis, ultricies varius nulla. Nulla tempus imperdiet nisi, vitae tincidunt massa imperdiet pharetra. Mauris hendrerit nisl nec dui lobortis pharetra. In ullamcorper, lorem eu blandit maximus, magna purus sagittis nunc, non tincidunt justo dui eget urna.','2018-06-21 11:20:03',00001,'/images/news/news-7.jpg','/images/thumbs/news/news-7.jpg'),(00013,'16) Integer hendrerit erat eget tortor accumsan hendrerit.',' Curabitur mollis est congue posuere sagittis. Cras nibh velit, consequat faucibus justo sit amet, maximus suscipit felis. Phasellus suscipit lacus mollis imperdiet venenatis. Donec sit amet mi quis lacus aliquam placerat. Vivamus rutrum et nunc hendrerit volutpat. Morbi rhoncus velit sit amet nisl sollicitudin, eget tincidunt mi vulputate. Duis at tincidunt ex. Phasellus sed dolor nulla. Integer tincidunt interdum tempor.','2018-05-21 11:12:03',00004,'/images/news/news-8.jpg','/images/thumbs/news/news-8.jpg'),(00014,'19) enean imperdiet dolor at risus tempus luctus.',' Duis mattis eget turpis ac molestie. Vestibulum id risus feugiat, tincidunt elit a, pulvinar massa. Nam gravida metus vitae arcu vulputate commodo. Mauris sit amet fermentum sem, id vulputate ligula. Fusce cursus, justo id congue semper, metus nibh scelerisque lacus, quis tempus dolor neque nec ex. Nam pretium sed nulla eget semper. Nulla viverra rhoncus erat, id ullamcorper est consequat ut. Proin tempor libero ut lectus sodales posuere. Nunc cursus risus ac turpis dapibus porta. Morbi hendrerit laoreet nibh, placerat blandit dui feugiat vitae. Sed congue nunc non leo pharetra commodo. Quisque maximus luctus mi quis condimentum. Donec ligula sem, condimentum maximus egestas nec, efficitur et augue.','2018-04-26 11:22:00',00001,'/images/news/news-9.jpg','/images/thumbs/news/news-9.jpg'),(00015,'13) Vivamus in est id ante suscipit ultricies.',' Mauris ut ligula sed est porttitor elementum at sed ante. Sed et massa sodales, malesuada lorem eget, elementum nulla. Pellentesque tincidunt facilisis turpis. Nullam gravida, dolor eget dictum rutrum, sem libero auctor augue, eget sollicitudin purus dui quis enim. Donec dignissim tellus ut quam iaculis accumsan. Curabitur eget magna a magna pellentesque sollicitudin. Vestibulum maximus metus quis fringilla imperdiet. Donec porta libero eu odio rhoncus, a molestie felis lacinia. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis imperdiet fringilla ornare. Nam vitae urna diam. Pellentesque pellentesque, odio ac iaculis pharetra, turpis arcu sodales erat, nec sagittis lorem est sed magna. Sed aliquet arcu id ex ultrices eleifend. Maecenas ac urna orci.','2018-05-21 13:22:03',00001,'/images/news/news-10.jpg','/images/thumbs/news/news-10.jpg'),(00016,'6) Sed pretium ante ac enim consectetur vestibulum vel vel ipsum.',' Phasellus in nibh porta, vestibulum est in, tristique ante. Fusce in leo sit amet eros porta congue quis id arcu. Cras iaculis lorem mollis nisi luctus, et porttitor nibh convallis. Aenean placerat tristique consectetur. In eget efficitur eros. Nulla nec sagittis magna. Etiam nisi dolor, convallis nec nunc non, vehicula ullamcorper enim. Nunc tincidunt enim eget ante lacinia tempor. Cras a turpis finibus, accumsan risus quis, aliquam sapien. Praesent malesuada lacus risus, ut faucibus erat interdum vel. Quisque sed suscipit nibh, in pharetra massa. Vivamus varius dapibus mollis. Nulla nisl est, gravida ultrices rutrum eget, cursus ut ligula. Mauris mollis elit sit amet nulla luctus, vel tincidunt nibh vehicula. Nunc libero purus, rutrum eget aliquet id, ultricies sit amet lorem.','2018-06-22 14:00:03',00003,'/images/news/news-11.jpg','/images/thumbs/news/news-11.jpg'),(00017,'9) Cras et leo aliquet, condimentum lectus quis, molestie mi.',' Vestibulum mattis eros vitae fermentum cursus. Suspendisse vitae pharetra felis. Nam justo enim, mattis eu ipsum pellentesque, scelerisque vestibulum sapien. Nullam tincidunt justo sit amet pharetra ornare. Aliquam erat volutpat. Nunc blandit, turpis vel tincidunt bibendum, quam magna porta elit, sodales mattis sapien enim quis justo. Nulla velit justo, malesuada eget lectus at, mattis lacinia massa. Donec semper quam tempor sem scelerisque placerat. Etiam suscipit tempor sapien, in consectetur magna. Maecenas auctor at eros quis volutpat.','2018-06-21 11:22:03',00004,'/images/news/news-12.jpg','/images/thumbs/news/news-12.jpg'),(00018,'4) Donec in velit bibendum, efficitur tortor eu, mollis dolor.',' In gravida, libero vel bibendum interdum, dui tortor dapibus quam, quis placerat turpis lacus non odio. Maecenas dapibus commodo commodo. Aliquam nec metus turpis. Sed in turpis ac nisl tristique fermentum facilisis quis arcu. Pellentesque iaculis tempus velit ultrices imperdiet. Aenean sodales lorem ut mi sagittis, vitae finibus dui pretium. Phasellus maximus id sem non laoreet. Morbi magna elit, auctor vitae enim id, tempus pellentesque arcu. In consequat elit at lorem vehicula cursus. Phasellus faucibus consequat neque, sed faucibus elit venenatis a. Vestibulum a dolor ac lacus auctor malesuada.','2018-06-24 12:22:03',00001,'/images/news/news-13.jpg','/images/thumbs/news/news-13.jpg'),(00019,'14) Morbi id orci facilisis, accumsan turpis facilisis, tincidunt ex.',' Aenean commodo sapien nisi, id dapibus metus cursus et. Nulla ac massa dignissim, viverra sapien at, sollicitudin dolor. Cras tristique feugiat orci, in facilisis nibh rutrum in. In tincidunt pulvinar pretium. Mauris gravida enim at magna iaculis volutpat. Nam consequat enim sed ex egestas, sed dignissim nunc interdum. Proin consequat eget felis vel placerat. Morbi lobortis tellus vel mattis fringilla.','2018-05-21 13:22:03',00003,'/images/news/news-14.jpg','/images/thumbs/news/news-14.jpg'),(00020,'17) Nam nec lacus et odio vulputate sagittis accumsan at lacus.',' Phasellus ac ex augue. Proin vel neque sit amet ligula ullamcorper tincidunt a quis ex. Nunc fermentum tellus massa, eget lobortis nunc rutrum ultrices. In sed nulla pellentesque, maximus neque placerat, bibendum ante. Aenean ac mauris sed tortor iaculis tempor ac eu tortor. In ac metus turpis. Etiam pharetra ultrices sodales.','2018-05-12 15:22:03',00001,'/images/news/news-1.jpg','/images/thumbs/news/news-1.jpg'),(00021,'7) Comes from a line in section','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.','2018-06-21 15:17:31',00004,'/images/news/news-2.jpg','/images/thumbs/news/news-2.jpg'),(00022,'12) My first News','Text for my first news','2018-05-21 19:28:06',00001,'/images/news/news-3.jpg','/images/thumbs/news/news-3.jpg'),(00025,'5) condimentum lectus quis, molestie mi.',' Vestibulum et tempor lorem, a viverra sapien. Mauris mattis fermentum odio rhoncus posuere. Sed vitae placerat dui. Aenean scelerisque, odio ut consectetur fermentum, nulla nisi rhoncus turpis, quis aliquet mauris dolor ac nisi. Quisque nibh ante, volutpat vitae enim eget, varius sollicitudin odio. Morbi lacinia eget velit in sodales. Cras ullamcorper nibh quam, porttitor placerat ex auctor ut.','2018-06-23 13:51:38',00003,'/images/news/news-4.jpg','/images/thumbs/news/news-4.jpg'),(00027,'2) Hello Word','Hello WordHello WordHello WordHello WordHello o ordHello WordHello WrdHello WordHello WordHelHello WordHello Word','2018-07-06 15:41:20',00004,'/images/news/news-5.jpg','/images/thumbs/news/news-5.jpg'),(00033,'0) What is Lorem Ipsum?','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\r\n\r\nThe standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.','2018-07-07 14:49:28',00001,'/images/news/news-7.jpg','/images/thumbs/news/news-7.jpg'),(00042,'Where does it come from?','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\r\n\r\nThe standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.','2018-07-10 12:15:49',00001,'/images/news/news-9.jpg','/images/thumbs/news/news-9.jpg'),(00061,'Lukashenka decided the fate of Orsha Aircraft Repair Plant','President of Belarus Alexander Lukashenko demands to restore the effective work of the Orsha Aircraft Repair Plant. The corresponding instruction was given today, visiting this enterprise, BelTA informs.\r\n\r\n\"The owner is already not here (we are talking about the shareholders of PJSC Motor Sich (Ukraine) and the Belarusian CJSC Systems of Investments and Innovations). Owners are you (state organizations). All the owners who were here, they refused themselves from this plant. So act. This is a state enterprise from today, \"the head of state said.\r\n\r\nLukashenka was informed that the process of returning the plant to state ownership was prepared. The President expressed a number of criticism of the Ministry of Transport and Communications for the fact that it had previously withdrawn from the situation at this enterprise.\r\n\r\n\"Here my people work, your people - the Belarusians work, the Russians - our citizens of Belarus. It does not matter who owns these shares. It is our task that people here receive a normal salary, \"he said, referring to Minister Anatoly Sivak.\r\nThe President instructed during July to resolve all issues regarding the departmental subordination of the enterprise.\r\n\"The plant must work, no matter what it costs. The plant not only has to be - this plant should have a development perspective. And put an architectural project on my desk and so on, taking into account what the business does for the expansion of the airport, and so on, \"the president said.\r\nAlexander Lukashenko is sure that such an enterprise will be in demand for many years. \"Helicopter production and modernization, restoration, repair of helicopters - in the world of their sea - that\'s why you have a good piece of work,\" the head of state drew attention.\r\nThe President also noted that if there are difficulties with the supply of the necessary components from the traditional partners, then it is necessary to look for new options, diversify partnerships, independently master the release of certain components. \"Do not go around and think that we can not do anything. It is necessary to deal with this matter, if there is a market and helicopters for repair and modernization. Thousands of people - here is a good school, ideal conditions to work, \"- assured Alexander Lukashenko.\r\nThe head of state did not rule out that certain assistance and support could be provided to the enterprise by the state as well. \"We will help something for a certain period, but we need to turn around here,\" he said.\r\nAs reported by TUT.BY, in 2012, by the order of the President of Belarus, the state-owned company Motor Sich, controlled by Vyacheslav Bohuslayev (about 60% of shares), and the Sistema Investment and Innovation System (almost 40%) bought the state-owned stake in OARZ. The Belarusian and Ukrainian companies planned to invest about $ 12 million in the reconstruction, modernization of the production capacities of the Sarov region in 2012-2016, create over 300 jobs. Investors intended to invest in the social sphere of the plant and the town of Bolbasovo, where the plant is located. But not all the goals were realized.\r\nA source close to one of the current shareholders said that the plant severely shattered the \"conflict [of Russia] with Ukraine, as a result of which Russian deliveries were completely blocked.\" Earlier, \"Motor Sich\" provided more than 80% of the Russian aircraft industry\'s need for helicopter engines. With a focus on the Russian market, the Ukrainian company bought out the Belarusian plant, experts said.\r\nAfter the problems with the Russian Federation began, OARZ tried to maximize the geography of orders, working on the market of Uganda, Venezuela, Sudan, Nigeria, Yemen. At the same time, I looked for what, besides helicopters, it is possible to load capacities. This was in an interview with TUT.BY in 2016, told Alexander Sadovoy. But the profits for development were not enough.\r\nIn the second half of 2017, the situation in OARZ deteriorated. There were talks that the state could again become the main shareholder of the plant. It is worth noting that in October 2014 Alexander Lukashenko was not very pleased with the project\'s implementation and threatened to return OARZ to the bosom of the state in the event of anything.\r\nThis year, according to the interlocutors, it was a question of the fact that the controlling share holding of the Orarzesky GOZ would pass to the special armament exporter Belspetsvneshtechnika. According to another source, there will be a replacement of the \"Ukrainian shareholder, who disowned the plant six months ago, and the top managers left OARZ.\"','2018-08-02 18:28:29',00001,'/images/news/1735340918.jpg','/images/thumbs/news/392266274.jpg'),(00080,'qweqwtwt','wetqewyewryeyr','2018-08-02 12:56:44',00001,'/images/news/2057071201.jpg','/images/thumbs/news/-1857872301.jpg');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishing_houses`
--

DROP TABLE IF EXISTS `publishing_houses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publishing_houses` (
  `publishing_house_id` smallint(5) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Суррогатный ключ издательства.',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Издательство.',
  PRIMARY KEY (`publishing_house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='Издательские дома';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishing_houses`
--

LOCK TABLES `publishing_houses` WRITE;
/*!40000 ALTER TABLE `publishing_houses` DISABLE KEYS */;
INSERT INTO `publishing_houses` VALUES (00001,'Эксмо'),(00002,'ABC_CLIO'),(00003,'Ace Books'),(00004,'Basic Books'),(00005,'CRC Press'),(00006,'Del Rey Books'),(00007,'Orbit Books'),(00008,'Tor Books'),(00009,'Viking Books'),(00010,'Feral House'),(00011,'Future plc'),(00012,'НеЭксмо'),(00013,'MyBook'),(00017,'ÐÐ·Ð±ÑÐºÐ°'),(00018,'123'),(00019,'1'),(00020,'Ёжик'),(00021,'Азбука'),(00022,'PH');
/*!40000 ALTER TABLE `publishing_houses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` tinyint(3) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Суррогатный ключ.',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Название роли.',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Роли системы.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (001,'Administrator'),(002,'Moderator'),(003,'User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbooks`
--

DROP TABLE IF EXISTS `tbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbooks` (
  `book_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ на книгу.',
  `lang` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Внешний ключ на используемый язык.',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Название на соответствующем языке.',
  `description` text COMMENT 'Описание на соответствующем языке.',
  `authors` varchar(100) DEFAULT NULL,
  `pdf_file_url` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Ссылка на pdf файл на соответствующем языке.',
  `text_file_url` varchar(75) NOT NULL,
  PRIMARY KEY (`book_id`,`lang`),
  KEY `fk_books_has_languages_languages1_idx` (`lang`),
  KEY `fk_books_has_languages_books1_idx` (`book_id`),
  FULLTEXT KEY `fullText` (`name`),
  CONSTRAINT `fk_books_has_languages_books1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_books_has_languages_languages1` FOREIGN KEY (`lang`) REFERENCES `languages` (`lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Книги на разных языках.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbooks`
--

LOCK TABLES `tbooks` WRITE;
/*!40000 ALTER TABLE `tbooks` DISABLE KEYS */;
INSERT INTO `tbooks` VALUES (00017,'ru','Коллектор','Товарищи! дальнейшее развитие различных форм деятельности позволяет выполнять важные задания по разработке модели развития. Значимость этих проблем настолько очевидна, что консультация с широким активом требуют от нас анализа новых предложений. Таким образом дальнейшее развитие различных форм деятельности обеспечивает широкому кругу (специалистов) участие в формировании систем массового участия. Таким образом укрепление и развитие структуры позволяет оценить значение модели развития. Не следует, однако забывать, что рамки и место обучения кадров требуют от нас анализа новых предложений. Задача организации, в особенности же постоянный количественный рост и сфера нашей активности способствует подготовки и реализации модели развития.','Жюстин Честертон','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/book1ru.txt'),(00018,'ru','Дом на Бакервиль','Повседневная практика показывает, что консультация с широким активом требуют определения и уточнения позиций, занимаемых участниками в отношении поставленных задач. Значимость этих проблем настолько очевидна, что дальнейшее развитие различных форм деятельности позволяет выполнять важные задания по разработке соответствующий условий активизации.','Миллисент Донован','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/book2ru.txt'),(00019,'ru','Скандал в Богемия','Идейные соображения высшего порядка, а также реализация намеченных плановых заданий играет важную роль в формировании направлений прогрессивного развития. С другой стороны укрепление и развитие структуры обеспечивает широкому кругу (специалистов) участие в формировании систем массового участия. Повседневная практика показывает, что постоянное информационно-пропагандистское обеспечение нашей деятельности требуют от нас анализа модели развития. Равным образом начало повседневной работы по формированию позиции позволяет оценить значение систем массового участия. Разнообразный и богатый опыт дальнейшее развитие различных форм деятельности способствует подготовки и реализации дальнейших направлений развития.','Арнольд(Арни) Джексон','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/book3ru.txt'),(00020,'ru','Приключения танцующего мужчины','Повседневная практика показывает, что рамки и место обучения кадров требуют от нас анализа соответствующий условий активизации. Задача организации, в особенности же новая модель организационной деятельности требуют от нас анализа систем массового участия.','Сьюзан Болдуин','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/book4ru.txt'),(00021,'ru','10 маленьких негритят','Таким образом дальнейшее развитие различных форм деятельности обеспечивает широкому кругу (специалистов) участие в формировании систем массового участия. Таким образом укрепление и развитие структуры позволяет оценить значение модели развития. Не следует, однако забывать, что рамки и место обучения кадров требуют от нас анализа новых предложений. Задача организации, в особенности же постоянный количественный рост и сфера нашей активности способствует подготовки и реализации модели развития.','Кил Булман','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/book5ru.txt'),(00022,'ru','дом негритят','Какой-то текст','Пэйдж Ганзалес','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/book6ru.txt'),(00038,'ru','Моя книга1','Моя книга1','Брайан Ли','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/ru/-493886862.txt'),(00042,'ru','Смертельная записка. Чёрное издание.','Заместитель министра здравоохранения Игорь Лосицкий задержан и помещен под стражу за благоприятное решение вопросов, входящих в его компетенцию по организации государственной регистрации изделий медицинского назначения, а также лояльное отношение при выборе поставщиков медицинской техники. «Суммы взяток варьировались от $ 2,5 тыс. до $ 10 тыс.», — проинформировал Валерий Вакульчик.\r\n\r\nСреди задержанных также генеральный директор предприятия «Белфармация» Вячеслав Гнитий, который получал взятки за организацию первоочередных расчетов с коммерческими структурами, а также за выбор «нужных» поставщиков медицинской продукции.\r\nЧитать полностью:  https://news.tut.by/economics/600401.html','','/pdf/ru/-1246669625.pdf','/WEB-INF/classes/text/ru/763908144.txt'),(00047,'ru','Рик и Морти','Если вы следите за невероятными приключениями бедолаги Морти и его деда-алкоголика, учёного и по совместительству межгалактического преступника Рика Санчеза и устали ждать выхода третьего сезона, то эта книга определенно поможет вам скоротать время в ожидании. Приготовьтесь к новой порции безумия, но теперь уже в комиксах, потому что таких историй вы ещё не слышали и не видели!','Остин Хейли','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/ru/488453713.txt'),(00048,'ru','Плохой мальчик','Товарищи! дальнейшее развитие различных форм деятельности позволяет выполнять важные задания по разработке модели развития. Значимость этих проблем настолько очевидна, что консультация с широким активом требуют от нас анализа новых предложений. Таким образом дальнейшее развитие различных форм деятельности обеспечивает широкому кругу (специалистов) участие в формировании систем массового участия. Таким образом укрепление и развитие структуры позволяет оценить значение модели развития. Не следует, однако забывать, что рамки и место обучения кадров требуют от нас анализа новых предложений. Задача организации, в особенности же постоянный количественный рост и сфера нашей активности способствует подготовки и реализации модели развития.','Джоана Митчел','/pdf/ru/763771819.pdf','/WEB-INF/classes/text/ru/-1182817696.txt'),(00066,'ru','йцуцуецйе','ыпвр вырвы рвы р','','/pdf/ru/-1909370921.pdf','/WEB-INF/classes/text/ru/-1880164883.txt');
/*!40000 ALTER TABLE `tbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tgenres`
--

DROP TABLE IF EXISTS `tgenres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tgenres` (
  `genre_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ на жанр.',
  `lang` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Внешний ключ на используемый язык.',
  `genre_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Жанр',
  PRIMARY KEY (`genre_id`,`lang`),
  KEY `fk_genres_has_languages_languages1_idx` (`lang`),
  KEY `fk_genres_has_languages_genres1_idx` (`genre_id`),
  CONSTRAINT `fk_genres_has_languages_genres1` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_genres_has_languages_languages1` FOREIGN KEY (`lang`) REFERENCES `languages` (`lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Переведённые жанры';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tgenres`
--

LOCK TABLES `tgenres` WRITE;
/*!40000 ALTER TABLE `tgenres` DISABLE KEYS */;
INSERT INTO `tgenres` VALUES (00001,'ru','Исторический'),(00002,'ru','Триллер'),(00003,'ru','Роман'),(00004,'ru','Сатира'),(00005,'ru','Ужасы'),(00006,'ru','Религиозный'),(00007,'ru','Здоровье'),(00008,'ru','Кулинарная'),(00009,'ru','Детская'),(00010,'ru','Политика'),(00011,'ru','Фантастика'),(00012,'ru','Путешествие');
/*!40000 ALTER TABLE `tgenres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tnews`
--

DROP TABLE IF EXISTS `tnews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tnews` (
  `news_id` smallint(5) unsigned zerofill NOT NULL COMMENT 'Внешний ключ на оригинальную новость.',
  `lang` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Внешний ключ на язык',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Заголовок на соответствующем языке.',
  `text` text NOT NULL COMMENT 'Текст новости на соответствующем языке.',
  PRIMARY KEY (`news_id`,`lang`),
  KEY `fk_news_has_languages_languages1_idx` (`lang`),
  KEY `fk_news_has_languages_news1_idx` (`news_id`),
  CONSTRAINT `fk_news_has_languages_languages1` FOREIGN KEY (`lang`) REFERENCES `languages` (`lang`),
  CONSTRAINT `fk_news_has_languages_news1` FOREIGN KEY (`news_id`) REFERENCES `news` (`news_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Новости на разных языках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tnews`
--

LOCK TABLES `tnews` WRITE;
/*!40000 ALTER TABLE `tnews` DISABLE KEYS */;
INSERT INTO `tnews` VALUES (00006,'ru','Повседневная практика показывает, что начало повседневной работы по формированию',' Повседневная практика показывает, что начало повседневной работы по формированию позиции влечет за собой процесс внедрения и модернизации новых предложений. Таким образом укрепление и развитие структуры играет важную роль в формировании позиций, занимаемых участниками в отношении поставленных задач. Повседневная практика показывает, что реализация намеченных плановых заданий обеспечивает широкому кругу (специалистов) участие в формировании существенных финансовых и административных условий. С другой стороны дальнейшее развитие различных форм деятельности влечет за собой процесс внедрения и модернизации соответствующий условий активизации. Значимость этих проблем настолько очевидна, что консультация с широким активом представляет собой интересный эксперимент проверки позиций, занимаемых участниками в отношении поставленных задач.'),(00007,'ru','С другой стороны новая модель организационной деятельности влечет',' Не следует, однако забывать, что дальнейшее развитие различных форм деятельности влечет за собой процесс внедрения и модернизации системы обучения кадров, соответствует насущным потребностям. Повседневная практика показывает, что постоянное информационно-пропагандистское обеспечение нашей деятельности требуют определения и уточнения систем массового участия.'),(00008,'ru','Не следует, однако забывать, что дальнейшее развитие различных форм деятельности',' Значимость этих проблем настолько очевидна, что сложившаяся структура организации влечет за собой процесс внедрения и модернизации новых предложений. Задача организации, в особенности же новая модель организационной деятельности обеспечивает широкому кругу (специалистов) участие в формировании направлений прогрессивного развития. Равным образом консультация с широким активом способствует подготовки и реализации форм развития. Идейные соображения высшего порядка, а также начало повседневной работы по формированию позиции способствует подготовки и реализации позиций, занимаемых участниками в отношении поставленных задач.'),(00009,'ru','Не следует, однако забывать, что постоянный количественный рост и сфера',' С другой стороны новая модель организационной деятельности позволяет выполнять важные задания по разработке модели развития. Разнообразный и богатый опыт укрепление и развитие структуры обеспечивает широкому кругу (специалистов) участие в формировании существенных финансовых и административных условий. Повседневная практика показывает, что постоянное информационно-пропагандистское обеспечение нашей деятельности играет важную роль в формировании новых предложений. Идейные соображения высшего порядка, а также реализация намеченных плановых заданий представляет собой интересный эксперимент проверки дальнейших направлений развития. Значимость этих проблем настолько очевидна, что укрепление и развитие структуры требуют от нас анализа соответствующий условий активизации.'),(00010,'ru','Равным образом дальнейшее развитие различных форм деятельности представляет',' Не следует, однако забывать, что новая модель организационной деятельности требуют от нас анализа существенных финансовых и административных условий. С другой стороны сложившаяся структура организации позволяет выполнять важные задания по разработке системы обучения кадров, соответствует насущным потребностям. Задача организации, в особенности же консультация с широким активом требуют определения и уточнения форм развития.'),(00011,'ru','Значимость этих проблем настолько очевидна, что сложившаяся структура',' Таким образом укрепление и развитие структуры позволяет выполнять важные задания по разработке систем массового участия. Идейные соображения высшего порядка, а также сложившаяся структура организации способствует подготовки и реализации систем массового участия. С другой стороны начало повседневной работы по формированию позиции представляет собой интересный эксперимент проверки существенных финансовых и административных условий.'),(00012,'ru','Таким образом постоянный количественный рост и сфера нашей активности требуют',' Не следует, однако забывать, что начало повседневной работы по формированию позиции требуют определения и уточнения систем массового участия. Таким образом постоянное информационно-пропагандистское обеспечение нашей деятельности позволяет оценить значение дальнейших направлений развития.'),(00013,'ru','Товарищи! постоянный количественный рост и сфера нашей активности представляет',' Идейные соображения высшего порядка, а также постоянное информационно-пропагандистское обеспечение нашей деятельности обеспечивает широкому кругу (специалистов) участие в формировании позиций, занимаемых участниками в отношении поставленных задач. Значимость этих проблем настолько очевидна, что дальнейшее развитие различных форм деятельности позволяет оценить значение модели развития. Равным образом укрепление и развитие структуры играет важную роль в формировании соответствующий условий активизации. Товарищи! постоянное информационно-пропагандистское обеспечение нашей деятельности способствует подготовки и реализации модели развития. Повседневная практика показывает, что укрепление и развитие структуры требуют определения и уточнения соответствующий условий активизации. Значимость этих проблем настолько очевидна, что новая модель организационной деятельности обеспечивает широкому кругу (специалистов) участие в формировании форм развития.'),(00014,'ru','С другой стороны новая модель организационной деятельности позволяет выполнять',' Не следует, однако забывать, что новая модель организационной деятельности позволяет оценить значение существенных финансовых и административных условий. Задача организации, в особенности же постоянный количественный рост и сфера нашей активности обеспечивает широкому кругу (специалистов) участие в формировании новых предложений.'),(00015,'ru','С другой стороны дальнейшее развитие различных форм деятельности позволяет выполнять',' Значимость этих проблем настолько очевидна, что новая модель организационной деятельности влечет за собой процесс внедрения и модернизации направлений прогрессивного развития. Таким образом укрепление и развитие структуры требуют определения и уточнения существенных финансовых и административных условий. Таким образом реализация намеченных плановых заданий позволяет оценить значение соответствующий условий активизации. Товарищи! консультация с широким активом обеспечивает широкому кругу (специалистов) участие в формировании дальнейших направлений развития. Не следует, однако забывать, что консультация с широким активом влечет за собой процесс внедрения и модернизации системы обучения кадров, соответствует насущным потребностям.'),(00016,'ru','Не следует, однако забывать, что новая модель организационной деятельности требуют',' Равным образом новая модель организационной деятельности представляет собой интересный эксперимент проверки систем массового участия. Таким образом постоянный количественный рост и сфера нашей активности позволяет выполнять важные задания по разработке позиций, занимаемых участниками в отношении поставленных задач.'),(00017,'ru','Разнообразный и богатый опыт дальнейшее развитие различных форм деятельности',' Равным образом консультация с широким активом позволяет выполнять важные задания по разработке соответствующий условий активизации. Равным образом рамки и место обучения кадров представляет собой интересный эксперимент проверки дальнейших направлений развития. Значимость этих проблем настолько очевидна, что начало повседневной работы по формированию позиции играет важную роль в формировании соответствующий условий активизации. Равным образом постоянный количественный рост и сфера нашей активности позволяет выполнять важные задания по разработке направлений прогрессивного развития. Таким образом начало повседневной работы по формированию позиции способствует подготовки и реализации существенных финансовых и административных условий.'),(00018,'ru','Товарищи! реализация намеченных плановых заданий требуют определения',' Повседневная практика показывает, что постоянный количественный рост и сфера нашей активности способствует подготовки и реализации модели развития. Идейные соображения высшего порядка, а также новая модель организационной деятельности представляет собой интересный эксперимент проверки модели развития.'),(00019,'ru','Таким образом укрепление и развитие структуры позволяет выполнять важные',' Задача организации, в особенности же реализация намеченных плановых заданий играет важную роль в формировании форм развития. Товарищи! новая модель организационной деятельности позволяет оценить значение позиций, занимаемых участниками в отношении поставленных задач.'),(00020,'ru','Таким образом консультация с широким активом представляет собой интересный эксперимент',' Повседневная практика показывает, что сложившаяся структура организации в значительной степени обуславливает создание дальнейших направлений развития. Не следует, однако забывать, что постоянное информационно-пропагандистское обеспечение нашей деятельности в значительной степени обуславливает создание новых предложений. Равным образом рамки и место обучения кадров требуют определения и уточнения новых предложений.'),(00027,'ru','Привет мир!','Привет мир!Привет мир!Привет мир!Привет мир!Привет мир!Привет мир!Привет мир!'),(00042,'ru','Идейные соображения высшего порядка?',' Идейные соображения высшего порядка, а также консультация с широким активом позволяет оценить значение системы обучения кадров, соответствует насущным потребностям. Таким образом начало повседневной работы по формированию позиции требуют определения и уточнения позиций, занимаемых участниками в отношении поставленных задач. Задача организации, в особенности же рамки и место обучения кадров обеспечивает широкому кругу (специалистов) участие в формировании соответствующий условий активизации.\r\n\r\nС другой стороны реализация намеченных плановых заданий требуют от нас анализа системы обучения кадров, соответствует насущным потребностям. Равным образом новая модель организационной деятельности требуют от нас анализа направлений прогрессивного развития. Таким образом укрепление и развитие структуры представляет собой интересный эксперимент проверки системы обучения кадров, соответствует насущным потребностям. Равным образом рамки и место обучения кадров в значительной степени обуславливает создание системы обучения кадров, соответствует насущным потребностям. Значимость этих проблем настолько очевидна, что реализация намеченных плановых заданий позволяет оценить значение направлений прогрессивного развития. Не следует, однако забывать, что укрепление и развитие структуры играет важную роль в формировании соответствующий условий активизации.\r\n\r\nЗначимость этих проблем настолько очевидна, что сложившаяся структура организации влечет за собой процесс внедрения и модернизации системы обучения кадров, соответствует насущным потребностям. Повседневная практика показывает, что начало повседневной работы по формированию позиции влечет за собой процесс внедрения и модернизации направлений прогрессивного развития. Значимость этих проблем настолько очевидна, что консультация с широким активом играет важную роль в формировании системы обучения кадров, соответствует насущным потребностям. Значимость этих проблем настолько очевидна, что начало повседневной работы по формированию позиции требуют определения и уточнения системы обучения кадров, соответствует насущным потребностям. С другой стороны сложившаяся структура организации требуют определения и уточнения позиций, занимаемых участниками в отношении поставленных задач. '),(00061,'ru','Лукашенко решил судьбу Оршанского авиаремонтного завода','Президент Беларуси Александр Лукашенко требует восстановить эффективную работу Оршанского авиаремонтного завода. Соответствующее поручение он дал сегодня, посещая это предприятие, передает БЕЛТА.\r\n\r\n«Собственника уже здесь нет (речь об акционерах ПАО «Мотор Сич» (Украина) и белорусского ЗАО «Системы инвестиций и инноваций»). Собственники — вы (государственные организации). Все собственники, которые здесь были, они отказались сами от этого завода. Поэтому действуйте. Это государственное предприятие с сегодняшнего дня», — заявил глава государства.\r\n\r\nЛукашенко доложили, что подготовлен процесс возвращения завода в собственность государства. Президент высказал ряд нареканий в адрес Министерства транспорта и коммуникаций за то, что оно ранее самоустранилось от ситуации на этом предприятии.\r\n\r\n«Тут работают мои люди, твои люди — белорусы работают, русские — наши граждане Беларуси. Какая разница, кому принадлежат эти акции. Это наша задача, чтобы люди здесь получали нормальную заработную плату», — сказал он, обращаясь к министру Анатолию Сиваку.\r\nПрезидент поручил в течение июля решить все вопросы в отношении ведомственной подчиненности предприятия.\r\n«Завод должен работать, чего бы это ни стоило. Заводу не только быть — этот завод должен иметь перспективу развития. И положите мне на стол архитектурный проект и прочее с учетом того, что здесь делает бизнес для расширения аэропорта и так далее», — заявил президент.\r\nАлександр Лукашенко уверен, что подобное предприятие будет востребовано на протяжении многих лет. «Вертолетное производство и модернизация, восстановление, ремонт вертолетов — в мире их море — поэтому кусок работы у вас хороший», — обратил внимание глава государства.\r\nПрезидент также отметил, что если есть трудности с поставкой необходимых комплектующих со стороны традиционных партнеров, то необходимо искать новые варианты, диверсифицировать партнерские связи, самостоятельно осваивать выпуск тех или иных комплектующих. «Не надо ходить и думать, что мы ничего не можем. Надо заниматься этим делом, если есть рынок и вертолеты для ремонта и модернизации. Тысячи человек — здесь хорошая школа, идеальные условия, чтобы работать», — уверен Александр Лукашенко.\r\nГлава государства не исключил, что определенная помощь и поддержка может быть оказана предприятию и со стороны государства. «Чем-то поможем на какой-то период, но надо разворачиваться здесь», — сказал он.\r\nКак сообщал TUT.BY, в 2012 году по распоряжению президента Беларуси госпакет акций ОАРЗ купили украинская компания «Мотор Сич», подконтрольная Вячеславу Богуслаеву (около 60% акций), и «Системы инвестиций и инноваций» Александра Садового (почти 40%). Белорусская и украинская компании планировали в течение 2012−2016 годов инвестировать в реконструкцию, модернизацию производственных мощностей ОАРЗ около 12 млн долларов, создать свыше 300 рабочих мест. Инвесторы намеревались вложить серьезные средства в развитие социальной сферы завода и городского поселка Болбасово, где находится завод. Но не все намеченное удалось реализовать.\r\nИсточник, близкий к одному из действующих акционеров, говорил, что завод сильно подкосил «конфликт [России] с Украиной, в результате чего оказались полностью заблокированы российские поставки». Ранее «Мотор Сич» обеспечивал более чем на 80% потребность авиапрома РФ в вертолетных двигателях. С прицелом на российский рынок украинская компания и выкупила белорусский завод, отмечали эксперты.\r\nПосле того как начались проблемы с РФ, ОАРЗ старался максимально расширять географию заказов, работая на рынок Уганды, Венесуэлы, Судана, Нигерии, Йемена. Параллельно искал, чем, кроме вертолетов, можно подгрузить мощности. Об этом в интервью TUT.BY в 2016 году рассказывал Александр Садовой. Но прибыли на развитие было недостаточно.\r\nВо второй половине 2017 года положение на ОАРЗ ухудшилось. Появились разговоры о том, что государство может вновь стать основным акционером завода. Стоит сказать, что в октябре 2014 года Александр Лукашенко был не очень доволен реализацией проекта и угрожал вернуть ОАРЗ в лоно государства в случае чего.\r\nВ нынешнем году, по словам собеседников, речь шла о том, что контрольный пакет акций ОАРЗ перейдет спецэкспортеру вооружений «Белспецвнештехнике». По словам другого источника, будет замена «украинского акционера, который полгода назад открестился от завода, покинули ОАРЗ и его топ-менеджеры».\r\n'),(00080,'ru','йцкварвыр','ыфпыврпфывр');
/*!40000 ALTER TABLE `tnews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` smallint(5) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Суррогатный ключ.',
  `login` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Логин пользователя. До 15 символов. Уникальный.',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `first_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Имя пользователя.',
  `last_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Фамилия пользователя.',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Электронный адресс пользователя. Не обязательно.',
  `registration_date` datetime NOT NULL COMMENT 'Дата регистрации пользователя.',
  `role_id` tinyint(3) unsigned zerofill NOT NULL COMMENT 'Внешний ключ на таблицу ролей.',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_users_roles1_idx` (`role_id`),
  CONSTRAINT `fk_users_roles1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='Пользователи.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (00001,'admin','e00cf25ad42683b3df678c61f42c6bda','Nikita','Shkonda','admin@admin.com','2018-06-19 20:46:38',001),(00003,'GipFel','6968978a9a535d7fe9facc1f54b84b94','Inav','Vasiliev','vasya@mail.ru','2018-06-22 19:27:33',002),(00004,'franceMan','c4680909f48901a488488e83848070cf','Vasiliy','Ivanov','vasya@mail.ru','2018-06-22 19:28:45',002),(00005,'qwerty','d8578edf8458ce06fbc5bb76a58c5ca4','Semion','Petrov','qwerty@mail.ru','2018-06-22 19:29:17',002),(00006,'EnglishMan','a6af6634b1b845ecb118e55f151396aa','Viktor','Surich','man@mail.ru','2018-06-22 19:29:50',003),(00007,'FromBelarus','7823e19fadfd9066779b7c2c28412f6d','Eugene','Bulbash','by@mail.ru','2018-06-22 19:30:28',003),(00014,'man','7823e19fadfd9066779b7c2c28412f6d','Eugene','Bulbash','by@mail.ru','2018-06-22 19:31:28',003),(00015,'man1','7823e19fadfd9066779b7c2c28412f6d','Eugene','Bulbash','by@mail.ru','2018-06-22 19:32:28',003),(00016,'man2','7823e19fadfd9066779b7c2c28412f6d','Eugene','Bulbash','by@mail.ru','2018-06-22 19:33:28',003),(00017,'man3','7823e19fadfd9066779b7c2c28412f6d','Eugene','Bulbash','by@mail.ru','2018-06-22 19:34:28',003),(00018,'man4','7823e19fadfd9066779b7c2c28412f6d','Eugene','Bulbash','by@mail.ru','2018-06-22 19:35:28',003),(00019,'man5','7823e19fadfd9066779b7c2c28412f6d','Eugene','Bulbash','by@mail.ru','2018-06-22 19:36:28',002),(00020,'woman','7823e19fadfd9066779b7c2c28412f6d','Eugene','Bulbashowa','by@mail.ru','2018-07-14 15:00:04',003);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'library'
--

--
-- Dumping routines for database 'library'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_book` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_book`( in name nvarchar(100),
														in description TEXT,
														in publish_year smallint,
														in authors nvarchar(100),
														in price decimal(5,2),
														in pages smallint,
														in publishing_house_name nvarchar(50),
														in pdf_file_url nvarchar(75),
														in cover_url nvarchar(75),
														in text_file_url nvarchar(75),
                                                        
														out book_id smallint)
BEGIN

	DECLARE publishing_house_id smallint;

	if (!exists (
		SELECT publishing_houses.publishing_house_id
			FROM publishing_houses
		WHERE publishing_houses.name = publishing_house_name))
	THEN
		INSERT INTO publishing_houses (`name`) VALUES (publishing_house_name);
	END IF;
	
    set publishing_house_id = (
					SELECT publishing_houses.publishing_house_id
						FROM publishing_houses
					WHERE publishing_houses.name = publishing_house_name);
    
	INSERT INTO `books` (  `def_name`,
						   `def_description`,
						   `publish_year`,
						   `price`,
						   `pages`,
						   `publishing_house_id`,
						   `def_pdf_file_url`,
						   `cover_url`,
						   `def_text_file_url`,
						   `def_authors`) 
                           
			VALUES (		name,
							description,
							publish_year,
							price,
							pages,
							publishing_house_id,
							pdf_file_url,
							cover_url,
							text_file_url,
							authors);
	
    set book_id =  (
		select books.book_id
			from books 
		where   books.def_name=name 
			AND books.def_description=description
            AND books.publish_year=publish_year
            AND books.def_authors = authors
            AND books.price=price
            AND books.pages=pages
            AND books.publishing_house_id=publishing_house_id
            AND books.def_pdf_file_url=pdf_file_url
            AND books.cover_url=cover_url
            AND books.def_text_file_url=text_file_url);
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_book_genre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_book_genre`(in genre_id smallint,
															 in book_id smallint)
BEGIN	
	INSERT INTO `books_has_genres` (
			`book_id`,
			`genre_id`)
	VALUES (book_id,
			genre_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_news` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_news`( in title nvarchar(100),
														in `text` TEXT,
														in photo_url nvarchar(75),
														in thumbs_url nvarchar(75),
														in user_id smallint,

														out news_id smallint)
BEGIN
	INSERT INTO `news` ( `def_title`,
						 `def_text`,
						 `publish_date`,
						 `user_id`,
						 `photo_url`,
						 `thumbs_url`)
		VALUES (          title,
						  `text`,
						  NOW(),
						  user_id,
					  	  photo_url,
						  thumbs_url);
    set news_id = (
		select news.news_id 
			from news
		where   news.def_title = title 
			AND news.def_text = text 
			AND news.user_id=user_id 
			AND news.photo_url = photo_url 
			AND news.thumbs_url = thumbs_url);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_translated_book` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_translated_book`(  in book_id smallint,
																	in lang nvarchar(2),
																	in name nvarchar(100),
																	in authors nvarchar(100),
																	in description TEXT,
																	in pdf_file_url nvarchar(75),
																	in text_file_url nvarchar(75))
BEGIN
	INSERT INTO `tbooks` (  `book_id`,
							`lang`,
							`name`,
							`description`,
							`pdf_file_url`,
							`text_file_url`,
							`authors`)
		VALUES (			book_id,
							lang,
							name,
							description,
							pdf_file_url,
							text_file_url,
							authors);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_translated_news` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_translated_news`(  in news_id smallint,
																	in title nvarchar(100),
																	in `text` TEXT,
																	in lang nvarchar(2))
BEGIN
	INSERT INTO `tnews` (`news_id`, `title`, `text`, `lang`)
		VALUES (news_id, title, `text`, lang);    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_user`( in login nvarchar(20),
														in `password` nvarchar(100),
														in first_name nvarchar(20),
														in last_name nvarchar(20),
														in email nvarchar(20),
														in role_name nvarchar(20))
BEGIN
	DECLARE role_id INT;
    
    set role_id = (
		SELECT roles.role_id
			FROM roles
		WHERE roles.role_name = role_name);
        
	INSERT INTO users ( users.login,
						users.password,
						users.first_name,
						users.last_name,
						users.email,
						users.role_id,
						users.registration_date) 
		VALUES (		login,
						`password`,
						first_name,
						last_name,
						email,
						role_id,
						NOW());
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calc_pages_in_bookmarks` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calc_pages_in_bookmarks`(  in count_bookmarks_on_page smallint,
																		in user_id smallint,
																		in lang nvarchar(2),
                                                                        
																		out result smallint)
BEGIN
	DECLARE count_bookmarks smallint;
  	
	set count_bookmarks = (
		select count(bookmarks.book_id)
			from bookmarks
		where   bookmarks.user_id = user_id
			AND bookmarks.lang = lang);
    
    SET result = CEILING(count_bookmarks/count_bookmarks_on_page);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calc_pages_in_books` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calc_pages_in_books`(  in count_books_on_page smallint,
																	in lang nvarchar(2),
                                                                    
																	out result smallint)
BEGIN
	DECLARE count_books smallint;
    DECLARE def_lang nvarchar(2) DEFAULT 'en';
    
	IF (lang = def_lang) THEN
		set count_books = (
			select count(books.book_id)
				from books);
    ELSE
		set count_books = (
			select count(tbooks.book_id)
				from tbooks
			where tbooks.lang = lang);
    END IF;
    
    SET result = CEILING(count_books/count_books_on_page);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calc_pages_in_book_search` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calc_pages_in_book_search`(in search NVARCHAR(100),
																		in lang nvarchar(2),
																		in count_books_on_page smallint,
                                                                        
																		out result smallint)
BEGIN
	DECLARE count_books smallint;
    DECLARE def_lang nvarchar(2) DEFAULT 'en';
    
	IF (lang = def_lang) THEN
		set count_books = (
			select count(b.book_id)
				from (
					SELECT books.book_id
						FROM books
					WHERE MATCH (def_name) AGAINST (search IN NATURAL LANGUAGE MODE)) as b);
    ELSE
		set count_books = (
			select count(tb.book_id)
				from (
					SELECT tbooks.book_id
						FROM tbooks
					WHERE MATCH (`name`) AGAINST (search IN NATURAL LANGUAGE MODE) AND tbooks.lang = lang) as tb);
    END IF;
    
    SET result = CEILING(count_books/count_books_on_page);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calc_pages_in_news` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calc_pages_in_news`(   in count_news_on_page smallint,
																	in lang nvarchar(2),
                                                                    
																	out result smallint)
BEGIN
	DECLARE count_news smallint;
    DECLARE def_lang nvarchar(2) DEFAULT 'en';
    
	IF (lang = def_lang) THEN
		set count_news = (
			select count(news.news_id)
				from news);
    ELSE
		set count_news = (
			select count(tnews.news_id)
				from tnews
			where tnews.lang = lang);
    END IF;
    
    SET result = CEILING(count_news/count_news_on_page);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calc_pages_in_users` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calc_pages_in_users`(  in count_users_on_page smallint,

																	out result smallint)
BEGIN
	DECLARE count_users smallint;
    
    set count_users = (
		SELECT  COUNT(users.user_id)	
			FROM users);
    
    SET result = CEILING(count_users/count_users_on_page);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calc_pages_in_users_search` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calc_pages_in_users_search`(   in search NVARCHAR(100),
																			in count_users_on_page smallint,
                                                                            
																			out result smallint)
BEGIN
	DECLARE count_users smallint;
    
    set count_users = (
		SELECT  COUNT(users.user_id)	
			FROM users
		WHERE users.login like CONCAT('%',search,'%'));
    
    SET result = CEILING(count_users/count_users_on_page);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `change_user_role` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `change_user_role`( in user_id smallint,
																in role_name NVARCHAR(100))
BEGIN
	UPDATE users 
		SET role_id = (
			SELECT role_id
				from roles
			where roles.role_name=role_name)
	WHERE users.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_book` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_book`(in book_id smallint)
BEGIN
	DELETE FROM `books` 
    WHERE `books`.`book_id`=book_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_bookmark` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_bookmark`(  in user_id smallint,
																in book_id smallint,
																in lang varchar(2))
BEGIN
	DELETE FROM bookmarks
    WHERE   bookmarks.book_id=book_id
		AND bookmarks.user_id=user_id
		AND bookmarks.lang=lang;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_book_genre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_book_genre`(in book_id smallint)
BEGIN	
	delete from books_has_genres 
	where books_has_genres.book_id = book_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_news` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_news`(in news_id smallint)
BEGIN
	delete from news
    where news.news_id=news_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_user`(in user_id smallint)
BEGIN
	delete from users 
    where users.user_id=user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_book` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_book`( in search NVARCHAR(100),
														 in lang NVARCHAR(2),
														 in count_books_on_page smallint,
														 in number_of_page smallint)
BEGIN
	DECLARE def_lang nvarchar(2) DEFAULT 'en';    
    DECLARE number_of_first_books int;
    
    set number_of_first_books = (number_of_page - 1) * count_books_on_page;
    
	IF (lang = def_lang) THEN
		SELECT * from (
			SELECT  books.book_id,
					def_name AS name, 				 
					publish_year, 
					price, 
					cover_url
			FROM books
        ) as b
        WHERE MATCH (b.`name`) AGAINST (search IN NATURAL LANGUAGE MODE)
			LIMIT number_of_first_books, count_books_on_page;
    ELSE
		SELECT * from (
			SELECT  books.book_id,
					name, 
					publish_year, 
					price, 
					cover_url
			FROM books
			RIGHT JOIN tbooks on books.book_id = tbooks.book_id
			WHERE tbooks.lang = lang
		) as tb
        WHERE MATCH (tb.`name`) AGAINST (search IN NATURAL LANGUAGE MODE)
			LIMIT number_of_first_books, count_books_on_page;
	END IF;    	
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_user`(    in search NVARCHAR(100),
															in count_users_on_page smallint,
															in number_of_page smallint)
BEGIN 
    DECLARE number_of_first_user int;
    set number_of_first_user = (number_of_page - 1) * count_users_on_page;
    
	SELECT  users.user_id,
			users.login, 
            users.first_name, 
            users.last_name, 
            users.email, 
            users.registration_date, 
            users.role_id, 
            roles.role_name
		FROM users
			RIGHT JOIN roles on users.role_id = roles.role_id
    WHERE users.login like CONCAT('%',search,'%')
		order by users.registration_date
			LIMIT number_of_first_user, count_users_on_page;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_book` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_book`(in book_id smallint, in lang nvarchar(2))
BEGIN
	DECLARE def_lang nvarchar(2) DEFAULT 'en';
    
	IF (lang = def_lang) THEN
		SELECT  books.book_id,
				def_name AS name, 
				def_description AS description, 
				publish_year, 
				price, 
				pages, 
				books.publishing_house_id,
                publishing_houses.name as publishing_house_name,
                books.def_authors as authors,
				def_pdf_file_url as pdf_file_url, 
				cover_url
			FROM books
				left join publishing_houses on publishing_houses.publishing_house_id = books.publishing_house_id
		WHERE books.book_id = book_id;
    ELSE
		SELECT  books.book_id,
				tbooks.name, 
				description, 
				publish_year, 
				price, 
				pages, 
				books.publishing_house_id,
                publishing_houses.name as publishing_house_name,
                tbooks.authors,
				tbooks.pdf_file_url, 
				cover_url
			FROM books
				RIGHT JOIN tbooks on books.book_id = tbooks.book_id
				left join publishing_houses on publishing_houses.publishing_house_id = books.publishing_house_id
		WHERE tbooks.lang = lang AND books.book_id = book_id;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_bookmark` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_bookmark`( in user_id smallint,
															in book_id smallint, 
															in lang nvarchar(2))
BEGIN
	select bookmarks.page_number
		from bookmarks
    where   bookmarks.user_id = user_id
		AND bookmarks.book_id = book_id
		AND bookmarks.lang = lang;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_book_genres` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_book_genres`(  in book_id smallint,
																in lang varchar(2))
BEGIN
	select  genres.genre_id,
            coalesce(tgenres.genre_name, genres.def_genre_name) AS `genre_name`
		from genres
            LEFT JOIN tgenres ON (genres.genre_id = tgenres.genre_id AND tgenres.lang = lang)
	where genres.genre_id in (

		select books_has_genres.genre_id 
			from books_has_genres
		where books_has_genres.book_id = book_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_list_of_bookmarks` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_list_of_bookmarks`(	in user_id smallint,
																		in lang nvarchar(2),
																		in count_bookmarks_on_page smallint,
																		in number_of_page smallint)
BEGIN
	DECLARE number_of_first_bookmark int;
    
    set number_of_first_bookmark = (number_of_page-1)*count_bookmarks_on_page;

	select bookmarks.book_id, bookmarks.page_number
		from bookmarks
    where 	bookmarks.user_id = user_id
		AND bookmarks.lang = lang
			order by bookmarks.last_update desc
				limit number_of_first_bookmark, count_bookmarks_on_page;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_list_of_books` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_list_of_books`(	IN lang nvarchar(2),
																	in count_books_on_page smallint,
																	in number_of_page smallint)
BEGIN
	DECLARE def_lang nvarchar(2) DEFAULT 'en';    
    DECLARE number_of_first_books int;
    set number_of_first_books = (number_of_page-1)*count_books_on_page;
    
	IF (lang = def_lang) THEN
		SELECT  books.book_id,
				def_name AS name, 				 
				publish_year, 
				price, 
				cover_url
			FROM books
				LIMIT number_of_first_books, count_books_on_page;
    ELSE
		SELECT  books.book_id,
				name, 
				publish_year, 
				price, 
				cover_url
			FROM books
				RIGHT JOIN tbooks on books.book_id = tbooks.book_id
		WHERE tbooks.lang = lang
			LIMIT number_of_first_books, count_books_on_page;
	END IF;    	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_list_of_genres` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_list_of_genres`(IN lang nvarchar(2))
BEGIN	
    select  genres.genre_id,
            coalesce(tgenres.genre_name, genres.def_genre_name) AS `genre_name`
		from genres
            LEFT JOIN tgenres ON (  genres.genre_id = tgenres.genre_id
								AND tgenres.lang = lang);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_list_of_news` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_list_of_news`(IN lang nvarchar(2), in count_news_on_page smallint, in number_of_page smallint)
BEGIN
	DECLARE def_lang nvarchar(2) DEFAULT 'en';
    DECLARE number_of_first_news int;
    
    set number_of_first_news = (number_of_page-1)*count_news_on_page;
    
	IF (lang = def_lang) THEN
		(SELECT  news.news_id,
				news.def_title as title, 				 
				news.publish_date, 
				news.thumbs_url,
                users.first_name,
                users.last_name
			FROM news
				LEFT JOIN users on news.user_id = users.user_id)
        ORDER BY publish_date DESC
			LIMIT number_of_first_news, count_news_on_page;
    ELSE
		(SELECT  news.news_id,
				tnews.title, 				 
				news.publish_date, 
				news.thumbs_url,
                users.first_name,
                users.last_name
			FROM news
				LEFT JOIN users on news.user_id = users.user_id
				RIGHT JOIN tnews on news.news_id = tnews.news_id
		WHERE tnews.lang = lang)
			ORDER BY publish_date DESC
				LIMIT number_of_first_news, count_news_on_page;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_list_of_roles` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_list_of_roles`()
BEGIN
	SELECT  roles.role_id, 
            roles.role_name
		FROM roles;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_list_of_users` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_list_of_users`( in count_users_on_page smallint,
																 in number_of_page smallint)
BEGIN
	DECLARE number_of_first_user int;
    set number_of_first_user = (number_of_page-1)*count_users_on_page;

	SELECT  users.user_id,
			users.login, 
            users.first_name, 
            users.last_name, 
            users.email, 
            users.registration_date, 
            users.role_id, 
            roles.role_name
		FROM users
			RIGHT JOIN roles on users.role_id = roles.role_id
		ORDER BY users.registration_date
			LIMIT number_of_first_user, count_users_on_page;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_news` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_news`( in news_id smallint,
														in lang nvarchar(2))
BEGIN
	DECLARE def_lang nvarchar(2) DEFAULT 'en';
    
	IF (lang = def_lang) THEN
		SELECT  news.news_id,
				news.def_title as title, 
                news.def_text as text,
				news.publish_date, 
				news.photo_url,
                news.thumbs_url,
                users.first_name,
                users.last_name
			FROM news
				LEFT JOIN users on news.user_id = users.user_id
        WHERE news.news_id=news_id;
    ELSE
		SELECT  news.news_id,
				tnews.title, 
                tnews.text,
				news.publish_date, 
				news.photo_url,
                news.thumbs_url,
                users.first_name,
                users.last_name
			FROM news
				LEFT JOIN users on news.user_id = users.user_id
				RIGHT JOIN tnews on news.news_id = tnews.news_id
		WHERE tnews.lang = lang AND news.news_id=news_id;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_url_to_text_of_book` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_url_to_text_of_book`(  in book_id smallint,
																		in lang nvarchar(2))
BEGIN
	SELECT COALESCE(tbooks.text_file_url, books.def_text_file_url) as text_file_url
		FROM books	
			LEFT JOIN tbooks ON (  books.book_id = tbooks.book_id
							   AND tbooks.lang = lang) 
	WHERE books.book_id = book_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_user_by_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_user_by_id`(in user_id smallint)
BEGIN
	SELECT  users.user_id,
			users.login, 
            users.first_name, 
            users.last_name, 
            users.email, 
            users.registration_date, 
            users.role_id, 
            roles.role_name 
		FROM users
			RIGHT JOIN roles on users.role_id = roles.role_id
    where users.user_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_user_by_login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_user_by_login`(in login nvarchar(20))
BEGIN
	SELECT  users.user_id,
			users.login, 
            users.first_name, 
            users.last_name, 
            users.email, 
            users.registration_date, 
            users.role_id, 
            roles.role_name
		FROM users
			RIGHT JOIN roles on users.role_id = roles.role_id
    where users.login = login;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `is_administrator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `is_administrator`(		in login NVARCHAR(20),

																	out result boolean)
BEGIN
	DECLARE role_name nvarchar(15) DEFAULT 'Administrator';
    
	if (exists (
		SELECT user_id
			FROM users
		WHERE   users.login=login
			AND users.role_id = (
					SELECT roles.role_id
						from roles
					where roles.role_name=role_name)))
                
	THEN
		set result = true;
    else
		set result = false;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `is_exist_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `is_exist_user`( in login NVARCHAR(20),
															 in password NVARCHAR(100),
                                                             
															 out result boolean)
BEGIN
	if (exists (
			SELECT user_id
				FROM users
			WHERE   users.login=login
				AND users.password=password))
                
	THEN
		set result = true;
    else
		set result = false;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `is_free_login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `is_free_login`( in login NVARCHAR(20),

															 out result boolean)
BEGIN
	if (exists (
			SELECT user_id
				FROM users
			WHERE users.login=login))
	THEN
		set result = false;
    else
		set result = true;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `is_moderator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `is_moderator`( in login NVARCHAR(20),

															out result boolean)
BEGIN
	DECLARE role_name nvarchar(15) DEFAULT 'Moderator';
    
	if (exists (
			SELECT user_id
				FROM users
			WHERE 	users.login=login
				AND users.role_id = (
					SELECT roles.role_id
						from roles
					where roles.role_name=role_name)))
	THEN
		set result = true;
    ELSE
		call is_administrator(login, result);
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `set_bookmark` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_bookmark`( in user_id smallint,
															in book_id smallint,
															in lang nvarchar(2),
															in page_number smallint)
BEGIN
	if (exists (
			SELECT user_id
				FROM bookmarks
			WHERE   bookmarks.user_id = user_id
				AND bookmarks.book_id = book_id
                AND bookmarks.lang = lang))
	THEN
		UPDATE bookmarks
			SET bookmarks.page_number = page_number,
				bookmarks.last_update = NOW()
        WHERE   bookmarks.user_id = user_id
			AND bookmarks.book_id = book_id
			AND bookmarks.lang = lang;
    ELSE
		INSERT INTO `bookmarks` (   `user_id`,
									`book_id`,
									`page_number`,
									`lang`,
									`last_update`)
			VALUES (	user_id,
						book_id,
						page_number,
						lang,
						NOW());
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_book` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_book`(  in book_id smallint,
															in name nvarchar(100),
															in description TEXT,
															in publish_year smallint,
															in authors nvarchar(100),
															in price decimal(5,2),
															in pages smallint,
															in publishing_house_name nvarchar(50),
															in pdf_file_url nvarchar(75),
															in cover_url nvarchar(75),
															in text_file_url nvarchar(75))
BEGIN
	DECLARE publishing_house_id smallint;

	if (!exists (
		SELECT publishing_houses.publishing_house_id
			FROM publishing_houses
		WHERE publishing_houses.name = publishing_house_name))
	THEN
		INSERT INTO publishing_houses (`name`)
			VALUES (publishing_house_name);
	END IF;
	
    set publishing_house_id = (
			SELECT publishing_houses.publishing_house_id
				FROM publishing_houses
            WHERE publishing_houses.name = publishing_house_name);
    
    UPDATE books 
		SET books.def_name=name,
			books.def_description=description,
			books.publish_year=publish_year, 
			books.price=price,
			books.pages=pages,
			books.publishing_house_id=publishing_house_id, 
			books.def_pdf_file_url=pdf_file_url,
			books.cover_url=cover_url,
			books.def_text_file_url=text_file_url,
			books.def_authors=authors
	WHERE books.book_id = book_id;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_news` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_news`(  in news_id smallint,
															in title nvarchar(100),
															in `text` TEXT,
															in photo_url nvarchar(75),
															in thumbs_url nvarchar(75),
															in user_id smallint)
BEGIN
    UPDATE news
		SET news.def_title = title,
			news.def_text = text,
            news.user_id=user_id,
            news.photo_url = photo_url,
            news.thumbs_url = thumbs_url, 
			news.publish_date = NOW() 
	WHERE news.news_id = news_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_translated_book` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_translated_book`(	in book_id smallint, 
																		in lang nvarchar(2),
																		in name nvarchar(100),
																		in authors nvarchar(100),
																		in description TEXT,
																		in pdf_file_url nvarchar(75), 
																		in text_file_url nvarchar(75))
BEGIN
	UPDATE tbooks
		SET tbooks.name=name,
        tbooks.description=description,
        tbooks.pdf_file_url=pdf_file_url,
        tbooks.text_file_url=text_file_url,
        tbooks.authors=authors
    WHERE   tbooks.book_id = book_id
		AND tbooks.lang = lang;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_translated_news` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_translated_news`(	in news_id smallint,
																		in title nvarchar(100),
																		in `text` TEXT,
																		in lang nvarchar(2))
BEGIN
    UPDATE tnews 
		SET tnews.title = title,
            tnews.text = text
    WHERE   tnews.news_id = news_id
		AND tnews.lang = lang;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_user`(	in login varchar(20),
															in password varchar(100),
															in first_name varchar(20),
															in last_name varchar(20),
															in email varchar(50))
BEGIN	
	UPDATE users 
		SET users.`password`=`password`,
			users.first_name = first_name,
            users.last_name = last_name,
            users.email = email
	WHERE users.login = login;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-02 19:37:05
