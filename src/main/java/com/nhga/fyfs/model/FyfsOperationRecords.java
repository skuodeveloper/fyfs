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
public class FyfsOperationRecords implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("ProcessInstanceId")
    private String ProcessInstanceId;

    private String operationRecordsId;

    private String userid;

    private Date udate;

    /**
     * 操作类型，分为

EXECUTE_TASK_NORMAL（正常执行任务），EXECUTE_TASK_AGENT（代理人执行任务），APPEND_TASK_BEFORE（前加签任务），APPEND_TASK_AFTER（后加签任务），REDIRECT_TASK（转交任务），START_PROCESS_INSTANCE（发起流程实例），TERMINATE_PROCESS_INSTANCE（终止(撤销)流程实例），FINISH_PROCESS_INSTANCE（结束流程实例），ADD_REMARK（添加评论）
     */
    private String operationType;

    /**
     * 操作结果，分为
        AGREE（同意），REFUSE（拒绝）
     */
    private String operationResult;

    /**
     * 评论内容。审批操作附带评论时才返回该字段
     */
    private String remark;

    private String sign;


    public String getProcessInstanceId() {
        return ProcessInstanceId;
    }

    public void setProcessInstanceId(String ProcessInstanceId) {
        this.ProcessInstanceId = ProcessInstanceId;
    }

    public String getOperationRecordsId() {
        return operationRecordsId;
    }

    public void setOperationRecordsId(String operationRecordsId) {
        this.operationRecordsId = operationRecordsId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "FyfsOperationRecords{" +
        "ProcessInstanceId=" + ProcessInstanceId +
        ", operationRecordsId=" + operationRecordsId +
        ", userid=" + userid +
        ", udate=" + udate +
        ", operationType=" + operationType +
        ", operationResult=" + operationResult +
        ", remark=" + remark +
        ", sign=" + sign +
        "}";
    }
}
