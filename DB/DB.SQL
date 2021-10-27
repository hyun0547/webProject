# DB 생성
DROP DATABASE IF EXISTS sbs_s_2021_10;
CREATE DATABASE sbs_s_2021_10_t;
USE sbs_s_2021_10_t;

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시물 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';

SELECT * 
FROM article;

CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId VARCHAR(20) NOT NULL,
    loginPw VARCHAR(60) NOT NULL,
    `authLevel` SMALLINT(2) UNSIGNED NOT NULL DEFAULT 3 COMMENT '권한레벨(3=일반,7=관리자)',
    `name` VARCHAR(20) NOT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    cellphoneNo VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전,1=탈퇴)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

INSERT INTO `sbs_s_2021_10`.`member` 
	(
	`regDate`, 
	`updateDate`, 
	`loginId`, 
	`loginPw`, 
	`name`, 
	`nickname`, 
	`cellphoneNo`, 
	`email`
	)
	VALUES
	(
	NOW(), 
	NOW(), 
	'testUser1', 
	'1234',  
	'testUser1', 
	'testUser1', 
	'01012345678', 
	'testUser1@daum.net'
	);


INSERT INTO `sbs_s_2021_10`.`member` 
	(
	`regDate`, 
	`updateDate`, 
	`loginId`, 
	`loginPw`, 
	`name`, 
	`nickname`, 
	`cellphoneNo`, 
	`email`
	)
	VALUES
	(
	NOW(), 
	NOW(), 
	'testUser2', 
	'1234',  
	'testUser2', 
	'testUser2', 
	'01012345679', 
	'testUser2@daum.net'
	);