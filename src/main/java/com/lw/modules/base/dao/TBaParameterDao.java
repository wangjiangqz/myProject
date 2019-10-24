/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.base.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.base.entity.TBaParameter;

/**
 * 参数配置DAO接口
 * @author handf
 * @version 2015-08-11
 */
@MyBatisDao
public interface TBaParameterDao extends CrudDao<TBaParameter> {
	
}