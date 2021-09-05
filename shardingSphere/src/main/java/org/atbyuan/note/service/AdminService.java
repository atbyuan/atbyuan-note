package org.atbyuan.note.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.atbyuan.note.entity.SsUser;
import org.atbyuan.note.entity.User;
import org.atbyuan.note.mapper.ds1.SsUserMapper;
import org.atbyuan.note.mapper.ds2.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author atbyuan
 * @date 2021/8/1 15:42
 **/
@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SsUserMapper ssUserMapper;

    public User save(String mobile) {
        User user = get(mobile);
        if (user != null) {
            logger.info("User already exists. {}", user);
            return user;
        }
        user = User.builder()
                .mobile(mobile).password(RandomUtil.randomString(12))
                .build();
        userMapper.insert(user);
        user = get(mobile);
        logger.info("User already insert. {}", user);
        return user;
    }

    public User get(String mobile) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getMobile, mobile));
    }

    public User get(Integer id) {
        return userMapper.selectById(id);
    }

    public List<SsUser> getSsUser(String email) {
        return ssUserMapper.selectList(Wrappers.<SsUser>lambdaQuery()
                .eq(SsUser::getEmail, email));
    }

    public List<SsUser> getSsUser2(String email) {
        return ssUserMapper.findByEmail(email);
    }
}
