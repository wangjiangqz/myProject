/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.base.dao;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.base.entity.TBaCalendarDay;

/**
 * 日历设置DAO接口
 * @author zhangxudong
 * @version 2015-12-03
 */
@MyBatisDao
public interface TBaCalendarDao extends CrudDao<TBaCalendarDay> {
	public TBaCalendarDay getTBaCalendar(TBaCalendarDay tBaCalendarDay);
	
}