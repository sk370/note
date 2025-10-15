package string;

import javax.print.attribute.standard.PrinterName;
import java.util.Locale;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/4 21:50
 */
public class StringFormat {
    public static void main(String[] args) {
        printName("Han shun Ping");
    }
    public static void printName(String str){
        if(str == null){
            System.out.println("不能为空");
            return;
        }
        String names[] = str.split(" ");
        if(names.length != 3){
            System.out.println("名字长度不对");
        }
        System.out.println(str.format("%s,%s .%c", names[2], names[0], names[1].toUpperCase().charAt(0)));
    }
}
