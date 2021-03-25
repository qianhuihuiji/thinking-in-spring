package org.nofirst.thinking.in.spring.dependencylookup.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        // 启动上下文
        applicationContext.refresh();

        // 关闭上下文
        applicationContext.close();
    }
}
