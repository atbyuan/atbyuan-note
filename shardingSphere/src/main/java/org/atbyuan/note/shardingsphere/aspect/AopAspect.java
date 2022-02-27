package org.atbyuan.note.shardingsphere.aspect;

import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author atbyuan
 * @since 2021年8月6日 22点56分
 */
@Slf4j
@Aspect
@Configuration
public class AopAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointCut() {
        // 无事发生
    }

    @Before(value = "pointCut()")
    public void before() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String path = URLUtil.getPath(request.getRequestURI());
    }

    @Before(value = "pointCut()")
    public void after() {
    }
}
