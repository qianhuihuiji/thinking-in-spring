package org.nofirst.thinking.in.spring.dependencylookup.lookup;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@Link NoUniqueBeanDefinitionException} 示例代码
 **/
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        // 启动上下文
        applicationContext.refresh();

        try {
            // 由于上下文里面存在两个 String 类型的 Bean，所以通过类型查找会报错
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("上下文存在 %d 个 %s 类型的 Bean，具体原因是：%s%n",
                    e.getNumberOfBeansFound() ,
                    String.class.getName(),
                    e.getMessage());
        }

        // 关闭上下文
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "1";
    }

    @Bean
    public String bean2() {
        return "2";
    }

    @Bean
    public String bean3() {
        return "3";
    }
}
