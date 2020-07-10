package com.nhga.fyfs.schdule;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.request.OapiProcessinstanceListidsRequest;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.dingtalk.api.response.OapiProcessinstanceListidsResponse;
import com.nhga.fyfs.config.Constant;
import com.nhga.fyfs.config.URLConstant;
import com.nhga.fyfs.mapper.FyfsFormComponentValuesMapper;
import com.nhga.fyfs.mapper.FyfsOperationRecordsMapper;
import com.nhga.fyfs.mapper.FyfsProcessInstanceMapper;
import com.nhga.fyfs.model.FyfsFormComponentValues;
import com.nhga.fyfs.model.FyfsOperationRecords;
import com.nhga.fyfs.model.FyfsProcessInstance;
import com.nhga.fyfs.util.AccessTokenUtil;
import com.nhga.fyfs.util.ImageUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
public class ScheduledConfig {
    @Resource
    private FyfsProcessInstanceMapper instanseMapper;
    @Resource
    private FyfsFormComponentValuesMapper formValuesMapper;
    @Resource
    private FyfsOperationRecordsMapper operationRecordsMapper;

//    @Scheduled(cron = "0 0 0/6 * * *")
    @Scheduled(fixedDelay = 10)
    public void schedule_1() {
        try {
            long cusor = 0;

            String str = "2020-07-10 00:00:00";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(str);
            long ts = date.getTime();

            Long startTime = ts - TimeUnit.DAYS.toMillis(22);

            for (; ; ) {
                String accessToken = AccessTokenUtil.getToken();
                OapiProcessinstanceListidsResponse response = getList(accessToken, cusor, startTime);
                if (response == null) {
                    break;
                }

                if (response.getErrcode() == 0) {
                    for (String ids : response.getResult().getList()) {
                        OapiProcessinstanceGetResponse info = getInfo(ids, accessToken);
                        if (info != null) {
                            FyfsProcessInstance instance = new FyfsProcessInstance();
                            instance.setProcessInstanceId(ids);
                            instance.setTitle(info.getProcessInstance().getTitle());
                            instance.setCreateTime(info.getProcessInstance().getCreateTime());
                            instance.setFinishTime(info.getProcessInstance().getFinishTime());
                            instance.setRwTime(new Date());
                            instance.setOriginatorUserid(info.getProcessInstance().getOriginatorUserid());
                            instance.setOriginatorDeptId(info.getProcessInstance().getOriginatorDeptId());
                            instance.setOriginatorDeptName(info.getProcessInstance().getOriginatorDeptName());
                            instance.setStatus(info.getProcessInstance().getStatus());
                            String ccUserids = StringUtils.join(info.getProcessInstance().getCcUserids(), ',');
                            instance.setCcUserids(ccUserids);
                            instance.setBusinessId(info.getProcessInstance().getBusinessId());
                            instance.setResult(info.getProcessInstance().getResult());
                            instance.setBizAction(info.getProcessInstance().getBizAction());
                            String attachedProcessInstanceIds = StringUtils.join(info.getProcessInstance().getAttachedProcessInstanceIds(), ',');
                            instance.setAttachedProcessInstanceIds(attachedProcessInstanceIds);

                            //查询主表ids是否已存在
                            FyfsProcessInstance fyfs = instanseMapper.selectById(ids);
                            if (fyfs == null) {
                                //插入主表
//                                int s = instanseMapper.insert(instance);
                                int s = instanseMapper.addProcess(instance);
                                if (s > 0) {
                                    /********************************RECORD表********************************/
                                    List<OapiProcessinstanceGetResponse.OperationRecordsVo> operationRecords = info.getProcessInstance().getOperationRecords();
                                    insertOperation(ids, operationRecords);

                                    /********************************FORM表********************************/
                                    List<OapiProcessinstanceGetResponse.FormComponentValueVo> formValues = info.getProcessInstance().getFormComponentValues();
                                    insertFormValue(ids, formValues);
                                }
                            } else {
//                                int s = instanseMapper.updateById(instance);
                                int s = instanseMapper.updProcess(instance);
                                if (s > 0) {
                                    /********************************RECORD表********************************/
                                    List<OapiProcessinstanceGetResponse.OperationRecordsVo> operationRecords = info.getProcessInstance().getOperationRecords();
                                    insertOperation(ids, operationRecords);
                                }
                            }
                        }
                    }
                }

                //一直循环直到最后
                if (response.getResult() == null || response.getResult().getNextCursor() == null) {
                    break;
                } else {
                    cusor = response.getResult().getNextCursor();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void insertFormValue(String ids, List<OapiProcessinstanceGetResponse.FormComponentValueVo> formValues) {
        formValues.forEach(u -> {
            FyfsFormComponentValues formValue = new FyfsFormComponentValues();
            formValue.setProcessInstanceId(ids);
            String formComponentId = RandomStringUtils.randomAlphanumeric(10);
            formValue.setFormComponentId(formComponentId);
            formValue.setName(u.getName());
//            formValue.setExtValue(u.getExtValue());

            if (!StringUtils.isEmpty(u.getName())) {
                if (u.getName().contains("上传宣传照片") && !StringUtils.isEmpty(u.getValue())) {
                    List<String> newList = new ArrayList<>();
                    List<String> list = JSONObject.parseArray(u.getValue(), String.class);
                    if(list != null) {
                        list.forEach(k -> {
                            int length = k.length();
                            if (length > 3) {
                                String suffix = k.substring(length - 3, length);
                                String base64 = ImageUtils.getBase64ByImgUrl(k);
                                String pic = String.format("data:image/%s;base64,%s", suffix, base64);
                                if (!StringUtils.isEmpty(base64)) {
                                    newList.add(pic);
                                }
                            }
                        });

                        String jsonValue = JSONObject.toJSONString(newList);
                        if (!StringUtils.isEmpty(jsonValue)) {
                            formValue.setValue(jsonValue);
                        }
                    }
                } else {
                    formValue.setValue(u.getValue());
                }
            } else {
                formValue.setValue(u.getValue());
            }

//            formValuesMapper.insert(formValue);
            formValuesMapper.addFormValues(formValue);
        });
    }

    private void insertOperation(String ids, List<OapiProcessinstanceGetResponse.OperationRecordsVo> operationRecords) {
        operationRecords.forEach(p -> {
            FyfsOperationRecords operationRecords1 = new FyfsOperationRecords();
            operationRecords1.setProcessInstanceId(ids);
            String operationRecordsId = RandomStringUtils.randomAlphanumeric(10);
            operationRecords1.setOperationRecordsId(operationRecordsId);
            operationRecords1.setUserid(p.getUserid());
            operationRecords1.setUdate(p.getDate());
            operationRecords1.setOperationType(p.getOperationType());
            operationRecords1.setOperationResult(p.getOperationResult());
            operationRecords1.setRemark(p.getRemark());

            int cnt = operationRecordsMapper.selectCount(new QueryWrapper<FyfsOperationRecords>()
                    .eq("ProcessInstanceId", ids)
                    .eq("userid", p.getUserid())
                    .eq("udate", p.getDate())
                    .eq("operation_type", p.getOperationType())
                    .eq("operation_result", p.getOperationResult()));
            if (cnt == 0) {
//                operationRecordsMapper.insert(operationRecords1);
                operationRecordsMapper.addOperationRecords(operationRecords1);
            }
        });
    }

    /**
     * 获取审批实例列表
     *
     * @param accessToken
     * @return
     */
    private OapiProcessinstanceListidsResponse getList(String accessToken, Long cursor, Long startTime) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_APPROVAL_LIST);
            OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
            req.setProcessCode(Constant.PROCESS_CODE);
            req.setStartTime(startTime);
            req.setEndTime(System.currentTimeMillis());
            req.setSize(10L);
            req.setCursor(cursor);
            return client.execute(req, accessToken);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * 获取审批实例信息
     *
     * @param ids
     * @param accessToken
     * @return
     */
    private OapiProcessinstanceGetResponse getInfo(String ids, String accessToken) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_APPROVAL_INFO);
            OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
            request.setProcessInstanceId(ids);
            return client.execute(request, accessToken);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
