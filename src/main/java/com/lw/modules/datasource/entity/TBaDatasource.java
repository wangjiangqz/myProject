/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.lw.modules.datasource.entity;

import org.hibernate.validator.constraints.Length;

import com.lw.common.persistence.DataEntity;

/**
 * 数据源Entity
 * @author yan
 * @version 2018-11-07
 */
public class TBaDatasource extends DataEntity<TBaDatasource> {
	
	private static final long serialVersionUID = 1L;
	private String dataName;		// 数据源名称
	private String dataType;		// 数据源类型
	private String resultType;		// 结果类型
	private String dataSourceIdentifier;		// 数据源标识
	private String customTableName;		// 自定义表名
	private String uniqueConstraint;		// 唯一约束
	private String deletingConstraint;		// 删除约束
	
	public TBaDatasource() {
		super();
	}

	public TBaDatasource(String id){
		super(id);
	}

	@Length(min=0, max=4000, message="数据源名称长度必须介于 0 和 4000 之间")
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	@Length(min=0, max=4000, message="数据源类型长度必须介于 0 和 4000 之间")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Length(min=0, max=4000, message="结果类型长度必须介于 0 和 4000 之间")
	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
	@Length(min=0, max=4000, message="数据源标识长度必须介于 0 和 4000 之间")
	public String getDataSourceIdentifier() {
		return dataSourceIdentifier;
	}

	public void setDataSourceIdentifier(String dataSourceIdentifier) {
		this.dataSourceIdentifier = dataSourceIdentifier;
	}
	
	@Length(min=0, max=4000, message="自定义表名长度必须介于 0 和 4000 之间")
	public String getCustomTableName() {
		return customTableName;
	}

	public void setCustomTableName(String customTableName) {
		this.customTableName = customTableName;
	}
	
	@Length(min=0, max=4000, message="唯一约束长度必须介于 0 和 4000 之间")
	public String getUniqueConstraint() {
		return uniqueConstraint;
	}

	public void setUniqueConstraint(String uniqueConstraint) {
		this.uniqueConstraint = uniqueConstraint;
	}
	
	@Length(min=0, max=4000, message="删除约束长度必须介于 0 和 4000 之间")
	public String getDeletingConstraint() {
		return deletingConstraint;
	}

	public void setDeletingConstraint(String deletingConstraint) {
		this.deletingConstraint = deletingConstraint;
	}
	
}