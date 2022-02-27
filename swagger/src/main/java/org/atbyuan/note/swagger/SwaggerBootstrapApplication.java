package org.atbyuan.note.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author atbyuan
 * @date 2021/7/26 9:59
 **/
@SpringBootApplication
@EnableAsync
public class SwaggerBootstrapApplication {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerBootstrapApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SwaggerBootstrapApplication.class, args);
        logger.info("--------------------------server start!--------------------------");
    }

}
