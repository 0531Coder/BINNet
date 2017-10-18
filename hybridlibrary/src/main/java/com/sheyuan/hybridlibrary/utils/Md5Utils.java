package com.sheyuan.hybridlibrary.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by xiaoma on 2016/11/23.
 */

public class Md5Utils {

    private static byte[] buffer = new byte[1024];
    public static String getMD5(File file) {
        int len = 0;
        InputStream inputStream = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            inputStream = new FileInputStream(file);
            while ((len = inputStream.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            BigInteger bigInteger = new BigInteger(1, messageDigest.digest());
            return bigInteger.toString(16);
        } catch (Exception e) {
            return null;
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
