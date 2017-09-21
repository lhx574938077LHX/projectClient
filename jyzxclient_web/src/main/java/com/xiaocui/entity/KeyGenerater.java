package com.xiaocui.entity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings({ "unused", "restriction" })
//用RSA算法生成公钥私钥对
public class KeyGenerater {
	
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    
    
    public static void main(String[] args) {
    	Map<String, Object> keyMap;
    	FileOutputStream fop = null;
    	FileOutputStream fop1 = null;
    	try {
	    	keyMap = initKey();
	    	//生成公钥
	    	String publicKey =  getPublicKey(keyMap);
	    	System.out.println(publicKey);
	    	
	    	File filePulbicKey = new File("D:/filePulbicKey.cer");
	    	fop = new FileOutputStream(filePulbicKey);
	    	fop.write(publicKey.getBytes());
	    	//生成私钥 
	    	String privateKey =  getPrivateKey(keyMap);
	    	System.out.println(privateKey);
	    	
	    	File filePrivateKey = new File("D:/filePrivateKey.jks");
	    	fop1 = new FileOutputStream(filePrivateKey);
	    	fop1.write(privateKey.getBytes());
	    	
	    	fop.flush();
	    	fop1.flush();
    		fop.close();
    		fop1.close();   		
    	} catch (Exception e) { 
    		e.printStackTrace();
    	} finally{
    		if(fop!=null){
    			try {fop.close();} catch (IOException e) {e.printStackTrace();}
    		}
    		if(fop1!=null){
    			try {fop1.close();} catch (IOException e) {e.printStackTrace();}
    		}
    	}
    }
    
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY); 
        byte[] publicKey = key.getEncoded(); 
        return encryptBASE64(key.getEncoded());
	}
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY); 
        byte[] privateKey =key.getEncoded(); 
        return encryptBASE64(key.getEncoded());
	}  
    
	/*
	 * base64解密
	 */
	public static byte[] decryptBASE64(String key) throws Exception {               
       return (new BASE64Decoder()).decodeBuffer(key);               
   }                                 
    
	/*
	 * base64加密
	 */
	public static String encryptBASE64(byte[] key) throws Exception {               
       return (new BASE64Encoder()).encodeBuffer(key);               
   }       
   
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}
    
}
