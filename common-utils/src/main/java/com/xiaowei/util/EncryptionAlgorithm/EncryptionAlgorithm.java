package com.xiaowei.util.EncryptionAlgorithm;

import com.xiaowei.util.Constant.ConstantCrowd;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密算法
 */
public class EncryptionAlgorithm {

    public static String md5Algorithm(String source){
        if(source == null || source.length() == 0){
            throw new RuntimeException(ConstantCrowd.ATTE_NAME_EXCEPTION);
        }

        try {
            // 1.定义加密方式
            String algorithm = "md5";

            // 2.获取MessageDigest对象
            MessageDigest instance = MessageDigest.getInstance(algorithm);

            // 3.获取明文字符串的对应字节数组
            byte[] bytes = source.getBytes();

            // 4.执行加密
            byte[] output = instance.digest(bytes);


            // 5.字节数组转换

            int signal = 1;
            BigInteger bigInteger = new BigInteger(signal,output);

            int radix = 16;
            String s = bigInteger.toString(radix).toUpperCase();
            return s;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
