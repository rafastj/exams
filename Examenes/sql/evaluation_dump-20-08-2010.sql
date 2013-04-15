/*
SQLyog Community Edition- MySQL GUI v8.14 
MySQL - 5.1.45-community : Database - evaluation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`evaluation` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `evaluation`;

/*Table structure for table `applicant` */

DROP TABLE IF EXISTS `applicant`;

CREATE TABLE `applicant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `birth_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `profile_id` int(11) NOT NULL,
  `seniority_id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `test_taked` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_applicant_seniority` (`seniority_id`),
  KEY `FK_applicant_profile` (`profile_id`),
  CONSTRAINT `FK_applicant_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`),
  CONSTRAINT `FK_applicant_seniority` FOREIGN KEY (`seniority_id`) REFERENCES `seniority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=latin1;

/*Data for the table `applicant` */

/*Table structure for table `option` */

DROP TABLE IF EXISTS `option`;

CREATE TABLE `option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `description` text CHARACTER SET latin1 NOT NULL,
  `correct` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_option` (`question_id`),
  CONSTRAINT `FK_option` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `option` */

insert  into `option`(`id`,`question_id`,`description`,`correct`) values (169,65,'Sometimes',0),(170,65,'Depends on the Server',0),(171,65,'Yes',1),(172,65,'No',0),(174,67,'They are exactly the same thing; its use depends on how big is the project',0),(175,67,'A servlet container is specifically designed to manage servlets only, while an application server manages many different types of software components including EJBs, JSPs, message publishers and subscribers, etc. \r\nA servlet container can be considered part of an application server, since J2EE application servers provide a servlet container within their complete infrastructure. ',1),(176,67,'Are completely different since a Servlet container  only shows static content and a EJB container can show dynamic content',0),(177,68,'<jsp:getProperty name=\"people\" property=\"friend\"/>',1),(178,68,'<jsp:getProperty name=\"friend\" property=\"people\"/>',0),(179,68,'<jsp:getProperty property=\"people.friend\"/>',0),(180,68,'<jsp:getProperty name=\"friend\" />',0),(181,69,'The controller',0),(182,69,'The view',0),(183,69,'The model',1),(184,70,'Necessarily be an abstract class.',1),(185,70,'Should have the method public abstract void someMethod();',0),(186,70,'Should have the method public void someMethod() which has to throw an exception which is a subclass of java.lang.Exception.',0),(187,70,' Should have the method public void someMethod() which need not throw an Exception.',0),(188,71,'Wallace-1 Wallace-2 Gromit-1 ',0),(189,71,'Wallace-1 Gromit-2 Wallace-2 Gromit-1 ',0),(190,71,'Wallace-1 Gromit-1 Gromit-2 Wallace-2 ',1),(191,71,'Gromit-1 Gromit-2 ',0),(192,71,'Gromit-2 Wallace-1 Gromit-1 Wallace-2 ',0),(193,71,'The code does not compile.',0),(194,71,'An error occurs at run time.',0),(197,73,'public class MyRunnable extends Runnable{public void run(){}} ',0),(198,73,'public class MyRunnable extends Object{public void run(){}}',0),(199,73,'public class MyRunnable implements Runnable{public void run(){}} ',1),(200,73,'public class MyRunnable implements Runnable{void run(){}} ',0),(201,73,'public class MyRunnable implements Runnable{public void start(){}} ',0),(202,74,'das abcdef abcdef',1),(203,74,'das das abcdef abcdef',0),(204,74,'das abcdef',0),(205,74,'abcdef abcdef',0),(206,75,'Prints out: Exception Finally',0),(207,75,'Prints out: Finally',1),(208,75,'Prints out: Exception',0),(209,75,'Compile with error',0),(210,76,'Methods cannot be overriden to be more private',1),(211,76,'static methods cannot be overloaded',0),(212,76,' private methods cannot be overloaded',0),(213,76,'An overloaded method cannot throw exceptions not checked in the base class',0),(214,77,'Compile and run without error ',0),(215,77,'Compile time Exception',0),(216,77,'Runtime Exception',1),(217,78,'Acts as the mapping servlet',0),(218,78,'Acts as the controller servlet',1),(219,78,'Acts as a ActionBean Factory servlet',0),(220,79,'Logic tag Library',1),(221,79,'HTML tag Library',0),(222,79,'Bean tag Library',0),(223,79,'Xml tag Library',0),(224,80,'Servlet, HTML and Java',0),(225,80,'Servlet, JSP, XML and Java',1),(226,80,'Applet, XML and Java',0),(227,80,'d)	Servlet, JSP and Java',0),(228,81,'ActionServlet',1),(229,81,'Action class',0),(230,81,'Deployment descriptor',0),(231,81,'None of the above',0),(232,82,'4',0),(233,82,'2',0),(234,82,'1',1),(235,82,'Uncountable instances',0),(236,83,'2',0),(237,83,'1',1),(238,83,'3',0),(239,83,'Unlimited',0),(240,84,'RequestAction,SwitchAction,LookupRequestAction,ConfigAction,ContextAction',0),(241,84,'LookupDispatchAction,SwitchAction,RequestAction,ResponseAction',0),(242,84,'ForwardAction,DispatchAction,IncludeAction,LookupDispatchAction,SwitchAction',1),(243,84,'ForwardAction,SwitchAction,ResponseAction, ContextAction, ConfigAction',0),(244,85,'struts-action-config.xml',0),(245,85,'struts-processor-conifg.xml',0),(246,85,'struts-config.xml',1),(247,86,'< html:page-form action=\"/some-action.do\">',1),(248,86,'< jsp:webform action=\"/some-action.do\">',0),(249,86,'< html:web-form action=\"/some-action.do\">',0),(250,86,'< html:form action=\"/some-action.do\">',0),(251,87,'Copies a directory',0),(252,87,'Changes directory',1),(253,87,'Creates a directory',0),(254,88,'Change the user password',0),(255,88,'Shows the current path',1),(256,88,'Opens the default editor',0),(257,89,'set',1),(258,89,'show',0),(259,89,'showv',0),(260,90,'ls –lt',0),(261,90,'ls –a',1),(262,90,'ls –h',0),(263,91,'Removes a directory',0),(264,91,'Remove the directory that starts with r',0),(265,91,'Remove dir_name and all it’s content.',1),(266,92,'Open a file',1),(267,92,'Creates a tar file',0),(268,92,'Shows the file permissions',0),(269,93,'vi command',0),(270,93,'man command',1),(271,93,'grep command',0),(272,94,'Find',0),(273,94,'Follow',1),(274,94,'Fix',0),(275,95,'clean word text into file.txt',0),(276,95,'count the word text into file.txt',1),(277,95,'copy the text  word into file.txt',0),(278,96,'Ignore case',1),(279,96,'Include',0),(280,96,'Import',0),(281,97,'Prints the number of newlines, words, and bytes in files',1),(282,97,'Open word documents',0),(283,97,'Commits into a SVN repository.',0),(284,98,'pp',0),(285,98,'ps',1),(286,98,'pg',0),(287,99,'redirect standard output to a file',0),(288,99,'append standard output to a file',1),(289,99,'redirect standard input from a file',0),(290,100,'Write',0),(291,100,'Read',0),(292,100,'Execute',1),(293,101,'who',0),(294,101,'chmod',1),(295,101,'fg',0),(296,102,'Outputs the number of kilobyes used by each subdirectory',0),(297,102,'Compare files and displays the differences',1),(298,102,'Outputs the number of Megabyes used by each subdirectory',0),(299,103,'netstat',0),(300,103,'rwho',0),(301,103,'chown',1),(302,104,':q',0),(303,104,':wq',1),(304,104,':!q',0),(305,105,'chown',0),(306,105,'sudo',1),(307,105,'bash',0),(308,106,'Shows the first four lines of the file',0),(309,106,'Shows the context between the word',1),(310,106,'Redirect the last four lines to stdout',0),(311,107,'{p=color:red}',1),(312,107,'{p:color=red}',0),(313,107,'p{color=red}',0),(314,107,'p{color:red}',0),(315,108,'To send a text full stop.',0),(316,108,'Indicates a comment.',0),(317,108,'Create an horizontal line.',1),(318,108,'Aligns the image on the right.',0),(319,109,'margin:2 px 3 px 4 px 1 px',1),(320,109,'margin:1 px 2 px 3 px 4 px',0),(321,109,'margin:1 px 3 px 2 px 4 px',0),(322,109,'margin:4 px 3 px 2 px 1 px',0);

/*Table structure for table `option_chosed` */

DROP TABLE IF EXISTS `option_chosed`;

CREATE TABLE `option_chosed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `option_id` int(11) NOT NULL,
  `question_answered_id` int(11) NOT NULL,
  `checked` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_option_chosed_op` (`option_id`),
  KEY `FK_option_chosed_qa` (`question_answered_id`),
  CONSTRAINT `FK_option_chosed_op` FOREIGN KEY (`option_id`) REFERENCES `option` (`id`),
  CONSTRAINT `FK_option_chosed_qa` FOREIGN KEY (`question_answered_id`) REFERENCES `question_answered` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=latin1;

/*Data for the table `option_chosed` */

/*Table structure for table `profile` */

DROP TABLE IF EXISTS `profile`;

CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `profile` */

insert  into `profile`(`id`,`description`) values (1,'JAVA'),(2,'QA'),(3,'DESIGNER'),(4,'Web Developer');

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text CHARACTER SET latin1 NOT NULL,
  `technology_id` int(11) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `seniority_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_question_prof` (`technology_id`),
  KEY `FK_question_seniority` (`seniority_id`),
  KEY `FK_question_profile` (`profile_id`),
  CONSTRAINT `FK_question_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`),
  CONSTRAINT `FK_question_seniority` FOREIGN KEY (`seniority_id`) REFERENCES `seniority` (`id`),
  CONSTRAINT `FK_question_techno` FOREIGN KEY (`technology_id`) REFERENCES `technology` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `question` */

insert  into `question`(`id`,`description`,`technology_id`,`profile_id`,`seniority_id`) values (65,'A JSP is a servlet',1,1,1),(67,'The difference between a Servlet container and a EJB container is ',1,1,2),(68,'Your JSP Bean \"people\" has a private member \"friend\" with proper setters and getters, you would have which line in your jsp page to obtain its value',1,1,2),(69,'If we are using MVC our POJO would be',1,1,1),(70,'Read the following code below\r\n\r\npublic interface AQuestion{\r\n	public abstract void someMethod() throws Exception;\r\n}\r\nA Class implementing this interface should',1,1,2),(71,'Given:  \r\n\r\n public class Messager implements Runnable {\r\n    public static void main(String[] args) {\r\n        new Thread(new Messager(\"Wallace\")).start() ;\r\n        new Thread(new Messager(\"Gromit\")).start();\r\n    }\r\n    private String  name;\r\n    public Messager(String name) { this.name = name; }\r\n    public void run() {\r\n        message(1);\r\n        message(2);\r\n    }\r\n    private synchronized void message(int n) {\r\n         System.out.print(name + \"-\" + n + \" \");\r\n    }\r\n}\r\nWhich of the following is a possible result? (Choose all that apply.)\r\n',1,1,3),(73,'The following block of code creates a Thread using a Runnable target: \r\n\r\nRunnable target = new MyRunnable();\r\nThread myThread = new Thread(target);\r\n\r\nWhich of the following classes can be used to create the target, so that the preceding code compiles correctly?\r\n',1,1,2),(74,'What is the output for the below code ? \r\n\r\npublic class NameBean {\r\n	private String str;\r\n	\r\n	NameBean(String str ){\r\n		this.str = str;\r\n	}\r\n	\r\n	public String toString() {\r\n		return str;\r\n	}\r\n}								\r\n								\r\nimport java.util.HashSet;\r\n\r\npublic class CollClient {\r\n	\r\n	public static void main(String ... sss) {\r\n		HashSet myMap = new HashSet();\r\n		String s1 = new String(\"das\");\r\n		String s2 = new String(\"das\");\r\n	NameBean s3 = new NameBean(\"abcdef\");\r\n	NameBean s4 = new NameBean(\"abcdef\");\r\n		\r\n		myMap.add(s1);\r\n		myMap.add(s2);\r\n		myMap.add(s3);\r\n		myMap.add(s4);\r\n		\r\n		System.out.println(myMap);\r\n	}	\r\n}\r\n',1,1,1),(75,'What is the result of executing the following code, using the parameters 0 and 3 ? \r\n\r\npublic void divide(int a, int b) {\r\n	try {\r\n		int c = a / b;\r\n	} catch (Exception e) {\r\n		System.out.print(\"Exception \");\r\n	} finally {\r\n		System.out.println(\"Finally\");\r\n	}\r\n}\r\n',1,1,1),(76,'Which of the following statements are true?',1,1,2),(77,'What will happen if you attempt to compile and run the following code?  \r\n\r\nclass Base {}\r\nclass Sub extends Base {}\r\nclass Sub2 extends Base {}\r\npublic class CEx{\r\n    public static void main(String argv[]){\r\n	Base b=new Base();\r\n	Sub s=(Sub) b;\r\n    }\r\n}\r\n',1,1,2),(78,'What is the use of the ActionServlet in Struts ?',1,1,3),(79,'Which of the following tag libraries is not provided by struts ',1,1,2),(80,'Struts framework is based on',1,1,2),(81,'Which of the following delegates the request handling to the RequestProcessor instance?',1,1,2),(82,'How many instances can be created per application module by the RequestProcessor class? ',1,1,3),(83,'What is the limitation of creating ActionServlet instances per web application? ',1,1,3),(84,'Which of the following are the struts pre-built Action classes? ',1,1,2),(85,'The dispatch from the controller to the Action class is based on a configuration that is provided by a',1,1,1),(86,'Which of the following tag is a valid form submission in Struts framework? [ some-action.do is a sample] ',1,1,2),(87,'cd',2,1,1),(88,'Pwd',2,1,1),(89,'show variables',2,1,1),(90,'List hidden files',2,1,1),(91,'rm –r dir_name',2,1,1),(92,'cat file',2,1,1),(93,'How do you show a command help',2,1,1),(94,'What’s the –f option of tail command for?',2,1,1),(95,'grep –c text file.txt',2,1,1),(96,'What’s the –i option in grep command for?',2,1,1),(97,'The wc command is for',2,1,1),(98,'Which command prints the process',2,1,1),(99,'What does the “>” symbol mean in this line: command > file',2,1,1),(100,'What means the “x” permission',2,1,1),(101,'Which command should be used to change file permissions',2,1,1),(102,'What’s the diff command for',2,1,1),(103,'What command allows to change a file owner',2,1,1),(104,'How do I quit vim saving changes',2,1,1),(105,'Which commands is used to execute a command like root',2,1,1),(106,'what does it do: grep –C4 word file.txt',2,1,1),(107,'What is the correct syntax in CSS?',3,4,1),(108,'What is the tag <HR>?',15,4,1),(109,'How do you define these margins?\r\n\r\nThe left margin: 1 px \r\nThe right margin: 3 px \r\nThe upper margin: 2 px \r\nThe bottom margin: 4 px',3,4,1);

/*Table structure for table `question_answered` */

DROP TABLE IF EXISTS `question_answered`;

CREATE TABLE `question_answered` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `correct` tinyint(1) NOT NULL,
  `test_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_question_answered` (`question_id`),
  KEY `FK_question_answered_test` (`test_id`),
  CONSTRAINT `FK_question_answered` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK_question_answered_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

/*Data for the table `question_answered` */

/*Table structure for table `questiontest` */

DROP TABLE IF EXISTS `questiontest`;

CREATE TABLE `questiontest` (
  `question_id` int(11) DEFAULT NULL,
  `test_id` int(11) DEFAULT NULL,
  KEY `FK_questiontest` (`test_id`),
  KEY `FK_questiontest_id` (`question_id`),
  CONSTRAINT `FK_questiontest` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`),
  CONSTRAINT `FK_questiontest_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `questiontest` */

/*Table structure for table `seniority` */

DROP TABLE IF EXISTS `seniority`;

CREATE TABLE `seniority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `seniority` */

