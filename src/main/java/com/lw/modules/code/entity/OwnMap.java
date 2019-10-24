package com.lw.modules.code.entity;

import com.lw.common.persistence.Page;

import java.util.HashMap;


public class OwnMap extends HashMap<String, Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Page<OwnMap> page;
	public Page<OwnMap> getPage() {
		return page;
	}
	public void setPage(Page<OwnMap> page) {
		this.page = page;
	}
	
	

	
}
