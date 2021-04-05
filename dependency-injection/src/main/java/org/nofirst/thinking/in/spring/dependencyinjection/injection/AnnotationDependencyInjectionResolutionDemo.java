package org.nofirst.thinking.in.spring.dependencyinjection.injection;

import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;
import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解驱动的依赖助理过程
 *
 * DefaultListableBeanFactory#resolveDependency
 * 1.判断是否懒加载-->doResolveDependency()
 * 2.判断是否是多类型的bean resolveMultipleBeans()
 * 3.根据类型查找匹配到的bean findAutowireCandidates()
 * 4.bean个数大于1,选择bean即@Primary修饰 determineAutowireCandidate()
 * 返回结果
 *
 * AutowiredAnnotationBeanPostProcessor 可以处理 @Autowired、@Value 和 @Inject 注解
 * CommonAnnotationBeanPostProcessor 可以处理 @Resource 和 @EJB 注解
 *
 * @date: 2021/03/29
 **/
public class AnnotationDependencyInjectionResolutionDemo {

    // 依赖查找（处理）+ 延迟
    @Autowired
    private User lazyUser;

    // 依赖查找（处理）
    // DependencyDescriptor -->
    // 必须的（required = true）
    // 是否首要（primary = true）
    // 实时注入（eager = true）
    // 通过类型（User.class）
    // 字段名称（"user"）
    @Autowired
    private User user;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private Optional<User> optionalUser;

    @Inject
    private User injectedUser;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(
                AnnotationDependencyInjectionResolutionDemo.class);

        // 期待输出 superUser
        System.out.println("demo.user: " + demo.user);
        System.out.println("demo.injectedUser: " + demo.injectedUser);
        System.out.println("demo.user == demo.injectedUser: " + (demo.user == demo.injectedUser));

        // 期待输出 user superUser （先注册的 bean 先初始化）
        System.out.println("demo.users: " + demo.users);
        // 期待输出 superUser
        System.out.println("demo.optionalUser: " + demo.optionalUser);

        // 关闭上下文
        applicationContext.close();
    }
}
