/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.workflow.entity;

import org.hibernate.validator.constraints.Length;

import com.base.common.persistence.DataEntity;

/**
 * 流程节点角色Entity
 * @author handf
 * @version 2015-09-02
 */
public class TBaNoteRole extends DataEntity<TBaNoteRole> {
	
	private static final long serialVersionUID = 1L;
	private String roleId;		// 角色ID
	private String noteId;		// 节点ID
	
	public TBaNoteRole() {
		super();
	}

	public TBaNoteRole(String id){
		super(id);
	}

	@Length(min=0, max=40, message="角色ID长度必须介于 0 和 40 之间")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Length(min=0, max=40, message="节点ID长度必须介于 0 和 40 之间")
	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	
}