package org.nofirst.thinking.in.spring.dependencyinjection.injection;

import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;
import org.nofirst.thinking.in.spring.dependencyinjection.injection.annotation.InjectedUser;
import org.nofirst.thinking.in.spring.dependencyinjection.injection.annotation.MyAutowired;
import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

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

//    @Autowired
    @MyAutowired
    private Optional<User> optionalUser;

    @Inject
    private User injectedUser;

    @InjectedUser
    private User myInjectedUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor () {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // @Autowired + @Inject + 新注解 @InjectedUser
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectedUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//
//        return beanPostProcessor;
//    }


    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    // 优先处理自定义注解，默认的 AutowiredAnnotationBeanPostProcessor 也会注册，去处理 @Autowired、@Value、@Inject
    // 见 AnnotationConfigUtils 170 行
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor () {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);

        return beanPostProcessor;
    }

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
        // 期待输出 superUser
        System.out.println("demo.myInjectedUser: " + demo.myInjectedUser);

        // 关闭上下文
        applicationContext.close();
    }
}
