package org.atbyuan.note.nacos.flow;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.note.common.constant.SpringConstant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.MultipartConfigElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author atbyuan
 * @since 2022/1/28 18:38
 */
@Slf4j
@Component
public class NacosListener implements ApplicationListener<RefreshEvent>, ApplicationContextAware, InitializingBean, Ordered {

    private ContextRefresher contextRefresher;
    private ApplicationContext applicationContext;

    private MultipartProperties multipartProperties;
    private MultipartAutoConfiguration multipartAutoConfiguration;

    @Bean
    public NacosListener actionApplicationListener(ContextRefresher contextRefresher) {
        return new NacosListener(contextRefresher);
    }

    public NacosListener(ContextRefresher refresh) {
        this.contextRefresher = refresh;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
    }

    @Override
    public void onApplicationEvent(RefreshEvent event) {
        ConfigurableEnvironment beforeEnv = (ConfigurableEnvironment) applicationContext.getEnvironment();
        MutablePropertySources propertySources = beforeEnv.getPropertySources();
        MutablePropertySources beforeSources = new MutablePropertySources(propertySources);
        // 刷新上下文
        Set<String> refresh = this.contextRefresher.refresh();
        // 获取对比值发布事件
        Map<String, HashMap> contrast = PropertyUtil.contrast(beforeSources, propertySources);

        if (contrast.containsKey(SpringConstant.SERVLET_MULTIPART_MAX_FILE_SIZE)) {
            Optional.ofNullable(contrast.get(SpringConstant.SERVLET_MULTIPART_MAX_FILE_SIZE))
                    .map(m -> m.get("after")).map(String::valueOf).ifPresent(maxFileSize -> {
                        log.info("文件上传大小更新：{}", maxFileSize);
                        log.info("multipartProperties.value:{}", multipartProperties);

                        multipartAutoConfiguration = new MultipartAutoConfiguration(multipartProperties);
                        MultipartConfigElement multipartConfigElement = multipartAutoConfiguration.multipartConfigElement();
                        StandardServletMultipartResolver standardServletMultipartResolver = multipartAutoConfiguration.multipartResolver();

                        //获取上下文
                        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
                        defaultListableBeanFactory.destroySingleton("multipartConfigElement");
                        defaultListableBeanFactory.registerSingleton("multipartConfigElement", multipartConfigElement);
                        defaultListableBeanFactory.destroySingleton("standardServletMultipartResolver");
                        defaultListableBeanFactory.registerSingleton("standardServletMultipartResolver", standardServletMultipartResolver);
                    });
        }

        log.info("[ActionApplicationListener] The update is successful {}", refresh);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     * 更新SpringApplicationContext对象，并更新路由
     *
     * @param changeKeys: 待更新的配置值
     */
    private void refreshProperties(Set<String> changeKeys) {
        log.info("properties refreshing!");
        //更新配置
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeKeys));
        log.info("properties refreshed!");
    }
}
