package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/4 22:04
 */
public class CountString {
    public static void main(String[] args) {
        String str= "jafaIJIOPADIOFJAO2123189IHDFJlpdklfjaj10e3";
        int numCount=0, lowerCount=0, upperCount=0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                numCount++;
            }
            if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                lowerCount++;
            }
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                upperCount++;
            }
        }
        System.out.println("数字：" + numCount + "，小写字母：" + lowerCount + "，大写字母：" + upperCount);
    }
}
