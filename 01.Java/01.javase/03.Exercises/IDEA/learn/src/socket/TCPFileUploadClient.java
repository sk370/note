package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 7:32
 */
public class TCPFileUploadClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
//        读入图片信息
        String srcPath = "D:\\IDEA\\learn\\src\\socket\\1-200Q121423M44.jpg";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcPath));
        byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);
//        给服务器器发送图片数据
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(bytes);
//      接收服务器反馈的信息
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);

        bufferedInputStream.close();
        bufferedOutputStream.close();
        inputStream.close();
        socket.close();
    }
}
