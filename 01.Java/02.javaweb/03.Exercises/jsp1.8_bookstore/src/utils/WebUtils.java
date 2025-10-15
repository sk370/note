package utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 15:59
 */
public class WebUtils<T> {
    /**
     * 把map中的值注入到JavaBean中
     * @param bean
     * @param map 参数名必须要与JavaBean中的参数名一致，因为populate展开参数时，默认调用JavaBean中的setter方法，如果名称不一致，相当于没有这个参数
     */
    public static <T> T copyParamToBean(T bean, Map map){
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
//    public static void copyParamToBean(Object bean, Map map){
//        try {
//            BeanUtils.populate(bean, map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 转换参数,获取参数值或者取默认值
     * @param str
     * @param n 默认值
     * @return 参数值或默认值
     */
    public static int parseInt(String str, int n){
        try {
            if(str==null){
                return n;
            }else {
                return Integer.parseInt(str);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return n;
    }
}
