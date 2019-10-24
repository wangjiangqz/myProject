/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.code.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.code.entity.OwnMap;
import com.lw.modules.code.entity.TBzCode;

import java.util.List;
import java.util.Map;

/**
 * 代码DAO接口
 * @author wzy
 * @version 2018-08-13
 */
@MyBatisDao
public interface TBzCodeDao extends CrudDao<TBzCode> {

    public List<OwnMap> executeSql(OwnMap map);
	
}