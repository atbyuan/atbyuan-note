package org.atbyuan.note.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author atbyuan
 * @date 2021/7/26 9:54
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @ApiOperation("test1")
    @GetMapping("test1")
    public void test1(@RequestParam("id") Integer id) {
        logger.info("id:{}", id);
    }

    @GetMapping("test2")
    public void test2(@RequestParam("id") Integer id) {
        logger.info("id:{}", id);
    }

}
