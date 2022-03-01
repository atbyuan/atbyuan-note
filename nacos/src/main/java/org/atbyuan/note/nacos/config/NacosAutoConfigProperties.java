package org.atbyuan.note.nacos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author atbyuan
 * @since 2022/2/7 18:03
 **/
@Data
@ConfigurationProperties(prefix = "nacos")
public class NacosAutoConfigProperties {

    private String value;
    private Integer age;
    private String name;

}
