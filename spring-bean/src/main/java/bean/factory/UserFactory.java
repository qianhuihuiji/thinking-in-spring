package bean.factory;

import iocoverview.domain.User;

/**
 * {@Link User} 工厂类
 */
public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
