package com.qyc.utils;

import com.qyc.model.largeData.ValuedataBean;
import com.qyc.service.largeData.LargeDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qianyongchao
 * @date 2019/6/3
 */
@Slf4j
public class LargeDataUtils {
    @Autowired
    private LargeDataService largeDataService;


    /**
     * 几百万级别数据的导出
     */
//    public static void jdbcex(boolean isClose) throws InstantiationException, IllegalAccessException,
//            ClassNotFoundException, SQLException, IOException, InterruptedException {
//
//        String xlsFile = "e:/poiSXXFSBigData.xlsx";        //输出文件
//        //内存中只创建100个对象，写临时文件，当超过100条，就将内存中不用的对象释放。
//        Workbook wb = new SXSSFWorkbook(100);//关键语句
//        Sheet sheet = wb.createSheet(); //工作表对象
//
//        //Sheet sheet = null;
//        Row nRow = null;        //行对象
//        Cell nCell = null;        //列对象
//
//        //使用jdbc链接数据库
//        Class.forName("com.mysql.jdbc.Driver").newInstance();
//        String url = "jdbc:mysql://39.98.47.236:3306/saotun_db?characterEncoding=UTF-8";
//        String user = "root";
//        String password = "Qyc5013865812073.";
//        //获取数据库连接
//        Connection conn = DriverManager.getConnection(url, user, password);
//        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        String sql = "select count(*) from hpa_normal_tissue";   //100万测试数据
//        ResultSet rs = stmt.executeQuery(sql);          //所有结果
//        ResultSetMetaData rsmd = rs.getMetaData();      //元数据
//
//        long startTime = System.currentTimeMillis();    //开始时间
//        log.info("strat execute time: " + startTime);
//
//        int rowNo = 0;        //总行号
//        int pageRowNo = 0;    //页行号
//
//        //每超过300000条，重新建立一个sheet
//        while (rs.next()) {
//            //打印300000条后切换到下个工作表，可根据需要自行拓展，2百万，3百万...数据一样操作，只要不超过1048576就可以
//            if (rowNo % 300000 == 0) {
//                log.info("Current Sheet:" + rowNo / 300000);
//                sheet = wb.createSheet("我的第" + (rowNo / 300000) + "个工作簿");//建立新的sheet对象
//                sheet = wb.getSheetAt(rowNo / 300000);        //动态指定当前的工作表
//                pageRowNo = 0;        //每当新建了工作表就将当前工作表的行号重置为0
//            }
//            rowNo++;
//
//            nRow = sheet.createRow(pageRowNo++);    //新建行对象
//
//            // 打印每行，每行有6列数据   rsmd.getColumnCount()==6 --- 列属性的个数
//            for (int j = 0; j < rsmd.getColumnCount(); j++) {
//                nCell = nRow.createCell(j);
//                nCell.setCellValue(rs.getString(j + 1));
//            }
//
//            if (rowNo % 10000 == 0) {
//                log.info("row no: " + rowNo);
//            }
//            //		Thread.sleep(1);	//休息一下，防止对CPU占用，其实影响不大
//        }
//
//        long finishedTime = System.currentTimeMillis();    //处理完成时间
//        log.info("finished execute  time: " + (finishedTime - startTime) / 1000 + "m");
//
//        FileOutputStream fOut = new FileOutputStream(xlsFile);
//        wb.write(fOut);
//        fOut.flush();        //刷新缓冲区
//        fOut.close();
//
//        long stopTime = System.currentTimeMillis();        //写文件时间
//        log.info("write xlsx file time: " + (stopTime - startTime) / 1000 + "m");
//
//        if (isClose) {
//            close(rs, stmt, conn);
//        }
//    }
//
//    //执行关闭流的操作
//    private static void close(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
//        rs.close();
//        stmt.close();
//        conn.close();
//    }


    /**
     * 100万条数据的导出
     */
//    public void exportBigDataExcel(String path) throws IOException {
//        // 最重要的就是使用SXSSFWorkbook，表示流的方式进行操作
//        // 在内存中保持100行，超过100行将被刷新到磁盘
//        SXSSFWorkbook wb = new SXSSFWorkbook(100);
//        Sheet sh = wb.createSheet(); // 建立新的sheet对象
//        Row row = sh.createRow(0);   // 创建第一行对象
//        // -----------定义表头-----------
//        Cell cel0 = row.createCell(0);
//        cel0.setCellValue("1");
//        Cell cel2 = row.createCell(1);
//        cel2.setCellValue("2");
//        Cell cel3 = row.createCell(2);
//        cel3.setCellValue("3");
//        Cell cel4 = row.createCell(3);
//        // ---------------------------
//        List<ValuedataBean> list = null;
//        // 数据库中存储的数据行
//        int page_size = 10000;
//        // 求数据库中待导出数据的行数
//        int list_count = largeDataService.getTotalCount();
//
//        // 根据行数求数据提取次数
//        int export_times = list_count % page_size > 0 ? list_count / page_size
//                + 1 : list_count / page_size;
//        // 按次数将数据写入文件
//        for (int j = 0; j < export_times; j++) {
//            System.out.println("start time: "+new Date());
//            list = largeDataService.getDataByTimes(j + 1, page_size);
//            System.out.println("end time: "+new Date());
//            int len = list.size() < page_size ? list.size() : page_size;
//            for (int i = 0; i < len; i++) {
//                Row row_value = sh.createRow(j * page_size + i + 1);
//                Cell cel0_value = row_value.createCell(0);
//                cel0_value.setCellValue(list.get(i).getId());
//                Cell cel2_value = row_value.createCell(1);
//                cel2_value.setCellValue(list.get(i).getBreast());
//                Cell cel3_value = row_value.createCell(2);
//                cel3_value.setCellValue(list.get(i).getNegative());
//            }
//            list.clear(); // 每次存储len行，用完了将内容清空，以便内存可重复利用
//        }
//        FileOutputStream fileOut = new FileOutputStream(path);
//        wb.write(fileOut);
//        fileOut.close();
//        wb.dispose();
//    }
}