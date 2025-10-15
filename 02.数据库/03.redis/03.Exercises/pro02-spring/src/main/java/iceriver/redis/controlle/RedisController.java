package iceriver.redis.controlle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className RedisController
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 21:20
 */
@RestController
@RequestMapping("/redisTest")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping
    public String testRedis(){
        redisTemplate.opsForValue().set("name" ,"marry");
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;
    }
}
