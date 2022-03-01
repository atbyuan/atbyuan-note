package org.atbyuan.note.spring.converter;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.atbyuan.note.common.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author atbyuan
 * @since 2022/3/1 13:55
 **/
@Slf4j
@RestController
@RequestMapping("/spring")
public class ConvertController {

    @GetMapping("/select")
    public User select() {
        return User.builder()
                .id(RandomUtil.randomInt(10000))
                .mobile("1" + RandomUtil.randomNumbers(10))
                .password(RandomUtil.randomString(10))
                .createTime(new Date())
                .build();
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        user.setUpdateTime(new Date());
        return user;
    }
}
