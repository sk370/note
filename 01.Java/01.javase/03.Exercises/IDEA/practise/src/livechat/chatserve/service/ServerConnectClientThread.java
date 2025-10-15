package livechat.chatserve.service;

import livechat.chatcommon.Message;
import livechat.chatcommon.MessgeType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * @desc: 与客户端保持通讯的某个线程
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 17:00
 */
public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId;

    public Socket getSocket() {
        return socket;
    }

    public String getUserId() {
        return userId;
    }

    public ServerConnectClientThread(Socket socket, String userId){
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("服务器开启客户端数据监听" + userId);
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//                获取客户端的请求message，看他要干什么
                Message message = (Message)ois.readObject();
                if(message.getMessType().equals(MessgeType.MESSAGE_GET_ONLINE_FRIEND)){//客户端是否请求在线用户清单
                    System.out.println(message.getSender() + "正在请求在线用户清单");
                    String onlineUser = ManageServerConnectClientThead.getOnlineUser();
//                    构建一个返回给客户端的message对象
                    Message message1 = new Message();
                    message1.setMessType(MessgeType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setContent(onlineUser);
                    message1.setReceiver(message.getSender());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);
                }else if(message.getMessType().equals(MessgeType.MESSAGE_CLIENT_EXIT)){//客户端是否请求退出
                    System.out.println(message.getSender() + "正在请求退出");
//                    线程集合中移除与该用于通信的线程
                    ManageServerConnectClientThead.reomveClientThread(userId);//这里传进去的userId也可以用message.getSender()
                    socket.close();//关闭连接
                    break;//退出服务端的监听
                }else if(message.getMessType().equals(MessgeType.MESSAGE_COMM_MES)) {//客户端是否请求私聊消息
                    System.out.println(message.getSender() + "正在向" + message.getReceiver() + "发送消息");
                    ServerConnectClientThread serverConnectClientThread = ManageServerConnectClientThead.getServerConnectClientThread(message.getReceiver());//获取接收消息客户端的线程
                    //给发送者的返回消息
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());//这个socket是发送者的
                    Message message1 = new Message();
                    if(serverConnectClientThread == null){
                        message1.setContent(message.getReceiver() + "不在线，消息发送失败");
                        message1.setMessType(MessgeType.MESSAGE_MESSAGE_FAILD);
                        oos.writeObject(message1);
                    }else {
//                    构建一个返回给消息接收者客户端的message对象
                        ObjectOutputStream oos1 = new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
                        oos1.writeObject(message);//转发消息
                        message1.setContent("消息发送成功");
                        message1.setMessType(MessgeType.MESSAGE_MESSAGE_SUCCEED);
                        oos.writeObject(message1);
                    }
                }else if(message.getMessType().equals(MessgeType.MESSAGE_TO_ALL)) {//客户端是否请求群发消息
                    System.out.println(message.getSender() + "正在群发发送消息");
                    Hashtable<String, ServerConnectClientThread> hs = ManageServerConnectClientThead.getHs();
                    Iterator<String> iterator = hs.keySet().iterator();
                    while (iterator.hasNext()) {
                        String onlineUserId = iterator.next();
                        if(!onlineUserId.equals(message.getSender())){
                            ServerConnectClientThread serverConnectClientThread = ManageServerConnectClientThead.getServerConnectClientThread(onlineUserId);//也可以通过hs的key获取thread
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
                            objectOutputStream.writeObject(message);
                        }
                    }
                }else if(message.getMessType().equals(MessgeType.MESSAGE_FILE)) {//是不是要传输文件
                    System.out.println(message.getSender() + "请求给" + message.getReceiver() + "发送文件");
                    ServerConnectClientThread serverConnectClientThread = ManageServerConnectClientThead.getServerConnectClientThread(message.getReceiver());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
                    objectOutputStream.writeObject(message);
                }
                else {
                    System.out.println("message类型不匹配，无法返回在线用户列表");
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
