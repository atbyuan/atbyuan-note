package org.atbyuan.note.rpc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author atbyuan
 * @since 2022/2/15 16:03
 **/
@FeignClient(name = "note-rpc", url = "http://localhost:8001")
public interface FeignApi {

    @GetMapping("/nacos/print")
    String print();

}
