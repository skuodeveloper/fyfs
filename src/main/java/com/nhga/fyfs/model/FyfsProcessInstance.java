package com.nhga.fyfs.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alpha
 * @since 2020-04-16
 */
public class FyfsProcessInstance implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 审批实例ID
     */
    @TableId("ProcessInstanceId")
    private String ProcessInstanceId;

    /**
     * 审批实例标题

审批实例标题

审批实例标题
     */
    private String title;

    /**
     * 开始时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date finishTime;

    /**
     * 入网时间
     */
    private Date rwTime;

    /**
     * 发起人
     */
    private String originatorUserid;

    /**
     * 发起部门
     */
    private String originatorDeptId;

    /**
     * 部门
     */
    private String originatorDeptName;

    /**
     * 审批状态，分为

NEW（新创建）

RUNNING（运行中）

TERMINATED（被终止）

COMPLETED（完成）
     */
    private String status;

    /**
     * 抄送人(多人通过逗号隔开)。审批附带抄送人时才返回该字段。
     */
    private String ccUserids;

    /**
     * 	
审批实例业务编号
     */
    private String businessId;

    /**
     * 审批结果，分为 agree 和 refuse
     */
    private String result;

    /**
     * 审批实例业务动作，MODIFY表示该审批实例是基于原来的实例修改而来，REVOKE表示该审批实例对原来的实例进行撤销，NONE表示正常发起
     */
    private String bizAction;

    /**
     * 审批附属实例列表，当已经通过的审批实例被修改或撤销，会生成一个新的实例，作为原有审批实例的附属。如果想知道当前已经通过的审批实例的状态，可以依次遍历它的附属列表，查询里面每个实例的biz_action
     */
    private String attachedProcessInstanceIds;


    public String getProcessInstanceId() {
        return ProcessInstanceId;
    }

    public void setProcessInstanceId(String ProcessInstanceId) {
        this.ProcessInstanceId = ProcessInstanceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getRwTime() {
        return rwTime;
    }

    public void setRwTime(Date rwTime) {
        this.rwTime = rwTime;
    }

    public String getOriginatorUserid() {
        return originatorUserid;
    }

    public void setOriginatorUserid(String originatorUserid) {
        this.originatorUserid = originatorUserid;
    }

    public String getOriginatorDeptId() {
        return originatorDeptId;
    }

    public void setOriginatorDeptId(String originatorDeptId) {
        this.originatorDeptId = originatorDeptId;
    }

    public String getOriginatorDeptName() {
        return originatorDeptName;
    }

    public void setOriginatorDeptName(String originatorDeptName) {
        this.originatorDeptName = originatorDeptName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCcUserids() {
        return ccUserids;
    }

    public void setCcUserids(String ccUserids) {
        this.ccUserids = ccUserids;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBizAction() {
        return bizAction;
    }

    public void setBizAction(String bizAction) {
        this.bizAction = bizAction;
    }

    public String getAttachedProcessInstanceIds() {
        return attachedProcessInstanceIds;
    }

    public void setAttachedProcessInstanceIds(String attachedProcessInstanceIds) {
        this.attachedProcessInstanceIds = attachedProcessInstanceIds;
    }

    @Override
    public String toString() {
        return "FyfsProcessInstance{" +
        "ProcessInstanceId=" + ProcessInstanceId +
        ", title=" + title +
        ", createTime=" + createTime +
        ", finishTime=" + finishTime +
        ", rwTime=" + rwTime +
        ", originatorUserid=" + originatorUserid +
        ", originatorDeptId=" + originatorDeptId +
        ", originatorDeptName=" + originatorDeptName +
        ", status=" + status +
        ", ccUserids=" + ccUserids +
        ", businessId=" + businessId +
        ", result=" + result +
        ", bizAction=" + bizAction +
        ", attachedProcessInstanceIds=" + attachedProcessInstanceIds +
        "}";
    }
}
