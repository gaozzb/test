package com.medicine.medicinebase.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.medicine.medicineutil.Z;
import lombok.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableCaching
@PropertySource(value = "classpath:*.properties", ignoreResourceNotFound = true)
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String redisHostUrl;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        Preconditions.checkArgument(!StringUtils.isEmpty(redisHostUrl), "请配置redis缓存IP地址");

        String[] ipAndPort = redisHostUrl.split(":");

        Preconditions.checkNotNull(ipAndPort, "请配置redis缓存IP地址及端口");

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        poolConfig.setBlockWhenExhausted(true);
        //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        poolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        //是否启用pool的jmx管理功能, 默认true
        poolConfig.setJmxEnabled(true);
        poolConfig.setJmxNamePrefix("pool");
        //是否启用后进先出, 默认true
        poolConfig.setLifo(true);
        //最小空闲连接数, 默认0
        poolConfig.setMinIdle(8);
        //最大空闲连接数, 默认8个
        poolConfig.setMaxIdle(32);
        //最大连接数, 默认8个
        poolConfig.setMaxTotal(512);
        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        poolConfig.setMaxWaitMillis(3000);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        poolConfig.setMinEvictableIdleTimeMillis(60000L);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        poolConfig.setNumTestsPerEvictionRun(3);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        poolConfig.setSoftMinEvictableIdleTimeMillis(60000L);
        //在获取连接的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(true);
        //在空闲时检查有效性, 默认false
        poolConfig.setTestWhileIdle(true);
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        poolConfig.setTimeBetweenEvictionRunsMillis(30000L);

        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setPoolConfig(poolConfig);
        redisConnectionFactory.setHostName(ipAndPort[0]);
        redisConnectionFactory.setPort(Integer.valueOf(ipAndPort[1]));
        redisConnectionFactory.afterPropertiesSet();
        redisConnectionFactory.setUsePool(true);
        redisConnectionFactory.setTimeout(3000);

        return redisConnectionFactory;
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(factory);

        ObjectMapper objectMapper = Z.objectMapper.copy();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        StringRedisSerializer srs = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer gjrs = new GenericJackson2JsonRedisSerializer(objectMapper);

        redisTemplate.setKeySerializer(srs);
        redisTemplate.setValueSerializer(gjrs);

        redisTemplate.setHashKeySerializer(srs);
        redisTemplate.setHashValueSerializer(gjrs);

        redisTemplate.setDefaultSerializer(gjrs);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate(redisConnectionFactory()));
        cacheManager.setDefaultExpiration(300);
        Map<String, Long> expires = new HashMap<>();
        for (CachedConfigurer.ExpireTimeEnum cet : CachedConfigurer.ExpireTimeEnum.values()) {
            expires.put(cet.name(), (long) cet.getTTL());
        }
        cacheManager.setExpires(expires);
        return cacheManager;
    }

    @Override
    public CacheManager cacheManager() {
        List<CacheManager> cacheManagers = Lists.newArrayList();
        cacheManagers.add(redisCacheManager());
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        cacheManager.setCacheManagers(cacheManagers);
        cacheManager.setFallbackToNoOpCache(false);
        return cacheManager;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
