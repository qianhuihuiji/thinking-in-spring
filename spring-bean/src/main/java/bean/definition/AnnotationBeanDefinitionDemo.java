package bean.definition;

import bean.definition.AnnotationBeanDefinitionDemo.Config;
import iocoverview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 注解 BeanDefinition 示例
 */

// 3. 通过 @Import 进行导入
@Import(Config.class)
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

        System.out.println("Config 类型的所有 Beans：" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans：" + applicationContext.getBeansOfType(User.class));

        // 显示关闭上下文
        applicationContext.close();
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
