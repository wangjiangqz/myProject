/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.workflow.dao;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.workflow.entity.TBaNoteRole;

/**
 * 流程节点角色DAO接口
 * @author handf
 * @version 2015-09-02
 */
@MyBatisDao
public interface TBaNoteRoleDao extends CrudDao<TBaNoteRole> {
	
}