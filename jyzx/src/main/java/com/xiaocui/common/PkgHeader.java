package com.xiaocui.common;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.xiaocui.platform.utils.Base64Crypto;
import com.xiaocui.platform.utils.CertHelper;

import sun.misc.BASE64Encoder;

public class PkgHeader {
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA"; //SHA1withRSA

    public static Logger logger = Logger.getLogger(PkgHeader.class);

    private String version;            //默认01
    private String companyCode;    //客户编号
    private String encode;            //01.UTF8 02.GBK
    private String trxCode;            //报文编号 默认四位 例:0001
    private String encryptType;        //加密类型 01.不加密 02.RSA
    private String msgType;            //01.JSON 02.XML 03.Protobuf
    private String msgBody;            //报文主体为Base64编码的字节数组
    private String retCode;            //返回代码
    private String retMsg;            //返回消息
    private String connKey;            //通讯秘钥

    private String mode;                //工作模式 S.91征信服务端 P.客户端
    private PrivateKey privateKey;    //私钥
    private PublicKey publicKey;    //公钥

    public PkgHeader() {
        this.setVersion("01");            //默认01
        this.setCompanyCode("");    //客户编号
        this.setEncode("01");            //01.UTF8 02.GBK
        this.setTrxCode("");            //报文编号 默认四位 例:0001
        this.setEncryptType("01");    //加密类型 01.不加密 02.RSA
        this.setMsgType("01");        //01.JSON 02.XML 03.Protobuf
        this.setMsgBody("");            //报文主体
        this.setRetCode("");            //返回代码
        this.setRetMsg("");                //返回消息
        this.setConnKey("");            //通讯秘钥
    }

    public PkgHeader(PrivateKey privateKey, PublicKey publicKey, String mode) {
        this.setVersion("01");            //默认01
        this.setCompanyCode("");    //客户编号
        this.setEncode("01");            //01.UTF8 02.GBK
        this.setTrxCode("");            //报文编号 默认四位 例:0001
        this.setEncryptType("01");    //加密类型 01.不加密 02.RSA
        this.setMsgType("01");        //01.JSON 02.XML 03.Protobuf
        this.setMsgBody("");            //报文主体
        this.setRetCode("");            //返回代码
        this.setRetMsg("");                //返回消息
        this.setConnKey("");            //通讯秘钥
        this.setMode(mode);            //工作模式S服务端P客户端

        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public void parseFormBytes(byte[] data, Charset charset) {
        parseFromString(new String(data, charset));
    }

    public void parseFormBytes(byte[] data, String charsetName) {
        parseFormBytes(data, GetEncoding());
    }

    public void parseFromString(String pkgStr) {
        String[] postDataStr = StringUtils.splitPreserveAllTokens(pkgStr, "|");

        this.setVersion(postDataStr[0]);                //默认01
        this.setCompanyCode(postDataStr[1]);    //客户编号
        this.setEncode(postDataStr[2]);                //01.UTF-8 02.GBK
        this.setTrxCode(postDataStr[3]);                //报文编号 默认四位 例:0001
        this.setEncryptType(postDataStr[4]);        //加密类型 01.不加密 02.RSA
        this.setMsgType(postDataStr[5]);            //01.JSON 02.XML 03.Protobuf
        this.setMsgBody(postDataStr[6]);

        String msgBody = "";
        if (!isEmpty(this.msgBody)) {
            if (this.getEncryptType().equals("01")) {
                msgBody = new String(Base64Crypto.decode(postDataStr[6]), GetEncoding());
            } else {
                byte[] dencryptContent = dencryptByPrivateKey(Base64Crypto.decode(postDataStr[6]), privateKey);
                msgBody = new String(dencryptContent, GetEncoding());
            }
        }

        this.setMsgBody(msgBody);            //报文主体
        this.setRetCode(postDataStr[7]);        //返回代码

        String retMsg = "";

        if (this.getRetMsg() != null) {
            if (this.getRetMsg().length() != 0) {
                retMsg = new String(Base64Crypto.decode(postDataStr[8]), GetEncoding());
            }
        }

        this.setRetMsg(retMsg);                    //返回消息
        this.setConnKey(postDataStr[9]);        //通讯秘钥
    }

    public String toPkgStr() {
        return toPkgStr(GetEncoding());
    }

    public String toPkgStr(Charset charset) {
        String msgBody = "";

        if (!isEmpty(this.msgBody)) {
            if (this.getEncryptType().equals("01")) {
                msgBody = Base64Crypto.encode(this.msgBody.getBytes(charset));
            } else {
                byte[] dencryptContent = CertHelper.encryptByPublicKey(this.getMsgBody().getBytes(charset), publicKey);
                msgBody = new BASE64Encoder().encode(dencryptContent);
            }
        }

        msgBody = msgBody.replaceAll("[\\t\\n\\r]", "");
        String retMsg = "";
        if (!isEmpty(this.retMsg)) {
            retMsg = Base64Crypto.encode(this.retMsg.getBytes(charset));
            retMsg = retMsg.replaceAll("[\\t\\n\\r]", "");
        }

        String result = this.version + "|" +
                this.companyCode + "|" +
                this.encode + "|" +
                this.trxCode + "|" +
                this.encryptType + "|" +
                this.msgType + "|" +
                msgBody + "|" +
                this.retCode + "|" +
                retMsg + "|" +
                this.connKey;

        return result;
    }

    public String toPkgStr(String charsetName) {
        String result = toPkgStr(Charset.forName(charsetName));
        return result;
    }

    public byte[] toPkgBytes(Charset charset) {
        return toPkgStr(charset).getBytes(charset);
    }

    public byte[] toPkgBytes(String charsetName) {
        byte[] result = toPkgBytes(Charset.forName(charsetName));

        return result;
    }

    private static boolean isEmpty(String str) {
        if (str == null) return true;
        if (str.trim().length() == 0) return true;

        return false;
    }

    private Charset GetEncoding() {
        return this.encode.equals("01") ? Charset.forName("UTF-8") : Charset.forName("GBK");
    }

    private byte[] dencryptByPublicKey(byte[] data, PublicKey publicKey) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            //result = cipher.doFinal(data);
            for (int i = 0; i < data.length; i += 256) {
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 256));
                result = ArrayUtils.addAll(result, doFinal);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    private byte[] dencryptByPrivateKey(byte[] data, PrivateKey privateKey) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //result = cipher.doFinal(data);
            for (int i = 0; i < data.length; i += 256) {
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 256));
                result = ArrayUtils.addAll(result, doFinal);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return result;
    }

    public static PrivateKey getPrivateKeyFromString(String privateKey) {
        KeyFactory keyFactory;
        PrivateKey privateK = null;
        try {
            byte[] keyBytes = Base64Crypto.decode(privateKey);
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        } catch (InvalidKeySpecException e) {
            logger.error(e);
        }

        return privateK;
    }

    public static PublicKey getPublicKeyFromString(String publicKey) {
        KeyFactory keyFactory;
        PublicKey publicK = null;
        try {
            byte[] keyBytes = Base64Crypto.decode(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            publicK = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        } catch (InvalidKeySpecException e) {
            logger.error(e);
        }
        return publicK;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getTrxCode() {
        return trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getConnKey() {
        return connKey;
    }

    public void setConnKey(String connKey) {
        this.connKey = connKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

