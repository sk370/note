package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 15:38
 */
public class CountTimes {
    public static void main(String[] args) {
        String s1 = "abkkcadkabkebfkabkskab";
        String s2 = "ab";
        System.out.println(getCount(s1, s2));
    }

    public static int getCount(String mainStr, String subString) {
        int count = 0;
        if (mainStr.length() >= subString.length()) {
            while (mainStr.indexOf(subString) != -1) {
                count++;
                mainStr = mainStr.substring(mainStr.indexOf(subString) + subString.length());
            }
        }
        return count;
    }
}
