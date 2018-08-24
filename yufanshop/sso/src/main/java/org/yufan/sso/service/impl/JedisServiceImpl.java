package org.yufan.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.sso.service.JedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class JedisServiceImpl implements JedisService {


    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis(){
        return jedisPool.getResource();
    }
    @Override
    public String get(String key) {
        Jedis jedis=getJedis();
        String s=jedis.get(key);
        jedis.close();
        return s;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis=getJedis();
        String s =jedis.set(key,value);
        jedis.close();
        return s;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis=getJedis();
        String s =jedis.hget(hkey,key);
        jedis.close();
        return s;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis=getJedis();
        Long res =jedis.hset(hkey,key,value);
        jedis.close();
        return res;
    }

    @Override
    public long incr(String key) {
        Jedis jedis=getJedis();
        Long res=jedis.incr(key);
        jedis.close();
        return res;
    }

    @Override
    public long expire(String key, int time) {
        Jedis jedis=getJedis();
        Long exp=jedis.expire(key,time);
        jedis.close();
        return exp;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis=getJedis();
        Long ttl=jedis.ttl(key);
        jedis.close();
        return ttl;
    }

    @Override
    public long del(String key) {
        Jedis jedis=getJedis();
        Long del=jedis.del(key);
        jedis.close();
        return del;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis=getJedis();
        Long hdel=jedis.hdel(hkey,key);
        jedis.close();
        return hdel;
    }
}
