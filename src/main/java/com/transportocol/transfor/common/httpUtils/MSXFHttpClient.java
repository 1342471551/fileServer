package com.transportocol.transfor.common.httpUtils;

import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HttpClient 工具类
 */
//@Component
public class MSXFHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(MSXFHttpClient.class);
    private static String UTF8 = "UTF-8";
    public final static int CONNECTION_REQUEST_TIMEOUT = 15 * 10000;
    public final static int CONNECTION_TIMEOUT = 15 * 10000;
    public final static int SOCKET_TIME_OUT = 15 * 10000;
    public final static String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    //从连接池获取连接、连接建立、读取数据超时时间都设置为5s,且不会重试,默认编码UTF-8
    private static HttpClient httpClient = HttpClientFactory.create();


    /**
     * 执行post请求,默认编码为UTF-8;
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String post(String serviceUrl, int connTimeout, int socketTimeout) throws Exception {
        return post(serviceUrl, connTimeout, socketTimeout, UTF8);
    }

    /**
     * 执行post请求;
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param connTimeout
     * @param socketTimeout
     * @param charset
     * @return
     * @throws Exception
     */
    public static String post(String serviceUrl, int connTimeout, int socketTimeout, String charset) throws Exception {
        HttpResponse response = null;
        try {
            //构造请求配置
            HttpHost proxy = new HttpHost("11.1.194.178",9000);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();

            HttpUriRequest request = RequestBuilder.post(serviceUrl)
                    .setCharset(Charset.forName(charset))
                    .setConfig(requestConfig)
                    .build();

            //执行请求
            response = httpClient.execute(request);

            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), charset);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("post resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }

            logger.info("post resource:[{}] success", statusCode + serviceUrl);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 执行post请求,默认编码为UTF-8;
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param paramMap
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String post(String serviceUrl, Map<String, String> paramMap, int connTimeout, int socketTimeout) throws Exception {
        return post(serviceUrl, paramMap, connTimeout, socketTimeout, UTF8);
    }

    /**
     * 执行post请求;
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param paramMap
     * @param connTimeout
     * @param socketTimeout
     * @param charset
     * @return
     * @throws Exception
     */
    public static String post(String serviceUrl, Map<String, String> paramMap, int connTimeout, int socketTimeout, String charset) throws Exception {
        if (paramMap == null || paramMap.isEmpty()) {
            throw new Exception("paramMap is null or empty");
        }

        List<NameValuePair> param = Lists.newArrayListWithExpectedSize(paramMap.size());
        StringBuilder paramStr = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            paramStr.append("[").append(entry.getKey()).append(":").append(entry.getValue()).append("]");
        }

        HttpResponse response = null;
        try {
            //构造请求参数
            HttpEntity entity = new UrlEncodedFormEntity(param, UTF8);

            //构造请求配置
            //设置代理
            HttpHost proxy = new HttpHost("11.1.194.178",9000);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();

            //构造请求命令
            HttpUriRequest request = RequestBuilder.post(serviceUrl)

                    .setCharset(Charset.forName(charset))
                    .setConfig(requestConfig)
                    .setEntity(entity)
                    .build();

            //执行请求
            response = httpClient.execute(request);

            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), charset);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("post resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }

            logger.info("post resource:[{}] success", statusCode + serviceUrl + paramStr.toString());
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 执行post请求,数据以json形式提交,默认编码为UTF-8;
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param param
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String postToJson(String serviceUrl, String param, int connTimeout, int socketTimeout) throws Exception {
        return postToJson(serviceUrl, param, connTimeout, socketTimeout, UTF8);
    }

    /**
     * 执行post请求,数据以application/json形式提交
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param param
     * @param connTimeout
     * @param socketTimeout
     * @param charset
     * @return
     * @throws Exception
     */
    public static String postToJson(String serviceUrl, String param, int connTimeout, int socketTimeout, String charset) throws Exception {
        if (serviceUrl == null || serviceUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("serviceUrl is null or empty");
        }
        if (param == null || param.trim().isEmpty()) {
            throw new IllegalArgumentException("param is null or empty");
        }

        HttpResponse response = null;
        try {
            //构造请求参数
            StringEntity entity = new StringEntity(param, charset);
            entity.setContentType(CONTENT_TYPE_JSON);
            //构造请求配置
            HttpHost proxy = new HttpHost("11.1.194.178",9000);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();

            //构造请求命令
            HttpUriRequest request = RequestBuilder.post(serviceUrl)
                    .setCharset(Charset.forName(charset))
                    .setHeader("Accept", "application/json")
                    .setConfig(requestConfig)
                    .setEntity(entity)
                    .build();

            //执行请求
            response = httpClient.execute(request);


            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();

            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), charset);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("post resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }
            logger.info("post resource:[{}] success", statusCode + serviceUrl + param);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            logger.error("post resource:[{}] failed,param:[{}],exception:{}", serviceUrl, param, e);
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 执行post请求,数据以byte array形式提交,默认编码为UTF-8
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param param
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String postToByteArray(String serviceUrl, String param, int connTimeout, int socketTimeout) throws Exception {
        return postToByteArray(serviceUrl, param, connTimeout, socketTimeout, UTF8);
    }

    /**
     * 执行post请求,数据以byte array形式提交
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param param
     * @param connTimeout
     * @param socketTimeout
     * @param charset
     * @return
     * @throws Exception
     */
    public static String postToByteArray(String serviceUrl, String param, int connTimeout, int socketTimeout, String charset) throws Exception {
        if (param == null || param.trim().isEmpty()) {
            throw new Exception("param is null or empty");
        }

        HttpResponse response = null;
        try {
            //构造请求配置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();

            //构造请求命令
            HttpUriRequest request = RequestBuilder.post(serviceUrl)
                    .setCharset(Charset.forName(charset))
                    .setConfig(requestConfig)
                    .setHeader("Content-Type", "application/json")
                    .setEntity(new ByteArrayEntity(param.getBytes(UTF8)))
                    .build();

            //执行请求
            response = httpClient.execute(request);

            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), charset);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("post resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }

            logger.info("post resource:[{}] success", statusCode + serviceUrl + param);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 需要身份验证的Post请求,支持请求header设置,默认编码UTF-8
     * 数据以application/json形式提交
     * headers可以不设置值
     *
     * @param serviceUrl
     * @param param
     * @param headers
     * @param userName      身份验证需要的用户名
     * @param password      身份验证需要的密码
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String post(String serviceUrl, String param, Map<String, String> headers, String userName, String password, int connTimeout, int socketTimeout) throws Exception {
        return post(serviceUrl, param, headers, userName, password, connTimeout, socketTimeout, UTF8);
    }

    /**
     * 需要身份验证的Post请求,支持请求header设置
     * 数据以application/json形式提交
     * headers可以不设置值
     *
     * @param serviceUrl
     * @param param
     * @param headers
     * @param userName      身份验证需要的用户名
     * @param password      身份验证需要的密码
     * @param connTimeout
     * @param socketTimeout
     * @param charset
     * @return
     * @throws Exception
     */
    public static String post(String serviceUrl, String param, Map<String, String> headers, String userName, String password, int connTimeout, int socketTimeout, String charset) throws Exception {
        if (param == null || param.trim().isEmpty()) {
            throw new Exception("param is null or empty");
        }
        if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception("userName or password is null or empty");
        }

        HttpResponse response = null;
        try {
            //构造请求参数
            StringEntity entity = new StringEntity(param, UTF8);
            entity.setContentType("application/json");

            //构造请求配置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();

            //构造请求命令
            RequestBuilder requestBuilder = RequestBuilder.post(serviceUrl)
                    .setCharset(Charset.forName(charset))
                    .setConfig(requestConfig)
                    .setEntity(entity);

            //requestBuilder.addHeader(new BasicScheme()
            //.authenticate(new UsernamePasswordCredentials(userName, password), requestBuilder.build(), null));
            requestBuilder.addHeader(authenticate(userName, password, "US-ASCII"));

            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    requestBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }

            //执行请求
            response = httpClient.execute(requestBuilder.build());

            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), charset);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("post resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }

            logger.error("post resource:[{}] success,param:[{}]", serviceUrl, param);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }


    /**
     * 需要身份验证的Post请求,支持请求header设置,默认编码UTF-8
     * 数据以application/json形式提交
     * headers可以不设置值
     *
     * @param serviceUrl
     * @param param
     * @param headers
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String postToJson(String serviceUrl, String param, Map<String, String> headers, int connTimeout, int socketTimeout) throws Exception {
        if (serviceUrl == null || serviceUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("serviceUrl is null or empty");
        }
        if (param == null || param.trim().isEmpty()) {
            throw new IllegalArgumentException("param is null or empty");
        }

        HttpResponse response = null;
        try {
            //构造请求参数
            StringEntity entity = new StringEntity(param, UTF8);
            entity.setContentType(CONTENT_TYPE_JSON);
            //构造请求配置
            HttpHost proxy = new HttpHost("11.1.194.178",9000);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();


            //构造请求命令
            HttpUriRequest request = RequestBuilder.post(serviceUrl)
                    .setCharset(Charset.forName(UTF8))
                    .setConfig(requestConfig)
                    .setEntity(entity)
                    .build();
            //headers.put("Accept", "application/json");
            if (isNotEmptyMap(headers)) {
                Iterator var11 = headers.keySet().iterator();
                while (var11.hasNext()) {
                    String key = (String) var11.next();
                    request.setHeader(key, (String) headers.get(key));
                }
            }

            //执行请求
            response = httpClient.execute(request);


            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();

            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), UTF8);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("post resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }
            logger.info("post resource:[{}] success", statusCode + serviceUrl + param);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            logger.error("post resource:[{}] failed,param:[{}],exception:{}", serviceUrl, param, e);
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }


    /**
     * 执行get请求;默认编码为UTF-8
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String get(String serviceUrl, int connTimeout, int socketTimeout) throws Exception {
        return get(serviceUrl, connTimeout, socketTimeout, UTF8);
    }

    /**
     * 执行get请求;
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String get(String serviceUrl, int connTimeout, int socketTimeout, String charset) throws Exception {
        HttpResponse response = null;
        try {
            //构造请求配置
            HttpHost proxy = new HttpHost("11.1.194.178",9000);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();

            //构造请求命令
            HttpUriRequest request = RequestBuilder.get(serviceUrl)
                    .setCharset(Charset.forName(charset))
                    .setConfig(requestConfig)
                    .build();

            //执行请求
            response = httpClient.execute(request);

            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), charset);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("get resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }

            logger.info("post resource:[{}] success", statusCode + serviceUrl);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 执行get请求;默认编码为UTF-8
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param paramMap
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String get(String serviceUrl, Map<String, String> paramMap, int connTimeout, int socketTimeout) throws Exception {
        return get(serviceUrl, paramMap, connTimeout, socketTimeout, UTF8);
    }

    //    南阳内部专线的不通过外部调用 不加代理的！！！！！！
    public static String get(String serviceUrl, Map<String, String> headers, Map<String, String> paramMap, int connTimeout, int socketTimeout) throws Exception {
        if (paramMap == null || paramMap.isEmpty()) {
            throw new Exception("paramMap is null or empty");
        }

        HttpResponse response = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();


            RequestBuilder requestBuilder = RequestBuilder.get(serviceUrl)
                    .setCharset(Charset.forName(UTF8))
                    .setConfig(requestConfig);

            StringBuilder paramStr = new StringBuilder();
            //设置请求参数
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                requestBuilder.addParameter(entry.getKey(), entry.getValue());
                paramStr.append("[").append(entry.getKey()).append(":").append(entry.getValue()).append("]");
            }

            HttpUriRequest request = requestBuilder.build();

            if (isNotEmptyMap(headers)) {
                Iterator var11 = headers.keySet().iterator();

                while (var11.hasNext()) {
                    String key = (String) var11.next();
                    request.setHeader(key, (String) headers.get(key));
                }
            }
            System.out.print(request);
            //执行请求
            response = httpClient.execute(request);
            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), UTF8);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("get resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }

            logger.info("post resource:[{}] success,param:[{}]", statusCode + serviceUrl);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 执行get请求;
     * connTimeout、socketTimeout如果小于等于0,则默认为5s
     *
     * @param serviceUrl
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String get(String serviceUrl, Map<String, String> paramMap, int connTimeout, int socketTimeout, String charset) throws Exception {
        if (paramMap == null || paramMap.isEmpty()) {
            throw new Exception("paramMap is null or empty");
        }


        /*StringBuilder paramUrl = new StringBuilder("?");
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            paramUrl.append(entry.getKey()).append("=")
            .append(URLEncoder.encode(entry.getValue(), UTF8))
            .append("&");
        }*/

        HttpResponse response = null;
        try {
            //构造请求配置
            HttpHost proxy = new HttpHost("11.1.194.178",9000);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();

            //构造请求命令
            RequestBuilder requestBuilder = RequestBuilder.get(serviceUrl)
                    .setCharset(Charset.forName(charset))
                    .setConfig(requestConfig);

            StringBuilder paramStr = new StringBuilder();
            //设置请求参数
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                requestBuilder.addParameter(entry.getKey(), entry.getValue());
                paramStr.append("[").append(entry.getKey()).append(":").append(entry.getValue()).append("]");
            }
            System.out.print(requestBuilder.build());
            //执行请求
            response = httpClient.execute(requestBuilder.build());
            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            //会主动关闭流
            String result = EntityUtils.toString(response.getEntity(), charset);

            if (statusCode / 100 > 3) {
                throw new Exception(String.format("get resource:[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, statusCode, result));
            }

            logger.info("post resource:[{}] success,param:[{}]", statusCode + serviceUrl);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 需要身份验证的get请求,默认编码UTF-8
     *
     * @param serviceUrl
     * @param userName      身份验证的用户名
     * @param password      身份验证的密码
     * @param connTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String get(String serviceUrl, String userName, String password, int connTimeout, int socketTimeout) throws Exception {
        return get(serviceUrl, userName, password, connTimeout, socketTimeout, UTF8);
    }

    /**
     * 需要身份验证的get请求,默认编码UTF-8
     *
     * @param serviceUrl
     * @param userName      身份验证的用户名
     * @param password      身份验证的密码
     * @param connTimeout
     * @param socketTimeout
     * @param charset
     * @return
     * @throws Exception
     */
    public static String get(String serviceUrl, String userName, String password, int connTimeout, int socketTimeout, String charset) throws Exception {
        if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception("userName or password is null or empty");
        }

        HttpResponse response = null;
        try {
            //构造请求配置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(connTimeout > 0 ? connTimeout : CONNECTION_TIMEOUT)
                    .setSocketTimeout(socketTimeout > 0 ? socketTimeout : SOCKET_TIME_OUT)
                    .build();

            //构造请求命令
            RequestBuilder requestBuilder = RequestBuilder.get(serviceUrl)
                    .setCharset(Charset.forName(charset))
                    .setConfig(requestConfig);

            requestBuilder.addHeader(authenticate(userName, password, "US-ASCII"));

            //执行请求
            response = httpClient.execute(requestBuilder.build());

            //判断请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity(), charset);
            if (statusCode / 100 > 3) {
                throw new Exception(String.format("get resource:[%s] userName=[%s] password=[%s] failed,return http state code:[%s],result:[%s]", serviceUrl, userName, password, statusCode, result));
            }

            logger.info("post resource:[{}] success,param:[{}]", statusCode + serviceUrl);
            logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static Header authenticate(final String name, final String password, final String charset) throws AuthenticationException {
        Args.notNull(name, "credentials name");
        Args.notNull(password, "credentials password");
        Args.notNull(charset, "credentials charset");

        final StringBuilder tmp = new StringBuilder();
        tmp.append(name).append(":").append(password);

        final Base64 base64codec = new Base64();
        final byte[] base64password = base64codec.encode(
                EncodingUtils.getBytes(tmp.toString(), charset));

        final CharArrayBuffer buffer = new CharArrayBuffer(32);

        buffer.append(AUTH.WWW_AUTH_RESP);
        buffer.append(": Basic ");
        buffer.append(base64password, 0, base64password.length);

        return new BufferedHeader(buffer);
    }


    private static boolean isEmptyMap(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    private static boolean isNotEmptyMap(Map<?, ?> map) {
        return !isEmptyMap(map);
    }
}
