package org.atbyuan.note.nacos.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static org.atbyuan.note.nacos.config.NacosEnableConfiguration.CONFIG_ENABLED_PROP;

/**
 * @author atbyuan
 * @since 2022/2/11 15:42
 **/
@Configuration
@EnableConfigurationProperties(NacosAutoConfigProperties.class)
@ConditionalOnProperty(name = CONFIG_ENABLED_PROP, matchIfMissing = true, havingValue = "true")
public class NacosEnableConfiguration {

    public static final String MAIN_PROPERTIES_PREFIX = "spring.cloud.nacos";

    public static final String CONFIG_ENABLED_PROP = MAIN_PROPERTIES_PREFIX + ".enabled";

}

