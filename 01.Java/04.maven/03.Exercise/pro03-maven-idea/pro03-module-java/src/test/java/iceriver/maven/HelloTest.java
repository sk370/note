package iceriver.maven;

import iceriver.maventest.Hello;
import org.junit.Test;

/**
 * @author: https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/10 23:50
 */
public class HelloTest {
    @Test
    public void showMessage(){
        Hello hello = new Hello();
        hello.showMessage();
    }
}
