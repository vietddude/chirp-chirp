package viet.io.chirpchirp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenStore {
    private final JedisPool jedisPool;

    public void storeToken(String key, String token, long expirationMinutes) {
        if (expirationMinutes <= 0) {
            throw new IllegalArgumentException("Expiration time must be greater than zero.");
        }
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex(key, (int) (expirationMinutes * 60), token);
            log.info("Stored token for key: {}", key);
        } catch (Exception e) {
            log.error("Error storing token for key: {}", key, e);
        }
    }

    public String getToken(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            String token = jedis.get(key);
            log.info("Retrieved token for key: {}", key);
            return token;
        } catch (Exception e) {
            log.error("Error retrieving token for key: {}", key, e);
            return null; // or handle accordingly
        }
    }

    public void deleteToken(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
            log.info("Deleted token for key: {}", key);
        } catch (Exception e) {
            log.error("Error deleting token for key: {}", key, e);
        }
    }
}
