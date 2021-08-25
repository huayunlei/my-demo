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
    private int id;

    private String name;

    private int age;

    private int gender;

}
