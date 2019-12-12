package com.mp5a5.Interview;

import java.util.HashMap;
import java.util.Map;

public class FindChar {

    //计算给定字符串中每个字符出现的次数
    public void findCharCount(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (map.containsKey(ch)) {
                Integer count = map.get(ch);
                count += 1;
                map.put(ch, count);
            } else {
                map.put(ch, 1);
            }
        }

        for (Character key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println(key + " 出现了 " + value + " 次");
        }
    }

    //计算给定字符串中每个字符出现的次数
    public void findCharCount1(String str) {
        int[] nums = new int[26];
        for (char value : str.toCharArray()) {
            if (value >= 97 && value <= 122) {
                nums[value - 97]++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                char ch = (char) (i + 97);
                System.out.println(ch + " 出现了 " + nums[i] + " 次");
            }
        }
    }

    //计算字符串在给定字符串出现的次数
    public void findCount(String src, String des) {
        int index = 0;
        int count = 0;
        while ((index = src.indexOf(des, index)) != -1) {
            count++;
            index = index + des.length();
        }
        System.out.println(des + "出现了 " + count + " 次");
    }
}
