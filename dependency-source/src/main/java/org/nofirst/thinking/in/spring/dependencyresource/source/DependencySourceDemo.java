package org.nofirst.thinking.in.spring.dependencyresource.source;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * 依赖来源示例
 *
 * @date: 2021/04/06
 **/
public class DependencySourceDemo {

    // 注入在 AutowiredAnnotationBeanPostProcessor#postProcessProperties 方法执行，早于 setter 注入，也早于 @PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println("beanFactory == applicationContext : " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBean : " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext : " + (resourceLoader == applicationContext));
        System.out.println("beanFactory == applicationEventPublisher : " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
        getBean(ApplicationContext.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型：" + beanType + " 无法在 BeanFactory 中查找！");
        }

        return null;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        applicationContext.register(DependencySourceDemo.class);

        // 启动上下文
        applicationContext.refresh();

        // 关闭上下文
        applicationContext.close();
    }
}
