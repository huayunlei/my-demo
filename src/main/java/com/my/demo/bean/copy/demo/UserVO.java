package com.my.demo.bean.copy.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyl
 * @create 2020-05-15
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    /**
     * userId : 1
     * userName : Van
     * age : 18
     * sex : 1
     */

    private long userId;
    private String name;
    private int age;
    private String sex;

}
