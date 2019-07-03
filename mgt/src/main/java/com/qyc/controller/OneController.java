package com.qyc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 拦截器实验
 */
@RequestMapping("one")
@RestController
public class OneController {

    @RequestMapping("/test")
    public void test() {
        System.out.println("调用oneController");
    }
}
