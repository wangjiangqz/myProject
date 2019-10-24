/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.dao;

import com.lw.common.persistence.CrudDao;
import com.lw.common.persistence.annotation.MyBatisDao;
import com.lw.modules.workflow.entity.TBaNote;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 流程实例DAO接口
 * @author zhangxudong
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaNoteDao extends CrudDao<TBaNote> {
	public TBaNote getTBaNote(TBaNote tBaNote);
    
    public List<TBaNote> countTBaNote(TBaNote tBaNote);
    
    public List<TBaNote> getTBaNoteList(TBaNote tBaNote);
    
    public List<TBaNote> getBeforeTBaNote(TBaNote tBaNote);
    
    public Integer updateFlag(TBaNote tBaNote);
    
    public List<Map<String,String>> queryNoteRestDay();

	public List<Map<String, String>> getNextTemp();
	
	public List<Map<String, String>> getEmail();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int updateNextTempFlag(String id);

	public List<Map<String, String>> getDate();

	public String getCNASWorkDate(String activityId);
	
	//现场评审剩余时间
	public String getWorkDate(String activityId);
	
	public String getCnasTotalDate(String activityId);
	
	public List<Map<String, Object>> getWarn();

	public List<Map<String, String>> getAtt(String activityId);

	public Date getAttDate();
	
	public int getWarmDate(Map<String, String> condition) ;

	public List<Map<String,String>> getCorrectWorkDate();
	
	public void insertCmaRectifyWorkday(Map<String, String> resultMap);
	
	public Date getStartWorkDays(Map<String, Object> result);
	
	/**
	 * 根据流程ID删除当前正在运行的节点
	 * @param activityId
	 */
	public void deleteNodeByActivityId(String activityId);
	
	/**
	 * 更新节点状态
	 * @param id
	 */
	public void updateNoteStatus(String id);
	
	public TBaNote getOrgSubmitDate(String id);
	
	/**
	 * 查询当前的待办
	 */
	public TBaNote ajaxCheckNote(String activityId);
}