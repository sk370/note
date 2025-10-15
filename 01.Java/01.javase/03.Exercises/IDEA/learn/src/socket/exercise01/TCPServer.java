package socket.exercise01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 11:16
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("你好");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedReader.close();
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }
}
