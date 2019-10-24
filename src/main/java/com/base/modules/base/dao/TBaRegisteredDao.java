/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.base.entity.TBaRegistered;

/**
 * 注册模块DAO接口
 * @author handf
 * @version 2015-08-13
 */
@MyBatisDao
public interface TBaRegisteredDao extends CrudDao<TBaRegistered> {
	
    public List<TBaRegistered> checkName(Map<String, Object> condition);
    
    public List<TBaRegistered> checkOrgCode(Map<String, Object> condition);
    
}