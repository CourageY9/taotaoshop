package com.taotao.common.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Auther: yangyi  <br/>
 * Date: 2019/12/5:10:10  <br/>
 * Description：简单的redis公共类
 */
public class JedisUtils {

    //创建redis连接池    (单例设计模式)
    private static final JedisPool jedisPool = new JedisPool("192.168.58.102", 6379);

    //创建Jedis对象
   // private static Jedis jedis = jedisPool.getResource();

    /**
     * <pre>
     * Description :  设置String类型的redis  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:18 [yangyi]);
      * @param key
     * @param value
      * @return java.lang.String
     * </pre>
     */
    public static String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  TODO  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:19 [yangyi]);
      * @param key
      * @return java.lang.String
     * </pre>
     */
    public static String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  判断key是否存在  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:24 [yangyi]);
      * @param key
      * @return java.lang.Boolean
     * </pre>
     */
    public static Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  设置key的过期时间  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:24 [yangyi]);
      * @param key
     * @param seconds
      * @return java.lang.Long
     * </pre>
     */
    public static Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :   获取key的过期时间  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:25 [yangyi]);
      * @param key
      * @return java.lang.Long
     * </pre>
     */
    public static Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  redis   String类型自增1  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:25 [yangyi]);
      * @param key
      * @return java.lang.Long
     * </pre>
     */
    public static Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  设置redis 散列（hash）  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:25 [yangyi]);
      * @param key
     * @param field
     * @param value
      * @return java.lang.Long
     * </pre>
     */
    public static Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  获取redis 散列（hash）的值  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:26 [yangyi]);
      * @param key
     * @param field
      * @return java.lang.String
     * </pre>
     */
    public static String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  删除散列  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:26 [yangyi]);
      * @param key
     * @param field
      * @return java.lang.Long
     * </pre>
     */
    public static Long hdel(String key, String... field) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  删除散列  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:26 [yangyi]);
     * @param key
     * @param field
     * @return java.lang.Long
     * </pre>
     */
    public static Long hdel(byte[] key, byte[]... field) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  删除redis String类型数据  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:27 [yangyi]);
      * @param key
      * @return java.lang.Long
     * </pre>
     */
    public static Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    /**
     * <pre>
     * Description :  删除redis String类型数据  <br/>
     * ChangeLog : 1. 创建 (2019/12/5 10:27 [yangyi]);
     * @param key
     * @return java.lang.Long
     * </pre>
     */
    public static Long del(String... key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }
}
