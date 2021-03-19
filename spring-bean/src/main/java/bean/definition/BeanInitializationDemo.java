package bean.definition;

import bean.factory.DefaultUserFactory;
import bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 实例化 Demo
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 实例
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(BeanInitializationDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 非延迟初始化在 Spring 应用上下文启动时，被初始化
        // 延迟加载是依赖查找触发了初始化
        System.out.println("Spring 应用上下文已启动...");
        // 依赖查找
//        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
//        System.out.println(userFactory);
        // 显示关闭上下文
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
//    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
