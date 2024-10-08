package viet.io.chirpchirp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Configuration class for setting up Redis connection using Jedis.
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.uri}")
    private String connectionString;

    /**
     * Creates and configures a JedisPool bean.
     *
     * @return a configured JedisPool instance
     */
    @Bean(destroyMethod = "close")
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setJmxEnabled(false);
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(64);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);

        return new JedisPool(poolConfig, connectionString);
    }
}