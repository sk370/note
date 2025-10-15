package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 10:04
 */
public class UDPSampleSend {
    public static void main(String[] args) throws IOException {
//        1. 指定接收端口——这里也被称作接收端口是因为UDP没有服务端、客户端的概念
        DatagramSocket socket = new DatagramSocket(9998);
//        2. 创建需要发送的数据及对象
        byte[] data = "Hello, UDPReceive".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.101.27"), 9999);
//        3. 发送数据
        socket.send(datagramPacket);
    //        接收反馈信息
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        int length = packet.getLength();
        byte[] receive = packet.getData();
        System.out.println(new String(receive, 0, length));

//        4. 关闭资源
        socket.close();
    }
}
