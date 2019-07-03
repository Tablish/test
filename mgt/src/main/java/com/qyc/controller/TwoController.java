package com.qyc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 拦截器实验
 */
@RequestMapping("two")
@RestController
public class TwoController {

    @RequestMapping("/test")
    public void test() {
        System.out.println("调用twoController");
    }
}
