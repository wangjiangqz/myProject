/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.workflow.entity;

import com.google.common.collect.Lists;
import com.base.common.persistence.DataEntity;
import com.base.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 任务记录Entity
 * @author 张旭东
 * @version 2015-09-01
 */
public class TBaTask extends DataEntity<TBaTask> {
	
	private static final long serialVersionUID = 1L;
	private String noteId;		   // 节点ID
	private String activityId;	   // 流程ID
	private String noteName;	   // 节点名称
	private String dualOpinion;    // 处理意见  ()
	private String opinionContent; // 意见说明
	private String updateName;	   // 处理人姓名
	private String createName;	   // 上一步处理人姓名
	private String attIds;		   // 附件ID
	private String status;         // 节点状态1未处理2已完成
	private String isSend;         // 是否发送短信
	private String sendContent;    // 短信内容
	private String dualType;//处理类型
	private String lastTaskId;//上一步任务任务ID
	private String nextTempNoteId;//下一节点的模板ID
	private String nextPerson;//下一处理人
	private List<User> userList = Lists.newArrayList();
	
	private String standy;
	private String standy1;
	private String fileName;
	private String tempNoteId;
	private String standy2;
	private Date standy3;
	private String standy4;
	private String certBelong; // 申请所属部门

	private String priority; //权重
	private String isFileShow;//附表公示：1是 2否
	private String formerTransactor;//原办理人，如果未设置代理人和办理人一致

	
	public TBaTask() {
		super();
	}
	
	public TBaTask(String id){
		super(id);
	}

	@Length(min=0, max=40, message="节点ID长度必须介于 0 和 40 之间")
	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	
	@Length(min=0, max=40, message="流程ID长度必须介于 0 和 40 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	@Length(min=0, max=40, message="节点名称长度必须介于 0 和 40 之间")
	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
	@Length(min=0, max=2, message="处理意见长度必须介于 0 和 2 之间")
	public String getDualOpinion() {
		return dualOpinion;
	}

	public void setDualOpinion(String dualOpinion) {
		this.dualOpinion = dualOpinion;
	}
	
	@Length(min=0, max=1000, message="意见说明长度必须介于 0 和 1000 之间")
	public String getOpinionContent() {
		return opinionContent;
	}

	public void setOpinionContent(String opinionContent) {
		this.opinionContent = opinionContent;
	}
	
	@Length(min=0, max=200, message="处理人姓名长度必须介于 0 和 200 之间")
	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
	@Length(min=0, max=200, message="上一步处理人姓名长度必须介于 0 和 200 之间")
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	@Length(min=0, max=200, message="附件ID长度必须介于 0 和 200 之间")
	public String getAttIds() {
		return attIds;
	}

	public void setAttIds(String attIds) {
		this.attIds = attIds;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

	public String getDualType() {
		return dualType;
	}

	public void setDualType(String dualType) {
		this.dualType = dualType;
	}

	public String getLastTaskId() {
		return lastTaskId;
	}

	public void setLastTaskId(String lastTaskId) {
		this.lastTaskId = lastTaskId;
	}

	public String getNextTempNoteId() {
		return nextTempNoteId;
	}

	public void setNextTempNoteId(String nextTempNoteId) {
		this.nextTempNoteId = nextTempNoteId;
	}

	public String getNextPerson() {
		return nextPerson;
	}

	public void setNextPerson(String nextPerson) {
		this.nextPerson = nextPerson;
	}

    public String getStandy() {
        return standy;
    }

    public void setStandy(String standy) {
        this.standy = standy;
    }

    public String getStandy1() {
        return standy1;
    }

    public void setStandy1(String standy1) {
        this.standy1 = standy1;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTempNoteId() {
		return tempNoteId;
	}

	public void setTempNoteId(String tempNoteId) {
		this.tempNoteId = tempNoteId;
	}

    public String getStandy2() {
        return standy2;
    }

    public void setStandy2(String standy2) {
        this.standy2 = standy2;
    }

    public Date getStandy3() {
        return standy3;
    }

    public void setStandy3(Date standy3) {
        this.standy3 = standy3;
    }

 
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStandy4() {
		return standy4;
	}

	public void setStandy4(String standy4) {
		this.standy4 = standy4;
	}

	public String getIsFileShow() {
		return isFileShow;
	}

	public void setIsFileShow(String isFileShow) {
		this.isFileShow = isFileShow;
	}

	public String getFormerTransactor()
	{
		return formerTransactor;
	}

	public void setFormerTransactor(String formerTransactor)
	{
		this.formerTransactor = formerTransactor;
	}

	public String getCertBelong() {
		return certBelong;
	}

	public void setCertBelong(String certBelong) {
		this.certBelong = certBelong;
	}
}