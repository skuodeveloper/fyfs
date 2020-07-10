package com.nhga.fyfs.model;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alpha
 * @since 2020-04-16
 */
public class FyfsFormComponentValues implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("ProcessInstanceId")
    private String ProcessInstanceId;

    /**
     * form表单id
     */
    private String formComponentId;

    /**
     * 标签名
     */
    private String name;

    /**
     * 	
标签值
     */
    private String value;

    /**
     * 	
标签扩展值
     */
    private String extValue;


    public String getProcessInstanceId() {
        return ProcessInstanceId;
    }

    public void setProcessInstanceId(String ProcessInstanceId) {
        this.ProcessInstanceId = ProcessInstanceId;
    }

    public String getFormComponentId() {
        return formComponentId;
    }

    public void setFormComponentId(String formComponentId) {
        this.formComponentId = formComponentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExtValue() {
        return extValue;
    }

    public void setExtValue(String extValue) {
        this.extValue = extValue;
    }

    @Override
    public String toString() {
        return "FyfsFormComponentValues{" +
        "ProcessInstanceId=" + ProcessInstanceId +
        ", formComponentId=" + formComponentId +
        ", name=" + name +
        ", value=" + value +
        ", extValue=" + extValue +
        "}";
    }
}
