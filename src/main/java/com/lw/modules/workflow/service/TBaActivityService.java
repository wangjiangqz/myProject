/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.workflow.dao.TBaActivityDao;
import com.lw.modules.workflow.dao.TBaNoteDao;
import com.lw.modules.workflow.dao.TBaTaskDao;
import com.lw.modules.workflow.entity.TBaActivity;
import com.lw.modules.workflow.entity.TBaNote;
import com.lw.modules.workflow.entity.TBaTask;

/**
 * 任务记录Service
 * @author 张旭东
 * @version 2015-09-01
 */
@Service
@Transactional(readOnly = true)
public class TBaActivityService extends CrudService<TBaActivityDao, TBaActivity> {
	

	@Autowired TBaNoteDao noteDao;
	
	@Autowired TBaTaskDao taskDao;

	
	@Autowired TBaActivityDao tBaActivityDao;
	public TBaActivity get(String id) {
		TBaActivity tBaActivity = super.get(id);
		return tBaActivity;
	}

	public List<TBaActivity> findList(TBaActivity tBaActivity) {
		return super.findList(tBaActivity);
	}
	
	public Page<TBaActivity> findPage(Page<TBaActivity> page, TBaActivity tBaActivity) {
		return super.findPage(page, tBaActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaActivity tBaActivity) {
		super.save(tBaActivity);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaActivity tBaActivity) {
		super.delete(tBaActivity);
	}
	
	public TBaActivity getByTBaActivity(TBaActivity tBaActivity){
		
		return dao.getByTBaActivity(tBaActivity);
	}
	
	public void deleteAllInfo(String id){
		
		TBaActivity activity=new TBaActivity();
		//删除所有流程相关数据
		//查询节点
		activity.setId(id);
		TBaNote note=new TBaNote();
		note.setActivityId(activity);
		noteDao.delete(note);
		TBaTask task=new TBaTask();
		task.setActivityId(id);
		taskDao.delete(task);
		super.delete(activity);
	}
	public List<Map<String,String>> queryNoteRestDay(){
		
		return noteDao.queryNoteRestDay();
		
	}
	public TBaActivity queryActivityIdByAbilityId(String abilityId){
		return tBaActivityDao.queryActivityIdByAbilityId(abilityId);
	}
	public TBaTask queryOpinionContentIdByActivityId(String activity){
		return tBaActivityDao.queryOpinionContentIdByActivityId(activity);
	}
	
	public TBaActivity queryActivity(String activityId){
		return tBaActivityDao.queryActivity(activityId);
	}
	
	public List<TBaActivity> queryByCnas(){
		return tBaActivityDao.queryByCnas();
	}

    public TBaActivity getTerminationActivity(String applyId)
    {
		return  dao.getTerminationActivity(applyId);
    }
}