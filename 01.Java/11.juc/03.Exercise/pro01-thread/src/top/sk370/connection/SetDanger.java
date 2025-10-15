package top.sk370.connection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className SetDanger
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 15:59
 */
public class SetDanger {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();//线程不安全
        Set<String> set = new CopyOnWriteArraySet<>();//解决方案
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
