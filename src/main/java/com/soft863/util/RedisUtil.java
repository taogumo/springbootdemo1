package com.soft863.util;

import redis.clients.jedis.Jedis;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-21 18:02
 **/
public class RedisUtil {
    private static Jedis jedis = new Jedis ("127.0.0.1", 6379);
    public static Jedis getInstance() {
        return jedis;
    }

    public RedisUtil(){}
}
