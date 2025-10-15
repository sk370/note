package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/14 20:07
 */
public class TCPServer3 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();//主机会在由客户端连接时返回Socket类对象，此时还未返回
        System.out.println("由客户端请求连接！");

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Hello, client");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        serverSocket.close();
        System.out.println("客户端停止请求！");
    }
}
