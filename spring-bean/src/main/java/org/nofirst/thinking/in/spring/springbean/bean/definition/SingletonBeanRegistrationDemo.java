package org.nofirst.thinking.in.spring.springbean.bean.definition;

import org.nofirst.thinking.in.spring.springbean.bean.factory.DefaultUserFactory;
import org.nofirst.thinking.in.spring.springbean.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 单例注册示例
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 创建一个外部 UserFactory 对象
        UserFactory userFactory = new DefaultUserFactory();
//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory(); 从 beanFactory 切换成 singletonBeanRegistry
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        // 注册外部单例对象
        singletonBeanRegistry.registerSingleton("userFactory", userFactory);
        // 启动上下文
        applicationContext.refresh();

        // 依赖查找
        // 这里 applicationContext 能 getBean 是因为使用了代理，getBean() 方法里面使用了 getBeanFactory()，效果等同于下面一行
        UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);
//        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);
        System.out.println("userFactory == userFactoryByLookup : " + (userFactory == userFactoryByLookup));

        // 关闭上下文
        applicationContext.close();
    }
}
