package livechat.chatserve.service;

import java.util.*;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 17:18
 */
public class ManageServerConnectClientThead {
    private static Hashtable<String, ServerConnectClientThread> hs = new Hashtable<>();//hashmap有线程安全问题，如果涉及hs的编辑，

    public static Hashtable<String, ServerConnectClientThread> getHs() {
        return hs;
    }

    public static void addServerConnectClientThread(String userId, ServerConnectClientThread scc){
        hs.put(userId, scc);
    }
    //根据userid，返回服务器与该用户通信的线程
    public static ServerConnectClientThread getServerConnectClientThread(String userId){
        return hs.get(userId);
    }
/**
 * @desc 返回（获取）全部在线用户列表（通过获取全部与服务器通信的线程获取）
 */
    public static String getOnlineUser(){
        String onlineUserList = "";
//        遍历hs
//        1. 遍历方式一
//        Set<Map.Entry<String, ServerConnectClientThread>> entries = hs.entrySet();
//        for (Map.Entry<String, ServerConnectClientThread> userId : entries){
//            String use = userId.getKey();
//        }
//        2. 遍历方式二
//        Set<String> usersId = hs.keySet();
//        for (String userId : usersId){
//            onlineUserList += userId + " ";
//        }
//        3. 遍历方式三
        Iterator<String> iterator = hs.keySet().iterator();
        while (iterator.hasNext()) {
            String next =  iterator.next();
            onlineUserList += next + " ";
        }
        return onlineUserList;
    }

    /**
     * @desc 移除一个用户进程
     * @param userId
     */
    public static void reomveClientThread(String userId){
        hs.remove(userId);
    }
}
