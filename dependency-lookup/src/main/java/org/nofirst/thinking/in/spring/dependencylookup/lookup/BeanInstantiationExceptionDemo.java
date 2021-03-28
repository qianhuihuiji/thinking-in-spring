package org.nofirst.thinking.in.spring.dependencylookup.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@Link BeanInstantiationException} 示例代码
 **/
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 BeanDefinition
        // Bean Class 是一个 CharSequence 接口
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("error-bean", beanDefinitionBuilder.getBeanDefinition());

        // 启动上下文
        applicationContext.refresh();

        // 关闭上下文
        applicationContext.close();
    }
}
