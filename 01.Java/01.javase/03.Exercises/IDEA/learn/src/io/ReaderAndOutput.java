package io;

import sun.text.normalizer.UTF16;

import java.io.*;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/13 17:43
 */
public class ReaderAndOutput {
    public static void main(String[] args) throws IOException {
        int count = 0;
        String filePath = "D:\\mytemp\\hello.txt";//该文件为gbk编码
        String line = "";
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath, "GBK"));//jdk11可用
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));

        while((line = bufferedReader.readLine()) != null){
            System.out.println(++count + line);
        }
        if(bufferedReader != null){
            bufferedReader.close();
        }
    }
}
