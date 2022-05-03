package org.atbyuan.note.db.controller;

import lombok.extern.slf4j.Slf4j;
import org.atbyuan.note.common.entity.User;
import org.atbyuan.note.common.response.ApiResponse;
import org.atbyuan.note.db.mapper.PeopleMapper;
import org.atbyuan.note.db.mapper.UserMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author atbyuan
 * @since 2022/2/25 18:36
 **/
@Slf4j
@RestController
@RequestMapping("/mysql")
public class MysqlController {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(3);
    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private UserMapper userMapper;
    @Resource
    private PeopleMapper peopleMapper;

    @GetMapping("/transactional")
    public ApiResponse<Void> transactional() {
        EXECUTOR_SERVICE.execute(() -> transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                test();
            }
        }));
        return ApiResponse.SUCCESS();
    }

    @Transactional(rollbackFor = Exception.class)
    public void test() {
        userMapper.insert(User.builder().mobile("18811112345").password("1").build());

//        peopleMapper.insert(People.builder().id(5).address("1232432").firstname("transactional").build());

        throw new RuntimeException("test");
    }

}
