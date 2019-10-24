/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.company.dao;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.company.entity.TBzCompanyPersonnel;

/**
 * 企业人员信息DAO接口
 * @author xupeng
 * @version 2018-04-16
 */
@MyBatisDao
public interface TBzCompanyPersonnelDao extends CrudDao<TBzCompanyPersonnel> {
	
}