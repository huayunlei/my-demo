package com.my.demo.bean.copy.demo;

/**
 * @author hyl
 * @create 2020-05-15
 * @version:
 */
public enum SexEnum {
    UNKNOW("未设置",0),
    MEN("男生", 1),
    WOMAN("女生",2),

    ;
    private String desc;
    private int code;

    SexEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public static SexEnum getDescByCode(int code) {
        SexEnum[] typeEnums = values();
        for (SexEnum value : typeEnums) {
            if (code == value.getCode()) {
                return value;
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
