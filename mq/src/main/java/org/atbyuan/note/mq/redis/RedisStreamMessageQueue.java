package org.atbyuan.note.mq.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStreamCommands;

/**
 * @author atbyuan
 * @since 2022/2/28 21:01
 */
public class RedisStreamMessageQueue {

    private RedisStreamCommands redisStreamCommands;

    public void init( ) {
        RedisURI redisURI = RedisURI.builder()
                .withSentinelMasterId("master")
                .withPassword("foobared")
                .withSentinelMasterId("master")
                .withSentinel("192.168.59.146",26379)
                .withSentinel("192.168.59.141",26379)
                .withSentinel("192.168.59.141",26389).build();

        RedisClient client = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisStreamCommands streamCommands = connection.sync();

    }

}
