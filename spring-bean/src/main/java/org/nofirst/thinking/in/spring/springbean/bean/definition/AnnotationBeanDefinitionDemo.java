package org.nofirst.thinking.in.spring.springbean.bean.definition;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注解 BeanDefinition 示例
 */

// 3. 通过 @Import 进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 实例
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 1. 通过 Bean 方式定义
        // 2. 通过 @Component 方式
        // 3. 通过 @Import 进行导入

        // 通过 BeanDefinition 注册 API 实现
        // 1. 命名 Bean 的注册方式
        registerUserBeanDefinition(applicationContext, "emenmei-user"); // 名称如果跟别名重复，也只会注册一个 Bean，例如这里的名称改为 emen-user
        // 2. 非命名 Bean 的注册方式
        registerUserBeanDefinition(applicationContext);

        System.out.println("Config 类型的所有 Beans：" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans：" + applicationContext.getBeansOfType(User.class));

        // 显示关闭上下文
        applicationContext.close();
    }

    /**
     * 命名 Bean 的注册方式
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "emen");

        // 如果参数 beanName 存在时
        if (StringUtils.hasText(beanName)) {
            // 注册 BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名 Bean 注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    // 2. 通过 @Component 方式
    @Component
    public static class Config {

        /**
         * 通过注解的方式注册了一个 Bean
         */
        @Bean(name = {"user", "emen-user"})
        public User user() {
            User user = new User();
            user.setId(10L);
            user.setName("emen10");

            return user;
        }
    }
}
