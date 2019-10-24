/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBaTask;

import java.util.List;
import java.util.Map;

/**
 * 任务记录DAO接口
 * @author 张旭东
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaTaskDao extends CrudDao<TBaTask> {	
	
    public List<TBaTask> getTBaTaskList(TBaTask tBaTask);
    
    public void deleteOthersTask(TBaTask tBaTask);
    
    public TBaTask getByTBaTask(TBaTask tBaTask);
    
    public List<TBaTask> getListByTask(TBaTask tBaTask);
    
    public TBaTask getCNASTask(String activityId);
    
    //cnas受理中详情页面信息查询（业务环节）	
    public List<Map<String,String>> getTInCnasTask(String cnasCode);
    //cnas受理中详情页面信息查询（评审组信息）	
    public List<Map<String,String>> getTInCnasReview(String cmaCode);
    //cnas整改记录信息查询
    public List<Map<String,String>> getTInCnasCorrect(String cnasCode);
    //cnas延期记录信息查询
    public List<Map<String,String>> getTInCnasDelayInfo(String cnasCode);
    
    /**
     * 查询模板节点Id
     * @param tBaTask
     * @return
     */
    public String queryTempNoteId(TBaTask tBaTask);
    
    /**
     * 查询流程评审类型
     * @param tBaTask
     * @return
     */
    public String queryReviewType(TBaTask tBaTask);
    
    /**
     * 得到下一权重
     */
    public String getNextPriority(Map<String, String> condition);

    public String getCertbelong(String taskId);

    public Integer getTaskByLastTaskCount(Map<String, String> condition);
    
    public TBaTask getBackTask(Map<String, String> condition);
    
    public TBaTask getTaskByNoteAndUser(TBaTask tBaTask);
    
    public void updateFlowReviewPerson(Map<String, String> condition);
    
    public TBaTask getCnasTaskByCmaCode(Map<String, String> condition);
    
    public TBaTask getCnasTaskByCmaCodeNot(Map<String, String> map);
    
    public TBaTask getDualOpinion(String activityId);
    
	/**
	 * 获得流程记录信息集合
	 * @param tBaTask
	 * @return
	 */
	public List<TBaTask> findWorkFlowList(TBaTask tBaTask);
	
	/**
	 * 根据任务ID更新电子签章附件ID
	 * @param tBaTask
	 */
	public void updateFileByTaskId(TBaTask tBaTask);

    public  void deleteOthersTaskAll(TBaTask tBaTask);

    String getMaxPriority(Map<String, String> condition);

    /**
     * 得到下一权重对应的角色
     */
    String getNextRole(Map<String, String> condition);

}