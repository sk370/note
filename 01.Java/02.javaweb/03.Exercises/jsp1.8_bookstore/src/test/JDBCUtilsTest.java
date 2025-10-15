package test;

import org.junit.jupiter.api.Test;
import utils.JDBCUtils;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/3 15:18
 */
public class JDBCUtilsTest {
    @Test
    public void testJDBCUtils(){
        System.out.println(JDBCUtils.getConnection());
    }
}
