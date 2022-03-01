package org.atbyuan.note.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author atbyuan
 * @since 2022/2/15 16:01
 **/
@SpringBootApplication
@EnableFeignClients
public class RpcBootstrapApplication {

    private static final Logger logger = LoggerFactory.getLogger(RpcBootstrapApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RpcBootstrapApplication.class, args);
        logger.info("--------------------------server start!--------------------------");
    }

}
