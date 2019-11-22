package repositories;

import dto.CopterDto;
import models.Copter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Repository
public class CopterDao {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void initRedisTemplate() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    }

    public void save(CopterDto copter) {
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        jedisConnectionFactory.getConnection().close();
        jedisConnectionFactory.destroy();
        redisTemplate.opsForValue().set(copter.getId(), copter);
    }

    public CopterDto getCopterById(String id) {
        CopterDto msg =  (CopterDto) redisTemplate.opsForValue().get(id);
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        jedisConnectionFactory.getConnection().close();
        jedisConnectionFactory.destroy();
        return msg;
    }

    public Set<String> getAllKeys() {
        Set<byte[]> set = redisTemplate.getConnectionFactory().getConnection().keys("*".getBytes());
        Iterator it = set.iterator();
        Set<String> result = new HashSet<>();
        while (it.hasNext()) {
            byte[] data = (byte[]) it.next();
            result.add(new String(data, 0, data.length));
        }
        return result;
    }

}
