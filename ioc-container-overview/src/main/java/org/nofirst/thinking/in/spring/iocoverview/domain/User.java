package org.nofirst.thinking.in.spring.iocoverview.domain;

import org.nofirst.thinking.in.spring.iocoverview.enums.City;
import org.springframework.core.io.Resource;

/**
 * 用户类
 */
public class User {
    private Long id;

    private String name;

    private City city;

    private Resource configFileLocation;

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", configFileLocation=" + configFileLocation +
                '}';
    }
    public static User createUser() {
        User user = new User();
        user.setName("emen-create-user");
        user.setId(100L);
        return user;
    }
}
