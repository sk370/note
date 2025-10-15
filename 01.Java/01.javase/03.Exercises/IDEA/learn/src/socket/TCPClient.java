package socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/14 20:13
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);//连接指定的服务器成功时才会返回Socket对象
        System.out.println("服务器连接成功！");
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Hello, server".getBytes());
        outputStream.close();
        socket.close();
        System.out.println("断开服务器连接！");
    }
}
