package com.example;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

    public static void main(String[] arg) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }
        for(String str:list)
        System.out.print(str);
    }
}
