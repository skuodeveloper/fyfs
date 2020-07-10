package com.nhga.fyfs.mapper;

import com.nhga.fyfs.model.FyfsFormComponentValues;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alpha
 * @since 2020-04-16
 */
public interface FyfsFormComponentValuesMapper extends BaseMapper<FyfsFormComponentValues> {
    void addFormValues(FyfsFormComponentValues formValues);
    void updFormValues(FyfsFormComponentValues formValues);
}
