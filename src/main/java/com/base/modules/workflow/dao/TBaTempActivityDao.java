/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.workflow.dao;

import java.util.List;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.workflow.entity.TBaTempActivity;

/**
 * 流程管理DAO接口
 * @author handf
 * @version 2015-09-01
 */
@MyBatisDao
public interface TBaTempActivityDao extends CrudDao<TBaTempActivity> {
	
    public List<TBaTempActivity> checkActivtyName(TBaTempActivity tBaTempActivit);
    
    public List<TBaTempActivity> checkActivtyLogo(TBaTempActivity tBaTempActivity);
    
    public TBaTempActivity getByTBaTempActivity(TBaTempActivity tBaTempActivity);

}