package org.atbyuan.note.rpc.controller;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.note.rpc.service.FeignApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author atbyuan
 * @since 2022/2/15 16:02
 **/
@Slf4j
@RestController
@RequestMapping("/rpc")
public class RpcController {

    @Resource
    private FeignApi feignApi;

    @GetMapping("/print")
    public String print() {
        return feignApi.print();
    }
}
