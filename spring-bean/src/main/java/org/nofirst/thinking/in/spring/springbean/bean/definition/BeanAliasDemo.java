package org.nofirst.thinking.in.spring.springbean.bean.definition;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        // 配置 xml 配置文件
        // 启动 Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

        User user = beanFactory.getBean("user", User.class);
        // 通过别名来查找
        User emenUser = beanFactory.getBean("emen-user", User.class);

        System.out.println("两者是否相等：" + (user == emenUser));
    }
}
