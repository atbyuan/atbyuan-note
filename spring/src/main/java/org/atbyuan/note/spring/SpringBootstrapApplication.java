package org.atbyuan.note.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author atbyuan
 * @since 2022/3/1 13:48
 **/
@Slf4j
@SpringBootApplication
public class SpringBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootstrapApplication.class, args);
        log.info("--------------------------SpringBootstrapApplication start!--------------------------");
    }
}