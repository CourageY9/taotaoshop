package com.taotao.content.jedis;

//缓存接口(方便后面条用)
public interface JedisClient {

    //设置String类型redis
    String set(String key, String value);

    //获取String类型redis的值
    String get(String key);

    //判断可以是否存在
    Boolean exists(String key);

    //设置key的过期时间
    Long expire(String key, int seconds);

    //获取key的过期时间
    Long ttl(String key);

    //redis   String类型自增1
    Long incr(String key);

    //设置redis 散列（hash）
    Long hset(String key, String field, String value);

    //获取redis 散列（hash）的值
    String hget(String key, String field);

    //删除散列
    Long del(String key, String... field);

    //删除String
    Long del(String key);
}
