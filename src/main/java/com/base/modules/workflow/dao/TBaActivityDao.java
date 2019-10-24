/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.workflow.dao;

import java.util.List;
import java.util.Map;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.workflow.entity.TBaActivity;
import com.base.modules.workflow.entity.TBaTask;

/**
 * 流程实例DAO接口
 * @author zhangxudong
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaActivityDao extends CrudDao<TBaActivity> {
	
    public List<TBaActivity> countTBaActivity(TBaActivity tBaActivity);
    
    public TBaActivity getByTBaActivity(TBaActivity tBaActivity);
    
    public TBaActivity queryActivityIdByAbilityId(String abilityId);
    
    public TBaTask queryOpinionContentIdByActivityId(String activity);
    //新增
    public TBaActivity queryActivity(String activityId);
    
    public List<TBaActivity> queryByCnas();
    
    /**
     * cma申请部分节点撤回
     * @param condition
     * @return
     */
	public String cmaRetractFlowNode(Map<String, String> condition);
	
	/**
     * cma申请部分节点撤回
     * @param condition
     * @return
     */
	public String cnasRetractFlowNode(Map<String, String> condition);

    TBaActivity getTerminationActivity(String applyId);
}