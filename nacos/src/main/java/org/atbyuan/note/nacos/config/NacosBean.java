package org.atbyuan.note.nacos.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author atbyuan
 * @since 2022/2/7 18:10
 **/
@Configuration
public class NacosBean {

    @Getter
    private NacosAutoConfigProperties nacosAutoConfigProperties;

    public NacosBean(NacosAutoConfigProperties configProperties) {
        this.nacosAutoConfigProperties = configProperties;
    }

    @Bean(name = "nacosConfig")
    public NacosBean nacosBean(@Autowired NacosAutoConfigProperties configProperties) {
        return new NacosBean(configProperties);
    }

}
