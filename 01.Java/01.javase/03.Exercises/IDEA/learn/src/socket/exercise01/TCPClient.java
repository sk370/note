package socket.exercise01;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 11:39
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        OutputStream outputStream = socket.getOutputStream();
        String name = "我是INFINITY";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(name);
        writer.newLine();
        writer.flush();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        bufferedReader.close();
        writer.close();
        socket.close();
    }
}
