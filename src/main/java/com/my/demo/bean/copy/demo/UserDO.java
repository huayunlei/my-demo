package com.my.demo.bean.copy.demo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author hyl
 * @create 2020-05-15
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    /**
     * userId : 1
     * userName : Van
     * age : 18
     * sex : 1
     */

    private long userId;
    private String userName;
    private int age;
    private int sex;

    public static void main(String[] args) {
        UserDO userDO = new UserDO();
    }
}
