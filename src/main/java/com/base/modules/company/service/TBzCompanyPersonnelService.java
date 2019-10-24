/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.company.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.persistence.Page;
import com.base.common.service.CrudService;
import com.base.modules.company.entity.TBzCompanyPersonnel;
import com.base.modules.company.dao.TBzCompanyPersonnelDao;

/**
 * 企业人员信息Service
 * @author xupeng
 * @version 2018-04-16
 */
@Service
@Transactional(readOnly = true)
public class TBzCompanyPersonnelService extends CrudService<TBzCompanyPersonnelDao, TBzCompanyPersonnel> {

	public TBzCompanyPersonnel get(String id) {
		return super.get(id);
	}
	
	public List<TBzCompanyPersonnel> findList(TBzCompanyPersonnel tBzCompanyPersonnel) {
		return super.findList(tBzCompanyPersonnel);
	}
	
	public Page<TBzCompanyPersonnel> findPage(Page<TBzCompanyPersonnel> page, TBzCompanyPersonnel tBzCompanyPersonnel) {
		return super.findPage(page, tBzCompanyPersonnel);
	}
	
	@Transactional(readOnly = false)
	public void save(TBzCompanyPersonnel tBzCompanyPersonnel) {
		super.save(tBzCompanyPersonnel);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBzCompanyPersonnel tBzCompanyPersonnel) {
		super.delete(tBzCompanyPersonnel);
	}
	
}