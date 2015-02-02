package com.wos.common;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * DES加密算法工具类
 * @author lance kuai
 * @date 2015-02-01 下午3:24:14
 */
public class DESUtils {
private static Key key;
    private static String KEY_STR = "myKey111";// 密钥
    private static String CHARSETNAME = "ASCII";// 编码
    private static String ALGORITHM = "DES";// 加密类型
 
    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            generator.init(new SecureRandom(KEY_STR.getBytes()));
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     * 对str进行DES加密
     * 
     * @param str
     * @return
     */
    public static String getEncryptString(String str) {
        BASE64Encoder base64encoder = new BASE64Encoder();
        try {
            byte[] bytes = str.getBytes(CHARSETNAME);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(KEY_STR.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
//            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
//            return base64encoder.encode(doFinal);
            return new String(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     * 对str进行DES解密
     * 
     * @param str
     * @return
     */
    public static String getDecryptString(String str) {
        BASE64Decoder base64decoder = new BASE64Decoder();
        try {
            byte[] bytes = base64decoder.decodeBuffer(str);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args)
    {
    	System.out.println(toHexString(getEncryptString("admin123")));
        System.out.println(toHexString("/Vpu2Ev2zTEwHk5QMcWBSg=="));
//    	System.out.println(getDecryptString("hu0E6AeFH/Oasfe3Eqr2jA=="));
    }
    
    //转化字符串为十六进制编码
    public static String toHexString(String s) {
       String str = "";
       for (int i = 0; i < s.length(); i++) {
        int ch = (int) s.charAt(i);
        String s4 = Integer.toHexString(ch);
        str = str + s4;
       }
       return str;
    }
}