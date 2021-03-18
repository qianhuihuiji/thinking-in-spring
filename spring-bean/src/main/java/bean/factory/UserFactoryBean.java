package bean.factory;

import iocoverview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * User Bean 的 BeanFactory 实现
 */
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
