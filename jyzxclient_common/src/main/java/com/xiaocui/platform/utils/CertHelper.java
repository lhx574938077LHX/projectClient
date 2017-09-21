package com.xiaocui.platform.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

public class CertHelper {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public enum SaveMethod {
        CREATE_NEW,
        APPEND
    }

    public enum KeyStoreType {
        JKS,
        PKCS12
    }

    public static void saveCertificate(Certificate certificate, String caCertPath) {
        try {
            FileOutputStream stream = new FileOutputStream(caCertPath);
            stream.write(certificate.getEncoded());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void savePEM(Key key, String pemPath) {
        try {
            JcaPEMWriter writer = new JcaPEMWriter(new FileWriter(pemPath));
            writer.writeObject(key);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static KeyPair getPEM(String pemPath) {
        KeyPair keyPair = null;
        try {
            PEMParser reader = new PEMParser(new FileReader(pemPath));
            PEMKeyPair pemKeyPair = (PEMKeyPair) reader.readObject();

            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            keyPair = converter.getKeyPair(pemKeyPair);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return keyPair;
    }

    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
            throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
            throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    public static void createX509Cert(
            PrivateKey caPrivateKey, //为空时采用自签名
            String issuer,        //证书颁发者
            Long serial,            //证书序号
            String subject,        //证书主题
            Date certNotBefore,    //证书起始日期
            Date certNotAfter,        //证书截止日期
            Locale dateLocale,        //日期区域设置
            String name,                    //证书命名
            String path                        //证书生成地址
    ) {
        InputStream is = null;
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            savePEM(privateKey, path + "/" + name + "_private.pem");
            savePEM(publicKey, path + "/" + name + "_public.pem");

            ContentSigner sigGen = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(caPrivateKey == null ? privateKey : caPrivateKey);

            //证书颁发者
            X500Name certIssuer = new X500Name(issuer);
            BigInteger certSerial = BigInteger.valueOf(serial);
            //Locale dateLocale = Locale.CHINA;
            //证书主题
            X500Name certSubject = new X500Name(subject);

            ASN1InputStream aIstream = new ASN1InputStream(keyPair.getPublic().getEncoded());
            SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(aIstream.readObject());

            X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(certIssuer, certSerial, certNotBefore, certNotAfter, dateLocale, certSubject, publicKeyInfo);

            X509CertificateHolder holder = certGen.build(sigGen);
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            is = new ByteArrayInputStream(holder.toASN1Structure().getEncoded());
            X509Certificate theCert = (X509Certificate) certFactory.generateCertificate(is);

            saveCertificate(theCert, path + "/" + name + ".cer");

            aIstream.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean createKeyStore(
            String alias,    //别名
            Certificate certificate,//证书
            PrivateKey privateKey,//KEY密匙
            String keyPass,    //KEY密码
            String keyStorePass,    //KeyStore密码
            String keyStorePath,    //KeyStore文件路径
            KeyStoreType keyStoreType, //KeyStore类型
            SaveMethod saveMethod //保存方式
    ) {
        boolean result = false;
        FileInputStream is = null;
        FileOutputStream os = null;
        File file = new File(keyStorePath);
        try {
            KeyStore store = KeyStore.getInstance(keyStoreType.toString());

            if (saveMethod == SaveMethod.CREATE_NEW) {
                if (file.exists()) {
                    file.delete();
                }
                store.load(null, null);
            } else {
                is = new FileInputStream(file);
                store.load(is, keyStorePass.toCharArray());
            }

            store.setKeyEntry(alias, privateKey, keyPass.toCharArray(), new Certificate[]{certificate});
            os = new FileOutputStream(file);
            store.store(os, keyStorePass.toCharArray());

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (os != null) os.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    public static PrivateKey getPrivateKeyFromKeyStore(
            String alias,    //别名
            String keyPass,    //Key密码
            String keyStorePass,    //KeyStore密码
            String keyStoreFile,    //KeyStore文件
            KeyStoreType keyStoreType //KeyStore类型
    ) {
        PrivateKey result = null;
        FileInputStream is = null;
        File file = new File(keyStoreFile);
        try {
            is = new FileInputStream(file);
            KeyStore store = KeyStore.getInstance(keyStoreType.toString());
            store.load(is, keyStorePass.toCharArray());

            result = (PrivateKey) store.getKey(alias, keyPass.toCharArray());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return result;
    }

    public static Certificate getCertificateFromKeyStore(
            String alias,    //别名
            String keyStorePass,    //KeyStore密码
            String keyStoreFile,    //KeyStore文件
            KeyStoreType keyStoreType //KeyStore类型
    ) {
        Certificate result = null;
        FileInputStream is = null;
        File file = new File(keyStoreFile);
        try {
            is = new FileInputStream(file);
            KeyStore store = KeyStore.getInstance(keyStoreType.toString());
            store.load(is, keyStorePass.toCharArray());

            result = store.getCertificate(alias);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return result;
    }

    public static X509Certificate getX509CertificateFromFile(String certFile) {
        FileInputStream is = null;
        try {
            is = new FileInputStream(certFile);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(is);

            return x509Certificate;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] sign(byte[] data, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifySign(byte[] data, PublicKey publicKey, String base64Sign) {
        try {
            byte[] sign = Base64.getDecoder().decode(base64Sign);
            Signature signature = Signature.getInstance("SHA1withRSA", "BC");
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static byte[] encryptByPrivateKey(byte[] data, PrivateKey privateKey) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            for (int i = 0; i < data.length; i += 64) {
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 64));
                result = ArrayUtils.addAll(result, doFinal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            for (int i = 0; i < data.length; i += 64) {
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 64));
                result = ArrayUtils.addAll(result, doFinal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] dencryptByPublicKey(byte[] data, PublicKey publicKey) {
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
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] dencryptByPrivateKey(byte[] data, PrivateKey privateKey) {
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
            e.printStackTrace();
        }
        return result;
    }

    public static PrivateKey getPrivateKeyFromFile(String file) {
        KeyPair keyPair = getPEM(file);
        return keyPair.getPrivate();
    }

    public static PublicKey getPublicKeyFromFile(String file) {
        KeyPair keyPair = getPEM(file);
        return keyPair.getPublic();
    }
}