package org.atbyuan.note.box.sync;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.atbyuan.note.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author atbyuan
 * @since 2022/1/28 21:05
 */
@Slf4j
@RestController
@RequestMapping
public class SyncLock {

    private static final ThreadLocal<Integer> TLS = new ThreadLocal<>();

    Map<String, ReentrantLock> mutexCache = Maps.newConcurrentMap();

    /**
     * <pre>
     * 演示[锁同步失败场景]的操作。
     * remove 前后各有一个线程执行了 computeIfAbsent，前一个线程执行 computeIfAbsent.get 时取到的是将要 remove 的 rt，后一个线程执行 computeIfAbsent.get 时取到的是自己创建的 rt.
     * 根本在于 mutex4Key.getQueueLength() == 0 和 mutexCache.remove(orderId) 非原子操作。
     * {@code
     * 2022-01-28 23:38:17.612  INFO 27360 --- [tor-http-nio-10] org.atbyuan.note.box.sync.SyncLock   : tls-[3310]. rtCode-[651135389]. [1] 开始
     * 2022-01-28 23:38:17.612  INFO 27360 --- [tor-http-nio-10] org.atbyuan.note.box.sync.SyncLock   : tls-[3310]. rtCode-[651135389]. [1] 结束
     * 2022-01-28 23:38:17.612  INFO 27360 --- [tor-http-nio-11] org.atbyuan.note.box.sync.SyncLock   : tls-[3208]. rtCode-[651135389]. [1] 开始
     * 2022-01-28 23:38:17.612  INFO 27360 --- [tor-http-nio-12] org.atbyuan.note.box.sync.SyncLock   : tls-[7440]. rtCode-[1786133990]. [1] 开始
     * 2022-01-28 23:38:17.612  INFO 27360 --- [tor-http-nio-11] org.atbyuan.note.box.sync.SyncLock   : tls-[3208]. rtCode-[651135389]. [1] 结束
     * 2022-01-28 23:38:17.612  INFO 27360 --- [tor-http-nio-12] org.atbyuan.note.box.sync.SyncLock   : tls-[7440]. rtCode-[1786133990]. [1] 结束
     * }
     * </pre>
     * 来源：<a href="https://www.bilibili.com/video/BV1cS4y1Z75h?spm_id_from=333.999.0.0">Java封装同步锁（翻车后续）</a>
     *
     * @param orderId: 请求ID
     * @return {@link ApiResponse}
     */
    @GetMapping("/bug/{orderId}")
    public ApiResponse<String> bugLock(@PathVariable("orderId") String orderId) {
        TLS.set(RandomUtil.randomInt(1000, 9999));
        ReentrantLock mutex4Key = mutexCache.computeIfAbsent(orderId, k -> new ReentrantLock());
        mutex4Key.lock();
        try {
            log.info("tls-[{}]. rtCode-[{}]. [{}] 开始", TLS.get(), mutex4Key.hashCode(), orderId);
            ThreadUtil.sleep(10);
            log.info("tls-[{}]. rtCode-[{}]. [{}] 结束", TLS.get(), mutex4Key.hashCode(), orderId);
        } finally {
            if (mutex4Key.getQueueLength() == 0) {
                mutexCache.remove(orderId);
            }
            mutex4Key.unlock();
            TLS.remove();
        }
        return ApiResponse.SUCCESS(orderId);
    }


    @GetMapping("/solve/{orderId}")
    public ApiResponse<String> solveLock(@PathVariable("orderId") String orderId) {
        TLS.set(RandomUtil.randomInt(1000, 9999));
        ReentrantLock mutex4Key = null;
        ReentrantLock mutexInCache;
        do {
            if (mutex4Key != null) {
                mutex4Key.unlock();
            }
            mutex4Key = mutexCache.computeIfAbsent(orderId, k -> new ReentrantLock());
            mutex4Key.lock();
            mutexInCache = mutexCache.get(orderId);
        } while (mutexInCache == null || mutexInCache != mutex4Key);
        try {
            log.info("tls-[{}]. [{}] 开始", TLS.get(), orderId);
            ThreadUtil.sleep(10);
            log.info("tls-[{}]. [{}] 结束", TLS.get(), orderId);
        } finally {
            if (mutex4Key.getQueueLength() == 0) {
                mutexCache.remove(orderId);
            }
            mutex4Key.unlock();
            TLS.remove();
        }
        return ApiResponse.SUCCESS(orderId);
    }
}