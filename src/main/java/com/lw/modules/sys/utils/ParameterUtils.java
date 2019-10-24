/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.lw.modules.sys.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lw.common.utils.CacheUtils;
import com.lw.common.utils.SpringContextHolder;
import com.lw.modules.base.dao.TBaParameterDao;
import com.lw.modules.base.entity.TBaParameter;
import com.lw.modules.sys.dao.DictDao;
import com.lw.modules.sys.entity.Dict;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class ParameterUtils
{
	
	private static TBaParameterDao tBaParameterDao = SpringContextHolder.getBean(TBaParameterDao.class);

	public static final String CACHE_TBAPARAMETER_MAP = "tBaParameterMap";



	public static String getParaValue(String paraKey, String paraType, String defaultValue){
		if (StringUtils.isNotBlank(paraKey) && StringUtils.isNotBlank(paraType)){
			for (TBaParameter tBaParameter : getParameterList(paraType)){
				if (paraType.equals(tBaParameter.getParaType()) && paraKey.equals(tBaParameter.getParaKey())){
					return tBaParameter.getParaValue();
				}
			}
		}
		return defaultValue;
	}

	public static TBaParameter getParameter(String paraKey, String paraType){
		if (StringUtils.isNotBlank(paraKey) && StringUtils.isNotBlank(paraType)){
			for (TBaParameter tBaParameter : getParameterList(paraType)){
				if (paraType.equals(tBaParameter.getParaType()) && paraKey.equals(tBaParameter.getParaKey())){
					return tBaParameter;
				}
			}
		}
		return new TBaParameter();
	}
	
	public static List<TBaParameter> getParameterList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<TBaParameter>> tBaParameterMap = (Map<String, List<TBaParameter>>)CacheUtils.get(CACHE_TBAPARAMETER_MAP);
		if (tBaParameterMap==null){
			tBaParameterMap = Maps.newHashMap();
			for (TBaParameter tBaParameter : tBaParameterDao.findAllList(new TBaParameter())){
				List<TBaParameter> tBaParameterList = tBaParameterMap.get(tBaParameter.getParaType());
				if (tBaParameterList != null){
					tBaParameterList.add(tBaParameter);
				}else{
					tBaParameterMap.put(tBaParameter.getParaType(), Lists.newArrayList(tBaParameter));
				}
			}
			CacheUtils.put(CACHE_TBAPARAMETER_MAP, tBaParameterMap);
		}
		List<TBaParameter> tBaParameterList = tBaParameterMap.get(type);
		if (tBaParameterList == null){
			tBaParameterList = Lists.newArrayList();
		}
		return tBaParameterList;
	}
	
}
