package com.taotao.portal.pojo;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RedisTestTest {

    /**
     * <pre>
     * Description :  redis基本运用一  <br/>
     * ChangeLog : 1. 创建 (2019/12/4 17:16 [yangyi]);
      *
      * @return void
     * </pre>
     */
    @Test
    public void show(){
        //                      redis运行得虚拟机地址     端口号
        Jedis jedis = new Jedis("192.168.58.102", 6379);    //创建redis连接（连接本地redis服务器）
        jedis.set("userName", "y9");    //创建key-value
        String userName = jedis.get("userName");
        jedis.close();  //关闭连接
        System.out.println(userName);
    }

    @Test
    public void show2(){
        //创建redis连接池
        JedisPool jedisPool = new JedisPool("192.168.58.102", 6379);
        //从连接池中获取jedis对象
        Jedis jedis = jedisPool.getResource();
        jedis.del("userName");
        jedis.set("userName", "357");
        String userName = jedis.get("userName");
        System.out.println(userName);
        jedis.close();//关闭jedis对象
        jedisPool.close();//关闭jedisPool连接池
    }

    @Test
    public void show3(){
        // 1.使用JedisCluster对象。（需要一个Set）
        Set<HostAndPort> ips = new HashSet<HostAndPort>();//用于存储redis集群的IP地址和端口号
        ips.add(new HostAndPort("192.168.58.104",7001));
        ips.add(new HostAndPort("192.168.58.104",7002));
        ips.add(new HostAndPort("192.168.58.104",7003));
        ips.add(new HostAndPort("192.168.58.104",7004));
        ips.add(new HostAndPort("192.168.58.104",7005));
        ips.add(new HostAndPort("192.168.58.104",7006));
        JedisCluster jedisCluster = new JedisCluster(ips);  //创建使用JedisCluster对象连接redis集群
        //直接使用jedisCluster对象操作redis
        String result = jedisCluster.get("key1");
        System.out.println(result);

        jedisCluster.close();   //关闭JedisCluster对象
    }
}