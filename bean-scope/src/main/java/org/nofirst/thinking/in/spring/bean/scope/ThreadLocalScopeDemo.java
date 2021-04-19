package org.nofirst.thinking.in.spring.bean.scope;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义 Scope 示例
 *
 * @date: 2021/04/08
 **/
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public static User user() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());

        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册自定义 scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        // 启动上下文
        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);

        // 关闭上下文
        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0;i < 3;i++) {
            // user 是线程共享对象，因此相同的线程的对象是相同的
            User user = applicationContext.getBean("user", User.class);
            System.out.printf("[Thread id : %d] User = %s%n", Thread.currentThread().getId(), user);
        }


            for (int i = 0;i < 3;i++) {
            Thread thread = new Thread(() -> {
                // user 是线程共享对象，因此不同的线程的对象是不同的
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id : %d] User = %s%n", Thread.currentThread().getId(), user);
            });

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
