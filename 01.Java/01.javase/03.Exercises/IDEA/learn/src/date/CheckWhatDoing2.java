package date;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 10:35
 */
public class CheckWhatDoing2 {
    public static void main(String[] args) {
        String str = "1990-01-01";
        String str1 = str + " 00:00:00";
        LocalDateTime startDay = LocalDateTime.parse(str1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime now1 = LocalDateTime.now();
        int days1 = (int) Duration.between(startDay, now1).toDays();
        System.out.println(days1);
        if ((days1 % 5) < 3) {
            System.out.println("正在打渔");
        } else {
            System.out.println("正在晒网");
        }
    }
}
