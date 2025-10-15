package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 16:09
 */
public class SearchCommon {
    public static void main(String[] args) {
        String s1 = "abcwerthelloyuiodef";
        String s2 = "cvhellobnm";
        System.out.println(getCommon(s1, s2));
    }
    public static String getCommon(String str1, String str2){
        String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
        String minStr = (str1.length() < str2.length()) ? str1 : str2;
        int length = minStr.length();
        for (int i = 0; i < length; i++) {
            for (int j = 0, k = length - i; k <=  length; j++, k++) {
                String subStr = minStr.substring(j, k);
                if(maxStr.contains(subStr)){
                    return subStr;
                }
            }
        }
        return null;
    }
}
