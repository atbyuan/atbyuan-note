package org.atbyuan.note;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author baiyu.yuan@yintech.cn
 * @since 2022/1/5 10:54
 **/
@SpringBootApplication
public class NacosBootstrapApplication {

    private static final Logger logger = LoggerFactory.getLogger(NacosBootstrapApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NacosBootstrapApplication.class, args);
        logger.info("--------------------------server start!--------------------------");
    }

}
