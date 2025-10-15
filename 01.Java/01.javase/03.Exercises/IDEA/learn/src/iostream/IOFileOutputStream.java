package iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/12 8:22
 */
public class IOFileOutputStream {
    public static void main(String[] args) {
        String str = "D:\\hello.txt";
        String content = "h";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write('h');
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
