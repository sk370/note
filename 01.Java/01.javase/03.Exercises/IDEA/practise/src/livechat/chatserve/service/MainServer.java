package livechat.chatserve.service;

import livechat.chatclient.service.ManageClientConnectServerThread;
import livechat.chatcommon.Message;
import livechat.chatcommon.MessgeType;
import livechat.chatcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @desc: 开启主服务，等待客户端的连接
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 16:47
 */
public class MainServer {
    private ServerSocket ss = null;
//    创建合法用户清单
    private static HashMap<String, User> validUsers = new HashMap<>();
//    初始化合法用户清单
    static {
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));
        validUsers.put("至尊宝", new User("至尊宝", "123456"));
        validUsers.put("紫霞仙子", new User("紫霞仙子", "123456"));
        validUsers.put("牛魔王", new User("牛魔王", "123456"));
    }
//    验证用户是否有效(这里同一用户可以登录多次，如果要限制为单点登录，需要在拿到userid后，在ManageServerConnectClientThead的hs集合中判断是否存在该键）
    private boolean checkUser(String userId, String password){
        User user = validUsers.get(userId);//键不存在时返回为空
        if(user == null){//userid不存在，用户名错误
            return false;
        }
        if(!user.getPassword().equals(password)){//密码错误
            return false;
        }
        return true;
    }

    public MainServer() {
        try {
            ss = new ServerSocket(9999);
            System.out.println("启动服务器，等待连接~");
            new Thread(new NewsService()).start();
            while (true) {//不断监听9999端口，看有无client访问/连接
                Socket socket = ss.accept();
//                输入流对象，用与检查登录用户信息
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User user = (User)ois.readObject();
                Message message = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                if(checkUser(user.getUserId(), user.getPassword())){
                    System.out.println("用户校验通过");
                    message.setMessType(MessgeType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);
//                  创建与客户端进行通信的线程
                    ServerConnectClientThread scct = new ServerConnectClientThread(socket, user.getUserId());
                    scct.start();
//                    创建管理与客户端通信的线程集合
                    ManageServerConnectClientThead.addServerConnectClientThread(user.getUserId(), scct);
                }else{
                    System.out.println("用户校验不通过");
                    message.setMessType(MessgeType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                ss.close();
                System.out.println("服务器关机");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
