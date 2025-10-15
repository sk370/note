package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 7:32
 */
public class TCPFileUploadServer {
    public static void main(String[] args) throws Exception {
        ServerSocket sreverSocket = new ServerSocket(8888);
        Socket socket = sreverSocket.accept();
//      接收客户端传入的数据
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);
//      将客户端接收的数据写入文件
        String destPath = "D:\\IDEA\\learn\\src\\socket\\copy.jpg";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destPath));
        bufferedOutputStream.write(bytes);
//      向客户端回复信息
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("图片收到！");
        writer.flush();
        socket.shutdownOutput();

        bufferedInputStream.close();
        bufferedOutputStream.close();
        writer.close();
        socket.close();
        sreverSocket.close();
    }
}
