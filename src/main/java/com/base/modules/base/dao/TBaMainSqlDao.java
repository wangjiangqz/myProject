/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.base.entity.TBaField;
import com.base.modules.base.entity.TBaMainSql;

/**
 * 动态查询DAO接口
 * @author handf
 * @version 2016-03-14
 */
@MyBatisDao
public interface TBaMainSqlDao extends CrudDao<TBaMainSql> {
	
	/**
	 * 检测sql能否正确的执行
	 * @param headSql
	 * @return
	 */
	public int checkIsSql(Map<String, String> headMap) throws Exception ;
	
	/**
	 * 获得查询条件结果集类型
	 * @param headMap
	 * @return
	 */
	public List<TBaField> getResultTypeList(Map<String, String> headMap);
	
	/**
	 * 创建视图
	 * @param headMap
	 */
	public void createTempView(Map<String, String> headMap);
	
	/**
	 * 删除视图
	 * @param headMap
	 */
	public void deleteTempView(Map<String, String> headMap);
	
}