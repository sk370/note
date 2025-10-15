package nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 非阻塞式网络通信（加入多线程即为网络聊天室）
 * @author zhuyuqi
 * @version v0.0.1
 * @className SocketNIO
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/18 10:04
 */
public class SocketNIO {
    @Test
    public  void client() throws Exception{
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));//1. 获取通道
        socketChannel.configureBlocking(false);//2. 设置非阻塞模式
        ByteBuffer allocate = ByteBuffer.allocate(1024);//3. 指定缓冲区大小
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            allocate.put((new Date().toString() + "\n" + str).getBytes());//4. 给缓冲区存放数据
            allocate.flip();//5. 切换读写模式
            socketChannel.write(allocate);//6. 通道内写入数据
            allocate.clear();//7. 清空缓冲区
        }
        socketChannel.close();//8. 关闭通道
    }
    @Test
    public  void server() throws Exception{
        ServerSocketChannel socketChannel = ServerSocketChannel.open();//1. 获取通道
        socketChannel.configureBlocking(false);//2. 设置非阻塞模式
        socketChannel.bind(new InetSocketAddress(9898));//3. 绑定连接
        Selector selector = Selector.open();//4. 获取选择器
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);//5. 将通道注册到选择器，并监听”接收“事件
        while(selector.select() > 0){//6. 轮询式获取选择器上已经准备就绪的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();//7. 获取当前选择器中所有注册的选择键（已就绪监听事件）
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();//8. 获取就绪的事件
                if(next.isAcceptable()){//9. 判断已就绪的事件类型，做相应处理
                    SocketChannel accept = socketChannel.accept();// 10. 获取已接收就绪的事件的通道
                    accept.configureBlocking(false);// 11. 切换为非阻塞模式
                    accept.register(selector, SelectionKey.OP_READ);//12. 将该通道注册到选择器上
                }else if (next.isReadable()){ //9. 判断已就绪的事件类型，做相应处理
                    SocketChannel readChannel = (SocketChannel) next.channel();// 10. 获取已读就绪的事件的通道
                    ByteBuffer allocate = ByteBuffer.allocate(1024);//11. 指定缓冲区大小
                    int len = 0;
                    while ((len = readChannel.read(allocate)) > 0){// 12. 循环读取数据
                        allocate.flip();//13. 切换读写模式
                        System.out.println(new String(allocate.array(), 0, len));
                        allocate.clear();//14. 清空缓冲区
                    }
                }
                iterator.remove();//15. 取消选择键（取消next已就绪事件）
            }
        }
        socketChannel.close();//16. 关闭通道
    }
}
