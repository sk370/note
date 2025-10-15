package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 10:04
 */
public class UDPSampleReceive {
    public static void main(String[] args) throws IOException {
//        1. 指定接收端口
        DatagramSocket socket = new DatagramSocket(9999);
//        2. 创建接收数据的对象
        byte[] buf = new byte[1024];//UDP协议最大的数据报64k，1024 * 64 Byte
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
//        3. 调用接收方法（给socket对象添加数据），未接收到消息时进入阻塞状态
        socket.receive(packet);
//        4. 使用packet的方法，对数据进行拆包
        int length = packet.getLength();
        byte[] data = packet.getData();
//        5. 生成字符串信息
        String string = new String(data, 0, length);
        System.out.println(string);
    //     创建反馈信息
        byte[] send = "Hello, UDPSend".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(send, send.length, InetAddress.getByName("192.168.101.27"), 9998);
        socket.send(datagramPacket);

//        6.关闭资源
        socket.close();
    }
}
