/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.persistence.Page;
import com.base.common.service.CrudService;
import com.base.modules.demo.entity.TBzInfo;
import com.base.modules.demo.dao.TBzInfoDao;
import com.base.modules.sys.entity.User;

/**
 * 弹出消息Service
 * @author wj
 * @version 2017-01-24
 */
@Service
@Transactional(readOnly = true)
public class TBzInfoService extends CrudService<TBzInfoDao, TBzInfo> {
    
    @Autowired
    private TBzInfoDao tBzInfoDao;
    
	public TBzInfo get(String id) {
		return super.get(id);
	}
	
	public List<TBzInfo> findList(TBzInfo tBzInfo) {
		return super.findList(tBzInfo);
	}
	
	public Page<TBzInfo> findPage(Page<TBzInfo> page, TBzInfo tBzInfo) {
		return super.findPage(page, tBzInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(TBzInfo tBzInfo) {
		super.save(tBzInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBzInfo tBzInfo) {
		super.delete(tBzInfo);
	}

    public List<TBzInfo> getByUser(User user)
    {
        return tBzInfoDao.getByUser(user);
    }

    public void haveReadData(String id)
    {
        tBzInfoDao.haveReadData(id);
    }
	
}