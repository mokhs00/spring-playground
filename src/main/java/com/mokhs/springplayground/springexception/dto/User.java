package com.mokhs.springplayground.springexception.dto;


import org.springframework.lang.NonNull;

import javax.validation.constraints.*;

public class User {

    @Size(min = 1, max = 10)
    private String name;

    @NotNull
    @Min(1)
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
