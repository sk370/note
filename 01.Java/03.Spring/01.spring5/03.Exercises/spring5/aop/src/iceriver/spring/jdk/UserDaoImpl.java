package iceriver.spring.jdk;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 7:56
 */
public class UserDaoImpl implements UserDao{
    @Override
    public int add(int a, int b) {
        System.out.println("userdaoimpl 的 add ……");
        return a + b;
    }

    @Override
    public String update(String id) {
        System.out.println("userdaoimpl 的 update ……");
        return id;
    }
}
