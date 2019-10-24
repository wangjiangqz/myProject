/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.demo.dao;

import java.util.List;

import com.base.common.persistence.CrudDao;
import com.base.common.persistence.annotation.MyBatisDao;
import com.base.modules.demo.entity.TBzInfo;
import com.base.modules.sys.entity.User;

/**
 * 弹出消息DAO接口
 * @author wj
 * @version 2017-01-24
 */
@MyBatisDao
public interface TBzInfoDao extends CrudDao<TBzInfo> {

    List<TBzInfo> getByUser(User user);

    void haveReadData(String id);
	
}