/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.base.dao;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.base.entity.DictEntity;

import java.util.List;

/**
 * 附近配置DAO接口
 * @author handf
 * @version 2015-08-09
 */
@MyBatisDao
public interface DictEntityDao extends CrudDao<DictEntity> {
	
	public List<DictEntity> queryDic(String dicTable,String dicCode,String dicText);
	
}