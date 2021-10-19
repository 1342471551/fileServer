package com.transportocol.transfor.common;

public enum HttpPostTypeEnum {
    FORM_URLENCODED("x-www-form-urlencoded"),
    JSON("application/json");

    private String type;

    HttpPostTypeEnum(String type) {
        this.type = type;
    }
}
