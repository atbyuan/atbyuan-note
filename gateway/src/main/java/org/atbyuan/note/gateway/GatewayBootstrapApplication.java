package org.atbyuan.note.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayBootstrapApplication {

    private static final Logger logger = LoggerFactory.getLogger(GatewayBootstrapApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GatewayBootstrapApplication.class, args);
        logger.info("--------------------------server start!--------------------------");
    }

}
