package edu.hw1;

import java.util.Arrays;

public class task_4 {
    public static String fixString(String string){
        char[] chars = string.toCharArray();
        for(int i = 0; i < chars.length-1; i++){
            if (i % 2 == 0){
                var cache = chars[i];

                chars[i] = chars[i+1];
                chars[i+1] = cache;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(fixString("test"));
    }
}
