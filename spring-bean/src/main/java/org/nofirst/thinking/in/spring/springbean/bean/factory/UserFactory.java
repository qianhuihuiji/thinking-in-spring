package org.nofirst.thinking.in.spring.springbean.bean.factory;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;

/**
 * {@Link User} 工厂类
 */
public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
