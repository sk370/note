package iceriver.spring.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 7:58
 */
public class JDKProxy {
    public static void main(String[] args) {
        Class[] interfaces = {UserDao.class};
//        1. 方式一：第三个参数使用匿名内部类
//        Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        });
//      2. 方式二：第三个参数使用代理类
        UserDao userDao = new UserDaoImpl();
        UserDao dao = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
        int result = dao.add(2, 3);
        System.out.println(result);
    }
}

/**
 * 创建代理对象
 */
class UserDaoProxy implements InvocationHandler{
//    增强原始类功能的代码
    private Object obj;

    public UserDaoProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法之前执行……" + method.getName() + "，传递的参数：" + Arrays.toString(args));

        Object res = method.invoke(obj, args);

        System.out.println("方法之后执行" + obj);

        return res;
    }
}