insert  into `seniority`(`id`,`description`) values (1,'JUNIOR'),(2,'SEMI_SR'),(3,'SENIOR');

/*Table structure for table `tech_score` */

DROP TABLE IF EXISTS `tech_score`;

CREATE TABLE `tech_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_id` int(11) NOT NULL,
  `technology_id` int(11) NOT NULL,
  `score` double NOT NULL,
  `questions_correct` int(11) NOT NULL,
  `questions` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tech_score` (`technology_id`),
  KEY `FK_tech_score_test` (`test_id`),
  CONSTRAINT `FK_tech_score` FOREIGN KEY (`technology_id`) REFERENCES `technology` (`id`),
  CONSTRAINT `FK_tech_score_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;

/*Data for the table `tech_score` */

/*Table structure for table `technology` */

DROP TABLE IF EXISTS `technology`;

CREATE TABLE `technology` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `technology` */

insert  into `technology`(`id`,`description`) values (1,'Java'),(2,'Unix'),(3,'Css'),(4,'JavaScript'),(5,'Oracle'),(15,'HTML'),(16,'java server pages');

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applicant_id` int(11) NOT NULL,
  `score` double DEFAULT NULL,
  `test_model_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_test` (`applicant_id`),
  KEY `FK_test_testmod` (`test_model_id`),
  CONSTRAINT `FK_test` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`id`),
  CONSTRAINT `FK_test_testmod` FOREIGN KEY (`test_model_id`) REFERENCES `test_model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

