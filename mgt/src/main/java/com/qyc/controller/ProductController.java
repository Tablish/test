package com.qyc.controller;

import com.qyc.model.data.Product;
import com.qyc.service.biz.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author qianyongchao
 * @Description
 * @Date 2019/5/9 10:20
 * @Modified By
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class ProductController {
    @Autowired
    private ProductService productService;
    //说明了什么问题：现在是不能调用其他模块的bean,一引入就出问题！

    @RequestMapping("/djh")
    public String show() {
        //System.out.println("hello world");
        //productService.showInfo();
        return "家华你好呀";
    }

    @RequestMapping("/zyn")
    public String zyn(ModelAndView modelAndView) {
        //System.out.println("hello world");
        //productService.showInfo();
        //return "周亚男同学，六一快乐！";

        modelAndView.setViewName("zyn");
        //return modelAndView;

        return "周亚男同学，六一快乐！";

    }

    @RequestMapping("/getProductList")
    public List<Product> getProductList() {
        System.out.println("获取套餐列表");
        return productService.getProductList();
    }
}