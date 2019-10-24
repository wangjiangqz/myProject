/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.datasource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.modules.datasource.entity.TBaDatasource;
import com.lw.modules.datasource.dao.TBaDatasourceDao;

/**
 * 数据源Service
 * @author yan
 * @version 2018-11-07
 */
@Service
@Transactional(readOnly = true)
public class TBaDatasourceService extends CrudService<TBaDatasourceDao, TBaDatasource> {

	public TBaDatasource get(String id) {
		return super.get(id);
	}
	
	public List<TBaDatasource> findList(TBaDatasource tBaDatasource) {
		return super.findList(tBaDatasource);
	}
	
	public Page<TBaDatasource> findPage(Page<TBaDatasource> page, TBaDatasource tBaDatasource) {
		return super.findPage(page, tBaDatasource);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaDatasource tBaDatasource) {
		super.save(tBaDatasource);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBaDatasource tBaDatasource) {
		super.delete(tBaDatasource);
	}
	
}