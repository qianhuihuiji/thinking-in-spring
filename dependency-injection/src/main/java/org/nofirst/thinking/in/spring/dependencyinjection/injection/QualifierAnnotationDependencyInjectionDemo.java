package org.nofirst.thinking.in.spring.dependencyinjection.injection;

import java.util.Collection;
import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link Qualifier}
 *
 * @date: 2021/03/29
 **/
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // 会找到 superUser，因为它标注了 primary = true

    @Autowired
    @Qualifier("user") // 指定 Bean 名称
    private User namedUser;

    // 整体上下文存在 4 个 User 类型的 Bean
    // superUser
    // user
    // user1 -> @Qualifier
    // user2 -> @Qualifier

    @Autowired
    private Collection<User> allUsers; // 4 个 bean

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers; // 2 beans = user1 + user2;

    @Bean
    @Qualifier // 进行逻辑分组
    public static User user1() { // 加上 static 会让 Bean 提前初始化，如果不加的话，框架默认先加载 XML 配置中注册的 Bean，导致 allUsers 找不到配置类中的 user1 user2 这两个 Bean
        User user = new User();
        user.setId(7L);

        return user;
    }

    @Bean
    @Qualifier // 进行逻辑分组
    public static User user2() {
        User user = new User();
        user.setId(8L);

        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        // 期待输出 superUser
        System.out.println("demo.user: " + demo.user);
        // 期待输出 user
        System.out.println("demo.namedUser: " + demo.namedUser);
        // 期待输出 superUser user user1 user2
        System.out.println("demo.allUsers: " + demo.allUsers);
        // 期待输出 user1 user2
        System.out.println("demo.qualifiedUsers: " + demo.qualifiedUsers);

        // 关闭上下文
        applicationContext.close();
    }
}
