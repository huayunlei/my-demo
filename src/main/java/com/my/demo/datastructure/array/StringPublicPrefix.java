package com.my.demo.datastructure.array;

/**
 * @author hyl
 * @create 2021-03-17
 * @version: branch_member_20210305_v4_3_1
 */
public class StringPublicPrefix {

    public static String getPublicPrefix(String[] params) {
        if (params == null || params.length <= 0) {
            return "";
        }
        String publicPrefix = params[0];
        for (int j = 1,preLen = publicPrefix.length();j < preLen;j++) {
            for (int i = 1,length = params.length; i < length; i++) {
                String s = params[i];
                if (s == null || s.length() <= 0) {
                    return "";
                }
                if (s.indexOf(publicPrefix) < 0) {
                    publicPrefix = publicPrefix.substring(0, preLen-j);
                    break;
                }
                return publicPrefix;
            }
        }

        return publicPrefix;
    }

    public static void main(String[] args) {
        String[] params = {"flower","flow","flowight"};
        System.out.println(StringPublicPrefix.getPublicPrefix(params));
    }
}
