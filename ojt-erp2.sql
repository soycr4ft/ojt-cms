DROP TABLE IF EXISTS `skill_code`;

CREATE TABLE `skill_code` (
	`skill_code_id`	int	NOT NULL,
	`skill_name`	varchar	NOT NULL,
	`keywords`	varchar	NOT NULL	COMMENT '기술분야,키워드,기타',
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `training`;

CREATE TABLE `training` (
	`edu_id`	int	NOT NULL,
	`course`	varchar	NOT NULL,
	`name`	varchar	NOT NULL,
	`start_train`	date	NOT NULL,
	`end_train`	date	NOT NULL,
	`status`	varchar	NOT NULL	COMMENT '수료',
	`user_id`	int	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `user_skill`;

CREATE TABLE `user_skill` (
	`user_skill_id`	int	NOT NULL,
	`skill_code_id`	int	NOT NULL,
	`user_id`	int	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`user_id`	int	NOT NULL,
	`log_id`	varchar	NOT NULL,
	`password`	varchar	NOT NULL,
	`name`	varchar	NOT NULL,
	`gender`	tinyint	NOT NULL	COMMENT '남자, 여자',
	`birthday`	date	NOT NULL,
	`phone`	varchar	NOT NULL,
	`email`	varchar	NOT NULL,
	`approved`	tinyint	NOT NULL	DEFAULT 대기	COMMENT '대기, 승인, 거절',
	`log_info`	datetime	NULL,
	`ip_info`	varchar	NULL,
	`dept_id`	int	NOT NULL,
	`auth`	varchar	NOT NULL	COMMENT 'member, admin, master, 대기중',
	`is_deleted`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT '0 활성, 1 탈퇴',
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
	`edu_id`	int	NOT NULL,
	`is_exam`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT '0 이면 고졸, 1이면 검정고시',
	`school_name`	varchar	NULL,
	`level`	varchar	NULL	COMMENT '전문학사,학사,석사,학점은행제 학사',
	`major`	varchar	NULL,
	`ent_date`	date	NULL,
	`gradu_date`	date	NULL,
	`edu_status`	varchar	NULL	COMMENT '졸업,졸업예정,재학,중퇴,수료,휴학',
	`user_id`	int	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `detail`;

CREATE TABLE `detail` (
	`detail_id`	int	NOT NULL,
	`address`	varchar	NOT NULL,
	`profile`	varchar	NULL,
	`user_id`	int	NOT NULL,
	`status`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT '0대기중 1프로젝트 진행중',
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
	`dept_id`	int	NOT NULL,
	`dept_name`	varchar	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `career`;

CREATE TABLE `career` (
	`career_id`	int	NOT NULL,
	`com_name`	varchar	NOT NULL,
	`rank`	varchar	NOT NULL,
	`start_date`	date	NOT NULL,
	`end_date`	date	NULL	COMMENT '비어있으면 진행 중',
	`user_id`	int	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `military`;

CREATE TABLE `military` (
	`mil_id`	int	NOT NULL,
	`miliary`	varchar	NOT NULL	COMMENT '군필,면제,해당사항없음',
	`mil_type`	varchar	NOT NULL	COMMENT '육군,해군,공군,공익',
	`rank`	varchar	NOT NULL	COMMENT '이병~일병~~',
	`full_type`	varchar	NOT NULL	COMMENT '만기제대,의가사제대',
	`start_mil`	date	NOT NULL,
	`end_mil`	date	NOT NULL,
	`user_id`	int	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `license`;

CREATE TABLE `license` (
	`license_id`	int	NOT NULL,
	`name`	varchar	NOT NULL	COMMENT 'kosa인지 정처기인지',
	`ac_date`	date	NULL	COMMENT '정처기만 취득일자',
	`user_id`	int	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
	`project_id`	int	NOT NULL,
	`project_name`	varchar	NOT NULL,
	`start_date`	date	NOT NULL,
	`end_date`	date	NULL	COMMENT '비어있으면 진행 중',
	`customer`	varchar	NOT NULL,
	`role`	varchar	NOT NULL,
	`os`	varchar	NOT NULL,
	`lang`	varchar	NOT NULL,
	`db`	varchar	NOT NULL,
	`tool`	varchar	NOT NULL,
	`etc`	varchar	NULL,
	`career_id`	int	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`updated_at`	datetime	NULL
);

ALTER TABLE `skill_code` ADD CONSTRAINT `PK_SKILL_CODE` PRIMARY KEY (
	`skill_code_id`
);

ALTER TABLE `training` ADD CONSTRAINT `PK_TRAINING` PRIMARY KEY (
	`edu_id`
);

ALTER TABLE `user_skill` ADD CONSTRAINT `PK_USER_SKILL` PRIMARY KEY (
	`user_skill_id`
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `education` ADD CONSTRAINT `PK_EDUCATION` PRIMARY KEY (
	`edu_id`
);

ALTER TABLE `detail` ADD CONSTRAINT `PK_DETAIL` PRIMARY KEY (
	`detail_id`
);

ALTER TABLE `department` ADD CONSTRAINT `PK_DEPARTMENT` PRIMARY KEY (
	`dept_id`
);

ALTER TABLE `career` ADD CONSTRAINT `PK_CAREER` PRIMARY KEY (
	`career_id`
);

ALTER TABLE `military` ADD CONSTRAINT `PK_MILITARY` PRIMARY KEY (
	`mil_id`
);

ALTER TABLE `license` ADD CONSTRAINT `PK_LICENSE` PRIMARY KEY (
	`license_id`
);

ALTER TABLE `project` ADD CONSTRAINT `PK_PROJECT` PRIMARY KEY (
	`project_id`
);

ALTER TABLE `training` ADD CONSTRAINT `FK_user_TO_training_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `user_skill` ADD CONSTRAINT `FK_skill_code_TO_user_skill_1` FOREIGN KEY (
	`skill_code_id`
)
REFERENCES `skill_code` (
	`skill_code_id`
);

ALTER TABLE `user_skill` ADD CONSTRAINT `FK_user_TO_user_skill_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `user` ADD CONSTRAINT `FK_department_TO_user_1` FOREIGN KEY (
	`dept_id`
)
REFERENCES `department` (
	`dept_id`
);

ALTER TABLE `education` ADD CONSTRAINT `FK_user_TO_education_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `detail` ADD CONSTRAINT `FK_user_TO_detail_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `career` ADD CONSTRAINT `FK_user_TO_career_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `military` ADD CONSTRAINT `FK_user_TO_military_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `license` ADD CONSTRAINT `FK_user_TO_license_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `project` ADD CONSTRAINT `FK_career_TO_project_1` FOREIGN KEY (
	`career_id`
)
REFERENCES `career` (
	`career_id`
);

