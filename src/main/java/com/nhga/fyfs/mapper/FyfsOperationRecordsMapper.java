package com.nhga.fyfs.mapper;

import com.nhga.fyfs.model.FyfsOperationRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alpha
 * @since 2020-04-16
 */
public interface FyfsOperationRecordsMapper extends BaseMapper<FyfsOperationRecords> {
    void addOperationRecords(FyfsOperationRecords operationRecords);
}
