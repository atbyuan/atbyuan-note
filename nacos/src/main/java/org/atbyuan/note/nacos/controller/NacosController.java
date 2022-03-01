package org.atbyuan.note.nacos.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.atbyuan.note.nacos.config.NacosAutoConfigProperties;
import org.atbyuan.note.nacos.config.NacosBean;
import org.atbyuan.note.nacos.service.ReloadConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * @author atbyuan
 * @since 2022/1/24 9:38
 **/
@RestController
@RequestMapping("/nacos")
@RefreshScope
@Slf4j
public class NacosController {

    @Resource
    private NacosAutoConfigProperties nacosAutoConfigProperties;
    @Resource
    private NacosBean nacosBean;

    @Value("${nacos.value}")
    private String nacosValue;

    @GetMapping("/print")
    public String print() {
        return nacosAutoConfigProperties.toString();
    }

    @GetMapping("/nacosBean")
    public String nacosBean() {
        return nacosBean.getNacosAutoConfigProperties().toString();
    }

    @GetMapping("/nacosValue")
    public String nacosValue() {
        log.info("nacosValue: {}", nacosValue);
        return nacosValue;
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        return "success";
    }

    @Resource
    private MultipartProperties multipartProperties;
    @Resource
    private MultipartAutoConfiguration multipartAutoConfiguration;
    @Resource
    private MultipartConfigElement multipartConfigElement;
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private ReloadConfigService reloadConfigService;

    @GetMapping("/info")
    public void info() {
        log.info("multipartProperties: {}, maxFileSize:{}", JSON.toJSONString(multipartProperties), multipartProperties.getMaxFileSize().toString());
        log.info("multipartAutoConfiguration: {}", JSON.toJSONString(multipartAutoConfiguration));
        log.info("multipartConfigElement: {}", JSON.toJSONString(multipartConfigElement));
    }

    @GetMapping("/reload")
    public void reload() {
        //获取上下文
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        //销毁指定实例
        defaultListableBeanFactory.destroySingleton("multipartConfigElement");
        MultipartConfigElement configElement = defaultListableBeanFactory.getBean("multipartConfigElement", MultipartConfigElement.class);
        log.info("configElement: {}", JSON.toJSONString(configElement));

        defaultListableBeanFactory.destroySingleton("dispatcherServletRegistrationBean");
        DispatcherServletRegistrationBean dispatcherServletRegistrationBean = reloadConfigService.dispatcherServletRegistration(configElement);
        defaultListableBeanFactory.registerSingleton("dispatcherServletRegistrationBean", dispatcherServletRegistrationBean);
        log.info("dispatcherServletRegistrationBean.multipartConfigElement: {}", JSON.toJSONString(dispatcherServletRegistrationBean.getMultipartConfig()));
    }

}
