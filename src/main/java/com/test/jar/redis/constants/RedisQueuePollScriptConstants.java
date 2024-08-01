package com.test.jar.redis.constants;

public interface RedisQueuePollScriptConstants {

    String POLL_SCRIPT = "local result = redis.call('ZRANGE', KEYS[1], ARGV[1], ARGV[2]); " +
            "if #result > 0 then " +
            "redis.call('ZREM', KEYS[1], unpack(result)); " +
            "end; " +
            "return result;";
}
