package com.qyc.config;

import com.qyc.Interceptors.OneInterceptor;
import com.qyc.Interceptors.TwoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        //oneInterceptor 针对 oneController
        //twoInterceptor 针对 twoController
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**").addPathPatterns("/two/**");
        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**");

        //oneInterceptor 针对所有controller
        //registry.addInterceptor(new OneInterceptor()).addPathPatterns("/*/**");

    }

}
