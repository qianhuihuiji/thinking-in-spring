package bean.factory;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;

/**
 * 默认实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {

    // 1. 基于 @PostConstruct 注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory 初始化中...");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法 ： UserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() ： UserFactory 初始化中...");
    }
}
