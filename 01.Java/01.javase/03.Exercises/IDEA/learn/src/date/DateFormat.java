package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/4 22:49
 */
public class DateFormat {
    public static void main(String[] args) {
        String str = "2020-09-08";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(date);
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        System.out.println(date1);
    }
}
