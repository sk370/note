package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/7 20:41
 */
public class LambdaTest {
    List<Employee> emps = Arrays.asList(
        new Employee(101, "张三", 18, 9999.99),
        new Employee(102, "李四", 59, 6666.66),
        new Employee(103, "王五", 28, 3333.33),
        new Employee(104, "赵六", 28, 7777.77),
        new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test1(){
        Collections.sort(emps, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }
    @Test
    public void test2(){
        String upper = strHandler("abcdef", (str) -> str.toUpperCase());
        System.out.println(upper);

        String newStr = strHandler("谁敢横刀立马", (str) -> str.substring(2, 5));
        System.out.println(newStr);

        System.out.println("=======非Lambda表达式形式=======");

        String upper2 = strHandler("abcdef", new MyFunction() {
            @Override
            public String getValue(String str) {
                return str.toUpperCase();
            }
        });
        System.out.println(upper2);

        String newStr2 = strHandler("谁敢横刀立马", new MyFunction() {
            @Override
            public String getValue(String str) {
                return str.substring(2, 5);
            }
        });
        System.out.println(newStr2);


    }

    //需求：用于处理字符串
    public String strHandler(String str, MyFunction mf){
        return mf.getValue(str);
    }

    @Test
    public void test3(){
        op(100L, 200L, (x, y) -> x + y);
        op(100L, 200L, (x, y) -> x * y);

        System.out.println("=======非Lambda表达式形式=======");

        op(100L, 200L, new MyFunction2<Long, Long>() {
            @Override
            public Long getValue(Long t1, Long t2) {
                return t1 + t1;
            }
        });
        op(100L, 200L, new MyFunction2<Long, Long>() {
            @Override
            public Long getValue(Long t1, Long t2) {
                return t1 * t1;
            }
        });

    }
    void op(Long l1, Long l2, MyFunction2<Long,Long> mf){
        System.out.println(mf.getValue(l1,l2));
    }
}

