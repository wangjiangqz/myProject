select * from SYS_AREA; --行政区划表（初始化数据不要轻易修改）;有初始化数据
select * from SYS_DICT;--字典表，一般用来定义下拉框值等;有初始化数据
select * from SYS_MENU;--菜单表（根据自己需要可以改变菜单);有初始化数据
select * from SYS_OFFICE;--组织机构或部门表;有初始化数据
select * from SYS_ROLE;--角色表;有初始化数据
select * from SYS_ROLE_MENU;--;菜单与角色关系表；有初始化数据
select * from SYS_ROLE_OFFICE;--部门与角色关系表
select * from SYS_USER;--用户表
select * from SYS_USER_ROLE;--用户角色表
select * from SYS_LOG;--系统日志表
select * from GEN_SCHEME;--平台生成代码信息记录表，开发用，数据一般都没有用
select * from GEN_TABLE;--平台生成代码信息记录表，开发用，数据一般都没有用
select * from GEN_TABLE_COLUMN;--平台生成代码信息记录表，开发用，数据一般都没有用
select * from GEN_TEMPLATE;--平台生成代码信息记录表，开发用，数据一般都没有用
select * from T_BA_ATT;--附件模板配置表
select * from T_BA_PARAMETER;--参数配置表
select * from T_BA_FILE;--文件上传记录表
select * from T_BA_REGISTERED;--系统注册表
select * from T_BA_ACTIVITY;--系统流程实例表
select * from T_BA_NOTE;--系统流程节点表
select * from T_BA_NOTE_ROLE;--节点角色配置表
select * from T_BA_TASK;--任务表
select * from T_BA_TEMP_ACTIVITY;--流程定义表
select * from T_BA_TEMP_NOTE;--流程节点定义表
select * from T_BA_CALENDAR;--系统日历设置表
