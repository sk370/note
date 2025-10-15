package livechat.chatclient.service;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @desc: 管理客户端连接到服务器的线程：一个客户端的多个线程
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 16:19
 */
public class ManageClientConnectServerThread {
    public static Hashtable<String, ClientConnectServerThread> hm = new Hashtable<>();//key-->userId, value--->thread

    public static void addClientConnectServerThread(String userId, ClientConnectServerThread ccst) {
        hm.put(userId, ccst);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return hm.get(userId);
    }
}
