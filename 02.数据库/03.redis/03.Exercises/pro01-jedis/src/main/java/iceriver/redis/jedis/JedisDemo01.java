package iceriver.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className JedisDemo01
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 19:00
 */
public class JedisDemo01 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.150.132", 6379);
        String ping = jedis.ping();
        System.out.println(ping);
    }
    @Test
    public void demo01(){
        Jedis jedis = new Jedis("192.168.150.132", 6379);
//        jedis.set()
        Set<String> keys = jedis.keys("*");
        for (String key : keys){
            System.out.println(key);
        }
    }

}
