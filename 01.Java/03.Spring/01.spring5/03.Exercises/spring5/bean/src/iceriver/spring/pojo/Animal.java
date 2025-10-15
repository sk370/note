package iceriver.spring.pojo;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Animal
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/09/23 12:48
 */
public class Animal implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User();
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
