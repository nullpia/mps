/* 블로그 분류 */
DROP TABLE IF EXISTS TB_BLOG_CATG;
CREATE TABLE TB_BLOG_CATG (
	CATG_ID 	INT 			NOT NULL COMMENT '카테고리ID',
	CATG_NM 	VARCHAR(200) 		NULL DEFAULT NULL COMMENT '카테고리명',
	CRE_USR_ID 	VARCHAR(30) 		NULL DEFAULT 'ADMIN' COMMENT '생성자',
	CRE_DTTM 	DATETIME 		NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	MOD_USR_ID 	VARCHAR(30) 		NULL DEFAULT 'ADMIN' COMMENT '수정자',
	MOD_DTTM 	DATETIME 		NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
	PRIMARY KEY (CATG_ID)
)
COMMENT='블로그>카테고리'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/* 블로그 POST */
DROP TABLE IF EXISTS TB_BLOG_POST;
CREATE TABLE TB_BLOG_POST (
	POST_ID 	INT 			NOT NULL COMMENT '포스트ID',
	CATG_ID 	INT 				NULL COMMENT '카테고리ID',
	POST_TITL 	VARCHAR(200) 	NOT NULL COMMENT '포스트제목',
	POST_CN 	LONGTEXT 		NOT NULL COMMENT '포스트내용',
	READ_CNT 	INT 			NOT NULL COMMENT '조회수',
	CRE_USR_ID 	VARCHAR(30) 		NULL DEFAULT 'ADMIN' COMMENT '생성자',
	CRE_DTTM 	DATETIME 		NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	MOD_USR_ID 	VARCHAR(30) 		NULL DEFAULT 'ADMIN' COMMENT '수정자',
	MOD_DTTM 	DATETIME 		NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
	PRIMARY KEY (POST_ID)
)
COMMENT='블로그>POST'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/* 시퀀스 테이블 */
DROP TABLE IF EXISTS TB_COMM_SQNO;
CREATE TABLE TB_COMM_SQNO (
	SQNO_NM 	VARCHAR(60) 	NOT NULL COMMENT '시퀀스명',
	SQNO_VL 	BIGINT 			NOT NULL DEFAULT 1 	COMMENT '시퀀스값',
	MOD_DTTM 	DATETIME 			NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
	PRIMARY KEY (SQNO_NM)
)
COMMENT='공통>시퀀스'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/* SEQUENCE 를 가져오는 Function */
DROP FUNCTION IF EXISTS FN_GET_NEW_SQNO;

DELIMITER $$
CREATE FUNCTION FN_GET_NEW_SQNO(p_sqno_nm VARCHAR(60))
	RETURNS BIGINT READS SQL DATA
BEGIN
	DECLARE RESULT_ID BIGINT;

	UPDATE TB_COMM_SQNO SET SQNO_VL = LAST_INSERT_ID(SQNO_VL+1) WHERE SQNO_NM = p_sqno_nm;

	SET RESULT_ID =	(SELECT LAST_INSERT_ID());

	RETURN RESULT_ID;
END $$
DELIMITER ;

/* 메뉴 */
DROP TABLE IF EXISTS TB_COMM_MENU;
CREATE TABLE TB_COMM_MENU (
	MENU_ID 	VARCHAR(8)		NOT NULL COMMENT '메뉴ID',
	MENU_PID 	VARCHAR(8)		NOT NULL COMMENT '메뉴PID',
	QUICK_ID 	VARCHAR(60) 		NULL COMMENT 'QUICK ID',
	MENU_NM  	VARCHAR(100) 	NOT NULL COMMENT '메뉴명',
	LVL_NO      INT          	NOT NULL DEFAULT 0	COMMENT '레벨번호',
	SRT_NO		INT          	NOT NULL DEFAULT 0 	COMMENT '정렬번호',
	CRE_USR_ID 	VARCHAR(30) 		NULL DEFAULT 'ADMIN' COMMENT '생성자',
	CRE_DTTM 	DATETIME 		NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	MOD_USR_ID 	VARCHAR(30) 		NULL DEFAULT 'ADMIN' COMMENT '수정자',
	MOD_DTTM 	DATETIME 		NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
	PRIMARY KEY (MENU_ID)
)
COMMENT='공통>메뉴'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/* 사용자 */
DROP TABLE IF EXISTS TB_COMM_USER;
CREATE TABLE TB_COMM_USER (
	USER_ID		VARCHAR(30)		NOT NULL COMMENT '사용자ID',
	LGON_PWD	VARCHAR(300)	NOT NULL COMMENT '로그인비밀번호',
	USER_NM  	VARCHAR(100) 	NOT NULL COMMENT '사용자명',
	EMAL		VARCHAR(320) 		NULL COMMENT '이메일',
	LGON_DTTM	DATETIME 			NULL COMMENT '로그인일시',
	LGOF_DTTM	DATETIME 			NULL COMMENT '로그아웃일시',
	CRE_USR_ID 	VARCHAR(30) 		NULL DEFAULT 'ADMIN' COMMENT '생성자',
	CRE_DTTM 	DATETIME 		NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	MOD_USR_ID 	VARCHAR(30) 		NULL DEFAULT 'ADMIN' COMMENT '수정자',
	MOD_DTTM 	DATETIME 		NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
	PRIMARY KEY (USER_ID)
)
COMMENT='공통>사용자'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

