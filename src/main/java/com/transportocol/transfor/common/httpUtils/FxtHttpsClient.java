package com.transportocol.transfor.common.httpUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class FxtHttpsClient {

    public static String post(String uri) throws Exception {
        return post(uri, null);
    }

    public static String post(String uri, String data) throws Exception {
        String result = "";
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = getSSLHttpClient();
            HttpPost httpPost = new HttpPost(uri);
            if (StringUtils.isNotBlank(data)) httpPost.setEntity(new StringEntity(data, "UTF-8"));
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("charset", "UTF-8");






            response = httpclient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) result = EntityUtils.toString(httpEntity);
        } finally {
            if (response != null) response.close();
            if (httpclient != null) httpclient.close();
        }
        return result;
    }

    private static CloseableHttpClient getSSLHttpClient() throws Exception {
        CloseableHttpClient httpclient;
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        };
        SSLContext sc = SSLContext.getInstance(SSLConnectionSocketFactory.SSL);
        sc.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sc, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        httpclient = HttpClients.custom().setSSLSocketFactory(factory).build();
        return httpclient;
    }

    public static String get(String uri) throws Exception {
        String result = "";
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = getSSLHttpClient();
            HttpGet httpget = new HttpGet(uri);
            response = httpclient.execute(httpget);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity);
            }
        } finally {
            if (response != null) response.close();
            if (httpclient != null) httpclient.close();
        }
        return result;
    }


}
