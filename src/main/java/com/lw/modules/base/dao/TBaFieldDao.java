/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.base.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.base.entity.TBaField;

/**
 * 动态查询DAO接口
 * @author handf
 * @version 2016-03-24
 */
@MyBatisDao
public interface TBaFieldDao extends CrudDao<TBaField> {
	
}