package myssm.listener;

import myssm.ioc.BeanFactory;
import myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/24 11:28
 */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext applicationContext = servletContextEvent.getServletContext();
        String contextConfigLocation = applicationContext.getInitParameter("contextConfigLocation");

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(contextConfigLocation);
        applicationContext.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
