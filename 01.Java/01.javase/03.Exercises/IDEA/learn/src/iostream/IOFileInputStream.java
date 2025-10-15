package iostream;

import java.io.*;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/12 8:22
 */
public class IOFileInputStream {
    public static void main(String[] args) {
        String str = "D:\\hello.txt";
        int content = -1;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(str);
            while ((content = fileInputStream.read()) != -1){
                System.out.print((char)content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
