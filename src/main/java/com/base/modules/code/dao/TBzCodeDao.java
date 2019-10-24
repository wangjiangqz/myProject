/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.code.dao;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.code.entity.OwnMap;
import com.base.modules.code.entity.TBzCode;

import java.util.List;

/**
 * 代码DAO接口
 * @author wzy
 * @version 2018-08-13
 */
@MyBatisDao
public interface TBzCodeDao extends CrudDao<TBzCode> {

    public List<OwnMap> executeSql(OwnMap map);
	
}