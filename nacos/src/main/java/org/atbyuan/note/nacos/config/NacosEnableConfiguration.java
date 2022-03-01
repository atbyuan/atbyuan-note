package org.atbyuan.note.nacos.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author atbyuan
 * @since 2022/2/11 15:42
 **/
@Configuration
@EnableConfigurationProperties(NacosAutoConfigProperties.class)
public class NacosEnableConfiguration {
}
