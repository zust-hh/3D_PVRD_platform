package com.xinmiao.back.util;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
    //private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public static String encryptPassword(String password,String userName) {
        String newPassword = new SimpleHash(algorithmName, password,  ByteSource.Util.bytes(userName), hashIterations).toHex();
        return newPassword;
    }

}
