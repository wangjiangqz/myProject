/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.company.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.company.entity.TBzCompanyPersonnel;

/**
 * 企业人员信息DAO接口
 * @author xupeng
 * @version 2018-04-16
 */
@MyBatisDao
public interface TBzCompanyPersonnelDao extends CrudDao<TBzCompanyPersonnel> {
	
}