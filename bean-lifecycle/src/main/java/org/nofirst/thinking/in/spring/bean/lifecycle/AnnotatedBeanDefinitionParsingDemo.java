package org.nofirst.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解实现的 BeanDefinition 示例
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 Java 注解 AnnotatedBeanDefinitionReader 实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类（非 @Component class）
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        System.out.println("已加载 BeanDefinition 数量： " + (beanDefinitionCountAfter - beanDefinitionCountBefore));

        // 普通的 Class 作为 Component 注册到 Spring IoC 容器之后，通常名称为 annotatedBeanDefinitionParsingDemo
        // Bean 名称生成来自于 BeanNameGenerator，注解实现：AnnotationBeanNameGenerator#generateBeanName
        AnnotatedBeanDefinitionParsingDemo annotatedBeanDefinitionParsingDemo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo",
                AnnotatedBeanDefinitionParsingDemo.class);
        
        System.out.println(annotatedBeanDefinitionParsingDemo);
    }
}
