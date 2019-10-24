/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import java.util.List;
import java.util.Map;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBaTempNote;

/**
 * 流程管理DAO接口
 * @author handf
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaTempNoteDao extends CrudDao<TBaTempNote> {
    /**
     * 根据节点ID查询角色集合
     * @param noteId
     * @return
     */
	public List<String> queryNoteRoleByNoteId(String noteId); 
	
	/**
	 * 删除对应节点下的角色集合
	 * @param condition
	 */
	public void deleteNoteRoleByMap(Map<String, Object> condition);
	
	public List<TBaTempNote> findListByTBaTempNote(TBaTempNote tBaTempNote);
	
	public List<TBaTempNote> findAllNote();
	
}