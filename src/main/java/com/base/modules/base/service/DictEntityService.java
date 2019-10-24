/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.base.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.service.CrudService;
import com.base.modules.base.entity.DictEntity;
import com.base.modules.base.dao.DictEntityDao;

/**
 * 附近配置Service
 * @author handf
 * @version 2015-08-09
 */
@Service
@Transactional(readOnly = true)
public class DictEntityService extends CrudService<DictEntityDao, DictEntity> {

	public List<DictEntity> queryDic(String dicTable,String dicCode,String dicText){
		return dao.queryDic(dicTable, dicCode, dicText);
	}
   
}