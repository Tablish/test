package com.qyc.controller;

import com.qyc.service.largeData.LargeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author qianyongchao
 * @date 2019/6/4
 */
@Controller
@RequestMapping("/largeData")
public class LargeDataController {

    @Autowired
    private LargeDataService largeDataService;


    @RequestMapping("100")
    public ModelAndView lartTest(ModelAndView modelAndView) throws IOException {
        largeDataService.exportBigDataExcel("e:/poiSXXFSBigData.xlsx");
        return modelAndView;
    }
}