/*Data for the table `test` */

/*Table structure for table `test_model` */

DROP TABLE IF EXISTS `test_model`;

CREATE TABLE `test_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seniority_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_test_model_srt` (`seniority_id`),
  KEY `FK_test_model_prof` (`profile_id`),
  CONSTRAINT `FK_test_model_prof` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`),
  CONSTRAINT `FK_test_model_srt` FOREIGN KEY (`seniority_id`) REFERENCES `seniority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `test_model` */

insert  into `test_model`(`id`,`seniority_id`,`profile_id`,`active`) values (8,1,1,0),(9,1,1,0),(10,1,1,0),(11,1,1,0),(12,2,1,0),(13,3,1,0),(14,3,1,0),(15,3,1,0),(16,3,1,0),(17,1,4,0),(18,1,1,0);

/*Table structure for table `test_model_questionqty` */

DROP TABLE IF EXISTS `test_model_questionqty`;

CREATE TABLE `test_model_questionqty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_model_id` int(11) NOT NULL,
  `technology_id` int(11) NOT NULL,
  `seniority_id` int(11) NOT NULL,
  `qty_questions` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_test_model_questionqty_tm` (`test_model_id`),
  KEY `FK_test_model_questionqty_tech` (`technology_id`),
  KEY `FK_test_model_questionqty` (`seniority_id`),
  CONSTRAINT `FK_test_model_questionqty` FOREIGN KEY (`seniority_id`) REFERENCES `seniority` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_test_model_questionqty_tech` FOREIGN KEY (`technology_id`) REFERENCES `technology` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_test_model_questionqty_tm` FOREIGN KEY (`test_model_id`) REFERENCES `test_model` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;

/*Data for the table `test_model_questionqty` */

insert  into `test_model_questionqty`(`id`,`test_model_id`,`technology_id`,`seniority_id`,`qty_questions`) values (18,8,1,1,11),(19,8,1,2,11),(20,8,1,3,11),(21,8,1,3,11),(22,9,1,1,1),(23,9,1,2,1),(24,9,1,3,1),(25,9,1,3,1),(26,10,1,1,44),(27,10,1,2,33),(28,10,1,3,33),(29,10,1,3,22),(30,11,1,1,1),(31,11,1,2,2),(32,11,1,3,2),(33,11,1,3,3),(34,12,1,1,2),(35,12,1,3,4),(36,12,1,2,3),(37,13,1,1,5),(38,13,1,3,7),(39,13,1,2,6),(40,14,1,1,1),(41,14,1,3,1),(42,14,1,2,1),(43,15,1,1,0),(44,15,2,1,99),(45,15,1,3,0),(46,15,1,2,0),(47,16,1,1,0),(48,16,1,3,0),(49,16,2,1,88),(50,16,1,2,0),(51,17,15,1,11),(52,17,3,1,11),(53,18,1,1,5),(54,18,1,3,1),(55,18,2,1,1),(56,18,1,2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
