/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.workflow.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.persistence.Page;
import com.base.common.service.CrudService;
import com.base.modules.workflow.dao.TBaTaskDao;
import com.base.modules.workflow.entity.TBaTask;

/**
 * 任务记录Service
 * 
 * @author 张旭东
 * @version 2015-09-01
 */
@Service
@Transactional(readOnly = true)
public class TBaTaskService extends CrudService<TBaTaskDao, TBaTask> {

	@Autowired
	private TBaTaskDao tBaTaskDao;

	public TBaTask get(String id) {
		TBaTask tBaTask = super.get(id);
		return tBaTask;
	}

	public TBaTask getByTBaTask(TBaTask tBaTask) {

		return dao.getByTBaTask(tBaTask);
	}

	public List<TBaTask> findList(TBaTask tBaTask) {
		return super.findList(tBaTask);
	}

	public Page<TBaTask> findPage(Page<TBaTask> page, TBaTask tBaTask) {
		return super.findPage(page, tBaTask);
	}

	@Transactional(readOnly = false)
	public void save(TBaTask tBaTask) {
		super.save(tBaTask);
	}
	
	public void saveWithUpdateBy(TBaTask tBaTask){
		
		dao.update(tBaTask);
		
		
	}

	@Transactional(readOnly = false)
	public void delete(TBaTask tBaTask) {
		super.delete(tBaTask);
	}

	/**
	 * 查询模板节点Id
	 * 
	 * @param tBaTask
	 * @return
	 */
	public String queryTempNoteId(TBaTask tBaTask) {
		return dao.queryTempNoteId(tBaTask);
	}

	/**
	 * 得到该流程的评审类型
	 * 
	 * @param tBaTask
	 * @return
	 */
	public String queryReviewType(TBaTask tBaTask) {
		return dao.queryReviewType(tBaTask);
	}

	public Boolean getTaskByLastTaskCount(String lastTaskId, String taskId) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("lastTaskId", lastTaskId);
		condition.put("id", taskId);
		condition.put("status", "1");
		Integer count = dao.getTaskByLastTaskCount(condition);
		if (count == 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public List<TBaTask> getListByTask(TBaTask tBaTask) {

		return dao.getTBaTaskList(tBaTask);
	}

	public TBaTask getBackTask(String activityId, String noteName) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("activityId", activityId);
		condition.put("noteName", noteName);
		return dao.getBackTask(condition);

	}
	
	 public TBaTask getTaskByNoteAndUser(TBaTask tBaTask){
		 
		 
		 return dao.getTaskByNoteAndUser(tBaTask);
	 }
	 public TBaTask getCNASTask(String activityId){
		 
		 return tBaTaskDao.getCNASTask(activityId);
	 }
	    //cnas受理中详情页面信息查询（业务环节）	
	 public List<Map<String,String>> getTInCnasTask(String cnasCode){
		 return dao.getTInCnasTask(cnasCode);
	 }
	    //cnas受理中详情页面信息查询（评审组信息）	
	 public List<Map<String,String>> getTInCnasReview(String cnasCode){
		 return dao.getTInCnasReview(cnasCode);
	 }
	   //整改信息查询
	 public List<Map<String,String>> getTInCnasCorrect(String cnasCode){
		 return dao.getTInCnasCorrect(cnasCode);
	 }
	 public List<Map<String,String>> getTInCnasDelayInfo(String cnasCode){
		 return dao.getTInCnasDelayInfo(cnasCode);
	 }
	 
	 
	 public TBaTask getCnasTaskByCmaCode(String cmaCode, String userId){
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("cmaCode", cmaCode);
		 map.put("userId", userId);
		return dao.getCnasTaskByCmaCode(map);
		 
	 }
	 
	 public TBaTask getCnasTaskByCmaCodeNot(String cmaCode, String userId){
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("cmaCode", cmaCode);
		 map.put("userId", userId);
		return dao.getCnasTaskByCmaCodeNot(map);
	 }
	 
	 
	 public TBaTask getDualOpinion(String activityId){
		 return dao.getDualOpinion(activityId);
	 }
	 
	 /**
	  * 获得流程记录信息集合
	  * @param tBaTask
	  * @return
	  */
	 public List<TBaTask> findWorkFlowList(TBaTask tBaTask) {
		return dao.findWorkFlowList(tBaTask);
	 }
	 
	 /**
	  * 通过任务ID更新文件ID
	  * @param tBaTask
	  */
	 public void updateFileByTaskId(String taskId, String fileId){
		 TBaTask tBaTask = new TBaTask();
		 tBaTask.setId(taskId);
		 tBaTask.setAttIds(fileId);
		 tBaTaskDao.updateFileByTaskId(tBaTask);
	 }

}