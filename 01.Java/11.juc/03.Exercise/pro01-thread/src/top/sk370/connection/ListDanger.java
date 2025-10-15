package top.sk370.connection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ListDanger
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/03 15:39
 */
public class ListDanger {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();//线程不安全
//        List<String> list = new Vector<>();//解决方案一
//        List<String> list = Collections.synchronizedList(new ArrayList<>());//解决方案二
        List<String> list = new CopyOnWriteArrayList<>();//解决方案三
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
