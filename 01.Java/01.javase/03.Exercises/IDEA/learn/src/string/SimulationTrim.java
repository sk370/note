package string;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 14:50
 */
public class SimulationTrim {
    public static void main(String[] args) {
        String str = "  sfaa adfa ";
        int len = 0, startIndex = 0, endIndex = 0;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if(c[i] != ' '){
                startIndex = i;
                break;
            }
        }
        for (int i = c.length - 1; i >= 0; i--) {
            if(c[i] != ' '){
                endIndex = i;
                break;
            }
        }
        len = endIndex - startIndex;
        char[] cs = new char[len+1];
        for (int i = startIndex; i <= endIndex; i++) {
            cs[i - startIndex] = c[i];
        }
        String s = new String(cs);
        System.out.println(s);
    }
}
