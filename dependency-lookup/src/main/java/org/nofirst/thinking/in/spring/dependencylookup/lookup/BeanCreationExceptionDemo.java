package org.nofirst.thinking.in.spring.dependencylookup.lookup;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@Link BeanCreationException} 示例代码
 **/
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 BeanDefinition
        // Bean Class 是一个 POJO 普通类
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("error-bean", beanDefinitionBuilder.getBeanDefinition());

        // 启动上下文
        applicationContext.refresh();

        // 关闭上下文
        applicationContext.close();
    }

    static class POJO implements InitializingBean {
        @PostConstruct
        public void init() throws Throwable { // CommonAnnotationBeanPostProcessor 处理，因此会先执行，因而 afterPropertiesSet 就不会执行了
            throw new Throwable("init() : For Purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet() : For Purpose...");

        }
    }
}
