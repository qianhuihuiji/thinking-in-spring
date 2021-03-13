package iocoverview.dependency.injection;

import iocoverview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入示例
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 xml 配置文件
        // 启动 Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);

//        System.out.println(userRepository.getUsers());

        // 依赖注入
//        System.out.println(userRepository.getBeanFactory());
//        System.out.println(beanFactory == userRepository.getBeanFactory());

        ObjectFactory userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject());
        System.out.println(beanFactory == userFactory.getObject());

        // 依赖查找（错误示例）
//        System.out.println(beanFactory.getBean(BeanFactory.class));
    }
}
