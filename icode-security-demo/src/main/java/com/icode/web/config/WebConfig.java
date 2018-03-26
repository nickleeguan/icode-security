package com.icode.web.config;


import com.icode.web.filter.TimeFilter;
import com.icode.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
    }

    /**
     * 异步配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        拦截器配置
        configurer.registerCallableInterceptors();
        configurer.registerDeferredResultInterceptors();
//        设置超时时间
        configurer.setDefaultTimeout(2000);

//        configurer.setTaskExecutor();设置特定线程池
    }

    /**
     * 过滤器配置
     * @return
     */
    //@Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();

        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();

        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }
}
