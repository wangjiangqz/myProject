/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.datasource.dao;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.datasource.entity.TBaDatasource;

/**
 * 数据源DAO接口
 * @author yan
 * @version 2018-11-07
 */
@MyBatisDao
public interface TBaDatasourceDao extends CrudDao<TBaDatasource> {
	
}