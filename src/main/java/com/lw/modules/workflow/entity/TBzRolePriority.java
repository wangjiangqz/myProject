/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.modules.sys.entity.Role;
import com.lw.modules.sys.entity.User;

import com.lw.common.persistence.DataEntity;

/**
 * 角色定义权重管理Entity
 * @author handf
 * @version 2016-06-25
 */
public class TBzRolePriority extends DataEntity<TBzRolePriority> {
	
	private static final long serialVersionUID = 1L;
	private String roleId;		// 角色ID
	private User user;		// 用户ID
	private String priority;		// 优先级
	private Role role;
	private String sort;
	
	public TBzRolePriority() {
		super();
	}

	public TBzRolePriority(String id){
		super(id);
	}

	@Length(min=0, max=40, message="角色ID长度必须介于 0 和 40 之间")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=10, message="优先级长度必须介于 0 和 10 之间")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}
}