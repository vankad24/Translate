package com.example.yatranslate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

public class Request {
    String text;
    String lang;
    int finishCode;

    public Request(String text, String lang, int finishCode) {
        this.text = text;
        this.lang = lang;
        this.finishCode = finishCode;
    }

    String format = "plain";


    String key = "trnsl.1.1.20200409T064045Z.6fde6a81bb8886ca.d8d3fae5f40a508ed9471784187e78ccdba67312";

    public byte[] toByteArray() {
                try {
            text = URLEncoder.encode(text,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // формируем данные веб-формы
        String data = "format="+format+"&key="+key+"&text="+text+"&lang="+lang;

        return data.getBytes();
    }
}
