package org.atbyuan.note.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author atbyuan
 * @since 2022/2/28 20:36
 */
@SpringBootApplication
public class MqBootstrapApplication {

    private static final Logger logger = LoggerFactory.getLogger(MqBootstrapApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MqBootstrapApplication.class, args);
        logger.info("--------------------------server start!--------------------------");
    }

}