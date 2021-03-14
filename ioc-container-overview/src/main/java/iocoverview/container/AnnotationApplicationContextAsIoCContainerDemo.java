package iocoverview.container;

import iocoverview.domain.User;
import java.util.Map;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 注解能力 {@link ApplicationContext} 作为 IoC 容器示例
 */
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找
        lookupCollectionByType(applicationContext);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(10L);
        user.setName("emen10");

        return user;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory  listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有 User 集合对象：" + users);
        }
    }
}
