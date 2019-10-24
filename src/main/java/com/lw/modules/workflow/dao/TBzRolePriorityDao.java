/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBzRolePriority;

/**
 * 角色定义权重管理DAO接口
 * @author handf
 * @version 2016-06-25
 */
@MyBatisDao
public interface TBzRolePriorityDao extends CrudDao<TBzRolePriority> {
	
}