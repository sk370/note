package livechat.chatclient.service;

import livechat.chatcommon.Message;
import livechat.chatcommon.MessgeType;
import livechat.chatcommon.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @desc: 登录验证和用户注册
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 15:51
 */
public class UserClientService {
    private User user;
    private Socket socket;

    /**
     * @param userId
     * @param password
     * @return
     * @desc 将客户端输入的用户名和密码发送到服务器进行验证，以判断能否登录
     */
    public boolean checekUser(String userId, String password) throws IOException, ClassNotFoundException {
        boolean b = false;//判断是否登录成功
        user = new User(userId, password);
//          创建与服务器的连接
        socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
//         将用户的登录信息发送到服务器
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(user);
//        获取服务器验证后的信息（Message对象）
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Message message = (Message) objectInputStream.readObject();
//        判断是否登录成功
        if (message.getMessType().equals(MessgeType.MESSAGE_LOGIN_SUCCEED)) {
//            创建一个与服务器端进行通信的线程
            ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
            ccst.start();
//            将该线程加入到线程集合进行管理
            ManageClientConnectServerThread.addClientConnectServerThread(userId, ccst);
            b = true;
        } else if (message.getMessType().equals(MessgeType.MESSAGE_LOGIN_FAIL)) {
            System.out.println("用户名或密码错误");
            socket.close();
        }
        return b;
    }

    /**
     * @desc 向服务端请求在线用户清单
     */
    public void onLineFriendList(){
        Message message = new Message();
        message.setMessType(MessgeType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(user.getUserId());

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(user.getUserId()).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc 通知服务器我要退出，正常操作退出时，直接关闭窗口服务器端还是会报异常
     */
    public void logout(){
        Message message = new Message();
        message.setMessType(MessgeType.MESSAGE_CLIENT_EXIT);
        message.setSender(user.getUserId());

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(user.getUserId()).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
            System.out.println("欢迎您（" + user.getUserId() + "）再次登录");
            System.exit(0);//退出该用的进程。ManageClientConnectServerThread是一个客户端进程的多个进程管理类，这里退出了进程，ManageClientConnectServerThread中的hm集合也就全没有了
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc 给当前在线的用户发送消息： 1. 不能给自己发消息， 2. 给离线用户发送会提示失败 3. 给在线用户发送会显示成功
     * @param content
     * @param userId
     * @param targetId
     */
    public void secretChat(String content, String userId, String targetId){
        if (userId.equals(targetId)) {
            System.out.println("你不能给自己发消息");
            return;
        }
        Message message = new Message();
        message.setMessType(MessgeType.MESSAGE_COMM_MES);
        message.setSender(userId);
        message.setReceiver(targetId);
        message.setContent(content);
        message.setSendTime(new java.util.Date().toString());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(userId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
//            System.out.println("给" + message.getReceiver() + "发送消息成功");
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc 给所有用户发送消息。自己收不到
     * @param content
     * @param userId
     */
    public void sendMessageToAll(String content, String userId){
        Message message = new Message();
        message.setMessType(MessgeType.MESSAGE_TO_ALL);
        message.setSender(userId);
        message.setContent(content);
        message.setSendTime(new java.util.Date().toString());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(userId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
//            System.out.println("给" + message.getReceiver() + "发送消息成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc 给指定用户传输文件
     * @param src
     * @param dest
     * @param userId
     * @param getterId
     */
    public void sendFileToOne(String src, String dest, String userId, String getterId) throws IOException {
        Message message = new Message();
        message.setSrcPath(src);
        message.setDestPath(dest);
        message.setMessType(MessgeType.MESSAGE_FILE);
        message.setSender(userId);
        message.setReceiver(getterId);
        message.setSendTime(new java.util.Date().toString());
//        从外部读取文件
        byte[] fileBytes = new byte[(int)new File(src).length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);
            message.setFileBytes(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        向服务器发送文件
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(message);
    }
}
