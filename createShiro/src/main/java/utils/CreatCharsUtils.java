package utils;

import java.util.Random;

/**
 * 随机生成salt
 */
public class CreatCharsUtils {
    public static String createRandomChars(Integer n){
        char[] chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890~!@#$%^&*()_+".toCharArray();
       StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <n ; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(createRandomChars(5));
    }
}
