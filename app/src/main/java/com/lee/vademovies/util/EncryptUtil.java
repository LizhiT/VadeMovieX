package com.lee.vademovies.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by xyj on 2017/6/29.
 */
public class EncryptUtil {

    private static final String KEY = "12baweiyidong345";

    private static final String IV = "67baweiyidong899";

    /**
     * 加密
     */
    public static String encrypt(String passWord) {
        try {
            Key keySpec = new SecretKeySpec(KEY.getBytes(), "AES"); //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//实例化加密类，参数为加密方式，要写全
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] b = cipher.doFinal(passWord.getBytes());
            String ret = Base64.encode(b);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
