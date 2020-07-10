package com.nhga.fyfs.mapper;

import com.nhga.fyfs.model.FyfsProcessInstance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alpha
 * @since 2020-04-16
 */
public interface FyfsProcessInstanceMapper extends BaseMapper<FyfsProcessInstance> {
    int addProcess(FyfsProcessInstance instance);
    int updProcess(FyfsProcessInstance instance);
}
