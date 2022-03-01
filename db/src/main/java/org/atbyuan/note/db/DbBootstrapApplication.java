package org.atbyuan.note.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author atbyuan
 * @since 2021/12/16 16:05
 **/
@Slf4j
@SpringBootApplication
public class DbBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbBootstrapApplication.class, args);
        log.info("--------------------------DbApplication start!--------------------------");
    }
}
