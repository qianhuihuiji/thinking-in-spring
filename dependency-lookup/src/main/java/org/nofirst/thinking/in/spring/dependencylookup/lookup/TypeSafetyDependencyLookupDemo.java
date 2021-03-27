package org.nofirst.thinking.in.spring.dependencylookup.lookup;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全依赖查找示例
 *
 * @date: 2021-03-27 23:03
 **/
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        // 启动上下文
        applicationContext.refresh();
        // 演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);

        // 关闭上下文
        applicationContext.close();
    }

    private static void displayObjectFactoryGetObject(BeanFactory beanFactory) {
        // ObjectProvider is ObjectFactory
        ObjectFactory<User> userObjectFactory = beanFactory.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> userObjectFactory.getObject());
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("Source from: " + source);
        try {
            runnable.run();
        }catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
