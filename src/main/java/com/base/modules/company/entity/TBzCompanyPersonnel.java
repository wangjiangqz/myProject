/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.company.entity;

import org.hibernate.validator.constraints.Length;

import com.base.common.persistence.DataEntity;

/**
 * 企业人员信息Entity
 * @author xupeng
 * @version 2018-04-16
 */
public class TBzCompanyPersonnel extends DataEntity<TBzCompanyPersonnel> {
	
	private static final long serialVersionUID = 1L;
	private String companyName;		// 企业名称
	private String type;		// 企业负责人
	private String recodeCode;		// 出口备案编号
	private String personName;		// 联系人姓名
	private String phone;		// 联系人号码
	
	public TBzCompanyPersonnel() {
		super();
	}

	public TBzCompanyPersonnel(String id){
		super(id);
	}

	@Length(min=0, max=255, message="企业名称长度必须介于 0 和 255 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Length(min=0, max=255, message="企业负责人长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="出口备案编号长度必须介于 0 和 255 之间")
	public String getRecodeCode() {
		return recodeCode;
	}

	public void setRecodeCode(String recodeCode) {
		this.recodeCode = recodeCode;
	}
	
	@Length(min=0, max=255, message="联系人姓名长度必须介于 0 和 255 之间")
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	@Length(min=0, max=255, message="联系人号码长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}