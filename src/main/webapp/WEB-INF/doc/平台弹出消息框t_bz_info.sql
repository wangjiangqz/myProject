create table T_BZ_INFO
(
  id         VARCHAR2(64) not null,
  userid     VARCHAR2(64),
  title      VARCHAR2(100),
  content    VARCHAR2(600),
  flag       CHAR(2) default 00,
  createby   VARCHAR2(64),
  createdate DATE,
  updateby   VARCHAR2(64),
  updatedate DATE
)
;
comment on column T_BZ_INFO.id
  is '编号';
comment on column T_BZ_INFO.userid
  is '用户编号';
comment on column T_BZ_INFO.title
  is '标题';
comment on column T_BZ_INFO.content
  is '内容';
comment on column T_BZ_INFO.flag
  is '00未读，01已读';
alter table T_BZ_INFO
  add constraint ID primary key (ID);
