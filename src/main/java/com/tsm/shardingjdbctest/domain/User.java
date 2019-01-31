package com.tsm.shardingjdbctest.domain;

import java.util.function.Supplier;

/**
 * @Author: 江千月
 * @Date: 2019/1/24 16:03
 * @Version 1.0
 */
public class User {

    Integer id;
    String name;
    Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
