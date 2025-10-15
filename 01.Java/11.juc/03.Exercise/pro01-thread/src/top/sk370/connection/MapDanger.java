package top.sk370.connection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className SetDanger
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 15:59
 */
public class MapDanger {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();//线程不安全
        Map<String, String> map = new ConcurrentHashMap<>();//线程不安全
        for (int i = 0; i < 10; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },key).start();
        }
    }
}
