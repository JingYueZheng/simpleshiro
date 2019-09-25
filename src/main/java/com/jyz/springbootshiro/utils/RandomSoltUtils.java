package com.jyz.springbootshiro.utils;

import java.util.Random;

public class RandomSoltUtils {

    /** a-z A-Z
     * 生成一个7位数的随即盐
     * @return
     */
    public static String RandomSolt(){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i=0;i<8;i++){
            int item=0;
            do {
                item=random.nextInt(57)+65;
            }while (90<item&&item<97);
            builder.append((char)item);
        }
        return builder.toString();
    }
}
