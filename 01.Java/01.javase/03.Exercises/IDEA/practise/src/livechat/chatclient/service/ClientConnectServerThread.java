package livechat.chatclient.service;


import livechat.chatcommon.Message;
import livechat.chatcommon.MessgeType;

import java.io.*;
import java.net.Socket;

/**
 * @desc: 登录成功就创建一个线程,1 给服务器发送数据，2 从服务器获取数据
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 16:07
 */
public class ClientConnectServerThread extends Thread{
    private Socket socket;//每个线程都持有一个socket用于与服务器通信

    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            try {
//                输入流对象，用于与服务器进行通信(这里登录验证已经完成（具体在UserClientServer的if语句前）)
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();//若服务器没有发送message对象，则该线程会阻塞

//                获取在线用户清单
                if(message.getMessType().equals(MessgeType.MESSAGE_RET_ONLINE_FRIEND)){
                    String[] onLineUsers = message.getContent().split(" ");
                    System.out.println("当前在线用户为：");
                    for (int i = 0; i < onLineUsers.length; i++) {
                        System.out.print(onLineUsers[i] + "\t");
                    }
                }else if(message.getMessType().equals(MessgeType.MESSAGE_COMM_MES)){//接收服务器转发的聊天消息
                    System.out.println(message.getSender() + "给你发送了一条消息：");
                    System.out.println(message.getContent());
                    System.out.println(message.getSendTime());
                }else if(message.getMessType().equals(MessgeType.MESSAGE_MESSAGE_SUCCEED)){//接收私聊时服务器的返回信息，看发送成功没有
                    System.out.println(message.getContent());
                }else if(message.getMessType().equals(MessgeType.MESSAGE_MESSAGE_FAILD)){//接收私聊时服务器的返回信息，看发送成功没有
                    System.out.println(message.getContent());
                }else if(message.getMessType().equals(MessgeType.MESSAGE_TO_ALL)){
                    System.out.println(message.getContent());
                    System.out.println(message.getSendTime());
                }else if(message.getMessType().equals(MessgeType.MESSAGE_FILE)){
                    byte[] fileBytes = message.getFileBytes();
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(message.getDestPath());
                        fos.write(fileBytes);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }finally {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(message.getSendTime());
                    System.out.println(message.getSendTime() + "给你发送了文件，文件保存在了" + message.getDestPath());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Socket getSocket() {
        return socket;
    }
}
