package bean.definition;

import bean.factory.UserFactory;
import iocoverview.domain.User;
import java.util.Iterator;
import java.util.ServiceLoader;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 特殊的 Bean 实例化示例
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置 xml 配置文件
        // 启动 Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        demoServiceLoader();
    }

    private static void demoServiceLoader() {
        // 从 META-INF/services/ 里面取 Factory 接口
        // 文件里面重复定义，会去重，而不是注册多个 Bean
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
