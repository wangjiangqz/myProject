/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.workflow.service;

import com.lw.common.service.CrudService;
import com.lw.modules.workflow.dao.TBaNoteDao;
import com.lw.modules.workflow.dao.TBaTempNoteDao;
import com.lw.modules.workflow.entity.TBaNote;
import com.lw.modules.workflow.entity.TBaTempNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 任务节点Service
 * @author 张太然
 * @version 2015-09-01
 */
@Service
@Transactional(readOnly = true)
public class TBaNoteService extends CrudService<TBaNoteDao, TBaNote> {
	private static final String String = null;
	@Autowired TBaTempNoteDao tBaTempNoteDao;
	
	public List<TBaTempNote> findAllNote(){
		return tBaTempNoteDao.findAllNote();
	}
	//结束cnas结点发送到分配评定任务结点
	public List<Map<String,String>> getNextTemp(){
		return dao.getNextTemp();
	}
	
	public void updateNextTempFlag(String id){
		
		dao.updateNextTempFlag(id);
		
	}
	//cnas处理任务剩余时间
	public List<Map<String,String>> getDate(){
		return dao.getDate();
	}
	

	//cnas处理超时告警
	public List<Map<String,Object>> getWarn(){
		return dao.getWarn();
		}
	//获得cnas的附件
	public List<Map<String,String>> getAtt(String activityId){
		return dao.getAtt(activityId);
		}	
	
	//获得cnas的附件
		public Date getAttDate(){
			return dao.getAttDate();
		}
	//获取邮箱
		
	public List<Map<String,String>> getEmail(){
		return dao.getEmail();
		
	}

	public List<Map<String,String>> getCorrectWorkDate(){
		return dao.getCorrectWorkDate();
	}
	
	public void insertCmaRectifyWorkday(Map<String,String> resultMap){
		dao.insertCmaRectifyWorkday(resultMap);
	}
	
	public Date getStartWorkDays(Map<String,Object> result){
		return dao.getStartWorkDays(result);
	}
	
	public TBaNote getOrgSubmitDate(String activityId){
		return dao.getOrgSubmitDate(activityId);
	}
	 /**
	 * 查询当前的待办
	 */
	public TBaNote ajaxCheckNote(String activityId){
		return dao.ajaxCheckNote(activityId);
	}
}