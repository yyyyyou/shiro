package test;

import cn.hutool.Hutool;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import org.apache.shiro.crypto.hash.Md5Hash;

public class t1 {
    public static void main(String[] args) {
        System.out.println(SecureUtil.md5("123"));

        //使用md5
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println(md5Hash.toHex());

        //使用MD5 + salt处理
        Md5Hash md5Hash1 = new Md5Hash("123", "X0*7ps");
        System.out.println(md5Hash1.toHex());

        //使用md5 + salt + hash散列（参数代表要散列多少次，一般是 1024或2048）
        Md5Hash md5Hash2 = new Md5Hash("123", "X0*7ps", 1024);
        System.out.println(md5Hash2.toHex());
    }
}
