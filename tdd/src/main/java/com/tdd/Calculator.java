package com.tdd;

import org.springframework.util.StringUtils;

/**
 * Created by tgreen on 7/12/16.
 */
public class Calculator {


    public int add(String s) {
        if (!StringUtils.isEmpty(s)){
            String delimiter;
            if (s.startsWith("//")){
                delimiter = s.split("\n")[0].substring(2);
                s = s.replace("//"+delimiter+"\n", "");
            }
            else {
                delimiter = ",|\n";
            }
            String[] strings = s.split(delimiter);
            int sum = getSum(strings);
            return sum;
        }
        return 0;
    }

    private int getSum(String[] strings) {
        int sum =0;
        for (int i=0; i<strings.length; i++){
            int integer = Integer.parseInt(strings[i]);
            if (integer < 0 ){
                throw new NumberFormatException("negatives not allowed");
            }
            sum += integer;
        }
        return sum;
    }


}
