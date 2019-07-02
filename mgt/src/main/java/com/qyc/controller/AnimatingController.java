package com.qyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author qianyongchao
 * @date 2019/5/22 23:33
 */
@RequestMapping("/")
@Controller
public class AnimatingController {

    /**
     * shoLove 动画
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("love")
    public ModelAndView showLove(ModelAndView modelAndView) {
        modelAndView.setViewName("show");
        return modelAndView;
    }
}
