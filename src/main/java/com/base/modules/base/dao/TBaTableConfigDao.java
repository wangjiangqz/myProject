/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.base.entity.TBaTableConfig;

/**
 * 表单配置DAO接口
 * @author weixm
 * @version 2016-03-29
 */
@MyBatisDao
public interface TBaTableConfigDao extends CrudDao<TBaTableConfig> {

	/**
	 * 执行创建表的SQL
	 * @param sql
	 */
	public void exeSql(Map<String, Object> sql);

	/**
	 * 查询数据库中存不存在改表
	 * @param tableName
	 * @return
	 */
	public int isExistTable(String tableName);

	public Integer checkTableName(String tableName);
	
	/*
	 * 根据表名查找表定义的数据
	 */
	public TBaTableConfig findTBaTableConfigByTableName(String tableName);
	
	/*
	 * 根据主表名查找有没有子表
	 */
	public List<TBaTableConfig> findSonTableInfoByTableName(String tableName);
	
	/*
	 * 执行sql语句查找list
	 */
//	public List findListBySql(Map<String, Object> sql);
}