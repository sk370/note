package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/14 20:13
 */
public class TCPClient3 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);//连接指定的服务器成功时才会返回Socket对象
        System.out.println("服务器连接成功！");

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Hello, server");
        bufferedWriter.newLine();//插入一个换行符，表示写入结束，同时对方必须使用readLine()：【结束标志】
        bufferedWriter.flush();//必须刷新一下，不然会写入失败

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
        System.out.println("断开服务器连接！");
    }
}
