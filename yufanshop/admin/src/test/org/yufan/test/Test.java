package org.yufan.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test {
    @org.junit.Test
    public void TestJedisSingle() {
        //1.设置IP和端口
        Jedis jedis = new Jedis("chaoyous.cn",6379);
        //2.设置数据
        jedis.set("xxx", "yyy");
        //3.取数据
        String xxx = jedis.get("xxx");
        System.out.println(xxx);
        jedis.close();
    }
    //带数据池
    @org.junit.Test
    public void TestPoolJedisSingle() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);//最大连接数
        config.setMaxIdle(10); //最大空闲数
        //----->实际开中上面的需要写到一个properties文件中
        JedisPool jedisPool = new JedisPool(config,"chaoyous.cn",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("aaa", "bbb");
        //3.取数据
        String xxx = jedis.get("aaa");
        System.out.println(xxx);
        jedis.close();

    }
}
