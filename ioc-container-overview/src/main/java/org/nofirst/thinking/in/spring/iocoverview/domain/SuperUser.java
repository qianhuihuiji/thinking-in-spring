package org.nofirst.thinking.in.spring.iocoverview.domain;

import org.nofirst.thinking.in.spring.iocoverview.annotation.Super;

/**
 * 超级用户
 */

@Super
public class SuperUser extends User{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}