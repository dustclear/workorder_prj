package com.wos.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.DigestUtils;

/*
 * MD5 算法
 */
public class MD5Util {

	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public MD5Util() {
	}

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 返回形式只为数字
	private static String byteToNum(byte bByte) {
		int iRet = bByte;
		System.out.println("iRet1=" + iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String GetMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static void main(String[] args) {
		MD5Util getMD5 = new MD5Util();
		System.out.println(getMD5.GetMD5Code("000000"));
		System.out.println(DigestUtils.md5DigestAsHex("000000".getBytes()));
	}

	public static String getMd5Hash1(String input)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		java.security.MessageDigest md = java.security.MessageDigest
				.getInstance("MD5");
		// Integer hash = new Integer(input.hashCode());

		md.update(input.getBytes());

		byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
									// 用字节表示就是 16 个字节
		char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
										// 所以表示成 16 进制需要 32 个字符
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
										// 转换成 16 进制字符的转换
			byte byte0 = tmp[i]; // 取第 i 个字节
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
														// >>> 为逻辑右移，将符号位一起右移
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
	
	/**
	 * 两次加密的md5
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMd5Hash2(String input) throws NoSuchAlgorithmException {
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		java.security.MessageDigest md = java.security.MessageDigest
				.getInstance("MD5");
		// Integer hash = new Integer(input.hashCode());

		md.update(input.getBytes());

		byte tmp2[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
		md.update(tmp2); // 用字节表示就是 16 个字节
		byte tmp[] = md.digest();
		char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
										// 所以表示成 16 进制需要 32 个字符
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
										// 转换成 16 进制字符的转换
			byte byte0 = tmp[i]; // 取第 i 个字节
			str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
}