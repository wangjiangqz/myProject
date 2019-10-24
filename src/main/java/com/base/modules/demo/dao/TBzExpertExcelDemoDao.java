/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.demo.dao;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.demo.entity.TBzExpertExcelDemo;

/**
 * Excel演示DAO接口
 * @author handf
 * @version 2016-08-26
 */
@MyBatisDao
public interface TBzExpertExcelDemoDao extends CrudDao<TBzExpertExcelDemo> {
	
	/**
	 * 清除所有的信息
	 */
	public void deleteAll();
}