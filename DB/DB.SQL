# DB 생성
DROP DATABASE IF EXISTS sbs_s_2021_10;
CREATE DATABASE sbs_s_2021_10;
USE sbs_s_2021_10;

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

# 게시물 작성자 추가
ALTER TABLE article ADD COLUMN memberLoginId VARCHAR(20) NOT NULL AFTER updatedate;

# 기존 게시글 작성자 testUser1번으로 설정
UPDATE article SET memberLoginId = 'testUser1' WHERE memberLoginId = '';
	
SELECT * FROM `member`;

SELECT * 
FROM article;

CREATE TABLE article_type (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    typeName VARCHAR(30) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '게시판 삭제 여부(0=유지,1=삭제)',
    delDate DATETIME COMMENT '삭제날짜'
);

INSERT INTO `sbs_s_2021_10`.`article_type` 
	(
	`regDate`, 
	`updateDate`, 
	`typeName`
	)
	VALUES
	(
	NOW(), 
	NOW(), 
	'스터디 로그'
	);
	
INSERT INTO `sbs_s_2021_10`.`article_type` 
	(
	`regDate`, 
	`updateDate`, 
	`typeName`
	)
	VALUES
	(
	NOW(), 
	NOW(), 
	'일기'
	);
	
INSERT INTO `sbs_s_2021_10`.`article_type` 
	(
	`regDate`, 
	`updateDate`, 
	`typeName`
	)
	VALUES
	(
	NOW(), 
	NOW(), 
	'메모'
	);

ALTER TABLE article ADD typeId INT(10) UNSIGNED NOT NULL;

UPDATE article SET typeId=1 WHERE typeId=0;

ALTER TABLE article ADD FOREIGN KEY(typeId) REFERENCES article_type(id);