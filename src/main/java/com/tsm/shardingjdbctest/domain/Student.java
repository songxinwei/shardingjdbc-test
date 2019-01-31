package com.tsm.shardingjdbctest.domain;

/**
 * @Author: walkingWind
 * @Date: 2019/1/17 13:53
 * @Version 1.0
 */
public class Student {
    Long id;
    String name;
    String age;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
