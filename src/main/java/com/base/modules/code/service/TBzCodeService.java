/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.code.service;

import java.util.List;

import com.base.modules.code.entity.OwnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.persistence.Page;
import com.base.common.service.CrudService;
import com.base.modules.code.entity.TBzCode;
import com.base.modules.code.dao.TBzCodeDao;

/**
 * 代码Service
 * @author wzy
 * @version 2018-08-13
 */
@Service
@Transactional(readOnly = true)
public class TBzCodeService extends CrudService<TBzCodeDao, TBzCode> {

	@Autowired
	private TBzCodeDao dao;

	public TBzCode get(String id) {
		return super.get(id);
	}
	
	public List<TBzCode> findList(TBzCode tBzCode) {
		return super.findList(tBzCode);
	}
	
	public Page<TBzCode> findPage(Page<TBzCode> page, TBzCode tBzCode) {
		return super.findPage(page, tBzCode);
	}
	
	@Transactional(readOnly = false)
	public void save(TBzCode tBzCode) {
		super.save(tBzCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBzCode tBzCode) {
		super.delete(tBzCode);
	}

	public Page<OwnMap> executeSql(Page<OwnMap> page, OwnMap map) {
		map.setPage(page);
		List<OwnMap> list = dao.executeSql(map);
		page.setList(list);
		return page;
	}
	
}