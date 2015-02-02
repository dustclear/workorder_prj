package com.wos.common;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESInUse
{
    private static String KEY_STR = "myKey111";// 密钥
    
    private static String CHARSETNAME = "UTF-8";// 编码
    
    private static String ALGORITHM = "DES";// 加密类型    
    
    private static String ALGORITHM_DETAIL = "DES/CBC/PKCS5Padding";// 加密类型    
    
    //解密数据 
    public static String decrypt(String message)
    {
        try
        {
            byte[] bytesrc = convertHexString(message);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DETAIL);
            DESKeySpec desKeySpec = new DESKeySpec(KEY_STR.getBytes(CHARSETNAME));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(KEY_STR.getBytes(CHARSETNAME));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] retByte = cipher.doFinal(bytesrc);
            return new String(retByte);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        
    }
    
    public static byte[] encrypt(String message) throws Exception
    {
        try
        {
            Cipher cipher = Cipher.getInstance(ALGORITHM_DETAIL);
            DESKeySpec desKeySpec = new DESKeySpec(KEY_STR.getBytes(CHARSETNAME));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(KEY_STR.getBytes(CHARSETNAME));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return cipher.doFinal(message.getBytes(CHARSETNAME));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        
    }
    
    public static byte[] convertHexString(String ss)
    {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++)
        {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte)byteValue;
        }
        return digest;
    }
    
    public static void main(String[] args) throws Exception
    {
        String value = "sasa";
        String jiami = java.net.URLEncoder.encode(value, CHARSETNAME).toLowerCase();
        System.out.println("加密数据:" + jiami);
        String a = toHexString(encrypt(jiami)).toUpperCase();
        
        System.out.println("加密后的数据为:" + a);
        String b = java.net.URLDecoder.decode(decrypt(a), CHARSETNAME);
        System.out.println("解密后的数据:" + b);
    }
    
    public static String toHexString(byte b[])
    {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++)
        {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }
}