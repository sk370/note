package generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/9 22:27
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        System.out.println(list);
        list.add("123");
        System.out.println(list);
        for(String str : list){
            System.out.println(str);
        }
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next =  iterator.next();
            System.out.println(next);
        }

    }
}
