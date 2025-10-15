package nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className TestPipe
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/18 10:57
 */
public class TestPipe {
    @Test
    public void test01() throws IOException {
        Pipe pipe = Pipe.open();
        // 向管道中写入数据
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        Pipe.SinkChannel sinkChannel = pipe.sink();
        allocate.put("通过管道单向发送数据".getBytes());
        allocate.flip();
        sinkChannel.write(allocate);
        // 读取缓冲区中的数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        allocate.flip();
        int len = sourceChannel.read(allocate);
        System.out.println(new String(allocate.array(), 0, len));
        sourceChannel.close();
        sinkChannel.close();
    }
}
