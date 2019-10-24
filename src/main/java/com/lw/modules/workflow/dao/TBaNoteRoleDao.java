/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBaNoteRole;

/**
 * 流程节点角色DAO接口
 * @author handf
 * @version 2015-09-02
 */
@MyBatisDao
public interface TBaNoteRoleDao extends CrudDao<TBaNoteRole> {
	
}