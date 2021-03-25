package org.nofirst.thinking.in.spring.springbean.bean.definition;

import org.nofirst.thinking.in.spring.springbean.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean GC Demo
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory 实例
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(BeanInitializationDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        // 显示关闭上下文
        applicationContext.close();
        // 触发垃圾回收
        userFactory = null;
        System.gc();
        Thread.sleep(500L);
    }
}
