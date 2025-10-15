package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/13 17:22
 */
public class CreateFile {
    public static void main(String[] args) throws IOException {
        String directPath = "d:\\mytemp";
        File file = new File(directPath);
        if(!file.exists()){
            if(file.mkdirs()){
                System.out.println("创建" + directPath + "成功");
            }else {
                System.out.println("创建" + directPath + "失败");
            }
        }

        String filePath = directPath + "\\hello.txt";
        file = new File(filePath);
        if(!file.exists()){
            if(file.createNewFile()){
                System.out.println("创建" + filePath + "成功");


            }else{
                System.out.println("创建" + filePath + "失败");
            }
        }else{
            System.out.println(filePath + "文件已经存在");
        }

        if(file.exists()){
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write("helloworld");
            fileWriter.close();
        }
    }
}
