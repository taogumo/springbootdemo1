package com.soft863.config;

import com.soft863.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class MVCConfig extends WebMvcConfigurationSupport {

    /**
     * 为拦截器增加
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        // 可以添加多个拦截器组成一个拦截器链
        registry.addInterceptor (new LoginInterceptor ())
                // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
                .addPathPatterns ("/**")
                // excludePathPatterns 用户排除拦截
                .excludePathPatterns ("/hi", "/user/query");

        super.addInterceptors (registry);
    }


    /*
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("main");
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
            super.addViewControllers(registry);
        }*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler ("/**")
                .addResourceLocations ("classpath:/META-INF/resources/")
                .addResourceLocations ("classpath:/resources/")
                .addResourceLocations ("classpath:/static/")
                .addResourceLocations ("classpath:/web/")
                .addResourceLocations ("classpath:/public/");
        super.addResourceHandlers (registry);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter (Charset.forName("UTF-8"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
        // 这里必须加上加载默认转换器，不然bug玩死人，并且该bug目前在网络上似乎没有解决方案
        // 百度，谷歌，各大论坛等。你可以试试去掉。
        addDefaultHttpMessageConverters(converters);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
