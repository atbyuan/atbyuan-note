package org.atbyuan.note.nacos.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

import static org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME;

/**
 * @author atbyuan
 * @since 2022/2/25 18:42
 **/
@Slf4j
@Service
public class ReloadConfigService {

    @Resource
    private WebMvcProperties webMvcProperties;
    @Resource
    private DispatcherServlet dispatcherServlet;

    public DispatcherServletRegistrationBean dispatcherServletRegistration(MultipartConfigElement multipartConfigElement) {
        DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet, this.webMvcProperties.getServlet().getPath());
        registration.setName(DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
        registration.setLoadOnStartup(this.webMvcProperties.getServlet().getLoadOnStartup());
        registration.setMultipartConfig(multipartConfigElement);
        return registration;
    }

}
