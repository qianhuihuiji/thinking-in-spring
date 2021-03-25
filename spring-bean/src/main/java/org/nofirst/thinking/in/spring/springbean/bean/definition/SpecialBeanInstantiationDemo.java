package org.nofirst.thinking.in.spring.springbean.bean.definition;


import java.util.Iterator;
import java.util.ServiceLoader;

import org.nofirst.thinking.in.spring.springbean.bean.factory.DefaultUserFactory;
import org.nofirst.thinking.in.spring.springbean.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 特殊的 Bean 实例化示例
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置 xml 配置文件
        // 启动 Spring 上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        // 通过 ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);

        displayServiceLoader(serviceLoader);

//        demoServiceLoader();

        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

    private static void demoServiceLoader() {
        // 从 META-INF/services/ 里面取 Factory 接口
        // 文件里面重复定义，会去重，而不是注册多个 Bean
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
