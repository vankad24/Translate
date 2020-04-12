package com.example.yatranslate;

import java.util.Arrays;

public class Response {
    int code;
    String lang;
    String[] text;
    int finishCode;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String s:text)builder.append(s);
        return builder.toString();
    }
}
