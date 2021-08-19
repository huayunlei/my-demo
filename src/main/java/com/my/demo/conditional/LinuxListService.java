package com.my.demo.conditional;

/**
 * @author hyl
 * @create 2020-08-01
 * @version: branch_member_20200601_v3_7_1
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
