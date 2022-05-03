package org.atbyuan.note.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author atbyuan
 * @since 2022/4/9 17:36
 */
@Slf4j
@EnableCaching
@SpringBootApplication
public class CacheBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheBootstrapApplication.class, args);
        log.info("--------------------------CacheBootstrapApplication start!--------------------------");
    }

}
