package com.xiaocui.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;


public class RSAUtils {
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	private static int MAX_LENGTH = 117;
	
	/**
	 * 用私钥对信息生成数字签名
	 */
	public static String sign(byte[] data, String key) throws Exception{
		// 对私钥解密
		byte[] keyBytes = KeyGenerater.decryptBASE64(key);
		
		//取得私钥
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KeyGenerater.KEY_ALGORITHM);
		// 取私钥匙对象
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateKey);
		signature.update(data);

		return KeyGenerater.encryptBASE64(signature.sign());
	}
	
	/**
	 * 校验数字签名
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {

		// 解密由base64编码的公钥
		byte[] keyBytes = KeyGenerater.decryptBASE64(publicKey);

		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KeyGenerater.KEY_ALGORITHM);

		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);

		// 验证签名是否正常
		return signature.verify(KeyGenerater.decryptBASE64(sign));
	}
	
	
	/**
	 * 公钥加密
	 */
	public static byte[] encryptPublicKey(byte[] data, String key) throws Exception{
		// 对公钥解密
		byte[] keyBytes = KeyGenerater.decryptBASE64(key);
		
		//取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KeyGenerater.KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		
		//数据加密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        
        return cipher.doFinal(data);  
	}
	
	/**
	 * 公钥解密
	 */
	public static byte[] decryptPublicKey(byte[] data, String key) throws Exception{
		// 对公钥解密
		byte[] keyBytes = KeyGenerater.decryptBASE64(key);
		
		//取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KeyGenerater.KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		
		//数据解密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);  
        
        return cipher.doFinal(data);
	}
	
	/**
	 * 私钥加密
	 */
	public static byte[] encryptPrivateKey(byte[] data,String key) throws Exception{
		// 对私钥解密
		byte[] keyBytes = KeyGenerater.decryptBASE64(key);
		
		//取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KeyGenerater.KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		
		//数据加密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
        
        return cipher.doFinal(data); 
	}
	
	/**
	 * 私钥解密
	 */
	public static byte[] decryptPrivateKey(byte[] data,String key) throws Exception{
		// 对私钥解密
		byte[] keyBytes = KeyGenerater.decryptBASE64(key);
		
		//取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KeyGenerater.KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		
		//数据解密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        
        return cipher.doFinal(data); 
	}
	
	/**
	 * 用私钥分段加密
	 * @throws Exception 
	 */
	public static byte[]  encryLongData(byte[] data, String privateKey) throws Exception{
		int arrayMaxLength = MAX_LENGTH;
		if (data.length <= arrayMaxLength) return encryptPrivateKey(data, privateKey);
		InputStream in = new ByteArrayInputStream(data);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buff = new byte[arrayMaxLength];
		int temp = 0;
		while((temp = in.read(buff, 0, buff.length)) != -1){//读取能够加密的长度进行分段加密
			byte[] tempByte = Arrays.copyOf(buff, temp);
			byte[] encodedData = encryptPrivateKey(tempByte, privateKey);
			out.write(encodedData, 0, encodedData.length);
		}
		return out.toByteArray();
	}
	
	/**
	 * 用公钥分段解密
	 * @throws Exception 
	 * 
	 */
	public static byte[] decryLongData(byte[] data, String publicKey) throws Exception{
		int arrayMaxLength = MAX_LENGTH + 11;
		if(data.length <= arrayMaxLength) return decryptPublicKey(data, publicKey);
		InputStream in = new ByteArrayInputStream(data);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buff = new byte[arrayMaxLength];
		int temp = 0;
		while((temp = in.read(buff, 0, buff.length)) != -1){//读取能够加密的长度进行分段加密
			byte[] tempByte = Arrays.copyOf(buff, temp);
			byte[] encodedData = decryptPublicKey(tempByte, publicKey);
			out.write(encodedData, 0, encodedData.length);
		}
		return out.toByteArray();
	}
	
}
