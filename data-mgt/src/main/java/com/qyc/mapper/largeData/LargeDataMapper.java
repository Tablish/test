package com.qyc.mapper.largeData;


import com.qyc.model.largeData.ValuedataBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 大量数据的测试
 */
public interface LargeDataMapper {

    /**
     * 获取总数据条数
     *
     * @return 总条数
     */
    int getTotalCount();

    /**
     * limit 两个参数，分批次拿值
     *
     * @param time
     * @param page_size
     * @return
     */
    List<ValuedataBean> getDataByTimes(@Param("time") int time, @Param("pageSize") int page_size);

    /**
     * 前10000条数据
     *
     * @return
     */
    List<ValuedataBean> getPartData();
}
