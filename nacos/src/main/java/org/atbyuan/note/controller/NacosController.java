package org.atbyuan.note.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baiyu.yuan@yintech.cn
 * @since 2022/1/24 9:38
 **/
@RestController
@RequestMapping("/nacos")
@RefreshScope
@Slf4j
public class NacosController {

    @Value("${nacos.value}")
    private String nacosValue;

    @GetMapping("/print")
    public String print() {
        log.info("nacosValue: {}", nacosValue);
        return nacosValue;
    }

}
