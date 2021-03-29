package org.nofirst.thinking.in.spring.dependencyinjection.injection;

import org.nofirst.thinking.in.spring.iocoverview.domain.User;

/**
 * {@link User} 的 Holder 类型
 *
 * @date: 2021/03/29
 **/
public class UserHolder {
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
