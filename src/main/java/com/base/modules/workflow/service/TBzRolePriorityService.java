/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.workflow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.persistence.Page;
import com.base.common.service.CrudService;
import com.base.modules.workflow.entity.TBzRolePriority;
import com.base.modules.workflow.dao.TBzRolePriorityDao;

/**
 * 角色定义权重管理Service
 * @author handf
 * @version 2016-06-25
 */
@Service
@Transactional(readOnly = true)
public class TBzRolePriorityService extends CrudService<TBzRolePriorityDao, TBzRolePriority> {

	public TBzRolePriority get(String id) {
		return super.get(id);
	}
	
	public List<TBzRolePriority> findList(TBzRolePriority tBzRolePriority) {
		return super.findList(tBzRolePriority);
	}
	
	public Page<TBzRolePriority> findPage(Page<TBzRolePriority> page, TBzRolePriority tBzRolePriority) {
		return super.findPage(page, tBzRolePriority);
	}
	
	@Transactional(readOnly = false)
	public void save(TBzRolePriority tBzRolePriority) {
		super.save(tBzRolePriority);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBzRolePriority tBzRolePriority) {
		super.delete(tBzRolePriority);
	}
	
}