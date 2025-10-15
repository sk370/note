package date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/2 15:10
 */
public class DateFormat {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String s = simpleDateFormat.format(date);
        System.out.println(s);
        System.out.println(simpleDateFormat);

    }
}
