CREATE TABLE IF NOT EXISTS classes (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,category varchar(255) COLLATE utf8_unicode_ci NOT NULL,description varchar(255) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS classes (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,code varchar(10) COLLATE utf8_unicode_ci NOT NULL,name varchar(255) COLLATE utf8_unicode_ci NOT NULL,categoryId int(10) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS courses (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,title varchar(150) COLLATE utf8_unicode_ci NOT NULL,description varchar(255) COLLATE utf8_unicode_ci NOT NULL,cu int(10) COLLATE utf8_unicode_ci NOT NULL,classId int(10) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS questions (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,question varchar(255) COLLATE utf8_unicode_ci NOT NULL,option1 varchar(255) COLLATE utf8_unicode_ci NOT NULL,option2 varchar(255) COLLATE utf8_unicode_ci NOT NULL,option3 varchar(255) COLLATE utf8_unicode_ci NOT NULL,option4 varchar(255) COLLATE utf8_unicode_ci NULL,option5 varchar(255) COLLATE utf8_unicode_ci NULL,marks int(10) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,courseId int(10) COLLATE utf8_unicode_ci NOT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS questions (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,classId int(10) COLLATE utf8_unicode_ci NOT NULL,email varchar(255) COLLATE utf8_unicode_ci NOT NULL,password varchar(255) COLLATE utf8_unicode_ci NOT NULL,phone varchar(25) COLLATE utf8_unicode_ci NOT NULL,address varchar(255) COLLATE utf8_unicode_ci NULL,user_type varchar(50) COLLATE utf8_unicode_ci NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS candidate_class (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,classId int(10) COLLATE utf8_unicode_ci NOT NULL,candidateId int(10) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS test (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,noOfQuestions int(10) COLLATE utf8_unicode_ci NOT NULL,candidateId int(10) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS score (id int(10) UNSIGNED NOT NULL AUTO_INCREMENT,courseId int(10) COLLATE utf8_unicode_ci NOT NULL,examId int(10) COLLATE utf8_unicode_ci NOT NULL,candidateId int(10) COLLATE utf8_unicode_ci NOT NULL,score int(10) COLLATE utf8_unicode_ci NOT NULL,created_at timestamp NULL DEFAULT NULL,updated_at timestamp NULL DEFAULT NULL, PRIMARY KEY (id)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;















