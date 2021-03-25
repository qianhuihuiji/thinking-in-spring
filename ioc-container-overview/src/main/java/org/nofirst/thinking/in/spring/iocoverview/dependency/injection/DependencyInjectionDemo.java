package org.nofirst.thinking.in.spring.iocoverview.dependency.injection;

import org.nofirst.thinking.in.spring.iocoverview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 xml 配置文件
        // 启动 Spring 上下文
//        BeanFactory applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        // 依赖来源一：自定义 Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

//        System.out.println(userRepository.getUsers());

        // 依赖来源二：内建依赖
        // 依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());

        ObjectFactory userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject());
        System.out.println(applicationContext == userFactory.getObject());

        // 依赖查找（错误示例）
//        System.out.println(applicationContext.getBean(BeanFactory.class));

        // 依赖来源三： 内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean:" + environment);
    }

    private static void whoIsIocContainer(BeanFactory beanFactory, UserRepository userRepository) {
        System.out.println(beanFactory == userRepository.getBeanFactory());
    }
}
