package com.my.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hyl
 * @create 2020-05-15
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    private String name;

    private int age;

    private boolean isMan;

    private int id;


    public static void main(String[] args) {
        List<User> list = new ArrayList<>();


        Map<String, User> map = list.stream().filter(User::isMan).collect(Collectors.toMap(User::getName, user -> {
            return user;
        }));
    }

}
