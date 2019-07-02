package com.qyc.service.largeData.impl;

import com.qyc.mapper.largeData.LargeDataMapper;
import com.qyc.model.largeData.ValuedataBean;
import com.qyc.service.largeData.LargeDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author qianyongchao
 * @date 2019/6/3
 */
@Service
@Slf4j
public class LargeDataServiceImpl implements LargeDataService {
    @Autowired
    private LargeDataMapper largeDataMapper;


    @Override
    public int getTotalCount() {
        return largeDataMapper.getTotalCount();
    }

    /**
     * limit 两个参数，分批次拿值
     *
     * @param i
     * @param page_size
     * @return
     */
    @Override
    public List<ValuedataBean> getDataByTimes(int i, int page_size) {
        return largeDataMapper.getDataByTimes(i * page_size, page_size);
    }

    @Override
    public void exportBigDataExcel(String path) throws IOException {
        // 最重要的就是使用SXSSFWorkbook，表示流的方式进行操作
        // 在内存中保持100行，超过100行将被刷新到磁盘
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet(); // 建立新的sheet对象
        Row row = sh.createRow(0);   // 创建第一行对象
        // -----------定义表头-----------
        Cell cel0 = row.createCell(0);
        cel0.setCellValue("1");
        Cell cel2 = row.createCell(1);
        cel2.setCellValue("2");
        Cell cel3 = row.createCell(2);
        cel3.setCellValue("3");
        Cell cel4 = row.createCell(3);

        long start_time = System.currentTimeMillis();
        log.info("start time：" + start_time);

        List<ValuedataBean> list;
        // 数据库中存储的数据行
        int page_size = 5000;
        // 求数据库中待导出数据的行数
        int list_count = largeDataMapper.getTotalCount();

        // 根据行数求数据提取次数
        int export_times = list_count % page_size > 0 ? list_count / page_size
                + 1 : list_count / page_size;
        // 按次数将数据写入文件
        for (int j = 0; j < export_times; j++) {

            list = largeDataMapper.getDataByTimes((j * page_size) + 1, page_size);

            int len = list.size() < page_size ? list.size() : page_size;
            for (int i = 0; i < len; i++) {
                Row row_value = sh.createRow(j * page_size + i + 1);
                Cell cel0_value = row_value.createCell(0);
                cel0_value.setCellValue(list.get(i).getId());
                Cell cel2_value = row_value.createCell(1);
                cel2_value.setCellValue(list.get(i).getBreast());
                Cell cel3_value = row_value.createCell(2);
                cel3_value.setCellValue(list.get(i).getNegative());
            }
            list.clear(); // 每次存储len行，用完了将内容清空，以便内存可重复利用
        }

        long end_time = System.currentTimeMillis();
        log.info("end time：", end_time);

        FileOutputStream fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.close();
        wb.dispose();

        log.info("耗时：" + (end_time - start_time));
    }
}