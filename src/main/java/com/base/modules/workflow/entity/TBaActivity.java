/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.workflow.entity;

import org.hibernate.validator.constraints.Length;
import com.base.modules.sys.entity.Office;
import com.base.modules.sys.entity.User;
import java.util.List;
import com.google.common.collect.Lists;

import com.base.common.persistence.DataEntity;

/**
 * 流程实例Entity
 * @author zhangxudong
 * @version 2015-09-01
 */
public class TBaActivity extends DataEntity<TBaActivity> {
	
	private static final long serialVersionUID = 1L;
	private String tempActivityId; // 流程定义ID
	private String applyCode;	   // 申请编号
	private String applyState;	   // 申请状态 01未提交 02 已提交未处理 03 已处理04已办结05补充补正06退回07不予受理08审批不通过(09申请成功)(没用的)10申请审核11审核退回12流程终止(手动终止)13流程终止（自动终止）
	                               //20机构申请终止
	private String applyId;		   // 申请ID
	private String status;		   // 流程实例状态:1运行中2结束3终止4暂停
	private String reviewAdvice;   // 审批结果
	private Office company;		   // 组织机构ID
	private String orgName;		   // 机构名称
	private User user;		       // 机构ID
	private List<TBaNote> tBaNoteList = Lists.newArrayList();		// 子表列表
	private String activityName;   // 流程定义名称
	private String terminationState;//终止状态（0机构终止提交1行业评审组确认2认监委确认）
	private String terminationReason;//终止原因
	
	public TBaActivity() {
		super();
	}

	public TBaActivity(String id){
		super(id);
	}

	@Length(min=0, max=40, message="流程定义ID长度必须介于 0 和 40 之间")
	public String getTempActivityId() {
		return tempActivityId;
	}

	public void setTempActivityId(String tempActivityId) {
		this.tempActivityId = tempActivityId;
	}
	
	@Length(min=0, max=40, message="申请编号长度必须介于 0 和 40 之间")
	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}
	
	@Length(min=0, max=2, message="申请状态长度必须介于 0 和 2 之间")
	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	
	@Length(min=0, max=40, message="申请ID长度必须介于 0 和 40 之间")
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@Length(min=0, max=10, message="流程实例状态:1运行中2结束3终止4暂停长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="审批结果长度必须介于 0 和 2 之间")
	public String getReviewAdvice() {
		return reviewAdvice;
	}

	public void setReviewAdvice(String reviewAdvice) {
		this.reviewAdvice = reviewAdvice;
	}
	
	public Office getCompany() {
        return company;
    }

    public void setCompany(Office company) {
        this.company = company;
    }

    public List<TBaNote> gettBaNoteList() {
        return tBaNoteList;
    }

    public void settBaNoteList(List<TBaNote> tBaNoteList) {
        this.tBaNoteList = tBaNoteList;
    }

    @Length(min=0, max=200, message="机构名称长度必须介于 0 和 200 之间")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<TBaNote> getTBaNoteList() {
		return tBaNoteList;
	}

	public void setTBaNoteList(List<TBaNote> tBaNoteList) {
		this.tBaNoteList = tBaNoteList;
	}

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

	public String getTerminationState()
	{
		return terminationState;
	}

	public void setTerminationState(String terminationState)
	{
		this.terminationState = terminationState;
	}

	public String getTerminationReason()
	{
		return terminationReason;
	}

	public void setTerminationReason(String terminationReason)
	{
		this.terminationReason = terminationReason;
	}
}