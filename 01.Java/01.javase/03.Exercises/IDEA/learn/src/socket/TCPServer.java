package socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/14 20:07
 */
public class TCPServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();//主机会在由客户端连接时返回Socket类对象，此时还未返回
        System.out.println("由客户端请求连接！");
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while((readLen = inputStream.read(buf)) != -1){
            System.out.println(new String(buf, 0, readLen));
        }
        inputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("客户端停止请求！");
    }
}
