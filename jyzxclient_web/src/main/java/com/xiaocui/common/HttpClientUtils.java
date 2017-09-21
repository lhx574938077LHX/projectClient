package com.xiaocui.common;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientUtils {
	private static CloseableHttpClient closeableHttpClient;
	
	public static CloseableHttpClient getCloseableHttpClient() {
		return closeableHttpClient==null?createSSLClientDefault():closeableHttpClient;
	}

	private static  CloseableHttpClient createSSLClientDefault(){
		try {
			X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager 
				private X509Certificate[] certificates;

				@Override
				public void checkClientTrusted(X509Certificate certificates[], String authType) throws CertificateException {
					if (this.certificates == null) {
						this.certificates = certificates;
					}
				}

				@Override
				public void checkServerTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException {
					if (this.certificates == null) {
						this.certificates = ax509certificate;
					}
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
	        };
	        
			 SSLContext sslContext = SSLContext.getInstance("TLS");
			 sslContext.init(null, new TrustManager[]{xtm}, null); 
			 SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,new HostnameVerifier(){
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
				
			 });
			 Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()  
			           .register("http", PlainConnectionSocketFactory.getSocketFactory())  
			           .register("https", sslsf)  
			           .build();  
			
			 PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
				// 将最大连接数增加到
			 connManager.setMaxTotal(1000);
				// 将每个路由基础的连接增加到
			 connManager.setDefaultMaxPerRoute(200);
			 
			 return HttpClients.custom()
					 .setConnectionManager(connManager)
					 .setSSLSocketFactory(sslsf).build();
		 } catch (KeyManagementException e) {
		     e.printStackTrace();
		 } catch (NoSuchAlgorithmException e) {
		     e.printStackTrace();
		 }
		 return  HttpClients.createDefault();
	}
}

