package org.atbyuan.note.shardingsphere;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author atbyuan
 * @date 2021年8月2日 00点54分
 */
@SpringBootApplication
public class ShardingSphereApplication {

    private static final Logger logger = LoggerFactory.getLogger(ShardingSphereApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereApplication.class, args);
        logger.info("--------------------------ShardingSphereApplication start!--------------------------");
    }
}
