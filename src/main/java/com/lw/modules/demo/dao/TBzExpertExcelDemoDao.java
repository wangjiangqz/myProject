/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.demo.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.demo.entity.TBzExpertExcelDemo;

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