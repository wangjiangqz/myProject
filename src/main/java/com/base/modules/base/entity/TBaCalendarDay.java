/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.base.common.persistence.DataEntity;

/**
 * 日历设置Entity
 * @author zhangxudong
 * @version 2015-12-03
 */
public class TBaCalendarDay extends DataEntity<TBaCalendarDay> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 年份
	private String workDate;		//工作日
	
	public TBaCalendarDay() {
		super();
	}

	public TBaCalendarDay(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
}