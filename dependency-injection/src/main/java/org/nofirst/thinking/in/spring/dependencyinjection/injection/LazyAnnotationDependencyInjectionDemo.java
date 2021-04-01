package org.nofirst.thinking.in.spring.dependencyinjection.injection;

import java.util.Set;
import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link ObjectProvider} 示例
 *
 * @date: 2021/03/29
 **/
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // 实时依赖注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟依赖注入

    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        // 期待输出 superUser
        System.out.println("demo.user: " + demo.user);
        // 期待输出 superUser
        System.out.println("demo.userObjectProvider.getObject: " + demo.userObjectProvider.getObject());
        // 期待输出 superUser user
        System.out.println("demo.usersObjectFactory.getObject: " + demo.usersObjectFactory.getObject());

        // 集合查找
        demo.userObjectProvider.forEach(System.out::println);

        // 关闭上下文
        applicationContext.close();
    }
}
