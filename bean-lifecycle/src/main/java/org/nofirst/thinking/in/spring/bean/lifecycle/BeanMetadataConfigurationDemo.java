package org.nofirst.thinking.in.spring.bean.lifecycle;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 元信息注册 BeanDefinition 示例
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化基于 Properties 资源的 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        // properties 文件默认以 ASCII 码读取，而 properties 默认是 utf-8
        // 如果不指定编码会产生乱码
        // 以下演示乱码读取
//        String notEncodeLocation = "classpath:/META-INF/user.properties";
//        // 加载  properties 资源
//        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(notEncodeLocation);
//        System.out.println("已加载 BeanDefinition 数量： " + beanNumbers);
//        // 通过 Bean id 和 类型进行依赖查找
//        User user = beanFactory.getBean("user", User.class);
//        System.out.println(user);

        // 以下演示正确输出
        String encodeLocation = "META-INF/user.properties";
        // 基于 ClassPath 加载 properties 资源
        Resource resource = new ClassPathResource(encodeLocation);
        // 指定字符编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        // 加载  properties 资源
        // 注：beanDefinitionReader.loadBeanDefinitions() 在本例中只会生效一次，不行可以把乱码的演示注释去掉，试一试
        int beanNumbers2 = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载 BeanDefinition 数量： " + beanNumbers2);

        // 通过 Bean id 和 类型进行依赖查找
        User user2 = beanFactory.getBean("user", User.class);
        System.out.println(user2);
    }
}
