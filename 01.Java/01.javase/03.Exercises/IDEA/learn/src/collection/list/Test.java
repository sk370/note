package collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/5 18:25
 */
public class Test {
    public static void main(String[] args) {
        List list = new ArrayList();
        Vector vector = new Vector();
        vector.add(null);
        vector.add(null);
        System.out.println(list.toString());
        System.out.println(vector);
        System.out.println(vector.toString());
        System.out.println(vector.get(0));
        System.out.println(vector.get(1));
    }
}
