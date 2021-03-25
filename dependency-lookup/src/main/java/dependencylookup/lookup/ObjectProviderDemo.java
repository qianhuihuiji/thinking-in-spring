package dependencylookup.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@Link ObjectProvider } Demo
 */
public class ObjectProviderDemo { // @Configuration 是非必须配置

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类
        applicationContext.register(ObjectProviderDemo.class);
        // 启动上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupByObjectProvider(applicationContext);

        // 关闭上下文
        applicationContext.close();
    }

    @Bean
    public String helloWord() { // 方法名就是 Bean 名称 = "helloWord"
        return "Hello,world!";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
