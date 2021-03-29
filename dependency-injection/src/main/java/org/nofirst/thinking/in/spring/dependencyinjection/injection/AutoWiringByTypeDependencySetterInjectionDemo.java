package org.nofirst.thinking.in.spring.dependencyinjection.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * "byType" 自动绑定实现的示例
 * 因为 superUser Bean 被标注了 primary="true"，所以尽管 User 类型有两个 Bean，也不会报错
 *
 * @author: <a href="https://git.code.oa.com/u/emenmei">emenmei</a>
 * @date: 2021/03/29
 **/
public class AutoWiringByTypeDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META-INF/autowiring-by-type-dependency-setter-injection.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 依赖查找并且创建 Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
