package org.nofirst.thinking.in.spring.dependencyinjection.injection;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解驱动的依赖助理过程
 *
 * @date: 2021/03/29
 **/
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired         // 依赖查找（处理）
    private User user; // DependencyDescriptor -->
                       // 必须的（required = true）
                       // 是否首要（primary = true）
                       // 实时注入（eager = true）
                       // 通过类型（User.class）
                       // 字段名称（"user"）

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

        // 关闭上下文
        applicationContext.close();
    }
}
