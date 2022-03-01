package org.atbyuan.note;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.note.common.entity.User;
import org.atbyuan.note.db.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author atbyuan
 * @since 2021/12/16 16:38
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test {

    @Autowired
    private UserMapper userMapper;

    @org.junit.Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        int num = 1000;
        executorService.execute(() -> {
            User user = User.builder().id(10).build();
            for (int i = 0; i < num; i++) {
                user.setPassword(String.valueOf(i));
                userMapper.updateById(user);
                user = userMapper.selectById(10);
                log.info("{}", user);
            }
        });
    }

    @org.junit.Test
    public void test2() {
        BigDecimal bigDecimal = new BigDecimal("0.01");
    }
}
