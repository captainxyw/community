-- we don't know how to generate database COMMUNITY (class Database) :(
create table COMMENT
(
	ID BIGINT default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_818A10C0_32D0_40D1_B0A6_B76A189456EF)
		primary key,
	PARENT_ID BIGINT not null,
	TYPE INTEGER not null,
	COMMENTATOR BIGINT default NULL not null,
	GMT_CREATE BIGINT not null,
	GMT_MODIFIED BIGINT not null,
	LIKE_COUNT BIGINT default 0,
	CONTENT VARCHAR(1024),
	COMMENT_COUNT INTEGER default 0
)
;

create table NOTIFICATION
(
	ID BIGINT default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_B7577209_FCED_4448_9DF8_4BDBB86E5F1F)
		primary key,
	NOTIFIER BIGINT not null,
	RECEIVER BIGINT not null,
	OUTERID BIGINT not null,
	TYPE INTEGER not null,
	GMT_CREATE BIGINT not null,
	STATUS INTEGER default 0 not null,
	NOTIFIER_NAME VARCHAR(100),
	OUTER_TITLE VARCHAR(256)
)
;

create table QUESTION
(
	ID BIGINT default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_3BB08A81_D8D2_46DF_A6A1_955B97EEC5E5)
		primary key,
	TITLE VARCHAR(50),
	DESCRIPTION CLOB,
	GMT_MODIFIED BIGINT,
	CREATOR BIGINT default NULL,
	COMMENT_COUNT INTEGER default 0,
	VIEW_COUNT INTEGER default 0,
	LIKE_COUNT INTEGER default 0,
	TAG VARCHAR(256),
	GMT_CREATE BIGINT
)
;

create unique index QUESTION_ID_UINDEX
	on QUESTION (ID)
;

create table USER
(
	ID BIGINT default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_AAD3D56B_CB62_4CC5_8FFD_53516F67FC34)
		primary key,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	BIO VARCHAR(256),
	AVATAR_URL VARCHAR(100)
)
;

create table "flyway_schema_history"
(
	"installed_rank" INTEGER not null,
	"version" VARCHAR(50),
	"description" VARCHAR(200) not null,
	"type" VARCHAR(20) not null,
	"script" VARCHAR(1000) not null,
	"checksum" INTEGER,
	"installed_by" VARCHAR(100) not null,
	"installed_on" TIMESTAMP default CURRENT_TIMESTAMP() not null,
	"execution_time" INTEGER not null,
	"success" BOOLEAN not null,
	constraint "flyway_schema_history_pk"
		primary key ("installed_rank")
)
;

create index "flyway_schema_history_s_idx"
	on "flyway_schema_history" ("success")
;

