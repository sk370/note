package date;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 10:35
 */
public class CheckWhatDoing {
    public static void main(String[] args) {
        String str = "1990-01-01";
//        JDK8之前
        String str1 = str + " 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date;
        try {
            date = simpleDateFormat.parse(str1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(date);
        long time = date.getTime();
        Date now = new Date(90, 00, 03, 00, 00);
        System.out.println(now);
        long time1 = now.getTime();
        long s = time1 - time;
        System.out.println(s);
        double days = s / 1000/ 60.0 / 60 / 24 % 5;
        System.out.println(days);
        if(days < 5){
            if(days < 3){
                System.out.println("正在打渔");
            }else {
                System.out.println("正在晒网");
            }
        }

//        JDK8之后不会写
    }
}
