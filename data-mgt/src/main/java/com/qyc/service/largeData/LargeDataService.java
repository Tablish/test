package com.qyc.service.largeData;

import com.qyc.model.largeData.ValuedataBean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface LargeDataService {
    /**
     * 获取总条数
     *
     * @return
     */
    int getTotalCount();

    /**
     * limit 两个参数，分批次拿值
     *
     * @param i
     * @param page_size
     * @return
     */
    List<ValuedataBean> getDataByTimes(int i, int page_size);

    /**
     * 导出数据到指定位置
     */
    void exportBigDataExcel(String path) throws IOException;
}
